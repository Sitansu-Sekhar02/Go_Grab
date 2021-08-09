package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

public class ProductListMainModel {
    private final String TAG = "SubCatMainModel";

    private final String
            MESSAGE                    = "status",
            STATUS                     = "status_bool",
            RESPONSE                   = "response";

    private String
            message                    = null;
    private boolean
            isStatus                  = false;

    ProductListSubModel
            productListSubModel            = null;

    public ProductListMainModel(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public ProductListSubModel getProductListSubModel() {
        return productListSubModel;
    }

    public void setProductListSubModel(ProductListSubModel productListSubModel) {
        this.productListSubModel = productListSubModel;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            if(json.has(MESSAGE)){this.message = json.getString(MESSAGE);}
            if(json.has(STATUS)){this.isStatus = json.getBoolean(STATUS);}
            if(json.has(RESPONSE)){
                ProductListSubModel statusModel = new ProductListSubModel();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1 = json.getJSONObject(RESPONSE);
                if(jsonObject1 != null){statusModel.toObject(jsonObject1.toString());}
                productListSubModel = statusModel;
            }

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
            jsonMain.put(STATUS, isStatus);
            jsonMain.put(MESSAGE, message);
            jsonMain.put(RESPONSE, productListSubModel != null ? new JSONObject(this.productListSubModel.toString()) : new JSONObject());

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}


