package com.sa.gograb.location_service;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.sa.gograb.R;
import com.sa.gograb.global.GlobalVariables;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.LatlongModel;


public class LocationMonitoringService extends Service implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private FusedLocationProviderClient mFusedLocationClient;

    private static final String TAG = LocationMonitoringService.class.getSimpleName();
    GoogleApiClient mLocationClient;
    LocationRequest mLocationRequest = new LocationRequest();
    private Location mGetedLocation;

    CountDownTimer cdt = null;

    public static final String ACTION_LOCATION_BROADCAST = LocationMonitoringService.class.getName() + "LocationBroadcast";
    public static final String EXTRA_LATITUDE = "extra_latitude";
    public static final String EXTRA_LONGITUDE = "extra_longitude";
   // public static final String EXTRA_ORDER_ID = "extra_order_Id";

    String orderId=null;

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
//        orderId= GlobalFunctions.getSharedPreferenceString(this,"extra_order_Id");

        return super.getSharedPreferences(name, mode);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mLocationClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest.setInterval(GlobalVariables.LOCATION_INTERVAL);
        mLocationRequest.setFastestInterval(GlobalVariables.FASTEST_LOCATION_INTERVAL);


        int priority = LocationRequest.PRIORITY_HIGH_ACCURACY; //by default
        //PRIORITY_BALANCED_POWER_ACCURACY, PRIORITY_LOW_POWER, PRIORITY_NO_POWER are the other priority modes


        mLocationRequest.setPriority(priority);
        mLocationClient.connect();

        cdt = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {


            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Timer finished");
                GetlatLong();


                cdt.start();


            }
        };

        cdt.start();


        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*
     * LOCATION CALLBACKS
     */
    @Override
    public void onConnected(Bundle dataBundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            Log.d(TAG, "== Error On onConnected() Permission not granted");
            //Permission not granted by user so cancel the further execution.


            return;
        }


        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Task<Location> task = fusedLocationProviderClient.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    //Write your implemenation here
                    //orderId= GlobalFunctions.getSharedPreferenceString(getApplicationContext(),"extra_order_Id");
                   // Log.d("orderId00","=="+orderId);
                  //  if (GlobalFunctions.isNotNullValue(orderId))
                        sendLatLongToServer(getApplicationContext(), String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
                    // Log.d("AndroidClarified", location.getLatitude() + " " + location.getLongitude());
                }
            }
        });


        Log.d(TAG, "Connected to Google API");
    }

    /*
     * Called by Location Services if the connection to the
     * location client drops because of an error.
     */
    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Connection suspended");
    }


    //to get the location change
    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Location changed");


        if (location != null) {
            Log.d(TAG, "== location != null");

            //Send result to activities
           /// orderId= GlobalFunctions.getSharedPreferenceString(this,"extra_order_Id");
           // Log.d("orderId00","=="+orderId);
           // if (GlobalFunctions.isNotNullValue(orderId))
                sendLatLongToServer(getApplicationContext(), String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
        }

    }

    private void sendLatLongToServer(final Context context, String lat, String lng) {
        LatlongModel latlongModel=new LatlongModel();

        //Riyad location for testing
//        lat="24.71";
//        lng="46.67";

        //LIVE
        //locationUpdateModel.setOrder_vendor_product_id(orderId);
       String latitude= latlongModel.setLatitude(lat);
        String longitude=latlongModel.setLongitude(lng);


//        GlobalFunctions.showProgress(activity, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.updateLatLong(context,latitude,longitude, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                // GlobalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());

            }

            @Override
            public void OnFailureFromServer(String msg) {
                //GlobalFunctions.hideProgress();
                //GlobalFunctions.displayMessaage(activity, mainView, msg);
                Log.d(TAG, "Failure : " + msg);
            }

            @Override
            public void OnError(String msg) {
                //GlobalFunctions.hideProgress();
                //GlobalFunctions.displayMessaage(activity, mainView, msg);
                Log.d(TAG, "Error : " + msg);
            }
        }, "SendLatLongToServer");
    }

    public void ShowNotifcation(String lat, String lng) {

// Sets an ID for the notification, so it can be updated.
        int notifyID = 1;
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        CharSequence name = "Testing";// The user-visible name of the channel.
        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        }
// Create a notification and set the notification channel.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("New Message")
                    .setContentText("You've received new messages.")
                    .setSmallIcon(R.drawable.app_icon)
                    .setChannelId(CHANNEL_ID)
                    .build();
        }
    }


    public void showNotification(Context context, String title, String body, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.app_icon)
                .setContentTitle(title)
                .setContentText(body);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Failed to connect to Google API");

    }

    public void GetlatLong() {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    //Write your implemenation here
                   // orderId= GlobalFunctions.getSharedPreferenceString(getApplicationContext(),"extra_order_Id");
//                    Log.d("orderId00","=="+orderId);
                   // if (GlobalFunctions.isNotNullValue(orderId))
                        sendLatLongToServer(getApplicationContext(), String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
                    //Log.d("AndroidClarified", location.getLatitude() + " " + location.getLongitude());
                }
            }
        });
    }


}