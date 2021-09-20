package com.sauthi.gograb.wishlist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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

import com.sauthi.gograb.AppController;
import com.sauthi.gograb.R;
import com.sauthi.gograb.adapter.WishListAdapter;
import com.sauthi.gograb.adapter.interfaces.FavouriteListClickListener;
import com.sauthi.gograb.global.GlobalFunctions;
import com.sauthi.gograb.global.GlobalVariables;
import com.sauthi.gograb.services.ServerResponseInterface;
import com.sauthi.gograb.services.ServicesMethodsManager;
import com.sauthi.gograb.services.model.StatusMainModel;
import com.sauthi.gograb.services.model.StatusModel;
import com.sauthi.gograb.services.model.WishListMainModel;
import com.sauthi.gograb.services.model.WishListModel;
import com.sauthi.gograb.services.model.WishModel;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity  extends AppCompatActivity implements FavouriteListClickListener {

    public static String TAG = "WishlistActivity";

    public static final String BUNDLE_MAIN_NOTIFICATION_MODEL = "BundleMainModelNotificationModel";
    Context context;
    private static Activity activity;
    private static int SPLASH_TIME_OUT = 2000;
    int count = 0;

    static Toolbar toolbar;
    static ActionBar actionBar;
    static String mTitle;
    static int mResourceID;
    static int titleResourseID;
    static TextView toolbar_title;
    static ImageView toolbar_logo, tool_bar_back_icon;

    View mainView;

    GlobalVariables globalVariables;
    GlobalFunctions globalFunctions;

    String restaurant_id=null;
    boolean isWishlisted=false;

    WishListAdapter wishListAdapter;
    List<WishModel> wishModels = new ArrayList<>();
    LinearLayoutManager gridLayoutManager;
    ProgressLinearLayout progressActivity;
    RecyclerView wishListRecyclerview;
    SwipeRefreshLayout swipe_container;

   /* WishlistAdapter wishlistAdapter;
    List<String> wishlist = new ArrayList<>();
    GridLayoutManager wishlist_linear;
    ProgressLinearLayout progressActivity;
    RecyclerView wishlist_recyclerview;

*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist_activity);

        activity = this;
        context = this;


        globalFunctions = AppController.getInstance().getGlobalFunctions();
        globalVariables = AppController.getInstance().getGlobalVariables();

        gridLayoutManager=new GridLayoutManager(activity,2);
        progressActivity = findViewById(R.id.favourite_progressActivity);
        wishListRecyclerview = findViewById(R.id.wishlist_recyclerview);
        swipe_container = findViewById(R.id.swipe_container);


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

        mainView = wishListRecyclerview;


        getWishListData();

        swipe_container.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWishListData();
            }
        });


        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        setTitle(getString(R.string.favourite_restaurant), 0, 0);

    }

    private void getWishListData() {
         globalFunctions.showProgress(activity, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getWishListes(context, new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                 globalFunctions.hideProgress();
                if (swipe_container.isRefreshing()) {
                    swipe_container.setRefreshing( false );
                }
                Log.d(TAG, "Response : " + arg0.toString());
                WishListMainModel wishListMainModel = (WishListMainModel) arg0;
                if (wishListMainModel!=null && wishListMainModel.getWishListModel()!=null){
                    WishListModel listModel = wishListMainModel.getWishListModel();
                    //WishListModel wishListModel=listModel.getWishList();
                    setThisPage(listModel);
                }

            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnFailureFromServer(String msg) {
                globalFunctions.hideProgress();
                if (swipe_container.isRefreshing()) {
                    swipe_container.setRefreshing( false );
                }
                globalFunctions.displayMessaage(context, mainView, msg);
                Log.d(TAG, "Failure : " + msg);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnError(String msg) {
                globalFunctions.hideProgress();
                if (swipe_container.isRefreshing()) {
                    swipe_container.setRefreshing( false );
                }
                globalFunctions.displayMessaage(context, mainView, msg);
                Log.d(TAG, "Error : " + msg);
            }
        }, "list");
    }

    private void setThisPage(WishListModel wishListModel) {
        if (wishListModel != null && wishModels != null) {
            wishModels.clear();
            wishModels.addAll(wishListModel.getWishList());
            if (wishListAdapter != null) {
                synchronized (wishListAdapter) {
                    wishListAdapter.notifyDataSetChanged();
                }
            }

            if (wishModels.size() <= 0) {
                showEmptyPage();
            } else {
                showContent();
                initRecyclerView();
            }

        }
    }
    private void showEmptyPage() {
        if (progressActivity != null) {
            progressActivity.showEmpty(getResources().getDrawable(R.drawable.app_icon), getString(R.string.emptyList),
                    getString(R.string.no_favourite_item));
        }
    }


    private void showContent() {
        if (progressActivity != null) {
            progressActivity.showContent();
        }
    }

    private void initRecyclerView() {
        wishListRecyclerview.setLayoutManager(gridLayoutManager);
        wishListRecyclerview.setHasFixedSize(true);
        wishListAdapter = new WishListAdapter(activity, wishModels,this::OnFavouriteClickListener);
        wishListRecyclerview.setAdapter(wishListAdapter);
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
    public void OnFavouriteClickListener(int position, WishModel wishModel) {
        if (GlobalFunctions.isNotNullValue(wishModel.getId())){
            restaurant_id=wishModel.getId();
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
                getWishListData();

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

                }/*else {
                    //wishlist icon
                    isWishlisted=true;
                    //iv_favourite.setImageResource(R.drawable.ic_favourite_grey);

                }*/

            }
        }
    }
}
