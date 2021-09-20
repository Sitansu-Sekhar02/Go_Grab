package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

public class FilterMainModel {
    private final String TAG = "FilterMainModel";

    private final String
            MESSAGE                    = "status",
            STATUS                     = "status_bool",
            MAIN_STATUS                = "status",
            RESPONSE                   = "response";

    private String
            message                    = null;
    private boolean
            isStatus                  = false;

    FilterModel
            filterModel            = null;

    public FilterMainModel(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public FilterModel getFilterModel() {
        return filterModel;
    }

    public void setFilterModel(FilterModel filterModel) {
        this.filterModel = filterModel;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            if(json.has(MESSAGE)){this.message = json.getString(MESSAGE);}
            if(json.has(STATUS)){this.isStatus = json.getBoolean(STATUS);}
            if(json.has(MAIN_STATUS)){this.isStatus = json.getBoolean(MAIN_STATUS);}

            if(json.has(RESPONSE)){
                FilterModel statusModel = new FilterModel();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1 = json.getJSONObject(RESPONSE);
                if(jsonObject1 != null){statusModel.toObject(jsonObject1.toString());}
                filterModel = statusModel;
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
            jsonMain.put(MAIN_STATUS, isStatus);

            jsonMain.put(RESPONSE, filterModel != null ? new JSONObject(this.filterModel.toString()) : new JSONObject());

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}


