package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class FeedbackMainModel implements Serializable {
    private final String TAG = "FeedbackMainModel";

    private final String
            RATING               = "rating",
            RATING_COUNT         = "rating_count",
            COMMENT_COUNT        = "comment_count",
            RESPONSE             = "response";

    private String
            rating            = null,
            ratingCount       = null,
            commentCount      = null;

    FeedbackListModel
            feedbackList             = null;

    public FeedbackMainModel(){}

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public FeedbackListModel getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(FeedbackListModel feedbackList) {
        this.feedbackList = feedbackList;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);

            if(json.has(RATING)){this.rating = json.getString(RATING);}
            if(json.has(RATING_COUNT)){this.ratingCount = json.getString(RATING_COUNT);}
            if(json.has(COMMENT_COUNT)){this.commentCount = json.getString(COMMENT_COUNT);}

            if(json.has(RESPONSE)) {
                JSONArray array = json.getJSONArray(RESPONSE);
                FeedbackListModel listModelLocal = new FeedbackListModel();
                if(listModelLocal.toObject(array)){this.feedbackList = listModelLocal;}
                else{this.feedbackList = null;}
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
            jsonMain.put(RATING, rating);
            jsonMain.put(RATING_COUNT, ratingCount);
            jsonMain.put(COMMENT_COUNT, commentCount);
            jsonMain.put(RESPONSE, feedbackList!=null?new JSONArray(feedbackList.toString(true)):null);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}


