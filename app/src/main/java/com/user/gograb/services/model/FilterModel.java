package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class FilterModel {
    private final String TAG = "FilterModel";

    private final String
            TITLE                   = "title",
            TYPE                    = "type",
            DISTANCE                = "distance",
            CUSINES                 = "cusines",
            PREPARE_TIME            = "preparation_time";


    FilterDistanceListModel
            filterDistanceListModel          = null,
            filterCusinesListModel           =null,
            filterPrepareListModel           =null;

String title  = null;
String type   = null;



    public FilterModel(){}


    public FilterDistanceListModel getFilterDistanceListModel() {
        return filterDistanceListModel;
    }

    public void setFilterDistanceListModel(FilterDistanceListModel filterDistanceListModel) {
        this.filterDistanceListModel = filterDistanceListModel;
    }

    public FilterDistanceListModel getFilterCusinesListModel() {
        return filterCusinesListModel;
    }

    public void setFilterCusinesListModel(FilterDistanceListModel filterCusinesListModel) {
        this.filterCusinesListModel = filterCusinesListModel;
    }

    public FilterDistanceListModel getFilterPrepareListModel() {
        return filterPrepareListModel;
    }

    public void setFilterPrepareListModel(FilterDistanceListModel filterPrepareListModel) {
        this.filterPrepareListModel = filterPrepareListModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);

            if(json.has(DISTANCE)) {
                JSONArray array = json.getJSONArray(DISTANCE);
                FilterDistanceListModel listModelLocal = new FilterDistanceListModel();
                if(listModelLocal.toObject(array)){this.filterDistanceListModel = listModelLocal;}
                else{this.filterDistanceListModel = null;}
            }
            if(json.has(CUSINES)) {
                JSONArray array = json.getJSONArray(CUSINES);
                FilterDistanceListModel listModelLocal = new FilterDistanceListModel();
                if(listModelLocal.toObject(array)){this.filterCusinesListModel = listModelLocal;}
                else{this.filterCusinesListModel = null;}
            }
            if(json.has(PREPARE_TIME)) {
                JSONArray array = json.getJSONArray(PREPARE_TIME);
                FilterDistanceListModel listModelLocal = new FilterDistanceListModel();
                if(listModelLocal.toObject(array)){this.filterPrepareListModel = listModelLocal;}
                else{this.filterPrepareListModel = null;}
            }


            if (json.has(TITLE)) title = json.getString(TITLE);
            if (json.has(TYPE)) type = json.getString(TYPE);

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
            jsonMain.put(DISTANCE, filterDistanceListModel!=null?new JSONArray(filterDistanceListModel.toString(true)):null);
            jsonMain.put(CUSINES, filterCusinesListModel!=null?new JSONArray(filterCusinesListModel.toString(true)):null);
            jsonMain.put(PREPARE_TIME, filterPrepareListModel!=null?new JSONArray(filterPrepareListModel.toString(true)):null);
            //jsonMain.put(PRICE, priceRangeModel!=null ? new JSONObject(priceRangeModel.toString()) : new JSONObject());
            jsonMain.put(TITLE, title);
            jsonMain.put(TYPE, type);


            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);
        }
        return returnString;
    }

}


