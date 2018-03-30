package ch.hslu.mobprog.servicesreceivers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by sandr on 30.03.2018.
 */

public class DemoBroadcastReceiver extends BroadcastReceiver {
    private int count = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        count++;
        NotificationChannel channel = new NotificationChannel("1234", "default", NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription("default channel");

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "1234")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Broadcast")
                .setContentText("Erhaltene Broadcast:" + count)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Notification notification = builder.build();
        notificationManager.notify(12345, notification);
    }
}
