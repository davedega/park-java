package com.dega.parkjava.infrastructure;

import com.dega.parkjava.home.ParkActivity;
import com.dega.parkjava.home.ParkPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ddelgado on 20/02/2018.
 */
@Singleton
@Component(modules = ParkModule.class)
public interface ParkComponent {

    void inject(ParkPresenterImpl presenter);

    void inject(ParkActivity activity);

}


