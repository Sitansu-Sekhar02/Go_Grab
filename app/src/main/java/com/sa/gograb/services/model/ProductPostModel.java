package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class ProductPostModel implements Serializable {

    private final String TAG = "ProductPostModel";
    private final String
            OFFER_ID            = "id_offer",
            PRODUCT_ID          = "product_id",
            CATEGORY_ID         = "id_category",
            SUB_CATEGORY_ID     = "id_sub_category",
            BRAND_ID            = "brand_id",
            MIN_PRICE           = "from_price",
            MAX_PRICE           = "to_price",
            VENDOR_ID           = "id_vendor",
            SORT                = "sort",
            INDEX               = "index",
            SIZE                = "size";

    String
            offerId          = null,
            vendorId         = null,
            productId        = null,
            categoryId       = null,
            subCategoryId    = null,
            brandId          = null,
            minPrice         = null,
            maxPrice         = null,
            sort             = null,
            index            = null,
            size             = null;

    public ProductPostModel(){}

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if(json.has(CATEGORY_ID)){this.categoryId = json.getString(CATEGORY_ID);}
            if(json.has(SUB_CATEGORY_ID)){this.subCategoryId = json.getString(SUB_CATEGORY_ID);}
            if(json.has(OFFER_ID)){this.offerId = json.getString(OFFER_ID);}
            if(json.has(BRAND_ID)){this.brandId = json.getString(BRAND_ID);}
            if(json.has(MIN_PRICE)){this.minPrice = json.getString(MIN_PRICE);}
            if(json.has(MAX_PRICE)){this.maxPrice = json.getString(MAX_PRICE);}
            if(json.has(SORT)){this.sort = json.getString(SORT);}
            if(json.has(INDEX)){this.index = json.getString(INDEX);}
            if(json.has(SIZE)){this.size = json.getString(SIZE);}
            if(json.has(PRODUCT_ID)){this.productId = json.getString(PRODUCT_ID);}
            if(json.has(VENDOR_ID)){this.vendorId = json.getString(VENDOR_ID);}

            return true;
        }catch(Exception ex){Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(OFFER_ID, offerId);
            jsonMain.put(BRAND_ID, brandId);
            jsonMain.put(MIN_PRICE, minPrice);
            jsonMain.put(MAX_PRICE, maxPrice);
            jsonMain.put(SORT, sort);
            jsonMain.put(CATEGORY_ID, categoryId);
            jsonMain.put(SUB_CATEGORY_ID, subCategoryId);
            jsonMain.put(INDEX, index);
            jsonMain.put(SIZE, size);
            jsonMain.put(PRODUCT_ID, productId);
            jsonMain.put(VENDOR_ID, vendorId);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

