package com.sa.gograb.profile;

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

import com.sa.gograb.R;
import com.sa.gograb.SplashActivity;
import com.sa.gograb.adapter.MyOrderAdapter;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.global.GlobalVariables;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.ProfileModel;
import com.sa.gograb.services.model.StatusModel;
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


    public static String TAG = "ProfileActivity";
    public static final String BUNDLE_MAIN_NOTIFICATION_MODEL = "BundleMainModelNotificationModel";
    Context context;
    Activity activity;
    private static int SPLASH_TIME_OUT = 2000;
    int count = 0;

    private TextView tv_edit_profile;
    private TextView tv_logout,tv_user_name,etv_mobile_no,etv_country_code;
    private RelativeLayout rl_favorite_main,rl_setting_main;
    private CircleImageView iv_profile;


    GlobalVariables globalVariables;
    GlobalFunctions globalFunctions;

    MyOrderAdapter myOrderAdapter;
    List<String> all_order = new ArrayList<>();
    LinearLayoutManager homeCategory_linear;
    ProgressLinearLayout progressActivity;
    RecyclerView recent_order_recyclerview;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);

        activity = getActivity();
        context = getActivity();

        tv_edit_profile=view.findViewById(R.id.tv_edit_profile);
        rl_favorite_main=view.findViewById(R.id.rl_favorite_main);
        tv_user_name=view.findViewById(R.id.tv_user_name);
        iv_profile=view.findViewById(R.id.iv_profile);
        etv_mobile_no=view.findViewById(R.id.etv_mobile_no);
        etv_country_code=view.findViewById(R.id.etv_country_code);
        tv_logout=view.findViewById(R.id.tv_logout);
        rl_setting_main=view.findViewById(R.id.rl_setting_main);

        recent_order_recyclerview = view.findViewById(R.id.recent_order_recyclerview);
        homeCategory_linear = new LinearLayoutManager(activity);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("The chicken jumbo burger X1, Cheese sandwich X2");


        homeCategoryRecyclerview(animalNames);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.ColorStatusBar));
        }

        tv_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,EditProfileActivity.class);
                startActivity(intent);
            }
        });
        rl_favorite_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, WishlistActivity.class);
                startActivity(intent);
            }
        });
        rl_setting_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();

            }
        });

        //set Profile Details
       // getProfile();

        return view;
    }

    private void getProfile() {
        ProfileModel profileModel = globalFunctions.getProfile( mainContext );
        if (profileModel != null && mainContext != null) {
            try {

                String
                        fullName = profileModel.getFirstName() + " " + profileModel.getLastName();
                        tv_user_name.setText( profileModel.getFullname() );
                        etv_country_code.setText( profileModel.getCountry_code() );
                        etv_mobile_no.setText( profileModel.getPhone());

                try {
                    if (profileModel.getProfileImg() != null || !profileModel.getProfileImg().equals( "null" ) || !profileModel.getProfileImg().equalsIgnoreCase( "" )) {
                        Picasso.with( mainContext ).load(profileModel.getProfileImg() ).placeholder( R.drawable.image_grp ).into( iv_profile );
                    }
                } catch (Exception e) {
                }

            } catch (Exception exxx) {
                Log.e( TAG, exxx.getMessage() );
            }

        } else {
            if (mainContext != null) {
                try {
                    etv_mobile_no.setVisibility( View.GONE );
                    tv_user_name.setText( mainContext.getString( R.string.guest ) );
                } catch (Exception exxx) {
                    Log.e( TAG, exxx.getMessage() );
                }
            }
        }
    }

    private void homeCategoryRecyclerview(ArrayList<String> animalNames) {
        recent_order_recyclerview.setLayoutManager(homeCategory_linear);
        recent_order_recyclerview.setHasFixedSize(true);
        myOrderAdapter = new MyOrderAdapter(activity,animalNames);
        recent_order_recyclerview.setAdapter(myOrderAdapter);
    }
    private void logout() {
       // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      //  drawer.closeDrawer(GravityCompat.START);
        final AlertDialog alertDialog = new AlertDialog( activity );
        alertDialog.setCancelable( false );
        alertDialog.setIcon( R.drawable.app_icon );
        alertDialog.setTitle( activity.getString( R.string.app_name ) );
        alertDialog.setMessage( activity.getResources().getString( R.string.appLogoutText ));
        alertDialog.setPositiveButton( activity.getString( R.string.yes ), new View.OnClickListener() {
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
        } );

        alertDialog.setNegativeButton( activity.getString( R.string.cancel ), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        } );

        alertDialog.show();

    }

    private void logoutUser(final  Context mainContext,final UpdateLanguageModel updateLanguageModel) {
        GlobalFunctions.showProgress( mainContext, getString( R.string.logingout ) );
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.logout( mainContext,updateLanguageModel, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                GlobalFunctions.hideProgress();
                Log.d( TAG, "Response : " + arg0.toString() );
                validateOutput( arg0 );
            }

            @Override
            public void OnFailureFromServer(String msg) {
                GlobalFunctions.hideProgress();
                Toast.makeText( mainContext, msg, Toast.LENGTH_SHORT ).show();
                //GlobalFunctions.displayMessaage(context, mainView, msg);
                Log.d( TAG, "Failure : " + msg );
            }

            @Override
            public void OnError(String msg) {
                GlobalFunctions.hideProgress();
                Toast.makeText( mainContext, msg, Toast.LENGTH_SHORT ).show();
                //GlobalFunctions.displayMessaage(context, mainView, msg);
                Log.d( TAG, "Error : " + msg );
            }
        }, "Logout_User" );
    }
    private void validateOutput(Object arg0) {
        if (arg0 instanceof StatusModel) {
            StatusModel statusModel = ( StatusModel ) arg0;
            //globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());
            if (statusModel.isStatus()) {
                /*Logout success, Clear all cache and reload the home page*/
                globalFunctions.logoutApplication( mainContext ,false);
                GlobalFunctions.closeAllActivities();
                RestartEntireApp( mainContext, false );

            }
        }

    }
    public void RestartEntireApp(Context context, boolean isLanguageChange) {
        if (isLanguageChange) {
            SharedPreferences shared_preference = PreferenceManager.getDefaultSharedPreferences( this
                    .getActivity() );

            String mCustomerLanguage = shared_preference.getString(
                    globalVariables.SHARED_PREFERENCE_SELECTED_LANGUAGE, "null" );
            String mCurrentlanguage;
            if ((mCustomerLanguage.equalsIgnoreCase( "en" ))) {
                globalFunctions.setLanguage( context, GlobalVariables.LANGUAGE.ARABIC );

                mCurrentlanguage = "ar";
            } else {
                mCurrentlanguage = "en";
                globalFunctions.setLanguage( context, GlobalVariables.LANGUAGE.ENGLISH );

            }
            SharedPreferences.Editor editor = shared_preference.edit();
            editor.putString( globalVariables.SHARED_PREFERENCE_SELECTED_LANGUAGE, mCurrentlanguage );
            editor.commit();
        }
        globalFunctions.closeAllActivities();
        Intent i = new Intent( activity, SplashActivity.class );
        i.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity( i );
        System.exit( 0 );
    }
    @Override
    public void onResume() {
         getProfile();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

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
