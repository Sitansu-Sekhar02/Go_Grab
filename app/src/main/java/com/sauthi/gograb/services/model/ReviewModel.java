package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class ReviewModel implements Serializable {
    private final String TAG = "ReviewModel";
    private final String
            ID                       = "review_id",
            FIRST_NAME                = "first_name",
            LAST_NAME                = "last_name",
            RATING                   = "rating",
            COMMENT                  = "comment",
            CREATED_ON               = "created_on";

    private String
            review_id          = null,
            first_name       = null,
            last_name       = null,
            rating         = null,
            comment       = null,
            created_on      = null;

    public ReviewModel(){}

    public String getReview_id() {
        return review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public boolean toObject(String jsonObject){
        try{

            JSONObject json = new JSONObject(jsonObject);
            if (json.has(ID)) review_id = json.getString(ID);
            if (json.has(FIRST_NAME)) first_name = json.getString(FIRST_NAME);
            if (json.has(LAST_NAME)) last_name = json.getString(LAST_NAME);
            if (json.has(COMMENT)) comment = json.getString(COMMENT);
            if (json.has(CREATED_ON)) created_on = json.getString(CREATED_ON);
            if (json.has(RATING)) rating = json.getString(RATING);

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
            jsonMain.put(ID, review_id);
            jsonMain.put(FIRST_NAME, first_name);
            jsonMain.put(LAST_NAME, last_name);
            jsonMain.put(COMMENT, comment);
            jsonMain.put(CREATED_ON, created_on);
            jsonMain.put(RATING, rating);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}


