package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductImagesListModel implements Serializable {

    private final String TAG = "ProductImagesListModel";

    private final String RESPONSE = "media";

    List<ProductImageModel> productImageList = new ArrayList<ProductImageModel>();

    public ProductImagesListModel() {
    }

    public List<ProductImageModel> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(List<ProductImageModel> productImageList) {
        this.productImageList = productImageList;
    }

    public List<String> getImages() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < this.productImageList.size(); i++) {
            list.add(productImageList.get(i).getImage());
        }
        return list;
    }

    public boolean toObject(String jsonObjectString) {
        try {
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<ProductImageModel> list = new ArrayList<ProductImageModel>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                ProductImageModel model = new ProductImageModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.productImageList = list;
            return true;
        } catch (Exception ex) {
            Log.d(TAG, "Json Exception : " + ex);
        }
        return false;
    }

    public boolean toObject(JSONArray jsonArray) {
        try {
            List<ProductImageModel> list = new ArrayList<ProductImageModel>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ProductImageModel model = new ProductImageModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.productImageList = list;
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
            JSONArray jsonArray = new JSONArray();
            List<ProductImageModel> list = this.productImageList;
            for (int i = 0; i < list.size(); i++) {
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            jsonMain.put(RESPONSE, jsonArray);
            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }

    public String toString(boolean isArray) {
        String returnString = null;
        try {
            JSONObject jsonMain = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            List<ProductImageModel> list = this.productImageList;
            for (int i = 0; i < list.size(); i++) {
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            if (!isArray) {
                jsonMain.put(RESPONSE, jsonArray);
                returnString = jsonMain.toString();
            } else {
                returnString = jsonArray.toString();
            }

        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
