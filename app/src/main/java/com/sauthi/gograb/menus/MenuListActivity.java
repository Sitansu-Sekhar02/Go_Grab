package com.sauthi.gograb.menus;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sauthi.gograb.AppController;
import com.sauthi.gograb.R;
import com.sauthi.gograb.adapter.CategoryMenuListAdapter;
import com.sauthi.gograb.adapter.HomeCusineAdapter;
import com.sauthi.gograb.adapter.MenuListAdapter;
import com.sauthi.gograb.adapter.interfaces.CartClickListener;
import com.sauthi.gograb.cart.CartActivity;
import com.sauthi.gograb.global.GlobalFunctions;
import com.sauthi.gograb.global.GlobalVariables;
import com.sauthi.gograb.services.ServerResponseInterface;
import com.sauthi.gograb.services.ServicesMethodsManager;
import com.sauthi.gograb.services.model.CartMainModel;
import com.sauthi.gograb.services.model.CartModel;
import com.sauthi.gograb.services.model.CartPostModel;
import com.sauthi.gograb.services.model.CartSubMainModel;
import com.sauthi.gograb.services.model.CategoryMenuListModel;
import com.sauthi.gograb.services.model.CategoryMenuModel;
import com.sauthi.gograb.services.model.CusineModel;
import com.sauthi.gograb.services.model.HomeSubCategoryModel;
import com.sauthi.gograb.services.model.HomeTopCategoryModel;
import com.sauthi.gograb.services.model.MenuCatModel;
import com.sauthi.gograb.services.model.MenuListMainModel;
import com.sauthi.gograb.services.model.MenuModel;
import com.sauthi.gograb.services.model.RestaurantModel;
import com.sauthi.gograb.services.model.StatusMainModel;
import com.sauthi.gograb.services.model.StatusModel;
import com.sauthi.gograb.services.model.TrendingMenuListModel;
import com.sauthi.gograb.services.model.TrendingMenuModel;
import com.sauthi.gograb.services.model.WishModel;
import com.squareup.picasso.Picasso;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuListActivity extends AppCompatActivity implements CartClickListener {
    private static final String TAG = "MenuListActivity",
                                TAG_2 = "Cart",

                                 BUNDLE_MENU_LIST = "MenuList",
                                 BUNDLE_HOME_SUB_CATEGORY = "HomeSubCategoryModel",
                                 BUNDLE_HOME_TOP_CATEGORY = "HomeTopCategoryModel",
                                 BUNDLE_WISHLIST = "WishListModel",
                                 BUNDLE_POPULAR_RESTAURANT_NEAREST = "PopularNearestRestro",
                                 BUNDLE_SEAR_PLACE_MENU = "MenuFromSearchPlace",
                                 BUNDLE_SEARCH_FROM_HOME = "SearchFromModel";
    Context context;
    private static Activity activity;
    View mainView;

    String restaurant_id=null;
    String menu_id=null;
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
    private static RelativeLayout rl_view_cart;
    private static TextView tv_item_name,tv_category_first_title,tv_category_second_title,tv_category_third_title;
    private static TextView tv_preparation_time,tv_ratings,tv_rating_count,tv_distance,tv_view_cart,tv_sub_total,tv_currency,tv_item_count;


    GlobalFunctions globalFunctions = null;
    GlobalVariables globalVariables = null;



    RestaurantModel restaurantModel = null;
    MenuModel menuModel = null;
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


    //menu cusine
    HomeCusineAdapter cusineAdapter;
    List<CusineModel> cusineModels = new ArrayList<>();
    RecyclerView rl_menu_cusines;
    LinearLayoutManager cusineLinear;



    public static Intent newInstance(Activity activity, RestaurantModel restaurantModel) {
        Intent intent = new Intent(activity, MenuListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_MENU_LIST,restaurantModel);
        bundle.putSerializable(BUNDLE_SEAR_PLACE_MENU,restaurantModel);
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

    public static Intent newInstance(Activity activity, MenuModel menuModel) {
        Intent intent = new Intent(activity, MenuListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_SEARCH_FROM_HOME, menuModel);
        intent.putExtras(bundle);
        return intent;
    }

    /* public static Intent newInstance(Activity activity, RestaurantModel restaurantModel) {
         Intent intent = new Intent(activity, MenuListActivity.class);
         Bundle bundle = new Bundle();
         bundle.putSerializable(BUNDLE_SEAR_PLACE_MENU,restaurantModel);
         intent.putExtras(bundle);
         return intent;
     }
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);

        context = this;
        activity = this;



        globalFunctions = AppController.getInstance().getGlobalFunctions();
        globalVariables = AppController.getInstance().getGlobalVariables();

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
        tv_sub_total=(TextView) findViewById(R.id.tv_sub_total);
        tv_currency=(TextView) findViewById(R.id.tv_currency);
        tv_item_count=(TextView) findViewById(R.id.tv_item_count);
        rl_view_cart=(RelativeLayout) findViewById(R.id.rl_view_cart);
        trending_details_progressActivity= findViewById(R.id.trending_details_progressActivity);
        rl_menu_cusines= findViewById(R.id.rl_menu_cusines);
        cusineLinear=new LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false);

        menu_list_recyclerview=(RecyclerView)findViewById(R.id.menu_list_recyclerview);
        linearLayoutManager = new LinearLayoutManager(activity);

        sub_menu_list_recyclerview=(RecyclerView)findViewById(R.id.sub_menu_list_recyclerview);
        menuCarLinear = new LinearLayoutManager(activity);

        mainView=toolbar;


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
        //search from home
        if (getIntent().hasExtra(BUNDLE_SEARCH_FROM_HOME)) {
            menuModel = (MenuModel) getIntent().getSerializableExtra(BUNDLE_SEARCH_FROM_HOME);

        } else {
            menuModel = null;
        }

        if (menuModel != null) {
            if (GlobalFunctions.isNotNullValue(menuModel.getId())) {
                Log.d(TAG,menuModel.getId());
                restaurant_id = menuModel.getId();
            }
        }

        //Menu  list
        if (getIntent().hasExtra(BUNDLE_SEAR_PLACE_MENU)) {

            restaurantModel = (RestaurantModel) getIntent().getSerializableExtra(BUNDLE_SEAR_PLACE_MENU);
        } else {
            restaurantModel = null;
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



       // getCart();
       // getMenuList();

        tv_view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,CartActivity.class);
                startActivity( intent );
               /* Intent intent = CartActivity.newInstance( activity, menuModel );
                startActivity( intent );*/
            }
        });


        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
       // setTitle(getString(R.string.restaurant_near_you), 0, 0);

    }

    private void getCart() {
        //GlobalFunctions.showProgress(context, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getCart(context, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                //GlobalFunctions.hideProgress();
                Log.d(TAG_2, "Response : " + arg0.toString());
                CartMainModel cartMainModel = (CartMainModel) arg0;
                CartSubMainModel cartSubMainModel=cartMainModel.getCartModel();
                CartModel cartModel = cartMainModel.getCartModel().getCartModel();
                if (cartModel!=null && cartSubMainModel !=null){
                    setCartPage(cartModel,cartSubMainModel);
                }else {
                    rl_view_cart.setVisibility(View.GONE);
                }

            }

            @Override
            public void OnFailureFromServer(String msg) {
               // GlobalFunctions.hideProgress();
                Log.d(TAG_2, "Failure : " + msg);
                GlobalFunctions.displayMessaage(context, mainView, msg);
            }

            @Override
            public void OnError(String msg) {
               // GlobalFunctions.hideProgress();
                Log.d(TAG_2, "Error : " + msg);
                GlobalFunctions.displayMessaage(context, mainView, msg);
            }
        }, "Cart List");
    }

    private void setCartPage(CartModel cartModel, CartSubMainModel cartSubMainModel) {
        if (cartModel != null && context!=null ) {
            if (GlobalFunctions.isNotNullValue(cartModel.getCart_count())) {
                rl_view_cart.setVisibility(View.GONE);
            }else {
                rl_view_cart.setVisibility(View.VISIBLE);

            }

            if (GlobalFunctions.isNotNullValue(cartModel.getGrand_total())){
                tv_sub_total.setText(cartModel.getGrand_total());

            }
            if (GlobalFunctions.isNotNullValue(cartSubMainModel.getCartcount())){
                tv_item_count.setText(cartSubMainModel.getCartcount()+" "+activity.getString(R.string.items));

            }
            if (GlobalFunctions.isNotNullValue(cartModel.getCurrency())){
                tv_currency.setText(cartModel.getCurrency());

            }
            if (GlobalFunctions.isNotNullValue(cartModel.getRestaurant_id())){
                menu_id=cartModel.getRestaurant_id();

            }

        }else {
            rl_view_cart.setVisibility(View.GONE);
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
        categoryMenuListAdapter = new CategoryMenuListAdapter(activity, menuCatModels,this);
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
        menuListAdapter = new MenuListAdapter(activity, trendingMenuModels,this);
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
            if (menuModel.getCusineListModel() != null && menuModel.getCusineListModel().getCusineModels().size() > 0){
                cusineModels.clear();
                cusineModels.addAll(menuModel.getCusineListModel().getCusineModels());
            }

            if (cusineModels.size() > 0) {
                cusineAdapter = new HomeCusineAdapter(activity, cusineModels);
                rl_menu_cusines.setLayoutManager(cusineLinear);
                rl_menu_cusines.setAdapter(cusineAdapter);

                rl_menu_cusines.setVisibility(View.VISIBLE);
            } else {
                rl_menu_cusines.setVisibility(View.GONE);
            }


            if (GlobalFunctions.isNotNullValue(menuModel.getName())) {
                tv_item_name.setText(menuModel.getName());
            }
            if (GlobalFunctions.isNotNullValue(menuModel.getImage())) {
                Picasso.with(context).load(menuModel.getImage()).placeholder(R.drawable.lazy_load).into(iv_menu_item);
            }
            if (GlobalFunctions.isNotNullValue(menuModel.getDistance())) {
               /* activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                         tv_distance.setText(GlobalFunctions.getDistanceFromAddress(activity,menuModel.getLatitude(),menuModel.getLongitude()));
                    }
                });*/
                
                tv_distance.setText(menuModel.getDistance());
            }
            if (GlobalFunctions.isNotNullValue(menuModel.getPreparation_time())) {
                tv_preparation_time.setText(menuModel.getPreparation_time()+" "+activity.getString(R.string.mins));
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
    public void onResume() {
         getCart();
         getMenuList();

        super.onResume();
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
    public void OnCartInvoked(TrendingMenuModel trendingMenuModel, String count) {
        CartPostModel cartPostModel=new CartPostModel();
        cartPostModel.setRestaurant_id(restaurant_id);
        cartPostModel.setMenu_id(trendingMenuModel.getId());
        cartPostModel.setQuantity(count);
        insertCart(context, cartPostModel);

    }

    @Override
    public void OnCategoryCartInvoked(MenuCatModel menuCatModel, String count) {
        CartPostModel cartPostModel=new CartPostModel();
        cartPostModel.setRestaurant_id(restaurant_id);
        cartPostModel.setMenu_id(menuCatModel.getId());
        cartPostModel.setQuantity(count);
        insertCart(context, cartPostModel);
    }

    private void insertCart(final Context context, CartPostModel cartPostModel) {
        globalFunctions.showProgress(activity, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.insertCart(context, cartPostModel, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                if (context != null) {
                    globalFunctions.hideProgress();
                    Log.d(TAG, "Response: " + arg0.toString());
                    StatusMainModel model = (StatusMainModel) arg0;
                    validateInsertCartOutput(model.getStatusModel());
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
        }, "insert cart");
    }
    private void validateInsertCartOutput(StatusModel model) {
        if (model != null) {
          //  GlobalFunctions.displayMessaage(activity, mainView, model.getMessage());
            getCart();
            getMenuList();
        }
    }
}
