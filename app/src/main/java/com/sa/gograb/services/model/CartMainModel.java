package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class CartMainModel implements Serializable {

    private final String TAG = "CartMainModel";

    private final String
            STATUS                     = "status",
            RESPONSE                   = "response";

    private String
            status                    = null;

  CartModel
            cartModel   = null;

    public CartMainModel(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CartModel getCartModel() {
        return cartModel;
    }

    public void setCartModel(CartModel cartModel) {
        this.cartModel = cartModel;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);

            if(json.has(STATUS)){this.status = json.getString(STATUS);}


            if(json.has(RESPONSE)){
                CartModel statusModel = new CartModel();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1 = json.getJSONObject(RESPONSE);
                if(jsonObject1 != null){statusModel.toObject(jsonObject1.toString());}
                cartModel = statusModel;
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
            jsonMain.put(STATUS, status);
            jsonMain.put(RESPONSE, cartModel != null ? new JSONObject(this.cartModel.toString()) : new JSONObject());
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
