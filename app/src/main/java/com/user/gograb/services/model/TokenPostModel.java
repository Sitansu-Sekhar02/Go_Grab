package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class TokenPostModel implements Serializable {

    private final String TAG = "TokenPostModel";
    private final String
            UID         = "uid",
            TOKEN       = "token";
    String
            uid        = null,
            token      = null;

    public TokenPostModel(){}

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

            if(json.has(UID)){this.uid = json.getString(UID);}
            if(json.has(TOKEN)){this.token = json.getString(TOKEN);}

            return true;
        }catch(Exception ex){Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(UID, uid);
            jsonMain.put(TOKEN, token);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

