package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class MenuModel implements Serializable {

    private final String TAG = "MenuModel";

    private final String
            ID                    = "id",
            NAME                  = "full_name",
            RATING                = "rating",
            RATING_COUNT          = "rating_count",
            WISHLIST              = "wishlist",
            IMAGE                 = "image",
            PREPARE_TIME          = "preparation_time",
            DISTANCE              = "distance",
            TRENDING_MENU         = "trending_menu",
            CATEGORY_MENU         = "category_menu";


    String
            id                   = null,
            name                 = null,
            rating               = null,
            rating_count         = null,
            wishlist             = null,
            distance             = null,
            preparation_time     = null,
            image                = null,
            trending_menu        = null,
            category_menu        = null;

    TrendingMenuListModel
            trendingMenuListModel=null;
    CategoryMenuListModel
             categoryMenuListModel=null;

    public MenuModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(String preparation_time) {
        this.preparation_time = preparation_time;
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

    public TrendingMenuListModel getTrendingMenuListModel() {
        return trendingMenuListModel;
    }

    public void setTrendingMenuListModel(TrendingMenuListModel trendingMenuListModel) {
        this.trendingMenuListModel = trendingMenuListModel;
    }

    public CategoryMenuListModel getCategoryMenuListModel() {
        return categoryMenuListModel;
    }

    public void setCategoryMenuListModel(CategoryMenuListModel categoryMenuListModel) {
        this.categoryMenuListModel = categoryMenuListModel;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);
            if (json.has(ID)) id = json.getString(ID);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(NAME)) name = json.getString(NAME);
            if (json.has(RATING)) rating = json.getString(RATING);
            if (json.has(RATING_COUNT)) rating_count = json.getString(RATING_COUNT);
            if (json.has(PREPARE_TIME)) preparation_time = json.getString(PREPARE_TIME);
            if (json.has(WISHLIST)) wishlist = json.getString(WISHLIST);
            if (json.has(DISTANCE)) distance = json.getString(DISTANCE);


            if(json.has(TRENDING_MENU)) {
                JSONArray array = json.getJSONArray(TRENDING_MENU);
                TrendingMenuListModel listModelLocal = new TrendingMenuListModel();
                if(listModelLocal.toObject(array)){this.trendingMenuListModel = listModelLocal;}
                else{this.trendingMenuListModel = null;}
            }
            if(json.has(CATEGORY_MENU)) {
                JSONArray array = json.getJSONArray(CATEGORY_MENU);
                CategoryMenuListModel reviewListModel = new CategoryMenuListModel();
                if(reviewListModel.toObject(array)){this.categoryMenuListModel = reviewListModel;}
                else{this.categoryMenuListModel = null;}
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
            jsonMain.put(IMAGE, image);
            jsonMain.put(NAME, name);
            jsonMain.put(RATING, rating);
            jsonMain.put(RATING_COUNT, rating_count);
            jsonMain.put(PREPARE_TIME, preparation_time);
            jsonMain.put(WISHLIST, wishlist);
            jsonMain.put(DISTANCE, distance);
            jsonMain.put(TRENDING_MENU, trendingMenuListModel!=null?new JSONArray(trendingMenuListModel.toString(true)):null);
            jsonMain.put(CATEGORY_MENU, categoryMenuListModel!=null?new JSONArray(categoryMenuListModel.toString(true)):null);


            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
