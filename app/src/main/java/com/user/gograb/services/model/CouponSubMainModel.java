package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class CouponSubMainModel implements Serializable {
    private final String TAG = "CouponSubMainModel";

    private final String
            RESPONSE            = "extra",
            STATUS              = "status",
            MESSAGE             = "message";

    CartModel
            cartModel      = null;

    String statusMessage = null;

    String message = null;
    boolean isStatus=false,isStatusLogin=false;

    public CouponSubMainModel(){}

    public CartModel getCartModel() {
        return cartModel;
    }

    public void setCartModel(CartModel cartModel) {
        this.cartModel = cartModel;
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

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public boolean isStatusLogin() {
        return isStatusLogin;
    }

    public void setStatusLogin(boolean statusLogin) {
        isStatusLogin = statusLogin;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            if(json.has(MESSAGE)){this.message = json.getString(MESSAGE);}
            if(json.has(STATUS)){this.isStatus = json.getBoolean(STATUS);}

            if(json.has(RESPONSE)){
                CartModel tmpstatusModel = new CartModel();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1 = json.getJSONObject(RESPONSE);
                if(jsonObject1 != null){tmpstatusModel.toObject(jsonObject1.toString());}
                cartModel = tmpstatusModel;
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
            jsonMain.put(STATUS, isStatus);
            jsonMain.put(MESSAGE, message);
            jsonMain.put(RESPONSE, cartModel != null ? new JSONObject(this.cartModel.toString()) : new JSONObject());

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
