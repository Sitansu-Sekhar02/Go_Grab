package com.sauthi.gograb.services.model;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WishlistCategoryListModel implements Serializable {

    private final String TAG = "WishlistCategoryListModel";

    private final String RESPONSE = "response";

    List<WishlistCategoryModel> wishlistCategoryModels = new ArrayList<WishlistCategoryModel>();

    public WishlistCategoryListModel(){}

    public List<WishlistCategoryModel> getWishlistCategoryModels() {
        return wishlistCategoryModels;
    }

    public void setWishlistCategoryModels(List<WishlistCategoryModel> wishlistCategoryModels) {
        this.wishlistCategoryModels = wishlistCategoryModels;
    }

    @SuppressLint("LongLogTag")
    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            return this.toObject(array);
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @SuppressLint("LongLogTag")
    public boolean toObject(JSONArray jsonArray){
        try{
            List<WishlistCategoryModel> list = new ArrayList<WishlistCategoryModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                WishlistCategoryModel model = new WishlistCategoryModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.wishlistCategoryModels = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @SuppressLint("LongLogTag")
    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            List<WishlistCategoryModel> list = this.wishlistCategoryModels;
            for(int i=0;i<list.size();i++){
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            jsonMain.put(RESPONSE, jsonArray);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

    @SuppressLint("LongLogTag")
    public String toString(boolean isArray){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            List<WishlistCategoryModel> list = this.wishlistCategoryModels;
            for(int i=0;i<list.size();i++){
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            if(!isArray){
                jsonMain.put(RESPONSE, jsonArray);
                returnString = jsonMain.toString();
            }else{
                returnString = jsonArray.toString();
            }

        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
