package com.sauthi.gograb.profile;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.sauthi.gograb.AppController;
import com.sauthi.gograb.R;
import com.sauthi.gograb.global.GlobalFunctions;
import com.sauthi.gograb.global.GlobalVariables;
import com.sauthi.gograb.login.LoginActivity;
import com.sauthi.gograb.services.ServerResponseInterface;
import com.sauthi.gograb.services.ServicesMethodsManager;
import com.sauthi.gograb.services.model.ChangePasswordModel;
import com.sauthi.gograb.services.model.LoginModel;
import com.sauthi.gograb.services.model.StatusMainModel;
import com.sauthi.gograb.services.model.StatusModel;
import com.sauthi.gograb.view.AlertDialog;

public class ChangePasswordActivity extends AppCompatActivity {

    private static final String TAG ="ChangePasswordActivity" ;
    public static final String BUNDLE_OTP_ACTIVITY_LOGIN_MODEL = "BundleOtpActivityLoginModel";
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

    private EditText password_etv,confirm_password_etv;
    private Button btn_change_password;
    ChangePasswordModel changePasswordModel = null;

    GlobalVariables globalVariables;
    GlobalFunctions globalFunctions;
    LoginModel loginModel = null;

    public static Intent newInstance(Context context, LoginModel model) {
        Intent intent = new Intent(context, ChangePasswordActivity.class);
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_OTP_ACTIVITY_LOGIN_MODEL, model);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_activity);

        context = this;
        activity = this;


        globalFunctions = AppController.getInstance().getGlobalFunctions();
        globalVariables = AppController.getInstance().getGlobalVariables();


        if (getIntent().hasExtra(BUNDLE_OTP_ACTIVITY_LOGIN_MODEL)) {
            loginModel = (LoginModel) getIntent().getSerializableExtra(BUNDLE_OTP_ACTIVITY_LOGIN_MODEL);
        } else {
            loginModel = null;
        }

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
        password_etv =findViewById(R.id.password_etv);
        confirm_password_etv =findViewById(R.id.confirm_password_etv);
        btn_change_password =findViewById(R.id.btn_change_password);

        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateChangePassword();
            }
        });


        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        setTitle(getString(R.string.change_password), 0, 0);


    }

    private void validateChangePassword() {
        if (password_etv != null || confirm_password_etv != null  ) {
            String
                    changePassword = password_etv.getText().toString().trim(),
                    confirmPassword = confirm_password_etv.getText().toString().trim();

            if (changePassword.isEmpty()) {
                password_etv.setError( getString( R.string.pleaseFillMandatoryDetails) );
                password_etv.setFocusableInTouchMode( true );
                password_etv.requestFocus();
            }  else if (confirmPassword.isEmpty()) {
                confirm_password_etv.setError(getString(R.string.pleaseFillMandatoryDetails));
                confirm_password_etv.setFocusableInTouchMode(true);
                confirm_password_etv.requestFocus();
            }else if (changePassword.length() < 8) {
                password_etv.setError(getString(R.string.passwordContainsError));
                password_etv.setFocusableInTouchMode(true);
                password_etv.requestFocus();
            } else if (!confirmPassword.equals(confirmPassword)) {
                globalFunctions.displayMessaage(activity, mainView, getString(R.string.passwordNotMatched));
                confirm_password_etv.setFocusableInTouchMode(true);
                confirm_password_etv.requestFocus();
            }  else {

                if (changePasswordModel == null) {
                    changePasswordModel = new ChangePasswordModel();
                }
                //profileModel= GlobalFunctions.getProfile(activity);

                changePasswordModel.setNewPassword(globalFunctions.md5(changePassword ));
                if (loginModel!=null && loginModel.getMobile_number()!=null)
                changePasswordModel.setMobileNumber( loginModel.getMobile_number());

                updatePassword(context, changePasswordModel);


            }
        }

    }

    private void updatePassword(Context context, ChangePasswordModel changePasswordModel) {
        globalFunctions.showProgress(activity, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.changePassword(context, changePasswordModel, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                globalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                //StatusModel model = (StatusModel) arg0;
                validateOutputAfterPasswordChange(arg0);
            }

            @Override
            public void OnFailureFromServer(String msg) {
                globalFunctions.hideProgress();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Failure : " + msg);
            }

            @Override
            public void OnError(String msg) {
                globalFunctions.hideProgress();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Error : " + msg);
            }
        }, "Change Password");
    }

    private void validateOutputAfterPasswordChange(Object arg0) {
        if (arg0 instanceof StatusMainModel) {
            StatusMainModel statusMainModel = (StatusMainModel) arg0;
            StatusModel statusModel = statusMainModel.getStatusModel();
            if (statusModel.isStatus()) {
                showAlertMessage(statusModel.getMessage());

      //          GlobalFunctions.setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_TOKEN, statusModel.getToken());
//                showAlertMessage(subProfileModel.getMessage());
              //  getProfile();
            } else {
                globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());

            }
        }
    }
    private void showAlertMessage(String message) {
        final AlertDialog alertDialog = new AlertDialog(context);
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.drawable.app_icon);
        alertDialog.setTitle(getString(R.string.app_name));
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(getString(R.string.continu), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                GlobalFunctions.closeAllActivities();
                Intent intent=new Intent(activity, LoginActivity.class);
                startActivity(intent);

            }
        });

        alertDialog.setNegativeButton(getString(R.string.ok), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
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
