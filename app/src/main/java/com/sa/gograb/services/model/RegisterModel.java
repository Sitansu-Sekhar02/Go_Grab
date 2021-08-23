package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class RegisterModel implements Serializable {

    private final String TAG = "RegisterModel";

    private final String
            COUNTRY_CODE          = "country_code",
            PHONE                 = "mobile_number",
            EMAIL                 = "email_id",
            PASSWORD              = "password",
            FIRST_NAME            = "first_name",
            LAST_NAME             = "last_name",
            COMP_NAME             = "company_name",
            UUID                  = "uuid",
            SYSTEM_INFO           = "system_info",
            DEVICE_TYPE           = "device_type",
            LOGIN_TOKEN           = "login_token",
            GENDER                = "gender",
            REGISTERED_FROM       = "registerd_from",
            FULL_NAME             = "full_name",
            PROFILE_IMAGE         = "profile_image",
            ADDRESS               = "address",
            LATITUDE              = "latitude",
            LONGITUDE             = "longitude",
            CITY_ID               = "city_id",
            COUNTRY_ID            = "country_id";

    String
            countryCode           = null,
            mobileNumber          = null,
            emailId               = null,
            password              = null,
            firstName             = null,
            lastName              = null,
            companyName           = null,
            uid                   = null,
            deviceType            = "2",
            systemInfo            = null,
            loginToken            = null,
            gender                = null,
            registeredFrom        = "3",
            fullName              = null,
            profileImage          = null,
            address               = null,
            latitude              = null,
            longitude             = null,
            city_id               = null,
            countryId             = null;

    public RegisterModel(){}

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(String systemInfo) {
        this.systemInfo = systemInfo;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegisteredFrom() {
        return registeredFrom;
    }

    public void setRegisteredFrom(String registeredFrom) {
        this.registeredFrom = registeredFrom;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(COUNTRY_CODE)){this.countryCode = json.getString(COUNTRY_CODE);}
            if(json.has(PHONE)){this.mobileNumber = json.getString(PHONE);}
            if(json.has(EMAIL)){this.emailId = json.getString(EMAIL);}
            if(json.has(PASSWORD)){this.password = json.getString(PASSWORD);}
            if(json.has(FIRST_NAME)){this.firstName = json.getString(FIRST_NAME);}
            if(json.has(LAST_NAME)){this.lastName = json.getString(LAST_NAME);}
            if(json.has(COMP_NAME)){this.companyName = json.getString(COMP_NAME);}
            if(json.has(UUID)){this.uid = json.getString(UUID);}
            if(json.has(DEVICE_TYPE)){this.deviceType = json.getString(DEVICE_TYPE);}
            if(json.has(SYSTEM_INFO)){this.systemInfo = json.getString(SYSTEM_INFO);}
            if(json.has(LOGIN_TOKEN)){this.loginToken = json.getString(LOGIN_TOKEN);}
            if(json.has(GENDER)){this.gender = json.getString(GENDER);}
            if(json.has(REGISTERED_FROM)){this.registeredFrom = json.getString(REGISTERED_FROM);}
            if(json.has(FULL_NAME)){this.fullName = json.getString(FULL_NAME);}
            if(json.has(PROFILE_IMAGE)){this.profileImage = json.getString(PROFILE_IMAGE);}
            if(json.has(ADDRESS)){this.address = json.getString(ADDRESS);}
            if(json.has(LATITUDE)){this.latitude = json.getString(LATITUDE);}
            if(json.has(LONGITUDE)){this.longitude = json.getString(LONGITUDE);}
            if(json.has(CITY_ID)){this.city_id = json.getString(CITY_ID);}
            if(json.has(COUNTRY_ID)){this.countryId = json.getString(COUNTRY_ID);}

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
            jsonMain.put(COUNTRY_CODE, countryCode);
            jsonMain.put(PHONE, mobileNumber);
            jsonMain.put(EMAIL, emailId);
            jsonMain.put(PASSWORD, password);
            jsonMain.put(FIRST_NAME, firstName);
            jsonMain.put(LAST_NAME, lastName);
            jsonMain.put(COMP_NAME, companyName);
            jsonMain.put(UUID, uid);
            jsonMain.put(DEVICE_TYPE, deviceType);
            jsonMain.put(SYSTEM_INFO, systemInfo);
            jsonMain.put(LOGIN_TOKEN, loginToken);
            jsonMain.put(GENDER, gender);
            jsonMain.put(REGISTERED_FROM, registeredFrom);
            jsonMain.put(FULL_NAME, fullName);
            jsonMain.put(PROFILE_IMAGE, profileImage);
            jsonMain.put(ADDRESS, address);
            jsonMain.put(LATITUDE, latitude);
            jsonMain.put(LONGITUDE, longitude);
            jsonMain.put(CITY_ID, city_id);
            jsonMain.put(COUNTRY_ID, countryId);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
