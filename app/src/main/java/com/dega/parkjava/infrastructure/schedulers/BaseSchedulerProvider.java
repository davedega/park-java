package com.dega.parkjava.infrastructure.schedulers;

import android.support.annotation.NonNull;

import rx.Scheduler;


/**
 * Created by ddelgado on 22/02/2018.
 */
public interface BaseSchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
