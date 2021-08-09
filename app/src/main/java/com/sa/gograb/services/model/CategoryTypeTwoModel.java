package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class CategoryTypeTwoModel implements Serializable {

    private final String TAG = "CategoryTypeTwoModel";

    private final String
            ID               = "id",
            CATEGORY         = "category",
            MODEL            = "model",
            VIEWS            = "views",
            PRICE            = "price",
            DISCOUNT         = "discount",
            TITLE            = "title",
            WISHLIST         = "wishlist",
            IMAGE            = "image";

    String
            id               = null,
            category         = null,
            model            = null,
            views            = null,
            price            = null,
            discount         = null,
            title            = null,
            wishlist         = null,
            image            = null;

    public CategoryTypeTwoModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWishlist() {
        return wishlist;
    }

    public void setWishlist(String wishlist) {
        this.wishlist = wishlist;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);
            id = json.getString(ID);
            if (json.has(CATEGORY)) category = json.getString(CATEGORY);
            if (json.has(MODEL)) model = json.getString(MODEL);
            if (json.has(VIEWS)) views = json.getString(VIEWS);
            if (json.has(PRICE)) price = json.getString(PRICE);
            if (json.has(DISCOUNT)) discount = json.getString(DISCOUNT);
            if (json.has(TITLE)) title = json.getString(TITLE);
            if (json.has(WISHLIST)) wishlist = json.getString(WISHLIST);
            if (json.has(IMAGE)) image = json.getString(IMAGE);

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
            jsonMain.put(CATEGORY, category);
            jsonMain.put(MODEL, model);
            jsonMain.put(VIEWS, views);
            jsonMain.put(PRICE, price);
            jsonMain.put(DISCOUNT, discount);
            jsonMain.put(TITLE, title);
            jsonMain.put(WISHLIST, wishlist);
            jsonMain.put(IMAGE, image);

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
