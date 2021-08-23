package com.sa.gograb.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.hbb20.CountryCodePicker;
import com.sa.gograb.AppController;
import com.sa.gograb.MainActivity;
import com.sa.gograb.R;
import com.sa.gograb.account.CreateAccountActivity;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.global.GlobalVariables;
import com.sa.gograb.profile.ChangePasswordActivity;
import com.sa.gograb.registration.RegistrationActivity;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.LoginModel;
import com.sa.gograb.services.model.ProfileMainModel;
import com.sa.gograb.services.model.ProfileModel;
import com.sa.gograb.services.model.RegisterModel;
import com.sa.gograb.services.model.StatusMainModel;
import com.sa.gograb.services.model.StatusModel;
import com.sa.gograb.view.AlertDialog;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

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

    private EditText phone_number_etv, password_etv;
    private Button btn_login;
    private TextView skip_login, tv_register, login_with_otp_tv, login_with_password_tv, tv_forgot_password;
    private CountryCodePicker country_code_picker;


    RegisterModel myRegisterModel = null;

    LoginModel loginModel = null;
    RegisterModel registerModel = null;
    boolean isLoginBtnEnabled = false;
    String pageType = null;

    GlobalVariables globalVariables;
    GlobalFunctions globalFunctions;

    String selected_country_code = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        context = this;
        activity = this;


        globalFunctions = AppController.getInstance().getGlobalFunctions();
        globalVariables = AppController.getInstance().getGlobalVariables();


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tool_bar_back_icon = (ImageView) toolbar.findViewById(R.id.toolbar_icon);
        tool_bar_back_icon.setVisibility(View.GONE);
       /* tool_bar_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.ColorStatusBar));
        }


        //initialize id
        login_with_otp_tv = (TextView) findViewById(R.id.login_with_otp_tv);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_register = (TextView) findViewById(R.id.tv_register);
        phone_number_etv = findViewById(R.id.phone_number_etv);
        password_etv = findViewById(R.id.password_etv);
        country_code_picker = (CountryCodePicker) findViewById(R.id.country_code_picker);
        tv_forgot_password = (TextView) findViewById(R.id.tv_forgot_password);

        country_code_picker.setCountryForPhoneCode(+91);
        country_code_picker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                selected_country_code = country_code_picker.getSelectedCountryCodeWithPlus();
                phone_number_etv.setText("");
            }
        });
        mainView = toolbar;

        country_code_picker.registerCarrierNumberEditText(phone_number_etv);

        selected_country_code = country_code_picker.getSelectedCountryCodeWithPlus();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateInput();

            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CreateAccountActivity.class);
                activity.startActivity(intent);
            }
        });
        login_with_otp_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CreateAccountActivity.class);
                activity.startActivity(intent);
            }
        });
        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(activity, ChangePasswordActivity.class);
                // startActivity(intent);
                validaInputForChangePassword();
            }
        });

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        setTitle(getString(R.string.login_account), 0, 0);

    }

    private void validaInputForChangePassword() {
        if (phone_number_etv != null) {
            String
                    mobileNo = phone_number_etv.getText().toString().trim();
            if (mobileNo.isEmpty()) {
                phone_number_etv.setError(getString(R.string.phn_erro));
                phone_number_etv.setFocusableInTouchMode(true);
                phone_number_etv.requestFocus();
            } else if (!globalFunctions.isPhoneNumberValid(mobileNo)) {
                //                mobNo_ev.setText( "" );
                phone_number_etv.setError(getString(R.string.phoneNONotValid));
                phone_number_etv.setFocusableInTouchMode(true);
                phone_number_etv.requestFocus();
            } else {
                //go to otp page....
                if (loginModel == null) {
                    loginModel = new LoginModel();
                }
                loginModel.setCountryCode(selected_country_code);
                loginModel.setMobile_number(mobileNo);
                // registerModel.setPassword(GlobalFunctions.md5(password));

                checkMobileNumber(context, loginModel);

            }
        }

    }

    private void checkMobileNumber(final Context context, final LoginModel loginModel) {
        GlobalFunctions.showProgress(activity, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.checkMobileNumber(context, loginModel, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                if (arg0 instanceof StatusMainModel) {
                    StatusMainModel statusMainModel = (StatusMainModel) arg0;
                    validateOutputAfterCheckingMobileNumber(statusMainModel, loginModel);
                }
            }

            @Override
            public void OnFailureFromServer(String msg) {
                GlobalFunctions.hideProgress();
                GlobalFunctions.displayMessaage(activity, mainView, msg);
                Log.d(TAG, "Failure : " + msg);
            }

            @Override
            public void OnError(String msg) {
                GlobalFunctions.hideProgress();
                GlobalFunctions.displayMessaage(activity, mainView, msg);
                Log.d(TAG, "Error : " + msg);
            }
        }, "CheckMobileNumber");
    }

    private void validateOutputAfterCheckingMobileNumber(StatusMainModel statusMainModel, LoginModel loginModel) {
        StatusModel statusModel = statusMainModel.getStatusModel();

        if (statusModel.getExtra().equalsIgnoreCase("2")) {
            pageType = globalVariables.PAGE_TYPE_FORGOT_PASSWORD;
            sendOtpToMyMobileNumber(loginModel, pageType);
        } else {
            //registered user..
            globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());
        }
    }


    private void sendOtpToMyMobileNumber(LoginModel model, String pageType) {
        String phoneNumber = selected_country_code + model.getMobile_number();
        Intent intent = OtpActivity.newInstance(context, model, phoneNumber, pageType);
        activity.startActivity(intent);
    }
    private void showAlertMessage(String message, LoginModel registerModel) {
        final AlertDialog alertDialog = new AlertDialog(context);
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.drawable.app_icon);
        alertDialog.setTitle(getString(R.string.app_name));
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(getString(R.string.continu), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                pageType=globalVariables.PAGE_FROM_REGISTRATION;
                sendOtpToMyMobileNumber(registerModel, pageType);
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


    private void validateInput() {
        if (phone_number_etv != null && password_etv != null) {
            String
                    mobileNo = phone_number_etv.getText().toString().trim(),
                    password = password_etv.getText().toString().trim();

            if (mobileNo.isEmpty()) {
                phone_number_etv.setError(getString(R.string.phn_erro));
                phone_number_etv.setFocusableInTouchMode(true);
                phone_number_etv.requestFocus();
            } else if (!globalFunctions.isPhoneNumberValid(mobileNo)) {
                //                mobNo_ev.setText( "" );
                phone_number_etv.setError(getString(R.string.phoneNONotValid));
                phone_number_etv.setFocusableInTouchMode(true);
                phone_number_etv.requestFocus();
                // GlobalFunctions.displayMessaage(context, mainView, getString(R.string.mobileNumberNotValid));
            } else if (password.isEmpty()) {
                password_etv.setError(getString(R.string.password_error));
                password_etv.setFocusableInTouchMode(true);
                password_etv.requestFocus();
            } else if (selected_country_code.isEmpty()) {
                globalFunctions.displayMessaage(activity, mainView, getString(R.string.countryCodeNONotValid));
            } else if (!country_code_picker.isValidFullNumber()) {
//                mobile_number_etv.setText("");
                globalFunctions.displayMessaage(activity, mainView, getString(R.string.please_enter_valid_number));
                phone_number_etv.setSelection(phone_number_etv.getText().length());
                phone_number_etv.setFocusableInTouchMode(true);
                phone_number_etv.requestFocus();
            } else {
                LoginModel loginModel = new LoginModel();
                loginModel.setMobile_number(mobileNo);
                loginModel.setCountryCode(selected_country_code);
                loginModel.setPassword(password);

                loginUser(context, loginModel);

            }
        }
    }

    private void loginUser(final Context context, final LoginModel model) {
        GlobalFunctions.showProgress(context, getString(R.string.loggin));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.loginUser(context, model, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                //hideLoading();
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                validateOutput(arg0);
            }

            @Override
            public void OnFailureFromServer(String msg) {
                //                hideLoading();
                GlobalFunctions.hideProgress();
                GlobalFunctions.displayMessaage(context, mainView, msg);
                Log.d(TAG, "Failure : " + msg);
            }

            @Override
            public void OnError(String msg) {
                //                hideLoading();
                GlobalFunctions.hideProgress();
                GlobalFunctions.displayMessaage(context, mainView, msg);
                Log.d(TAG, "Error : " + msg);
            }
        }, "Login_User");
    }

    private void validateOutput(Object model) {
        if (model instanceof StatusMainModel) {
            StatusMainModel statusMainModel = (StatusMainModel) model;
            StatusModel statusModel = statusMainModel.getStatusModel();
            if (statusMainModel.isStatusLogin()) {

                GlobalFunctions.setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_TOKEN, statusModel.getToken());
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                //get profile api
                getProfile();
            } else {
                GlobalFunctions.displayErrorDialog(context, statusModel.getMessage());
            }
             /*   if (statusModel.getExtra().equalsIgnoreCase( "1" )) {
                    final AlertDialog alertDialog = new AlertDialog( activity );
                    alertDialog.setCancelable( false );
                    alertDialog.setIcon( R.drawable.ic_app );
                    alertDialog.setTitle( getString( R.string.otp ) );
                    alertDialog.setMessage( statusModel.getMessage() );

                    alertDialog.setPositiveButton( getString( R.string.ok ), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
    //                        GlobalFunctions.closeAllActivities();
                            OtpModel otpModel = new OtpModel();
                            otpModel.setPhone( loginModel.getUserName() );
                            otpModel.setCountryCode( loginModel.getCountryCode() );
                            Intent intent = RegisterActivity.newInstance( context, otpModel );
                            startActivity( intent );
                        }

                    } );
                    alertDialog.show();

                } else {*/
            //            GlobalFunctions.displayErrorDialog( context, statusModel.getMessage() );

        } /*else if (model instanceof ProfileModel) {
                GlobalFunctions.closeAllActivities();
                ProfileModel profileModel = ( ProfileModel ) model;
                GlobalFunctions.setProfile( context, profileModel );
                Intent intent = new Intent( context, MainActivity.class );
                startActivity( intent );
            }*/
    }

    private void getProfile() {
        GlobalFunctions.showProgress(context, getString(R.string.loading_profile));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getProfile(context, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                //                hideLoading();
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                //ProfileDetails(arg0);
                validateProfileOutput(arg0); //naming convention of method should be proper ?

            }

            @Override
            public void OnFailureFromServer(String msg) {
                //                hideLoading();
                GlobalFunctions.hideProgress();
                GlobalFunctions.displayMessaage(LoginActivity.this.context, mainView, msg);
                Log.d(TAG, "Failure : " + msg);
            }

            @Override
            public void OnError(String msg) {
                //                hideLoading();
                GlobalFunctions.hideProgress();
                GlobalFunctions.displayMessaage(LoginActivity.this.context, mainView, msg);
                Log.d(TAG, "Error : " + msg);
            }
        }, "ProfileFragment");
    }

    private void validateProfileOutput(Object arg0) {
        if (arg0 instanceof ProfileMainModel) {

            GlobalFunctions.closeAllActivities();
            ProfileMainModel profileMainModel = (ProfileMainModel) arg0;
            ProfileModel profileModel = profileMainModel.getProfileModel();
            GlobalFunctions.setProfile(context, profileModel);
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);

        }

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public static void setTitle(String title, int titleImageID, int backgroundResourceID) {
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
    private static void restoreToolbar() {
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

    public void onBackPressed() {
        closeThisActivity();
        super.onBackPressed();
    }

    public static void closeThisActivity() {
        if (activity != null) {
            activity.finish();
            //activity.overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getFragmentManager().findFragmentByTag(TAG) != null)
            getFragmentManager().findFragmentByTag(TAG).setRetainInstance(true);
    }

    @Override
    public void onStart() {

       /* if(hint != null) {
            hint.launchAutomaticHintForCall(activity.findViewById(R.id.action_call));
        }*/
//       globalFunctions.launchAutomaticHintForSearch(mainView, getString(R.string.search_title),  getString(R.string.search_description));
        super.onStart();
    }

    @Override
    public void onDestroy() {
        if (activity != null) activity = null;
        super.onDestroy();
    }

}
