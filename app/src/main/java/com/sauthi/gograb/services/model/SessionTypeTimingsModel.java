package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class SessionTypeTimingsModel implements Serializable {
    private final String TAG = "SessionTypeTimModel";
    private final String
            ID              = "id",
            TITLE           = "name",
            TIME_SLOT       = "time_slot";

    String
            id              = null,
            title           = null;



    KeyValueListModel timeSlot = null;

    public SessionTypeTimingsModel(){}

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

    public KeyValueListModel getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(KeyValueListModel timeSlot) {
        this.timeSlot = timeSlot;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            this.id = json.getString(ID);
            if(json.has(TITLE)) this.title = json.getString(TITLE);

            if(json.has(TIME_SLOT)) {
                JSONArray timeSlotArray = json.getJSONArray(TIME_SLOT);
                KeyValueListModel timeslotListModel = new KeyValueListModel();
                if (timeslotListModel.toObject(timeSlotArray)) {
                    this.timeSlot = timeslotListModel;
                } else {
                    this.timeSlot = null;
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
            jsonMain.put(ID, id);
            jsonMain.put(TITLE, title);
            jsonMain.put(TIME_SLOT, timeSlot!=null ? new JSONArray(timeSlot.toString(true)) : new JSONArray());
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
