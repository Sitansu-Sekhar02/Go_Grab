package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class ProfileServicesModel implements Serializable {
    private final String TAG = "ProfileServicesModel";
    private final String
            ID              = "id",
            TITLE           = "title",
            IMAGE           = "image",
            SELECTED        = "selected";


    String
            id              = null,
            title            = null,
            image            = null;

    boolean selected = false;

    public ProfileServicesModel(){}

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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            this.id = json.getString(ID);
            if(json.has(TITLE))this.title = json.getString(TITLE);
            if(json.has(IMAGE))this.image = json.getString(IMAGE);
            if(json.has(SELECTED))this.selected = json.getBoolean(SELECTED);

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
            jsonMain.put(ID, id);
            jsonMain.put(TITLE, title);
            jsonMain.put(IMAGE, image);
            jsonMain.put(SELECTED, selected);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
