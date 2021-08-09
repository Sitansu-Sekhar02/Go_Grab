package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class OrderStatus implements Serializable {

    private final String TAG = "OrderStatus";

    private final String
            ID              = "id",
            STATUS_TITLE    = "status_title",
            CREATED_ON      = "created_on";

    private String
            id             = null,
            created_on     = null,
            status_title   = null;


    public OrderStatus(){}

    List<OrderStatus> statusList = new ArrayList<OrderStatus>();

    public List<OrderStatus> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<OrderStatus> statusList) {
        this.statusList = statusList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getStatus_title() {
        return status_title;
    }

    public void setStatus_title(String status_title) {
        this.status_title = status_title;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }


    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(CREATED_ON))this.created_on = json.getString(CREATED_ON);
            if(json.has(STATUS_TITLE))this.status_title = json.getString(STATUS_TITLE);
            if(json.has(ID))this.id = json.getString(ID);


            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);
            return false;}

    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(ID, this.id);
            jsonMain.put(STATUS_TITLE, status_title);
            jsonMain.put(CREATED_ON, created_on);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
