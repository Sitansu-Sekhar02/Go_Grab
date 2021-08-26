package com.sa.gograb.profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.github.ramiz.nameinitialscircleimageview.NameInitialsCircleImageView;
import com.sa.gograb.R;
import com.sa.gograb.SplashActivity;
import com.sa.gograb.adapter.MyOrderAdapter;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.global.GlobalVariables;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.CategoryMenuListModel;
import com.sa.gograb.services.model.MenuListMainModel;
import com.sa.gograb.services.model.MenuModel;
import com.sa.gograb.services.model.OrderDetailListModel;
import com.sa.gograb.services.model.OrderDetailModel;
import com.sa.gograb.services.model.OrderListMainModel;
import com.sa.gograb.services.model.OrderListModel;
import com.sa.gograb.services.model.OrderMainModel;
import com.sa.gograb.services.model.OrderModel;
import com.sa.gograb.services.model.ProfileMainModel;
import com.sa.gograb.services.model.ProfileModel;
import com.sa.gograb.services.model.StatusModel;
import com.sa.gograb.services.model.TrendingMenuListModel;
import com.sa.gograb.services.model.UpdateLanguageModel;
import com.sa.gograb.view.AlertDialog;
import com.sa.gograb.wishlist.WishlistActivity;
import com.squareup.picasso.Picasso;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.sa.gograb.MainActivity.mainContext;

public class ProfileFragment extends Fragment {


    public static String TAG = "ProfileFragment";
    public static final String BUNDLE_MAIN_NOTIFICATION_MODEL = "BundleMainModelNotificationModel";
    Context context;
    Activity activity;
    View mainView;
    private static int SPLASH_TIME_OUT = 2000;
    int count = 0;

    private TextView tv_edit_profile, tv_view_allOrders;
    private TextView tv_logout, tv_user_name, etv_mobile_no, etv_country_code, tv_order_date, tv_item_title, tv_ratings, tv_rating_count, tv_distance;
    private RelativeLayout rl_favorite_main, rl_setting_main;
   // private CircleImageView iv_profile, iv_restaurant;
    NameInitialsCircleImageView iv_profile;


    GlobalVariables globalVariables;
    GlobalFunctions globalFunctions;

    MyOrderAdapter myOrderAdapter;
    List<OrderModel> orderModels = new ArrayList<>();
    List<OrderModel> fullOrderModels = new ArrayList<>();
    LinearLayoutManager order_linear;
    ProgressLinearLayout details_progressActivity;
    RecyclerView recent_order_recyclerview;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);

        activity = getActivity();
        context = getActivity();

        tv_edit_profile = view.findViewById(R.id.tv_edit_profile);
        rl_favorite_main = view.findViewById(R.id.rl_favorite_main);
        tv_user_name = view.findViewById(R.id.tv_user_name);
        iv_profile = view.findViewById(R.id.iv_profile);
        etv_mobile_no = view.findViewById(R.id.etv_mobile_no);
        etv_country_code = view.findViewById(R.id.etv_country_code);
        tv_logout = view.findViewById(R.id.tv_logout);
        rl_setting_main = view.findViewById(R.id.rl_setting_main);
       // iv_restaurant = view.findViewById(R.id.iv_restaurant);
        tv_item_title = view.findViewById(R.id.tv_item_title);
        tv_order_date = view.findViewById(R.id.tv_order_date);
        tv_ratings = view.findViewById(R.id.tv_ratings);
        tv_rating_count = view.findViewById(R.id.tv_rating_count);
        tv_distance = view.findViewById(R.id.tv_distance);
        tv_view_allOrders = view.findViewById(R.id.tv_view_allOrders);


        recent_order_recyclerview = view.findViewById(R.id.recent_order_recyclerview);
        details_progressActivity = view.findViewById(R.id.details_progressActivity);
        order_linear = new LinearLayoutManager(activity);
        mainView = tv_edit_profile;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.ColorStatusBar));
        }

        getOrderList();


        tv_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, EditProfileActivity.class);
                startActivity(intent);
            }
        });
        rl_favorite_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, WishlistActivity.class);
                startActivity(intent);
            }
        });
        rl_setting_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(activity, ChangePasswordActivity.class);
