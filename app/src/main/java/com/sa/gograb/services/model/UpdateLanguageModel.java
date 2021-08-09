package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class UpdateLanguageModel implements Serializable {

    private final String TAG = "UpdateLanguageModel";
    private final String
            UUID            = "uuid",
            LANGUAGE        = "lang",
            PUSH_TOKEN      = "token";

    String
            uid             = null,
            language        = null,
            pushToken       = null;

    public UpdateLanguageModel(){}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(UUID)){this.uid = json.getString(UUID);}
            if(json.has(LANGUAGE)){this.language = json.getString(LANGUAGE);}
            if(json.has(PUSH_TOKEN)){this.pushToken = json.getString(PUSH_TOKEN);}
            return true;
        }catch(Exception ex){Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(UUID, uid);
            jsonMain.put(LANGUAGE, language);
            jsonMain.put(PUSH_TOKEN, pushToken);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

