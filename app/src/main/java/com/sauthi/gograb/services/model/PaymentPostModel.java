package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class PaymentPostModel implements Serializable {

    private final String TAG = "PaymentPostModel";

    private final String
            INVOICE_VALUE           = "invoice_value",
            CREATED_DATE            = "created_date",
            CARD_BRAND              = "card_brand",
            RECURRING_ID            = "recurring_id",
            INVOICE_STATUS          = "invoice_status",
            CUSTOMER_MOBILE         = "customer_mobile",
            TOKEN                   = "token",
            INVOICE_ID              = "invoice_id",
            EXPIRY_MONTH            = "expiry_month",
            INVOICE_AMOUNT          = "invoice_amount",
            EXPIRY_YEAR             = "expiry_year",
            CUSTOMER_REFERENCE      = "customer_reference",
            PAYMENT_EXPIRY_DATE     = "payment_expiry_date",
            CARD_ISSUER             = "card_issuer",
            INVOICE_REFERENCE       = "invoice_reference",
            CUSTOMER_EMAIL          = "customer_email",
            CARD_NUMBER             = "card_number",
            CUSTOMER_NAME           = "customer_name";

    String
            invoiceValue         = null,
            createdDate          = null,
            cardBrand            = null,
            recurringId          = null,
            invoiceStatus        = null,
            customerMobile       = null,
            token                = null,
            invoiceId            = null,
            expiryMonth          = null,
            invoiceAmount        = null,
            expiryYear           = null,
            customerReference    = null,
            paymentExpiryDate    = null,
            cardIssuer           = null,
            invoiceReference     = null,
            customerEmail        = null,
            cardNumber           = null,
            customerName         = null;

    public PaymentPostModel(){}

    public String getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(String invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public String getRecurringId() {
        return recurringId;
    }

    public void setRecurringId(String recurringId) {
        this.recurringId = recurringId;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public String getPaymentExpiryDate() {
        return paymentExpiryDate;
    }

    public void setPaymentExpiryDate(String paymentExpiryDate) {
        this.paymentExpiryDate = paymentExpiryDate;
    }

    public String getCardIssuer() {
        return cardIssuer;
    }

    public void setCardIssuer(String cardIssuer) {
        this.cardIssuer = cardIssuer;
    }

    public String getInvoiceReference() {
        return invoiceReference;
    }

    public void setInvoiceReference(String invoiceReference) {
        this.invoiceReference = invoiceReference;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);

            if(json.has(INVOICE_VALUE)){this.invoiceValue = json.getString(INVOICE_VALUE);}
            if(json.has(CREATED_DATE)){this.createdDate = json.getString(CREATED_DATE);}
            if(json.has(CARD_BRAND)){this.cardBrand = json.getString(CARD_BRAND);}
            if(json.has(RECURRING_ID)){this.recurringId = json.getString(RECURRING_ID);}
            if(json.has(INVOICE_STATUS)){this.invoiceStatus = json.getString(INVOICE_STATUS);}
            if(json.has(CUSTOMER_MOBILE)){this.customerMobile = json.getString(CUSTOMER_MOBILE);}
            if(json.has(TOKEN)){this.token = json.getString(TOKEN);}
            if(json.has(INVOICE_ID)){this.invoiceId = json.getString(INVOICE_ID);}
            if(json.has(EXPIRY_MONTH)){this.expiryMonth = json.getString(EXPIRY_MONTH);}
            if(json.has(INVOICE_AMOUNT)){this.invoiceAmount = json.getString(INVOICE_AMOUNT);}
            if(json.has(EXPIRY_YEAR)){this.expiryYear = json.getString(EXPIRY_YEAR);}
            if(json.has(CUSTOMER_REFERENCE)){this.customerReference = json.getString(CUSTOMER_REFERENCE);}
            if(json.has(PAYMENT_EXPIRY_DATE)){this.paymentExpiryDate = json.getString(PAYMENT_EXPIRY_DATE);}
            if(json.has(CARD_ISSUER)){this.cardIssuer = json.getString(CARD_ISSUER);}
            if(json.has(INVOICE_REFERENCE)){this.invoiceReference = json.getString(INVOICE_REFERENCE);}
            if(json.has(CUSTOMER_EMAIL)){this.customerEmail = json.getString(CUSTOMER_EMAIL);}
            if(json.has(CARD_NUMBER)){this.cardNumber = json.getString(CARD_NUMBER);}
            if(json.has(CUSTOMER_NAME)){this.customerName = json.getString(CUSTOMER_NAME);}

            return true;
        }catch(Exception ex){Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();

            jsonMain.put(INVOICE_VALUE, invoiceValue);
            jsonMain.put(CREATED_DATE, createdDate);
            jsonMain.put(CARD_BRAND, cardBrand);
            jsonMain.put(RECURRING_ID, recurringId);
            jsonMain.put(INVOICE_STATUS, invoiceStatus);
            jsonMain.put(CUSTOMER_MOBILE, customerMobile);
            jsonMain.put(TOKEN, token);
            jsonMain.put(INVOICE_ID, invoiceId);
            jsonMain.put(EXPIRY_MONTH, expiryMonth);
            jsonMain.put(INVOICE_AMOUNT, invoiceAmount);
            jsonMain.put(EXPIRY_YEAR, expiryYear);
            jsonMain.put(CUSTOMER_REFERENCE, customerReference);
            jsonMain.put(PAYMENT_EXPIRY_DATE, paymentExpiryDate);
            jsonMain.put(CARD_ISSUER, cardIssuer);
            jsonMain.put(INVOICE_REFERENCE, invoiceReference);
            jsonMain.put(CUSTOMER_EMAIL, customerEmail);
            jsonMain.put(CARD_NUMBER, cardNumber);
            jsonMain.put(CUSTOMER_NAME, customerName);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

