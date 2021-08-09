package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class HomePageModel {
    private final String TAG = "HomePageModel";

    private final String
            HOME_CATEGORY                = "category",
            TOP_CATEGORY                 = "top_near_rest",
            SUB_CATEGORY                 = "popular_rest";


    HomeTopCategoryListModel
            top_near_rest          = null;
    HomeSubCategoryListModel
            popular_rest       =null;





    public HomePageModel(){}



    public HomeTopCategoryListModel getTop_near_rest() {
        return top_near_rest;
    }

    public void setTop_near_rest(HomeTopCategoryListModel top_near_rest) {
        this.top_near_rest = top_near_rest;
    }

    public HomeSubCategoryListModel getPopular_rest() {
        return popular_rest;
    }

    public void setPopular_rest(HomeSubCategoryListModel popular_rest) {
        this.popular_rest = popular_rest;
    }




    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);

           /* if(json.has(CATEGORY)) {
                JSONArray array = json.getJSONArray(CATEGORY);
                CategoryListModel listModelLocal = new CategoryListModel();
                listModelLocal.setRESPONSE(CATEGORY);
                if(listModelLocal.toObject(array)){this.categoryList = listModelLocal;}
                else{this.categoryList = null;}
            }*/
            if(json.has(TOP_CATEGORY)) {
                JSONArray array = json.getJSONArray(TOP_CATEGORY);
                HomeTopCategoryListModel listModelLocal = new HomeTopCategoryListModel();
                if(listModelLocal.toObject(array)){this.top_near_rest = listModelLocal;}
                else{this.top_near_rest = null;}
            }
            if(json.has(SUB_CATEGORY)) {
                JSONArray array = json.getJSONArray(SUB_CATEGORY);
                HomeSubCategoryListModel listModelLocal = new HomeSubCategoryListModel();
                listModelLocal.setRESPONSE(SUB_CATEGORY);
                if(listModelLocal.toObject(array)){this.popular_rest = listModelLocal;}
                else{this.popular_rest = null;}
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
            //jsonMain.put(CATEGORY, categoryList!=null?new JSONArray(categoryList.toString(true)):null);
            jsonMain.put(TOP_CATEGORY, top_near_rest!=null?new JSONArray(top_near_rest.toString(true)):null);
            jsonMain.put(SUB_CATEGORY, popular_rest!=null?new JSONArray(popular_rest.toString(true)):null);
            //jsonMain.put(PRICE, priceRangeModel!=null ? new JSONObject(priceRangeModel.toString()) : new JSONObject());


            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);
        }
        return returnString;
    }

}


