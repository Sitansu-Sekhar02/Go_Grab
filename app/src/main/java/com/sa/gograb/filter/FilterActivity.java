package com.sa.gograb.filter;

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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sa.gograb.AppController;
import com.sa.gograb.R;
import com.sa.gograb.adapter.FilterCheckBoxAdapter;
import com.sa.gograb.adapter.FilterTitleAdapter;
import com.sa.gograb.adapter.interfaces.OnFilterItemClickInvoke;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.global.GlobalVariables;
import com.sa.gograb.restaurant.RestaurantListActivity;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.FilterCusinesModel;
import com.sa.gograb.services.model.FilterDistanceModel;
import com.sa.gograb.services.model.FilterMainModel;
import com.sa.gograb.services.model.FilterModel;
import com.sa.gograb.services.model.FilterPrepareModel;
import com.sa.gograb.services.model.HomeTopCategoryModel;
import com.sa.gograb.services.model.RestaurantModel;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity implements OnFilterItemClickInvoke {

    private static final String TAG = "FilterActivity";
    public static  final String BUNDLE_PRODUCT_POST_MODEL = "ResModel";

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
    GlobalFunctions globalFunctions;
    GlobalVariables globalVariables;

    private TextView tv_apply_filter,tv_close;


    FilterTitleAdapter filterTitleAdapter;
    FilterCheckBoxAdapter FilterCheckBoxAdapter;

    List<FilterModel> mainList = new ArrayList<>();
    List<FilterModel> filterModels = new ArrayList<>();
    LinearLayoutManager filter_linear;
    LinearLayoutManager filter_sub_linear;
    ProgressLinearLayout details_progressActivity;
    SwipeRefreshLayout swipe_container;
    RecyclerView cat_recycler_view;
    RecyclerView sub_category_recycler_view;

    List<FilterDistanceModel> filterCusinesModels = new ArrayList<>();
    List<FilterDistanceModel> filterDistanceModels = new ArrayList<>();
    List<FilterDistanceModel> filterPrepareModels = new ArrayList<>();

    RestaurantModel restaurantModel=null;

    public static Intent newInstance(Activity activity, RestaurantModel restaurantModel) {
        Intent intent = new Intent(activity, FilterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_PRODUCT_POST_MODEL, restaurantModel);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_activity);

        context = this;
        activity = this;

        globalFunctions = AppController.getInstance().getGlobalFunctions();
        globalVariables = AppController.getInstance().getGlobalVariables();


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tool_bar_back_icon = (ImageView) toolbar.findViewById(R.id.toolbar_icon);

        if (getIntent().hasExtra(BUNDLE_PRODUCT_POST_MODEL)) {

            restaurantModel = (RestaurantModel) getIntent().getSerializableExtra(BUNDLE_PRODUCT_POST_MODEL);
        } else {
            restaurantModel= null;
        }


        tool_bar_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.ColorStatusBar));
        }


        getFilterList();

        tv_apply_filter = findViewById(R.id.tv_apply_filter);
        tv_close = findViewById(R.id.tv_close);


        cat_recycler_view = findViewById(R.id.cat_recycler_view);
        sub_category_recycler_view = findViewById(R.id.sub_category_recycler_view);
        details_progressActivity = findViewById(R.id.details_progressActivity);
        swipe_container = findViewById(R.id.swipe_container);
        filter_linear=new LinearLayoutManager(activity);
        filter_sub_linear=new LinearLayoutManager(activity);

        mainView=toolbar;


        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeThisActivity();

            }
        });
        tv_apply_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFilterModel();
            }
        });



        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        setTitle(getString(R.string.filter), 0, 0);

    }
    private void setFilterModel() {
        String selectedDistanceIds = GlobalFunctions.getSelectedIdsFromList(filterDistanceModels);
        String selectedCusineIds = GlobalFunctions.getSelectedIdsFromList(filterCusinesModels);
        String selectedPrepareIds = GlobalFunctions.getSelectedIdsFromList(filterPrepareModels);

        if (restaurantModel == null) {
            restaurantModel = new RestaurantModel();
        }


        if (GlobalFunctions.isNotNullValue(selectedDistanceIds)) {
            restaurantModel.setDistance(selectedDistanceIds);
        }

        if (GlobalFunctions.isNotNullValue(selectedCusineIds)) {
            restaurantModel.setCuisine_id(selectedCusineIds);
        }

        if (GlobalFunctions.isNotNullValue(selectedPrepareIds)) {
            restaurantModel.setPreparation_time(selectedPrepareIds);
        }

        setResults(true, restaurantModel);
    }

    public void setResults(boolean isSuccess, RestaurantModel restaurantModel) {
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_PRODUCT_POST_MODEL, restaurantModel);
        if (isSuccess) setResult(RESULT_OK, intent);
        else setResult(RESULT_CANCELED, intent);
        closeThisActivity();
    }

    private void getFilterList() {
        GlobalFunctions.showProgress(activity, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getFilterList(context, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                if (context != null) {
                    globalFunctions.hideProgress();
                    Log.d(TAG, "Response: " + arg0.toString());
                    FilterMainModel filterMainModel=(FilterMainModel) arg0;
                    FilterModel filterModel=filterMainModel.getFilterModel();

                    setThisPage(filterModel);
                }
            }

            @Override
            public void OnFailureFromServer(String msg) {
                if (context != null) {
                    globalFunctions.hideProgress();
                    Log.d(TAG, "Failure : " + msg);
                }
            }

            @Override
            public void OnError(String msg) {
                if (context != null) {
                     globalFunctions.hideProgress();
                    Log.d(TAG, "Error : " + msg);
                }
            }
        }, "Sub_Category");

    }

    private void setThisPage(FilterModel filterModel) {
      if (filterModel != null) {

          if (filterModel.getFilterDistanceListModel() != null) {
              filterDistanceModels.addAll(filterModel.getFilterDistanceListModel().getFilterDistanceModels());
          }

          if (filterModel.getFilterCusinesListModel() != null) {
              filterCusinesModels.addAll(filterModel.getFilterCusinesListModel().getFilterDistanceModels());
          }

          if (filterModel.getFilterPrepareListModel() != null) {
              filterPrepareModels.addAll(filterModel.getFilterPrepareListModel().getFilterDistanceModels());
          }

          setUpHeaderFilterList();

      }
    }

    private void setUpHeaderFilterList() {
        for (int i = 0; i < 3; i++) {
            FilterModel filterModel = new FilterModel();
            if (i == 0) {
                filterModel.setTitle("Distance");
            } else if (i == 1) {
                filterModel.setTitle("Cusines");
            } else if (i == 2) {
                filterModel.setTitle("Preparation Time");
            }
            mainList.add(filterModel);
        }
        subSectionCatInitRecycler();
    }

    private void subSectionCatInitRecycler() {
        cat_recycler_view.setLayoutManager(filter_linear);
        cat_recycler_view.setHasFixedSize(true);
        filterTitleAdapter = new FilterTitleAdapter(activity, mainList,this);
        cat_recycler_view.setAdapter(filterTitleAdapter);
    }

    private void subCatSectionCatInitRecycler(List<FilterDistanceModel> filterDistanceModels) {
        sub_category_recycler_view.setLayoutManager(filter_sub_linear);
        sub_category_recycler_view.setHasFixedSize(true);
        FilterCheckBoxAdapter = new FilterCheckBoxAdapter(activity, filterDistanceModels);
        sub_category_recycler_view.setAdapter(FilterCheckBoxAdapter);
    }

    private void showSubSectionCatContent() {
        if (details_progressActivity != null) {
            details_progressActivity.showContent();
        }
    }

    private void showSubSectionCatEmptyPage() {
        if (details_progressActivity != null) {
            details_progressActivity.showEmpty(getResources().getDrawable(R.drawable.app_icon), getString(R.string.emptyList),
                    getString(R.string.not_available));
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
    public void OnFilterItemClickInvoke(FilterModel model) {

        if (model.getType().equalsIgnoreCase("0")){

            if (filterDistanceModels.size()>0){
                subCatSectionCatInitRecycler(filterDistanceModels);
            }

        }else if (model.getType().equalsIgnoreCase("1")){

            if (filterCusinesModels.size()>0){
                subCatSectionCatInitRecycler(filterCusinesModels);
            }

        }else if (model.getType().equalsIgnoreCase("2")){

            if (filterPrepareModels.size()>0){
                subCatSectionCatInitRecycler(filterPrepareModels);
            }

        }


    }
}
