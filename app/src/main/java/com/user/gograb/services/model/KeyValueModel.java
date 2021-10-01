package com.user.gograb.services.model;

import android.util.Log;

import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.io.Serializable;

public class KeyValueModel implements Serializable {
    private final String TAG = "KeyValueModel";
    private final String
            ID = "id",
            NAME = "name",
            EXTRA = "extra";

    String id=null, name=null, extra = null;

    public KeyValueModel(){}

    public KeyValueModel(String id, String name, @Nullable String extra){
        this.id = id;
        this.name = name;
        this.extra = extra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            this.id = json.getString(ID);
            this.name = json.getString(NAME);
           if (json.has(EXTRA))this.extra = json.getString(EXTRA);
            if(this.extra.equalsIgnoreCase("null")||this.extra.equalsIgnoreCase("")||this.extra.equalsIgnoreCase("Null")||this.extra.equalsIgnoreCase("NULL")||this.extra.isEmpty()){
                this.extra=null;
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
            jsonMain.put(ID, this.getId());
            jsonMain.put(NAME, this.getName());
            jsonMain.put(EXTRA, this.getExtra());
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
