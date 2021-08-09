package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddVideoListModel implements Serializable {

    private final String TAG = "AddVideoListModel";

    private final String RESPONSE = "videos";

    List<AddGalleryModel> addVideoList = new ArrayList<AddGalleryModel>();

    public AddVideoListModel() {
    }

    public List<AddGalleryModel> getAddVideoList() {
        return addVideoList;
    }

    public void setAddVideoList(List<AddGalleryModel> addVideoList) {
        this.addVideoList = addVideoList;
    }

    public List<String> getVideo() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < this.addVideoList.size(); i++) {
            list.add(addVideoList.get(i).getLink());
        }
        return list;
    }

    public boolean toObject(String jsonObjectString) {
        try {
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<AddGalleryModel> list = new ArrayList<AddGalleryModel>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                AddGalleryModel model = new AddGalleryModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.addVideoList = list;
            return true;
        } catch (Exception ex) {
            Log.d(TAG, "Json Exception : " + ex);
        }
        return false;
    }

    public boolean toObject(JSONArray jsonArray) {
        try {
            List<AddGalleryModel> list = new ArrayList<AddGalleryModel>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                AddGalleryModel model = new AddGalleryModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.addVideoList = list;
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
            List<AddGalleryModel> list = this.addVideoList;
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
            List<AddGalleryModel> list = this.addVideoList;
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
