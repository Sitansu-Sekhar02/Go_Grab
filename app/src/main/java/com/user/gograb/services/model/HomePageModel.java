package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class HomePageModel {
    private final String TAG = "HomePageModel";

    private final String
            ORDER_ID                            = "order_id",
            RESTAURANT_IMAGE                    = "restaurant_image",
            RESTAURANT_ID                       = "restaurant_id",
            HOME_FILTER_CATEGORY                = "filters",
            TOP_CATEGORY                        = "top_near_rest",
            SUB_CATEGORY                        = "popular_rest";



    String
            order_id            =null,
            restaurant_image    =null,
            restaurant_id        =null;

    HomeFilterCategoryListModel
            homeFilterCategoryListModel   = null;
    HomeTopCategoryListModel
            top_near_rest          = null;
    HomeSubCategoryListModel
            popular_rest       =null;



    public HomePageModel(){}


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getRestaurant_image() {
        return restaurant_image;
    }

    public void setRestaurant_image(String restaurant_image) {
        this.restaurant_image = restaurant_image;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public HomeTopCategoryListModel getTop_near_rest() {
        return top_near_rest;
    }

    public void setTop_near_rest(HomeTopCategoryListModel top_near_rest) {
        this.top_near_rest = top_near_rest;
    }

    public HomeSubCategoryListModel getPopular_rest() {
        return popular_rest;
    }

    public void setPopular_rest(HomeSubCategoryListModel popular_rest) {
        this.popular_rest = popular_rest;
    }

    public HomeFilterCategoryListModel getHomeFilterCategoryListModel() {
        return homeFilterCategoryListModel;
    }

    public void setHomeFilterCategoryListModel(HomeFilterCategoryListModel homeFilterCategoryListModel) {
        this.homeFilterCategoryListModel = homeFilterCategoryListModel;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            if(json.has(ORDER_ID)){this.order_id = json.getString(ORDER_ID);}
            if(json.has(RESTAURANT_IMAGE)){this.restaurant_image = json.getString(RESTAURANT_IMAGE);}
            if(json.has(RESTAURANT_ID)){this.restaurant_id = json.getString(RESTAURANT_ID);}

            if(json.has(HOME_FILTER_CATEGORY)) {
                JSONArray array = json.getJSONArray(HOME_FILTER_CATEGORY);
                HomeFilterCategoryListModel listModelLocal = new HomeFilterCategoryListModel();
                listModelLocal.setRESPONSE(HOME_FILTER_CATEGORY);
                if(listModelLocal.toObject(array)){this.homeFilterCategoryListModel = listModelLocal;}
                else{this.homeFilterCategoryListModel = null;}
            }
            if(json.has(TOP_CATEGORY)) {
                JSONArray array = json.getJSONArray(TOP_CATEGORY);
                HomeTopCategoryListModel listModelLocal = new HomeTopCategoryListModel();
                if(listModelLocal.toObject(array)){this.top_near_rest = listModelLocal;}
                else{this.top_near_rest = null;}
            }
            if(json.has(SUB_CATEGORY)) {
                JSONArray array = json.getJSONArray(SUB_CATEGORY);
                HomeSubCategoryListModel listModelLocal = new HomeSubCategoryListModel();
                listModelLocal.setRESPONSE(SUB_CATEGORY);
                if(listModelLocal.toObject(array)){this.popular_rest = listModelLocal;}
                else{this.popular_rest = null;}
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
            jsonMain.put(ORDER_ID, order_id);
            jsonMain.put(RESTAURANT_IMAGE, restaurant_image);
            jsonMain.put(RESTAURANT_ID, restaurant_id);

            jsonMain.put(HOME_FILTER_CATEGORY, homeFilterCategoryListModel!=null?new JSONArray(homeFilterCategoryListModel.toString(true)):null);
            jsonMain.put(TOP_CATEGORY, top_near_rest!=null?new JSONArray(top_near_rest.toString(true)):null);
            jsonMain.put(SUB_CATEGORY, popular_rest!=null?new JSONArray(popular_rest.toString(true)):null);
            //jsonMain.put(PRICE, priceRangeModel!=null ? new JSONObject(priceRangeModel.toString()) : new JSONObject());

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);
        }
        return returnString;
    }

}


