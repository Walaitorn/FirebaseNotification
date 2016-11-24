package com.egco486.firebasenotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by 6272user on 11/24/2016 AD.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        for(String k:remoteMessage.getData().keySet()){
            String s = remoteMessage.getData().get(k);
            Log.i("Result","onMessageReceivved"+s);
        }
        if(remoteMessage.getNotification() != null){
            String s = remoteMessage.getNotification().getBody();
            String t = remoteMessage.getNotification().getTitle();
            Log.i("Result","onMessageReceivved"+s +" "+t);
            showNoti(s,t);
        }
    }

    private void showNoti(String body,String title){

        Intent intentCall = new Intent(this,Call.class);
        PendingIntent pIntentCall = PendingIntent.getActivity(this,(int) System.currentTimeMillis(), intentCall,0);

        Intent intentSet = new Intent(this,Setting.class);
        PendingIntent pIntentSet = PendingIntent.getActivity(this,(int) System.currentTimeMillis(), intentSet,0);

        Intent intentWarn = new Intent(this,Warning.class);
        PendingIntent pIntentWarn = PendingIntent.getActivity(this,(int) System.currentTimeMillis(), intentWarn,0);

        NotificationCompat.Action callAction = new NotificationCompat.Action.Builder(R.drawable.ic_call,"Call",pIntentCall).build();
        NotificationCompat.Action settingAction = new NotificationCompat.Action.Builder(R.drawable.ic_setting,"Setting",pIntentSet).build();
        NotificationCompat.Action warningAction = new NotificationCompat.Action.Builder(R.drawable.ic_warning,"Warning",pIntentWarn).build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_message);
        builder.setContentTitle("New Message from "+title);
        builder.setContentText("Body "+body);
        builder.addAction(callAction);
        builder.addAction(settingAction);
        builder.addAction(warningAction);
        builder.setWhen(System.currentTimeMillis()+10);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());

    }
}
