package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Saad on 1/8/2018.
 */

public class FcmModel implements Serializable {
    private final String TAG = "FcmModel";
    private final String

            FCM_ID          = "token",
            DEVICE_TYPE     = "device_type";


    String fcm_id           =null,
            deviceType      = "1";


    public FcmModel(){}


    public String getFcm_id() {
        return fcm_id;
    }

    public void setFcm_id(String fcm_id) {
        this.fcm_id = fcm_id;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(FCM_ID))this.fcm_id = json.getString(FCM_ID);
            if(json.has(DEVICE_TYPE))this.deviceType = json.getString(DEVICE_TYPE);

            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();

            jsonMain.put(FCM_ID, this.fcm_id);
            jsonMain.put(DEVICE_TYPE, this.deviceType);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
