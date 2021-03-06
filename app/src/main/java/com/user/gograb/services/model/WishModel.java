package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class WishModel implements Serializable {
    private final String TAG = "WishModel";

    private final String
            ID                   = "id",
            NAME                 = "full_name",
            IMAGE                = "image",
            DISTANCE             = "distance",
            RATING               = "rating",
            RATING_COUNT         = "rating_count",
            WISHLIST             = "wishlist",
            CUSINS               = "cusine";



    String
            id                      = null,
            full_name               = null,
            image                   = null,
            distance                = null,
            rating                  = null,
            rating_count            = null,
            wishlist                = null;


    CusineListModel
            cusineListModel = null;

    public WishModel() {
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getWishlist() {
        return wishlist;
    }

    public void setWishlist(String wishlist) {
        this.wishlist = wishlist;
    }

    public CusineListModel getCusineListModel() {
        return cusineListModel;
    }

    public void setCusineListModel(CusineListModel cusineListModel) {
        this.cusineListModel = cusineListModel;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);
            id = json.getString(ID);
            if (json.has(NAME)) full_name = json.getString(NAME);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(DISTANCE)) distance = json.getString(DISTANCE);
            if (json.has(RATING)) rating = json.getString(RATING);
            if (json.has(RATING_COUNT)) rating_count = json.getString(RATING_COUNT);
            if (json.has(WISHLIST)) wishlist = json.getString(WISHLIST);

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
        } catch (Exception ex) {
            Log.d(TAG, "Json Exception : " + ex);
        }
        return false;
    }

    @Override
    public String toString() {
        String returnString = null;
        try {
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(ID, id);
            jsonMain.put(NAME, full_name);
            jsonMain.put(IMAGE, image);
            jsonMain.put(DISTANCE, distance);
            jsonMain.put(RATING, rating);
            jsonMain.put(RATING_COUNT, rating_count);
            jsonMain.put(WISHLIST, wishlist);
            jsonMain.put(CUSINS, cusineListModel != null ? new JSONArray(cusineListModel.toString(true)) : new JSONArray());

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}


