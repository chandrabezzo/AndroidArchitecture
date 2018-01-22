package com.chandraabdulfattah.coremvp.util.rabbitMQ;

import android.os.AsyncTask;

/**
 * Created by bezzo on 09/01/18.
 */

public class ConsumerConnect extends AsyncTask<String, Void, Void> {

    private MessageConsumer mConsumer;

    public ConsumerConnect(MessageConsumer mConsumer){
        this.mConsumer = mConsumer;
    }

    @Override
    protected Void doInBackground(String... Message) {
        try {
            // Connect to broker
            mConsumer.connectToRabbitMQ();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        return null;
    }
}
