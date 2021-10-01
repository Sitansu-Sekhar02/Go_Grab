package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BottomAdvertisementListModel implements Serializable {

    private final String TAG = "BottomAdvertisementList";

    private final String RESPONSE = "bottom_adv";

    List<BottomAdvertisementModel> bottomAdvertisementList = new ArrayList<BottomAdvertisementModel>();

    public BottomAdvertisementListModel(){}

    public List<BottomAdvertisementModel> getBottomAdvertisementList() {
        return bottomAdvertisementList;
    }

    public void setBottomAdvertisementList(List<BottomAdvertisementModel> bottomAdvertisementList) {
        this.bottomAdvertisementList = bottomAdvertisementList;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<BottomAdvertisementModel> list = new ArrayList<BottomAdvertisementModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                BottomAdvertisementModel model = new BottomAdvertisementModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.bottomAdvertisementList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<BottomAdvertisementModel> list = new ArrayList<BottomAdvertisementModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                BottomAdvertisementModel model = new BottomAdvertisementModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.bottomAdvertisementList = list;
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
            List<BottomAdvertisementModel> list = this.bottomAdvertisementList;
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
            List<BottomAdvertisementModel> list = this.bottomAdvertisementList;
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
