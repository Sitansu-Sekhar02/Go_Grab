package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Sivasabharish on 9/27/2017.
 */

public class HomeIndexModel implements Serializable {
    private final String TAG = "HomeIndexModel";
    private final String
            UID            = "uuid",
            DEVICE_TYPE    = "device_type",
            SYSTEM_INFO    = "system_info";

    String
            uid = null,
            deviceType = "1",
            system_info = null;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSystem_info() {
        return system_info;
    }

    public void setSystem_info(String system_info) {
        this.system_info = system_info;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            uid = json.getString(UID);
            system_info = json.getString(SYSTEM_INFO);
            if(json.has(DEVICE_TYPE))deviceType = json.getString(DEVICE_TYPE);
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);
            return false;}

    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(UID, uid);
            jsonMain.put(DEVICE_TYPE, deviceType);
            jsonMain.put(SYSTEM_INFO, system_info);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
