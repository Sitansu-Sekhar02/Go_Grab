package com.sauthi.gograb.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.snackbar.Snackbar;
import com.sauthi.gograb.AppController;
import com.sauthi.gograb.R;
import com.sauthi.gograb.adapter.HomeCategoryAdapter;
import com.sauthi.gograb.adapter.HomeTopCategoryListAdapter;
import com.sauthi.gograb.adapter.HomeSubCategoryListAdapter;
import com.sauthi.gograb.adapter.interfaces.OnWishlistClickInvoke;
import com.sauthi.gograb.menus.MenuListActivity;
import com.sauthi.gograb.restaurant.RestaurantListActivity;
import com.sauthi.gograb.global.GlobalFunctions;
import com.sauthi.gograb.global.GlobalVariables;
import com.sauthi.gograb.location_service.LocationMonitoringService;
import com.sauthi.gograb.search.SearchActivity;
import com.sauthi.gograb.services.ServerResponseInterface;
import com.sauthi.gograb.services.ServicesMethodsManager;
import com.sauthi.gograb.services.model.CusineModel;
import com.sauthi.gograb.services.model.HomeFilterCategoryListModel;
import com.sauthi.gograb.services.model.HomeFilterCategoryModel;
import com.sauthi.gograb.services.model.HomePageMainModel;
import com.sauthi.gograb.services.model.HomePageModel;
import com.sauthi.gograb.services.model.HomeSubCategoryListModel;
import com.sauthi.gograb.services.model.HomeSubCategoryModel;
import com.sauthi.gograb.services.model.HomeTopCategoryListModel;
import com.sauthi.gograb.services.model.HomeTopCategoryModel;
import com.sauthi.gograb.services.model.MenuModel;
import com.sauthi.gograb.services.model.RatingNFeedbackModel;
import com.sauthi.gograb.services.model.SearchResponseModel;
import com.sauthi.gograb.services.model.StatusMainModel;
import com.sauthi.gograb.services.model.StatusModel;
import com.squareup.picasso.Picasso;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment implements OnWishlistClickInvoke {

    public static final String TAG = "HomeFragment";
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

    private TextView tv_feedback_title, tv_preparation_time, tv_order_no, tv_chat_with_restro;
    private EditText et_feedback_comment;
    private CircleImageView iv_product_image, iv_menu_item;
    private ImageView iv_call, iv_location_to;
    //private Button btn_submit;

    String rating = "1";
    String order_id = null;
    RatingNFeedbackModel ratingNFeedbackModel;


    GlobalFunctions globalFunctions;
    GlobalVariables globalVariables;

    String restaurant_id = null;
    boolean isWishlisted = false;

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

    int selectedCatAdapterPosition = 0, selectedSubCatAdapterPosition = 0;
    ProgressDialog progress;
    LayoutInflater layoutInflater;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        activity = getActivity();
        context = getActivity();
        setHasOptionsMenu(true);

        layoutInflater = activity.getLayoutInflater();



        globalFunctions=AppController.getInstance().getGlobalFunctions();
        globalVariables=AppController.getInstance().getGlobalVariables();

        search_card_view = view.findViewById(R.id.search_card_view);

        //recyclerview id
        home_category_recyclerview = view.findViewById(R.id.home_category_recyclerview);
        home_top_category_recyclerview = view.findViewById(R.id.home_top_category_recyclerview);
        home_sub_category_recyclerview = view.findViewById(R.id.home_sub_category_recyclerview);

        //progress linear
        home_top_category_progress = view.findViewById(R.id.home_sub_category_progress);
        home_sub_category_progress = view.findViewById(R.id.home_sub_section_category_progress);
        home_category_progress = view.findViewById(R.id.home_category_progress);


        //view all id
        view_all_category_tv = view.findViewById(R.id.view_all_category_tv);
        view_all_sub_category_tv = view.findViewById(R.id.view_all_sub_category_tv);

        //layout manager
        homeCategory_linear = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        gridLayoutManager = new GridLayoutManager(activity, 2, GridLayoutManager.HORIZONTAL, false);
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
                startActivityForResult(intent, GlobalVariables.REQUEST_CODE_FOR_SEARCH);
            }
        });


        progress = new ProgressDialog(activity);
        progress.setTitle(activity.getString(R.string.loading));
        progress.setMessage(activity.getString(R.string.please_wait));
        progress.setCancelable(false);
        progress.show();

        //home API's
        homePageApi(context);

        view_all_category_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = RestaurantListActivity.newInstance(activity, "1");
                activity.startActivity(intent);

            }
        });

        view_all_sub_category_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = RestaurantListActivity.newInstance(activity, "2");
                activity.startActivity(intent);
            }
        });


        return view;

    }


    private void homePageApi(final Context context) {
       // globalFunctions.showProgress(activity, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getHomeDetails(context, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
               // globalFunctions.hideProgress();
                progress.dismiss();
                Log.d(TAG, "Response: " + arg0.toString());
                HomePageMainModel homePageMainModel = (HomePageMainModel) arg0;
                HomePageModel homePageModel = homePageMainModel.getHomePageModel();

                if (GlobalFunctions.isNotNullValue(homePageModel.getOrder_id())) {
                    userFeedbackPopup(homePageModel);

                }

                if (homePageModel.getHomeFilterCategoryListModel() != null) {
                    HomeFilterCategoryListModel homeFilterCategoryListModel = homePageModel.getHomeFilterCategoryListModel();
                    setUpCatFilterCatPage(homeFilterCategoryListModel);
                }
                if (homePageModel.getTop_near_rest() != null) {
                    HomeTopCategoryListModel homeTopCategoryListModel = homePageModel.getTop_near_rest();
                    setUpSubCatPage(homeTopCategoryListModel);
                }

                if (homePageModel.getPopular_rest() != null) {
                    HomeSubCategoryListModel subSection1CatListModel = homePageModel.getPopular_rest();
                    setUpSubSectionCatPage(subSection1CatListModel);
                }

            }

            @Override
            public void OnFailureFromServer(String msg) {
               // globalFunctions.hideProgress();
                globalFunctions.displayMessaage(activity, mainView, msg);
                Log.d(TAG, "Failure : " + msg);

            }

            @Override
            public void OnError(String msg) {
                //globalFunctions.hideProgress();
                globalFunctions.displayMessaage(activity, mainView, msg);

                Log.d(TAG, "Error : " + msg);
            }

        }, "Home Page");

    }

    private void setUpCatFilterCatPage(HomeFilterCategoryListModel homeTopCategoryListModel) {
        if (homeTopCategoryListModel != null && homeFilterCategoryModels != null) {
            homeFilterCategoryModels.clear();
            homeFilterCategoryModels.addAll(homeTopCategoryListModel.getHomeFilterCategoryModels());

            if (homeCategoryAdapter != null) {
                synchronized (homeCategoryAdapter) {
                    homeCategoryAdapter.notifyDataSetChanged();
                }
            }
            if (homeFilterCategoryModels.size() > 0) {
                // showFilterCatContent();
                subFilterCatInitRecycler();
            }

        }
    }

    private void setUpSubCatPage(HomeTopCategoryListModel homeTopCategoryListModel) {
        if (homeTopCategoryListModel != null && homeTopCategoryModels != null) {
            homeTopCategoryModels.clear();
            homeTopCategoryModels.addAll(homeTopCategoryListModel.getHomeTopCategoryModels());

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
            homeSubCategoryModels.addAll(subSection1CatListModel.getHomeSubCategoryModels());
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
        topCategoryListAdapter = new HomeTopCategoryListAdapter(activity, homeTopCategoryModels, this);
        home_top_category_recyclerview.setAdapter(topCategoryListAdapter);
        home_top_category_recyclerview.getLayoutManager().scrollToPosition(selectedCatAdapterPosition);
    }

    private void subFilterCatInitRecycler() {
        home_category_recyclerview.setLayoutManager(homeCategory_linear);
        home_category_recyclerview.setHasFixedSize(true);
        homeCategoryAdapter = new HomeCategoryAdapter(activity, homeFilterCategoryModels);
        home_category_recyclerview.setAdapter(homeCategoryAdapter);
    }

    private void subSectionCatInitRecycler() {
        home_sub_category_recyclerview.setLayoutManager(home_subSectionCat_linear);
        home_sub_category_recyclerview.setHasFixedSize(true);
        homeSubCategoryListAdapter = new HomeSubCategoryListAdapter(activity, homeSubCategoryModels, this);
        home_sub_category_recyclerview.setAdapter(homeSubCategoryListAdapter);


        home_sub_category_recyclerview.getLayoutManager().scrollToPosition(selectedSubCatAdapterPosition);
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

    private void userFeedbackPopup(HomePageModel homePageModel) {

        /*final android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(activity);
        final View alertView = layoutInflater.inflate(R.layout.feedback_activity, null, false);
        alertDialog.setView(alertView);
        alertDialog.setCancelable(false);
        final View dialogView = alertView;
        // alertDialog.setIcon(R.drawable.app_icon);
        final android.app.AlertDialog dialog = alertDialog.create();*/

        final Dialog ratingDialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar);
        ratingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ratingDialog.setContentView(R.layout.feedback_activity);
        Window window = ratingDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        ratingDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        ratingDialog.setCancelable(true);
        ratingDialog.setCanceledOnTouchOutside(true);
        ratingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ratingDialog.show();

       final  CircleImageView iv_product_image = ratingDialog.findViewById(R.id.iv_product_image);
       final EditText et_feedback_comment = ratingDialog.findViewById(R.id.etv_Comment);
       final Button btn_submit = ratingDialog.findViewById(R.id.btn_submit);
        et_feedback_comment.clearFocus();

        if (GlobalFunctions.isNotNullValue(homePageModel.getRestaurant_image()) && GlobalFunctions.isNotNullValue(homePageModel.getRestaurant_id())) {
            restaurant_id = homePageModel.getRestaurant_id();
            Picasso.with(activity).load(homePageModel.getRestaurant_image()).placeholder(R.drawable.lazy_load).into(iv_product_image);
        }
        if (GlobalFunctions.isNotNullValue(homePageModel.getOrder_id())) {
            order_id = homePageModel.getOrder_id();
        }

        final ImageView rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5;

        rating_iv1 = ratingDialog.findViewById(R.id.rating_iv1);
        rating_iv2 = ratingDialog.findViewById(R.id.rating_iv2);
        rating_iv3 = ratingDialog.findViewById(R.id.rating_iv3);
        rating_iv4 = ratingDialog.findViewById(R.id.rating_iv4);
        rating_iv5 = ratingDialog.findViewById(R.id.rating_iv5);

        setImageView(5, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);


        rating_iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(1, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        rating_iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(2, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        rating_iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(3, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        rating_iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(4, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        rating_iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(5, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String
                            comment = et_feedback_comment.getText().toString().trim();

                    if (ratingNFeedbackModel == null) {
                        ratingNFeedbackModel = new RatingNFeedbackModel();
                    }
                    ratingNFeedbackModel.setRestaurant_id(restaurant_id);
                    ratingNFeedbackModel.setOrder_id(order_id);
                    ratingNFeedbackModel.setRating(rating);
                    ratingNFeedbackModel.setComment(comment);

                    insertFeedback(activity, ratingNFeedbackModel);
                    ratingDialog.dismiss();

            }
        });

      //  dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       // dialog.show();
    }

    private void setImageView(int position, ImageView rating_iv1, ImageView rating_iv2, ImageView rating_iv3, ImageView rating_iv4, ImageView rating_iv5) {
        switch (position) {
            case 1:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_grey_star);
                rating_iv3.setImageResource(R.drawable.ic_grey_star);
                rating_iv4.setImageResource(R.drawable.ic_grey_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = position + "";
                break;

            case 2:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_yellow_star);
                rating_iv3.setImageResource(R.drawable.ic_grey_star);
                rating_iv4.setImageResource(R.drawable.ic_grey_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = position + "";
                break;

            case 3:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_yellow_star);
                rating_iv3.setImageResource(R.drawable.ic_yellow_star);
                rating_iv4.setImageResource(R.drawable.ic_grey_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = position + "";
                break;

            case 4:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_yellow_star);
                rating_iv3.setImageResource(R.drawable.ic_yellow_star);
                rating_iv4.setImageResource(R.drawable.ic_yellow_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = position + "";
                break;

            case 5:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_yellow_star);
                rating_iv3.setImageResource(R.drawable.ic_yellow_star);
                rating_iv4.setImageResource(R.drawable.ic_yellow_star);
                rating_iv5.setImageResource(R.drawable.ic_yellow_star);
                rating = position + "";
                break;

            default:
                rating_iv1.setImageResource(R.drawable.ic_grey_star);
                rating_iv2.setImageResource(R.drawable.ic_grey_star);
                rating_iv3.setImageResource(R.drawable.ic_grey_star);
                rating_iv4.setImageResource(R.drawable.ic_grey_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = "1";
        }
    }

    private void insertFeedback(Activity activity, RatingNFeedbackModel ratingNFeedbackModel) {
        //globalFunctions.showProgress(activity, activity.getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.insertFeedback(context, ratingNFeedbackModel, new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                //globalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                validateOutputAfterFeedback(arg0);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnFailureFromServer(String msg) {
                // globalFunctions.hideProgress();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Failure : " + msg);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnError(String msg) {
                //  globalFunctions.hideProgress();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Error : " + msg);
            }
        }, "feedback");
    }

    private void validateOutputAfterFeedback(Object arg0) {
        if (arg0 instanceof StatusMainModel) {
            StatusMainModel statusMainModel = (StatusMainModel) arg0;
            StatusModel statusModel = statusMainModel.getStatusModel();
            if (!statusMainModel.isStatusLogin()) {
                globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());

            } else {
                globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());
                homePageApi(context);

            }
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();


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
        if (GlobalFunctions.isNotNullValue(model.getId())) {
            restaurant_id = model.getId();
        }
        this.selectedCatAdapterPosition = position;
        checkWishlist(restaurant_id);

    }

    @Override
    public void OnSubCategoryClickInvoke(int position, HomeSubCategoryModel homeSubCategoryModel) {
        if (GlobalFunctions.isNotNullValue(homeSubCategoryModel.getId())) {
            restaurant_id = homeSubCategoryModel.getId();
        }
        this.selectedSubCatAdapterPosition = position;
        checkWishlist(restaurant_id);
    }

    private void checkWishlist(String restaurant_id) {
        // globalFunctions.showProgress(activity, activity.getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getCheckWishList(context, restaurant_id, new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                //globalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                //StatusModel model = (StatusModel) arg0;
                validateOutputAfterWishList(arg0);


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

    private void validateOutputAfterWishList(Object arg0) {
        if (arg0 instanceof StatusMainModel) {
            StatusMainModel statusMainModel = (StatusMainModel) arg0;
            StatusModel statusModel = statusMainModel.getStatusModel();
            // globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());
            if (statusMainModel.isStatusLogin()) {
                if (isWishlisted) {
                    //not wishlist icon
                    //iv_favourite.setImageResource(R.drawable.ic_favourite_grey);
                    isWishlisted = false;

                } else {
                    //wishlist icon
                    isWishlisted = true;
                    //iv_favourite.setImageResource(R.drawable.ic_favourite_grey);

                }

                homePageApi(context);

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
