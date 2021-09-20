package com.sauthi.gograb.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sauthi.gograb.AppController;
import com.sauthi.gograb.MainActivity;
import com.sauthi.gograb.R;
import com.sauthi.gograb.fcm.util.NotificationUtils;
import com.sauthi.gograb.global.GlobalFunctions;
import com.sauthi.gograb.global.GlobalVariables;
import com.sauthi.gograb.services.ServicesMethodsManager;
import com.sauthi.gograb.services.model.NotificationModel;
import com.sauthi.gograb.services.model.PushNotificationModel;


import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

        private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
        private NotificationUtils notificationUtils;

        GlobalVariables globalVariables;
        GlobalFunctions globalFunctions;

        @Override
        public void onNewToken(@NonNull String s) {
                super.onNewToken(s);
                //now we will have the token
                Log.d(TAG, "Refreshed token: " + s);

               // String token = MyFirebaseInstanceIDService.getInstance().getToken();
                PushNotificationModel model = new PushNotificationModel();
                model.setRegistration_id(s);
                sendPushNotificationID(getApplicationContext(), model);
                //  SendFcmTokenToserver(token);
                //for now we are displaying the token in the log
                //copy it as this method is called only when the new token is generated
                //and usually new token is only generated when the app is reinstalled or the data is cleared
                Log.d("MyRefreshedToken", s);
        }

        private void sendPushNotificationID(final Context applicationContext, PushNotificationModel model) {
                ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
              /*  servicesMethodsManager.sendPushNotificationID(applicationContext, model, new ServerResponseInterface() {
                        @Override
                        public void OnSuccessFromServer(Object arg0) {
                                Log.d(TAG, "Response : "+ arg0.toString());
                        }

                        @Override
                        public void OnFailureFromServer(String msg) {
                                Log.d(TAG, "Failure : "+ msg);
                        }

                        @Override
                        public void OnError(String msg) {
                                Log.d(TAG, "Error : "+ msg);
                        }
                }, "Send_Push_Notification_ID");*/
        }

        @Override
        public void onCreate() {
                super.onCreate();
                globalVariables = AppController.getInstance().getGlobalVariables();
                globalFunctions = AppController.getInstance().getGlobalFunctions();
        }

        @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {
//                Log.e(TAG, "##########onMessageReceived From: #########" + remoteMessage.getFrom());
//                Log.e(TAG, "##########onMessageReceived From: #########" + remoteMessage.getNotification().toString());
//                Log.e(TAG, "##########onMessageReceived From: #########" + remoteMessage.getNotification().getBody());
//                Log.e(TAG, "##########onMessageReceived From: #########" + remoteMessage.getNotification().getTitle());
//                Log.e(TAG, "##########onMessageReceived From: #########" + remoteMessage.getNotification().getIcon());
                  Log.e(TAG, "##########onMessageReceived From: #########" + remoteMessage.getData());
//                Log.e(TAG, "##########onMessageReceived From: #########" + remoteMessage.getMessageType());
//                Log.e(TAG, "##########onMessageReceived From: #########" + remoteMessage.getData().get("image"));

                if (remoteMessage == null)
                        return;
                // Check if message contains a data payload.
                /*if (!remoteMessage.getNotification().getBody().isEmpty()) {
                        Log.e(TAG, "Data Payload: " + remoteMessage.getNotification().getBody().toString());

                        try {
                                JSONObject json = new JSONObject(remoteMessage.getNotification().getBody().toString());
                                handleDataMessage(json, remoteMessage);
                        } catch (Exception e) {
                                Log.e(TAG, "Exception: " + e.getMessage());
                        }
                        // Check if message contains a notification payload.

                }*/

             /*if(globalFunctions.getNotificationSettings().isShowNotification()) {
                     if (!remoteMessage.getData().isEmpty()) {
                             Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());

                             try {
                                     JSONObject json = new JSONObject(remoteMessage.getData());
                                     handleDataMessage(json, remoteMessage);
                             } catch (Exception e) {
                                     Log.e(TAG, "Exception: " + e.getMessage());
                             }
                             // Check if message contains a notification payload.
                     } else {
                             handleDataMessage(remoteMessage);
                     }
             }*/
        }

        private void handleNotification(NotificationModel notificationModel, RemoteMessage remoteMessage) {
                int i=0;
                NotificationCompat.BigPictureStyle bigPictureStyle =null;
                if(notificationModel.getImage()!=null && !notificationModel.getImage().equalsIgnoreCase("null")) {
                        Bitmap bitmap = getBitmapfromUrl(notificationModel.getImage());
                        bigPictureStyle = new NotificationCompat.BigPictureStyle();
                        bigPictureStyle.bigPicture(bitmap);
                }
                // remove this comment for bigtext
                NotificationCompat.BigTextStyle bigText =  new NotificationCompat.BigTextStyle();
                bigText.bigText(notificationModel.getMessage());
               // bigTextStyle.bigText(notificationModel.getMessage());

        /*if(!TextUtils.isEmpty(notificationModel.getAlbum_id()))
            i= Integer.parseInt(notificationModel.getAlbum_id());
*/            //  Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notifysound);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

                //icon appears in device notification bar and right hand corner of notification
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        builder.setLargeIcon(BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.app_icon));
                        builder.setSmallIcon(R.drawable.app_icon);
                      //  builder.setColor(getResources().getColor(R.color.orange_light));
                }else{
                        builder.setSmallIcon(R.drawable.app_icon);
                }
