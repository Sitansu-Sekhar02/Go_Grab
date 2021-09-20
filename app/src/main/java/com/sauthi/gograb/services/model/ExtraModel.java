package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Saad on 1/2/2018.
 */

public class ExtraModel implements Serializable {
    private final String TAG = "IndexModel";
    private final String

            SYSTEM_INFO     = "system_info",
            UUID            = "uuid";


    String systemInfo=null,
            uuid=null;


    public ExtraModel(){}


    public String getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(String systemInfo) {
        this.systemInfo = systemInfo;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(SYSTEM_INFO))this.systemInfo = json.getString(SYSTEM_INFO);
            if(json.has(UUID))this.uuid = json.getString(UUID);

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

            jsonMain.put(SYSTEM_INFO, this.systemInfo);
            jsonMain.put(UUID, this.uuid);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
