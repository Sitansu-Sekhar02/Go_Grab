package com.sa.gograb.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Address;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.sa.gograb.AppController;
import com.sa.gograb.R;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.global.GlobalVariables;
import com.sa.gograb.services.model.AddressModel;
import com.sa.gograb.services.model.UserAddressModel;
import com.sa.gograb.util.PermissionUtils;
import com.sa.gograb.widget.TouchableWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class MapActivity extends AppCompatActivity implements MapWrapperLayout.OnDragListener, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback, GoogleApiClient.OnConnectionFailedListener,
        TouchableWrapper.UpdateMapAfterUserInterection {

    public static final String
            TAG = "MapActivity",
            BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_ADDRESS_MODEL = "BundleSearchPlaceOnMapActivityAddressModel",
            BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_VENDOR_DETAIL_MODEL = "BundleSearchPlaceOnMapActivityVendorDetailModel",
            BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_USER_REQ_MODEL = "BundleSearchPlaceOnMapActivityUserReqModel",
            BUNDLE_USER_ADDRESS_ACTIVITY_USER_REQUEST_MODEL = "BundleUserAddressListActivityUserRequestModel",
            BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_LOCATION_TYPE = "BundleSearchPlaceOnMapActivityLocationType";


    private static final int CAMERA_ZOOM_LEVEL_FOR_MAP = 16;

    private static final int
            LOCATION_PERMISSION_REQUEST_CODE = 132,
            PERMISSION_ACCESS_COARSE_LOCATION = 156;

    final Handler handler = new Handler();
    Timer timer = new Timer();

    static Activity activity;

    Context context;
    View mainView;

    Toolbar toolbar;
    ActionBar actionBar;
    String mTitle;
    int mResourceID, titleResourseID;
    TextView toolbar_title;
    ImageView toolbar_icon;
    //AppBarLayout appBarLayout;
    Menu menu;
    public static RelativeLayout cart_notification_layout;

    String addressType = "-1";
    UserAddressModel userAddressModel = null;
    //This is for restriciting search only to india, for other countries you can specify lat lng by changing the below values.
    private static final LatLngBounds BOUNDS_GREATER_INDIA = new LatLngBounds(
            new LatLng(8.062148, 68.212642), new LatLng(37.372499, 96.513423));

    protected GoogleApiClient mGoogleApiClient;

    private static int RESEND_OTP_TOTAL_TIME_OUT = 5000;
    private static int RESEND_OTP_TIME_INTERVAL = 1000;

    private PlaceAutocompleteAdapter mAdapter;
    private AutoCompleteTextView mAutocompleteView;

    private GlobalVariables.LOCATION_TYPE locationType = GlobalVariables.LOCATION_TYPE.NONE;

    private AddressModel mAddress;
    boolean miSConnectedDone = false;

    UserAddressModel model = null;
    //VendorDetailModel vendorDetailModel = null;

    private View mMarkerParentView;
    private ImageView mMarkerImageView;
    private CardView googleplacesearch_cv;

    private PlacesClient placesClient;

    private int
            imageParentWidth = -1,
            imageParentHeight = -1,
            imageHeight = -1,
            centerX = -1,
            centerY = -1;

    private CustomMapFragment
            mCustomMapFragment;

    private GoogleMap
            mMap;

    private GoogleApiClient
            googleApiClient;

    private GlobalFunctions
            globalFunctions;

    private OnSuccessListener fetchPlaceResponseHandler = new OnSuccessListener() {
        @Override
        public void onSuccess(Object object) {
            if (object instanceof FetchPlaceResponse) {
                FetchPlaceResponse fetchPlaceResponse = (FetchPlaceResponse) object;
                if (fetchPlaceResponse != null) {
                    fetchPlaceResponse.getPlace();
                    hideKeyboard();
                    Place place = fetchPlaceResponse.getPlace();
                    String address = place.getAddress();
                    if (mAddress == null) {
                        mAddress = new AddressModel();
                    }
                    mAddress.setAddress(address);
                    mAddress.setLatitude(place.getLatLng().latitude);
                    mAddress.setLongitude(place.getLatLng().longitude);

                    LatLng newLatLngTemp = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                    updateLocation(newLatLngTemp, true);

                }
            }
        }
    };

/*    private ResultCallback<PlaceBuffer>
            mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
                @Override
                public void onResult(PlaceBuffer places) {
                    if (!places.getStatus().isSuccess()) {
                        places.release();
                        return;
                    }
                    final Place place = places.get(0);
                    hideKeyboard();
                    String address = place.getAddress().toString();
                    if(mAddress == null){mAddress = new AddressModel();}
                    mAddress.setAddress(address);
                    mAddress.setLatitude(place.getLatLng().latitude);
                    mAddress.setLongitude(place.getLatLng().longitude);

                    LatLng newLatLngTemp = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                    updateLocation(newLatLngTemp, true);
                }
            };*/

    /*  private AdapterView.OnItemClickListener
              mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                      final PlaceAutocompleteAdapter.PlaceAutocomplete item = mAdapter.getItem(position);
                      final String placeId = String.valueOf(item.placeId);

                      PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                              .getPlaceById(mGoogleApiClient, placeId);
                      placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
                  }
              };
  */
    private AdapterView.OnItemClickListener
            mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            final PlaceAutocompleteAdapter.PlaceAutocomplete item = mAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);

            final List<Place.Field> fieldList = new ArrayList<>();
            fieldList.add(Place.Field.NAME);
            fieldList.add(Place.Field.ADDRESS);
            fieldList.add(Place.Field.LAT_LNG);

            FetchPlaceRequest fetchPlaceRequest =
                    FetchPlaceRequest.builder(placeId, fieldList)
                            .build();

            Task<FetchPlaceResponse> fetchPlaceRequestTask = placesClient.fetchPlace(fetchPlaceRequest);

            fetchPlaceRequestTask.addOnSuccessListener(fetchPlaceResponseHandler);

        }
    };

    public static Intent newInstance(Context context, UserAddressModel model, AddressModel addressModel, GlobalVariables.LOCATION_TYPE locationType) {
        Intent intent = new Intent(context,MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_ADDRESS_MODEL, addressModel);
        bundle.putSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_USER_REQ_MODEL, model);
        bundle.putSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_LOCATION_TYPE, locationType);
        intent.putExtras(bundle);
        return intent;
    }


    public static Intent newInstance(Context context, AddressModel addressModel, GlobalVariables.LOCATION_TYPE locationType) {
        Intent intent = new Intent(context,MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_ADDRESS_MODEL, addressModel);
        bundle.putSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_LOCATION_TYPE, locationType);
        intent.putExtras(bundle);
        return intent;
    }

    public static Intent newInstance(Context context, AddressModel addressModel) {
        Intent intent = new Intent(context,MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_ADDRESS_MODEL, addressModel);
        bundle.putSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_LOCATION_TYPE, GlobalVariables.LOCATION_TYPE.NONE);
        intent.putExtras(bundle);
        return intent;
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_ADDRESS_MODEL, null);
        bundle.putSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_LOCATION_TYPE, GlobalVariables.LOCATION_TYPE.NONE);
        intent.putExtras(bundle);
        return intent;
    }

    public static Intent newInstance(Context context, GlobalVariables.LOCATION_TYPE locationType) {
        Intent intent = new Intent(context, MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_ADDRESS_MODEL, null);
        bundle.putSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_LOCATION_TYPE, locationType);
        intent.putExtras(bundle);
        return intent;
    }

 /*   public static Intent newInstance(Context context, VendorDetailModel vendorDetailModel, AddressModel addressModel, GlobalVariables.LOCATION_TYPE locationType) {
        Intent intent = new Intent(context, MapActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_VENDOR_DETAIL_MODEL, vendorDetailModel);
        bundle.putSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_ADDRESS_MODEL, addressModel);
        bundle.putSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_LOCATION_TYPE, locationType);
        intent.putExtras(bundle);
        return intent;
    }*/

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        activity = this;
        context = this;

        Places.initialize(AppController.getInstance().getApplicationContext(), activity.getString(R.string.GoogleAPIKey));

        initilizeMap();

        globalFunctions = AppController.getInstance().getGlobalFunctions();

        //  globalFunctions = AppController.getInstance().getGlobalFunctions();

        mAutocompleteView = (AutoCompleteTextView) findViewById(R.id.googleplacesearch);
        mMarkerParentView = findViewById(R.id.marker_view_incl);
        mMarkerImageView = (ImageView) findViewById(R.id.marker_icon_view);

        googleplacesearch_cv = (CardView) findViewById(R.id.googleplacesearch_cv);

        //mAutocompleteView.setPadding(0, GlobalFunctions.getStatusBarHeight(context), 0, 0);

        mainView = mAutocompleteView;

        placesClient = Places.createClient(context);

        mAdapter = new PlaceAutocompleteAdapter(this, R.layout.google_places_search_items, placesClient, null);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        //toolbar.setPadding(0, GlobalFunctions.getStatusBarHeight(context), 0, 0);
        //toolbar.setNavigationIcon(R.drawable.ic_back_draw);
        //toolbar.setContentInsetsAbsolute(0,0);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_icon = (ImageView) toolbar.findViewById(R.id.toolbar_icon);
        toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        setOptionsMenuVisiblity(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.ColorPrimary));
        }

        mAutocompleteView.setAdapter(mAdapter);
        mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);

        mAutocompleteView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (mAutocompleteView.getRight() - mAutocompleteView.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        mAutocompleteView.setText("");
                        return true;
                    }
                }
                if (event.getRawX() <= (mAutocompleteView.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
                    setResult(false);
                    return true;
                }
                return false;
            }
        });

        try {
            Intent intent = getIntent();
            mAddress = (AddressModel) intent.getExtras().getSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_ADDRESS_MODEL);
            if (mAddress != null) {
                if (mAddress.getLatitude() == 0 || mAddress.getLongitude() == 0) {
                    GetLatLon();
                } else {
                    LatLng newLatLngTemp = new LatLng(mAddress.getLatitude(), mAddress.getLongitude());
                    updateLocation(newLatLngTemp, true);
                }
            } else {
                GetLatLon();
            }
        } catch (Exception e) {
        }

        try {
            Intent intent = getIntent();
            model = (UserAddressModel) intent.getExtras().getSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_USER_REQ_MODEL);
        } catch (Exception e) {
            model = null;
        }

      /*  try {
            Intent intent = getIntent();
            vendorDetailModel = (VendorDetailModel) intent.getExtras().getSerializable(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_VENDOR_DETAIL_MODEL);
        } catch (Exception e) {
            vendorDetailModel = null;
        }*/

        try {
            Intent intent = getIntent();
            locationType = (GlobalVariables.LOCATION_TYPE) intent.getSerializableExtra(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_LOCATION_TYPE);
        } catch (Exception e) {
            locationType = null;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

      /*  if (locationType == GlobalVariables.LOCATION_TYPE.DRIVER_LOCATION) {
            mMarkerImageView.setBackground(activity.getResources().getDrawable(R.drawable.ic_car));
        } else {
            mMarkerImageView.setBackground(activity.getResources().getDrawable(R.drawable.ic_pin));
        }*/

        imageParentWidth = mMarkerParentView.getWidth();
        imageParentHeight = mMarkerParentView.getHeight();
        imageHeight = mMarkerImageView.getHeight();

        centerX = imageParentWidth / 2;
        centerY = (imageParentHeight / 2) + (imageHeight / 2);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }


    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

        }
    }

    private void initilizeMap() {
        if (mMap == null) {
            mCustomMapFragment = ((CustomMapFragment) getFragmentManager().findFragmentById(R.id.map));
            mCustomMapFragment.setOnDragListener(this);
            mCustomMapFragment.getMapAsync(this);
            if (mMap == null) {
                Log.d(TAG, "Sorry! unable to create maps");
            }
        }
    }

    public void setLatLng(double latitude, double longitude) {
        if (mAddress == null) {
            mAddress = new AddressModel();
        }
        mAddress.setLatitude(latitude);
        mAddress.setLongitude(longitude);
    }

    public LatLng getLatLng() {
        if (mAddress != null) {
            return new LatLng(mAddress.getLatitude(), mAddress.getLongitude());
        }
        return null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();
        updateLocation(getLatLng(), true);
    }

    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        setLatLng(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude);
        updateLocation(getLatLng(), true);
        return false;
    }

    @Override
    protected void onResume() {
        setTitle(getString(R.string.address), 0, 0);
        super.onResume();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }


    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (!miSConnectedDone) {
            miSConnectedDone = true;
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                try {
                    @SuppressLint("MissingPermission") Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    double lat = lastLocation.getLatitude(), lon = lastLocation.getLongitude();
                    setLatLng(lat, lon);
                    updateLocation(getLatLng(), true);
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public void GetLatLon() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_ACCESS_COARSE_LOCATION);
        } else {
            try {
                @SuppressLint("MissingPermission") Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                double lat = lastLocation.getLatitude(), lon = lastLocation.getLongitude();
                setLatLng(lat, lon);
            } catch (Exception e) {
            }

            if (sLocationEnabled(this)) {
                googleApiClient = new GoogleApiClient.Builder(this, this, this).addApi(LocationServices.API).build();
                googleApiClient.connect();
            } else {
                showSettingsAlert();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
        super.onStop();
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MapActivity.this);

        // Setting Dialog Title
        alertDialog.setTitle("GPS settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
               MapActivity.this.startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public boolean sLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    private void setResult(boolean isSuccess) {

        Intent intent = new Intent();
        intent.putExtra(BUNDLE_SEARCH_PLACE_ON_MAP_ACTIVITY_ADDRESS_MODEL, mAddress);

        if (isSuccess) setResult(RESULT_OK, intent);
        else setResult(RESULT_CANCELED, intent);
        // landmark_etv.setText(mAddress.getLandmark());

        if (userAddressModel == null) {
            userAddressModel = new UserAddressModel();
        }
       // userAddressModel.setAddress1(mAddress.getAddress());
        userAddressModel.setLatitude(mAddress.getLatitude() + "");
        userAddressModel.setLongitude(mAddress.getLongitude() + "");
        userAddressModel.setLongitude(mAddress.getLongitude() + "");
      //  userAddressModel.setLandmark(mAddress.getLandmark());

//        closeThisActivity();
    }

    public void setTitle(String title, int titleImageID, int backgroundResourceID) {
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

    private void restoreToolbar() {
        //toolbar = (Toolbar) findViewById(R.id.tool_bar);
        Log.d(TAG, "Restore Tool Bar");
        if (actionBar != null) {
            Log.d(TAG, "Restore Action Bar not Null");
            Log.d(TAG, "Title : " + mTitle);
            if (titleResourseID != 0) {
                toolbar_title.setVisibility(View.GONE);
            } else {
                toolbar_title.setVisibility(View.VISIBLE);
                toolbar_title.setText(mTitle);
            }


            if (mResourceID != 0) toolbar.setBackgroundResource(mResourceID);
            //actionBar.setTitle("");
            //actionBar.setDisplayHomeAsUpEnabled(true);
        }

       /* if (appBarLayout != null) {
            appBarLayout.bringToFront();
        }*/

    }

   /* public static void setCartCount(final int count) {
        if (cart_notification_layout != null) {
            final TextView tv = (TextView) cart_notification_layout.findViewById(R.id.actionbar_badge_cart_textview);
            if (activity != null) {
                if (count == 0) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setVisibility(View.GONE);
                        }
                    });

                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setVisibility(View.VISIBLE);
                            tv.setText(count + "");
                        }
                    });
                }
            }
        }
    }*/

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        this.menu = menu;
        setOptionsMenuVisiblity(false);
        MenuItem item = menu.findItem(R.id.action_cart);
        MenuItemCompat.setActionView(item, R.layout.actionbar_badge_cart_layout);
        cart_notification_layout = (RelativeLayout) MenuItemCompat.getActionView(item);
        cart_notification_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        globalFunctions.setCartCount(context, globalFunctions.getCartCount(context));

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        this.menu = menu;
        setOptionsMenuVisiblity(false);
        MenuItem item = menu.findItem(R.id.action_cart);
        MenuItemCompat.setActionView(item, R.layout.actionbar_badge_cart_layout);
        cart_notification_layout = (RelativeLayout) MenuItemCompat.getActionView(item);
        cart_notification_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        globalFunctions.setCartCount(context, globalFunctions.getCartCount(context));

        return true;
    }*/

    public void setOptionsMenuVisiblity(boolean showMenu) {
        if (menu == null)
            return;
        //menu.setGroupVisible(R.id.menu, showMenu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                break;
        }
        return false;
    }


    @Override
    public void onDrag(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            Projection projection = (mMap != null && mMap.getProjection() != null) ? mMap.getProjection() : null;
            if (projection != null) {
                LatLng centerLatLng = projection.fromScreenLocation(new Point(
                        centerX, centerY));
                updateLocation(centerLatLng, false);
            }
        }
    }

    private void updateLocation(LatLng centerLatLng, boolean isCameraZoom) {
        if (centerLatLng != null) {
            setLatLng(centerLatLng.latitude, centerLatLng.longitude);

            if (isCameraZoom) {
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(centerLatLng, CAMERA_ZOOM_LEVEL_FOR_MAP);
                if (mMap != null)
                    mMap.animateCamera(cameraUpdate);
                /*All Zooming camer is an showcase so we dont want to show the suggestion again to stop the loop of Functionality.*/
                //mAutocompleteView.setOnItemClickListener(null);
            }

            Address address = globalFunctions.getAddressfromLatLng(activity, centerLatLng.latitude, centerLatLng.longitude);

            if (address != null) {
                mAddress = globalFunctions.getAddressModelFromAddress(address);
            }
            String completeAddress = null;
            if (mAddress.getAddress() != null) {
                completeAddress = mAddress.getAddress();
            } else {
                completeAddress = globalFunctions.getAddressTextFromModelExcludingNameAndAddresswithComma(context, mAddress);
            }

            if (completeAddress != null) {
                //mAutocompleteView.setOnItemClickListener(null);
                mAutocompleteView.setText(completeAddress);
                mAutocompleteView.dismissDropDown();
                // address_etv.setText(mAddress.getAddress() );
                //landmark_etv.setText(mAddress.getLandmark());
                //mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);

                if (userAddressModel == null) {
                    userAddressModel = new UserAddressModel();
                }
             //   userAddressModel.setAddress1(mAddress.getAddress());
                userAddressModel.setLatitude(mAddress.getLatitude() + "");
                userAddressModel.setLongitude(mAddress.getLongitude() + "");
                // userAddressModel.setLandmark(mAddress.getLandmark());
            }
        }
    }

    @Override
    public void onBackPressed() {
        //  setResult(false);
        closeThisActivity();
        //onBackPressed();
    }

    public static void closeThisActivity() {
        if (activity != null) {
            activity.finish();
        }
    }

    @Override
    public void onUpdateMapAfterUserInterection() {

    }
}
