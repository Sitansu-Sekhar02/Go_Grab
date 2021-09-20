package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class WishlistCategoryModel implements Serializable {

    private final String TAG = "WishlistCategoryModel";

    private final String
                    ID                        = "id",
                    NAME                       ="name";


    private String
             id                  = null,
            name                =null;




    List<WishlistCategoryModel> wishlistCategoryModels = new ArrayList<WishlistCategoryModel>();


    public WishlistCategoryModel(){}


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

    public List<WishlistCategoryModel> getWishlistCategoryModels() {
        return wishlistCategoryModels;
    }

    public void setWishlistCategoryModels(List<WishlistCategoryModel> wishlistCategoryModels) {
        this.wishlistCategoryModels = wishlistCategoryModels;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if (json.has(ID)) {
                id= json.getString(ID);
            }if (json.has(NAME)) {
                name = json.getString(NAME);
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

            jsonMain.put(NAME, name);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
