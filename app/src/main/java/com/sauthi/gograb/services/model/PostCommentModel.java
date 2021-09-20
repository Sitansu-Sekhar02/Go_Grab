package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class PostCommentModel implements Serializable {

    private final String TAG = "PostCommentModel";


    private final String
            MASTER_ID             = "master_id",
            CATEGORY_ID           = "category_id",
            COMMENT               = "comment",
            SUB_CARNIVAL_ID       = "sub_carnival_id",
            TEXT                  = "text";

    String
            masterId              = null,
            categoryId            = null,
            comment               = null,
            subCarnivalId         = null,
            text                  = null;

    public PostCommentModel(){}

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSubCarnivalId() {
        return subCarnivalId;
    }

    public void setSubCarnivalId(String subCarnivalId) {
        this.subCarnivalId = subCarnivalId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if(json.has(MASTER_ID)){this.masterId = json.getString(MASTER_ID);}
            if(json.has(CATEGORY_ID)){this.categoryId = json.getString(CATEGORY_ID);}
            if(json.has(COMMENT)){this.comment = json.getString(COMMENT);}
            if(json.has(SUB_CARNIVAL_ID)){this.subCarnivalId = json.getString(SUB_CARNIVAL_ID);}
            if(json.has(TEXT)){this.text = json.getString(TEXT);}

            return true;
        }catch(Exception ex){Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(MASTER_ID, masterId);
            jsonMain.put(CATEGORY_ID, categoryId);
            jsonMain.put(COMMENT, comment);
            jsonMain.put(SUB_CARNIVAL_ID, subCarnivalId);
            jsonMain.put(TEXT, text);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

