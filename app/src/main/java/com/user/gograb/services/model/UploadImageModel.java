package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class UploadImageModel implements Serializable {

    private final String TAG = "UploadImageModel";

    private final String
            FILE            = "file",
            CATEGORY        = "category";

    String
            file            = null,
            categoryID      = null;

    public UploadImageModel(){}

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            this.file = json.getString(FILE);
            //this.categoryID = json.getString(CATEGORY);
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
            jsonMain.put(FILE, this.file);
            //jsonMain.put(CATEGORY, this.categoryID);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
