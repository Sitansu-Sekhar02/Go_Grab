package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class WishlistPostModel implements Serializable {
    private final String TAG = "WishlistPostModel";

    private final String
            ID                     = "id",
            PRODUCT_ID             = "id_product",
            VARIATION_ID           = "id_variation",
            INDEX                  = "index",
            SIZE                   = "size",
            STATUS                 = "status";

    String
            id                    = null,
            productId             = null,
            variationId           = null,
            index                 = null,
            size                  = null,
            status                = null;

    public WishlistPostModel(){}

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(INDEX)){this.index = json.getString(INDEX);}
            if(json.has(SIZE)){this.size = json.getString(SIZE);}
            if(json.has(ID)){this.id = json.getString(ID);}
            if(json.has(PRODUCT_ID)){this.productId = json.getString(PRODUCT_ID);}
            if(json.has(VARIATION_ID)){this.variationId = json.getString(VARIATION_ID);}
            if(json.has(STATUS)){this.status = json.getString(STATUS);}

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
            jsonMain.put(INDEX, this.index);
            jsonMain.put(SIZE, this.size);
            jsonMain.put(PRODUCT_ID, this.productId);
            jsonMain.put(VARIATION_ID, this.variationId);
            jsonMain.put(STATUS, this.status);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
