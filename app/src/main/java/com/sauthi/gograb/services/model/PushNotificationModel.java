package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class PushNotificationModel implements Serializable {

    private final String TAG = "PushNotificationModel";
    private final String REGISTRATION_ID = "token";
    private final String DEVICE_TYPE = "device_type";

    String registration_id=null,deviceType=null;

    public PushNotificationModel(){}

    public String getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(String registration_id) {
        this.registration_id = registration_id;
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
            this.registration_id = json.getString(REGISTRATION_ID);
            this.deviceType = json.getString(DEVICE_TYPE);
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
            jsonMain.put(REGISTRATION_ID, this.registration_id);
            jsonMain.put(DEVICE_TYPE, this.deviceType);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

