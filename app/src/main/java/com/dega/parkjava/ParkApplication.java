package com.dega.parkjava;

import android.app.Application;

import com.dega.parkjava.infrastructure.DaggerParkComponent;
import com.dega.parkjava.infrastructure.ParkComponent;
import com.dega.parkjava.infrastructure.ParkModule;

/**
 * Created by ddelgado on 20/02/2018.
 */
public class ParkApplication extends Application {

    public static ParkComponent component;

    public static ParkComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerParkComponent.builder().parkModule(new ParkModule(this)).build();
    }
}
