package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class LocationModel implements Serializable {
    private final String TAG = "LocationModel";

    private final String
            NAME                 = "name",
            ADDRESS               = "address",
            IMAGE                 = "image",
            MEMBERSHIP_ID         = "membership_id",
            IS_PREMIUM             = "is_premium",
            PRIORITY               = "priority",
            LATITUDE              = "latitude",
            LONGITUDE               = "longitude";

    String
            address     = null,
            priority      = null,
            is_premium     = null,
            name           = null,
            image         = null,
            membership_id   = null;


    double
            latitude    = 0.0,
            longitude   = 0.0;

    public LocationModel(){}


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(String membership_id) {
        this.membership_id = membership_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getIs_premium() {
        return is_premium;
    }

    public void setIs_premium(String is_premium) {
        this.is_premium = is_premium;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            name = json.getString(NAME);
            address = json.getString(ADDRESS);
            image = json.getString(IMAGE);
            membership_id = json.getString(MEMBERSHIP_ID);
            priority = json.getString(PRIORITY);
            is_premium = json.getString(IS_PREMIUM);

            try{latitude = json.getDouble(LATITUDE);}
            catch(Exception exx){latitude = 0.0;}

            try{longitude = json.getDouble(LONGITUDE);}
            catch (Exception exx){longitude = 0.0;}

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
            jsonMain.put(NAME, name);
            jsonMain.put(ADDRESS, address);
            jsonMain.put(IMAGE, image);
            jsonMain.put(MEMBERSHIP_ID, membership_id);
            jsonMain.put(LATITUDE, latitude);
            jsonMain.put(LONGITUDE, longitude);
            jsonMain.put(PRIORITY, priority);
            jsonMain.put(IS_PREMIUM, is_premium);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
