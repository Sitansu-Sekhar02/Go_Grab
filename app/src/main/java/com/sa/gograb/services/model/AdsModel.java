package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class AdsModel implements Serializable {

    private final String TAG = "AdsModel";

    private final String
            ID                   = "id",
            TITLE                = "title",
            AR_TITLE             = "ar_title",
            IMAGE                = "image",
            LINK                 = "link",
            START_DATE           = "start_date",
            END_DATE             = "end_date",
            STATUS               = "status";

    String
            id               = null,
            title            = null,
            arTitle          = null,
            image            = null,
            link             = null,
            startDate        = null,
            endDate          = null,
            status           = null;

    public AdsModel() {
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

    public String getArTitle() {
        return arTitle;
    }

    public void setArTitle(String arTitle) {
        this.arTitle = arTitle;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);

                if (json.has(ID)) id = json.getString(ID);
                if (json.has(TITLE)) title = json.getString(TITLE);
                if (json.has(AR_TITLE)) arTitle = json.getString(AR_TITLE);
                if (json.has(IMAGE)) image = json.getString(IMAGE);
                if (json.has(LINK)) link = json.getString(LINK);
                if (json.has(START_DATE)) startDate = json.getString(START_DATE);
                if (json.has(END_DATE)) endDate = json.getString(END_DATE);
                if (json.has(STATUS)) status = json.getString(STATUS);

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
            jsonMain.put(AR_TITLE, arTitle);
            jsonMain.put(IMAGE, image);
            jsonMain.put(LINK, link);
            jsonMain.put(START_DATE, startDate);
            jsonMain.put(END_DATE, endDate);
            jsonMain.put(STATUS, status);

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
