package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;


public class BottomAdvertisementModel implements Serializable {
    private final String TAG = "BottomAdvertisementModel";
    private final String
            TITLE          = "title",
            IMAGE          = "image",
            LINK           = "link";

    private String
            title          = null,
            image          = null,
            link           = null;

    public BottomAdvertisementModel(){}

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean toObject(String jsonObject){
        try{

            JSONObject json = new JSONObject(jsonObject);
            if(json.has(TITLE)){this.title = json.getString(TITLE);}
            if(json.has(IMAGE)){this.image = json.getString(IMAGE);}
            if(json.has(LINK)){this.link = json.getString(LINK);}

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
            jsonMain.put(TITLE, title);
            jsonMain.put(IMAGE, image);
            jsonMain.put(LINK, link);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}


