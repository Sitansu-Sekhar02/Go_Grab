package com.sauthi.gograb.services.model.registration;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class OtpModel implements Serializable {

    private final String
            TAG             = "OtpModel";

    private final String
            OTP             = "otp",
            PHONE           = "mobile_number",
            COUNTRY_CODE    = "country_code",
            FROM            = "flag";

    String
            otp             = null,
            phone           = null,
            countryCode     = null,
            from            = null;


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public boolean toObject(String jsonObject){

        try{
            JSONObject json = new JSONObject(jsonObject);
            this.otp = json.getString(OTP);
            if(json.has(PHONE))phone = json.getString(PHONE);
            if(json.has(FROM))from = json.getString(FROM);
            if(json.has(COUNTRY_CODE))countryCode = json.getString(COUNTRY_CODE);

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

            jsonMain.put(OTP, this.otp);
            jsonMain.put(PHONE, phone);
            jsonMain.put(FROM, from);
            jsonMain.put(COUNTRY_CODE, countryCode);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
