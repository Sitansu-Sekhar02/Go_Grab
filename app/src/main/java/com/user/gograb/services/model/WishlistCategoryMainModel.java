package com.user.gograb.services.model;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class WishlistCategoryMainModel implements Serializable {
    private final String TAG = "WishlistCategoryMainModel";
    private final String
            RESPONSE            = "response",
            STATUS              = "status",
            MESSAGE             = "message";

    WishlistCategoryListModel
            wishlistCategoryListModel      = null;



    String message = null;
    boolean isStatus=false;

    public WishlistCategoryMainModel(){}


    public WishlistCategoryListModel getWishlistCategoryListModel() {
        return wishlistCategoryListModel;
    }

    public void setWishlistCategoryListModel(WishlistCategoryListModel wishlistCategoryListModel) {
        this.wishlistCategoryListModel = wishlistCategoryListModel;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SuppressLint("LongLogTag")
    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            if(json.has(MESSAGE)){this.message = json.getString(MESSAGE);}
            if(json.has(STATUS)){this.isStatus = json.getBoolean(STATUS);}

            if(json.has(RESPONSE)){
                WishlistCategoryListModel wishlistCategory = new WishlistCategoryListModel();
                JSONArray jsonArray = new JSONArray();
                jsonArray = json.getJSONArray(RESPONSE);
                if(jsonArray!=null){wishlistCategory.toObject(jsonArray);}
                this.wishlistCategoryListModel = wishlistCategory;
            }

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
            jsonMain.put(STATUS, isStatus);
            jsonMain.put(MESSAGE, message);
            jsonMain.put(RESPONSE, this.wishlistCategoryListModel != null? new JSONArray(this.wishlistCategoryListModel.toString(true)) : new JSONArray());

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
