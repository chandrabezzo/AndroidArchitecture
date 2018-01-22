package com.chandraabdulfattah.coremvp.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.chandraabdulfattah.coremvp.MvpApp;
import com.chandraabdulfattah.coremvp.data.DataManagerContract;
import com.chandraabdulfattah.coremvp.di.component.DaggerServiceComponent;
import com.chandraabdulfattah.coremvp.di.component.ServiceComponent;
import com.chandraabdulfattah.coremvp.util.AppLogger;

import javax.inject.Inject;

/**
 * Created by bezzo on 26/09/17.
 */

public class SyncService extends Service {

    private static final String TAG = "SyncService";

    @Inject
    DataManagerContract mDataManager;

    public static Intent getStartIntent(Context context){
        return new Intent(context, SyncService.class);
    }

    public static void start(Context context){
        Intent starter = new Intent(context, SyncService.class);
        context.startService(starter);
    }

    public static void stop(Context context){
        context.stopService(new Intent(context, SyncService.class));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceComponent component = DaggerServiceComponent.builder()
                .applicationComponent(((MvpApp) getApplication()).getComponent())
                .build();
        component.inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppLogger.d(TAG, "SyncService Started");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        AppLogger.d(TAG, "SyncService Stopped");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
