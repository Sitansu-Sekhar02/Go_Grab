package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class FilterDistanceModel implements Serializable {
    private final String TAG = "FilterDistanceModel";
    private final String
            ID                   = "id",
            NAME                 = "name",
            PREPARE_TIME         = "preparation_time",
            SELECTED             = "selected",
            DISTANCE             = "distance";

    private String
            id               = null,
            name             = null,
            preTime          = null,
            selected         = null,
            distance         = null;



    public FilterDistanceModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreTime() {
        return preTime;
    }

    public void setPreTime(String preTime) {
        this.preTime = preTime;
    }

    public boolean toObject(String jsonObject){
        try{

            JSONObject json = new JSONObject(jsonObject);
            if (json.has(ID)) id = json.getString(ID);
            if (json.has(NAME)) name = json.getString(NAME);
            if (json.has(PREPARE_TIME)) preTime = json.getString(PREPARE_TIME);
            if (json.has(SELECTED)) selected = json.getString(SELECTED);
            if (json.has(DISTANCE)) distance = json.getString(DISTANCE);

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
            jsonMain.put(ID, id);
            jsonMain.put(DISTANCE, distance);
            jsonMain.put(NAME, name);
            jsonMain.put(PREPARE_TIME, preTime);
            jsonMain.put(SELECTED, selected);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}


