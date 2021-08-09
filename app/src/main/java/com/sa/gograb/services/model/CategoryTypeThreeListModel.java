package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryTypeThreeListModel implements Serializable {

    private final String TAG = "CategoryThreeListModel";

    private  String RESPONSE = "section7";

    List<CategoryTypeThreeModel> categoryTypeThreeList = new ArrayList<CategoryTypeThreeModel>();

    public CategoryTypeThreeListModel(){}

    public List<CategoryTypeThreeModel> getCategoryTypeThreeList() {
        return categoryTypeThreeList;
    }

    public void setCategoryTypeThreeList(List<CategoryTypeThreeModel> categoryTypeThreeList) {
        this.categoryTypeThreeList = categoryTypeThreeList;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<CategoryTypeThreeModel> list = new ArrayList<CategoryTypeThreeModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                CategoryTypeThreeModel keyValueModel = new CategoryTypeThreeModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.categoryTypeThreeList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<CategoryTypeThreeModel> list = new ArrayList<CategoryTypeThreeModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                CategoryTypeThreeModel keyValueModel = new CategoryTypeThreeModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.categoryTypeThreeList = list;
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
            List<CategoryTypeThreeModel> list = this.categoryTypeThreeList;
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
            List<CategoryTypeThreeModel> list = this.categoryTypeThreeList;
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
