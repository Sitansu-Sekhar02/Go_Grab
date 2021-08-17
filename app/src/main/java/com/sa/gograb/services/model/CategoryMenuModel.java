package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class CategoryMenuModel implements Serializable {

    private final String TAG = "CategoryMenuModel";
    private final String
            ID                       = "id",
            NAME                     = "name",
            MENU_TYPE                = "menu_type",
            TRENDING                 = "trending",
            FOOD_TYPE                = "food_type",
            DESCRIPTION              = "description",
            PRICE                    = "price",
            CART_COUNT               = "cart_count",
            SELECTED                 = "selected",
            MENU                     = "menu",
            CURRENCY                 = "currency",
            OFFER_PRICE              = "offer_price",
            IMAGE                    = "image";

    private String
            id                    = null,
            name                  = null,
            menu_type             = null,
            trending              = null,
            food_type             = null,
            description           = null,
            price                 = null,
            cart_count            = null,
            selected              = "1",
            menu                  = null,
            currency              = null,
            offer_price           = null,
            image                 = null;

    public CategoryMenuModel(){}

    MenuCatListModel
                 menuCatListModel=null;

    public MenuCatListModel getMenuCatListModel() {
        return menuCatListModel;
    }

    public void setMenuCatListModel(MenuCatListModel menuCatListModel) {
        this.menuCatListModel = menuCatListModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenu_type() {
        return menu_type;
    }

    public void setMenu_type(String menu_type) {
        this.menu_type = menu_type;
    }

    public String getTrending() {
        return trending;
    }

    public void setTrending(String trending) {
        this.trending = trending;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCart_count() {
        return cart_count;
    }

    public void setCart_count(String cart_count) {
        this.cart_count = cart_count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(String offer_price) {
        this.offer_price = offer_price;
    }

    public boolean toObject(String jsonObject){
        try{

            JSONObject json = new JSONObject(jsonObject);
            if (json.has(ID)) id = json.getString(ID);
            if (json.has(NAME)) name = json.getString(NAME);
            if (json.has(IMAGE)) image = json.getString(IMAGE);
            if (json.has(DESCRIPTION)) description = json.getString(DESCRIPTION);
            if (json.has(PRICE)) price = json.getString(PRICE);
            if (json.has(FOOD_TYPE)) food_type = json.getString(FOOD_TYPE);
            if (json.has(TRENDING)) trending = json.getString(TRENDING);
            if (json.has(MENU_TYPE)) menu_type = json.getString(MENU_TYPE);
            if (json.has(CART_COUNT)) cart_count = json.getString(CART_COUNT);
            if (json.has(CURRENCY)) currency = json.getString(CURRENCY);
            if (json.has(OFFER_PRICE)) offer_price = json.getString(OFFER_PRICE);
            if (json.has(SELECTED)) selected = json.getString(SELECTED);

            if(json.has(MENU)) {
                JSONArray array = json.getJSONArray(MENU);
                MenuCatListModel listModelLocal = new MenuCatListModel();
                if(listModelLocal.toObject(array)){this.menuCatListModel = listModelLocal;}
                else{this.menuCatListModel = null;}
            }


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
            jsonMain.put(ID, id);
            jsonMain.put(NAME, name);
            jsonMain.put(IMAGE, image);
            jsonMain.put(DESCRIPTION, description);
            jsonMain.put(PRICE, price);
            jsonMain.put(FOOD_TYPE, food_type);
            jsonMain.put(TRENDING, trending);
            jsonMain.put(MENU_TYPE, menu_type);
            jsonMain.put(CART_COUNT, cart_count);
            jsonMain.put(CURRENCY, currency);
            jsonMain.put(OFFER_PRICE, offer_price);
            jsonMain.put(SELECTED, selected);
            jsonMain.put(MENU, menuCatListModel!=null?new JSONArray(menuCatListModel.toString(true)):null);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}


