package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class OrderDetailModel implements Serializable {
    private final String TAG = "OrderDetailModel";
    private final String
            ID            = "id",
            MENU_ID       = "menu_id",
            PRODUCT_ID    = "product_id",
            NAME          = "name",
            IMAGE         = "image",
            CURRENCY      = "currency",
            QUANTITY      = "quantity",
            PRICE         = "price",
            OFFER         = "offer";

    String
            id           = null,
            menu_id      = null,
            product_id   = null,
            name         = null,
            currency     = null,
            image        = null,
            quantity     = null,
            price        =null;


    public OrderDetailModel(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
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

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(ID))this.id = json.getString(ID);
            if(json.has(MENU_ID))this.menu_id = json.getString(MENU_ID);
            if(json.has(PRODUCT_ID))this.product_id = json.getString(PRODUCT_ID);
            if(json.has(NAME))this.name = json.getString(NAME);
            if(json.has(CURRENCY))this.currency = json.getString(CURRENCY);
            if(json.has(IMAGE))this.image = json.getString(IMAGE);
            if(json.has(PRICE))this.price = json.getString(PRICE);
            if(json.has(QUANTITY))this.quantity = json.getString(QUANTITY);


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
            jsonMain.put(MENU_ID, this.menu_id);
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
