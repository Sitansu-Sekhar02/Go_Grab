package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class ProductImageModel implements Serializable {
    private final String TAG = "ProductImageModel";

    private final String
            ID          = "id",
            TITLE       = "media_title",
            VALUE       = "media_alt_text",
            IMAGE       = "media_url";

    private String
            id          = null,
            title       = null,
            value       = null,
            image       = null;

    public ProductImageModel(){}


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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean toObject(String jsonObject){
        try{

            JSONObject json = new JSONObject(jsonObject);
            if (json.has(ID)) id = json.getString(ID);
            if (json.has(TITLE)) title = json.getString(TITLE);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(VALUE)) value = json.getString(VALUE);

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
            jsonMain.put(IMAGE, image);
            jsonMain.put(VALUE, value);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}


