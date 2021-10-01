package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrendingMenuListModel implements Serializable {

    private final String TAG = "TrendingMenuListModel";

    private final String RESPONSE = "trending_menu";

    List<TrendingMenuModel> trendingMenuModels = new ArrayList<TrendingMenuModel>();

    public TrendingMenuListModel(){}

    public List<TrendingMenuModel> getTrendingMenuModels() {
        return trendingMenuModels;
    }

    public void setTrendingMenuModels(List<TrendingMenuModel> trendingMenuModels) {
        this.trendingMenuModels = trendingMenuModels;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<TrendingMenuModel> list = new ArrayList<TrendingMenuModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                TrendingMenuModel model = new TrendingMenuModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.trendingMenuModels = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<TrendingMenuModel> list = new ArrayList<TrendingMenuModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                TrendingMenuModel model = new TrendingMenuModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.trendingMenuModels = list;
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
            List<TrendingMenuModel> list = this.trendingMenuModels;
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
            List<TrendingMenuModel> list = this.trendingMenuModels;
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
