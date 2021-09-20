package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;


public class PackageModel implements Serializable {
    private final String TAG = "PackageModel";
    private final String
            ID             = "id",
            TITLE          = "title",
            NO_OF_DAYS     = "no_of_days",
            DESCRIPTION    = "description",
            IMAGE          = "image",
            PRICE          = "price";

    private String
            id               = null,
            title            = null,
            noOfDays         = null,
            description      = null,
            image            = null,
            price            = null;

    public PackageModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean toObject(String jsonObject){
        try{

            JSONObject json = new JSONObject(jsonObject);

            if (json.has(ID)) id = json.getString(ID);
            if (json.has(TITLE)) title = json.getString(TITLE);
            if (json.has(NO_OF_DAYS)) noOfDays = json.getString(NO_OF_DAYS);
            if (json.has(DESCRIPTION)) description = json.getString(DESCRIPTION);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(PRICE)) price = json.getString(PRICE);

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
            jsonMain.put(TITLE, title);
            jsonMain.put(NO_OF_DAYS, noOfDays);
            jsonMain.put(DESCRIPTION, description);
            jsonMain.put(IMAGE, image);
            jsonMain.put(PRICE, price);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}


