package com.chandraabdulfattah.coremvp.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.chandraabdulfattah.coremvp.BuildConfig;
import com.chandraabdulfattah.coremvp.ui.features.main.MainActivity;
import com.chandraabdulfattah.coremvp.R;
import com.chandraabdulfattah.coremvp.data.model.network.NotificationData;

/**
 * Created by bezzo on 09/01/18.
 */

public class NotificationUtils {

    private static NotificationChannel configureChannel(String channelId, String channelName){
        NotificationChannel channel = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            channel = new NotificationChannel(channelId, channelName, importance);
//            channel.setDescription("Reminders");
        }

        return channel;
    }

    public static void createNotification(String channelId, String channelName, int notifId, String title, NotificationData data, Context context){
        String content = data.getAttribut();

        Intent intent = new Intent(context, MainActivity.class);
//        Add data if you need
//        Bundle bundle = new Bundle();
//        intent.putExtras(bundle);

        int requestID = (int) System.currentTimeMillis();
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        PendingIntent pIntent = PendingIntent.getActivity(context, requestID, intent, flags);

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setLargeIcon(largeIcon)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(title)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setTicker(content)
                .setContentText(content)
                .setDefaults(Notification.DEFAULT_ALL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBuilder.setPriority(NotificationManager.IMPORTANCE_HIGH);
        }
        else {
            mBuilder.setPriority(Notification.PRIORITY_HIGH);
        }

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationManager.createNotificationChannel(NotificationUtils.configureChannel(channelId,
                    channelName));
        }

        mNotificationManager.notify(BuildConfig.APPLICATION_ID, notifId, mBuilder.build());
    }
}
