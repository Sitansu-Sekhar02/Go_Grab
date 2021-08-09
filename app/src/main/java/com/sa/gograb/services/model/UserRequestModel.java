package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class UserRequestModel implements Serializable {
    private final String TAG = "UserRequestModel";
    private final String
            ID                  = "id",
            CAT_ID              = "category_id",
            CAT_NAME            = "category_name",
            CAT_ICON            = "category_icon",
            SUB_CAT_ID          = "sub_category_id",
            SUB_CAT_NAME        = "sub_category_name",
            SUB_CAT_ICON        = "sub_category_image",
            CHILD_SUB_CAT_ID    = "child_sub_category_id",
            CHILD_SUB_CAT_NAME  = "child_sub_category_name",
            CHILD_SUB_CAT_ICON  = "child_sub_category_image",
            ADDRESS             = "address",
            PICK_ADDRS_ID       = "pickup_address_id",
            PICK_ADDRS          = "pickup_address",
            DROP_ADDRS_ID       = "delivery_address_id",
            DROP_ADDRS          = "delivery_address",
            CITY_ID             = "city_id",
            CITY_NAME           = "city_name",
            DATE                = "date",
            TIME                = "time",
            FLAG                = "flag",
            COUNT               = "vendor_count",
            CURRENCY            = "currency",
            PRICE               = "price",
            COMMENTS            = "comments",
            ADDRESS_ID          = "address_id";

    String
            id                   = null,
            categoryId           = null,
            subCategoryId        = null,
            childSubCategoryId   = null,
            categoryName         = null,
            subCategoryName      = null,
            childSubCategoryName = null,
            categoryIcon         = null,
            subCategoryIcon      = null,
            childSubCategoryIcon = null,
            address              = null,
            pickupAddressId      = null,
            pickupAddress        = null,
            deliveryAddressId    = null,
            deliveryAddress      = null,
            cityId               = null,
            cityName             = null,
            date                 = null,
            time                 = null,
            flag                 = null,
            count                = null,
            currency             = null,
            price                = null,
            comments             = null,
            addressId            = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getChildSubCategoryId() {
        return childSubCategoryId;
    }

    public void setChildSubCategoryId(String childSubCategoryId) {
        this.childSubCategoryId = childSubCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getChildSubCategoryName() {
        return childSubCategoryName;
    }

    public void setChildSubCategoryName(String childSubCategoryName) {
        this.childSubCategoryName = childSubCategoryName;
    }

    public String getSubCategoryIcon() {
        return subCategoryIcon;
    }

    public void setSubCategoryIcon(String subCategoryIcon) {
        this.subCategoryIcon = subCategoryIcon;
    }

    public String getChildSubCategoryIcon() {
        return childSubCategoryIcon;
    }

    public void setChildSubCategoryIcon(String childSubCategoryIcon) {
        this.childSubCategoryIcon = childSubCategoryIcon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPickupAddressId() {
        return pickupAddressId;
    }

    public void setPickupAddressId(String pickupAddressId) {
        this.pickupAddressId = pickupAddressId;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(ID)){id = json.getString(ID);}
            if(json.has(CAT_ID)){categoryId = json.getString(CAT_ID);}
            if(json.has(SUB_CAT_ID)){subCategoryId = json.getString(SUB_CAT_ID);}
            if(json.has(CHILD_SUB_CAT_ID)){childSubCategoryId = json.getString(CHILD_SUB_CAT_ID);}
            if(json.has(CAT_NAME)){categoryName = json.getString(CAT_NAME);}
            if(json.has(SUB_CAT_NAME)){subCategoryName = json.getString(SUB_CAT_NAME);}
            if(json.has(CHILD_SUB_CAT_NAME)){childSubCategoryName = json.getString(CHILD_SUB_CAT_NAME);}
            if(json.has(CAT_ICON)){categoryIcon = json.getString(CAT_ICON);}
            if(json.has(SUB_CAT_ICON)){subCategoryIcon = json.getString(SUB_CAT_ICON);}
            if(json.has(CHILD_SUB_CAT_ICON)){childSubCategoryIcon = json.getString(CHILD_SUB_CAT_ICON);}
            if(json.has(DATE)){date = json.getString(DATE);}
            if(json.has(TIME)){ time = json.getString(TIME);}
            if(json.has(PRICE)){price = json.getString(PRICE);}
            if(json.has(COMMENTS)){comments = json.getString(COMMENTS);}
            if(json.has(ADDRESS_ID)){addressId = json.getString(ADDRESS_ID);}
            if(json.has(CITY_ID)){this.cityId = json.getString(CITY_ID);}
            if(json.has(CITY_NAME)){this.cityName = json.getString(CITY_NAME);}
            if(json.has(ADDRESS)){this.address = json.getString(ADDRESS);}
            if(json.has(PICK_ADDRS_ID)){pickupAddressId = json.getString(PICK_ADDRS_ID);}
            if(json.has(PICK_ADDRS)){pickupAddress = json.getString(PICK_ADDRS);}
            if(json.has(DROP_ADDRS_ID)){deliveryAddressId = json.getString(DROP_ADDRS_ID);}
            if(json.has(DROP_ADDRS)){deliveryAddress = json.getString(DROP_ADDRS);}
            if(json.has(FLAG)){this.flag = json.getString(FLAG);}
            if(json.has(COUNT)){this.count = json.getString(COUNT);}
            if(json.has(CURRENCY)){ currency = json.getString(CURRENCY);}

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
            jsonMain.put(ID, id);
            jsonMain.put(CAT_ID, categoryId);
            jsonMain.put(SUB_CAT_ID, subCategoryId);
            jsonMain.put(CHILD_SUB_CAT_ID, childSubCategoryId);
            jsonMain.put(CAT_NAME, categoryName);
            jsonMain.put(SUB_CAT_NAME, subCategoryName);
            jsonMain.put(CHILD_SUB_CAT_NAME, childSubCategoryName);
            jsonMain.put(CAT_ICON, categoryIcon);
            jsonMain.put(SUB_CAT_ICON, subCategoryIcon);
            jsonMain.put(CHILD_SUB_CAT_ICON, childSubCategoryIcon);
            jsonMain.put(DATE, date);
            jsonMain.put(TIME, time);
            jsonMain.put(PRICE, price);
            jsonMain.put(COMMENTS, comments);
            jsonMain.put(ADDRESS_ID, addressId);
            jsonMain.put(CITY_NAME, this.cityName);
            jsonMain.put(CITY_ID, this.cityId);
            jsonMain.put(ADDRESS, address);
            jsonMain.put(PICK_ADDRS_ID, pickupAddressId);
            jsonMain.put(PICK_ADDRS, pickupAddress);
            jsonMain.put(DROP_ADDRS_ID, deliveryAddressId);
            jsonMain.put(DROP_ADDRS, deliveryAddress);
            jsonMain.put(FLAG, flag);
            jsonMain.put(COUNT, count);
            jsonMain.put(CURRENCY, currency);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
