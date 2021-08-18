package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class OrderModel implements Serializable {

    private final String TAG = "OrderModel";

    private final String
                    INDEX                        = "index",
                    SIZE                         ="size",
                    ORDER_ID                     = "order_id",
                    GRAND_TOTAL                  = "grand_total",
                    COUPON_DIS                   = "coupan_discount",
                    VAT                          = "vat",
                    PACKING_CHARGE               = "packing_charges",
                    SUB_TOTAL                    = "sub_total",
                    QTY                          = "quantity",
                    PAYMENT_TYPE                 = "payment_type",
                    DISTANCE                      = "distance",
                    CREATED_ON                   = "created_on",
                    REST_ID                      = "restaurant_id",
                    REST_LATITUDE                = "rest_laltitude",
                    REST_LONGITUDE               = "rest_longitude",
                    REST_MBL_NO                  = "rest_mobile_number",
                    REST_PREP_TIME               = "rest_preparation_time",
                    REST_NAME                    = "rest_full_name",
                    REST_LOGO                    = "rest_logo",
                    RATING_COUNT                 = "rating_count",
                    CURRENCY                     = "currency",
                    RATING                       = "rating",
                    STATUS                       = "status",
                    STATUS_TITLE                 = "status_title",
                    COUNTS                       = "counts",
                    ORDER_DETAILS                = "order_detail";


    private String
             id                  = null,
             index               =null,
             size                =null,
            grand_total          = null,
            created_on           = null,
            coupan_discount      = null,
            distance             = null,
            order_id             = null,
            rest_full_name       = null,
            vat                  = null,
            packing_charges      = null,
            sub_total            = null,
            status_title         = null,
            status               = null,
            quantity             = null,
            payment_type         = null,
            restaurant_id        = null,
            currency             = null,
            rest_laltitude       = null,
            rest_longitude       = null,
            rest_mobile_number   = null,
            rest_preparation_time  = null,
            rest_logo              = null,
            rating_count           = null,
            rating                 = null,
            counts                 = null,
            delivery_on            = null;




    private OrderDetailListModel
            order_details = null;



    public OrderModel(){}


    OrderSendAddressModel deliveryAddress, billingAddress ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRest_full_name() {
        return rest_full_name;
    }

    public void setRest_full_name(String rest_full_name) {
        this.rest_full_name = rest_full_name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getStatus_title() {
        return status_title;
    }

    public void setStatus_title(String status_title) {
        this.status_title = status_title;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    public OrderDetailListModel getOrder_details() {
        return order_details;
    }

    public void setOrder_details(OrderDetailListModel order_details) {
        this.order_details = order_details;
    }

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCoupan_discount() {
        return coupan_discount;
    }

    public void setCoupan_discount(String coupan_discount) {
        this.coupan_discount = coupan_discount;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getPacking_charges() {
        return packing_charges;
    }

    public void setPacking_charges(String packing_charges) {
        this.packing_charges = packing_charges;
    }

    public String getSub_total() {
        return sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getRest_laltitude() {
        return rest_laltitude;
    }

    public void setRest_laltitude(String rest_laltitude) {
        this.rest_laltitude = rest_laltitude;
    }

    public String getRest_longitude() {
        return rest_longitude;
    }

    public void setRest_longitude(String rest_longitude) {
        this.rest_longitude = rest_longitude;
    }

    public String getRest_mobile_number() {
        return rest_mobile_number;
    }

    public void setRest_mobile_number(String rest_mobile_number) {
        this.rest_mobile_number = rest_mobile_number;
    }

    public String getRest_preparation_time() {
        return rest_preparation_time;
    }

    public void setRest_preparation_time(String rest_preparation_time) {
        this.rest_preparation_time = rest_preparation_time;
    }

    public String getRest_logo() {
        return rest_logo;
    }

    public void setRest_logo(String rest_logo) {
        this.rest_logo = rest_logo;
    }

    public String getRating_count() {
        return rating_count;
    }

    public void setRating_count(String rating_count) {
        this.rating_count = rating_count;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public OrderSendAddressModel getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(OrderSendAddressModel deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public OrderSendAddressModel getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(OrderSendAddressModel billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }


    public String getDelivery_on() {
        return delivery_on;
    }

    public void setDelivery_on(String delivery_on) {
        this.delivery_on = delivery_on;
    }


    public String getStatus() {
        return status;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if (json.has(INDEX)) {
                index = json.getString(INDEX);
            }
            if (json.has(SIZE)) {
                size = json.getString(SIZE);

            } if (json.has(REST_NAME)) {
                rest_full_name = json.getString(REST_NAME);

            }if (json.has(ORDER_ID)) {
                order_id = json.getString(ORDER_ID);
            }if (json.has(GRAND_TOTAL)) {
                grand_total = json.getString(GRAND_TOTAL);
            }
            if (json.has(DISTANCE)) {
                distance = json.getString(DISTANCE);
            }if (json.has(COUPON_DIS)) {
                coupan_discount = json.getString(COUPON_DIS);
            }if (json.has(VAT)) {
                vat = json.getString(VAT);
            }if (json.has(STATUS_TITLE)) {
                status_title = json.getString(STATUS_TITLE);
            }if (json.has(CURRENCY)) {
                currency = json.getString(CURRENCY);
            }if (json.has(PACKING_CHARGE)) {
                packing_charges = json.getString(PACKING_CHARGE);
            }if (json.has(SUB_TOTAL)) {
                sub_total = json.getString(SUB_TOTAL);
            }if (json.has(QTY)) {
                quantity = json.getString(QTY);
            }if (json.has(PAYMENT_TYPE)) {
                payment_type = json.getString(PAYMENT_TYPE);
            }if (json.has(CREATED_ON)) {
                created_on = json.getString(CREATED_ON);
            }if (json.has(REST_ID)) {
                restaurant_id = json.getString(REST_ID);
            }if (json.has(REST_LATITUDE)) {
                rest_laltitude = json.getString(REST_LATITUDE);
            }if (json.has(REST_LONGITUDE)) {
                rest_longitude = json.getString(REST_LONGITUDE);
            }if (json.has(REST_LOGO)) {
                rest_logo = json.getString(REST_LOGO);
            }if (json.has(REST_MBL_NO)) {
                rest_mobile_number = json.getString(REST_MBL_NO);
            }if (json.has(REST_PREP_TIME)) {
                rest_preparation_time = json.getString(REST_PREP_TIME);
            }if (json.has(RATING_COUNT)) {
                rating_count = json.getString(RATING_COUNT);
            }if (json.has(RATING)) {
                rating = json.getString(RATING);
            }if (json.has(COUNTS)) {
                counts = json.getString(COUNTS);
            }
            if (json.has(STATUS)) {
                status =json.getString(STATUS);
            }

            if(json.has(ORDER_DETAILS)) {
                JSONArray array = json.getJSONArray(ORDER_DETAILS);
                OrderDetailListModel listModelLocal = new OrderDetailListModel();
                if(listModelLocal.toObject(array)){this.order_details = listModelLocal;}
                else{this.order_details = null;}
            }




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
            //jsonMain.put(ID, this.id);
            jsonMain.put(INDEX, this.index);
            jsonMain.put(SIZE, this.size);
            jsonMain.put(ORDER_ID, this.order_id);
            jsonMain.put(REST_NAME, this.rest_full_name);
            jsonMain.put(GRAND_TOTAL, grand_total);
            jsonMain.put(COUPON_DIS, coupan_discount);
            jsonMain.put(DISTANCE, distance);
            jsonMain.put(VAT, vat);
            jsonMain.put(PACKING_CHARGE, packing_charges);
            jsonMain.put(CURRENCY, currency);
            jsonMain.put(STATUS_TITLE, status_title);
            jsonMain.put(STATUS, status);
            jsonMain.put(SUB_TOTAL, sub_total);
            jsonMain.put(QTY, quantity);
            jsonMain.put(PAYMENT_TYPE, payment_type);
            jsonMain.put(REST_ID, restaurant_id);
            jsonMain.put(REST_LATITUDE, rest_laltitude);
            jsonMain.put(REST_LONGITUDE, rest_longitude);
            jsonMain.put(REST_MBL_NO, rest_mobile_number);
            jsonMain.put(REST_LOGO, rest_logo);
            jsonMain.put(REST_PREP_TIME, rest_preparation_time);
            jsonMain.put(CREATED_ON, created_on);
            jsonMain.put(RATING, rating);
            jsonMain.put(RATING_COUNT, rating_count);
            jsonMain.put(COUNTS, counts);
            jsonMain.put(ORDER_DETAILS, order_details!=null?new JSONArray(order_details.toString(true)):null);


           // jsonMain.put(ORDER_STATUS_HISTORY, this.orderStatusListModel!=null?new JSONObject(this.orderStatusListModel.toString()):new JSONObject());


            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
