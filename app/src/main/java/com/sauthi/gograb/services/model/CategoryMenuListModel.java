package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryMenuListModel implements Serializable {

    private final String TAG = "CategoryMenuListModel";

    private final String RESPONSE = "category_menu";

    List<CategoryMenuModel> categoryMenuModels = new ArrayList<CategoryMenuModel>();

    public CategoryMenuListModel(){}

    public List<CategoryMenuModel> getCategoryMenuModels() {
        return categoryMenuModels;
    }

    public void setCategoryMenuModels(List<CategoryMenuModel> categoryMenuModels) {
        this.categoryMenuModels = categoryMenuModels;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<CategoryMenuModel> list = new ArrayList<CategoryMenuModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                CategoryMenuModel model = new CategoryMenuModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.categoryMenuModels = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<CategoryMenuModel> list = new ArrayList<CategoryMenuModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                CategoryMenuModel model = new CategoryMenuModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.categoryMenuModels = list;
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
            List<CategoryMenuModel> list = this.categoryMenuModels;
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
            List<CategoryMenuModel> list = this.categoryMenuModels;
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
