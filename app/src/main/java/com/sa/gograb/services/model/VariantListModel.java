package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VariantListModel implements Serializable {

    private final String TAG = "VariantListModel";

    String RESPONSE = "variation1";

    public String getRESPONSE() {
        return RESPONSE;
    }

    public void setRESPONSE(String RESPONSE) {
        this.RESPONSE = RESPONSE;
    }


    public VariantListModel(){}



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
