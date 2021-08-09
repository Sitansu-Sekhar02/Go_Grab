package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class FeedbackPostModel implements Serializable{

    private final String
            TAG             = "FeedbackPostModel";

    private final String
            ORDER_ID            = "order_id",
            RATING              = "rating",
            COMMENT             = "comment";

    String
            orderId         = null,
            rating          = null,
            comment         = null;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean toObject(String jsonObject){

        try{
            JSONObject json = new JSONObject(jsonObject);
            this.orderId = json.getString(ORDER_ID);
            this.rating = json.getString(RATING);
            this.comment = json.getString(COMMENT);

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

            jsonMain.put(ORDER_ID, this.orderId);
            jsonMain.put(RATING, this.rating);
            jsonMain.put(COMMENT, this.comment);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
