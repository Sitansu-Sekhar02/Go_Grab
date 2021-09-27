package com.user.gograb.services.model;

import android.util.Log;


import com.user.gograb.global.GlobalVariables;

import org.json.JSONObject;

import java.io.Serializable;

public class DocumentModel implements Serializable{

    private final String TAG = "DocumentModel";

    private final String
            ID                  = "id",
            FILE                = "file",
            DOCUMENT_NUMBER     = "document_no",
            VERIFIED            = "verified",
            TYPE                = "document_type";

    String
            id                  = null,
            file                = null,
            documentNumber      = null;

    boolean
            verified            = false;

    GlobalVariables.DOCUMENT_TYPE
            type                = GlobalVariables.DOCUMENT_TYPE.IMAGE;

    public DocumentModel(){}

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public GlobalVariables.DOCUMENT_TYPE getType() {
        return type;
    }

    public void setType(GlobalVariables.DOCUMENT_TYPE type) {
        this.type = type;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            this.id = json.optString(ID);
            this.file = json.optString(FILE);

            int typeTemp = json.getInt(TYPE);
            this.type = GlobalVariables.DOCUMENT_TYPE.valueOf(""+typeTemp);

            if(json.has(DOCUMENT_NUMBER)){this.documentNumber = json.getString(DOCUMENT_NUMBER);}

            if(json.has(VERIFIED)){
                try{
                    this.verified = json.getBoolean(VERIFIED);
                }catch (Exception exxxx){
                    this.verified = false;
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
            jsonMain.put(FILE, file);
            jsonMain.put(TYPE, type.toInt());
            jsonMain.put(DOCUMENT_NUMBER, documentNumber);
            jsonMain.put(VERIFIED, verified);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
