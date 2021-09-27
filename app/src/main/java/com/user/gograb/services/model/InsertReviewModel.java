package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class InsertReviewModel implements Serializable {

    private final String TAG = "InsertReviewModel";

    private final String
                    STORE_ID                  = "store_id",
                    RATING                    ="rating",
                    COMMENT                   = "comment",
                    LAST_NAME                   = "last_name",
                    PROFILE_IMAGE                = "profile_image";


    private String
            store_id                 = null,
            rating                   =null,
            comment                  =null,
            last_name                = null,
            profile_image             = null;



    List<InsertReviewModel> orderModelList = new ArrayList<InsertReviewModel>();


    public InsertReviewModel(){}


    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
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

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public List<InsertReviewModel> getOrderModelList() {
        return orderModelList;
    }

    public void setOrderModelList(List<InsertReviewModel> orderModelList) {
        this.orderModelList = orderModelList;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if (json.has(STORE_ID)) {
                store_id= json.getString(STORE_ID);
            }
            if (json.has(RATING)) {
                rating = json.getString(RATING);

            }if (json.has(COMMENT)) {
                comment = json.getString(COMMENT);
            }if (json.has(LAST_NAME)) {
                last_name = json.getString(LAST_NAME);
            }if (json.has(PROFILE_IMAGE)) {
                profile_image = json.getString(PROFILE_IMAGE);
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
            jsonMain.put(STORE_ID, store_id);
            jsonMain.put(RATING,rating);

            jsonMain.put(COMMENT, comment);
            jsonMain.put(LAST_NAME, last_name);
            jsonMain.put(PROFILE_IMAGE, profile_image);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
