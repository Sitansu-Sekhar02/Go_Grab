package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class UtilitySubMainModel implements Serializable {
    private final String TAG = "WalletSubMainModel";

    private final String
            RESPONSE                   = "utility";

    UtilityModel
            utilityModel             = null;

    public UtilitySubMainModel(){}

    public UtilityModel getUtilityModel() {
        return utilityModel;
    }

    public void setUtilityModel(UtilityModel utilityModel) {
        this.utilityModel = utilityModel;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);

            if(json.has(RESPONSE)){
                UtilityModel statusModel = new UtilityModel();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1 = json.getJSONObject(RESPONSE);
                if(jsonObject1 != null){statusModel.toObject(jsonObject1.toString());}
                utilityModel = statusModel;
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
            jsonMain.put(RESPONSE, utilityModel != null ? new JSONObject(this.utilityModel.toString()) : new JSONObject());
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}


