package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class AdvertiseModel implements Serializable {

    private final String TAG = "AdvertiseModel";

    private final String
            ID               = "id",
            TITLE            = "title",
            AR_TITLE         = "ar_title",
            IMAGE            = "image",
            LINK             = "link";

    String
            id               = null,
            title            = null,
            artitle          = null,
            image            = null,
            link             = null;

    public AdvertiseModel() {
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

    public String getArtitle() {
        return artitle;
    }

    public void setArtitle(String artitle) {
        this.artitle = artitle;
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

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);

            if (json.has(ID)) id = json.getString(ID);
            if (json.has(TITLE)) title = json.getString(TITLE);
            if (json.has(AR_TITLE)) artitle = json.getString(AR_TITLE);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(LINK)) link = json.getString(LINK);

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
            jsonMain.put(AR_TITLE, artitle);
            jsonMain.put(IMAGE, image);
            jsonMain.put(LINK, link);

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
