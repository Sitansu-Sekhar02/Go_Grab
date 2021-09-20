package com.sauthi.gograb.services.model;

import android.util.Log;

import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.io.Serializable;

public class NewsModel implements Serializable {
    private final String TAG = "NewsModel";
    private final String
            ID = "id",
            TITLE = "title",
            IMAGE = "image",
            DESCRIPTION = "description";

    String id=null, title=null, image = null, description = null;

    public NewsModel(){}

    public NewsModel(String id, String title, @Nullable String image, @Nullable String description){
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
           if (json.has(ID))this.id = json.getString(ID);
           if (json.has(TITLE))this.title = json.getString(TITLE);
           if (json.has(IMAGE))this.image = json.getString(IMAGE);
           if (json.has(DESCRIPTION))this.description = json.getString(DESCRIPTION);

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
            jsonMain.put(ID, this.getId());
            jsonMain.put(TITLE, this.getTitle());
            jsonMain.put(IMAGE, this.getImage());
            jsonMain.put(DESCRIPTION, this.getDescription());
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
