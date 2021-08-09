package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class FeedbackModel implements Serializable {
    private final String TAG = "FeedbackModel";
    private final String
            ID                  = "user_id",
            FIRST_NAME          = "first_name",
            LAST_NAME           = "last_name",
            ORDER_ID            = "order_id",
            RATING              = "rating",
            COMMENT             = "comment",
            REVIEWS             = "reviews",
            CREATED_ON          = "created_on",
            IMAGE               = "profile_image",
            USER_IMAGE          = "user_image";

    private String
            id                 = null,
            image              = null,
            reviews            = null,
            firstName          = null,
            lastName           = null,
            orderId            = null,
            rating             = null,
            comment            = null,
            createdOn          = null,
            userImage          = null;

    public FeedbackModel(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public boolean toObject(String jsonObject){
        try{

            JSONObject json = new JSONObject(jsonObject);

            if(json.has(ID)){this.id = json.getString(ID);}
            if(json.has(IMAGE)){this.image = json.getString(IMAGE);}
            if(json.has(REVIEWS)){this.reviews = json.getString(REVIEWS);}
            if(json.has(FIRST_NAME)){this.firstName = json.getString(FIRST_NAME);}
            if(json.has(LAST_NAME)){this.lastName = json.getString(LAST_NAME);}
            if(json.has(ORDER_ID)){this.orderId = json.getString(ORDER_ID);}
            if(json.has(RATING)){this.rating = json.getString(RATING);}
            if(json.has(COMMENT)){this.comment = json.getString(COMMENT);}
            if(json.has(CREATED_ON)){this.createdOn = json.getString(CREATED_ON);}
            if(json.has(USER_IMAGE)){this.userImage = json.getString(USER_IMAGE);}

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

            jsonMain.put(ID, id);
            jsonMain.put(IMAGE, image);
            jsonMain.put(REVIEWS, reviews);
            jsonMain.put(FIRST_NAME, firstName);
            jsonMain.put(LAST_NAME, lastName);
            jsonMain.put(ORDER_ID, orderId);
            jsonMain.put(RATING, rating);
            jsonMain.put(COMMENT, comment);
            jsonMain.put(CREATED_ON, createdOn);
            jsonMain.put(USER_IMAGE, userImage);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}


