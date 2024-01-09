package com.example.elm_springboot.other.ScheduledTest;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TestClass {
    protected List<ScheduledFuture> scheduledFutures = new ArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

    {
        start();
    }
    private long initDelay1(){
        return 2;
    }

    private long initDelay2(){
        return 3;
    }
    private void perform1(){
        System.out.println("2延迟每5秒");
    }

    private void perform2(){
        System.out.println("3延迟每2秒");
    }

    public void start(){
        ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(this::perform1, initDelay1(), 5, TimeUnit.SECONDS);
        ScheduledFuture<?> scheduledFuture1 = scheduler.scheduleAtFixedRate(this::perform2, initDelay2(), 2, TimeUnit.SECONDS);
        this.scheduledFutures.add(scheduledFuture);
        this.scheduledFutures.add(scheduledFuture1);
    }

    public void cancelTask(){
        for (ScheduledFuture scheduledFuture : scheduledFutures) {
            scheduledFuture.cancel(true);
        }

        scheduledFutures.clear();
    }

    public void setTask(){

    }
}
