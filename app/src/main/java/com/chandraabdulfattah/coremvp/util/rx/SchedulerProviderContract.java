package com.chandraabdulfattah.coremvp.util.rx;

import io.reactivex.Scheduler;

/**
 * Created by bezzo on 26/09/17.
 */

public interface SchedulerProviderContract {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
