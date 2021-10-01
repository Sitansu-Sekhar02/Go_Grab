package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocationListModel implements Serializable {

    private final String TAG = "LocationListModel";

    private  String RESPONSE = "location";

    List<LocationModel> locationModels = new ArrayList<LocationModel>();

    public LocationListModel(){}

    public String getRESPONSE() {
        return RESPONSE;
    }

    public void setRESPONSE(String RESPONSE) {
        this.RESPONSE = RESPONSE;
    }

    public List<LocationModel> getLocationModels() {
        return locationModels;
    }

    public void setLocationModels(List<LocationModel> locationModels) {
        this.locationModels = locationModels;
    }

    public List<String> getNames(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.locationModels.size(); i++){
           // list.add(categoryList.get(i).getTitle());
        }
        return list;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<LocationModel> list = new ArrayList<LocationModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                LocationModel keyValueModel = new LocationModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.locationModels = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<LocationModel> list = new ArrayList<LocationModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                LocationModel keyValueModel = new LocationModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.locationModels = list;
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
            JSONArray jsonArray = new JSONArray();
            List<LocationModel> list = this.locationModels;
            for(int i=0;i<list.size();i++){
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            jsonMain.put(RESPONSE, jsonArray);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

    public String toString(boolean isArray){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            List<LocationModel> list = this.locationModels;
            for(int i=0;i<list.size();i++){
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            if(!isArray){
                jsonMain.put(RESPONSE, jsonArray);
                returnString = jsonMain.toString();
            }else{
                returnString = jsonArray.toString();
            }

        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
