package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class BankDetailsModel implements Serializable {

    private final String TAG = "BankDetailsModel";
    private final String
            BANK_NAME             = "bank_name",
            BRANCH                = "branch",
            ACCOUNT_NUMBER        = "account_number",
            SWIFT_CODE            = "swift_code",
            ACCOUNT_HOLDER_NAME   = "account_holder_name",
            IBAN                  = "iban";

    String
            bankName              = null,
            branch                = null,
            accountNumber         = null,
            swiftCode             = null,
            accountHolderName     = null,
            iban                  = null;

    public BankDetailsModel(){}

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(BANK_NAME)){this.bankName = json.getString(BANK_NAME);}
            if(json.has(BRANCH)){this.branch = json.getString(BRANCH);}
            if(json.has(ACCOUNT_NUMBER)){this.accountNumber = json.getString(ACCOUNT_NUMBER);}
            if(json.has(SWIFT_CODE)){this.swiftCode = json.getString(SWIFT_CODE);}
            if(json.has(ACCOUNT_HOLDER_NAME)){this.accountHolderName = json.getString(ACCOUNT_HOLDER_NAME);}
            if(json.has(IBAN)){this.iban = json.getString(IBAN);}

            return true;
        }catch(Exception ex){Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    @Override
    public String toString(){
        String returnString = null;
        try{
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(BANK_NAME, bankName);
            jsonMain.put(BRANCH, branch);
            jsonMain.put(ACCOUNT_NUMBER, accountNumber);
            jsonMain.put(SWIFT_CODE, swiftCode);
            jsonMain.put(ACCOUNT_HOLDER_NAME, accountHolderName);
            jsonMain.put(IBAN, iban);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}

