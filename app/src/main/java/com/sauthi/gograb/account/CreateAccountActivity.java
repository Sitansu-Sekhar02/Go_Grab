package com.sauthi.gograb.account;

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
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.hbb20.CountryCodePicker;
import com.sauthi.gograb.AppController;
import com.sauthi.gograb.R;
import com.sauthi.gograb.global.GlobalFunctions;
import com.sauthi.gograb.global.GlobalVariables;
import com.sauthi.gograb.login.OtpActivity;
import com.sauthi.gograb.services.ServerResponseInterface;
import com.sauthi.gograb.services.ServicesMethodsManager;
import com.sauthi.gograb.services.model.LoginModel;
import com.sauthi.gograb.services.model.ProfileMainModel;
import com.sauthi.gograb.services.model.ProfileModel;
import com.sauthi.gograb.services.model.RegisterModel;
import com.sauthi.gograb.services.model.StatusMainModel;
import com.sauthi.gograb.services.model.StatusModel;
import com.sauthi.gograb.view.AlertDialog;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG ="CreateAccountActivity" ;
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
    private Button btn_continue;


    String selected_country_code = "";

    String pageType = null;
    RegisterModel myRegisterModel = null;

    LoginModel loginModel = null;
    RegisterModel registerModel = null;

    private EditText phone_number_etv;
    private TextView continue_tv, skip_login, register_tv, login_with_otp_tv,login_with_password_tv;
    private CountryCodePicker country_code_picker;
    private EditText password_etv;

    boolean isLoginBtnEnabled = false;

    GlobalVariables globalVariables;
    GlobalFunctions globalFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_activity);

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

        //initialize Ids
        btn_continue=findViewById(R.id.btn_continue);
        phone_number_etv = (EditText) findViewById(R.id.phone_number_etv);
        country_code_picker = (CountryCodePicker) findViewById(R.id.country_code_picker);

        country_code_picker.setCountryForPhoneCode(+91);

        mainView = phone_number_etv;

        country_code_picker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                selected_country_code = country_code_picker.getSelectedCountryCodeWithPlus();
                phone_number_etv.setText("");
            }
        });

        country_code_picker.registerCarrierNumberEditText(phone_number_etv);

        country_code_picker.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {
            @Override
            public void onValidityChanged(boolean isValidNumber) {
            }
        });

        selected_country_code = country_code_picker.getSelectedCountryCodeWithPlus();

        phone_number_etv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().startsWith("0")) {
                    s.clear();
                }
                String digits = phone_number_etv.getText().toString().trim();
                if (selected_country_code.equalsIgnoreCase("+91")) {
                    if (digits.length() >= 10) {
                        globalFunctions.closeKeyboard(activity);
                        showSubmitButton(true);
                    } else {
                        showSubmitButton(false);
                    }
                } else if (digits.length() >= getResources().getInteger(R.integer.mobile_max_length)) {
                    globalFunctions.closeKeyboard(activity);
                    showSubmitButton(true);
                } else {
                    showSubmitButton(false);
                }
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(activity, OtpActivity.class);
                //startActivity(intent);
                validateInput();

            }
        });


        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
      //  setTitle(getString(R.string.create_account), 0, 0);

}


    private void showSubmitButton(boolean isShown) {

//        String pwd = password_etv.getText().toString().trim();

        if (isShown) {
            btn_continue.setTextColor(activity.getResources().getColor(R.color.white));
            btn_continue.setBackground(activity.getResources().getDrawable(R.drawable.bg_button_color_accent_curved_filled));
            isLoginBtnEnabled = true;
        } else {
            isLoginBtnEnabled = false;
            btn_continue.setTextColor(activity.getResources().getColor(R.color.grey));
            btn_continue.setBackground(activity.getResources().getDrawable(R.drawable.bg_button_color_white_filled_half_border));
        }

    }

    private void validateInput() {
        if (phone_number_etv != null) {
            String
                    mobileNumber = phone_number_etv.getText().toString().trim();

            if (mobileNumber.isEmpty()) {
                phone_number_etv.setError(getString(R.string.enter_mobileno));
                phone_number_etv.setFocusableInTouchMode(true);
                phone_number_etv.requestFocus();
            }else if (selected_country_code.isEmpty()) {
                globalFunctions.displayMessaage(activity, mainView, getString(R.string.countryCodeNONotValid));
            } else if (!country_code_picker.isValidFullNumber()) {
                globalFunctions.displayMessaage(activity, mainView, getString(R.string.please_enter_valid_number));
                phone_number_etv.setSelection(phone_number_etv.getText().length());
                phone_number_etv.setFocusableInTouchMode(true);
                phone_number_etv.requestFocus();
            } else {

                    //go to otp page....
                    if (registerModel == null) {
                        registerModel = new RegisterModel();
                    }
                    registerModel.setCountryCode(selected_country_code);
                    registerModel.setMobileNumber(mobileNumber);
                    // registerModel.setPassword(GlobalFunctions.md5(password));

                        LoginModel model = new LoginModel();
                        model.setMobile_number(registerModel.getMobileNumber());
                        model.setCountryCode(registerModel.getCountryCode());
                        checkMobileNumber(context, model, registerModel);

                }

        }
    }
    private void checkMobileNumber(final Context context, final LoginModel loginModel, final RegisterModel registerModel) {
        GlobalFunctions.showProgress(activity, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.checkMobileNumber(context, loginModel, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                if (arg0 instanceof StatusMainModel) {
                    StatusMainModel statusMainModel = (StatusMainModel) arg0;
                    validateOutputAfterCheckingMobileNumber(statusMainModel, registerModel);
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

    private void validateOutputAfterCheckingMobileNumber(StatusMainModel statusMainModel, RegisterModel registerModel) {
        StatusModel statusModel = statusMainModel.getStatusModel();

        if (statusModel.getExtra().equalsIgnoreCase("1")) {
            showAlertMessage(statusModel.getMessage(), registerModel);
        } else if (statusModel.getExtra().equalsIgnoreCase("2")) {
            pageType=globalVariables.PAGE_FROM_LOGIN;
            sendOtpToMyMobileNumber(registerModel, pageType);
        } else {
            //registered user..
            globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());
        }

      /*  if (pageType.equalsIgnoreCase(globalVariables.PAGE_FROM_LOGIN)) {
            if (statusMainModel.isStatus()) {
                sendOtpToMyMobileNumber(registerModel, pageType);
            } else {
                if (statusModel.getExtra().equalsIgnoreCase("1")) {
                    showAlertMessage(statusModel.getMessage(), registerModel);
                } else if (statusModel.getExtra().equalsIgnoreCase("2")) {
                    sendOtpToMyMobileNumber(registerModel, pageType);
                } else {
                    //registered user..
                    globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());
                }
            }
        }*/
/*

        if (pageType.equalsIgnoreCase(globalVariables.PAGE_FROM_REGISTRATION)) {
            if (!statusMainModel.isStatus()) {
                //new user....go for registration..
                sendOtpToMyMobileNumber(registerModel, pageType);
//                Intent intent = RegisterActivity.newInstance(context, registerModel);
//                startActivity(intent);
            } else {
                //registered user..
                globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());
            }
        } else if (pageType.equalsIgnoreCase(globalVariables.PAGE_FROM_LOGIN)) {
            if (statusMainModel.isStatus()) {
                sendOtpToMyMobileNumber(registerModel, pageType);
            } else {
                if (statusModel.getExtra().equalsIgnoreCase("1")){
                    showAlertMessage(statusModel.getMessage(),registerModel);
                } else  if (statusModel.getExtra().equalsIgnoreCase("2")){
                    sendOtpToMyMobileNumber(registerModel, pageType);
                }else {
                    //registered user..
                    globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());
                }
            }
        }
*/

    }


    private void sendOtpToMyMobileNumber(RegisterModel model, String pageType) {
        String phoneNumber = selected_country_code + model.getMobileNumber();
        Intent intent = OtpActivity.newInstance(context, model, phoneNumber, pageType);
        activity.startActivity(intent);
    }
    private void showAlertMessage(String message, RegisterModel registerModel) {
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

    private void loginUser(final Context context, final LoginModel model,RegisterModel registerModel) {
        globalFunctions.showProgress(activity, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.loginUser(context, model, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                globalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                //StatusModel model = (StatusModel) arg0;
                validateOutputAfterLogin(arg0, registerModel);
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
        }, "LoginUser");
    }

    private void validateOutputAfterLogin(Object arg0, RegisterModel model) {
        if (arg0 instanceof StatusMainModel) {
            StatusMainModel statusMainModel = (StatusMainModel) arg0;
            StatusModel statusModel = statusMainModel.getStatusModel();
           /* if (!statusMainModel.isStatusLogin()) {
                globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());
            } else {
                GlobalFunctions.setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_TOKEN, statusModel.getToken());
                getProfile();

            }*/
        }

    }
    private void getProfile() {
        // globalFunctions.showProgress(activity, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getProfile(context, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                Log.d(TAG, "Response : " + arg0.toString());
                // globalFunctions.hideProgress();
                if (arg0 instanceof ProfileMainModel) {
                    ProfileMainModel profileMainModel=(ProfileMainModel) arg0;
                    ProfileModel profileModel = profileMainModel.getProfileModel();
                  //  GlobalFunctions.setProfile(context, profileModel);
                    closeThisActivity();
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

