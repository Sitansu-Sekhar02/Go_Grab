package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class ProductDetailModel implements Serializable {

    private final String TAG = "ProductDetailModel";

    private final String
            ID               = "id_product",
            VARIATION_ID     = "id_variation",
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
            RATING_COUNT     = "rating_count",
            MIN_QUANTITY     = "minimum_quantity",
            AVAIL_QUANTITY   = "available_quantity",
            CART_QUANTITY    = "cart_quantity",
            CART_DETAIL_ID   = "cart_detail_id",
            DESCRIPTION      = "description",
            SORT_DESC        = "short_description",
            VARIATION        = "variation1",
            REVIEW           = "review",
            SPECIFICATION    = "specification",
            IMAGES           = "media";

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
            minQuantity      = null,
            ratingCount      = null,
            availQuantity    = null,
            cartQuantity     = null,
            cartDetailId     = null,
            description      = null,
            shortDesc        = null;


    ProductImagesListModel
           productImagesList = null;



    VariantListModel
            variantListModel   = null;

    FeedbackListModel
            feedbackListModel  = null;

    public ProductDetailModel() {
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

    public String getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getAvailQuantity() {
        return availQuantity;
    }

    public void setAvailQuantity(String availQuantity) {
        this.availQuantity = availQuantity;
    }

    public String getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(String cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public String getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(String cartDetailId) {
        this.cartDetailId = cartDetailId;
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

    public ProductImagesListModel getProductImagesList() {
        return productImagesList;
    }

    public void setProductImagesList(ProductImagesListModel productImagesList) {
        this.productImagesList = productImagesList;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public VariantListModel getVariantListModel() {
        return variantListModel;
    }

    public void setVariantListModel(VariantListModel variantListModel) {
        this.variantListModel = variantListModel;
    }

    public FeedbackListModel getFeedbackListModel() {
        return feedbackListModel;
    }

    public void setFeedbackListModel(FeedbackListModel feedbackListModel) {
        this.feedbackListModel = feedbackListModel;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);

            if (json.has(ID)) id = json.getString(ID);
            if (json.has(TITLE)) title = json.getString(TITLE);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(PRICE)) price = json.getString(PRICE);
            if (json.has(MIN_QUANTITY)) minQuantity = json.getString(MIN_QUANTITY);
            if (json.has(AVAIL_QUANTITY)) availQuantity = json.getString(AVAIL_QUANTITY);
            if (json.has(CART_QUANTITY)) cartQuantity = json.getString(CART_QUANTITY);
            if (json.has(CART_DETAIL_ID)) cartDetailId = json.getString(CART_DETAIL_ID);
            if (json.has(RATING_COUNT)) ratingCount = json.getString(RATING_COUNT);
            if (json.has(SUB_TITLE)) subTitle = json.getString(SUB_TITLE);
            if (json.has(CURRENCY)) currency = json.getString(CURRENCY);
            if (json.has(DISCOUNT)) discount = json.getString(DISCOUNT);
            if (json.has(WISH_LIST)) wishlist = json.getString(WISH_LIST);
            if (json.has(RATING)) rating = json.getString(RATING);
            if (json.has(BRAND)) brand = json.getString(BRAND);
            if (json.has(BRAND_ID)) brandId = json.getString(BRAND_ID);
            if (json.has(VARIATION_ID)) variation = json.getString(VARIATION_ID);
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
            if (json.has(SORT_DESC)) shortDesc = json.getString(SORT_DESC);
            if (json.has(DESCRIPTION)) description = json.getString(DESCRIPTION);

            if(json.has(IMAGES)) {
                JSONArray array = json.getJSONArray(IMAGES);
                ProductImagesListModel listModelLocal = new ProductImagesListModel();
                if(listModelLocal.toObject(array)){this.productImagesList = listModelLocal;}
                else{this.productImagesList = null;}
            }


            if(json.has(REVIEW)) {
                JSONArray array = json.getJSONArray(REVIEW);
                FeedbackListModel listModelLocal = new FeedbackListModel();
                if(listModelLocal.toObject(array)){this.feedbackListModel = listModelLocal;}
                else{this.feedbackListModel = null;}
            }

            if(json.has(VARIATION)) {
                JSONArray array = json.getJSONArray(VARIATION);
                VariantListModel listModelLocal = new VariantListModel();
                listModelLocal.setRESPONSE(VARIATION);
                if(listModelLocal.toObject(array)){this.variantListModel = listModelLocal;}
                else{this.variantListModel = null;}
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
            jsonMain.put(TITLE, title);
            jsonMain.put(IMAGE, image);
            jsonMain.put(PRICE, price);
            jsonMain.put(MIN_QUANTITY, minQuantity);
            jsonMain.put(AVAIL_QUANTITY, availQuantity);
            jsonMain.put(CART_QUANTITY, cartQuantity);
            jsonMain.put(CART_DETAIL_ID, cartDetailId);
            jsonMain.put(SUB_TITLE, subTitle);
            jsonMain.put(CURRENCY, currency);
            jsonMain.put(DISCOUNT, discount);
            jsonMain.put(WISH_LIST, wishlist);
            jsonMain.put(RATING, rating);
            jsonMain.put(RATING_COUNT, ratingCount);
            jsonMain.put(BRAND, brand);
            jsonMain.put(BRAND_ID, brandId);
            jsonMain.put(VARIATION_ID, variation);
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
            jsonMain.put(SORT_DESC, shortDesc);
            jsonMain.put(DESCRIPTION, description);
            jsonMain.put(IMAGES, productImagesList!=null?new JSONArray(productImagesList.toString(true)):null);
          //  jsonMain.put(SPECIFICATION, specificationListModel !=null?new JSONArray(specificationListModel.toString(true)):null);
            jsonMain.put(REVIEW, feedbackListModel !=null?new JSONArray(feedbackListModel.toString(true)):null);
            jsonMain.put(VARIATION, variantListModel!=null?new JSONArray(variantListModel.toString(true)):null);

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
