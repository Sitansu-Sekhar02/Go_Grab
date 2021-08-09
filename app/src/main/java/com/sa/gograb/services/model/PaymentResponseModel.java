package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class PaymentResponseModel implements Serializable {

    private final String TAG = "PaymentResponseModel";

    private final String
            COMMENTS                = "Comments",
            CREATED_DATE            = "CreatedDate",
            CUSTOMER_MOBILE         = "CustomerMobile",
            CUSTOMER_NAME           = "CustomerName",
            CUSTOMER_REFERENCE      = "CustomerReference",
            EXPIRY_DATE             = "ExpiryDate",
            INVOICE_DISPLAY_VALUE   = "InvoiceDisplayValue",
            INVOICE_ID              = "InvoiceId",
            INVOICE_ITEMS           = "InvoiceItems",
            INVOICE_REFERENCE       = "InvoiceReference",
            INVOICE_STATUS          = "InvoiceStatus",
            INVOICE_TRANSACTIONS    = "InvoiceTransactions",
            INVOICE_VALUE           = "InvoiceValue";

    String
            comments                = null,
            createdDate             = null,
            customerMobile          = null,
            customerName            = null,
            expiryDate              = null,
            invoiceDisplayValue     = null,
            invoiceId               = null,
            invoiceReference        = null,
            invoiceStatus           = null,
            invoiceValue            = null,
            customerReference       = null;

    InvoiceTransactionsListModel
            invoiceTransactionsList    = null;

    public PaymentResponseModel(){}

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getInvoiceDisplayValue() {
        return invoiceDisplayValue;
    }

    public void setInvoiceDisplayValue(String invoiceDisplayValue) {
        this.invoiceDisplayValue = invoiceDisplayValue;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceReference() {
        return invoiceReference;
    }

    public void setInvoiceReference(String invoiceReference) {
        this.invoiceReference = invoiceReference;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(String invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public InvoiceTransactionsListModel getInvoiceTransactionsList() {
        return invoiceTransactionsList;
    }

    public void setInvoiceTransactionsList(InvoiceTransactionsListModel invoiceTransactionsList) {
        this.invoiceTransactionsList = invoiceTransactionsList;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if(json.has(COMMENTS)){this.comments = json.getString(COMMENTS);}
            if(json.has(CREATED_DATE)){this.createdDate = json.getString(CREATED_DATE);}
            if(json.has(CUSTOMER_MOBILE)){this.customerMobile = json.getString(CUSTOMER_MOBILE);}
            if(json.has(CUSTOMER_NAME)){this.customerName = json.getString(CUSTOMER_NAME);}
            if(json.has(EXPIRY_DATE)){this.expiryDate = json.getString(EXPIRY_DATE);}
            if(json.has(INVOICE_DISPLAY_VALUE)){this.invoiceDisplayValue = json.getString(INVOICE_DISPLAY_VALUE);}
            if(json.has(INVOICE_ID)){this.invoiceId = json.getString(INVOICE_ID);}
            if(json.has(INVOICE_REFERENCE)){this.invoiceReference = json.getString(INVOICE_REFERENCE);}
            if(json.has(INVOICE_STATUS)){this.invoiceStatus = json.getString(INVOICE_STATUS);}
            if(json.has(INVOICE_VALUE)){this.invoiceValue = json.getString(INVOICE_VALUE);}
            if(json.has(CUSTOMER_REFERENCE)){this.customerReference = json.getString(CUSTOMER_REFERENCE);}

            if(json.has(INVOICE_TRANSACTIONS)) {
                JSONArray array = json.getJSONArray(INVOICE_TRANSACTIONS);
                InvoiceTransactionsListModel listModelLocal = new InvoiceTransactionsListModel();
                if(listModelLocal.toObject(array)){this.invoiceTransactionsList = listModelLocal;}
                else{this.invoiceTransactionsList = null;}
            }

            return true;
        }catch(Exception ex){Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();

            jsonMain.put(COMMENTS, comments);
            jsonMain.put(CREATED_DATE, createdDate);
            jsonMain.put(CUSTOMER_MOBILE, customerMobile);
            jsonMain.put(CUSTOMER_NAME, customerName);
            jsonMain.put(EXPIRY_DATE, expiryDate);
            jsonMain.put(INVOICE_DISPLAY_VALUE, invoiceDisplayValue);
            jsonMain.put(INVOICE_ID, invoiceId);
            jsonMain.put(INVOICE_REFERENCE, invoiceReference);
            jsonMain.put(INVOICE_STATUS, invoiceStatus);
            jsonMain.put(INVOICE_VALUE, invoiceValue);
            jsonMain.put(CUSTOMER_REFERENCE, customerReference);
            jsonMain.put(INVOICE_TRANSACTIONS, invoiceTransactionsList!=null?new JSONArray(invoiceTransactionsList.toString(true)):null);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

