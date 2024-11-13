package com.burakkuneko.datomi.helper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CustomeScheduledExecutorSevice {
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };
    //scheduledExecutorService.scheduleWithFixedDelay(runnable, 0, 1, TimeUnit.SECONDS);
    //scheduledExecutorService.schedule(scheduledExecutorService::shutdown, 10, TimeUnit.SECONDS);
}
