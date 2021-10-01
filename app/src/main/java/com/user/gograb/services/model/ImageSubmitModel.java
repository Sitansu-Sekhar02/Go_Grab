package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class ImageSubmitModel implements Serializable {
    private final String TAG = "ImageSubmitModel";

    private final String
            IMAGE               = "image";

    String
            image               = null;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(IMAGE)){this.image = json.getString(IMAGE);}

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
            jsonMain.put(IMAGE, this.image);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
