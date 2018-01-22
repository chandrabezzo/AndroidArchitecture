package com.chandraabdulfattah.coremvp.util.constanta;

/**
 * Created by bezzo on 28/09/17.
 */

public final class ApiConstans {

    //Component API
    public static final String DATA = "data";
    public static final String MESSAGE = "message";
    public static final String STATUS_CODE = "code";
    public static final String STATUS = "status";
    public static final String LIMIT = "limit";
    public static final String FILTER = "filter";

    // RabbitMQ
    public static final String PATTERN_TOPIC_RbMQ = "absensi.";
    public static final String EXCHANGE_NAME = "amq.topic";
    public static final String EXCHANGE_TYPE = "topic";
    public static final String RbMQ_HOST = "192.168.0.183";
    public static final String RbMQ_USERNAME = "bezzo";
    public static final String RbMQ_PASSWORD = "28111995";
    public static final String RbMQ_VIRTUAL_HOST = "/";
    public static final Integer RbMQ_PORT = 15672;
}
