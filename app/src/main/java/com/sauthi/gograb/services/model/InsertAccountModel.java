package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class InsertAccountModel implements Serializable {

    private final String TAG = "InsertAccountModel";

    private final String
                    ID                        = "id",
                    MEMBERSHIP_ID             ="membership_id",
                    FIRST_NAME                 = "first_name",
                    LAST_NAME                   = "last_name",
                    PROFILE_IMAGE                = "profile_image";


    private String
             id                  = null,
            membership_id       =null,
            first_name          =null,
            last_name           = null,
            profile_image       = null;



    List<InsertAccountModel> orderModelList = new ArrayList<InsertAccountModel>();


    public InsertAccountModel(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(String membership_id) {
        this.membership_id = membership_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public List<InsertAccountModel> getOrderModelList() {
        return orderModelList;
    }

    public void setOrderModelList(List<InsertAccountModel> orderModelList) {
        this.orderModelList = orderModelList;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if (json.has(ID)) {
                id= json.getString(ID);
            }
            if (json.has(MEMBERSHIP_ID)) {
                membership_id = json.getString(MEMBERSHIP_ID);

            }if (json.has(FIRST_NAME)) {
                first_name = json.getString(FIRST_NAME);
            }if (json.has(LAST_NAME)) {
                last_name = json.getString(LAST_NAME);
            }if (json.has(PROFILE_IMAGE)) {
                profile_image = json.getString(PROFILE_IMAGE);
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
            //jsonMain.put(ID, this.id);
            jsonMain.put(ID, id);
            jsonMain.put(MEMBERSHIP_ID,membership_id);

            jsonMain.put(FIRST_NAME, first_name);
            jsonMain.put(LAST_NAME, last_name);
            jsonMain.put(PROFILE_IMAGE, profile_image);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
