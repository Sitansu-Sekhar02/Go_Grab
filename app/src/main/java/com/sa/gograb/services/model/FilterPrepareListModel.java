package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FilterPrepareListModel implements Serializable {

    private final String TAG = "FilterPrepareListModel";

    private final String RESPONSE = "preparation_time";

    List<FilterPrepareModel> filterPrepareModelList = new ArrayList<FilterPrepareModel>();

    public FilterPrepareListModel(){}

    public List<FilterPrepareModel> getFilterPrepareModelList() {
        return filterPrepareModelList;
    }

    public void setFilterPrepareModelList(List<FilterPrepareModel> filterPrepareModelList) {
        this.filterPrepareModelList = filterPrepareModelList;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<FilterPrepareModel> list = new ArrayList<FilterPrepareModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                FilterPrepareModel model = new FilterPrepareModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.filterPrepareModelList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<FilterPrepareModel> list = new ArrayList<FilterPrepareModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                FilterPrepareModel model = new FilterPrepareModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.filterPrepareModelList = list;
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
            List<FilterPrepareModel> list = this.filterPrepareModelList;
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
            List<FilterPrepareModel> list = this.filterPrepareModelList;
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
