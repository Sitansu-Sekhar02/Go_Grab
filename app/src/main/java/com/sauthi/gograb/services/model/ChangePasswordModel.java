package com.sauthi.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class ChangePasswordModel implements Serializable {

    private final String TAG = "ChangePasswordModel";
    private final String
            CURRENT_PASSWORD = "old_password",
            NEW_PASSWORD     = "password",
            CONFIRM_PASSWORD = "confirm_new_password",
            MOBILE_NUMBER    = "mobile_number";

    String currentPassword = null, newPassword = null, confirmPassword = null, mobileNumber = null;

    public ChangePasswordModel() {
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);
            this.currentPassword = json.getString(CURRENT_PASSWORD);
            this.newPassword = json.getString(NEW_PASSWORD);
            this.confirmPassword = json.getString(CONFIRM_PASSWORD);
            this.mobileNumber = json.getString(MOBILE_NUMBER);
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
            jsonMain.put(CURRENT_PASSWORD, this.currentPassword);
            jsonMain.put(NEW_PASSWORD, this.newPassword);
            jsonMain.put(CONFIRM_PASSWORD, confirmPassword);
            jsonMain.put(MOBILE_NUMBER, mobileNumber);
            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
