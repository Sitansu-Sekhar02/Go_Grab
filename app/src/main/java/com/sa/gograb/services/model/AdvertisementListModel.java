package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementListModel implements Serializable {

    private final String TAG = "AdvertisementListModel";

    private final String RESPONSE = "section4";

    List<AdvertisementModel> advertisementList = new ArrayList<AdvertisementModel>();

    public AdvertisementListModel(){}

    public List<AdvertisementModel> getAdvertisementList() {
        return advertisementList;
    }

    public void setAdvertisementList(List<AdvertisementModel> advertisementList) {
        this.advertisementList = advertisementList;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<AdvertisementModel> list = new ArrayList<AdvertisementModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                AdvertisementModel model = new AdvertisementModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.advertisementList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<AdvertisementModel> list = new ArrayList<AdvertisementModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                AdvertisementModel model = new AdvertisementModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.advertisementList = list;
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
            List<AdvertisementModel> list = this.advertisementList;
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
            List<AdvertisementModel> list = this.advertisementList;
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
