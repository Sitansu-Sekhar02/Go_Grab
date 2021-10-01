package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Saad on 1/8/2018.
 */

public class CusineModel implements Serializable {
    private final String TAG = "CusineModel";
    private final String

            ID          = "id",
            NAME        = "name";


    String
            id             =null,
            name           =null;


    public CusineModel(){}


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

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(ID)) id = json.getString(ID);
            if(json.has(NAME)) name = json.getString(NAME);

            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();

            jsonMain.put(ID, this.id);
            jsonMain.put(NAME, this.name);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
