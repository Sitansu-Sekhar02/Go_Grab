package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class RatingNFeedbackModel implements Serializable {

    private final String TAG = "RatingNFeedbackModel";

    private final String
                    RESTAURANT_ID               = "restaurant_id",
                    ORDER_ID                    = "order_id",
                    RATING                      ="rating",
                    COMMENT                     ="comment";


    private String
            restaurant_id             = null,
            order_id                 = null,
            rating                    =null,
            comment                   =null;



    List<RatingNFeedbackModel> ratingNFeedbackModels = new ArrayList<RatingNFeedbackModel>();


    public RatingNFeedbackModel(){}


    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
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

    public List<RatingNFeedbackModel> getRatingNFeedbackModels() {
        return ratingNFeedbackModels;
    }

    public void setRatingNFeedbackModels(List<RatingNFeedbackModel> ratingNFeedbackModels) {
        this.ratingNFeedbackModels = ratingNFeedbackModels;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if (json.has(RESTAURANT_ID)) {
                restaurant_id= json.getString(RESTAURANT_ID);
            }
            if (json.has(ORDER_ID)) {
                order_id= json.getString(ORDER_ID);
            }
            if (json.has(RATING)) {
                rating = json.getString(RATING);

            }if (json.has(COMMENT)) {
                comment = json.getString(COMMENT);
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
            jsonMain.put(RESTAURANT_ID, restaurant_id);
            jsonMain.put(ORDER_ID, order_id);
            jsonMain.put(RATING,rating);
            jsonMain.put(COMMENT, comment);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
