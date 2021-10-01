package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchMainModel {
    private final String TAG = "SearchMainModel";

    private final String
            RESPONSE            = "response",
            STATUS              = "status",
            MESSAGE             = "message";

    SearchResponseListModel
            searchResponseListModel      = null;

    String message = null;
    boolean isStatus=false;

    public SearchMainModel(){}

    public SearchResponseListModel getSearchResponseListModel() {
        return searchResponseListModel;
    }

    public void setSearchResponseListModel(SearchResponseListModel searchResponseListModel) {
        this.searchResponseListModel = searchResponseListModel;
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

            if(json.has(RESPONSE)) {
                JSONArray array = json.getJSONArray(RESPONSE);
                SearchResponseListModel listModelLocal = new SearchResponseListModel();
                if(listModelLocal.toObject(array)){this.searchResponseListModel = listModelLocal;}
                else{this.searchResponseListModel = null;}
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
            jsonMain.put(RESPONSE, searchResponseListModel!=null?new JSONArray(searchResponseListModel.toString(true)):null);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}


