package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;


public class LogoutModel implements Serializable {
    private final String TAG = "LogoutModel";

    private final String
            UUID                  = "uuid",
            SYSTEM_INFO           = "system_info",
            PUSH_TOKEN            = "token";


    String
            uuid                  = null,
            system_info           = null,
            push_token            = null;

    public String getUuid() {
        return uuid;
    }

    public String getSystem_info() {
        return system_info;
    }

    public void setSystem_info(String system_info) {
        this.system_info = system_info;
    }

    public String getPush_token() {
        return push_token;
    }

    public void setPush_token(String push_token) {
        this.push_token = push_token;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(UUID)){
                this.uuid = json.getString(UUID);
            }if(json.has(SYSTEM_INFO)){
                this.system_info = json.getString(SYSTEM_INFO);
            } if(json.has(PUSH_TOKEN)){
                this.push_token = json.getString(PUSH_TOKEN);
            }

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
            jsonMain.put(UUID, this.uuid);
            jsonMain.put(SYSTEM_INFO, this.system_info);
            jsonMain.put(PUSH_TOKEN, this.push_token);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
