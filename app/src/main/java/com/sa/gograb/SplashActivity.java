package com.sa.gograb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.analytics.Tracker;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.global.GlobalVariables;
import com.sa.gograb.login.LoginActivity;
import com.sa.gograb.services.model.NotificationModel;


import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    public static String TAG = "SplashScreen";
    public static final String BUNDLE_MAIN_NOTIFICATION_MODEL = "BundleMainModelNotificationModel";
    Context context;
    Activity activity;
    private static int SPLASH_TIME_OUT = 2000;
    int count = 0;

    GlobalVariables globalVariables;
    GlobalFunctions globalFunctions;

    private Tracker tracker;


    private NotificationModel notificationModel = null;

    SharedPreferences shared_preference;

    public static Intent newInstance(Context mainContext, NotificationModel notificationModel) {
        Intent intent = new Intent(mainContext, SplashActivity.class);
        intent.putExtra(BUNDLE_MAIN_NOTIFICATION_MODEL, notificationModel);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        context = this;
        activity = this;

        globalFunctions = AppController.getInstance().getGlobalFunctions();
        globalVariables = AppController.getInstance().getGlobalVariables();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.ColorStatusBar));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
        }


        shared_preference = PreferenceManager.getDefaultSharedPreferences(this
                .getApplicationContext());
        String mCustomerLanguage = shared_preference.getString(
                globalVariables.SHARED_PREFERENCE_SELECTED_LANGUAGE, "null");

        if (mCustomerLanguage.equalsIgnoreCase("null")) {

            SharedPreferences.Editor editor = shared_preference.edit();
            editor.putString(globalVariables.SHARED_PREFERENCE_SELECTED_LANGUAGE, "en");
            editor.commit();
        }

        String mCurrentLocale = shared_preference.getString(globalVariables.SHARED_PREFERENCE_SELECTED_LANGUAGE,
                "null");
        Locale locale;
        if ((mCurrentLocale.equalsIgnoreCase("ar"))) {
            locale = new Locale("ar");
        } else {
            locale = new Locale("en");
        }
        //AppController.getInstance().myAppLanguage = mCurrentLocale;
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        if (getIntent().hasExtra(BUNDLE_MAIN_NOTIFICATION_MODEL)) {
            notificationModel = (NotificationModel) getIntent().getSerializableExtra(BUNDLE_MAIN_NOTIFICATION_MODEL);
        } else {
            notificationModel = null;
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMainActivity();

               /* Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
                finish();*/
            }
        }, 6000);
    }
    private void goToMainActivity() {
        Intent intent = null;
        if (globalFunctions.isLoggedIn(context)) {
            if (notificationModel != null) {
                //clicked notification....
                intent = MainActivity.newInstance(SplashActivity.this, notificationModel);
            } else {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            }
            startActivity(intent);
            activity.finish();
        } else {
            if (notificationModel != null) {
                //clicked notification....
                intent = MainActivity.newInstance(SplashActivity.this, notificationModel);
            } else {
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            activity.finish();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
