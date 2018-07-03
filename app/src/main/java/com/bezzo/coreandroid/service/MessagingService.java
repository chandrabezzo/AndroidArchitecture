package com.bezzo.coreandroid.service;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.bezzo.coreandroid.MvpApp;
import com.bezzo.coreandroid.di.component.DaggerServiceComponent;
import com.bezzo.coreandroid.di.component.ServiceComponent;
import com.bezzo.coreandroid.util.NotificationUtils;
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