package com.xiaozi.android.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.xiaozi.framework.libs.utils.Logger;

import java.util.Map;


/**
 * Created by user on 2017-08-23.
 */

public class SuperMessagingService extends FirebaseMessagingService {
    private final String LOG_TAG = getClass().getSimpleName();
    private Context mContext = null;
    private Handler mHandler = new Handler();

    private final String DATA_KEY_UID = "uuid";
    private final String DATA_KEY_ACT = "action";
    private final String DATA_KEY_TIMESTAMP = "timestamp";

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Logger.i(LOG_TAG, "onNewToken");
        Logger.d(LOG_TAG, "onNewToken token : " + token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Logger.i(LOG_TAG, "onMessageReceived");
        mContext = getApplicationContext();

        final String firebaseToken = FirebaseInstanceId.getInstance().getToken();
        String from = remoteMessage.getFrom();
        String messageId = remoteMessage.getMessageId();
        String messageType = remoteMessage.getMessageType();
        Map<String, String> data = remoteMessage.getData();
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Logger.d(LOG_TAG, "onMessageReceived firebaseToken : " + firebaseToken);
        Logger.d(LOG_TAG, "onMessageReceived from : " + from);
        Logger.d(LOG_TAG, "onMessageReceived messageId : " + messageId);
        Logger.d(LOG_TAG, "onMessageReceived messageType : " + messageType);
        Logger.d(LOG_TAG, "onMessageReceived data : " + data);

        if (notification != null) {
            Logger.d(LOG_TAG, "onMessageReceived notification.getTitle : " + notification.getTitle());
            Logger.d(LOG_TAG, "onMessageReceived notification.getBody : " + notification.getBody());
            Logger.d(LOG_TAG, "onMessageReceived notification.getTag : " + notification.getTag());
            Logger.d(LOG_TAG, "onMessageReceived notification.getColor : " + notification.getColor());
        }

        sendNotification(remoteMessage);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    private void sendNotification(RemoteMessage remoteMessage) {
        Logger.i(LOG_TAG, "sendNotification");
        Map<String, String> data = remoteMessage.getData();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ID = "my_channel_01";
        CharSequence name = "my_channel";
        String Description = "This is my channel";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(mContext.getString(R.string.app_name))
                .setContentText(data.toString())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
