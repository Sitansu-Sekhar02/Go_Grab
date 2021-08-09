package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class OrderSendAddressModel implements Serializable {
    private final String TAG = "AddressModel";
    private final String
            ID = "id",
            NAME = "name",
            SITE_PERSON_NAME = "site_person_name",
            COMPANY_TITLE = "company_title",
            PHONE = "mobile_number",
            EMAIL = "email_id",
            ADDRESS = "address",
            STATE = "state",
            LATITUDE = "latitude",
            LONGITUDE = "lognitude",
            CITY = "city",
            CITY_TITLE = "city_title",
            POSTAL_CODE ="zipcode",
            LANDMARK ="landmark",
            NEW ="new",
            ADD_TYPE = "add_type";

    String
            id = null,
            name = null,
            company_title = null,
            phone=null,
            landmark=null,
            address=null,
            state=null,
            add_type = null,
            cityID = null,
            sitePersonName = null,
            cityTitle = null,
            email = null,
            pincode = null;

    boolean
            isNew = false;

    double
            latitude=0.0,
            longitude=0.0;

    public OrderSendAddressModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCompany_title() {
        return company_title;
    }

    public void setCompany_title(String company_title) {
        this.company_title = company_title;
    }

    public String getAdd_type() {
        return add_type;
    }

    public void setAdd_type(String add_type) {
        this.add_type = add_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pinCode) {
        this.pincode = pinCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getSitePersonName() {
        return sitePersonName;
    }

    public void setSitePersonName(String sitePersonName) {
        this.sitePersonName = sitePersonName;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            id = json.getString(ID);

            if(json.has(NAME)) name = json.getString(NAME);
            if(json.has(SITE_PERSON_NAME)) sitePersonName = json.getString(SITE_PERSON_NAME);
            if(json.has(PHONE))phone = json.getString(PHONE);
            if(json.has(LANDMARK))landmark = json.getString(LANDMARK);
            if(json.has(EMAIL))email = json.getString(EMAIL);
            address = json.getString(ADDRESS);
            if(json.has(STATE))state = json.getString(STATE);
            if(json.has(LATITUDE))try{this.latitude = json.getDouble(LATITUDE);}catch (Exception e){this.latitude = 0.0;}
            if(json.has(LONGITUDE))try{this.longitude = json.getDouble(LONGITUDE);}catch (Exception e){this.longitude = 0.0;}
            if(json.has(COMPANY_TITLE))company_title = json.getString(COMPANY_TITLE);
            if(json.has(POSTAL_CODE))pincode = json.getString(POSTAL_CODE);

            if(json.has(ADD_TYPE))add_type = json.getString(ADD_TYPE);
            if(json.has(CITY))cityID = json.getString(CITY);
            if(json.has(CITY_TITLE))cityTitle = json.getString(CITY_TITLE);

            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);
            return false;}

    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(ID, id);
            jsonMain.put(NAME, name);
            jsonMain.put(SITE_PERSON_NAME, sitePersonName);
            if(company_title != null)jsonMain.put(COMPANY_TITLE, company_title);
            jsonMain.put(PHONE, phone);
            jsonMain.put(EMAIL, email);
            jsonMain.put(ADDRESS, address);
            jsonMain.put(LANDMARK, landmark);
            jsonMain.put(CITY, cityID);
            jsonMain.put(CITY_TITLE, cityTitle);
            jsonMain.put(STATE, state);
            if(String.valueOf(latitude) != null)jsonMain.put(LATITUDE, latitude);
            if(String.valueOf(longitude) != null)jsonMain.put(LONGITUDE, longitude);
           // jsonMain.put(CITY, city);
            if(pincode != null) jsonMain.put(POSTAL_CODE, pincode);
            if(add_type != null)jsonMain.put(ADD_TYPE, add_type);
            jsonMain.put(NEW, isNew ? "1":"0");

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
