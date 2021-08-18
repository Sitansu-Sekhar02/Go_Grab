package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class AddInstructionModel implements Serializable {
    private final String TAG = "AddInstructionModel";

    private final String
            INSTRUCTION           = "instruction";

    String
            instruction             = null;

    public AddInstructionModel(){}

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(INSTRUCTION)){this.instruction = json.getString(INSTRUCTION);}

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
            jsonMain.put(INSTRUCTION, this.instruction);

            returnString = jsonMain.toString();
        }
        catch (Exception ex){Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }

}
