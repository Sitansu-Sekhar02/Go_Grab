package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class UtilityModel implements Serializable {

    private final String TAG = "UtilityModel";

    private final String
            CONTACT_NUMBER        = "contact_number",
            EMAIL_ID              = "email_id",
            NUMBER_1              = "corporate_number_1",
            PLAY_STORE            = "play_store";

    String
            contactNumber    = null,
            number_one       = null,
            playStore        = null,
            emailId          = null;

    public UtilityModel(){}

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getNumber_one() {
        return number_one;
    }

    public void setNumber_one(String number_one) {
        this.number_one = number_one;
    }

    public String getPlayStore() {
        return playStore;
    }

    public void setPlayStore(String playStore) {
        this.playStore = playStore;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(CONTACT_NUMBER)){this.contactNumber = json.getString(CONTACT_NUMBER);}
            if(json.has(EMAIL_ID)){this.emailId = json.getString(EMAIL_ID);}
            if(json.has(NUMBER_1)){this.number_one = json.getString(NUMBER_1);}
            if(json.has(PLAY_STORE)){this.playStore = json.getString(PLAY_STORE);}

            return true;
        }catch(Exception ex){Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(CONTACT_NUMBER, contactNumber);
            jsonMain.put(EMAIL_ID, emailId);
            jsonMain.put(NUMBER_1, number_one);
            jsonMain.put(PLAY_STORE, playStore);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

