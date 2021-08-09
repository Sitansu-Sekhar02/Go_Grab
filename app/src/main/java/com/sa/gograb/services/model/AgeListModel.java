package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AgeListModel implements Serializable {

    private final String TAG = "AgeListModel";

    private final String RESPONSE = "response";

    List<AgeModel> ageList = new ArrayList<AgeModel>();

    public AgeListModel(){}

    public List<AgeModel> getAgeList() {
        return ageList;
    }

    public void setAgeList(List<AgeModel> ageList) {
        this.ageList = ageList;
    }

    public List<String> getNames(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.ageList.size(); i++){
            list.add(ageList.get(i).getTitle());
        }
        return list;
    }

    public int getPosition(String id){
        int position = 0;
        for (AgeModel city:ageList){
            if(city.getId().equalsIgnoreCase(id)){return position;}
            position ++;
        }
        return -1;
    }
    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<AgeModel> list = new ArrayList<AgeModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                AgeModel model = new AgeModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.ageList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<AgeModel> list = new ArrayList<AgeModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                AgeModel model = new AgeModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.ageList = list;
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
            List<AgeModel> list = this.ageList;
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
            List<AgeModel> list = this.ageList;
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
