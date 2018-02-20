package com.dega.parkjava.infrastructure;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ddelgado on 20/02/2018.
 */
@Module
public class ParkModule {

    private Context context;

    public ParkModule(Context context) {this.context = context;}

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    ApiManager getRadarApiManager() {
        return new ApiManager();
    }
}
