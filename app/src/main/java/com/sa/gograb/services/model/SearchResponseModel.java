package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class SearchResponseModel implements Serializable{
    private final String TAG = "SearchResponseModel";
    private final String
            ID                    = "id",
            NAME                  = "full_name",
            VARIATION             = "id_variation",
            EXTRA                 = "type",
            LATITUDE              = "latitude",
            LONGITUDE             = "longitude";

    String
            id                 = null,
            name               = null,
            variation          = null,
            extra              = null,
            latitude           = null,
            longitude         = null;

    public SearchResponseModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(ID))id = json.getString(ID);
            if(json.has(NAME))name = json.getString(NAME);
            if(json.has(EXTRA))extra = json.getString(EXTRA);
            if(json.has(VARIATION))variation = json.getString(VARIATION);
            if(json.has(LONGITUDE))longitude = json.getString(LONGITUDE);
            if(json.has(LATITUDE))latitude = json.getString(LATITUDE);

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
            jsonMain.put(ID, this.getId());
            jsonMain.put(NAME, this.getName());
            jsonMain.put(EXTRA, this.getExtra());
            jsonMain.put(VARIATION, this.getVariation());
            jsonMain.put(LONGITUDE, this.getLongitude());
            jsonMain.put(LATITUDE, this.getLatitude());

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
