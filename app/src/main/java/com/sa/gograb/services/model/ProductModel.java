package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class ProductModel implements Serializable {

    private final String TAG = "ProductModel";

    private final String
            ID               = "id_product",
            VARIATION        = "id_variation",
            CATEGORY         = "category",
            CATEGORY_ID      = "id_category",
            SUB_CATEGORY_ID  = "id_sub_category",
            SUB_CATEGORY     = "sub_category",
            VENDOR           = "id_vendor",
            SOLD_BY          = "sold_by",
            PRICE            = "price",
            MODEL            = "model",
            DATE             = "manufacturing_date",
            UNITS            = "measure_units",
            CODE             = "product_code",
            BRAND_ID         = "brand_id",
            BRAND            = "brand",
            SERIES           = "series_id",
            SERIES_ID        = "series",
            VARIANT_ID       = "variant_id",
            VARIANT          = "variant",
            SUB_VARIANT_ID   = "sub_variant_id",
            SUB_VARIANT      = "sub_variant",
            DISCOUNT         = "discount",
            TITLE            = "title",
            SUB_TITLE        = "sub_title",
            CURRENCY         = "currency",
            IMAGE            = "image",
            WISH_LIST        = "wishlist",
            RATING           = "rating",
            QUANTITY         = "minimum_quantity";

    String
            id               = null,
            variation        = null,
            categoryId       = null,
            category         = null,
            subCategoryId    = null,
            subCategory      = null,
            vendor           = null,
            soldBy           = null,
            model            = null,
            date             = null,
            units            = null,
            code             = null,
            series           = null,
            seriesId         = null,
            variantId        = null,
            variant          = null,
            subVariantId     = null,
            subVariant       = null,
            title            = null,
            subTitle         = null,
            brand            = null,
            brandId          = null,
            currency         = null,
            image            = null,
            price            = null,
            discount         = null,
            wishlist         = null,
            rating           = null,
            quantity         = null;

    public ProductModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getWishlist() {
        return wishlist;
    }

    public void setWishlist(String wishlist) {
        this.wishlist = wishlist;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getSoldBy() {
        return soldBy;
    }

    public void setSoldBy(String soldBy) {
        this.soldBy = soldBy;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getSubVariantId() {
        return subVariantId;
    }

    public void setSubVariantId(String subVariantId) {
        this.subVariantId = subVariantId;
    }

    public String getSubVariant() {
        return subVariant;
    }

    public void setSubVariant(String subVariant) {
        this.subVariant = subVariant;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);
            id = json.getString(ID);
            if (json.has(TITLE)) title = json.getString(TITLE);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(PRICE)) price = json.getString(PRICE);
            if (json.has(QUANTITY)) quantity = json.getString(QUANTITY);
            if (json.has(SUB_TITLE)) subTitle = json.getString(SUB_TITLE);
            if (json.has(CURRENCY)) currency = json.getString(CURRENCY);
            if (json.has(DISCOUNT)) discount = json.getString(DISCOUNT);
            if (json.has(WISH_LIST)) wishlist = json.getString(WISH_LIST);
            if (json.has(RATING)) rating = json.getString(RATING);
            if (json.has(BRAND)) brand = json.getString(BRAND);
            if (json.has(BRAND_ID)) brandId = json.getString(BRAND_ID);
            if (json.has(VARIATION)) variation = json.getString(VARIATION);
            if (json.has(CATEGORY)) category = json.getString(CATEGORY);
            if (json.has(CATEGORY_ID)) categoryId = json.getString(CATEGORY_ID);
            if (json.has(SUB_CATEGORY)) subCategory = json.getString(SUB_CATEGORY);
            if (json.has(SUB_CATEGORY_ID)) subCategoryId = json.getString(SUB_CATEGORY_ID);
            if (json.has(VENDOR)) vendor = json.getString(VENDOR);
            if (json.has(SOLD_BY)) soldBy = json.getString(SOLD_BY);
            if (json.has(MODEL)) model = json.getString(MODEL);
            if (json.has(DATE)) date = json.getString(DATE);
            if (json.has(UNITS)) units = json.getString(UNITS);
            if (json.has(CODE)) code = json.getString(CODE);
            if (json.has(SERIES)) series = json.getString(SERIES);
            if (json.has(SERIES_ID)) seriesId = json.getString(SERIES_ID);
            if (json.has(VARIANT)) variant = json.getString(VARIANT);
            if (json.has(VARIANT_ID)) variantId = json.getString(VARIANT_ID);
            if (json.has(SUB_VARIANT)) subVariant = json.getString(SUB_VARIANT);
            if (json.has(SUB_VARIANT_ID)) subVariantId= json.getString(SUB_VARIANT_ID);

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
            jsonMain.put(TITLE, title);
            jsonMain.put(IMAGE, image);
            jsonMain.put(PRICE, price);
            jsonMain.put(QUANTITY, quantity);
            jsonMain.put(SUB_TITLE, subTitle);
            jsonMain.put(CURRENCY, currency);
            jsonMain.put(DISCOUNT, discount);
            jsonMain.put(WISH_LIST, wishlist);
            jsonMain.put(RATING, rating);
            jsonMain.put(BRAND, brand);
            jsonMain.put(BRAND_ID, brandId);
            jsonMain.put(VARIATION, variation);
            jsonMain.put(CATEGORY, category);
            jsonMain.put(CATEGORY_ID, categoryId);
            jsonMain.put(SUB_CATEGORY, subCategory);
            jsonMain.put(SUB_CATEGORY_ID, subCategoryId);
            jsonMain.put(VENDOR, vendor);
            jsonMain.put(SOLD_BY, soldBy);
            jsonMain.put(MODEL, model);
            jsonMain.put(DATE, date);
            jsonMain.put(UNITS, units);
            jsonMain.put(CODE, code);
            jsonMain.put(SERIES, series);
            jsonMain.put(SERIES_ID, seriesId);
            jsonMain.put(VARIANT, variant);
            jsonMain.put(VARIANT_ID, variantId);
            jsonMain.put(SUB_VARIANT, subVariant);
            jsonMain.put(SUB_VARIANT_ID, subVariantId);

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
