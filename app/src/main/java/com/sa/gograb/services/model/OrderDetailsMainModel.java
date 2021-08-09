package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

public class OrderDetailsMainModel {
    private final String TAG = "OrderDetailsMainModel";

    private final String
            RESPONSE            = "response",
            STATUS              = "status_bool",
            MESSAGE             = "status";

    OrderMainModel
            orderMainModel      = null;

    String message = null;
    boolean isStatus=false;

    public OrderDetailsMainModel(){}

    public OrderMainModel getOrderMainModel() {
        return orderMainModel;
    }

    public void setOrderMainModel(OrderMainModel orderMainModel) {
        this.orderMainModel = orderMainModel;
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

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            if(json.has(MESSAGE)){this.message = json.getString(MESSAGE);}
            if(json.has(STATUS)){this.isStatus = json.getBoolean(STATUS);}

            if(json.has(RESPONSE)){
                OrderMainModel tmpstatusModel = new OrderMainModel();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1 = json.getJSONObject(RESPONSE);
                if(jsonObject1 != null){tmpstatusModel.toObject(jsonObject1.toString());}
                orderMainModel = tmpstatusModel;
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
            jsonMain.put(RESPONSE, orderMainModel != null ? new JSONObject(this.orderMainModel.toString()) : new JSONObject());

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}


