package com.chandraabdulfattah.coremvp.util.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by bezzo on 09/01/18.
 */

public abstract class ConnectToRabbitMQ {
    public String mServer;
    public String mExchange;
    public String mUsername;
    public String mPassword;
    public String mVirtualHost;

    protected Channel mModel = null;
    protected Connection mConnection;

    protected boolean Running ;

    protected String MyExchangeType ;

    /**
     *
     * @param server The server address
     * @param exchange The named exchange
     * @param exchangeType The exchange type name
     */
    public ConnectToRabbitMQ(String server, String exchange, String exchangeType, String username,
                             String password, String virtualHost)
    {
        mServer = server;
        mExchange = exchange;
        MyExchangeType = exchangeType;
        mUsername = username;
        mPassword = password;
        mVirtualHost = virtualHost;
    }

    public void Dispose()
    {
        Running = false;

        try {
            if (mConnection!=null)
                mConnection.close();
            if (mModel != null)
                mModel.abort();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Connect to the broker and create the exchange
     * @return success
     */
    public boolean connectToRabbitMQ()
    {
        if(mModel!= null && mModel.isOpen() )//already declared
            return true;
        try
        {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(mServer);
            connectionFactory.setUsername(mUsername);
            connectionFactory.setPassword(mPassword);
            connectionFactory.setVirtualHost(mVirtualHost);
            mConnection = connectionFactory.newConnection();
            mModel = mConnection.createChannel();
            mModel.exchangeDeclare(mExchange, MyExchangeType, true);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
