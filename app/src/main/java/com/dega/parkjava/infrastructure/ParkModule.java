package com.dega.parkjava.infrastructure;

import android.content.Context;

import com.dega.parkjava.api.ApiService;
import com.dega.parkjava.infrastructure.schedulers.*;
import com.dega.parkjava.model.Constants;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ddelgado on 20/02/2018.
 */
@Module
public class ParkModule {

    private Context context;

    public ParkModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    ApiService provideRemoteDataSource() {
        return new ApiService(new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build());
    }


    @Provides
    @Singleton
    com.dega.parkjava.infrastructure.schedulers.BaseSchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }
}
