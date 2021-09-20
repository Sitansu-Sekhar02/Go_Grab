package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class LiveStreamTokenModel implements Serializable {

    private final String TAG = "LiveStreamTokenModel";
    private final String
            CHANNEL_NAME         = "channel_name",
            UID_TOKEN            = "uidtoken",
            TOKEN                = "token",
            UID                  = "uid";
    String
            channelName     = null,
            uidtoken        = null,
            token           = null,
            uid             = null;

    public LiveStreamTokenModel(){}

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getUidtoken() {
        return uidtoken;
    }

    public void setUidtoken(String uidtoken) {
        this.uidtoken = uidtoken;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if(json.has(CHANNEL_NAME)){this.channelName = json.getString(CHANNEL_NAME);}
            if(json.has(UID_TOKEN)){this.uidtoken = json.getString(UID_TOKEN);}
            if(json.has(TOKEN)){this.token = json.getString(TOKEN);}
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
            jsonMain.put(UID_TOKEN, uidtoken);
            jsonMain.put(TOKEN, token);
            jsonMain.put(UID, uid);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

