package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class FixingTimePostModel implements Serializable {

    private final String TAG = "FixingTimePostModel";
    private final String
            SPARE_PART_ID        = "spare_part_id",
            SPARE_PARTS_TIME     = "spare_part_time";

    String
            sparePartId         = null,
            sparePartTime       = null;

    public FixingTimePostModel(){}

    public String getSparePartId() {
        return sparePartId;
    }

    public void setSparePartId(String sparePartId) {
        this.sparePartId = sparePartId;
    }

    public String getSparePartTime() {
        return sparePartTime;
    }

    public void setSparePartTime(String sparePartTime) {
        this.sparePartTime = sparePartTime;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if(json.has(SPARE_PART_ID)){this.sparePartId = json.getString(SPARE_PART_ID);}
            if(json.has(SPARE_PARTS_TIME)){this.sparePartTime = json.getString(SPARE_PARTS_TIME);}

            return true;
        }catch(Exception ex){Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(SPARE_PART_ID, sparePartId);
            jsonMain.put(SPARE_PARTS_TIME, sparePartTime);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

