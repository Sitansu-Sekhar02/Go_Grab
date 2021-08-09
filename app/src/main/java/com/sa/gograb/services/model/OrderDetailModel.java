package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class OrderDetailModel implements Serializable {
    private final String TAG = "OrderDetailModel";
    private final String
            ID            = "id",
            PRODUCT_ID    = "product_id",
            NAME          = "name",
            IMAGE         = "image",
            CURRENCY      = "currency",
            QUANTITY      = "quantity",
            PRICE         = "price",
            OFFER         = "offer";

    String
            id           = null,
            product_id   = null,
            name         = null,
            currency     = null,
            image        = null;
    private double price =0.0;
    private int quantity =0;


    public OrderDetailModel(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
            this.id = json.getString(ID);
            if(json.has(PRODUCT_ID))this.product_id = json.getString(PRODUCT_ID);
            if(json.has(NAME))this.name = json.getString(NAME);
            if(json.has(CURRENCY))this.currency = json.getString(CURRENCY);
            if(json.has(IMAGE))this.image = json.getString(IMAGE);
            if(json.has(QUANTITY)){
                try {
                    this.quantity = json.getInt(QUANTITY);
                }catch (Exception e){this.quantity =0;}
            }

            if(json.has(PRICE)){
                try {
                    this.price = json.getDouble(PRICE);
                }catch (Exception e){this.price =0.0;}
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
            jsonMain.put(ID, this.id);
            jsonMain.put(PRODUCT_ID, this.product_id);
            jsonMain.put(NAME, this.name);
            jsonMain.put(CURRENCY, this.currency);
            jsonMain.put(IMAGE, this.image);
            jsonMain.put(PRICE, this.price);
            jsonMain.put(QUANTITY, this.quantity);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
