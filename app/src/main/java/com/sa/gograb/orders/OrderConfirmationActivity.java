package com.sa.gograb.orders;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.mukesh.OtpView;
import com.sa.gograb.MainActivity;
import com.sa.gograb.R;
import com.sa.gograb.extra.DirectionFinder;
import com.sa.gograb.extra.DirectionFinderListener;
import com.sa.gograb.extra.Route;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.OrderDetailModel;
import com.sa.gograb.services.model.OrderDetailSecondMainModel;
import com.sa.gograb.services.model.OrderListModel;
import com.sa.gograb.services.model.OrderMainModel;
import com.sa.gograb.services.model.OrderModel;
import com.sa.gograb.services.model.RatingNFeedbackModel;
import com.sa.gograb.services.model.StatusMainModel;
import com.sa.gograb.services.model.StatusModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderConfirmationActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, DirectionFinderListener {

    private static final String TAG = "OrderConfirmationActivity",
                                 BUNDLE_CONFIRM_ORDER = "OrderConfirmId",
                                 BUNDLE_ORDER_NO = "OrderNo";

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    Context context;
    private static Activity activity;
    View mainView;

    static Toolbar toolbar;
    static ActionBar actionBar;
    static String mTitle;
    static int mResourceID;
    static int titleResourseID;
    static TextView toolbar_title;
    static ImageView toolbar_logo, tool_bar_back_icon;

    private TextView tv_feedback_title,tv_preparation_time,tv_order_no,tv_chat_with_restro;
    private  EditText et_feedback_comment;
    private CircleImageView iv_product_image,iv_menu_item;
    private ImageView iv_call,iv_location_to;
    private Button btn_submit;
    private Double latitude,longitude;
    private Double lat,lon;
    String restaurant_address;

    String originAddress;
    String destAddress;

    GlobalFunctions globalFunctions;

    String rating = "1";
    String restaurant_id=null;
    String order_id=null;
   GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    Location mLastLocation;
    Marker mCurrLocationMarker;

    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;

    RatingNFeedbackModel ratingNFeedbackModel;

    public static Intent newInstance(Activity activity, String restaurant_id, String order_id) {
        Intent intent = new Intent(activity, OrderConfirmationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_CONFIRM_ORDER, restaurant_id);
        bundle.putString(BUNDLE_ORDER_NO, order_id);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        context = this;
        activity = this;

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tool_bar_back_icon = (ImageView) toolbar.findViewById(R.id.toolbar_icon);
        iv_location_to = (ImageView) toolbar.findViewById(R.id.iv_location_to);
        tool_bar_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

        if (MainActivity.header_tv.getText()!=null){

            toolbar_title.setText(MainActivity.header_tv.getText());
            originAddress = toolbar_title.getText().toString();
            //Log.e("add00",""+originAddress);
            //get current address from latlong
            getLatlngFromAddress(context,originAddress);
        }


        tv_preparation_time = findViewById(R.id.tv_preparation_time);
        tv_order_no = findViewById(R.id.tv_order_no);
        iv_call = findViewById(R.id.iv_call);
        tv_chat_with_restro = findViewById(R.id.tv_chat_with_restro);
        iv_menu_item = findViewById(R.id.iv_menu_item);
        mainView=toolbar;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.ColorStatusBar));
        }

        if (getIntent().hasExtra(BUNDLE_CONFIRM_ORDER)) {
            restaurant_id = getIntent().getStringExtra(BUNDLE_CONFIRM_ORDER);
        } else {
            restaurant_id = null;
        }
        if (getIntent().hasExtra(BUNDLE_ORDER_NO)) {
            order_id = getIntent().getStringExtra(BUNDLE_ORDER_NO);
        } else {
            order_id = null;
        }

        mainView=toolbar;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                // Show your dialog here
                userFeedbackPopup();

            }
        }, 1500);

        orderDetails();




        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
       //setTitle(getString(R.string.places_search_error), 0, 0);

    }

    private String getAddressFromLatLng(double latitude, double longitude) {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                result.append(address.getLocality()).append("\n");
                result.append(address.getCountryName());
                restaurant_address= String.valueOf(address);
                // Log.e("restaurant_address00",""+restaurant_address);

            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();
    }

    private void orderDetails() {
        GlobalFunctions.showProgress(context, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getOrderDetails(context,order_id,new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                OrderDetailSecondMainModel orderMainModel = (OrderDetailSecondMainModel) arg0;
                OrderModel orderModel=orderMainModel.getOrderModel();

                if (orderModel!= null) {
                    setOrderDetails(orderModel);
                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnFailureFromServer(String msg) {
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Failure : " + msg);
                GlobalFunctions.displayMessaage(context, mainView, msg);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnError(String msg) {
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Error : " + msg);
                GlobalFunctions.displayMessaage(context, mainView, msg);
            }
        }, "Order Details");
    }

    private void setOrderDetails(OrderModel orderModel) {
        if (orderModel!=null ){
            if (GlobalFunctions.isNotNullValue(orderModel.getRest_preparation_time())){
                tv_preparation_time.setText(orderModel.getRest_preparation_time());
            }
            if (GlobalFunctions.isNotNullValue(orderModel.getOrder_id())){
                tv_order_no.setText("#"+orderModel.getOrder_id());
            }
            if (GlobalFunctions.isNotNullValue(orderModel.getRest_logo())) {
                Picasso.with(activity).load(orderModel.getRest_logo()).placeholder(R.drawable.lazy_load).into(iv_menu_item);
            }
            iv_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (GlobalFunctions.isNotNullValue(orderModel.getRest_mobile_number())) {
                        GlobalFunctions.callPhone(activity,orderModel.getRest_country_code()+""+ orderModel.getRest_mobile_number());
                    }
                }
            });
            if (GlobalFunctions.isNotNullValue(orderModel.getRest_laltitude())&& GlobalFunctions.isNotNullValue(orderModel.getRest_longitude())){
                latitude= Double.valueOf(orderModel.getRest_laltitude());
                Log.e("latitude",""+latitude);
                longitude= Double.valueOf(orderModel.getRest_longitude());
                getAddressFromLatLng(latitude,longitude);

            }
            iv_location_to.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDriveOnMap(orderModel);
                }
            });
            if (originAddress!=null && restaurant_address!=null){
               // Log.e("add111",""+restaurant_address);

                sendRequest();

            }else {

            }

        }
    }
    private void openDriveOnMap(OrderModel orderModel) {
        if (GlobalFunctions.isNotNullValue(orderModel.getRest_laltitude()) && GlobalFunctions.isNotNullValue(orderModel.getRest_longitude())) {
           /* String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            context.startActivity(intent);*/
            String url = "https://www.google.com/maps/dir/?api=1&destination=" + orderModel.getRest_laltitude()+ "," + orderModel.getRest_longitude() + "&travelmode=driving";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    }



    private void userFeedbackPopup() {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.feedback_activity);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        iv_product_image=dialog.findViewById(R.id.iv_product_image);
        tv_feedback_title=dialog.findViewById(R.id.tv_product_title);
        et_feedback_comment=dialog.findViewById(R.id.etv_Comment);
        btn_submit=dialog.findViewById(R.id.btn_submit);
        et_feedback_comment.clearFocus();

        final ImageView rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5;

        rating_iv1 = dialog.findViewById(R.id.rating_iv1);
        rating_iv2 = dialog.findViewById(R.id.rating_iv2);
        rating_iv3 = dialog.findViewById(R.id.rating_iv3);
        rating_iv4 = dialog.findViewById(R.id.rating_iv4);
        rating_iv5 = dialog.findViewById(R.id.rating_iv5);

        setImageView(5, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);


        rating_iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(1, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        rating_iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(2, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        rating_iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(3, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        rating_iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(4, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        rating_iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(5, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_feedback_comment != null) {
                    String
                            comment = et_feedback_comment.getText().toString().trim();

                    if (ratingNFeedbackModel == null) {
                        ratingNFeedbackModel = new RatingNFeedbackModel();
                    }
                    ratingNFeedbackModel.setRestaurant_id(restaurant_id);
                    ratingNFeedbackModel.setRating(rating);
                    ratingNFeedbackModel.setComment(comment);
                    dialog.dismiss();

                    insertFeedback(activity, ratingNFeedbackModel);

                }
            }

        });
    }
    private void setImageView(int position, ImageView rating_iv1, ImageView rating_iv2, ImageView rating_iv3, ImageView rating_iv4, ImageView rating_iv5) {
        switch (position) {
            case 1:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_grey_star);
                rating_iv3.setImageResource(R.drawable.ic_grey_star);
                rating_iv4.setImageResource(R.drawable.ic_grey_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = position + "";
                break;

            case 2:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_yellow_star);
                rating_iv3.setImageResource(R.drawable.ic_grey_star);
                rating_iv4.setImageResource(R.drawable.ic_grey_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = position + "";
                break;

            case 3:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_yellow_star);
                rating_iv3.setImageResource(R.drawable.ic_yellow_star);
                rating_iv4.setImageResource(R.drawable.ic_grey_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = position + "";
                break;

            case 4:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_yellow_star);
                rating_iv3.setImageResource(R.drawable.ic_yellow_star);
                rating_iv4.setImageResource(R.drawable.ic_yellow_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = position + "";
                break;

            case 5:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_yellow_star);
                rating_iv3.setImageResource(R.drawable.ic_yellow_star);
                rating_iv4.setImageResource(R.drawable.ic_yellow_star);
                rating_iv5.setImageResource(R.drawable.ic_yellow_star);
                rating = position + "";
                break;

            default:
                rating_iv1.setImageResource(R.drawable.ic_grey_star);
                rating_iv2.setImageResource(R.drawable.ic_grey_star);
                rating_iv3.setImageResource(R.drawable.ic_grey_star);
                rating_iv4.setImageResource(R.drawable.ic_grey_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = "1";
        }
    }

    private void insertFeedback(Activity activity, RatingNFeedbackModel ratingNFeedbackModel) {
        globalFunctions.showProgress(activity, activity.getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.insertFeedback(context, ratingNFeedbackModel, new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                globalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                //StatusModel model = (StatusModel) arg0;
                validateOutputAfterFeedback(arg0);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnFailureFromServer(String msg) {
                globalFunctions.hideProgress();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Failure : " + msg);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnError(String msg) {
                globalFunctions.hideProgress();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Error : " + msg);
            }
        }, "feedback");
    }
    private void validateOutputAfterFeedback(Object arg0) {
        if (arg0 instanceof StatusMainModel) {
            StatusMainModel feedbackNRatingMainModel = (StatusMainModel) arg0;
            StatusModel statusModel = feedbackNRatingMainModel.getStatusModel();
            if (!feedbackNRatingMainModel.isStatusLogin()) {
                globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());

            } else {
                globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());
                //goToMainActivity();

            }
        }
    }

    @Override
    public void onStop () {
        super.onStop();
    }

    public static void setTitle (String title,int titleImageID, int backgroundResourceID){
        mTitle = title;
        if (backgroundResourceID != 0) {
            mResourceID = backgroundResourceID;
        } else {
            mResourceID = 0;
        }
        if (titleImageID != 0) {
            titleResourseID = titleImageID;
        } else {
            titleResourseID = 0;
        }
        restoreToolbar();
    }

    @SuppressLint("LongLogTag")
    private static void restoreToolbar () {
        //toolbar = (Toolbar) findViewById(R.id.tool_bar);
        Log.d(TAG, "Restore Tool Bar");
        if (actionBar != null) {
            Log.d(TAG, "Restore Action Bar not Null");
            Log.d(TAG, "Title : " + mTitle);
            toolbar_title.setText(mTitle);
            if (mResourceID != 0) toolbar.setBackgroundResource(mResourceID);
            //actionBar.setTitle("");
            // actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public void onBackPressed () {
        closeThisActivity();
        Intent intent=new Intent(activity,MainActivity.class);
        startActivity(intent);
       // super.onBackPressed();
    }

    public static void closeThisActivity () {
        if (activity != null) {
            activity.finish();
            //activity.overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
        }
    }
    @Override
    public void onPause () {
        super.onPause();
        if (getFragmentManager().findFragmentByTag(TAG) != null)
            getFragmentManager().findFragmentByTag(TAG).setRetainInstance(true);
    }

    @Override
    public void onStart () {

       /* if(hint != null) {
            hint.launchAutomaticHintForCall(activity.findViewById(R.id.action_call));
        }*/
//       globalFunctions.launchAutomaticHintForSearch(mainView, getString(R.string.search_title),  getString(R.string.search_description));
        super.onStart();
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
    }

    private LatLng getLatlngFromAddress(Context context,String originAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;


        try {
            // May throw an IOException
            address = coder.getFromLocationName(originAddress, 5);
            if (address == null ) {
                return p1;
            }

            Address location = address.get(0);
            lat=location.getLatitude();
            Log.e("lat", "" + lat);

            lon=location.getLongitude();
            p1 = new LatLng(location.getLatitude(), location.getLongitude());
            //Log.e("originsssss", "" + p1);


        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);

        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        LocationManager locationManager = (LocationManager)
                activity.getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(new Criteria(), true);
        if (ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location locations = locationManager.getLastKnownLocation(provider);
        List<String> providerList = locationManager.getAllProviders();
        if (null != locations && null != providerList && providerList.size() > 0) {
            double longitude = locations.getLongitude();
            double latitude = locations.getLatitude();
            Geocoder geocoder = new Geocoder(activity,
                    Locale.getDefault());
            try {
                List<Address> listAddresses = geocoder.getFromLocation(latitude,
                        longitude, 1);
                if (null != listAddresses && listAddresses.size() > 0) {
                    String state = listAddresses.get(0).getAdminArea();
                    String country = listAddresses.get(0).getCountryName();
                    String subLocality = listAddresses.get(0).getSubLocality();
                    markerOptions.title("" + latLng + "," + subLocality + "," + state
                            + "," + country);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        markerOptions.icon(bitmapDescriptorFromVector(activity, R.drawable.ic_location_in)).title("Your Location");
        //dialog.cancel();
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(16));

        if (location != null)
        {

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(),location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(16)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder

            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            mGoogleMap.setMyLocationEnabled(true);
        }else {
            //buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,
                    this);
        }
    }
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap=googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }

    }
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(activity)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(activity,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
        }
    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(activity, activity.getString(R.string.please_wait),
                activity.getString(R.string.finding_direction), true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> route) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route routes : route) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(routes.startLocation, 16));

            originMarkers.add(mGoogleMap.addMarker(new MarkerOptions()
                    .icon(bitmapDescriptorFromVector(activity, R.drawable.ic_start_point))
                    .title(routes.startAddress)
                    .position(routes.startLocation)));
            destinationMarkers.add(mGoogleMap.addMarker(new MarkerOptions()
                    .icon(bitmapDescriptorFromVector(activity, R.drawable.ic_location_in))
                    .title(routes.endAddress)
                    .position(routes.endLocation)));


            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLACK).
                    width(10);

            for (int i = 0; i < routes.points.size(); i++)
                polylineOptions.add(routes.points.get(i));

            polylinePaths.add(mGoogleMap.addPolyline(polylineOptions));

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            LatLng origin = new LatLng(lat, lon);
            LatLng dest = new LatLng(latitude, longitude);
            builder.include(origin);
            builder.include(dest);
            LatLngBounds bounds = builder.build();

            int width = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (width * 0.30); // offset from edges of the map 10% of screen
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
            mGoogleMap.animateCamera(cu);


        }
    }
    private void sendRequest() {

        try {
            new DirectionFinder(this, originAddress, restaurant_address).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
