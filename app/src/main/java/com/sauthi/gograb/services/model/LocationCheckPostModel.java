package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class LocationCheckPostModel implements Serializable {
    private final String TAG = "LocationCheckPostModel";

    private final String
            LATITUDE                  = "lat",
            LONGITUDE                 = "long";

    String
            latitude                = null,
            longitude               = null;

    public LocationCheckPostModel(){}

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(LATITUDE)){this.latitude = json.getString(LATITUDE);}
            if(json.has(LONGITUDE)){this.longitude = json.getString(LONGITUDE);}

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
            jsonMain.put(LATITUDE, this.latitude);
            jsonMain.put(LONGITUDE, this.longitude);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
