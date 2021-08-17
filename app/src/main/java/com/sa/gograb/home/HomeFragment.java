package com.sa.gograb.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.snackbar.Snackbar;
import com.sa.gograb.AppController;
import com.sa.gograb.R;
import com.sa.gograb.adapter.HomeCategoryAdapter;
import com.sa.gograb.adapter.HomeTopCategoryListAdapter;
import com.sa.gograb.adapter.HomeSubCategoryListAdapter;
import com.sa.gograb.adapter.interfaces.OnWishlistClickInvoke;
import com.sa.gograb.menus.MenuListActivity;
import com.sa.gograb.restaurant.RestaurantListActivity;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.global.GlobalVariables;
import com.sa.gograb.location_service.LocationMonitoringService;
import com.sa.gograb.search.SearchActivity;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.CusineListModel;
import com.sa.gograb.services.model.CusineModel;
import com.sa.gograb.services.model.HomeCategoryModel;
import com.sa.gograb.services.model.HomeFilterCategoryListModel;
import com.sa.gograb.services.model.HomeFilterCategoryModel;
import com.sa.gograb.services.model.HomePageMainModel;
import com.sa.gograb.services.model.HomePageModel;
import com.sa.gograb.services.model.HomeSubCategoryListModel;
import com.sa.gograb.services.model.HomeSubCategoryModel;
import com.sa.gograb.services.model.HomeTopCategoryListModel;
import com.sa.gograb.services.model.HomeTopCategoryModel;
import com.sa.gograb.services.model.MenuModel;
import com.sa.gograb.services.model.SearchResponseModel;
import com.sa.gograb.services.model.StatusMainModel;
import com.sa.gograb.services.model.StatusModel;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnWishlistClickInvoke {

    public static final String TAG ="HomeFragment" ;


    public static final String TAG_Category = "categorylist";
    public static final String TAG_SubCategory = "HomeSubCategory";
    public static final String TAG_PopularRestro = "PopularRestro";

    public static final String BUNDLE_SEARCH_RESPONSE_MODEL = "Bundle_Search_Response_Model";

    Activity activity;
    Context context;

    View mainView;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    static Intent locationintent;
    private boolean mAlreadyStartedService = false;

    private TextView view_all_category_tv, view_all_sub_category_tv;
    CardView search_card_view;


    GlobalFunctions globalFunctions;
    GlobalVariables globalVariables;

    String restaurant_id=null;
    boolean isWishlisted=false;

    //home main category
    HomeCategoryAdapter homeCategoryAdapter;
    List<HomeFilterCategoryModel> homeFilterCategoryModels = new ArrayList<>();
    LinearLayoutManager homeCategory_linear;
    ProgressLinearLayout home_category_progress;
    RecyclerView home_category_recyclerview;

    //home sub category
    HomeTopCategoryListAdapter topCategoryListAdapter;
    List<HomeTopCategoryModel> homeTopCategoryModels = new ArrayList<>();
    List<CusineModel> cusineModels = new ArrayList<>();
    LinearLayoutManager home_subCategory_linear;
    ProgressLinearLayout home_top_category_progress;
    RecyclerView home_top_category_recyclerview;

   // home sub section category
    HomeSubCategoryListAdapter homeSubCategoryListAdapter;
    List<HomeSubCategoryModel> homeSubCategoryModels = new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager home_subSectionCat_linear;
    ProgressLinearLayout home_sub_category_progress;
    RecyclerView home_sub_category_recyclerview;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        activity = getActivity();
        context = getActivity();

        globalFunctions = AppController.getInstance().getGlobalFunctions();
        globalVariables = AppController.getInstance().getGlobalVariables();


        search_card_view=view.findViewById(R.id.search_card_view);

        //recyclerview id
        home_category_recyclerview = view.findViewById(R.id.home_category_recyclerview);
        home_top_category_recyclerview = view.findViewById(R.id.home_top_category_recyclerview);
        home_sub_category_recyclerview = view.findViewById(R.id.home_sub_category_recyclerview);

        //progress linear
        home_top_category_progress = view.findViewById( R.id.home_sub_category_progress );
        home_sub_category_progress = view.findViewById( R.id.home_sub_section_category_progress );
        home_category_progress = view.findViewById( R.id.home_category_progress );


        //view all id
        view_all_category_tv = view.findViewById(R.id.view_all_category_tv);
        view_all_sub_category_tv = view.findViewById(R.id.view_all_sub_category_tv);

        //layout manager
        homeCategory_linear = new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false);
        gridLayoutManager=new GridLayoutManager(activity,2,GridLayoutManager.HORIZONTAL, false);
        home_subCategory_linear = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        home_subSectionCat_linear = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);

        mainView = home_category_recyclerview;


        //update latlong
        locationintent = new Intent(activity, LocationMonitoringService.class);
        if (!mAlreadyStartedService) {
            startStep1();
        } else {
            //Intent intent = new Intent(activity, LocationMonitoringService.class);
            // activity.stopService(locationintent);
            // stopService(new Intent(SummaryActivity.this, LocationMonitoringService.class));
            //mAlreadyStartedService = false;
        }

        search_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, SearchActivity.class);
                startActivityForResult(intent,GlobalVariables.REQUEST_CODE_FOR_SEARCH);
            }
        });


        //home API's
        homeSubCategoryData();

        view_all_category_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = RestaurantListActivity.newInstance( activity, "1" );
                activity.startActivity( intent );

            }
        });

        view_all_sub_category_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = RestaurantListActivity.newInstance( activity, "2" );
                activity.startActivity( intent );
            }
        });

        return view;

    }


    private void homeSubCategoryData() {
        //globalFunctions.showProgress(activity, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getHomeSubCategory(context, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                if (context != null) {
                    //globalFunctions.hideProgress();
                    Log.d(TAG, "Response: " + arg0.toString());
                    HomePageMainModel homePageMainModel=(HomePageMainModel) arg0;
                    HomePageModel homePageModel = homePageMainModel.getHomePageModel();


                    if (homePageModel.getHomeFilterCategoryListModel()!= null) {
                        HomeFilterCategoryListModel homeFilterCategoryListModel =homePageModel.getHomeFilterCategoryListModel();
                        setUpCatFilterCatPage(homeFilterCategoryListModel);
                    }
                    if (homePageModel.getTop_near_rest()!= null) {
                        HomeTopCategoryListModel homeTopCategoryListModel =homePageModel.getTop_near_rest();
                        setUpSubCatPage(homeTopCategoryListModel);
                    }

                    if (homePageModel.getPopular_rest()!= null) {
                        HomeSubCategoryListModel subSection1CatListModel =homePageModel.getPopular_rest();
                        setUpSubSectionCatPage(subSection1CatListModel) ;
                    }


                }
            }

            @Override
            public void OnFailureFromServer(String msg) {
                if (context != null) {
                   // globalFunctions.hideProgress();
                    Log.d(TAG, "Failure : " + msg);
                }
            }

            @Override
            public void OnError(String msg) {
                if (context != null) {
                    // globalFunctions.hideProgress();
                    Log.d(TAG, "Error : " + msg);
                }
            }
        }, "Sub_Category");

    }
    private void setUpCatFilterCatPage(HomeFilterCategoryListModel homeTopCategoryListModel) {
        if (homeTopCategoryListModel != null && homeFilterCategoryModels != null) {
            homeFilterCategoryModels.clear();
            homeFilterCategoryModels.addAll( homeTopCategoryListModel.getHomeFilterCategoryModels());

            if (homeCategoryAdapter != null) {
                synchronized (homeCategoryAdapter) {
                    homeCategoryAdapter.notifyDataSetChanged();
                }
            }
            if (homeFilterCategoryModels.size() > 0)
            {
               // showFilterCatContent();
                subFilterCatInitRecycler();
            }
          /*  if (homeFilterCategoryModels.size() <= 0) {
                showFilterCatEmptyPage();

            } else {

            }*/
        }
    }

    private void setUpSubCatPage(HomeTopCategoryListModel homeTopCategoryListModel) {
        if (homeTopCategoryListModel != null && homeTopCategoryModels != null) {
            homeTopCategoryModels.clear();
            homeTopCategoryModels.addAll( homeTopCategoryListModel.getHomeTopCategoryModels());

           /* if (homeSubCategoryListAdapter != null) {
                synchronized (homeSubCategoryListAdapter) {
                    homeSubCategoryListAdapter.notifyDataSetChanged();
                }
            }*/

            homeTopCategoryModels.addAll(GlobalFunctions.getWishlist(homeTopCategoryListModel.getHomeTopCategoryModels(), true));
            homeTopCategoryModels.addAll(GlobalFunctions.getWishlist(homeTopCategoryListModel.getHomeTopCategoryModels(), false));

            if (homeTopCategoryModels.size() <= 0) {
                showSubCatEmptyPage();
            } else {
                showSubCatContent();
                subCatInitRecycler();
            }
        }
    }
    private void setUpSubSectionCatPage(HomeSubCategoryListModel subSection1CatListModel) {
        if (subSection1CatListModel != null && homeSubCategoryModels != null) {

            homeSubCategoryModels.clear();
            homeSubCategoryModels.addAll( subSection1CatListModel.getHomeSubCategoryModels() );
            if (homeSubCategoryListAdapter != null) {
                synchronized (homeSubCategoryListAdapter) {
                    homeSubCategoryListAdapter.notifyDataSetChanged();
                }
            }

            if (homeSubCategoryModels.size() <= 0) {
                showSubSectionCatEmptyPage();
            } else {
                showSubSectionCatContent();
                subSectionCatInitRecycler();
            }
        }
    }

    private void subCatInitRecycler() {
        home_top_category_recyclerview.setLayoutManager(home_subCategory_linear);
        home_top_category_recyclerview.setHasFixedSize(true);
        topCategoryListAdapter = new HomeTopCategoryListAdapter(activity, homeTopCategoryModels,this);
        home_top_category_recyclerview.setAdapter(topCategoryListAdapter);
    }

    private void subFilterCatInitRecycler() {
        home_category_recyclerview.setLayoutManager(homeCategory_linear);
        home_category_recyclerview.setHasFixedSize(true);
        homeCategoryAdapter = new HomeCategoryAdapter(activity,homeFilterCategoryModels);
        home_category_recyclerview.setAdapter(homeCategoryAdapter);
    }

    private void subSectionCatInitRecycler() {
        home_sub_category_recyclerview.setLayoutManager(home_subSectionCat_linear);
        home_sub_category_recyclerview.setHasFixedSize(true);
        homeSubCategoryListAdapter = new HomeSubCategoryListAdapter(activity, homeSubCategoryModels,this);
        home_sub_category_recyclerview.setAdapter(homeSubCategoryListAdapter);
    }

    private void showFilterCatContent() {
        if (home_category_progress != null) {
            home_category_progress.showEmpty(getResources().getDrawable(R.drawable.app_icon), getString(R.string.emptyList),
                    getString(R.string.not_available));
        }
    }
    private void showSubCatEmptyPage() {
        if (home_top_category_progress != null) {
            home_top_category_progress.showEmpty(getResources().getDrawable(R.drawable.app_icon), getString(R.string.emptyList),
                    getString(R.string.not_available));
        }
    }
    private void showSubSectionCatEmptyPage() {
        if (home_sub_category_progress != null) {
            home_sub_category_progress.showEmpty(getResources().getDrawable(R.drawable.app_icon), getString(R.string.emptyList),
                    getString(R.string.not_available));
        }
    }
    private void showSubCatContent() {
        if (home_top_category_progress != null) {
            home_top_category_progress.showContent();
        }
    }
     private void showFilterCatEmptyPage() {
            if (home_category_progress != null) {
                home_category_progress.showContent();
            }
        }

    private void showSubSectionCatContent() {
        if (home_sub_category_progress != null) {
            home_sub_category_progress.showContent();
        }
    }


    private void startStep1() {
        //Check whether this user has installed Google play service which is being used by Location updates.
        if (isGooglePlayServicesAvailable()) {
            //Passing null to indicate that it is executing for the first time.
            startStep2(null);
        } else {
            Toast.makeText(activity, R.string.no_google_playservice_available, Toast.LENGTH_LONG).show();
        }
    }
    public boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(context);
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
            }
            return false;
        }
        return true;
    }

    private Boolean startStep2(DialogInterface dialog) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            promptInternetConnect();
            return false;
        }

        if (dialog != null) {
            dialog.dismiss();
        }

        //Yes there is active internet connection. Next check Location is granted by user or not.

        if (checkPermissions()) { //Yes permissions are granted by the user. Go to the next step.
            startStep3();
        } else {  //No user has not granted the permissions yet. Request now.
            requestPermissions();
        }
        return true;
    }

    private void requestPermissions() {

        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.ACCESS_FINE_LOCATION);

        boolean shouldProvideRationale2 =
                ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.ACCESS_COARSE_LOCATION);


        // Provide an additional rationale to the img_user. This would happen if the img_user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale || shouldProvideRationale2) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");
            showSnackbar(R.string.permission_rationale,
                    android.R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            ActivityCompat.requestPermissions(activity,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    });
        } else {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the img_user denied the permission
            // previously and checked "Never ask again".
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void showSnackbar(final int mainTextStringId, final int actionStringId,
                              View.OnClickListener listener) {
        Snackbar.make(
                activity.findViewById(android.R.id.content),
                getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(actionStringId), listener).show();
    }

    private void startStep3() {
        //And it will be keep running until you close the entire application from task manager.
        //This method will executed only once.

        if (!mAlreadyStartedService) {
            //Start location sharing service to app server.........
            // Intent intent = new Intent(activity, LocationMonitoringService.class);
            // GlobalFunctions.setSharedPreferenceString(context, "extra_order_Id", order_vendor_product_id);
            activity.startService(locationintent);

            mAlreadyStartedService = true;
            //Ends................................................
        }
    }

    private void promptInternetConnect() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setTitle(R.string.no_internet);
        builder.setMessage(R.string.no_internet);

        String positiveText = getString(R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Block the Application Execution until user grants the permissions
                        if (startStep2(dialog)) {

                            //Now make sure about location permission.
                            if (checkPermissions()) {

                                //Step 2: Start the Location Monitor Service
                                //Everything is there to start the service.
                                startStep3();
                            } else if (!checkPermissions()) {
                                requestPermissions();
                            }
                        }
                    }
                });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void stopLocationService() {
        if (activity != null) {
            activity.stopService(locationintent);
        }
    }

    private boolean checkPermissions() {
        int permissionState1 = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION);

        int permissionState2 = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        return permissionState1 == PackageManager.PERMISSION_GRANTED && permissionState2 == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public void onResume() {
//        MainActivity.setNavigationBottomIcon(TAG);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();


        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getFragmentManager().findFragmentByTag(TAG) != null)
            getFragmentManager().findFragmentByTag(TAG).setRetainInstance(true);
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
    public void OnClickInvoke(int position, HomeTopCategoryModel model) {
        if (GlobalFunctions.isNotNullValue(model.getId())){
            restaurant_id=model.getId();
        }
        checkWishlist(restaurant_id);

    }

    @Override
    public void OnSubCategoryClickInvoke(int position, HomeSubCategoryModel homeSubCategoryModel) {
        if (GlobalFunctions.isNotNullValue(homeSubCategoryModel.getId())){
            restaurant_id= homeSubCategoryModel.getId();
        }
        checkWishlist(restaurant_id);
    }

    private void checkWishlist( String restaurant_id) {
        // globalFunctions.showProgress(activity, activity.getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getCheckWishList(context,restaurant_id, new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                //globalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                //StatusModel model = (StatusModel) arg0;
                validateOutputAfterWishList(arg0);
                homeSubCategoryData();
               // homeSubSectionListData();
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnFailureFromServer(String msg) {
                //  globalFunctions.hideProgress();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Failure : " + msg);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnError(String msg) {
                // globalFunctions.hideProgress();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Error : " + msg);
            }
        }, "Register_User");
    }

    private void validateOutputAfterWishList( Object arg0) {
        if (arg0 instanceof StatusMainModel) {
            StatusMainModel statusMainModel = (StatusMainModel) arg0;
            StatusModel statusModel = statusMainModel.getStatusModel();
            // globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());
            if (statusMainModel.isStatusLogin()) {
                if (isWishlisted){
                    //not wishlist icon

                    //iv_favourite.setImageResource(R.drawable.ic_favourite_grey);
                    isWishlisted=false;

                }else {
                    //wishlist icon
                    isWishlisted=true;
                    //iv_favourite.setImageResource(R.drawable.ic_favourite_grey);

                }

            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (resultCode == activity.RESULT_OK) {

            if (requestCode == globalVariables.REQUEST_CODE_FOR_SEARCH) {
                SearchResponseModel searchResponseModel = (SearchResponseModel) data.getExtras().getSerializable(SearchActivity.BUNDLE_SEARCH_RESPONSE_MODEL);
                if (searchResponseModel != null) {
                    if (searchResponseModel.getId() != null) {
                        MenuModel menuModel = new MenuModel();
                        menuModel.setId(searchResponseModel.getId());
                        Intent intent = MenuListActivity.newInstance(activity, menuModel);
                        activity.startActivity(intent);
                    }
                }
            }
        }
    }
}
