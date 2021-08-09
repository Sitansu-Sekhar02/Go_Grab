package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class PostModel implements Serializable {

    private final String TAG    = "PostModel";

    private final String
            ID                  = "id",
            TYPE                = "type",
            INDEX               = "index",
            COUNT               = "count";

    String
            id                  = null,
            type                = null;

    int
            index               = -1,
            count               = -1;

    public PostModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(ID))this.id = json.getString(ID);
            if(json.has(TYPE))this.type = json.getString(type);

            if(json.has(INDEX)){
                try{ this.index = json.getInt(INDEX); }
                catch (Exception exxx){ this.index = -1; }
            }
            if(json.has(COUNT)){
                try{ this.count = json.getInt(COUNT); }
                catch (Exception exxx){ this.count = -1; }
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
            jsonMain.put(ID, this.id);
            jsonMain.put(TYPE, this.type);
            jsonMain.put(INDEX, this.index>=0?this.index:null);
            jsonMain.put(COUNT, this.count>=0?this.count:null);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
