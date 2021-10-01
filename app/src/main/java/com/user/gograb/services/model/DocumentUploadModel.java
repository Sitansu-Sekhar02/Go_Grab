package com.user.gograb.services.model;

import android.util.Log;


import com.user.gograb.global.GlobalVariables;

import org.json.JSONObject;

import java.io.Serializable;

public class DocumentUploadModel implements Serializable {

    private final String TAG = "DocumentUploadModel";

    private final String
            FILE                = "file",
            DOCUMENT_NUMBER     = "document_no",
            FILE_TYPE           = "file_type",
            VEHICLE_ID          = "vehicle_id",
            TYPE                = "document_type";

    String
            file                = null,
            vehicleID           = null,
            documentNumber      = null;

    GlobalVariables.FILE_TYPE
            fileType            = GlobalVariables.FILE_TYPE.URL;

    GlobalVariables.DOCUMENT_TYPE
            type                = GlobalVariables.DOCUMENT_TYPE.IMAGE;

    public DocumentUploadModel(){}

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

    public GlobalVariables.FILE_TYPE getFileType() {
        return fileType;
    }

    public void setFileType(GlobalVariables.FILE_TYPE fileType) {
        this.fileType = fileType;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            this.file = json.optString(FILE);

            int typeTemp = json.getInt(TYPE);
            this.type = GlobalVariables.DOCUMENT_TYPE.valueOf(""+typeTemp);

            int fileTypeTemp = json.getInt(FILE_TYPE);
            this.fileType = GlobalVariables.FILE_TYPE.valueOf(""+fileTypeTemp);

            if(json.has(DOCUMENT_NUMBER)){this.documentNumber = json.getString(DOCUMENT_NUMBER);}
            if(json.has(VEHICLE_ID)){this.vehicleID = json.getString(VEHICLE_ID);}

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
            jsonMain.put(FILE, file);
            jsonMain.put(TYPE, type.toInt());
            jsonMain.put(VEHICLE_ID, vehicleID);
            jsonMain.put(FILE_TYPE, fileType.toInt());
            jsonMain.put(DOCUMENT_NUMBER, documentNumber);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
