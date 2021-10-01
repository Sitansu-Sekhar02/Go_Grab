package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class WishListSubMainModel implements Serializable {
    private final String TAG = "WishListSubMainModel";

    private final String
            AMOUNT                     = "balance",
            RESPONSE                   = "response";

    private String
            amount                   = null;

    WishListModel
            wishListModel             = null;

    public WishListSubMainModel(){}

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public WishListModel getWishListModel() {
        return wishListModel;
    }

    public void setWishListModel(WishListModel wishListModel) {
        this.wishListModel = wishListModel;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);

            if(json.has(AMOUNT)){this.amount = json.getString(AMOUNT);}

            if(json.has(RESPONSE)) {
                JSONArray array = json.getJSONArray(RESPONSE);
                WishListModel listModelLocal = new WishListModel();
                if(listModelLocal.toObject(array)){this.wishListModel = listModelLocal;}
                else{this.wishListModel = null;}
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
            jsonMain.put(AMOUNT, amount);
            jsonMain.put(RESPONSE, wishListModel!=null?new JSONArray(wishListModel.toString(true)):null);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}


