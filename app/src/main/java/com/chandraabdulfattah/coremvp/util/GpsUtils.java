package com.chandraabdulfattah.coremvp.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.chandraabdulfattah.coremvp.R;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by bezzo on 22/12/17.
 */

public class GpsUtils {
    private Activity activity;

    public GpsUtils(Activity activity){
        this.activity = activity;
    }

    public void isGpsActive() {
        LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(activity.getString(R.string.turn_on_gps))
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intentLocationSetting = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            activity.startActivity(intentLocationSetting);
                            dialogInterface.dismiss();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            activity.finish();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public boolean isMockLocationOn(Location location){
        Bundle locationExtras = location.getExtras();
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(activity);

        return (locationExtras != null) && locationExtras
                .getBoolean(FusedLocationProviderApi.KEY_MOCK_LOCATION, false);
    }
}
