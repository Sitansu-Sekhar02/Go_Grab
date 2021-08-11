package com.sa.gograb.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sa.gograb.R;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.menus.MenuListActivity;
import com.sa.gograb.restaurant.RestaurantListActivity;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.RestaurantListMainModel;
import com.sa.gograb.services.model.RestaurantListModel;
import com.sa.gograb.services.model.RestaurantModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchRestaurantMapFragment extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,GoogleMap.OnMarkerClickListener {

    public static String TAG = "SearchRestaurantMapFragment";
    public static final String BUNDLE_MAIN_NOTIFICATION_MODEL = "BundleMainModelNotificationModel";
    Context context;
    Activity activity;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;

    View mainView;

    String restaurant_id=null;


    Window mainWindow = null;
    FragmentManager mainActivityFM = null;

    private CardView googleplacesearch_cv;

    RestaurantModel restaurantModel=new RestaurantModel();
    List<RestaurantModel> list = new ArrayList<RestaurantModel>();
    Map<Marker, Integer> markerMap = new HashMap<>();
    private LayoutInflater layoutInflater;


    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search_place_on_map, container, false);

        activity = getActivity();
        context = getActivity();

        googleplacesearch_cv=view.findViewById(R.id.google_placesearch_cv);
        this.layoutInflater = activity.getLayoutInflater();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.ColorStatusBar));
        }

        googleplacesearch_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, SearchPlaceOnMapActivity.class);
                startActivity(intent);
               // Fragment searchPlaceOnMapFragment = new SearchPlaceOnMapFragment();
               // replaceFragment(searchPlaceOnMapFragment, SearchPlaceOnMapFragment.TAG, "", 0, 0);
            }
        });
        mainView=googleplacesearch_cv;


        mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.g_map);
        mapFrag.getMapAsync(this);

        restaurantModel.setDistance(String.valueOf(50000));

        getRestaurantList();

        return view;
    }

    private void getRestaurantList() {
        GlobalFunctions.showProgress(context, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getAllHomeSubCategoryList(context,restaurantModel, new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());

                RestaurantListMainModel restaurantListMainModel = (RestaurantListMainModel) arg0;
                if (restaurantListMainModel!=null && restaurantListMainModel.getRestaurantListModel()!=null){
                    RestaurantListModel restaurantListModel = restaurantListMainModel.getRestaurantListModel();
                    setThisPage(restaurantListModel);
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
        }, "Store List");
    }

    private void setThisPage(RestaurantListModel restaurantListModel) {

        if (restaurantListModel != null && list != null) {
            list.clear();
            markerMap.clear();
            list.addAll(restaurantListModel.getRestaurantModels());
            int listSize = 0;

            if (list != null) {
                listSize = list.size();
            }

            if (listSize > 0) {
                for (int i = 0; i < listSize; i++) {
                    final RestaurantModel mStoreLocationModel = list.get(i);
                    if (mStoreLocationModel != null) {
                        double latitude = 0.0;
                        double longitude = 0.0;
                        try {
                            latitude = Double.parseDouble(mStoreLocationModel.getLatitude());
                            longitude = Double.parseDouble(mStoreLocationModel.getLongitude());
                        } catch (Exception e) {
                            latitude = 0.0;
                            longitude = 0.0;
                        }
                        LatLng latLong = new LatLng(latitude, longitude);

                        Marker marker = mGoogleMap.addMarker(new MarkerOptions().position(latLong).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_point)));
//                        Marker marker = mGoogleMap.addMarker(new MarkerOptions().position(latLong).icon(BitmapDescriptorFactory.fromBitmap(GlobalFunctions.getBitmapFromURL(mStoreLocationModel.getImage()))));
                        markerMap.put(marker, i);

                        if (i==0){
                            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLong, 11));
                        }

                    }
                }
            }
//            LatLng latLong = new LatLng(24.71, 46.67);
//            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(li, 11));
        }
    }

    @Override
    public void onResume() {
//        MainActivity.setNavigationBottomIcon(TAG);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap=googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.setOnMarkerClickListener(this);

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
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(activity,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(activity, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        /*mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
*/
        /*//Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);

        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));LocationManager locationManager = (LocationManager)
                getActivity().getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(new Criteria(), true);
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }
       *//* Location locations = locationManager.getLastKnownLocation(provider);
        List<String> providerList = locationManager.getAllProviders();
        if (null != locations && null != providerList && providerList.size() > 0) {
            double longitude = locations.getLongitude();
            double latitude = locations.getLatitude();
            Geocoder geocoder = new Geocoder(getActivity(),
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
        }*//*

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
        }*/

       /* mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));*/
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if (marker != null) {
            int position = markerMap.get(marker);
            setBranchData(list.get(position));
        }
        return true;
    }

    private void setBranchData(final RestaurantModel mStoreLocationModel) {

        if (mStoreLocationModel != null) {

            View view = layoutInflater.inflate(R.layout.activity_search_restaurant_popup, null, false);
            final BottomSheetDialog alertView = new BottomSheetDialog(activity);
            alertView.setContentView(view);
            alertView.setCancelable(true);

            CircleImageView iv_product_image = alertView.findViewById(R.id.iv_product_image);
            TextView tv_item_name = alertView.findViewById(R.id.tv_item_name);
            TextView tv_preparation_time = alertView.findViewById(R.id.tv_preparation_time);
            TextView tv_ratings = alertView.findViewById(R.id.tv_ratings);
            TextView tv_rating_count = alertView.findViewById(R.id.tv_rating_count);
            TextView tv_distance = alertView.findViewById(R.id.tv_distance);
            Button btn_view_menu = alertView.findViewById(R.id.btn_view_menu);




            if (mStoreLocationModel.getId() != null) {
                restaurant_id=mStoreLocationModel.getId();

            }

            if (mStoreLocationModel.getName() != null) {
                tv_item_name.setText(mStoreLocationModel.getName());
            }

            if (mStoreLocationModel.getPreparation_time() != null) {
                tv_preparation_time.setText(mStoreLocationModel.getPreparation_time()+" "+activity.getString(R.string.mins));
            }
            if (mStoreLocationModel.getRating() != null) {
                tv_ratings.setText(mStoreLocationModel.getRating());
            }
            if (mStoreLocationModel.getDistance() != null) {
                tv_distance.setText(mStoreLocationModel.getDistance());
            }
            if (mStoreLocationModel.getRating_count() != null) {
                tv_rating_count.setText("("+mStoreLocationModel.getRating_count()+"+)");
            }

            if (GlobalFunctions.isNotNullValue(mStoreLocationModel.getImage())) {
                Picasso.with(activity).load(mStoreLocationModel.getImage()).placeholder(R.drawable.app_icon).into(iv_product_image);
            }

            btn_view_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = MenuListActivity.newInstance( activity, mStoreLocationModel);
                    activity.startActivity( intent );
                }
            });

            alertView.show();

        }
    }
}
