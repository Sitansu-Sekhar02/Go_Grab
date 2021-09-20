package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class PostCommentsListModel implements Serializable {

    private final String TAG = "PostCommentsListModel";

    private final String RESPONSE = "response";


    public PostCommentsListModel(){}



    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);

            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){

        return false;
    }

    @Override
    public String toString(){
        String returnString = null;

        return returnString;
    }

    public String toString(boolean isArray){
        String returnString = null;

        return returnString;
    }
}
