package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class OrderPostModel implements Serializable {

    private final String TAG = "OrderPostModel";
    private final String
            INDEX           = "index",
            SIZE            = "size",
            ADDED_TYPE      = "added_type",
            WALLET_TYPE     = "wallet_type",
            STATUS          = "status";

    String
            index        = null,
            size         = null,
            status       = null;

    public OrderPostModel(){}

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if(json.has(INDEX)){this.index = json.getString(INDEX);}
            if(json.has(SIZE)){this.size = json.getString(SIZE);}
            if(json.has(STATUS)){this.status = json.getString(STATUS);}

            return true;
        }catch(Exception ex){Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(INDEX, index);
            jsonMain.put(SIZE, size);
            jsonMain.put(STATUS, status);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

