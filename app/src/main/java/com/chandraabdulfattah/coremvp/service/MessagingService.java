package com.chandraabdulfattah.coremvp.service;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.chandraabdulfattah.coremvp.MvpApp;
import com.chandraabdulfattah.coremvp.di.component.DaggerServiceComponent;
import com.chandraabdulfattah.coremvp.di.component.ServiceComponent;
import com.chandraabdulfattah.coremvp.util.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by bezzo on 21/02/18.
 */

public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onCreate() {
        super.onCreate();

        ServiceComponent component = DaggerServiceComponent.builder()
                .applicationComponent(((MvpApp) getApplication()).getComponent())
                .build();
        component.inject(this);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        NotificationUtils.createNotification(1, remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody(), this);

        if (remoteMessage.getData() != null){
            sendBroadcast(remoteMessage.getData().get("type"));
        }
    }

    private void sendBroadcast(String type) {
        Intent intent = new Intent();
        intent.setAction("NOTIFICATION");
        intent.putExtra("type", type);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
