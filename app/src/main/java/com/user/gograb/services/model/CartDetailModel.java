package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class CartDetailModel implements Serializable {

    private final String TAG = "CartDetailModel";

    private final String
            ID               = "id",
            PRO_ID           = "product_id",
            MENU_ID          = "menu_id",
            MENU_TITLE       = "menu_title",
            DESCRIPTION      = "description",
            QTY              = "quantity",
            PRICE            = "total",
            TOTAL_PRICE      = "total_price",
            MODEL            = "model",
            DATE             = "manufacturing_date",
            UNITS            = "unit_price",
            CODE             = "product_code",
            DISCOUNT         = "discount",
            TITLE            = "product_title",
            SUB_TITLE        = "sub_title",
            CURRENCY         = "currency",
            IMAGE            = "image",
            WISH_LIST        = "wishlist",
            RATING           = "rating";

    String
            id               = null,
            productId        = null,
            model            = null,
            date             = null,
            units            = null,
            menu_id          = null,
            menu_title       = null,
            description      = null,
            total_price      = null,
            code             = null,
            series           = null,
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

    public CartDetailModel() {
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

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_title() {
        return menu_title;
    }

    public void setMenu_title(String menu_title) {
        this.menu_title = menu_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
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

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);
            if (json.has(ID)) id = json.getString(ID);
            if (json.has(PRO_ID)) productId = json.getString(PRO_ID);
            if (json.has(TITLE)) title = json.getString(TITLE);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(PRICE)) price = json.getString(PRICE);
            if (json.has(QTY)) quantity = json.getString(QTY);
            if (json.has(SUB_TITLE)) subTitle = json.getString(SUB_TITLE);
            if (json.has(CURRENCY)) currency = json.getString(CURRENCY);
            if (json.has(DISCOUNT)) discount = json.getString(DISCOUNT);
            if (json.has(WISH_LIST)) wishlist = json.getString(WISH_LIST);
            if (json.has(RATING)) rating = json.getString(RATING);
            if (json.has(MENU_ID)) menu_id = json.getString(MENU_ID);
            if (json.has(MENU_TITLE)) menu_title = json.getString(MENU_TITLE);
            if (json.has(DESCRIPTION)) description = json.getString(DESCRIPTION);
            if (json.has(TOTAL_PRICE)) total_price = json.getString(TOTAL_PRICE);
            if (json.has(MODEL)) model = json.getString(MODEL);
            if (json.has(DATE)) date = json.getString(DATE);
            if (json.has(UNITS)) units = json.getString(UNITS);
            if (json.has(CODE)) code = json.getString(CODE);

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
            jsonMain.put(PRO_ID, productId);
            jsonMain.put(TITLE, title);
            jsonMain.put(IMAGE, image);
            jsonMain.put(PRICE, price);
            jsonMain.put(QTY, quantity);
            jsonMain.put(SUB_TITLE, subTitle);
            jsonMain.put(CURRENCY, currency);
            jsonMain.put(DISCOUNT, discount);
            jsonMain.put(WISH_LIST, wishlist);
            jsonMain.put(RATING, rating);
            jsonMain.put(MENU_ID, menu_id);
            jsonMain.put(MENU_TITLE, menu_title);
            jsonMain.put(TOTAL_PRICE, total_price);
            jsonMain.put(DESCRIPTION, description);
            jsonMain.put(MODEL, model);
            jsonMain.put(DATE, date);
            jsonMain.put(UNITS, units);
            jsonMain.put(CODE, code);

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
