package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class AgeModel implements Serializable {
    private final String TAG = "AgeModel";

    private final String
            ID                  = "id",
            TITLE               = "title",
            TYPE                = "type";

    String
            id                  = null,
            title               = null,
            type                = null;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(ID)){this.id = json.getString(ID);}
            if(json.has(TITLE)){this.title = json.getString(TITLE);}
            if(json.has(TYPE)){this.type = json.getString(TYPE);}

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
            jsonMain.put(ID, this.id);
            jsonMain.put(TITLE, this.title);
            jsonMain.put(TYPE, this.type);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
