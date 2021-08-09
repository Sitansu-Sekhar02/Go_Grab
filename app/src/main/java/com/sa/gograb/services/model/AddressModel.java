package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class AddressModel implements Serializable {
    private final String TAG = "AddressModel";
    private final String
            ID              = "id",
            LONGITUDE       = "lng",
            LATITUDE        = "lat",
            NAME            = "name",
            CITY            = "city",
            PHONE           = "phone",
            STATE           = "state",
            STREET          = "street",
            COUNTRY         = "country",
            ADDRESS         = "address",
            PINCODE         = "pin_code",
            LANDMARK        = "landmark";

    String
            id = null,
            name = null,
            phone=null,
            address=null,
            state=null,
            city="",
            pincode = "",
            street = null,
            landmark = null,
            country=null;

    boolean
            deleting = false;

    double
            latitude=0.0,
            longitude=0.0;

    public AddressModel(){}

    public boolean isDeleting() {
        return deleting;
    }

    public void setDeleting(boolean deleting) {
        this.deleting = deleting;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
            if(json.has(ID))id = json.getString(ID);
            if(json.has(NAME)) name = json.getString(NAME);
            if(json.has(PHONE))phone = json.getString(PHONE);
            if(json.has(ADDRESS))address = json.getString(ADDRESS);
            if(json.has(STATE))state = json.getString(STATE);
            if(json.has(LATITUDE))latitude = json.getDouble(LATITUDE);
            if(json.has(LONGITUDE))longitude = json.getDouble(LONGITUDE);
            if(json.has(CITY))city = json.getString(CITY);
            if(json.has(PINCODE))pincode = json.getString(PINCODE);
            if(json.has(STREET))street = json.getString(STREET);
            if(json.has(COUNTRY))country = json.getString(COUNTRY);
            if(json.has(LANDMARK))landmark = json.getString(LANDMARK);
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
            jsonMain.put(PHONE, phone);
            jsonMain.put(ADDRESS, address);
            jsonMain.put(STATE, state);
            jsonMain.put(LATITUDE, latitude+"");
            jsonMain.put(LONGITUDE, longitude+"");
            jsonMain.put(CITY, city);
            jsonMain.put(PINCODE, pincode);
            jsonMain.put(STREET, street);
            jsonMain.put(COUNTRY, country);
            jsonMain.put(LANDMARK, landmark);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
