package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class CartMainModel implements Serializable {

    private final String TAG = "CartMainModel";

    private final String
            STATUS                     = "status",
            MESSAGE                    = "message",
            RESPONSE                   = "response";

    private String
            status                    = null;
    String message = null;


    CartSubMainModel
            cartModel   = null;

    public CartMainModel(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CartSubMainModel getCartModel() {
        return cartModel;
    }

    public void setCartModel(CartSubMainModel cartModel) {
        this.cartModel = cartModel;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);

            if(json.has(STATUS)){this.status = json.getString(STATUS);}
            if(json.has(MESSAGE)){this.message = json.getString(MESSAGE);}


            if(json.has(RESPONSE)){
                CartSubMainModel statusModel = new CartSubMainModel();
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
            jsonMain.put(MESSAGE, message);
            jsonMain.put(RESPONSE, cartModel != null ? new JSONObject(this.cartModel.toString()) : new JSONObject());
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
