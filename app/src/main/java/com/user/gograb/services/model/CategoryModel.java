package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class CategoryModel implements Serializable {

    private final String TAG = "CategoryModel";

    private final String
            ID = "id",
            NAME = "title",
            ICON = "image",
            COUNT = "count",
            SERVICES = "service";

    String
            id = null,
            name = null,
            count = null,
            icon = null;

    ServicesListModel
            servicesListModel = null;


    public CategoryModel() {
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ServicesListModel getServicesListModel() {
        return servicesListModel;
    }

    public void setServicesListModel(ServicesListModel servicesListModel) {
        this.servicesListModel = servicesListModel;
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

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);
            id = json.getString(ID);
            if (json.has(NAME)) name = json.getString(NAME);
            if (json.has(ICON)) icon = json.getString(ICON);
            if (json.has(COUNT)) count = json.getString(COUNT);

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
            jsonMain.put(COUNT, count);

            jsonMain.put(SERVICES, servicesListModel != null ? new JSONArray(servicesListModel.toString(true)) : new JSONArray());
            returnString = jsonMain.toString();
            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
