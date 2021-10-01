package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class HomeTopCategoryModel implements Serializable {
    private final String TAG = "HomeTopCategoryModel";
    private final String
            ID          = "id",
            FULL_NAME   = "full_name",
            DISTANCE    = "distance",
            RATING      = "rating",
            RATING_COUNT = "rating_count",
            IMAGE        = "image",
            CUSINS       = "cusine",
            WISHLIST     = "wishlist",
            LATITUDE     = "latitude",
            LONGITUDE     = "longitude";

    private String
            id          = null,
            full_name   = null,
            distance    = null,
            rating      = null,
            rating_count = null,
            image        = null,
            wishlist     = null,
             latitude     = null,
            longitude     = null;


    CusineListModel
            cusineListModel = null;

    public HomeTopCategoryModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating_count() {
        return rating_count;
    }

    public void setRating_count(String rating_count) {
        this.rating_count = rating_count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWishlist() {
        return wishlist;
    }

    public void setWishlist(String wishlist) {
        this.wishlist = wishlist;
    }

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

    public CusineListModel getCusineListModel() {
        return cusineListModel;
    }

    public void setCusineListModel(CusineListModel cusineListModel) {
        this.cusineListModel = cusineListModel;
    }

    public boolean toObject(String jsonObject){
        try{

            JSONObject json = new JSONObject(jsonObject);
            if (json.has(ID)) id = json.getString(ID);
            if (json.has(FULL_NAME)) full_name = json.getString(FULL_NAME);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(DISTANCE)) distance = json.getString(DISTANCE);
            if (json.has(RATING)) rating = json.getString(RATING);
            if (json.has(RATING_COUNT)) rating_count = json.getString(RATING_COUNT);
            if (json.has(WISHLIST)) wishlist = json.getString(WISHLIST);
            if (json.has(LATITUDE)) latitude = json.getString(LATITUDE);
            if (json.has(LONGITUDE)) longitude = json.getString(LONGITUDE);

            if (json.has(CUSINS)) {
                JSONArray array = json.getJSONArray(CUSINS);
                CusineListModel listModelLocal = new CusineListModel();
                if (listModelLocal.toObject(array)) {
                    this.cusineListModel = listModelLocal;
                } else {
                    this.cusineListModel = null;
                }
            }


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
            jsonMain.put(ID, id);
            jsonMain.put(FULL_NAME, full_name);
            jsonMain.put(IMAGE, image);
            jsonMain.put(DISTANCE, distance);
            jsonMain.put(RATING, rating);
            jsonMain.put(RATING_COUNT, rating_count);
            jsonMain.put(WISHLIST, wishlist);
            jsonMain.put(LATITUDE, latitude);
            jsonMain.put(LONGITUDE, longitude);
            jsonMain.put(CUSINS, cusineListModel != null ? new JSONArray(cusineListModel.toString(true)) : new JSONArray());

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}


