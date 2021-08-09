package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class CartModel implements Serializable {

    private final String TAG = "CartModel";

    private final String
            EMPTY_CART       = "EMPTY_CART",
            BOOKING_COUNT    = "booking_count",
            SALES_COUNT      = "sales_count",
            ID               = "id",
            ADDRESS_ID       = "address_id",
            CITY_ID          = "city_id",
            TYPE             = "type",
            PRODUCT_ID       = "product_id",
            WALLET           = "existing_wallet",
            QUANTITY         = "quantity",
            PRODUCT_TITLE    = "product_title",
            UNIT_PRICE       = "unit_price",
            IMAGE            = "image",
            SUB_TOTAL        = "sub_total",
            TOTAL_PRICE      = "total",
            DISCOUNT         = "discount",
            TOTAL_DISCOUNT   = "bill_discount",
            PRODUCT_DISCOUNT = "product_discount",
            SHIPPING         = "shipping_charge",
            VAT_PERC         = "vat_percent",
            VAT              = "vat",
            COUPON           = "coupon_code",
            OFFER_ID         = "offer_id",
            BRAND            = "brand",
            CURRENCY         = "currency",
            CART_DETAILS     = "cart_detail";

    String
            emptyCart        = null,
            bookingCount     = null,
            salesCount       = null,
            id               = null,
            addressId        = null,
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
            if (json.has(EMPTY_CART)) emptyCart = json.getString(EMPTY_CART);
            if (json.has(BOOKING_COUNT)) bookingCount = json.getString(BOOKING_COUNT);
            if (json.has(SALES_COUNT)) salesCount = json.getString(SALES_COUNT);

            if (json.has(ID)) id = json.getString(ID);
            if (json.has(ADDRESS_ID)) addressId = json.getString(ADDRESS_ID);
            if (json.has(CITY_ID)) cityId = json.getString(CITY_ID);
            if (json.has(WALLET)) wallet = json.getString(WALLET);
            if (json.has(TYPE)) type = json.getString(TYPE);
            if (json.has(COUPON)) coupon = json.getString(COUPON);
            if (json.has(SUB_TOTAL)) subTotal = json.getString(SUB_TOTAL);
            if (json.has(QUANTITY)) quantity = json.getString(QUANTITY);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(UNIT_PRICE)) unitPrice = json.getString(UNIT_PRICE);
            if (json.has(TOTAL_PRICE)) totalPrice = json.getString(TOTAL_PRICE);
            if (json.has(DISCOUNT)) discount = json.getString(DISCOUNT);
            if (json.has(TOTAL_DISCOUNT)) totalDiscount = json.getString(TOTAL_DISCOUNT);
            if (json.has(PRODUCT_DISCOUNT)) productDiscount = json.getString(PRODUCT_DISCOUNT);
            if (json.has(VAT)) vat = json.getString(VAT);
            if (json.has(VAT_PERC)) vatPerc = json.getString(VAT_PERC);
            if (json.has(OFFER_ID)) offerId = json.getString(OFFER_ID);
            if (json.has(BRAND)) brand = json.getString(BRAND);
            if (json.has(CURRENCY)) currency = json.getString(CURRENCY);
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
            jsonMain.put(EMPTY_CART, emptyCart);
            jsonMain.put(BOOKING_COUNT, bookingCount);
            jsonMain.put(SALES_COUNT, salesCount);

            jsonMain.put(ID, id);
            jsonMain.put(ADDRESS_ID, addressId);
            jsonMain.put(CITY_ID, cityId);
            jsonMain.put(WALLET, wallet);
            jsonMain.put(TYPE, type);
            jsonMain.put(COUPON, coupon);
            jsonMain.put(PRODUCT_ID, productId);
            jsonMain.put(SUB_TOTAL, subTotal);
            jsonMain.put(PRODUCT_DISCOUNT, productDiscount);
            jsonMain.put(PRODUCT_TITLE, productTitle);
            jsonMain.put(QUANTITY, quantity);
            jsonMain.put(IMAGE, image);
            jsonMain.put(UNIT_PRICE, unitPrice);
            jsonMain.put(TOTAL_PRICE, totalPrice);
            jsonMain.put(DISCOUNT, discount);
            jsonMain.put(VAT, vat);
            jsonMain.put(VAT_PERC, vatPerc);
            jsonMain.put(TOTAL_DISCOUNT, totalDiscount);
            jsonMain.put(OFFER_ID, offerId);
            jsonMain.put(BRAND, brand);
            jsonMain.put(CURRENCY, currency);
            jsonMain.put(SHIPPING, shipping);
            jsonMain.put(CART_DETAILS, cartDetailsListModel!=null?new JSONArray(cartDetailsListModel.toString(true)):null);

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
