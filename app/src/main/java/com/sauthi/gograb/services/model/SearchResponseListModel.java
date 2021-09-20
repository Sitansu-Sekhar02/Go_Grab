package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SearchResponseListModel implements Serializable {

    private final String TAG = "SearchResponseListModel";

    private final String RESPONSE = "response";

    ArrayList<SearchResponseModel> searchResponseList = new ArrayList<SearchResponseModel>();

    public SearchResponseListModel(){}

    public ArrayList<SearchResponseModel> getSearchResponseList() {
        return searchResponseList;
    }

    public void setSearchResponseList(ArrayList<SearchResponseModel> searchResponseList) {
        this.searchResponseList = searchResponseList;
    }

    public void setKeyValueList(ArrayList<SearchResponseModel> searchResponseList) {
        this.searchResponseList = searchResponseList;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            ArrayList<SearchResponseModel> list = new ArrayList<SearchResponseModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                SearchResponseModel keyValueModel = new SearchResponseModel();
                if(keyValueModel.toObject(jsonObject.toString())){list.add(keyValueModel);}
//                else{throw new Exception(new Throwable(MainActivity.context.getString(R.string.not_valid_model)));}
            }
            this.searchResponseList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);
            return false;}

    }

    public boolean toObject(JSONArray jsonArray){
        try{
            ArrayList<SearchResponseModel> list = new ArrayList<SearchResponseModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                SearchResponseModel keyValueModel = new SearchResponseModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.searchResponseList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);
            return false;}

    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            List<SearchResponseModel> list = this.searchResponseList;
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
            List<SearchResponseModel> list = this.searchResponseList;
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
