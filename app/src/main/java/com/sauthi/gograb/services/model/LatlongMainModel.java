package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class LatlongMainModel implements Serializable {
    private final String TAG = "StatusMainModel";

    private final String
            RESPONSE            = "response",
            STATUS              = "status",
            MESSAGE             = "message";

    LatlongModel
            latlongModel      = null;


    String statusMessage = null;

    String message = null;
    String status = null;
    boolean isStatus=false;

    public LatlongModel getLatlongModel() {
        return latlongModel;
    }

    public void setLatlongModel(LatlongModel latlongModel) {
        this.latlongModel = latlongModel;
    }

    public LatlongMainModel(){}


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            if(json.has(MESSAGE)){this.message = json.getString(MESSAGE);}
            if(json.has(STATUS)){this.status = json.getString(STATUS);}
           // if(json.has(STATUS)){this.isStatus = json.getBoolean(STATUS);}

            if(json.has(RESPONSE)){
                LatlongModel tmpstatusModel = new LatlongModel();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1 = json.getJSONObject(RESPONSE);
                if(jsonObject1 != null){tmpstatusModel.toObject(jsonObject1.toString());}
                latlongModel = tmpstatusModel;
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
          //  jsonMain.put(STATUS, isStatus);
            jsonMain.put(MESSAGE, message);
            jsonMain.put(RESPONSE, latlongModel != null ? new JSONObject(this.latlongModel.toString()) : new JSONObject());

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
