package com.sauthi.gograb.services.model;

import android.util.Log;


import com.sauthi.gograb.global.STATUS;

import org.json.JSONObject;

import java.io.Serializable;

public class NotificationModel implements Serializable {
    private final String TAG = "NotificationModel";
    private final String
            MESSAGE = "body",
            TITLE = "title",
            RESPONSE = "response",
            TYPE = "type",
            TYPE_EXTRA = "type_extra",
            IMAGE = "image";

    String
            typeExtra = null,
            message = null,
            image = null,
            title=null;

    STATUS.NOTIFICATION_TYPE type = STATUS.NOTIFICATION_TYPE.GENERAL;

    JSONObject response = new JSONObject();

    public NotificationModel(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JSONObject getResponse() {
        return response;
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTypeExtra() {
        return typeExtra;
    }

    public void setTypeExtra(String typeExtra) {
        this.typeExtra = typeExtra;
    }

    public STATUS.NOTIFICATION_TYPE getType() {
        return type;
    }

    public void setType(STATUS.NOTIFICATION_TYPE type) {
        this.type = type;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            this.message = json.getString(MESSAGE);
            this.title = json.getString(TITLE);
            if(json.has(IMAGE)){
                this.image = json.getString(IMAGE);
                if(this.image.equalsIgnoreCase("null") || this.image.trim().isEmpty()){
                    this.image = null;
                }
            }
            if(json.has(RESPONSE))this.response = json.getJSONObject(RESPONSE);
            if(json.has(TYPE_EXTRA))this.typeExtra = json.getString(TYPE_EXTRA);
            if(json.has(TYPE)){
                String typeVal = json.getString(TYPE);
                try{
                    this.type = STATUS.NOTIFICATION_TYPE.GENERAL.getNotificationType(typeVal);
                }catch (Exception exx){
                    this.type = STATUS.NOTIFICATION_TYPE.GENERAL;
                }
            }
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
            jsonMain.put(MESSAGE, this.message);
            jsonMain.put(TITLE, this.title);
            jsonMain.put(IMAGE, this.image);
            jsonMain.put(RESPONSE, this.response);
            jsonMain.put(TYPE, this.type.getCode());
            jsonMain.put(TYPE_EXTRA, this.typeExtra);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
