package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class IndexModel implements Serializable {
    private final String TAG = "IndexModel";
    private final String
            CITIES = "cities",
            BANNER = "banners",
            TIMESTAMP = "timestamp",
            CART_COUNT = "cart_count",
            CATEGORIES = "categories";

    KeyValueListModel banners, cities, categories;
    int cart_count=0;
    long timestamp;

    public IndexModel(){}

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public KeyValueListModel getBanners() {
        return banners;
    }

    public void setBanners(KeyValueListModel banners) {
        this.banners = banners;
    }

    public KeyValueListModel getCities() {
        return cities;
    }

    public void setCities(KeyValueListModel cities) {
        this.cities = cities;
    }

    public KeyValueListModel getCategories() {
        return categories;
    }

    public void setCategories(KeyValueListModel categories) {
        this.categories = categories;
    }

    public int getCart_count() {
        return cart_count;
    }

    public void setCart_count(int cart_count) {
        this.cart_count = cart_count;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if(json.has(TIMESTAMP)){
                timestamp = json.getLong(TIMESTAMP);
            }else{
                timestamp=0;
            }

            if(json.has(CART_COUNT)){
                cart_count = json.getInt(CART_COUNT);
            }else{
                cart_count=0;
            }
            /*get Banners*/
            KeyValueListModel bannerModel = new KeyValueListModel();
            JSONArray bannerJson = new JSONArray();
            if(json.has(BANNER)){bannerJson = json.getJSONArray(BANNER);}else{bannerJson=null;}
            if(bannerJson!=null){bannerModel.toObject(bannerJson);}
            banners = bannerModel;

            /*get Cities*/
            /*KeyValueListModel cityKeyValueModel = new KeyValueListModel();
            JSONArray cityJson = new JSONArray();
            if(json.has(CITIES)){cityJson = json.getJSONArray(CITIES);}else{cityJson=null;}
            if(cityJson!=null){cityKeyValueModel.toObject(cityJson);}
            cities = cityKeyValueModel;*/

            /*get Categories*/
            KeyValueListModel categoryModel = new KeyValueListModel();
            JSONArray categoryJson = new JSONArray();
            if(json.has(CATEGORIES)){categoryJson = json.getJSONArray(CATEGORIES);}else{categoryJson=null;}
            if(categoryJson!=null){categoryModel.toObject(categoryJson);}
            categories = categoryModel;

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
            jsonMain.put(TIMESTAMP, timestamp);
            jsonMain.put(CART_COUNT, cart_count);
            jsonMain.put(BANNER, new JSONObject(banners.toString()));
            /*jsonMain.put(CITIES, new JSONObject(cities.toString()));*/
            jsonMain.put(CATEGORIES, new JSONObject(categories.toString()));
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
