package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SessionTypeTimingsListModel implements Serializable {

    private final String
            TAG                = "SessionTypeListModel";

    private final String
            SESSION_TYPE       = "session_type";


    List<SessionTypeTimingsModel> listSessionType = new ArrayList<SessionTypeTimingsModel>();

    public SessionTypeTimingsListModel(){}

    public List<SessionTypeTimingsModel> getListSessionType() {
        return listSessionType;
    }

    public void setListSessionType(List<SessionTypeTimingsModel> listSessionType) {
        this.listSessionType = listSessionType;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(SESSION_TYPE);
            List<SessionTypeTimingsModel> list = new ArrayList<SessionTypeTimingsModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                SessionTypeTimingsModel model = new SessionTypeTimingsModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.listSessionType = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);
            return false;}

    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<SessionTypeTimingsModel> list = new ArrayList<SessionTypeTimingsModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                SessionTypeTimingsModel keyValueModel = new SessionTypeTimingsModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.listSessionType = list;
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
            List<SessionTypeTimingsModel> list = this.listSessionType;
            for(int i=0;i<list.size();i++){
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            jsonMain.put(SESSION_TYPE, jsonArray);
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
            List<SessionTypeTimingsModel> list = this.listSessionType;
            for(int i=0;i<list.size();i++){
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            if(!isArray){
                jsonMain.put(SESSION_TYPE, jsonArray);
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
