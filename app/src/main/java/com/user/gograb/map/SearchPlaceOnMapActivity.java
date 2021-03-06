package com.user.gograb.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
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
import com.google.android.libraries.places.api.Places;
import com.user.gograb.R;
import com.user.gograb.extra.DirectionFinder;
import com.user.gograb.extra.DirectionFinderListener;
import com.user.gograb.extra.Route;
import com.user.gograb.global.GlobalVariables;
import com.user.gograb.search.SearchActivity;
import com.user.gograb.services.model.SearchResponseModel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchPlaceOnMapActivity extends AppCompatActivity  implements OnMapReadyCallback, DirectionFinderListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    public static String TAG = "SearchPlaceOnMapFragment";
    public static final String BUNDLE_MAIN_NOTIFICATION_MODEL = "BundleMainModelNotificationModel";

    private String direction_api_key = "AIzaSyDPXn5_pPHwthkHa3m9-kIiwVnCOQq7rzI";
    private static int AUTOCOMPLETE_REQUEST_CODE_CURRENT_LOCATION = 1;
    private static int AUTOCOMPLETE_REQUEST_CODE_DESTINATION_LOCATION = 2;

    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;


    Double lat,lng;
    Double latitude,longitude;

    String originAddress;
    String currentLocAddress;
    String destAddress;


    Context context;
    private static Activity activity;
    Window mainWindow = null;


    static Toolbar toolbar;
    static ActionBar actionBar;
    static String mTitle;
    static int mResourceID;
    static int titleResourseID;
    static TextView toolbar_title;
    static TextView tv_current_location,tv_destination_location;
    static ImageView toolbar_logo, tool_bar_back_icon;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    GoogleMap gMap;
    SupportMapFragment mapFrag;

    private CardView googleplacesearch_cv;
    SupportMapFragment mapFragment;

    private CustomMapFragment
            mCustomMapFragment;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    //private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);

        context = this;
        activity = this;
        mainWindow = getWindow();


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tool_bar_back_icon = (ImageView) toolbar.findViewById(R.id.toolbar_icon);
        tool_bar_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.ColorStatusBar));
        }

        tv_current_location=findViewById(R.id.tv_current_location);
        tv_destination_location=findViewById(R.id.tv_destination_location);



        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);




        // Initialize Places.
        if (!Places.isInitialized()) {
            Places.initialize(activity, getString(R.string.GoogleAPIKey));
        }

       /* tv_current_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Place.Field> placefield = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);
                Intent i = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, placefield).build(activity);
                startActivityForResult(i, AUTOCOMPLETE_REQUEST_CODE_CURRENT_LOCATION);
            }
        });*/
        tv_destination_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SearchActivity.newInstance( activity,"1");
               // Intent intent = new Intent(activity, SearchActivity.class);
                startActivityForResult(intent, GlobalVariables.REQUEST_CODE_FOR_SEARCH);
               /* List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fieldList).build(activity);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE_DESTINATION_LOCATION);
*/
            }
        });

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
            latitude=location.getLatitude();
            longitude=location.getLongitude();
            p1 = new LatLng(location.getLatitude(), location.getLongitude());
            //Log.e("originsssss", "" + p1);


        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
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
                destAddress= String.valueOf(address);
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();
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
        super.onBackPressed();
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



    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap=googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

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
                gMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
            buildGoogleApiClient();
            gMap.setMyLocationEnabled(true);
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
        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
                        gMap.setMyLocationEnabled(true);
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
    public void onResume() {
//        MainActivity.setNavigationBottomIcon(TAG);

        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (resultCode == activity.RESULT_OK) {

            if (requestCode == GlobalVariables.REQUEST_CODE_FOR_SEARCH) {
                SearchResponseModel searchResponseModel = (SearchResponseModel) data.getExtras().getSerializable(SearchActivity.BUNDLE_SEARCH_RESPONSE_MODEL);
                if (searchResponseModel != null) {
                    lat= Double.valueOf(searchResponseModel.getLatitude());
                    lng= Double.valueOf(searchResponseModel.getLongitude());
                    getAddressFromLatLng(lat,lng);

                    tv_destination_location.setText(searchResponseModel.getName());

                    //for draw route between two location
                    sendRequest();
                }
            }
        }
    }
    private void sendRequest() {

        try {
            new DirectionFinder(this, originAddress, destAddress).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
        mCurrLocationMarker = gMap.addMarker(markerOptions);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        gMap.animateCamera(CameraUpdateFactory.zoomTo(16));

        if (location != null)
        {

            Log.d("location111",""+location);

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(),location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(16)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder

            gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            gMap.setMyLocationEnabled(true);

            lat=location.getLatitude();
            lng=location.getLongitude();
            getOriginAddressFromLatLng(lat,lng);

            originAddress = tv_current_location.getText().toString();
            //get current address from latlong
            getLatlngFromAddress(context,originAddress);

        }else {
            //buildGoogleApiClient();
            gMap.setMyLocationEnabled(true);
        }
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,
                    this);
        }
    }

    private void getOriginAddressFromLatLng(Double lat, Double lng) {
       /* StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                result.append(address.getLocality());
                result.append(address.getLocality());
                result.append(address.getCountryName());
                currentLocAddress= String.valueOf(address);
                Log.d("aaaaa111",""+currentLocAddress);
                tv_current_location.setText(currentLocAddress);

            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();*/
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            currentLocAddress= String.valueOf(address);
            Log.d("aaaaa111",""+currentLocAddress);
            tv_current_location.setText(currentLocAddress);
        }catch (Exception e){
            Log.e("tag", e.getMessage());

        }

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
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(routes.startLocation, 16));

            originMarkers.add(gMap.addMarker(new MarkerOptions()
                    .icon(bitmapDescriptorFromVector(activity, R.drawable.ic_start_point))
                    .title(routes.startAddress)
                    .position(routes.startLocation)));
            destinationMarkers.add(gMap.addMarker(new MarkerOptions()
                    .icon(bitmapDescriptorFromVector(activity, R.drawable.ic_location_in))
                    .title(routes.endAddress)
                    .position(routes.endLocation)));


            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLACK).
                    width(10);

            for (int i = 0; i < routes.points.size(); i++)
                polylineOptions.add(routes.points.get(i));

            polylinePaths.add(gMap.addPolyline(polylineOptions));

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            LatLng origin = new LatLng(latitude, longitude);
            LatLng dest = new LatLng(lat, lng);
            builder.include(origin);
            builder.include(dest);
            LatLngBounds bounds = builder.build();

            int width = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (width * 0.30); // offset from edges of the map 10% of screen
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
            gMap.animateCamera(cu);


        }
    }

}
