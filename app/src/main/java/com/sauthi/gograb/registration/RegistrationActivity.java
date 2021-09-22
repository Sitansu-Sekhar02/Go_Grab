package com.sauthi.gograb.registration;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.sauthi.gograb.AppController;
import com.sauthi.gograb.MainActivity;
import com.sauthi.gograb.R;
import com.sauthi.gograb.global.GlobalFunctions;
import com.sauthi.gograb.global.GlobalVariables;
import com.sauthi.gograb.services.ServerResponseInterface;
import com.sauthi.gograb.services.ServicesMethodsManager;
import com.sauthi.gograb.services.model.ProfileMainModel;
import com.sauthi.gograb.services.model.ProfileModel;
import com.sauthi.gograb.services.model.RegisterModel;
import com.sauthi.gograb.services.model.StatusProfileModel;
import com.sauthi.gograb.services.model.SubProfileModel;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG ="RegistrationActivity" ;


    public static final String BUNDLE_REGISTER_ACTIVITY_REGISTER_MODEL = "BundleRegisterActivityRegisterModel";
    public static final String BUNDLE_CITY_MODEL = "BundleCityModel";

    private static final int PERMISSION_REQUEST_CODE = 200;
    public static final int REQUEST_IMAGE = 100;

    String
            mobileNumber = "",
            countryCode = "";

    GlobalVariables globalVariables;
    GlobalFunctions globalFunctions;
    private LayoutInflater layoutInflater;

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

    String
            selectedGender = "0";

    private Button btn_register;
    private EditText full_name_etv, email_etv, phone_number_etv, vehicle_no_etv, choose_password_etv, confirm_password_etv;
    private TextView  gender_tv, tv_register, login_with_otp_tv,login_with_password_tv;
    private AppCompatCheckBox terms_checkbox;

    RegisterModel myRegisterModel = null;
    RegisterModel registerModel = null;

    public static Intent newInstance(Context context, RegisterModel model) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_REGISTER_ACTIVITY_REGISTER_MODEL, model);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        context = this;
        activity = this;

        this.layoutInflater = activity.getLayoutInflater();

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


        btn_register = (Button) findViewById(R.id.btn_register);
        full_name_etv = (EditText) findViewById(R.id.full_name_etv);
        vehicle_no_etv = (EditText) findViewById(R.id.vehicle_no_etv);
        email_etv = (EditText) findViewById(R.id.email_etv);
        choose_password_etv = (EditText) findViewById(R.id.choose_password_etv);
        confirm_password_etv = (EditText) findViewById(R.id.confirm_password_etv);
        gender_tv = (TextView) findViewById(R.id.gender_tv);
        terms_checkbox = (AppCompatCheckBox) findViewById(R.id.terms_checkbox);


        mainView = toolbar;

        if (getIntent().hasExtra(BUNDLE_REGISTER_ACTIVITY_REGISTER_MODEL)) {
            myRegisterModel = (RegisterModel) getIntent().getSerializableExtra(BUNDLE_REGISTER_ACTIVITY_REGISTER_MODEL);
        }

        if (myRegisterModel != null) {
            if (myRegisterModel.getCountryCode() != null && myRegisterModel.getMobileNumber() != null) {
                countryCode = myRegisterModel.getCountryCode();
                //country_code_picker.setCountryForPhoneCode(Integer.parseInt(myRegisterModel.getCountryCode()));
                mobileNumber = myRegisterModel.getMobileNumber();
               // phone_number_etv.setText(countryCode +" "+ myRegisterModel.getMobileNumber());
            }
        }
        gender_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGenderDialog(activity);
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(activity, MainActivity.class);
              //  activity.startActivity(intent);
                validateInput();

            }
        });



        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        setTitle(getString(R.string.register), 0, 0);


    }
    private void openGenderDialog(final Context context) {

        final android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(activity);
        final View alertView = layoutInflater.inflate(R.layout.gender_custom_dialog, null, false);
        alertDialog.setView(alertView);
        alertDialog.setCancelable(true);
        // alertDialog.setIcon(R.drawable.app_icon);
        final android.app.AlertDialog dialog = alertDialog.create();

        TextView back_tv, none_tv,male_tv, female_tv;

        back_tv = (TextView) alertView.findViewById(R.id.back_tv);
        male_tv = (TextView) alertView.findViewById(R.id.male_tv);
        female_tv = (TextView) alertView.findViewById(R.id.female_tv);
        none_tv = (TextView) alertView.findViewById(R.id.none_tv);


        back_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        none_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedGender = globalVariables.GENDER_NONE;
                gender_tv.setHint(getString(R.string.gender_optional));
                gender_tv.setText("");
                dialog.dismiss();
            }
        });

        male_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedGender = globalVariables.GENDER_MALE;
                gender_tv.setText(getString(R.string.male));
                dialog.dismiss();
            }
        });

        female_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedGender = globalVariables.GENDER_FEMALE;
                gender_tv.setText(getString(R.string.female));
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void validateInput() {
        if (full_name_etv != null && email_etv != null &&  choose_password_etv != null && confirm_password_etv != null && vehicle_no_etv!=null ) {
            String
                    fullName = full_name_etv.getText().toString().trim(),
                    email = email_etv.getText().toString().trim(),
                  //  mobile = phone_number_etv.getText().toString().trim(),
                    choosePassword = choose_password_etv.getText().toString().trim(),
                    confirmPassword = confirm_password_etv.getText().toString().trim(),
                    vehicleNumber = vehicle_no_etv.getText().toString().trim();



            if (fullName.isEmpty()) {
                full_name_etv.setError(getString(R.string.pleaseFillMandatoryDetails));
                full_name_etv.setFocusableInTouchMode(true);
                full_name_etv.requestFocus();
            }/*else if (mobile.isEmpty()) {
                phone_number_etv.setError(getString(R.string.pleaseFillMandatoryDetails));
                phone_number_etv.setFocusableInTouchMode(true);
                phone_number_etv.requestFocus();
            }else if (!globalFunctions.isPhoneNumberValid(mobile)) {
                globalFunctions.displayMessaage(activity, mainView, getString(R.string.phoneNONotValid));
                phone_number_etv.setFocusableInTouchMode(true);
                phone_number_etv.requestFocus();
            }*/ else if (email.isEmpty()) {
                email_etv.setError(getString(R.string.pleaseFillMandatoryDetails));
                email_etv.setFocusableInTouchMode(true);
                email_etv.requestFocus();
            } else if (!globalFunctions.isEmailValid(email)) {
                globalFunctions.displayMessaage(activity, mainView, getString(R.string.emailNotValid));
                email_etv.setFocusableInTouchMode(true);
                email_etv.requestFocus();

            }  else if (choosePassword.isEmpty()) {
                choose_password_etv.setError(getString(R.string.pleaseFillMandatoryDetails));
                choose_password_etv.setFocusableInTouchMode(true);
                choose_password_etv.requestFocus();
            } else if (confirmPassword.isEmpty()) {
                confirm_password_etv.setError(getString(R.string.pleaseFillMandatoryDetails));
                confirm_password_etv.setFocusableInTouchMode(true);
                confirm_password_etv.requestFocus();
            } /* else if (selectedGender.equalsIgnoreCase("0")) {
                GlobalFunctions.displayMessaage(activity, mainView, getString(R.string.please_select_your_gender));
            }*/ else if (choosePassword.length() < 8) {
                choose_password_etv.setError(getString(R.string.passwordContainsError));
                choose_password_etv.setFocusableInTouchMode(true);
                choose_password_etv.requestFocus();
            } else if (!choosePassword.equals(confirmPassword)) {
                globalFunctions.displayMessaage(activity, mainView, getString(R.string.passwordNotMatched));
                choose_password_etv.setFocusableInTouchMode(true);
                choose_password_etv.requestFocus();
            } else if (!terms_checkbox.isChecked()) {
                GlobalFunctions.displayMessaage(activity, mainView, getString(R.string.please_select_terms_and_conditions));
            }
            else {
                if (registerModel == null) {
                    registerModel = new RegisterModel();
                }
                registerModel.setFullName(fullName);
                registerModel.setMobileNumber(mobileNumber);
                registerModel.setEmailId(email);
                registerModel.setCountryCode(countryCode);
                registerModel.setGender(selectedGender);
                registerModel.setVehicle_number(vehicleNumber);
                registerModel.setPassword(globalFunctions.md5(choosePassword));

                registerUser(context, registerModel);

            }
        }
    }

    private void registerUser(final Context context, final RegisterModel model) {
        globalFunctions.showProgress(activity, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.registerUser(context, model, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                globalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                validateOutputAfterRegistration(arg0);
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
        }, "Register_User");
    }

    private void validateOutputAfterRegistration(Object model) {
        if (model instanceof StatusProfileModel) {
            StatusProfileModel statusProfileModel = (StatusProfileModel) model;
            SubProfileModel subProfileModel = statusProfileModel.getSubProfileModel();
            if (statusProfileModel.isStatus()) {
                GlobalFunctions.setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_TOKEN, subProfileModel.getToken());
//                showAlertMessage(subProfileModel.getMessage());
                getProfile();
            } else {
                globalFunctions.displayMessaage(activity, mainView, subProfileModel.getMessage());

            }
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
                    ProfileMainModel profileMainModel = (ProfileMainModel) arg0;
                    ProfileModel profileModel = profileMainModel.getProfileModel();
                    globalFunctions.setProfile(context, profileModel);

                    goToMainActivity();
                }
            }

            @Override
            public void OnFailureFromServer(String msg) {
                // globalFunctions.hideProgress();
                globalFunctions.displayMessaage(context, mainView, msg);
                Log.d(TAG, "Failure : " + msg);
               // goToMainActivity();
            }

            @Override
            public void OnError(String msg) {
                // globalFunctions.hideProgress();
                globalFunctions.displayMessaage(context, mainView, msg);
                Log.d(TAG, "Error : " + msg);
               // goToMainActivity();
            }
        }, "Get_Profile");
    }

    private void goToMainActivity() {
        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
        closeThisActivity();
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
