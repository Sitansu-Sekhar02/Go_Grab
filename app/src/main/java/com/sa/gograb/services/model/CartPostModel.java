package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class CartPostModel implements Serializable {
    private final String TAG = "CartPostModel";

    private final String
            ID                     = "id",
            PRODUCT_ID             = "product_id",
            VARIATION_ID           = "variation_id",
            QUANTITY               = "quantity";

    String
            id                    = null,
            productId             = null,
            variationId           = null,
            quantity              = null;

    public CartPostModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getVariationId() {
        return variationId;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(ID)){this.id = json.getString(ID);}
            if(json.has(PRODUCT_ID)){this.productId = json.getString(PRODUCT_ID);}
            if(json.has(VARIATION_ID)){this.variationId = json.getString(VARIATION_ID);}
            if(json.has(QUANTITY)){this.quantity = json.getString(QUANTITY);}

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
            jsonMain.put(PRODUCT_ID, this.productId);
            jsonMain.put(VARIATION_ID, this.variationId);
            jsonMain.put(QUANTITY, this.quantity);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
