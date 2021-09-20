package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class TrendingModel implements Serializable {

    private final String TAG = "TrendingModel";

    private final String
            ID = "vendor_id",
            NAME = "name",
            OFFER_TITLE = "offer_title",
            OFFER_APPLICABLE = "offer_applicable",
            ICON = "image",
            EXTRA = "extra",
            VENDOR_NAME = "vendor_name",
            OFFER_DISCOUNT = "offer_discount",
            COUNT = "count",
            SERVICES = "service";

    String
            id = null,
            name = null,
            offer_title = null,
            offer_applicable = null,
            vendor_name = null,
            offer_discount = null,
            count = null,
            icon = null;

    ServicesListModel
            servicesListModel = null;

    TrendingModel traTrendingModel=null;


    public TrendingModel() {
    }

    public String getTAG() {
        return TAG;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ServicesListModel getServicesListModel() {
        return servicesListModel;
    }

    public void setServicesListModel(ServicesListModel servicesListModel) {
        this.servicesListModel = servicesListModel;
    }

    public String getOffer_title() {
        return offer_title;
    }

    public void setOffer_title(String offer_title) {
        this.offer_title = offer_title;
    }

    public String getOffer_applicable() {
        return offer_applicable;
    }

    public void setOffer_applicable(String offer_applicable) {
        this.offer_applicable = offer_applicable;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getOffer_discount() {
        return offer_discount;
    }

    public void setOffer_discount(String offer_discount) {
        this.offer_discount = offer_discount;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getImageResourceID() {
        /*if (this.id.equalsIgnoreCase(GlobalVariables.CATEGORY_VALUE_FOR_FACE)) {
            return R.drawable.face_bg;
        } else if (this.id.equalsIgnoreCase(GlobalVariables.CATEGORY_VALUE_FOR_HAIR)) {
            return R.drawable.hair_bg;
        } else if (this.id.equalsIgnoreCase(GlobalVariables.CATEGORY_VALUE_FOR_MASSAGE)) {
            return R.drawable.massage_bg;
        } else if (this.id.equalsIgnoreCase(GlobalVariables.CATEGORY_VALUE_FOR_NAIL)) {
            return R.drawable.nail_bg;
        } else if (this.id.equalsIgnoreCase(GlobalVariables.CATEGORY_VALUE_FOR_TAILOR)) {
            return R.drawable.tailor_bg;
        } else if (this.id.equalsIgnoreCase(GlobalVariables.CATEGORY_VALUE_FOR_FITNESS)) {
            return R.drawable.fitness_bg;
        } else if (this.id.equalsIgnoreCase(GlobalVariables.CATEGORY_VALUE_FOR_PACKAGING)) {
            return R.drawable.packaging_bg;
        }*/
        return 0;
    }

    public TrendingModel getTraTrendingModel() {
        return traTrendingModel;
    }

    public void setTraTrendingModel(TrendingModel traTrendingModel) {
        this.traTrendingModel = traTrendingModel;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);
            id = json.getString(ID);
            if (json.has(NAME)) name = json.getString(NAME);
            if (json.has(ICON)) icon = json.getString(ICON);
            if (json.has(OFFER_TITLE)) offer_title = json.getString(OFFER_TITLE);
            if (json.has(OFFER_APPLICABLE)) offer_applicable = json.getString(OFFER_APPLICABLE);
            if (json.has(OFFER_DISCOUNT)) offer_discount = json.getString(OFFER_DISCOUNT);
            if (json.has(VENDOR_NAME)) vendor_name = json.getString(VENDOR_NAME);
            if (json.has(COUNT)) count = json.getString(COUNT);

            if(json.has(EXTRA)){
                TrendingModel tmpstatusModel = new TrendingModel();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1 = json.getJSONObject(EXTRA);
                if(jsonObject1 != null){tmpstatusModel.toObject(jsonObject1.toString());}
                traTrendingModel = tmpstatusModel;
            }


            if (json.has(SERVICES)) {
                try {
                    ServicesListModel servicesListModel = new ServicesListModel();
                    if (servicesListModel.toObject(json.getJSONArray(SERVICES))) {
                        this.servicesListModel = servicesListModel;
                    }
                } catch (Exception exx) {
                    this.servicesListModel = null;
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
            jsonMain.put(NAME, name);
            jsonMain.put(ICON, icon);
            jsonMain.put(OFFER_TITLE, offer_title);
            jsonMain.put(OFFER_APPLICABLE, offer_applicable);
            jsonMain.put(OFFER_DISCOUNT, offer_discount);
            jsonMain.put(VENDOR_NAME, vendor_name);
            jsonMain.put(COUNT, count);
            jsonMain.put(EXTRA, traTrendingModel != null ? new JSONObject(this.traTrendingModel.toString()) : new JSONObject());
            jsonMain.put(SERVICES, servicesListModel != null ? new JSONArray(servicesListModel.toString(true)) : new JSONArray());
            returnString = jsonMain.toString();
            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
