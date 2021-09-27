package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class CartPostModel implements Serializable {
    private final String TAG = "CartPostModel";

    private final String
            REST_ID                = "restaurant_id",
            MENU_ID                = "menu_id",
            QUANTITY               = "quantity";

    String
            restaurant_id           = null,
            menu_id                 = null,
            quantity                = null;

    public CartPostModel(){}

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(REST_ID)){this.restaurant_id = json.getString(REST_ID);}
            if(json.has(MENU_ID)){this.menu_id = json.getString(MENU_ID);}
            if(json.has(QUANTITY)){this.quantity = json.getString(QUANTITY);}

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
            jsonMain.put(REST_ID, this.restaurant_id);
            jsonMain.put(MENU_ID, this.menu_id);
            jsonMain.put(QUANTITY, this.quantity);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
