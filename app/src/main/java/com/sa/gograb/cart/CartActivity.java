package com.sa.gograb.cart;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.AppController;
import com.sa.gograb.R;
import com.sa.gograb.adapter.CartAdapter;
import com.sa.gograb.adapter.interfaces.CartClickListener;
import com.sa.gograb.adapter.interfaces.OnCartInvokeListener;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.global.GlobalVariables;
import com.sa.gograb.menus.MenuListActivity;
import com.sa.gograb.orders.OrderConfirmationActivity;
import com.sa.gograb.restaurant.RestaurantListActivity;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.CartDetailModel;
import com.sa.gograb.services.model.CartDetailsListModel;
import com.sa.gograb.services.model.CartMainModel;
import com.sa.gograb.services.model.CartModel;
import com.sa.gograb.services.model.CartPostModel;
import com.sa.gograb.services.model.CartSubMainModel;
import com.sa.gograb.services.model.MenuCatModel;
import com.sa.gograb.services.model.MenuModel;
import com.sa.gograb.services.model.StatusMainModel;
import com.sa.gograb.services.model.StatusModel;
import com.sa.gograb.services.model.TrendingMenuModel;
import com.squareup.picasso.Picasso;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartActivity extends AppCompatActivity implements OnCartInvokeListener {

    public static String TAG = "CartActivity";
    public static final String BUNDLE_MENU_MODEL = "MenuModelDetails";

    Context context;
    private static Activity activity;
    View mainView;

    GlobalFunctions globalFunctions = null;
    GlobalVariables globalVariables = null;

    private TextView tv_checkout,tv_item_name,tv_ratings,tv_rating_count,tv_distance,tv_apply_coupon;
    private TextView item_price_tv,packaging_charges_tv,sub_total_tv,discount_onBill_tv,vat_amount_tv,tv_sar_price,tv_currency;
    private CircleImageView iv_menu_main;
    private  static Button btn_view_restaurant;
    private  static RelativeLayout rl_cart_main;
    private  static LinearLayout ln_empty_cart;
    private  static LinearLayout ln_packaging;

    static Toolbar toolbar;
    static ActionBar actionBar;
    static String mTitle;
    static int mResourceID;
    static int titleResourseID;
    static TextView toolbar_title;
    static ImageView toolbar_logo, tool_bar_back_icon;

    MenuModel menuModel = null;
    String restaurant_id=null;


    CartAdapter cartAdapter;
    List<CartDetailModel> cartDetailModels = new ArrayList<>();
    LinearLayoutManager cart_linear;
    ProgressLinearLayout details_progressActivity;
    RecyclerView cart_list_recyclerview;

    public static Intent newInstance(Activity activity, MenuModel menuModel) {
        Intent intent = new Intent(activity, CartActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_MENU_MODEL,menuModel);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);

        context = this;
        activity = this;

        globalFunctions = AppController.getInstance().getGlobalFunctions();
        globalVariables = AppController.getInstance().getGlobalVariables();


       /* //search from home
        if (getIntent().hasExtra(BUNDLE_MENU_MODEL)) {
            menuModel = (MenuModel) getIntent().getSerializableExtra(BUNDLE_MENU_MODEL);

        } else {
            menuModel = null;
        }

        if (menuModel != null) {
            if (GlobalFunctions.isNotNullValue(menuModel.getId())) {
                restaurant_id = menuModel.getId();
            }
        }*/



        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tool_bar_back_icon = (ImageView) toolbar.findViewById(R.id.toolbar_icon);
        tool_bar_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mainView=toolbar;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.ColorStatusBar));
        }


        tv_checkout = findViewById(R.id.tv_checkout);
        btn_view_restaurant = findViewById(R.id.btn_view_restaurant);
        iv_menu_main = findViewById(R.id.iv_menu_main);
        tv_item_name = findViewById(R.id.tv_item_name);
        tv_ratings = findViewById(R.id.tv_ratings);
        tv_rating_count = findViewById(R.id.tv_rating_count);
        tv_distance = findViewById(R.id.tv_distance);
        item_price_tv = findViewById(R.id.item_price_tv);
        packaging_charges_tv = findViewById(R.id.packaging_charges_tv);
        sub_total_tv = findViewById(R.id.sub_total_tv);
        discount_onBill_tv = findViewById(R.id.discount_onBill_tv);
        vat_amount_tv = findViewById(R.id.vat_amount_tv);
        tv_sar_price = findViewById(R.id.tv_sar_price);
        tv_currency = findViewById(R.id.tv_currency);
        tv_apply_coupon = findViewById(R.id.tv_apply_coupon);
        ln_empty_cart = findViewById(R.id.ln_empty_cart);
        rl_cart_main = findViewById(R.id.rl_cart_main);
        ln_packaging = findViewById(R.id.ln_packaging);
        details_progressActivity = findViewById(R.id.details_progressActivity);

        cart_list_recyclerview = findViewById(R.id.cart_list_recyclerview);
        cart_linear = new LinearLayoutManager(activity);


        getCartList();
        //setCartPage(menuModel);

        tv_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitOrder();

            }
        });
        btn_view_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, RestaurantListActivity.class);
                startActivity(intent);
            }
        });

         setSupportActionBar(toolbar);
         actionBar = getSupportActionBar();


    }



    private void getCartList() {
        GlobalFunctions.showProgress(context, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getCart(context, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                CartMainModel cartMainModel = (CartMainModel) arg0;
                CartModel cartModel = cartMainModel.getCartModel().getCartModel();

                if (cartModel!=null &&  cartModel.getCartDetailsListModel()!=null){
                    CartDetailsListModel cartDetailsListModel=cartModel.getCartDetailsListModel();
                    if (cartDetailsListModel !=null){
                        setThisPage(cartDetailsListModel);
                    }

                    if (cartModel !=null){
                        setcartDetails(cartModel);
                    }

                }else {
                    ln_empty_cart.setVisibility(View.VISIBLE);
                    rl_cart_main.setVisibility(View.GONE);
                }
            }

            @Override
            public void OnFailureFromServer(String msg) {
                 GlobalFunctions.hideProgress();
                Log.d(TAG, "Failure : " + msg);
                GlobalFunctions.displayMessaage(context, mainView, msg);
            }

            @Override
            public void OnError(String msg) {
                 GlobalFunctions.hideProgress();
                Log.d(TAG, "Error : " + msg);
                GlobalFunctions.displayMessaage(context, mainView, msg);
            }
        }, "Cart List");
    }

    private void setcartDetails(CartModel cartModel) {
        if (cartModel!=null && context !=null){

            if (GlobalFunctions.isNotNullValue(cartModel.getPacking_charges())) {
                if (cartModel.getPacking_charges().equalsIgnoreCase("") && cartModel.getPacking_charges().equalsIgnoreCase("0")){
                    ln_packaging.setVisibility(View.GONE);
                }else {
                    ln_packaging.setVisibility(View.VISIBLE);
                    packaging_charges_tv.setText(cartModel.getPacking_charges());

                }
            }
            if (GlobalFunctions.isNotNullValue(cartModel.getRestaurant_id())){
                restaurant_id=cartModel.getRestaurant_id();
            }


            if (GlobalFunctions.isNotNullValue(cartModel.getVat())) {
                vat_amount_tv.setText(cartModel.getVat());
            }
            if (GlobalFunctions.isNotNullValue(cartModel.getSubTotal())) {
                sub_total_tv.setText(cartModel.getSubTotal());
            }
            if (GlobalFunctions.isNotNullValue(cartModel.getCurrency())) {
                tv_currency.setText(cartModel.getCurrency());
            }
            if (GlobalFunctions.isNotNullValue(cartModel.getGrand_total())) {
                tv_sar_price.setText(cartModel.getGrand_total());
            }


            if (GlobalFunctions.isNotNullValue(cartModel.getFull_name())) {
                tv_item_name.setText(cartModel.getFull_name());
            }
            if (GlobalFunctions.isNotNullValue(cartModel.getImage())) {
                Picasso.with(context).load(cartModel.getImage()).placeholder(R.drawable.image).into(iv_menu_main);
            }
            if (GlobalFunctions.isNotNullValue(cartModel.getDistance())) {
                tv_distance.setText(cartModel.getDistance());
            }
            if (GlobalFunctions.isNotNullValue(cartModel.getRating())) {
                tv_ratings.setText(cartModel.getRating());
            }
            if (GlobalFunctions.isNotNullValue(cartModel.getRating_count())) {
                tv_rating_count.setText("("+cartModel.getRating_count()+"+)");
            }
        }
    }

    private void setThisPage(CartDetailsListModel cartDetailsListModel) {
        if (cartDetailsListModel != null && cartDetailModels != null) {
            cartDetailModels.clear();
            cartDetailModels.addAll(cartDetailsListModel.getCartDetailModel());
            if (cartAdapter != null) {
                synchronized (cartAdapter) {
                    cartAdapter.notifyDataSetChanged();
                }
            }
            if (cartDetailModels.size() <= 0) {
                ln_empty_cart.setVisibility(View.VISIBLE);
                rl_cart_main.setVisibility(View.GONE);
                //showTrendingMenuEmptyPage();
            } else {
                showCartContent();
                initCartRecyclerView();
            }
        }else {
            ln_empty_cart.setVisibility(View.VISIBLE);
            rl_cart_main.setVisibility(View.GONE);
        }
    }

    private void initCartRecyclerView() {
        cart_list_recyclerview.setLayoutManager(cart_linear);
        cart_list_recyclerview.setHasFixedSize(true);
        cartAdapter = new CartAdapter(activity,cartDetailModels,this);
        cart_list_recyclerview.setAdapter(cartAdapter);

    }

    private void showCartContent() {
        if (details_progressActivity != null) {
            details_progressActivity.showContent();
        }
    }

    private void setCartPage(MenuModel menuModel) {
        if (menuModel!=null && context !=null){

            if (GlobalFunctions.isNotNullValue(menuModel.getName())) {
                tv_item_name.setText(menuModel.getName());
            }
            if (GlobalFunctions.isNotNullValue(menuModel.getImage())) {
                Picasso.with(context).load(menuModel.getImage()).placeholder(R.drawable.image).into(iv_menu_main);
            }
            if (GlobalFunctions.isNotNullValue(menuModel.getDistance())) {
                tv_distance.setText(menuModel.getDistance());
            }
            if (GlobalFunctions.isNotNullValue(menuModel.getRating())) {
                tv_ratings.setText(menuModel.getRating());
            }
            if (GlobalFunctions.isNotNullValue(menuModel.getRating_count())) {
                tv_rating_count.setText("("+menuModel.getRating_count()+"+)");
            }
        }

    }
    private void submitOrder() {
        //globalFunctions.showProgress(activity, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.submitOrder(context, new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                // globalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                validOutputAfterOrderSubmit(arg0);

            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnFailureFromServer(String msg) {
                //globalFunctions.hideProgress();
                globalFunctions.displayMessaage(context, mainView, msg);
                Log.d(TAG, "Failure : " + msg);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnError(String msg) {
                // globalFunctions.hideProgress();
                globalFunctions.displayMessaage(context, mainView, msg);
                Log.d(TAG, "Error : " + msg);
            }
        }, "Submit Order ");
    }

    private void validOutputAfterOrderSubmit(Object arg0) {
        if (arg0 instanceof StatusMainModel) {
            StatusMainModel statusMainModel = (StatusMainModel) arg0;
             StatusModel statusModel = statusMainModel.getStatusModel();
            if (statusMainModel.isStatus()) {
                Intent intent = OrderConfirmationActivity.newInstance( activity, restaurant_id);
                activity.startActivity( intent );

               // Intent intent=new Intent(activity, OrderConfirmationActivity.class);
               // startActivity(intent);
            } else {

                globalFunctions.displayMessaage(activity, mainView, statusMainModel.getMessage());

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

    @Override
    public void OnCartListListener(CartDetailModel cartDetailModel, String count) {

        CartPostModel cartPostModel=new CartPostModel();
        cartPostModel.setRestaurant_id(restaurant_id);
        cartPostModel.setMenu_id(cartDetailModel.getMenu_id());
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
        }, "GetSubCatList");
    }
    private void validateInsertCartOutput(StatusModel model) {
        if (model != null) {
            GlobalFunctions.displayMessaage(activity, mainView, model.getMessage());
            getCartList();
           // setCartPage(menuModel);
        }
    }
}
