package com.sa.gograb.services.model;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class FeedbackNRatingMainModel implements Serializable {
    private final String TAG = "FeedbackNRatingMainModel";
    private final String
            RESPONSE            = "response",
            STATUS              = "status",
            MESSAGE             = "message";

    RatingNFeedbackModel
            ratingNFeedbackModel      = null;



    String message = null;
    boolean isStatus=false;

    public FeedbackNRatingMainModel(){}

    public RatingNFeedbackModel getRatingNFeedbackModel() {
        return ratingNFeedbackModel;
    }

    public void setRatingNFeedbackModel(RatingNFeedbackModel ratingNFeedbackModel) {
        this.ratingNFeedbackModel = ratingNFeedbackModel;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SuppressLint("LongLogTag")
    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            if(json.has(MESSAGE)){this.message = json.getString(MESSAGE);}
            if(json.has(STATUS)){this.isStatus = json.getBoolean(STATUS);}

            if(json.has(RESPONSE)){
                RatingNFeedbackModel ratingNFeed = new RatingNFeedbackModel();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1 = json.getJSONObject(RESPONSE);
                if(jsonObject1 != null){ratingNFeed.toObject(jsonObject1.toString());}
                ratingNFeedbackModel = ratingNFeed;
            }

            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @SuppressLint("LongLogTag")
    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(STATUS, isStatus);
            jsonMain.put(MESSAGE, message);
            jsonMain.put(RESPONSE, ratingNFeedbackModel != null ? new JSONObject(this.ratingNFeedbackModel.toString()) : new JSONObject());

         //   jsonMain.put(RESPONSE, this.membershipListModel != null? new JSONArray(this.membershipListModel.toString(true)) : new JSONArray());

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
