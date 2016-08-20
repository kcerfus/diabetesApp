package com.uwm.wundergrads.diabetesselfmanagement_wundergrads;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class RegimenNotification extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.regimen_image)
                .setContentTitle(intent.getStringExtra("mode"))
                .setContentText(intent.getStringExtra("value"));

        mNotificationManager.notify(123, notification.build());
    }
}
