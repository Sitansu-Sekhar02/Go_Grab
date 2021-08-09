package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatusListModel implements Serializable {

    private final String TAG = "StatusListModel";

    private final String RESPONSE = "order_status_history";


    public StatusListModel(){}



    public boolean toObject(String jsonObjectString){

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
