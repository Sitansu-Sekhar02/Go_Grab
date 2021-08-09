package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyCityListModel implements Serializable {

    private final String TAG = "MyCityListModel";

    private String RESPONSE = "response";

    List<MyCityModel> cityList = new ArrayList<MyCityModel>();

    public MyCityListModel(){}

    public String getRESPONSE() {
        return RESPONSE;
    }

    public void setRESPONSE(String RESPONSE) {
        this.RESPONSE = RESPONSE;
    }

    public List<MyCityModel> getCityList() {
        return cityList;
    }

    public void setCityList(List<MyCityModel> cityList) {
        this.cityList = cityList;
    }

    public List<String> getNames(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.cityList.size(); i++){
            list.add(cityList.get(i).getTitle());
        }
        return list;
    }

    public int getPosition(String id){
        int position = 0;
        for (MyCityModel city:cityList){
            if(city.getId().equalsIgnoreCase(id)){return position;}
            position ++;
        }
        return -1;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<MyCityModel> list = new ArrayList<MyCityModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                MyCityModel model = new MyCityModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.cityList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<MyCityModel> list = new ArrayList<MyCityModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                MyCityModel model = new MyCityModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.cityList = list;
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
            List<MyCityModel> list = this.cityList;
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
            List<MyCityModel> list = this.cityList;
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
