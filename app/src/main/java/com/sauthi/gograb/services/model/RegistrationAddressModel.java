package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class RegistrationAddressModel implements Serializable {
    private final String TAG = "RegAddressModel";
    private final String
            CONTACT_NUMBER              = "contact_number",
            ADDRESS                     = "address",
            LANDLINE                    = "landline",
            LATITUDE                    = "latitude",
            LONGITUDE                   = "longitude",
            CITY                        = "city",
            TYPE                        = "type";

    String
            contactNumber    = null,
            address          = null,
            landline         = null,
            city             = null,
            type             = null;

    double
            latitude    = 0.0,
            longitude   = 0.0;

    public RegistrationAddressModel(){}

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(CONTACT_NUMBER))contactNumber = json.getString(CONTACT_NUMBER);
            if(json.has(ADDRESS))address = json.getString(ADDRESS);
            if(json.has(LANDLINE))landline = json.getString(LANDLINE);
            if(json.has(LATITUDE))latitude = json.getDouble(LATITUDE);
            if(json.has(LONGITUDE))longitude = json.getDouble(LONGITUDE);
            if(json.has(CITY))city = json.getString(CITY);
            if(json.has(TYPE)) type = json.getString(TYPE);

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
            jsonMain.put(CONTACT_NUMBER, contactNumber);
            jsonMain.put(ADDRESS, address);
            jsonMain.put(LANDLINE, landline);
            jsonMain.put(LATITUDE, latitude+"");
            jsonMain.put(LONGITUDE, longitude+"");
            jsonMain.put(CITY, city);
            jsonMain.put(TYPE, type);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
