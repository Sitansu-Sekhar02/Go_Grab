package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class UserAddressModel implements Serializable {
    private final String TAG = "UserAddressModel";

    private final String
            ID                   = "id",
            F_NAME               = "first_name",
            L_NAME               = "last_name",
            ADDRESS              = "address",
            LATITUDE             = "latitude",
            LONGITUDE            = "longitude",
            NUMBER               = "phone_number",
            EMAIL                = "email_id",
            COMM_NUMBER          = "communication_number",
            LANDMARK             = "landmark",
            TYPE                 = "default_address",
            ADDRESS_TYPE         = "address_type",
            PHONE                = "phone",
            HOUSE_NO             = "house_no",
            CITY_ID              = "city_id",
            CITY_NAME            = "city_title",
            PINCODE              = "postal_code",
            ACTION               = "action";
    ;

    String
            id               = null,
            firstName        = null,
            lastName         = null,
            address          = null,
            latitude         = null,
            longitude        = null,
            number           = null,
            email            = null,
            commNumber       = null,
            type             = null,
            landmark         = null,
            addressType      = null,
            phone            = null,
            houseNo          = null,
            cityId           = null,
            action           = null,
            pincode          = null,
            cityName         = null;

    public UserAddressModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCommNumber() {
        return commNumber;
    }

    public void setCommNumber(String commNumber) {
        this.commNumber = commNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(ID))id = json.getString(ID);
            if(json.has(ADDRESS))address = json.getString(ADDRESS);
            if(json.has(LATITUDE))latitude = json.getString(LATITUDE);
            if(json.has(LONGITUDE))longitude = json.getString(LONGITUDE);
            if(json.has(F_NAME))firstName = json.getString(F_NAME);
            if(json.has(L_NAME))lastName = json.getString(L_NAME);
            if(json.has(EMAIL))email = json.getString(EMAIL);
            if(json.has(PINCODE))pincode = json.getString(PINCODE);
            if(json.has(NUMBER))number = json.getString(NUMBER);
            if(json.has(COMM_NUMBER))commNumber = json.getString(COMM_NUMBER);
            if(json.has(TYPE))type = json.getString(TYPE);
            if(json.has(LANDMARK))landmark = json.getString(LANDMARK);
            if(json.has(ADDRESS_TYPE))addressType = json.getString(ADDRESS_TYPE);
            if(json.has(PHONE))phone = json.getString(PHONE);
            if(json.has(HOUSE_NO))houseNo = json.getString(HOUSE_NO);
            if(json.has(CITY_ID))cityId = json.getString(CITY_ID);
            if(json.has(ACTION))action = json.getString(ACTION);
            if(json.has(CITY_NAME))cityName = json.getString(CITY_NAME);


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
            jsonMain.put(ADDRESS, address);
            jsonMain.put(LATITUDE, latitude);
            jsonMain.put(LONGITUDE, longitude);
            jsonMain.put(F_NAME, firstName);
            jsonMain.put(L_NAME, lastName);
            jsonMain.put(EMAIL, email);
            jsonMain.put(EMAIL, email);
            jsonMain.put(PINCODE, pincode);
            jsonMain.put(NUMBER, number);
            jsonMain.put(COMM_NUMBER, commNumber);
            jsonMain.put(TYPE, type);
            jsonMain.put(LANDMARK, landmark);
            jsonMain.put(ADDRESS_TYPE, addressType);
            jsonMain.put(PHONE, phone);
            jsonMain.put(HOUSE_NO, houseNo);
            jsonMain.put(CITY_ID, cityId);
            jsonMain.put(ACTION, action);
            jsonMain.put(CITY_NAME, cityName);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
