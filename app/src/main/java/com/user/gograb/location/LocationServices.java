package com.user.gograb.location;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.ActivityCompat;


import com.user.gograb.global.GlobalFunctions;
import com.user.gograb.global.GlobalVariables;

import java.util.TimerTask;


public class LocationServices extends Service {
    private static final String TAG = "LocationServices";
    public static final String LOCATION_SERVICE = "com.yin.driver.location.LOCATION_SERVICE";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 10;
    private static final float LOCATION_DISTANCE = 0f;

    private static final long LOCATION_WAIT_TIME = 10000;// 10 seconds
    private Handler handler = new Handler();
    private Runnable locationTimeCheck = new Runnable() {
        @Override
        public void run() {
         //   listener.OnLocationFailure(getString(R.string.locationResponseFailure));
        }
    };

    static LocationListener listener;

    boolean canGetLocation = true;
    static Location mLastLocation;

    public LocationServices(){
        //initializeLocationManager();
    }

    public LocationListener getListener() {
        return listener;
    }

    private void startLocationTimer(){
        if(handler!=null && locationTimeCheck!=null){handler.postDelayed(locationTimeCheck,LOCATION_WAIT_TIME);}
    }

    private void stopLocationTimer(){
        if(handler!=null && locationTimeCheck!=null){handler.removeCallbacks(locationTimeCheck);}
    }

    public void setListener(LocationListener listener) {
        this.listener = listener;
    }

    private class LocationListener implements android.location.LocationListener {

        public LocationListener(String provider) {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.e(TAG, "onLocationChanged: " + location);
            mLastLocation=location;
            Log.d(TAG, "location :"+mLastLocation.getLatitude()+"--"+mLastLocation.getLongitude());
          //  if(listener!=null)listener.OnLocationFetch(mLastLocation);
        }

        @Override
        public void onProviderDisabled(String provider) {
            canGetLocation = false;
            Log.e(TAG, "onProviderDisabled: " + provider);
           // if(listener!=null)listener.OnProviderDisabled(provider);
        }

        @Override
        public void onProviderEnabled(String provider) {
            canGetLocation = true;
            Log.e(TAG, "onProviderEnabled: " + provider);
            //if(listener!=null)listener.OnProviderEnabled(provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged: " + provider);
        }
    }

    LocationListener[] mLocationListeners = new LocationListener[]{
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        startLocationTimer();
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        stopLocationTimer();
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
        if(!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)&&!mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            canGetLocation=false;
        }else{
            canGetLocation=true;
        }
    }

    public boolean canGetLocation(Context context){
        this.canGetLocation = GlobalFunctions.isGPSEnabled(context);
        return this.canGetLocation;
    }

    public void setCanGetLocation(boolean canGetLocation){
        this.canGetLocation = canGetLocation;
    }

    /**
     * Function to show settings alert dialog
     * On pressing Settings button will lauch Settings Options
     * */
    public void showSettingsAlert(final Activity activity){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                activity.startActivityForResult(intent, GlobalVariables.REQUEST_GPS_ENABLED);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
              //  if(listener!=null){listener.OnLocationFailure("GPS Cancelled");}
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public boolean checkPermission(Activity activity){
        return ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public double getLatitude(){
        return mLastLocation.getLatitude();
    }

    public double getLongitude(){
        return mLastLocation.getLongitude();
    }

    class LocationWaitTimer extends TimerTask {

        @Override
        public void run() {

        }
    }
}