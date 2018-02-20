package com.dega.parkjava.infrastructure;

import com.dega.parkjava.api.ParkApi;
import com.dega.parkjava.model.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ddelgado on 20/02/2018.
 */

public class ApiManager {

    private final ParkApi API_SERVICE =  new Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.API_BASE_URL)
            .build()
            .create(ParkApi.class);

    public ParkApi getApiService() {
        return API_SERVICE;
    }

}
