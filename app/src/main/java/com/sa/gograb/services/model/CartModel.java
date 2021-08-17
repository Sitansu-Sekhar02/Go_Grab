package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class CartModel implements Serializable {

    private final String TAG = "CartModel";

    private final String
            ID               = "id",
            RESTAURANT_ID    = "restaurant_id",
            RESTAURANT_NAME  = "full_name",
            WALLET           = "existing_wallet",
            QUANTITY         = "quantity",
            RATING           = "rating",
            RATING_COUNT     = "rating_count",
            DISTANCE         = "distance",
            PRODUCT_TITLE    = "product_title",
            UNIT_PRICE       = "unit_price",
            IMAGE            = "image",
            SUB_TOTAL        = "sub_total",
            GRAND_TOTAL      = "grand_total",
            TOTAL_PRICE      = "total",
            DISCOUNT         = "discount",
            TOTAL_DISCOUNT   = "bill_discount",
            PRODUCT_DISCOUNT = "product_discount",
            SHIPPING         = "shipping_charge",
            VAT_PERC         = "vat_percent",
            VAT              = "vat",
            PACKING_CHARGE   = "packing_charges",
            CART_COUNT       = "cart_count",
            COUPON           = "coupon_code",
            OFFER_ID         = "offer_id",
            DISCOUNT_PRICE   = "discount_price",
            CURRENCY         = "currency",
            CART_DETAILS     = "cart_detail";

    String
            emptyCart        = null,
            bookingCount     = null,
            salesCount       = null,
            id               = null,
            addressId        = null,
            restaurant_id    = null,
            full_name        = null,
            rating           = null,
            rating_count     = null,
            distance         = null,
            discount_price   = null,
            cityId           = null,
            type             = null,
            wallet           = null,
            coupon           = null,
            productId        = null,
            productTitle     = null,
            quantity         = null,
            image            = null,
            unitPrice        = null,
            subTotal         = null,
            totalPrice       = null,
            discount         = null,
            shipping         = null,
            totalDiscount    = null,
            productDiscount  = null,
            vat              = null,
            vatPerc          = null,
            offerId          = null,
            brand            = null,
            cart_count       = null,
            packing_charges  = null,
            grand_total      = null,
            currency         = null;

    CartDetailsListModel
            cartDetailsListModel  = null;

    public CartModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEmptyCart() {
        return emptyCart;
    }

    public void setEmptyCart(String emptyCart) {
        this.emptyCart = emptyCart;
    }

    public String getBookingCount() {
        return bookingCount;
    }

    public void setBookingCount(String bookingCount) {
        this.bookingCount = bookingCount;
    }

    public String getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(String salesCount) {
        this.salesCount = salesCount;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getCart_count() {
        return cart_count;
    }

    public void setCart_count(String cart_count) {
        this.cart_count = cart_count;
    }

    public String getPacking_charges() {
        return packing_charges;
    }

    public void setPacking_charges(String packing_charges) {
        this.packing_charges = packing_charges;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }

    public String getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(String productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getVatPerc() {
        return vatPerc;
    }

    public void setVatPerc(String vatPerc) {
        this.vatPerc = vatPerc;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public CartDetailsListModel getCartDetailsListModel() {
        return cartDetailsListModel;
    }

    public void setCartDetailsListModel(CartDetailsListModel cartDetailsListModel) {
        this.cartDetailsListModel = cartDetailsListModel;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);

            if (json.has(ID)) id = json.getString(ID);
            if (json.has(WALLET)) wallet = json.getString(WALLET);
            if (json.has(COUPON)) coupon = json.getString(COUPON);
            if (json.has(SUB_TOTAL)) subTotal = json.getString(SUB_TOTAL);
            if (json.has(QUANTITY)) quantity = json.getString(QUANTITY);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(RESTAURANT_ID)) restaurant_id = json.getString(RESTAURANT_ID);
            if (json.has(RESTAURANT_NAME)) full_name = json.getString(RESTAURANT_NAME);
            if (json.has(RATING)) rating = json.getString(RATING);
            if (json.has(RATING_COUNT)) rating_count = json.getString(RATING_COUNT);
            if (json.has(DISCOUNT_PRICE)) discount_price = json.getString(DISCOUNT_PRICE);
            if (json.has(DISTANCE)) distance = json.getString(DISTANCE);
            if (json.has(UNIT_PRICE)) unitPrice = json.getString(UNIT_PRICE);
            if (json.has(TOTAL_PRICE)) totalPrice = json.getString(TOTAL_PRICE);
            if (json.has(DISCOUNT)) discount = json.getString(DISCOUNT);
            if (json.has(TOTAL_DISCOUNT)) totalDiscount = json.getString(TOTAL_DISCOUNT);
            if (json.has(PRODUCT_DISCOUNT)) productDiscount = json.getString(PRODUCT_DISCOUNT);
            if (json.has(VAT)) vat = json.getString(VAT);
            if (json.has(VAT_PERC)) vatPerc = json.getString(VAT_PERC);
            if (json.has(OFFER_ID)) offerId = json.getString(OFFER_ID);
            if (json.has(CURRENCY)) currency = json.getString(CURRENCY);
            if (json.has(PACKING_CHARGE)) packing_charges = json.getString(PACKING_CHARGE);
            if (json.has(CART_COUNT)) cart_count = json.getString(CART_COUNT);
            if (json.has(GRAND_TOTAL)) grand_total = json.getString(GRAND_TOTAL);
            if (json.has(SHIPPING)) shipping = json.getString(SHIPPING);

            if(json.has(CART_DETAILS)) {
                JSONArray array = json.getJSONArray(CART_DETAILS);
                CartDetailsListModel listModelLocal = new CartDetailsListModel();
                if(listModelLocal.toObject(array)){this.cartDetailsListModel = listModelLocal;}
                else{this.cartDetailsListModel = null;}
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
            jsonMain.put(WALLET, wallet);
            jsonMain.put(COUPON, coupon);
            jsonMain.put(SUB_TOTAL, subTotal);
            jsonMain.put(PRODUCT_DISCOUNT, productDiscount);
            jsonMain.put(PRODUCT_TITLE, productTitle);
            jsonMain.put(QUANTITY, quantity);
            jsonMain.put(IMAGE, image);
            jsonMain.put(RESTAURANT_ID, restaurant_id);
            jsonMain.put(RESTAURANT_NAME, full_name);
            jsonMain.put(RATING, rating);
            jsonMain.put(RATING_COUNT, rating_count);
            jsonMain.put(DISTANCE, distance);
            jsonMain.put(UNIT_PRICE, unitPrice);
            jsonMain.put(TOTAL_PRICE, totalPrice);
            jsonMain.put(DISCOUNT, discount);
            jsonMain.put(VAT, vat);
            jsonMain.put(VAT_PERC, vatPerc);
            jsonMain.put(TOTAL_DISCOUNT, totalDiscount);
            jsonMain.put(OFFER_ID, offerId);
            jsonMain.put(CURRENCY, currency);
            jsonMain.put(GRAND_TOTAL, grand_total);
            jsonMain.put(CART_COUNT, cart_count);
            jsonMain.put(PACKING_CHARGE, packing_charges);
            jsonMain.put(SHIPPING, shipping);
            jsonMain.put(CART_DETAILS, cartDetailsListModel!=null?new JSONArray(cartDetailsListModel.toString(true)):null);

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
