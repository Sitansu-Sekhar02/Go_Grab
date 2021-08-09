package com.sa.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class NotificationSettingsModel implements Serializable {

    private final String TAG = "NotificationSetModel";
    private final String
            SHOW_NOTIFICATION       = "show_notification",
            PLAY_SOUND              = "play_sound",
            VIBRATE                 = "vibrate",
            LED_INDICATOR           = "led_indicator";

    boolean
            showNotification        = true,
            playSound               = true,
            vibrate                 = true,
            ledIndicator            = true;

    public NotificationSettingsModel(){}

    public boolean isShowNotification() {
        return showNotification;
    }

    public void setShowNotification(boolean showNotification) {
        this.showNotification = showNotification;
    }

    public boolean isPlaySound() {
        return playSound;
    }

    public void setPlaySound(boolean playSound) {
        this.playSound = playSound;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public boolean isLedIndicator() {
        return ledIndicator;
    }

    public void setLedIndicator(boolean ledIndicator) {
        this.ledIndicator = ledIndicator;
    }

    public boolean toObject(String jsonObject){
        try{
            JSONObject json = new JSONObject(jsonObject);
            if(json.has(SHOW_NOTIFICATION)){
                try{this.showNotification = json.getBoolean(SHOW_NOTIFICATION);}
                catch (Exception exx){this.showNotification = true;}
            }
            if(json.has(PLAY_SOUND)){
                try{this.playSound = json.getBoolean(PLAY_SOUND);}
                catch (Exception exx){this.playSound = true;}
            }
            if(json.has(VIBRATE)){
                try{this.vibrate = json.getBoolean(VIBRATE);}
                catch (Exception exx){this.vibrate = true;}
            }
            if(json.has(LED_INDICATOR)){
                try{this.ledIndicator = json.getBoolean(LED_INDICATOR);}
                catch (Exception exx){this.ledIndicator = true;}
            }
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
            jsonMain.put(SHOW_NOTIFICATION, this.showNotification);
            jsonMain.put(VIBRATE, this.vibrate);
            jsonMain.put(PLAY_SOUND, this.playSound);
            jsonMain.put(LED_INDICATOR, this.ledIndicator);
            returnString = jsonMain.toString();
        }
        catch (Exception ex){
            Log.d(TAG," To String Exception : "+ex);}
        return returnString;
    }
}
