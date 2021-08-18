package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailListModel implements Serializable {

    private final String TAG = "OrderDetailListModel";

    private final String RESPONSE = "order_details";

    List<OrderDetailModel> orderDetailModels = new ArrayList<OrderDetailModel>();

    public OrderDetailListModel(){}

    public List<OrderDetailModel> getOrderDetailModels() {
        return orderDetailModels;
    }

    public void setOrderDetailModels(List<OrderDetailModel> orderDetailModels) {
        this.orderDetailModels = orderDetailModels;
    }

    public List<String> getNames(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.orderDetailModels.size(); i++){
            list.add(orderDetailModels.get(i).getName() +" x "+orderDetailModels.get(i).getQuantity());
        }
        return list;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<OrderDetailModel> list = new ArrayList<OrderDetailModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                OrderDetailModel keyValueModel = new OrderDetailModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.orderDetailModels = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<OrderDetailModel> list = new ArrayList<OrderDetailModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                OrderDetailModel keyValueModel = new OrderDetailModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.orderDetailModels = list;
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
            List<OrderDetailModel> list = this.orderDetailModels;
            for(int i=0;i<list.size();i++){
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            jsonMain.put(RESPONSE, jsonArray);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

    public String toString(boolean isArray){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            List<OrderDetailModel> list = this.orderDetailModels;
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
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
