package com.sauthi.gograb.services.model;

import android.util.Log;


import com.sauthi.gograb.MainActivity;
import com.sauthi.gograb.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlanListModel implements Serializable {

    private final String TAG = "PlanListModel";
    private final String
            COUNT                       = "count";
    String count                       =null;

    private final String RESPONSE = "cars";

    List<PlanModel> planList = new ArrayList<PlanModel>();

    public PlanListModel(){}

    public PlanListModel(List<PlanModel> list){
        this.planList = list;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<PlanModel> getPlanList() {
        return planList;
    }

    public void setPlanList(List<PlanModel> planList) {
        this.planList = planList;
    }

    public List<String> getIDs(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.planList.size(); i++){
            list.add(planList.get(i).getId());
        }
        return list;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            if (json.has(COUNT))this.count = json.getString(COUNT);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<PlanModel> list = new ArrayList<PlanModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                PlanModel planModel = new PlanModel();
                if(planModel.toObject(jsonObject.toString())){list.add(planModel);}
                else{throw new Exception(new Throwable(MainActivity.mainContext.getString(R.string.not_valid_model)));}
            }
            this.planList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);
            return false;}

    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<PlanModel> list = new ArrayList<PlanModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PlanModel planModel = new PlanModel();
                planModel.toObject(jsonObject.toString());
                list.add(planModel);
            }
            this.planList = list;
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
            JSONArray jsonArray = new JSONArray();
            List<PlanModel> list = this.planList;
            for(int i=0;i<list.size();i++){
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            jsonMain.put(RESPONSE, jsonArray);
            jsonMain.put(COUNT, this.count);
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
            List<PlanModel> list = this.planList;
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