//                startActivity(intent);
            }
        });

        tv_view_allOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTrendingMenuContent();
                orderInitRecyclerView(true);
                tv_view_allOrders.setVisibility(View.GONE);
            }
        });


        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();

            }
        });

        //get Profile
        // getProfile();

        return view;
    }

    private void getOrderList() {
        GlobalFunctions.showProgress(context, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getOrderList(context, new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                OrderMainModel orderMainModel = (OrderMainModel) arg0;
                OrderListModel orderListModel = orderMainModel.getOrderListModel();

                if (orderListModel.getOrderModels() != null) {
                    setThisPage(orderListModel);
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
        }, "Order List");
    }

    private void setThisPage(OrderListModel orderListModel) {
        if (orderListModel != null && orderModels != null) {
            orderModels.clear();
            fullOrderModels.clear();
            fullOrderModels.addAll(orderListModel.getOrderModels());
            if (myOrderAdapter != null) {
                synchronized (myOrderAdapter) {
                    myOrderAdapter.notifyDataSetChanged();
                }
            }
            if (fullOrderModels.size() <= 0) {
                //showTrendingMenuEmptyPage();
            } else {
                setUpOrderList(fullOrderModels);

            }
        }
    }

    private void setUpOrderList(List<OrderModel> fullOrderModels) {
        if (fullOrderModels.size() >= 2) {
            for (int i = 0; i < 2; i++) {
                orderModels.add(fullOrderModels.get(i));
            }
            showTrendingMenuContent();
            orderInitRecyclerView(false);
        } else {
            showTrendingMenuContent();
            orderInitRecyclerView(true);
        }
    }


    private void showTrendingMenuContent() {
        if (details_progressActivity != null) {
            details_progressActivity.showContent();
        }
    }

    private void orderInitRecyclerView(boolean isFullList) {
        recent_order_recyclerview.setLayoutManager(order_linear);
        recent_order_recyclerview.setHasFixedSize(true);
        if (isFullList) {
            myOrderAdapter = new MyOrderAdapter(activity, fullOrderModels);
        } else {
            myOrderAdapter = new MyOrderAdapter(activity, orderModels);
        }
        recent_order_recyclerview.setAdapter(myOrderAdapter);
    }

    private void getProfile() {
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getProfile(context, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                Log.d(TAG, "Response : " + arg0.toString());
                // globalFunctions.hideProgress();
                if (arg0 instanceof ProfileMainModel) {
                    ProfileMainModel profileMainModel = (ProfileMainModel) arg0;
                    ProfileModel profileModel = profileMainModel.getProfileModel();
                    GlobalFunctions.setProfile(context, profileModel);
                    setProfile();
                }
            }

            @Override
            public void OnFailureFromServer(String msg) {
                // globalFunctions.hideProgress();
                globalFunctions.displayMessaage(context, mainView, msg);
                Log.d(TAG, "Failure : " + msg);
            }

            @Override
            public void OnError(String msg) {
                // globalFunctions.hideProgress();
                globalFunctions.displayMessaage(context, mainView, msg);
                Log.d(TAG, "Error : " + msg);
            }
        }, "Get Profile");
    }

    private void setProfile() {
        ProfileModel profileModel = globalFunctions.getProfile(mainContext);
        if (profileModel != null && mainContext != null) {
            try {

                String
                        fullName = profileModel.getFirstName() + " " + profileModel.getLastName();
                tv_user_name.setText(profileModel.getFullname());
                etv_country_code.setText(profileModel.getCountry_code());
                etv_mobile_no.setText(profileModel.getPhone());

                try {
                    if (profileModel.getProfileImg() != null || !profileModel.getProfileImg().equals("null") || !profileModel.getProfileImg().equalsIgnoreCase("")) {

                        String firstLetter= String.valueOf(profileModel.getFullname().charAt(0));
                        NameInitialsCircleImageView.ImageInfo imageInfo = new NameInitialsCircleImageView.ImageInfo
                                .Builder(firstLetter)
                                .setTextColor(android.R.color.primary_text_dark)
                                .setImageUrl(profileModel.getImage())
                                .setCircleBackgroundColorRes(android.R.color.black)
                                .build();
                        iv_profile.setImageInfo(imageInfo);

                       // Picasso.with(mainContext).load(profileModel.getProfileImg()).placeholder(R.drawable.lazy_load).into(iv_profile);
                    }
                } catch (Exception e) {
                }


            } catch (Exception exxx) {
                Log.e(TAG, exxx.getMessage());
            }

        } else {
            if (mainContext != null) {
                try {
                    etv_mobile_no.setVisibility(View.GONE);
                    tv_user_name.setText(mainContext.getString(R.string.guest));
                } catch (Exception exxx) {
                    Log.e(TAG, exxx.getMessage());
                }
            }
        }
    }

    private void logout() {
        // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //  drawer.closeDrawer(GravityCompat.START);
        final AlertDialog alertDialog = new AlertDialog(activity);
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.drawable.app_icon);
        alertDialog.setTitle(activity.getString(R.string.app_name));
        alertDialog.setMessage(activity.getResources().getString(R.string.appLogoutText));
        alertDialog.setPositiveButton(activity.getString(R.string.yes), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                //Logout the Application
              /*  LogoutModel logoutModel=new LogoutModel();
                logoutModel.setUuid(GlobalFunctions.getUniqueID(activity));
                logoutUser( mainContext ,logoutModel);*/

                UpdateLanguageModel updateLanguageModel = new UpdateLanguageModel();
                if (GlobalFunctions.isNotNullValue(GlobalFunctions.getSharedPreferenceString(mainContext, GlobalVariables.SHARED_PREFERENCE_TOKEN))) {
                    updateLanguageModel.setPushToken(GlobalFunctions.getSharedPreferenceString(mainContext, GlobalVariables.SHARED_PREFERENCE_TOKEN));
                    logoutUser(mainContext, updateLanguageModel);
                }
            }
        });

        alertDialog.setNegativeButton(activity.getString(R.string.cancel), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    private void logoutUser(final Context mainContext, final UpdateLanguageModel updateLanguageModel) {
        GlobalFunctions.showProgress(mainContext, getString(R.string.logingout));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.logout(mainContext, updateLanguageModel, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                validateOutput(arg0);
            }

            @Override
            public void OnFailureFromServer(String msg) {
                GlobalFunctions.hideProgress();
                Toast.makeText(mainContext, msg, Toast.LENGTH_SHORT).show();
                //GlobalFunctions.displayMessaage(context, mainView, msg);
                Log.d(TAG, "Failure : " + msg);
            }

            @Override
            public void OnError(String msg) {
                GlobalFunctions.hideProgress();
                Toast.makeText(mainContext, msg, Toast.LENGTH_SHORT).show();
                //GlobalFunctions.displayMessaage(context, mainView, msg);
                Log.d(TAG, "Error : " + msg);
            }
        }, "Logout_User");
    }

    private void validateOutput(Object arg0) {
        if (arg0 instanceof StatusModel) {
            StatusModel statusModel = (StatusModel) arg0;
            //globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());
            if (statusModel.isStatus()) {
                /*Logout success, Clear all cache and reload the home page*/
                globalFunctions.logoutApplication(mainContext, false);
                GlobalFunctions.closeAllActivities();
                RestartEntireApp(mainContext, false);

            }
        }

    }

    public void RestartEntireApp(Context context, boolean isLanguageChange) {
        if (isLanguageChange) {
            SharedPreferences shared_preference = PreferenceManager.getDefaultSharedPreferences(this
                    .getActivity());

            String mCustomerLanguage = shared_preference.getString(
                    globalVariables.SHARED_PREFERENCE_SELECTED_LANGUAGE, "null");
            String mCurrentlanguage;
            if ((mCustomerLanguage.equalsIgnoreCase("en"))) {
                globalFunctions.setLanguage(context, GlobalVariables.LANGUAGE.ARABIC);

                mCurrentlanguage = "ar";
            } else {
                mCurrentlanguage = "en";
                globalFunctions.setLanguage(context, GlobalVariables.LANGUAGE.ENGLISH);

            }
            SharedPreferences.Editor editor = shared_preference.edit();
            editor.putString(globalVariables.SHARED_PREFERENCE_SELECTED_LANGUAGE, mCurrentlanguage);
            editor.commit();
        }
        globalFunctions.closeAllActivities();
        Intent i = new Intent(activity, SplashActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        System.exit(0);
    }

    @Override
    public void onResume() {
        getProfile();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        // MainActivity.setTitleResourseID(0);
        // (( ProfileMainActivity ) activity).setTitle( getString( R.string.my_profile ), R.drawable.rezq_logo, 0 );
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        //((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}
