package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAddressListModel implements Serializable {

    private final String TAG = "UserAddressListModel";

    private final String RESPONSE = "response";

    List<UserAddressModel> userAddressList = new ArrayList<UserAddressModel>();

    public UserAddressListModel(){}

    public List<UserAddressModel> getUserAddressList() {
        return userAddressList;
    }

    public void setUserAddressList(List<UserAddressModel> userAddressList) {
        this.userAddressList = userAddressList;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<UserAddressModel> list = new ArrayList<UserAddressModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                UserAddressModel model = new UserAddressModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.userAddressList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<UserAddressModel> list = new ArrayList<UserAddressModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                UserAddressModel model = new UserAddressModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.userAddressList = list;
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
            List<UserAddressModel> list = this.userAddressList;
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
            List<UserAddressModel> list = this.userAddressList;
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
