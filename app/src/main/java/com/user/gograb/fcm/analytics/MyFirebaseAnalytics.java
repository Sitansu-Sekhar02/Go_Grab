package com.user.gograb.fcm.analytics;


import android.annotation.SuppressLint;
import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MyFirebaseAnalytics {

    private static FirebaseAnalytics instance ;
    private static Context context;

    @SuppressLint("MissingPermission")
    private MyFirebaseAnalytics(){
        if(context!=null)instance = FirebaseAnalytics.getInstance(context);
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MyFirebaseAnalytics.context = context;
    }

    @SuppressLint("MissingPermission")
    public static FirebaseAnalytics getInstance() {
        if(instance==null && context!=null){instance = FirebaseAnalytics.getInstance(context);}
        return instance;
    }

    public static void setInstance(FirebaseAnalytics instance) {
        MyFirebaseAnalytics.instance = instance;
    }
}
