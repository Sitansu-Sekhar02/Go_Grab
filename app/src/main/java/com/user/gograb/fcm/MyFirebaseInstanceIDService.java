package com.user.gograb.fcm;


import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.user.gograb.global.GlobalFunctions;
import com.user.gograb.services.model.PushNotificationModel;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseInstIDService";
    View mainView;
    Context context;


    //this method will be called
    //when the token is generated
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        context=getApplicationContext();

        //now we will have the token
        String token = FirebaseInstanceId.getInstance().getToken();
        PushNotificationModel pushNotificationModel = new PushNotificationModel();
        pushNotificationModel.setRegistration_id(token);

        if(GlobalFunctions.isLoggedIn(MyFirebaseInstanceIDService.this)) {
//            notificationListViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
//            notificationListViewModel.sendPushNotificationID(context, pushNotificationModel);
//            observePushViewModel();
        }
        //  SendFcmTokenToserver(token);
        //for now we are displaying the token in the log
        //copy it as this method is called only when the new token is generated
        //and usually new token is only generated when the app is reinstalled or the data is cleared
        Log.d("MyRefreshedToken", token);
    }

    /*private void observePushViewModel() {
        try {
//            notificationListViewModel.pushLiveData.removeObservers(this);
            notificationListViewModel.pushLiveData.observe(this, jsonobject -> {
                if (jsonobject != null) {
                    Log.d(TAG, "Response : " + jsonobject.toString());

                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

}