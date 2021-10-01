package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class InvoiceTransactionModel implements Serializable {

    private final String TAG = "InvoiceTransactionModel";

    private final String
            AUTHORIZATION_ID               = "AuthorizationId",
            CURRENCY                       = "Currency",
            CUSTOMER_SERVICE_CHARGE        = "CustomerServiceCharge",
            PAID_CURRENCY                  = "PaidCurrency",
            PAID_CURRENCY_VALUE            = "PaidCurrencyValue",
            PAYMENT_GATEWAY                = "PaymentGateway",
            PAYMENT_ID                     = "PaymentId",
            REFERENCE_ID                   = "ReferenceId",
            TRACK_ID                       = "TrackId",
            TRANSACTION_DATE               = "TransactionDate",
            TRANSACTION_ID                 = "TransactionId",
            TRANSACTION_STATUS             = "TransactionStatus",
            TRANSACTION_VALUE              = "TransationValue";

    String
            authorizationId               = null,
            currency                      = null,
            customerServiceCharge         = null,
            paidCurrency                  = null,
            paidCurrencyValue             = null,
            paymentGateway                = null,
            paymentId                     = null,
            referenceId                   = null,
            trackId                       = null,
            transactionDate               = null,
            transactionId                 = null,
            transactionStatus             = null,
            transationValue                = null;

    public InvoiceTransactionModel() {
    }

    public String getAuthorizationId() {
        return authorizationId;
    }

    public void setAuthorizationId(String authorizationId) {
        this.authorizationId = authorizationId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerServiceCharge() {
        return customerServiceCharge;
    }

    public void setCustomerServiceCharge(String customerServiceCharge) {
        this.customerServiceCharge = customerServiceCharge;
    }

    public String getPaidCurrency() {
        return paidCurrency;
    }

    public void setPaidCurrency(String paidCurrency) {
        this.paidCurrency = paidCurrency;
    }

    public String getPaidCurrencyValue() {
        return paidCurrencyValue;
    }

    public void setPaidCurrencyValue(String paidCurrencyValue) {
        this.paidCurrencyValue = paidCurrencyValue;
    }

    public String getPaymentGateway() {
        return paymentGateway;
    }

    public void setPaymentGateway(String paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransationValue() {
        return transationValue;
    }

    public void setTransationValue(String transationValue) {
        this.transationValue = transationValue;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);

            if (json.has(AUTHORIZATION_ID)) authorizationId = json.getString(AUTHORIZATION_ID);
            if (json.has(CURRENCY)) currency = json.getString(CURRENCY);
            if (json.has(CUSTOMER_SERVICE_CHARGE)) customerServiceCharge = json.getString(CUSTOMER_SERVICE_CHARGE);
            if (json.has(PAID_CURRENCY)) paidCurrency = json.getString(PAID_CURRENCY);
            if (json.has(PAID_CURRENCY_VALUE)) paidCurrencyValue = json.getString(PAID_CURRENCY_VALUE);
            if (json.has(PAYMENT_GATEWAY)) paymentGateway = json.getString(PAYMENT_GATEWAY);
            if (json.has(PAYMENT_ID)) paymentId = json.getString(PAYMENT_ID);
            if (json.has(REFERENCE_ID)) referenceId = json.getString(REFERENCE_ID);
            if (json.has(TRACK_ID)) trackId = json.getString(TRACK_ID);
            if (json.has(TRANSACTION_DATE)) transactionDate = json.getString(TRANSACTION_DATE);
            if (json.has(TRANSACTION_ID)) transactionId = json.getString(TRANSACTION_ID);
            if (json.has(TRANSACTION_STATUS)) transactionStatus = json.getString(TRANSACTION_STATUS);
            if (json.has(TRANSACTION_VALUE)) transationValue = json.getString(TRANSACTION_VALUE);

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
            jsonMain.put(AUTHORIZATION_ID, authorizationId);
            jsonMain.put(CURRENCY, currency);
            jsonMain.put(CUSTOMER_SERVICE_CHARGE, customerServiceCharge);
            jsonMain.put(PAID_CURRENCY, paidCurrency);
            jsonMain.put(PAID_CURRENCY_VALUE, paidCurrencyValue);
            jsonMain.put(PAYMENT_GATEWAY, paymentGateway);
            jsonMain.put(PAYMENT_ID, paymentId);
            jsonMain.put(REFERENCE_ID, referenceId);
            jsonMain.put(TRACK_ID, trackId);
            jsonMain.put(TRANSACTION_DATE, transactionDate);
            jsonMain.put(TRANSACTION_ID, transactionId);
            jsonMain.put(TRANSACTION_STATUS, transactionStatus);
            jsonMain.put(TRANSACTION_VALUE, transationValue);

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
