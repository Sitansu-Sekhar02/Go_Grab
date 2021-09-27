package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LanguageListModel implements Serializable {

    private final String TAG = "LanguageListModel";

    private final String RESPONSE = "response";

    List<LanguageModel> languageList = new ArrayList<LanguageModel>();

    public LanguageListModel(){}

    public List<LanguageModel> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<LanguageModel> languageList) {
        this.languageList = languageList;
    }

    public List<String> getNames(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.languageList.size(); i++){
            list.add(languageList.get(i).getTitle());
        }
        return list;
    }

    public int getPosition(String id){
        int position = 0;
        for (LanguageModel city:languageList){
            if(city.getId().equalsIgnoreCase(id)){return position;}
            position ++;
        }
        return -1;
    }
    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<LanguageModel> list = new ArrayList<LanguageModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                LanguageModel model = new LanguageModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.languageList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<LanguageModel> list = new ArrayList<LanguageModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                LanguageModel model = new LanguageModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.languageList = list;
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
            List<LanguageModel> list = this.languageList;
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
            List<LanguageModel> list = this.languageList;
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
