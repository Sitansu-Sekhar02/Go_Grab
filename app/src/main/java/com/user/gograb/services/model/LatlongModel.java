package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class LatlongModel implements Serializable {
    private final String TAG = "LatlongModel";

    private final String
            LATITUDE = "latitude",
            LONGITUDE = "longitude";


    private String
            latitude                  = null,
            longitude                 = null;



    public LatlongModel(){
    }

    public String getLatitude() {
        return latitude;
    }

    public String setLatitude(String latitude) {
        this.latitude = latitude;
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String setLongitude(String longitude) {
        this.longitude = longitude;
        return longitude;
    }



    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(LATITUDE))latitude = json.getString(LATITUDE);
            if(json.has(LONGITUDE))longitude = json.getString(LONGITUDE);


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
            jsonMain.put(LATITUDE, latitude);
            jsonMain.put(LONGITUDE, longitude);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
