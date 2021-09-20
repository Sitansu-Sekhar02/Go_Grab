package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryTypeTwoListModel implements Serializable {

    private final String TAG = "CategoryTwoListModel";

    private  String RESPONSE = "section6";

    List<CategoryTypeTwoModel> categoryTypeTwoList = new ArrayList<CategoryTypeTwoModel>();

    public CategoryTypeTwoListModel(){}

    public List<CategoryTypeTwoModel> getCategoryTypeTwoList() {
        return categoryTypeTwoList;
    }

    public void setCategoryTypeTwoList(List<CategoryTypeTwoModel> categoryTypeTwoList) {
        this.categoryTypeTwoList = categoryTypeTwoList;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<CategoryTypeTwoModel> list = new ArrayList<CategoryTypeTwoModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                CategoryTypeTwoModel keyValueModel = new CategoryTypeTwoModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.categoryTypeTwoList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<CategoryTypeTwoModel> list = new ArrayList<CategoryTypeTwoModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                CategoryTypeTwoModel keyValueModel = new CategoryTypeTwoModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.categoryTypeTwoList = list;
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
            List<CategoryTypeTwoModel> list = this.categoryTypeTwoList;
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
            List<CategoryTypeTwoModel> list = this.categoryTypeTwoList;
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
