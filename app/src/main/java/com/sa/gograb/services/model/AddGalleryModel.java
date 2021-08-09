package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class AddGalleryModel implements Serializable {
    private final String TAG = "AddGalleryModel";

    private final String
            SUB_CARNIVAL_ID       = "sub_carnival_id",
            LINK                  = "link",
            VIDEO_THUMBNAIL       = "video_thumbnail";

    String
            subCarnivalId      = null,
            link               = null,
            videoThumbnail     = null;

    public AddGalleryModel(){}

    public String getSubCarnivalId() {
        return subCarnivalId;
    }

    public void setSubCarnivalId(String subCarnivalId) {
        this.subCarnivalId = subCarnivalId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(SUB_CARNIVAL_ID)){this.subCarnivalId = json.getString(SUB_CARNIVAL_ID);}
            if(json.has(LINK)){this.link = json.getString(LINK);}
            if(json.has(VIDEO_THUMBNAIL)){this.videoThumbnail = json.getString(VIDEO_THUMBNAIL);}

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
            jsonMain.put(SUB_CARNIVAL_ID, this.subCarnivalId);
            jsonMain.put(LINK, this.link);
            jsonMain.put(VIDEO_THUMBNAIL, this.videoThumbnail);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
