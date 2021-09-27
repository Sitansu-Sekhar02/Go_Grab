package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class PlanModel implements Serializable {
    private final String TAG = "PlanModel";
    private final String
            ID                          = "id",
            ORDER_DETAIL_ID             = "order_detail_id",
            ORDER_ID                    = "order_id",
            PACKAGE_NAME                = "package_name",
            CAR_NO                      = "car_no",
            FROM_DATE                   = "from",
            TO_DATE                     = "to";

    String id                          =null,
            orderDetailId               =null,
            orderId                     = null,
            packageName                 = null,
            carNo                       = null,
            fromDate                    = null,
            toDate                      = null;

    public PlanModel(){}

    public PlanModel(String id, String orderDetailId, String orderId, String packageName, String carNo, String fromDate, String toDate) {
        this.id = id;
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.packageName = packageName;
        this.carNo = carNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
           if (json.has(ID))this.id = json.getString(ID);
           if (json.has(ORDER_DETAIL_ID))this.orderDetailId = json.getString(ORDER_DETAIL_ID);
           if (json.has(ORDER_ID))this.orderId = json.getString(ORDER_ID);
           if (json.has(PACKAGE_NAME))this.packageName = json.getString(PACKAGE_NAME);
           if (json.has(CAR_NO))this.carNo = json.getString(CAR_NO);
           if (json.has(FROM_DATE))this.fromDate = json.getString(FROM_DATE);
           if (json.has(TO_DATE))this.toDate = json.getString(TO_DATE);

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
            jsonMain.put(ID, this.id);
            jsonMain.put(ORDER_DETAIL_ID, this.orderDetailId);
            jsonMain.put(ORDER_ID, this.orderId);
            jsonMain.put(PACKAGE_NAME, this.packageName);
            jsonMain.put(CAR_NO, this.carNo);
            jsonMain.put(FROM_DATE, this.fromDate);
            jsonMain.put(TO_DATE, this.toDate);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
