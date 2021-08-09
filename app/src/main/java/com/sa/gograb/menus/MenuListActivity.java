package com.sa.gograb.menus;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.R;
import com.sa.gograb.adapter.CategoryMenuListAdapter;
import com.sa.gograb.adapter.MenuListAdapter;
import com.sa.gograb.cart.CartActivity;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.CategoryMenuListModel;
import com.sa.gograb.services.model.CategoryMenuModel;
import com.sa.gograb.services.model.HomeSubCategoryModel;
import com.sa.gograb.services.model.HomeTopCategoryModel;
import com.sa.gograb.services.model.MenuCatModel;
import com.sa.gograb.services.model.MenuListMainModel;
import com.sa.gograb.services.model.MenuModel;
import com.sa.gograb.services.model.RestaurantModel;
import com.sa.gograb.services.model.TrendingMenuListModel;
import com.sa.gograb.services.model.TrendingMenuModel;
import com.sa.gograb.services.model.WishModel;
import com.squareup.picasso.Picasso;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuListActivity extends AppCompatActivity {
    private static final String TAG = "MenuListActivity",
            TAG_2 = "Trending_MenuListActivity",

             BUNDLE_MENU_LIST = "MenuList",
             BUNDLE_HOME_SUB_CATEGORY = "HomeSubCategoryModel",
             BUNDLE_HOME_TOP_CATEGORY = "HomeTopCategoryModel",
             BUNDLE_WISHLIST = "WishListModel",
             BUNDLE_POPULAR_RESTAURANT_NEAREST = "PopularNearestRestro";
    Context context;
    private static Activity activity;
    View mainView;

    String restaurant_id=null;
    int index=0;
    int size=100;

    static Toolbar toolbar;
    static ActionBar actionBar;
    static String mTitle;
    static int mResourceID;
    static int titleResourseID;
    static TextView toolbar_title;
    static ImageView toolbar_logo, tool_bar_back_icon;

    private static CircleImageView iv_menu_item;
    private static TextView tv_item_name,tv_category_first_title,tv_category_second_title,tv_category_third_title;
    private static TextView tv_preparation_time,tv_ratings,tv_rating_count,tv_distance,tv_view_cart;


    RestaurantModel restaurantModel = null;
    HomeSubCategoryModel homeSubCategoryModel = null;
    HomeTopCategoryModel homeTopCategoryModel = null;
    WishModel wishModel = null;
    FragmentManager mainActivityFM = null;


    //Menu Trending  category
    MenuListAdapter menuListAdapter;
    List<TrendingMenuModel> trendingMenuModels = new ArrayList<>();
    RecyclerView menu_list_recyclerview;
    LinearLayoutManager linearLayoutManager;
    ProgressLinearLayout trending_details_progressActivity;

    //Menu category  category
    CategoryMenuListAdapter categoryMenuListAdapter;
    List<CategoryMenuModel> menuCatModels = new ArrayList<>();
    RecyclerView sub_menu_list_recyclerview;
    LinearLayoutManager menuCarLinear;

    public static Intent newInstance(Activity activity, RestaurantModel restaurantModel) {
        Intent intent = new Intent(activity, MenuListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_MENU_LIST,restaurantModel);
        intent.putExtras(bundle);
        return intent;
    }
    public static Intent newInstance(Activity activity, HomeSubCategoryModel homeSubCategoryModel) {
        Intent intent = new Intent(activity, MenuListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_HOME_SUB_CATEGORY,homeSubCategoryModel);
        intent.putExtras(bundle);
        return intent;
    }
    public static Intent newInstance(Activity activity, HomeTopCategoryModel homeTopCategoryModel) {
        Intent intent = new Intent(activity, MenuListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_HOME_TOP_CATEGORY,homeTopCategoryModel);
        intent.putExtras(bundle);
        return intent;
    }

    public static Intent newInstance(Activity activity, WishModel wishModel) {
        Intent intent = new Intent(activity, MenuListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_WISHLIST,wishModel);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);

        context = this;
        activity = this;


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
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.ColorStatusBar));
        }

        //find viewById
        iv_menu_item=(CircleImageView) findViewById(R.id.iv_menu_item);
        tv_item_name=(TextView) findViewById(R.id.tv_item_name);
        tv_category_first_title=(TextView) findViewById(R.id.tv_category_first_title);
        tv_category_second_title=(TextView) findViewById(R.id.tv_category_second_title);
        tv_category_third_title=(TextView) findViewById(R.id.tv_category_third_title);
        tv_preparation_time=(TextView) findViewById(R.id.tv_preparation_time);
        tv_ratings=(TextView) findViewById(R.id.tv_ratings);
        tv_rating_count=(TextView) findViewById(R.id.tv_rating_count);
        tv_distance=(TextView) findViewById(R.id.tv_distance);
        tv_view_cart=(TextView) findViewById(R.id.tv_view_cart);

        menu_list_recyclerview=(RecyclerView)findViewById(R.id.menu_list_recyclerview);
        linearLayoutManager = new LinearLayoutManager(activity);

        sub_menu_list_recyclerview=(RecyclerView)findViewById(R.id.sub_menu_list_recyclerview);
        menuCarLinear = new LinearLayoutManager(activity);


        //Menu  list
        if (getIntent().hasExtra(BUNDLE_MENU_LIST)) {

            restaurantModel = (RestaurantModel) getIntent().getSerializableExtra(BUNDLE_MENU_LIST);
        } else {
            restaurantModel = null;
        }

        if (restaurantModel != null) {
            if (GlobalFunctions.isNotNullValue(restaurantModel.getId())) {
                Log.d(TAG,restaurantModel.getId());
                restaurant_id = restaurantModel.getId();
            }
        }
        //for Home Top Category
        if (getIntent().hasExtra(BUNDLE_HOME_TOP_CATEGORY)) {

            homeTopCategoryModel = (HomeTopCategoryModel) getIntent().getSerializableExtra(BUNDLE_HOME_TOP_CATEGORY);
        } else {
            homeTopCategoryModel = null;
        }

        if (homeTopCategoryModel != null) {
            if (GlobalFunctions.isNotNullValue(homeTopCategoryModel.getId())) {
                restaurant_id = homeTopCategoryModel.getId();
            }
        }
        //for Home Popular sub Category
        if (getIntent().hasExtra(BUNDLE_HOME_SUB_CATEGORY)) {

            homeSubCategoryModel = (HomeSubCategoryModel) getIntent().getSerializableExtra(BUNDLE_HOME_SUB_CATEGORY);
        } else {
            homeSubCategoryModel = null;
        }

        if (homeSubCategoryModel != null) {
            if (GlobalFunctions.isNotNullValue(homeSubCategoryModel.getId())) {
                restaurant_id = homeSubCategoryModel.getId();
            }
        }

        //WishList  list
        if (getIntent().hasExtra(BUNDLE_WISHLIST)) {

            wishModel = (WishModel) getIntent().getSerializableExtra(BUNDLE_WISHLIST);
        } else {
            wishModel = null;
        }

        if (wishModel != null) {
            if (GlobalFunctions.isNotNullValue(wishModel.getId())) {
                restaurant_id = wishModel.getId();
            }
        }

        tv_view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, CartActivity.class);
                startActivity(intent);
                // Fragment searchPlaceOnMapFragment = new CartFragment();
               // replaceFragment(searchPlaceOnMapFragment, CartFragment.TAG, "", 0, 0);
            }
        });




        getMenuList();


        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
       // setTitle(getString(R.string.restaurant_near_you), 0, 0);

    }

    private void replaceFragment(@Nullable Fragment allFragment, @Nullable String tag, @Nullable String title, int titleImageID, @Nullable int bgResID) {
        if (allFragment != null && mainActivityFM != null) {
            Fragment tempFrag = mainActivityFM.findFragmentByTag( tag );
            if (tempFrag != null) {
//                mainActivityFM.beginTransaction().remove(tempFrag).commitAllowingStateLoss();
                mainActivityFM.beginTransaction().remove( tempFrag ).commit();
            }
            // setTitle( title, titleImageID, bgResID );
            FragmentTransaction ft = mainActivityFM.beginTransaction();
            // ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            //ft.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_out_right);
            ft.replace( R.id.container, allFragment, tag ).addToBackStack( tag ).commitAllowingStateLoss();
        }
    }

    private void setCategoryMenuPage(CategoryMenuListModel categoryMenuListModel) {
        if (categoryMenuListModel != null && menuCatModels != null) {
            menuCatModels.clear();
            menuCatModels.addAll(categoryMenuListModel.getCategoryMenuModels());
            if (categoryMenuListAdapter != null) {
                synchronized (categoryMenuListAdapter) {
                    categoryMenuListAdapter.notifyDataSetChanged();
                }
            }
            if (menuCatModels.size() <= 0) {
                //showTrendingMenuEmptyPage();
            } else {
                showCategoryMenuContent();
                categoryMenuRecyclerView();
            }
        }
    }

    private void showCategoryMenuContent() {
        if (trending_details_progressActivity != null) {
            trending_details_progressActivity.showContent();
        }
    }

    private void categoryMenuRecyclerView() {
        sub_menu_list_recyclerview.setLayoutManager(menuCarLinear);
        sub_menu_list_recyclerview.setHasFixedSize(true);
        categoryMenuListAdapter = new CategoryMenuListAdapter(activity, menuCatModels);
        sub_menu_list_recyclerview.setAdapter(categoryMenuListAdapter);
    }

    private void setTrendingMenuPage(TrendingMenuListModel trendingMenuListModel) {
        if (trendingMenuListModel != null && trendingMenuModels != null) {
            trendingMenuModels.clear();
            trendingMenuModels.addAll(trendingMenuListModel.getTrendingMenuModels());
            if (menuListAdapter != null) {
                synchronized (menuListAdapter) {
                    menuListAdapter.notifyDataSetChanged();
                }
            }
            if (trendingMenuModels.size() <= 0) {
                //showTrendingMenuEmptyPage();
            } else {
                showTrendingMenuContent();
                trendingMenuRecyclerView();
            }
        }
    }
   /* private void showTrendingMenuEmptyPage() {
        if (offerprogressActivity != null) {
            offerprogressActivity.showEmpty(getResources().getDrawable(R.drawable.rezq_logo), getString(R.string.emptyList),
                    getString(R.string.no_reviews));
        }
    }
*/
    private void showTrendingMenuContent() {
        if (trending_details_progressActivity != null) {
            trending_details_progressActivity.showContent();
        }
    }

    private void trendingMenuRecyclerView() {
        menu_list_recyclerview.setLayoutManager(linearLayoutManager);
        menu_list_recyclerview.setHasFixedSize(true);
        menuListAdapter = new MenuListAdapter(activity, trendingMenuModels);
        menu_list_recyclerview.setAdapter(menuListAdapter);
    }


    private void getMenuList() {
        GlobalFunctions.showProgress(context, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getMenuDetails(context, restaurant_id, new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                 GlobalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                MenuListMainModel menuListMainModel = (MenuListMainModel) arg0;
                MenuModel menuModel = menuListMainModel.getMenuModel();

                setThisPage(menuModel);

                if (menuModel.getTrendingMenuListModel()!= null) {
                    TrendingMenuListModel trendingMenuListModel = menuModel.getTrendingMenuListModel();
                    setTrendingMenuPage(trendingMenuListModel);
                }

                if (menuModel.getCategoryMenuListModel()!= null) {
                    CategoryMenuListModel categoryMenuListModel =menuModel.getCategoryMenuListModel();
                    setCategoryMenuPage(categoryMenuListModel);
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
        }, "Review List");
    }

    private void setThisPage(MenuModel menuModel) {
        if (menuModel != null && context != null) {

            if (GlobalFunctions.isNotNullValue(menuModel.getName())) {
                tv_item_name.setText(menuModel.getName());
            }
            if (GlobalFunctions.isNotNullValue(menuModel.getImage())) {
                Picasso.with(context).load(menuModel.getImage()).placeholder(R.drawable.image).into(iv_menu_item);
            }
            if (GlobalFunctions.isNotNullValue(menuModel.getDistance())) {
                tv_distance.setText(menuModel.getDistance());
            }
            if (GlobalFunctions.isNotNullValue(menuModel.getPreparation_time())) {
                tv_preparation_time.setText(menuModel.getPreparation_time());
            }
            if (GlobalFunctions.isNotNullValue(menuModel.getRating())) {
                tv_ratings.setText(menuModel.getRating());
            }
            if (GlobalFunctions.isNotNullValue(menuModel.getRating_count())) {
                tv_rating_count.setText("("+menuModel.getRating_count()+"+)");
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
}
