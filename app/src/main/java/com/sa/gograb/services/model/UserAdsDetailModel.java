package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class UserAdsDetailModel implements Serializable {

    private final String TAG = "UserAdsDetailModel";

    private final String
            ID                   = "id",
            TITLE                = "title",
            EXTRA                = "extra";

    String
            id               = null,
            title            = null,
            extra            = null;

    public UserAdsDetailModel() {
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

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);

                if (json.has(ID)) id = json.getString(ID);
                if (json.has(TITLE)) title = json.getString(TITLE);
                if (json.has(EXTRA)) extra = json.getString(EXTRA);

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
            jsonMain.put(EXTRA, extra);

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
