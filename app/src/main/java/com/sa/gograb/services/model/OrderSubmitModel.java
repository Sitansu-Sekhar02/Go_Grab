package com.sa.gograb.services.model;

import android.util.Log;


import com.sa.gograb.global.GlobalVariables;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class OrderSubmitModel implements Serializable {

    private final String TAG = "OrderSubmitModel";

    private final String
            CITY                         = "city",
            ORDER_FROM                   = "order_from",
            RESIDENCE_TYPE               = "residence_type",
            SERVICE                      = "service",
            PLAN_LIST                    = "cars",
            ADDRESS                      = "address";


    String order_from = "2";

    public String getOrder_from() {
        return order_from;
    }

    public void setOrder_from(String order_from) {
        this.order_from = order_from;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(ORDER_FROM)){this.order_from = json.getString(ORDER_FROM);}





          /*  if(json.has(APARTMENT)){
                ApartmentModel modelLocal = new ApartmentModel();
                if(modelLocal.toObject(json.getJSONObject(APARTMENT).toString())){
                    this.apartmentModel = modelLocal;
                }
            }*/

            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);
            return false;}

    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(ORDER_FROM, order_from);

//            jsonMain.put(CITY, this.cityModel != null? new JSONObject(this.cityModel.toString()) : new JSONObject());
//            jsonMain.put(RESIDENCE_TYPE, this.residenceModel != null? new JSONObject(this.residenceModel.toString()) : new JSONObject());
//           // jsonMain.put(APARTMENT, this.apartmentModel != null? new JSONObject(this.apartmentModel.toString()) : new JSONObject());
//            jsonMain.put(SERVICE, this.serviceModel != null? new JSONObject(this.serviceModel.toString()) : new JSONObject());
//            jsonMain.put(ADDRESS, this.addressModel != null? new JSONObject(this.addressModel.toString()) : new JSONObject());
//            jsonMain.put(PLAN_LIST, this.createPlanList != null? new JSONArray(this.createPlanList.toString(true)) : new JSONArray());

            returnString = jsonMain.toString();}
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
