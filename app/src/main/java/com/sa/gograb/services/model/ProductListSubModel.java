package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductListSubModel {
    private final String TAG = "ProductListSubModel";

    private final String
            PRODUCT_LIST             = "product_list";


    ProductListModel
            productListModel       = null;

    public ProductListSubModel(){}


    public ProductListModel getProductListModel() {
        return productListModel;
    }

    public void setProductListModel(ProductListModel productListModel) {
        this.productListModel = productListModel;
    }


    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);

            if(json.has(PRODUCT_LIST)) {
                JSONArray array = json.getJSONArray(PRODUCT_LIST);
                ProductListModel listModelLocal = new ProductListModel();
                listModelLocal.setRESPONSE(PRODUCT_LIST);
                if(listModelLocal.toObject(array)){this.productListModel = listModelLocal;}
                else{this.productListModel = null;}
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
            jsonMain.put(PRODUCT_LIST, productListModel!=null?new JSONArray(productListModel.toString(true)):null);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}


