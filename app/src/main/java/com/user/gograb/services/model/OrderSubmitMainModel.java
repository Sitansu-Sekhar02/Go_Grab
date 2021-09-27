package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class OrderSubmitMainModel implements Serializable {
    private final String TAG = "OrderSubmitMainModel";

    private final String
            RESPONSE            = "response",
            STATUS              = "status",
            STATUSLOGIN         = "status",
            MESSAGE             = "message";

    OrderSubmitSubModel
            orderSubmitSubModel      = null;

    String statusMessage = null;

    String message = null;
    boolean isStatus=false,isStatusLogin=false;

    public OrderSubmitMainModel(){}


    public OrderSubmitSubModel getOrderSubmitSubModel() {
        return orderSubmitSubModel;
    }

    public void setOrderSubmitSubModel(OrderSubmitSubModel orderSubmitSubModel) {
        this.orderSubmitSubModel = orderSubmitSubModel;
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
            if(json.has(STATUSLOGIN)){this.isStatusLogin = json.getBoolean(STATUSLOGIN);}

            if(json.has(RESPONSE)){
                OrderSubmitSubModel tmpstatusModel = new OrderSubmitSubModel();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1 = json.getJSONObject(RESPONSE);
                if(jsonObject1 != null){tmpstatusModel.toObject(jsonObject1.toString());}
                orderSubmitSubModel = tmpstatusModel;
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
            jsonMain.put(STATUSLOGIN, isStatusLogin);
            jsonMain.put(MESSAGE, message);
            jsonMain.put(RESPONSE, orderSubmitSubModel != null ? new JSONObject(this.orderSubmitSubModel.toString()) : new JSONObject());

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
