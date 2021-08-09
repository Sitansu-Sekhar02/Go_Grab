package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class UpdateCartPostModel implements Serializable {
    private final String TAG = "UpdateCartPostModel";

    private final String
            CART_DETAIL_ID         = "cart_detail_id",
            QUANTITY               = "quantity";

    String
            cartDetailId          = null,
            quantity              = null;

    public UpdateCartPostModel(){}

    public String getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(String cartDetailId) {
        this.cartDetailId = cartDetailId;
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
            if(json.has(CART_DETAIL_ID)){this.cartDetailId = json.getString(CART_DETAIL_ID);}
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
            jsonMain.put(CART_DETAIL_ID, this.cartDetailId);
            jsonMain.put(QUANTITY, this.quantity);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
