package com.sauthi.gograb.services.model;

import android.util.Log;


import com.sauthi.gograb.MainActivity;
import com.sauthi.gograb.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewsListModel implements Serializable {

    private final String TAG = "NewsListModel";

    private final String RESPONSE = "response";

    List<NewsModel> newsList = new ArrayList<NewsModel>();

    public NewsListModel(){}

    public NewsListModel(List<NewsModel> list){
        this.newsList = list;
    }

    public List<NewsModel> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsModel> newsList) {
        this.newsList = newsList;
    }

    public List<String> getIDs(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.newsList.size(); i++){
            list.add(newsList.get(i).getId());
        }
        return list;
    }

    public List<String> getTitles(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.newsList.size(); i++){
            list.add(newsList.get(i).getTitle());
        }
        return list;
    }

    public List<String> getDescriptions(){
        List<String> list = new ArrayList<String>();
        for(int i =0 ;i<this.newsList.size(); i++){
            list.add(newsList.get(i).getDescription());
        }
        return list;
    }

    public boolean toObject(String jsonObjectString){
        try{
            JSONObject json = new JSONObject(jsonObjectString);
            JSONArray array = json.getJSONArray(RESPONSE);
            List<NewsModel> list = new ArrayList<NewsModel>();
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                NewsModel newsModel = new NewsModel();
                if(newsModel.toObject(jsonObject.toString())){list.add(newsModel);}
                else{throw new Exception(new Throwable(MainActivity.mainContext.getString(R.string.not_valid_model)));}
            }
            this.newsList = list;
            return true;
        }catch(Exception ex){
            Log.d(TAG, "Json Exception : " + ex);
            return false;}

    }

    public boolean toObject(JSONArray jsonArray){
        try{
            List<NewsModel> list = new ArrayList<NewsModel>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                NewsModel newsModel = new NewsModel();
                newsModel.toObject(jsonObject.toString());
                list.add(newsModel);
            }
            this.newsList = list;
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
            List<NewsModel> list = this.newsList;
            for(int i=0;i<list.size();i++){
                jsonArray.put(new JSONObject(list.get(i).toString()));
            }
            jsonMain.put(RESPONSE, jsonArray);
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
            List<NewsModel> list = this.newsList;
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
