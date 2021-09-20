package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class RatingModel implements Serializable {

    private final String TAG = "RatingModel";


    private final String
            MASTER_ID             = "master_id",
            CAT_ID                = "category_id",
            FOLLOWED_BY           = "followed_by",
            RATING                = "rating";

    String
            masterId              = null,
            categoryId            = null,
            followedBy            = null,
            rating                = null;

    public RatingModel(){}

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(String followedBy) {
        this.followedBy = followedBy;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if(json.has(MASTER_ID)){this.masterId = json.getString(MASTER_ID);}
            if(json.has(CAT_ID)){this.categoryId = json.getString(CAT_ID);}
            if(json.has(RATING)){this.rating = json.getString(RATING);}
            if(json.has(FOLLOWED_BY)){this.followedBy = json.getString(FOLLOWED_BY);}

            return true;
        }catch(Exception ex){Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(MASTER_ID, masterId);
            jsonMain.put(CAT_ID, categoryId);
            jsonMain.put(RATING, rating);
            jsonMain.put(FOLLOWED_BY, followedBy);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

