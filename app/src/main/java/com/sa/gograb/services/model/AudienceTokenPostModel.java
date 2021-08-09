package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class AudienceTokenPostModel implements Serializable {

    private final String TAG = "AudienceTokenPostModel";
    private final String
            CHANNEL_NAME         = "channel_name",
            UID                  = "uid";
    String
            channelName     = null,
            uid             = null;

    public AudienceTokenPostModel(){}

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if(json.has(CHANNEL_NAME)){this.channelName = json.getString(CHANNEL_NAME);}
            if(json.has(UID)){this.uid = json.getString(UID);}

            return true;
        }catch(Exception ex){Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(CHANNEL_NAME, channelName);
            jsonMain.put(UID, uid);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

