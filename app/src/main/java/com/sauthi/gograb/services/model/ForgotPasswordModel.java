package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class ForgotPasswordModel implements Serializable {

    private final String TAG = "ForgotPasswordModel";
    private final String
            PHONE           = "mobile_number",
            PASSWORD        = "password",
            OLD_PASSWORD    = "old_password";


    String
            password        = null,
            mobile          = null,
            OldPassword     = null;

    public ForgotPasswordModel(){}

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            password = json.getString(PASSWORD);
            mobile = json.getString(PHONE);
            OldPassword = json.getString(OLD_PASSWORD);
            return true;
        }catch(Exception ex){Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(PASSWORD, password);
            jsonMain.put(PHONE, mobile);
            jsonMain.put(OLD_PASSWORD, OldPassword);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
