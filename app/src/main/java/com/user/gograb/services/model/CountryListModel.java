package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CountryListModel implements Serializable {

    private final String TAG = "CountryListModel";

    private final String RESPONSE = "response";

    List<CountryModel> countryList = new ArrayList<CountryModel>();

    public CountryListModel(){}

    public List<CountryModel> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryModel> countryList) {
        this.countryList = countryList;
    }

    public List<String> getCountryCodes(){
        List<String> countryCodelist = new ArrayList<String>();
        for(int i =0 ;i<this.countryList.size(); i++){
            countryCodelist.add(countryList.get(i).getCountryCode());
        }
        return countryCodelist;
    }

    public String getMobileNumberLength(){
        //List<String> mobileNumberLengthlist = new ArrayList<String>();
        String mobileNumberLengthlist = null;
        for(int i =0 ;i<this.countryList.size(); i++){
            //mobileNumberLengthlist.add(countryList.get(i).getContactLength());
            mobileNumberLengthlist = countryList.get(i).getContactLength();
        }
        return mobileNumberLengthlist;
    }

    public List<String> getNames(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.countryList.size(); i++){
            list.add(countryList.get(i).getName());
        }
        return list;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<CountryModel> list = new ArrayList<CountryModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                CountryModel model = new CountryModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.countryList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<CountryModel> list = new ArrayList<CountryModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                CountryModel model = new CountryModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.countryList = list;
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
            List<CountryModel> list = this.countryList;
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
            List<CountryModel> list = this.countryList;
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
