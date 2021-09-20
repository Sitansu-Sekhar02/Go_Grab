package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class SubProfileModel implements Serializable {
    private final String TAG = "SubProfileModel";
    private final String
            CUSTOMER_ID           = "customer_id",
            FULL_NAME             = "full_name",
            CITY_NAME             = "city_name",
            CITY_ID               = "city_id",
            MESSAGE               = "message",
            TOKEN                 = "token";

    String
            customerId            = null,
            fullName              = null,
            cityName              = null,
            cityId                = null,
            message                = null,
            token                 = null;

    public SubProfileModel(){}

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(CUSTOMER_ID)){customerId = json.getString(CUSTOMER_ID);}
            if(json.has(FULL_NAME)){this.fullName = json.getString(FULL_NAME);}
            if(json.has(CITY_NAME)){this.cityName = json.getString(CITY_NAME);}
            if(json.has(CITY_ID)){cityId = json.getString(CITY_ID);}
            if(json.has(TOKEN)){this.token = json.getString(TOKEN);}
            if(json.has(MESSAGE)){this.message = json.getString(MESSAGE);}

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
            jsonMain.put(CUSTOMER_ID, customerId);
            jsonMain.put(FULL_NAME, fullName);
            jsonMain.put(CITY_NAME, cityName);
            jsonMain.put(CITY_ID, cityId);
            jsonMain.put(TOKEN, token);
            jsonMain.put(MESSAGE, message);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
