package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FeedbackListModel implements Serializable {

    private final String TAG = "FeedbackListModel";

    private final String RESPONSE = "response";

    List<FeedbackModel> feedbackList = new ArrayList<FeedbackModel>();

    public FeedbackListModel(){}

    public List<FeedbackModel> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<FeedbackModel> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<FeedbackModel> list = new ArrayList<FeedbackModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                FeedbackModel model = new FeedbackModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.feedbackList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);}
        return false;
    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<FeedbackModel> list = new ArrayList<FeedbackModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                FeedbackModel model = new FeedbackModel();
                model.toObject(jsonObject.toString());
                list.add(model);
            }
            this.feedbackList = list;
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
            List<FeedbackModel> list = this.feedbackList;
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
            List<FeedbackModel> list = this.feedbackList;
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
