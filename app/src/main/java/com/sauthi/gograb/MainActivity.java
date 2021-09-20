package com.sauthi.gograb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easywaylocation.EasyWayLocation;
import com.example.easywaylocation.GetLocationDetail;
import com.example.easywaylocation.Listener;
import com.example.easywaylocation.LocationData;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.Marker;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sauthi.gograb.cart.CartActivity;
import com.sauthi.gograb.global.GlobalFunctions;
import com.sauthi.gograb.global.GlobalVariables;
import com.sauthi.gograb.home.HomeFragment;
import com.sauthi.gograb.location.LocationListener;
import com.sauthi.gograb.map.ShowRestaurantOnMapFragment;
import com.sauthi.gograb.profile.ProfileFragment;
import com.sauthi.gograb.services.model.AddressModel;
import com.sauthi.gograb.services.model.NotificationModel;
import com.sauthi.gograb.view.AlertDialog;

import java.util.Arrays;
import java.util.List;

import static com.example.easywaylocation.EasyWayLocation.LOCATION_SETTING_REQUEST_CODE;

public class MainActivity extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, Listener, LocationData.AddressCallBack {

    public static String TAG = "MainActivity";

    EasyWayLocation easyWayLocation;
    GetLocationDetail getLocationDetail;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;
    GoogleApiClient mGoogleApiClient;

    static Intent locationintent;


    public static Context mainContext = null;

    public static final String BUNDLE_MAIN_NOTIFICATION_MODEL = "BundleMainModelNotificationModel";
    public static final String BUNDLE_KEY_TO_PAGE = "BundleKeyToPage";

    static Activity activity = null;
    Window mainWindow = null;
    FragmentManager mainActivityFM = null;

    GlobalFunctions globalFunctions;
    GlobalVariables globalVariables;

    View mainView;

    private LayoutInflater layoutInflater;

    static Toolbar toolbar;
    static ActionBar actionBar;
    static String mTitle;
    static int mResourceID, titleResourseID;
    static Menu menu;
    static TextView toolbar_title;
    static ImageView toolbar_logo;
    public static TextView header_tv;
    public static String address;
    Double latitude, longitude;


    private NotificationModel notificationModel = null;


    static BottomNavigationView bottom_nav_view;

    public static Intent newInstance(Context mainContext, NotificationModel notificationModel) {
        Intent intent = new Intent(mainContext, MainActivity.class);
        intent.putExtra(BUNDLE_MAIN_NOTIFICATION_MODEL, notificationModel);
        return intent;
    }

    public static Intent newInstance(Context context, String toPage) {
        Intent intent = new Intent(context, MainActivity.class);
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_TO_PAGE, toPage);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        mainContext = this;
        mainWindow = getWindow();
        mainActivityFM = getSupportFragmentManager();

        globalFunctions = AppController.getInstance().getGlobalFunctions();
        globalVariables = AppController.getInstance().getGlobalVariables();
        this.layoutInflater = activity.getLayoutInflater();


        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        //toolbar.setPadding(0, globalFunctions.getStatusBarHeight(this), 0, 0);
        toolbar.setContentInsetsAbsolute(0, 0);
        //toolbar.setNavigationIcon(navIconDrawable);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_logo = (ImageView) toolbar.findViewById(R.id.tool_bar_logo);


        header_tv = (TextView) findViewById(R.id.toolbar_title);

        mainView = toolbar;

        //access permission
        accessPermissions(this);

