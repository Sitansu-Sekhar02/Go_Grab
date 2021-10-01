package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class SubCatSubModel {
    private final String TAG = "SubCatSubModel";

    private final String
            SUB_CATEGORY             = "sub_category";


    CategoryListModel
            subCategoryList        = null;

    public SubCatSubModel(){}


    public CategoryListModel getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(CategoryListModel subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);

            if(json.has(SUB_CATEGORY)) {
                JSONArray array = json.getJSONArray(SUB_CATEGORY);
                CategoryListModel listModelLocal = new CategoryListModel();
                listModelLocal.setRESPONSE(SUB_CATEGORY);
                if(listModelLocal.toObject(array)){this.subCategoryList = listModelLocal;}
                else{this.subCategoryList = null;}
            }

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
            jsonMain.put(SUB_CATEGORY, subCategoryList!=null?new JSONArray(subCategoryList.toString(true)):null);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}


