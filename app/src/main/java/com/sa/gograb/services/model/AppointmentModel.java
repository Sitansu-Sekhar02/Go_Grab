package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class AppointmentModel implements Serializable {

    private final String TAG = "AppointmentModel";

    private final String
            BOOKING_ID = "booking_id",
            USER_ID = "user_id",
            USER_NAME = "user_name",
            FIRST_NAME      = "first_name",
            LAST_NAME       = "last_name",
            MOBILE_NUMBER = "mobile_number",
            EMAIL_ID = "email_id",
            GENDER = "gender",
            CATEGORY_TITLE = "category_title",
            ICON = "icon",
            SERVICE_TITLE = "service_title",
            DURATION_TITLE = "duration_title",
            NO_OF_USER = "no_of_user",
            PRICE = "price",
            CURRENCY = "currency",
            OFFER = "offer",
            TAX = "tax",
            TOTAL_COST = "total_cost",
            BOOKING_DATE = "booking_date",
            BOOKING_TIME = "booking_time",
            ADDRESS = "address",
            LATITDE = "user_latitude",
            LONGITUDE = "user_longitude",
            CITY_TITLE = "city_title",
            PINCODE = "pincode",
            GEOCODE = "geocode",
            PAID_BY = "paid_by",
            BOOKING_STATUS_ID = "booking_status_id",
            BOOKING_STATUS = "booking_status";

    String
            booking_id = null,
            user_id = null,
            user_name = null,
            firstName    = null,
            lastName        = null,
            mobile_number = null,
            email_id = null,
            gender = null,
            category_title = null,
            icon = null,
            service_title = null,
            duration_title = null,
            no_of_user = null,
            price = null,
            currency = null,
            offer = null,
            tax = null,
            total_cost = null,
            lat = null,
            longitude = null,
            booking_date = null,
            booking_time = null,
            address = null,
            city_title = null,
            pincode = null,
            geocode = null,
            paid_by = null,
            booking_status_id = null,
            booking_status = null;

    public AppointmentModel() {
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getService_title() {
        return service_title;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }

    public String getDuration_title() {
        return duration_title;
    }

    public void setDuration_title(String duration_title) {
        this.duration_title = duration_title;
    }

    public String getNo_of_user() {
        return no_of_user;
    }

    public void setNo_of_user(String no_of_user) {
        this.no_of_user = no_of_user;
    }

    public String getPrice() {
        return price;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity_title() {
        return city_title;
    }

    public void setCity_title(String city_title) {
        this.city_title = city_title;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getGeocode() {
        return geocode;
    }

    public void setGeocode(String geocode) {
        this.geocode = geocode;
    }

    public String getPaid_by() {
        return paid_by;
    }

    public void setPaid_by(String paid_by) {
        this.paid_by = paid_by;
    }

    public String getBooking_status_id() {
        return booking_status_id;
    }

    public void setBooking_status_id(String booking_status_id) {
        this.booking_status_id = booking_status_id;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);
            booking_id = json.getString(BOOKING_ID);
            if (json.has(USER_ID)) user_id = json.getString(USER_ID);
            if (json.has(USER_NAME)) user_name = json.getString(USER_NAME);
            if (json.has(FIRST_NAME)) firstName = json.getString(FIRST_NAME);
            if (json.has(LAST_NAME)) lastName = json.getString(LAST_NAME);
            if (json.has(MOBILE_NUMBER)) mobile_number = json.getString(MOBILE_NUMBER);
            if (json.has(EMAIL_ID)) email_id = json.getString(EMAIL_ID);
            if (json.has(GENDER)) gender = json.getString(GENDER);
            if (json.has(CATEGORY_TITLE)) category_title = json.getString(CATEGORY_TITLE);
            if (json.has(ICON)) icon = json.getString(ICON);
            if (json.has(SERVICE_TITLE)) service_title = json.getString(SERVICE_TITLE);
            if (json.has(DURATION_TITLE)) this.duration_title = json.getString(DURATION_TITLE);
            if (json.has(NO_OF_USER)) this.no_of_user = json.getString(NO_OF_USER);
            if (json.has(PRICE)) this.price = json.getString(PRICE);
            if (json.has(CURRENCY)) this.currency = json.getString(CURRENCY);
            if (json.has(OFFER)) this.offer = json.getString(OFFER);
            if (json.has(TAX)) this.tax = json.getString(TAX);
            if (json.has(TOTAL_COST)) this.total_cost = json.getString(TOTAL_COST);
            if (json.has(BOOKING_DATE)) this.booking_date = json.getString(BOOKING_DATE);
            if (json.has(BOOKING_TIME)) this.booking_time = json.getString(BOOKING_TIME);
            if (json.has(ADDRESS)) this.address = json.getString(ADDRESS);
            if (json.has(LATITDE)) this.lat = json.getString(LATITDE);
            if (json.has(LONGITUDE)) this.longitude = json.getString(LONGITUDE);
            if (json.has(CITY_TITLE)) this.city_title = json.getString(CITY_TITLE);
            if (json.has(PINCODE)) this.pincode = json.getString(PINCODE);
            if (json.has(GEOCODE)) this.geocode = json.getString(GEOCODE);
            if (json.has(PAID_BY)) this.paid_by = json.getString(PAID_BY);
            if (json.has(BOOKING_STATUS_ID))
                this.booking_status_id = json.getString(BOOKING_STATUS_ID);
            if (json.has(BOOKING_STATUS)) this.booking_status = json.getString(BOOKING_STATUS);

            return true;
        } catch (Exception ex) {
            Log.d(TAG, "Json Exception : " + ex);
        }
        return false;
    }

    @Override
    public String toString() {
        String returnString = null;
        try {
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(BOOKING_ID, booking_id);
            jsonMain.put(USER_ID, user_id);
            jsonMain.put(USER_NAME, user_name);
            jsonMain.put(FIRST_NAME, firstName);
            jsonMain.put(LAST_NAME, lastName);
            jsonMain.put(MOBILE_NUMBER, mobile_number);
            jsonMain.put(EMAIL_ID, email_id);
            jsonMain.put(GENDER, gender);
            jsonMain.put(CATEGORY_TITLE, category_title);
            jsonMain.put(ICON, icon);
            jsonMain.put(SERVICE_TITLE, service_title);
            jsonMain.put(DURATION_TITLE, this.duration_title);
            jsonMain.put(NO_OF_USER, this.no_of_user);
            jsonMain.put(PRICE, this.price);
            jsonMain.put(CURRENCY, this.currency);
            jsonMain.put(OFFER, this.offer);
            jsonMain.put(TAX, this.tax);
            jsonMain.put(TOTAL_COST, this.total_cost);
            jsonMain.put(BOOKING_DATE, this.booking_date);
            jsonMain.put(BOOKING_TIME, this.booking_time);
            jsonMain.put(ADDRESS, this.address);
            jsonMain.put(LATITDE, this.lat);
            jsonMain.put(LONGITUDE, this.longitude);
            jsonMain.put(CITY_TITLE, this.city_title);
            jsonMain.put(PINCODE, this.pincode);
            jsonMain.put(GEOCODE, this.geocode);
            jsonMain.put(PAID_BY, this.paid_by);
            jsonMain.put(BOOKING_STATUS_ID, this.booking_status_id);
            jsonMain.put(BOOKING_STATUS, this.booking_status);

            returnString = jsonMain.toString();
            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
