package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RestaurantModel implements Serializable {

    private final String TAG = "RestaurantModel";

    private final String
            ID                           = "id",
            CUSINE_ID                    = "cuisine_id",
            PREPARE_TIME                 = "preparation_time",
            INDEX                        = "index",
            SIZE                         = "size",
            SORT_BY                      = "sort_by",
            FILTER                       = "filter",
            SORT_BY_DESC                 = "sort_by description",
            NAME                         = "full_name",
            IMAGE                        = "image",
            RATING                       = "rating",
            RATING_COUNT                 = "rating_count",
            WISHLIST                     = "wishlist",
            LATITUDE                     = "latitude",
            LONGITUDE                    = "longitude",
            CUSINS                       = "cusine",
            DISTANCE                     = "distance";


    String
            id                 = null,
            cuisine_id         = null,
            preparation_time   = null,
            index              = null,
            size               = null,
            sort_by            = null,
            filter             = null,
            sort_by_desc       = null,
            name               = null,
            distance           = null,
            rating             = null,
            rating_count       = null,
            wishlist           = null,
            latitude           = null,
            longitude           = null,
            image              = null;

    CusineListModel
            cusineListModel = null;

    public RestaurantModel() {
    }

    List<RestaurantModel> restaurantModels = new ArrayList<RestaurantModel>();

    public List<RestaurantModel> getRestaurantModels() {
        return restaurantModels;
    }

    public void setRestaurantModels(List<RestaurantModel> restaurantModels) {
        this.restaurantModels = restaurantModels;
    }

    public CusineListModel getCusineListModel() {
        return cusineListModel;
    }

    public void setCusineListModel(CusineListModel cusineListModel) {
        this.cusineListModel = cusineListModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCuisine_id() {
        return cuisine_id;
    }

    public void setCuisine_id(String cuisine_id) {
        this.cuisine_id = cuisine_id;
    }

    public String getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(String preparation_time) {
        this.preparation_time = preparation_time;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSort_by() {
        return sort_by;
    }

    public void setSort_by(String sort_by) {
        this.sort_by = sort_by;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getSort_by_desc() {
        return sort_by_desc;
    }

    public void setSort_by_desc(String sort_by_desc) {
        this.sort_by_desc = sort_by_desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getRating_count() {
        return rating_count;
    }

    public void setRating_count(String rating_count) {
        this.rating_count = rating_count;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

    public String getWishlist() {
        return wishlist;
    }

    public void setWishlist(String wishlist) {
        this.wishlist = wishlist;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);
            if (json.has(ID)) id = json.getString(ID);
            if (json.has(CUSINE_ID)) cuisine_id = json.getString(CUSINE_ID);
            if (json.has(PREPARE_TIME)) preparation_time = json.getString(PREPARE_TIME);
            if (json.has(INDEX)) index = json.getString(INDEX);
            if (json.has(SIZE)) size = json.getString(SIZE);
            if (json.has(SORT_BY)) sort_by = json.getString(SORT_BY);
            if (json.has(FILTER)) filter = json.getString(FILTER);
            if (json.has(SORT_BY_DESC)) sort_by_desc = json.getString(SORT_BY_DESC);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(NAME)) name = json.getString(NAME);
            if (json.has(RATING)) rating = json.getString(RATING);
            if (json.has(RATING_COUNT)) rating_count = json.getString(RATING_COUNT);
            if (json.has(WISHLIST)) wishlist = json.getString(WISHLIST);
            if (json.has(LATITUDE)) latitude = json.getString(LATITUDE);
            if (json.has(LONGITUDE)) longitude = json.getString(LONGITUDE);
            if (json.has(DISTANCE)) distance = json.getString(DISTANCE);

            if (json.has(CUSINS)) {
                JSONArray array = json.getJSONArray(CUSINS);
                CusineListModel listModelLocal = new CusineListModel();
                if (listModelLocal.toObject(array)) {
                    this.cusineListModel = listModelLocal;
                } else {
                    this.cusineListModel = null;
                }
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
            jsonMain.put(CUSINE_ID, cuisine_id);
            jsonMain.put(PREPARE_TIME, preparation_time);
            jsonMain.put(INDEX, index);
            jsonMain.put(SIZE, size);
            jsonMain.put(SORT_BY, sort_by);
            jsonMain.put(FILTER, filter);
            jsonMain.put(SORT_BY_DESC, sort_by_desc);
            jsonMain.put(IMAGE, image);
            jsonMain.put(NAME, name);
            jsonMain.put(RATING, rating);
            jsonMain.put(RATING_COUNT, rating_count);
            jsonMain.put(WISHLIST, wishlist);
            jsonMain.put(LATITUDE, latitude);
            jsonMain.put(LONGITUDE, longitude);
            jsonMain.put(DISTANCE, distance);
            jsonMain.put(CUSINS, cusineListModel != null ? new JSONArray(cusineListModel.toString(true)) : new JSONArray());

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
