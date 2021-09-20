package com.sauthi.gograb.services.model;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeFilterCategoryListModel implements Serializable {

    private final String TAG = "HomeFilterCategoryListModel";

    private  String RESPONSE = "popular_rest";

    List<HomeFilterCategoryModel> homeFilterCategoryModels = new ArrayList<HomeFilterCategoryModel>();

    public HomeFilterCategoryListModel(){}

    public String getRESPONSE() {
        return RESPONSE;
    }

    public void setRESPONSE(String RESPONSE) {
        this.RESPONSE = RESPONSE;
    }

    public List<HomeFilterCategoryModel> getHomeFilterCategoryModels() {
        return homeFilterCategoryModels;
    }

    public void setHomeFilterCategoryModels(List<HomeFilterCategoryModel> homeFilterCategoryModels) {
        this.homeFilterCategoryModels = homeFilterCategoryModels;
    }

    public List<String> getNames(){
        List<String> list = new ArrayList<String>();
        for(int i = 0; i<this.homeFilterCategoryModels.size(); i++){
           // list.add(categoryList.get(i).getTitle());
        }
        return list;
    }

    @SuppressLint("LongLogTag")
    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<HomeFilterCategoryModel> list = new ArrayList<HomeFilterCategoryModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                HomeFilterCategoryModel keyValueModel = new HomeFilterCategoryModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.homeFilterCategoryModels = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @SuppressLint("LongLogTag")
    public boolean toObject(JSONArray jsonArray){
        try{
            List<HomeFilterCategoryModel> list = new ArrayList<HomeFilterCategoryModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HomeFilterCategoryModel keyValueModel = new HomeFilterCategoryModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.homeFilterCategoryModels = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @SuppressLint("LongLogTag")
    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            List<HomeFilterCategoryModel> list = this.homeFilterCategoryModels;
            for(int i=0;i<list.size();i++){
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            jsonMain.put(RESPONSE, jsonArray);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

    @SuppressLint("LongLogTag")
    public String toString(boolean isArray){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            List<HomeFilterCategoryModel> list = this.homeFilterCategoryModels;
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
