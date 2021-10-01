package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class StatusModel implements Serializable {
    private final String TAG = "StatusModel";

    private final String
            MESSAGE             = "message",
            TOKEN               = "token",
            TITLE               = "title",
            ID                  = "id",
            STATUS              = "status",
            CART_COUNT          = "cart_count",
            STATUS_CODE         = "code",
            EXTRA               = "extra",
            ORDER_ID            = "order_number",
            TRANSACTION_ID      = "transcation_id",
            TOTAL               = "total";


    private String message = null,token = null, code = null,cartCount="0", extra=null, id=null, title = null, orderId = null, transcationId = null, total = null;
    private boolean status = false;


    public StatusModel(){
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTranscationId() {
        return transcationId;
    }

    public void setTranscationId(String transcationId) {
        this.transcationId = transcationId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCartCount() {
        return cartCount;
    }

    public void setCartCount(String cartCount) {
        this.cartCount = cartCount;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(STATUS))status = json.getBoolean(STATUS);
            if(json.has(ID))id = json.getString(ID);
            if(json.has(TITLE))title = json.getString(TITLE);
            if(json.has(TOKEN))token = json.getString(TOKEN);
            if(json.has(MESSAGE))message = json.getString(MESSAGE);
            if(json.has(EXTRA))extra = json.getString(EXTRA);
            if(json.has(STATUS_CODE))code = json.getString(STATUS_CODE);
            if(json.has(ORDER_ID))orderId = json.getString(ORDER_ID);
            if(json.has(TRANSACTION_ID))transcationId = json.getString(TRANSACTION_ID);
            if(json.has(TOTAL))total = json.getString(TOTAL);
            if(json.has(CART_COUNT))cartCount = json.getString(CART_COUNT);


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
            jsonMain.put(TITLE, title);
            jsonMain.put(MESSAGE, message);
            jsonMain.put(STATUS, status);
            jsonMain.put(EXTRA, extra);
            jsonMain.put(STATUS_CODE, code);
            jsonMain.put(ORDER_ID, orderId);
            jsonMain.put(TRANSACTION_ID, transcationId);
            jsonMain.put(TOTAL, total);
            jsonMain.put(TOKEN, token);
            jsonMain.put(CART_COUNT, cartCount);


            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