//                Intent intent=new Intent(this,MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                Intent intent = getIntentToOpen(notificationModel);

                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),i,intent, PendingIntent.FLAG_UPDATE_CURRENT);
                // Set the intent that will fire when the user taps the notification.
                builder.setContentIntent(pendingIntent);
                /*builder.setAutoCancel(true);*/
                // Large icon appears on the left of the notification
                builder.setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.app_icon));
                // Content title, which appears in large type at the top of the notification
                builder.setContentTitle(notificationModel.getTitle()/*remoteMessage.getNotification().getTitle()*/);
                builder.setContentText(notificationModel.getMessage()/*remoteMessage.getNotification().getBody()*/);
                builder.setAutoCancel(true);
                builder.setStyle(bigText); //remove this comment for bigtext
               // builder.setSound(sound);
                builder.setStyle(bigPictureStyle);
                // bigTextStyle.setBigContentTitle(notificationModel.getTitle());
                //  bigTextStyle.bigText(notificationModel.getBody());
                //builder.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+ "://" +getPackageName()+"/"+R.raw.notifysd));


                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        builder.setChannelId("com.yin.app");
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel(
                                "com.yin.app",
                                "Rapid Car Spa",
                                NotificationManager.IMPORTANCE_DEFAULT
                        );
                        if (notificationManager != null) {
                                notificationManager.createNotificationChannel(channel);
                        }
                }

                Notification notification=builder.build();
               // notification.sound = sound;
                notification.defaults =  Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE;
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(i,notification);
        }

        private void handleDataMessage(JSONObject json, RemoteMessage remoteMessage) {
                Log.e(TAG, "push json: " + json.toString());
                Context context=getApplicationContext();
                NotificationModel notificationModel=new NotificationModel();
                if(notificationModel.toObject(json.toString())) {
                        handleNotification(notificationModel, remoteMessage);
                        sendMessage(notificationModel);
                }
        }

        private void handleDataMessage(RemoteMessage remoteMessage) {
                NotificationModel notificationModel=new NotificationModel();
                notificationModel.setMessage(remoteMessage.getNotification().getBody());
                notificationModel.setTitle(remoteMessage.getNotification().getTitle());
                notificationModel.setImage(remoteMessage.getNotification().getIcon());
                handleNotification(notificationModel, remoteMessage);
        }

        private Intent getIntentToOpen(NotificationModel notificationModel) {

          /*  if (notificationModel.getType() == STATUS.NOTIFICATION_TYPE.OFFER) {
                Intent intent = MainActivity.newInstance(this, notificationModel.getTypeExtra());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                // startActivity(intent);
                return intent;
            } else if (notificationModel.getType() == STATUS.NOTIFICATION_TYPE.ORDER) {
                //   Intent intent = OrderDetailActivity.newInstance(this, notificationModel.getTypeExtra());
                //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //  startActivity(intent);
                //   return intent;
            } else {*/
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                // startActivity(intent);
                return intent;
          //  }


        }



        /**
         * Showing notification with text only
         */
        private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
                notificationUtils = new NotificationUtils(context);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
        }

        /**
         * Showing notification with text and image
         */
        private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
                notificationUtils = new NotificationUtils(context);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
        }

        private void sendMessage(NotificationModel notificationModel) {
                Log.d("sender", "Broadcasting message");
                Intent intent = new Intent(globalVariables.NOTIFICATION_LOCAL_BROADCAST_KEY);
                intent.putExtra(globalVariables.NOTIFICATION_LOCAL_BROADCAST_NOTIFICATION_MODEL, notificationModel);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }

        /*
         *To get a Bitmap image from the URL received
         * */
        public Bitmap getBitmapfromUrl(String imageUrl) {
                try {
                        URL url = new URL(imageUrl);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream input = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(input);
                        return bitmap;

                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return null;

                }
        }
}