        getLocationDetail = new GetLocationDetail(this, this);
        easyWayLocation = new EasyWayLocation(this, false, true, this);
        if (permissionIsGranted()) {
            doLocationWork();
        } else {
            checkLocationPermission();

        }


        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        //  actionBar.setHomeAsUpIndicator(navIconDrawable);
        //   setOptionsMenuVisiblity(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.ColorStatusBar));
        }

        bottom_nav_view = (BottomNavigationView) findViewById(R.id.bottom_nav_view);
        // navigationView.setNavigationItemSelectedListener(this);
        // navigationHeaderView = navigationView.getHeaderView(0);

        mainActivityFM.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            @Override
            public void onBackStackChanged() {
                if (mainActivityFM != null) {
                    Fragment currentFragment = mainActivityFM.findFragmentById(R.id.container);
                    if (currentFragment != null) {
                        currentFragment.onResume();
                    }
                }
            }
        });

        Places.initialize(AppController.getInstance().getApplicationContext(), activity.getString(R.string.GoogleAPIKey));

        header_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> placefield = Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME);
                Intent i = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, placefield).build(MainActivity.this);
                startActivityForResult(i, AUTOCOMPLETE_REQUEST_CODE);
            }
        });


        bottom_nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_dashboard:
                        Fragment rfpFragment = new HomeFragment();
                        replaceFragment(rfpFragment, HomeFragment.TAG, "", 0, 0);
                        break;

                    case R.id.nav_places:

                        Fragment searPlaceFragment = new ShowRestaurantOnMapFragment();
                        replaceFragment(searPlaceFragment, ShowRestaurantOnMapFragment.TAG, "", 0, 0);
                        break;

                    case R.id.nav_cart:
                        Intent intent = new Intent(activity, CartActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_profile:
                        Fragment profileFragment = new ProfileFragment();
                        replaceFragment(profileFragment, ProfileFragment.TAG, "", 0, 0);
                        break;

                }

                return true;
            }
        });

        bottom_nav_view.setSelectedItemId(R.id.nav_dashboard);


        Fragment homeFragment = new HomeFragment();
        replaceFragment(homeFragment, HomeFragment.TAG, getString(R.string.app_name), 0, 0);

        AddressModel addressModel=GlobalFunctions.getAddress(mainContext);
        Log.d("address000",""+addressModel);
        if (addressModel!=null && addressModel.getAddress()!=null){
            header_tv.setText(addressModel.getAddress());
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOCATION_SETTING_REQUEST_CODE) {
            easyWayLocation.onActivityResult(resultCode);
        }

        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                toolbar_title.setText(place.getAddress());
                Log.e("TAG", "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public boolean permissionIsGranted() {

        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void doLocationWork() {
        easyWayLocation.startLocation();
    }

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    private void accessPermissions(MainActivity mainActivity) {
        int permissionCheck_getAccounts = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.GET_ACCOUNTS);
        int permissionCheck_callPhone = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE);
        int permissionCheck_lockwake = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WAKE_LOCK);
        int permissionCheck_internet = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.INTERNET);
        int permissionCheck_Access_internet = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_NETWORK_STATE);
        int permissionCheck_Access_wifi = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_WIFI_STATE);
        // int permissionCheck_External_storage = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        // int permissionCheck_cam = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CAMERA);

        if (permissionCheck_internet != PackageManager.PERMISSION_GRANTED || permissionCheck_Access_internet != PackageManager.PERMISSION_GRANTED || permissionCheck_callPhone != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.INTERNET) && ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.ACCESS_NETWORK_STATE) && ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.CALL_PHONE)) {
                Fragment homeFragment = null;
                homeFragment = new HomeFragment();
                mainActivityFM.beginTransaction().replace(R.id.container, homeFragment, "").commitAllowingStateLoss();
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.INTERNET, android.Manifest.permission.ACCESS_NETWORK_STATE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CALL_PHONE}, globalVariables.PERMISSIONS_REQUEST_PRIMARY);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                    }
                } else {
                    Toast.makeText(this, "permission denied",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    public static void setTitle(String title, int titleImageID, int backgroundResourceID) {
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
        // restoreToolbar();
    }

    private static void restoreToolbar() {
        //toolbar = (Toolbar) findViewById(R.id.tool_bar);
        Log.d(TAG, "Restore Tool Bar");
        if (actionBar != null) {
            Log.d(TAG, "Restore Action Bar not Null");
            Log.d(TAG, "Title : " + mTitle);
            if (titleResourseID != 0) {
                toolbar_logo.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.GONE);
                toolbar_logo.setImageResource(titleResourseID);
            } else {
                toolbar_logo.setVisibility(View.GONE);
                toolbar_title.setVisibility(View.VISIBLE);
                toolbar_title.setText(mTitle);
            }
            if (mResourceID != 0) toolbar.setBackgroundResource(mResourceID);
            //actionBar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public static int getTitleResourseID() {
        return titleResourseID;
    }

    public static void setTitleResourseID(int titleResourseID) {
        MainActivity.titleResourseID = titleResourseID;
        //restoreToolbar();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        //  setTitle("", 0, 0);
        //setNavigationHeaders();
        // setCartCount(globalFunctions.getCartCount());
        super.onResume();
    }

    public void setOptionsMenuVisiblity(boolean showMenu) {
        if (menu == null)
            return;
        //menu.setGroupVisible(R.id.menu, showMenu);
    }

    public static void closeThisActivity() {
        if (activity != null) {
            activity.finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (mainActivityFM != null) {
            String currentFragment = mainActivityFM.findFragmentById(R.id.container).getClass().getName();
            String homeFragment = null;
            homeFragment = HomeFragment.class.getName();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            /*if (drawer.isDrawerOpen(gravity)) {
                drawer.closeDrawers();
            }*/
            if (!currentFragment.equalsIgnoreCase(homeFragment) && mainActivityFM.getBackStackEntryCount() == 0) {
                Fragment shopFragment = null;
                shopFragment = new HomeFragment();
                replaceFragment(shopFragment, HomeFragment.TAG, "", 0, 0);
//                setIcon(activity.getResources().getDrawable(R.drawable.ic_civil));
            } else if (currentFragment.equalsIgnoreCase(homeFragment)) {
                /*Exit Alert Box*/
                final AlertDialog alertDialog = new AlertDialog(mainContext);
                alertDialog.setCancelable(false);
                alertDialog.setIcon(R.drawable.app_icon);
                alertDialog.setTitle(getString(R.string.app_name));
                alertDialog.setMessage(getResources().getString(R.string.appExitText));
                alertDialog.setPositiveButton(getString(R.string.yes), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        /*analyticsReport.closeApp(globalFunctions.getProfile());*/
                        finishAffinity();
                        System.exit(0);
                    }
                });

                alertDialog.setNegativeButton(getString(R.string.cancel), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();

            } else {
                super.onBackPressed();
                stimulateOnResumeFunction();
            }
        } else {
            super.onBackPressed();

        }
        stimulateOnResumeFunction();

    }

    private void replaceFragment(@Nullable Fragment allFragment, @Nullable String tag, @Nullable String title, int titleImageID, @Nullable int bgResID) {
        if (allFragment != null && mainActivityFM != null) {
            Fragment tempFrag = mainActivityFM.findFragmentByTag(tag);
            if (tempFrag != null) {
//                mainActivityFM.beginTransaction().remove(tempFrag).commitAllowingStateLoss();
                mainActivityFM.beginTransaction().remove(tempFrag).commit();
            }
            // setTitle( title, titleImageID, bgResID );
            FragmentTransaction ft = mainActivityFM.beginTransaction();
            // ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            //ft.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_out_right);
            ft.add(R.id.container, allFragment, tag).addToBackStack(tag).commitAllowingStateLoss();
        }
    }


    public void stimulateOnResumeFunction() {
        mainActivityFM.findFragmentById(R.id.container).onResume();
    }


    public void releseFragments() {
        for (int i = 0; i < mainActivityFM.getBackStackEntryCount(); ++i) {
            mainActivityFM.popBackStack();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void locationOn() {

    }

    @Override
    public void currentLocation(Location location) {
        getLocationDetail.getAddress(location.getLatitude(), location.getLongitude(), activity.getString(R.string.GoogleAPIKey));

    }

    @Override
    public void locationCancelled() {

    }

    @Override
    public void locationData(LocationData locationData) {
//        header_tv.setText(locationData.getFull_address());
//        Log.d("address00", "==" + locationData.getFull_address());
//        address = locationData.getFull_address();
//        getLatLongFromAddress(mainContext, address);

      /*  Intent mIntent = new Intent(activity, SearchPlaceOnMapActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString("key", locationData.getFull_address());
        Log.e("location","=="+locationData.getFull_address());
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
*/
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                    mLocationRequest, (com.google.android.gms.location.LocationListener) this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void OnLocationFetch(Location location) {

    }

    @Override
    public void OnProviderDisabled(String provider) {

    }

    @Override
    public void OnProviderEnabled(String provider) {

    }

    @Override
    public void OnLocationFailure(String msg) {

    }
}