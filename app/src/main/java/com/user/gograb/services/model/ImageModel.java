package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageModel implements Serializable {
    private final String TAG = "ImageModel";
    private final String
            SMALLIMAGE = "small_image",
            MEDIUMIMAGE = "medium_image",
            LARGEIMAGE = "large_image",
            GALLERY = "gallery";

    String smallImage=null, mediumImage=null, largeImage=null;

    List<String> gallery = new ArrayList<String>();

    public ImageModel(){}

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getMediumImage() {
        return mediumImage;
    }

    public void setMediumImage(String mediumImage) {
        this.mediumImage = mediumImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public List<String> getGallery() {
        return gallery;
    }

    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            smallImage = json.getString(SMALLIMAGE);
            mediumImage = json.getString(MEDIUMIMAGE);
            largeImage = json.getString(LARGEIMAGE);
            JSONArray jsonArray = json.getJSONArray(GALLERY);
            List<String> gallListTemp = new ArrayList<String>();
            gallListTemp.clear();
            for(int i=0;i<jsonArray.length();i++){
                gallListTemp.add(jsonArray.getString(i));
            }
            gallery.clear();
            gallery.addAll(gallListTemp);
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
            jsonMain.put(SMALLIMAGE, smallImage);
            jsonMain.put(MEDIUMIMAGE, mediumImage);
            jsonMain.put(LARGEIMAGE, largeImage);
            JSONArray tempGalArray = new JSONArray();
            for(int i=0;i<gallery.size();i++){
                tempGalArray.put(gallery.get(i));
            }
            jsonMain.put(GALLERY, tempGalArray);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
