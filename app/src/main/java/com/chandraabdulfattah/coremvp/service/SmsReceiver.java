package com.chandraabdulfattah.coremvp.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsMessage;

import com.chandraabdulfattah.coremvp.util.constanta.AppConstans;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bezzo on 24/03/18.
 */

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data  = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for(int i=0;i<pdus.length;i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            //String sender = smsMessage.getDisplayOriginatingAddress();
            //b=sender.endsWith("WNRCRP");  //Just to fetch otp sent from WNRCRP
            String messageBody = smsMessage.getMessageBody();
            // here abcd contains otpwhich is in number format
            Pattern pattern = Pattern.compile("[0-9]{4}");
            Matcher matcher = pattern.matcher(messageBody);
            //Pass on the text to our listener.
            if(matcher.find()) {
                sendBroadcast(context, matcher.group());
            }
        }
    }

    void sendBroadcast(Context context, String kode){
        Intent intent = new Intent();
        intent.setAction(AppConstans.VERIFICATION);
        intent.putExtra(AppConstans.VERIFICATION_CODE, kode);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
