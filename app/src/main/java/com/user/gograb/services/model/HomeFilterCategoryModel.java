package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class HomeFilterCategoryModel implements Serializable {

    private final String TAG = "HomeFilterCategoryModel";

    private final String
            ID                        = "id",
            TITLE                     = "title",
            IMAGE                     = "image",
            PREPARATION               = "preparation",
            TYPE                      = "type";

    String
            id           = null,
            title        = null,
            image        = null,
            preparation  = null,
            type         = null;



    public HomeFilterCategoryModel() {
    }

    public String getTAG() {
        return TAG;
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

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);
            id = json.getString(ID);
            if (json.has(TITLE)) title = json.getString(TITLE);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(TYPE)) type = json.getString(TYPE);
            if (json.has(PREPARATION)) preparation = json.getString(PREPARATION);

            return true;
        } catch (Exception ex) {
            Log.d(TAG, "Json Exception : " + ex);
        }
        return false;
    }

    @Override
    public String toString() {
        String returnString = null;
        try {
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(ID, id);
            jsonMain.put(TITLE, title);
            jsonMain.put(IMAGE, image);
            jsonMain.put(TYPE, type);
            jsonMain.put(PREPARATION, preparation);

            returnString = jsonMain.toString();
            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
