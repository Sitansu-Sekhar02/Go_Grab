package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeSubCategoryListModel implements Serializable {

    private final String TAG = "HomeSubCategoryListModel";

    private  String RESPONSE = "popular_rest";

    List<HomeSubCategoryModel> homeSubCategoryModels = new ArrayList<HomeSubCategoryModel>();

    public HomeSubCategoryListModel(){}

    public String getRESPONSE() {
        return RESPONSE;
    }

    public void setRESPONSE(String RESPONSE) {
        this.RESPONSE = RESPONSE;
    }

    public List<HomeSubCategoryModel> getHomeSubCategoryModels() {
        return homeSubCategoryModels;
    }

    public void setHomeSubCategoryModels(List<HomeSubCategoryModel> homeSubCategoryModels) {
        this.homeSubCategoryModels = homeSubCategoryModels;
    }

    public List<String> getNames(){
        List<String> list = new ArrayList<String>();
        for(int i = 0; i<this.homeSubCategoryModels.size(); i++){
           // list.add(categoryList.get(i).getTitle());
        }
        return list;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<HomeSubCategoryModel> list = new ArrayList<HomeSubCategoryModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                HomeSubCategoryModel keyValueModel = new HomeSubCategoryModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.homeSubCategoryModels = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<HomeSubCategoryModel> list = new ArrayList<HomeSubCategoryModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HomeSubCategoryModel keyValueModel = new HomeSubCategoryModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.homeSubCategoryModels = list;
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
            List<HomeSubCategoryModel> list = this.homeSubCategoryModels;
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
            List<HomeSubCategoryModel> list = this.homeSubCategoryModels;
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
