package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class LoginModel implements Serializable {

    private final String TAG = "LoginModel";
    private final String
            MOBILE_NUMBER    = "mobile_number",
            FULL_NAME        = "full_name",
            EMAIL_ID        = "email_id",
            IMAGE           = "image",
            COUNTRY_CODE    = "country_code",
            VEHICLE_NUMBER  = "vehicle_number",
            GENDER           = "gender",
            PROVIDER        = "provider",
            PROVIDER_TOKEN  = "provider_token",
            PASSWORD        = "password",
            SYSTEM_INFO     = "system_info",
            USER_TYPE       = "user_type",
            DEVICE_TYPE     = "device_type",
            UUID            = "uuid";


    String
            mobile_number       =null,
            full_name           =null,
            email_id            =null,
            countryCode         =null,
            image               =null,
            vehicle_number      =null,
            gender              =null,
            password            =null,
            provider            =null,
            providerToken       = null,
            userType            = null,
            deviceType          = null,
            systemInfo          =null,
            uuid                =null;

    public LoginModel(){}


    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderToken() {
        return providerToken;
    }

    public void setProviderToken(String providerToken) {
        this.providerToken = providerToken;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            mobile_number = json.getString(MOBILE_NUMBER);
            full_name = json.getString(FULL_NAME);
            email_id=json.getString(EMAIL_ID);
            countryCode = json.getString(COUNTRY_CODE);
            image = json.getString(IMAGE);
            vehicle_number = json.getString(VEHICLE_NUMBER);
            gender = json.getString(GENDER);
            password = json.getString(PASSWORD);
            provider = json.getString(PROVIDER);
            providerToken = json.getString(PROVIDER_TOKEN);
            userType = json.getString(USER_TYPE);
            deviceType = json.getString(DEVICE_TYPE);
            this.systemInfo = json.getString(SYSTEM_INFO);
            this.uuid = json.getString(UUID);
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
            jsonMain.put(MOBILE_NUMBER, mobile_number);
            jsonMain.put(FULL_NAME, full_name);
            jsonMain.put(EMAIL_ID,email_id);
            jsonMain.put(COUNTRY_CODE, countryCode);
            jsonMain.put(IMAGE, image);
            jsonMain.put(VEHICLE_NUMBER, vehicle_number);
            jsonMain.put(GENDER, gender);
            jsonMain.put(PASSWORD, password);
            jsonMain.put(PROVIDER, provider);
            jsonMain.put(PROVIDER_TOKEN, providerToken);
            jsonMain.put(USER_TYPE, userType);
            jsonMain.put(DEVICE_TYPE, deviceType);
            jsonMain.put(SYSTEM_INFO, this.systemInfo);
            jsonMain.put(UUID, this.uuid);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
