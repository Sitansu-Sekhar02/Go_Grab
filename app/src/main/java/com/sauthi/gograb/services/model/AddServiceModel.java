package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class AddServiceModel implements Serializable {

    private final String TAG = "AddServiceModel";

    private final String
            ID                   = "id",
            SUB_CAT_ID           = "sub_category_id",
            SERVICE_TYPE_TITLE   = "service_type_title",
            TITLE                = "title",
            IMAGE                = "image",
            BANNER_IMAGE         = "banner_image",
            PACKAGE_ID           = "package_id",
            DESCRIPTION          = "description",
            ADDRESS              = "address",
            LATITUDE             = "latitude",
            LONGITUDE            = "longitude",
            IMAGES               = "images",
            VIDEOS               = "videos";

    String
            id               = null,
            subCatId         = null,
            subCatTitle      = null,
            title            = null,
            image            = null,
            bannerImage      = null,
            description      = null,
            address          = null,
            latitude         = null,
            longitude        = null,
            packageId        = null;

    ImageListModel
            imagesList = null;



    public AddServiceModel() {
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubCatTitle() {
        return subCatTitle;
    }

    public void setSubCatTitle(String subCatTitle) {
        this.subCatTitle = subCatTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public ImageListModel getImagesList() {
        return imagesList;
    }

    public void setImagesList(ImageListModel imagesList) {
        this.imagesList = imagesList;
    }



    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);

                if (json.has(ID)) id = json.getString(ID);
                if (json.has(SUB_CAT_ID)) subCatId = json.getString(SUB_CAT_ID);
                if (json.has(SERVICE_TYPE_TITLE)) subCatTitle = json.getString(SERVICE_TYPE_TITLE);
                if (json.has(TITLE)) title = json.getString(TITLE);
                if (json.has(IMAGE)) image = json.getString(IMAGE);
                if (json.has(BANNER_IMAGE)) bannerImage = json.getString(BANNER_IMAGE);
                if (json.has(DESCRIPTION)) description = json.getString(DESCRIPTION);
                if (json.has(ADDRESS)) address = json.getString(ADDRESS);
                if (json.has(LATITUDE)) latitude = json.getString(LATITUDE);
                if (json.has(LONGITUDE)) longitude = json.getString(LONGITUDE);
                if (json.has(PACKAGE_ID)) packageId = json.getString(PACKAGE_ID);

            if(json.has(IMAGES)) {
                JSONArray array = json.getJSONArray(IMAGES);
                ImageListModel listModelLocal = new ImageListModel();
                if(listModelLocal.toObject(array)){this.imagesList = listModelLocal;}
                else{this.imagesList = null;}
            }


            return true;
        } catch (Exception ex) {
            Log.d(TAG, "Json Exception : " + ex);
        }
        return false;
    }

    @Override
    public String toString() {
        String returnString = null;
        try {
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(ID, id);
            jsonMain.put(SUB_CAT_ID, subCatId);
            jsonMain.put(SERVICE_TYPE_TITLE, subCatTitle);
            jsonMain.put(TITLE, title);
            jsonMain.put(IMAGE, image);
            jsonMain.put(BANNER_IMAGE, bannerImage);
            jsonMain.put(DESCRIPTION, description);
            jsonMain.put(ADDRESS, address);
            jsonMain.put(LATITUDE, latitude);
            jsonMain.put(LONGITUDE, longitude);
            jsonMain.put(PACKAGE_ID, packageId);
            jsonMain.put(IMAGES, imagesList!=null?new JSONArray(imagesList.toString(true)):null);

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
