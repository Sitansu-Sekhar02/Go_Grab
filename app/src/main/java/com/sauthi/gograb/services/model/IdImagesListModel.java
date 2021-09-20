package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IdImagesListModel implements Serializable {

    private final String TAG = "IdImagesListModel";

    private final String RESPONSE = "id_images";

    List<IdImageModel> idImageList = new ArrayList<IdImageModel>();

    public IdImagesListModel(){}

    public List<IdImageModel> getIdImageList() {
        return idImageList;
    }

    public void setIdImageList(List<IdImageModel> idImageList) {
        this.idImageList = idImageList;
    }

    public List<String> getImages(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.idImageList.size(); i++){
            list.add(idImageList.get(i).getImage());
        }
        return list;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<IdImageModel> list = new ArrayList<IdImageModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                IdImageModel model = new IdImageModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.idImageList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<IdImageModel> list = new ArrayList<IdImageModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                IdImageModel model = new IdImageModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.idImageList = list;
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
            JSONArray jsonArray = new JSONArray();
            List<IdImageModel> list = this.idImageList;
            for(int i=0;i<list.size();i++){
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            jsonMain.put(RESPONSE, jsonArray);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

    public String toString(boolean isArray){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            List<IdImageModel> list = this.idImageList;
            for(int i=0;i<list.size();i++){
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            if(!isArray){
                jsonMain.put(RESPONSE, jsonArray);
                returnString = jsonMain.toString();
            }else{
                returnString = jsonArray.toString();
            }

        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
