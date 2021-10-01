package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class DetailSectionModel implements Serializable {
    private final String TAG = "DetailSectionModel";
    private final String
            ID             = "id",
            TITLE          = "title",
            EXTRA          = "extra",
            IS_SELECTED    = "is_selected";

    private String
            id          = null,
            title       = null,
            extra       = null;

    private boolean isSelected = false;

    public DetailSectionModel(){}

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

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean toObject(String jsonObject){
        try{

            JSONObject json = new JSONObject(jsonObject);

            if (json.has(ID)) id = json.getString(ID);
            if (json.has(TITLE)) title = json.getString(TITLE);
            if (json.has(EXTRA)) extra = json.getString(EXTRA);
            if (json.has(IS_SELECTED)) isSelected = json.getBoolean(IS_SELECTED);

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
            jsonMain.put(EXTRA, extra);
            jsonMain.put(IS_SELECTED, isSelected);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}


