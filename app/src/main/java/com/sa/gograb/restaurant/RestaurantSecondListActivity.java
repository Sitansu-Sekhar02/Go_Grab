package com.sa.gograb.restaurant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sa.gograb.R;
import com.sa.gograb.adapter.RestaurantListAdapter;
import com.sa.gograb.adapter.RestaurantSecondListAdapter;
import com.sa.gograb.adapter.interfaces.WishListClickListener;
import com.sa.gograb.filter.FilterActivity;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.RestaurantListMainModel;
import com.sa.gograb.services.model.RestaurantListModel;
import com.sa.gograb.services.model.RestaurantModel;
import com.sa.gograb.services.model.StatusMainModel;
import com.sa.gograb.services.model.StatusModel;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

public class RestaurantSecondListActivity extends AppCompatActivity implements WishListClickListener {
    private static final String TAG = "RestaurantListActivity",

                                BUNDLE_POPULAR_CATEGORY = "PopularCategoryList",
                                BUNDLE_POPULAR_RESTAURANT_NEAREST = "PopularNearestRestro";
    Context context;
    private static Activity activity;
    View mainView;

    String sort=null;
    int index=0;
    int size=100;

    String restaurant_id=null;
    boolean isWishlisted=false;



    static Toolbar toolbar;
    static ActionBar actionBar;
    static String mTitle;
    static int mResourceID;
    static int titleResourseID;
    static TextView toolbar_title;
    static ImageView toolbar_logo, tool_bar_back_icon,iv_filter,ic_filter_resturant,iv_filter_rest;


    RestaurantListAdapter restaurantListAdapter;
    RestaurantSecondListAdapter restaurantSecondListAdapter;
    List<RestaurantModel> restaurantModels = new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    LinearLayoutManager linearLayoutManager;
    ProgressLinearLayout details_progressActivity;
    RecyclerView sub_category_recyclerview;
    SwipeRefreshLayout swipe_container;

   /* VendorListAdapter vendorListAdapter;
    List<VendorStoreModel> vendorStoreModels = new ArrayList<>();
    LinearLayoutManager storelistLinear;
    ProgressLinearLayout reviewprogressActivity;
    RecyclerView vendor_store_list_recyclerview;
    SwipeRefreshLayout review_swipe_container;*/

    RestaurantModel restaurantModel=new RestaurantModel();

    public static Intent newInstance(Activity activity, String sort) {
        Intent intent = new Intent(activity, RestaurantSecondListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_POPULAR_CATEGORY, sort);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_list);

        context = this;
        activity = this;


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tool_bar_back_icon = (ImageView) toolbar.findViewById(R.id.toolbar_icon);
        iv_filter = (ImageView) toolbar.findViewById(R.id.iv_filter);
        ic_filter_resturant = (ImageView) toolbar.findViewById(R.id.iv_filter_rest_second);
        iv_filter_rest = (ImageView) toolbar.findViewById(R.id.iv_filter_rest);
        iv_filter_rest.setVisibility(View.GONE);
        ic_filter_resturant.setVisibility(View.VISIBLE);


        tool_bar_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ic_filter_resturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,RestaurantListActivity.class);
                startActivity(intent);
            }
        });
        iv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=FilterActivity.newInstance(activity,restaurantModel);
                startActivityForResult(intent,100);
            }
        });

        sub_category_recyclerview =findViewById(R.id.sub_category_recyclerview);
        gridLayoutManager = new GridLayoutManager(activity,2);
        linearLayoutManager=new LinearLayoutManager(activity);
        swipe_container = findViewById(R.id.swipe_container);
        details_progressActivity = findViewById(R.id.details_progressActivity);



        if (getIntent().hasExtra(BUNDLE_POPULAR_CATEGORY)) {

            sort = (String) getIntent().getStringExtra(BUNDLE_POPULAR_CATEGORY);
        } else {
            sort= null;
        }

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("All");

       /* home_category_recyclerview.setLayoutManager(homeCategory_linear);
        home_category_recyclerview.setHasFixedSize(true);
        homeCategoryAdapter = new HomeSubSectionListAdapter(activity,animalNames);
        home_category_recyclerview.setAdapter(homeCategoryAdapter);*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.ColorStatusBar));
        }

        mainView=sub_category_recyclerview;

        restaurantModel.setSize(size+"");
        restaurantModel.setIndex(index+"");

        getRestaurantList();

        swipe_container.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRestaurantList();
            }
        } );


        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        setTitle(getString(R.string.restaurant_near_you), 0, 0);

    }

    private void getRestaurantList() {
        GlobalFunctions.showProgress(context, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getRestaurantList(context,restaurantModel, new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                GlobalFunctions.hideProgress();
                if (swipe_container.isRefreshing()) {
                    swipe_container.setRefreshing( false );
                }
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
                if (swipe_container.isRefreshing()) {
                    swipe_container.setRefreshing( false );
                }

                Log.d(TAG, "Failure : " + msg);
                GlobalFunctions.displayMessaage(context, mainView, msg);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnError(String msg) {
                GlobalFunctions.hideProgress();
                if (swipe_container.isRefreshing()) {
                    swipe_container.setRefreshing( false );
                }
                Log.d(TAG, "Error : " + msg);
                GlobalFunctions.displayMessaage(context, mainView, msg);
            }
        }, "Store List");
    }

    private void setThisPage(RestaurantListModel restaurantListModel) {
        if (restaurantListModel != null && restaurantModels != null) {
            restaurantModels.clear();
            restaurantModels.addAll( restaurantListModel.getRestaurantModels() );
            if (restaurantSecondListAdapter != null) {
                synchronized (restaurantSecondListAdapter) {
                    restaurantSecondListAdapter.notifyDataSetChanged();
                }
            }
            if (restaurantModels.size() <= 0) {
                showOfferEmptyPage();
            } else {
                showOfferContent();
                offerRecyclerView();
            }
        }
    }

    private void showOfferEmptyPage() {
        if (details_progressActivity != null) {
            details_progressActivity.showEmpty(getResources().getDrawable(R.drawable.app_icon), getString(R.string.emptyList),
                    getString(R.string.no_data_show));
        }
    }

    private void offerRecyclerView() {
        sub_category_recyclerview.setLayoutManager(linearLayoutManager);
        sub_category_recyclerview.setHasFixedSize(true);
        restaurantSecondListAdapter = new RestaurantSecondListAdapter(activity,restaurantModels,this::OnRestaurantClickInvoke);
        sub_category_recyclerview.setAdapter(restaurantSecondListAdapter);
    }

    private void showOfferContent() {
        if (details_progressActivity != null) {
            details_progressActivity.showContent();
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
    public void OnRestaurantClickInvoke(int position, RestaurantModel restaurantModel) {
        if (GlobalFunctions.isNotNullValue(restaurantModel.getId())){
            restaurant_id= restaurantModel.getId();
        }
        checkWishlist(restaurant_id);
    }

    private void checkWishlist(String restaurant_id) {
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getCheckWishList(context,restaurant_id, new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                //globalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                //StatusModel model = (StatusModel) arg0;
                validateOutputAfterWishList(arg0);
                getRestaurantList();

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
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == activity.RESULT_OK) {

            if (requestCode == 100) {
                 restaurantModel = (RestaurantModel) data.getExtras().getSerializable(FilterActivity.BUNDLE_PRODUCT_POST_MODEL);
                if (restaurantModel != null) {
                    getRestaurantList();
                }
            }
        }
    }
}
