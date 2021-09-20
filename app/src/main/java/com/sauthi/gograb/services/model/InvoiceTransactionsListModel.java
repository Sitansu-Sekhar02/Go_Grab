package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InvoiceTransactionsListModel implements Serializable {

    private final String TAG = "InvoiceTransactionsListModel";

    private  String RESPONSE = "InvoiceTransactions";

    List<InvoiceTransactionModel> invoiceTransactionsList = new ArrayList<InvoiceTransactionModel>();

    public InvoiceTransactionsListModel(){}

    public List<InvoiceTransactionModel> getInvoiceTransactionsList() {
        return invoiceTransactionsList;
    }

    public void setInvoiceTransactionsList(List<InvoiceTransactionModel> invoiceTransactionsList) {
        this.invoiceTransactionsList = invoiceTransactionsList;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<InvoiceTransactionModel> list = new ArrayList<InvoiceTransactionModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                InvoiceTransactionModel keyValueModel = new InvoiceTransactionModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.invoiceTransactionsList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<InvoiceTransactionModel> list = new ArrayList<InvoiceTransactionModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                InvoiceTransactionModel keyValueModel = new InvoiceTransactionModel();
                keyValueModel.toObject(jsonObject.toString());
                list.add(keyValueModel);
            }
            this.invoiceTransactionsList = list;
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
            List<InvoiceTransactionModel> list = this.invoiceTransactionsList;
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
            List<InvoiceTransactionModel> list = this.invoiceTransactionsList;
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
