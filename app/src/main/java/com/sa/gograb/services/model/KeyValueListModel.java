package com.sa.gograb.services.model;

import android.util.Log;


import com.sa.gograb.MainActivity;
import com.sa.gograb.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KeyValueListModel implements Serializable {

    private final String TAG = "KeyValueListModel";

    private final String RESPONSE = "response";

    List<KeyValueModel> keyValueList = new ArrayList<KeyValueModel>();

    public KeyValueListModel(){}

    public KeyValueListModel(List<KeyValueModel> list){
        this.keyValueList = list;
    }

    public List<KeyValueModel> getKeyValueList() {
        return keyValueList;
    }

    public void setKeyValueList(List<KeyValueModel> keyValueList) {
        this.keyValueList = keyValueList;
    }

    public List<String> getIDs(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.keyValueList.size(); i++){
            list.add(keyValueList.get(i).getId());
        }
        return list;
    }

    public List<String> getNames(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.keyValueList.size(); i++){
            list.add(keyValueList.get(i).getName());
        }
        return list;
    }

    public List<String> getExtras(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.keyValueList.size(); i++){
            list.add(keyValueList.get(i).getExtra());
        }
        return list;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<KeyValueModel> list = new ArrayList<KeyValueModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                KeyValueModel keyValueModel = new KeyValueModel();
                if(keyValueModel.toObject(jsonObject.toString())){list.add(keyValueModel);}
                else{throw new Exception(new Throwable(MainActivity.mainContext.getString(R.string.not_valid_model)));}
            }
            this.keyValueList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);
            return false;}

    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<KeyValueModel> list = new ArrayList<KeyValueModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                KeyValueModel keyValueModel = new KeyValueModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.keyValueList = list;
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
            List<KeyValueModel> list = this.keyValueList;
            for(int i=0;i<list.size();i++){
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            jsonMain.put(RESPONSE, jsonArray);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

    public String toString(boolean isArray){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            List<KeyValueModel> list = this.keyValueList;
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
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
