package com.dega.parkjava.api;

import com.dega.parkjava.model.VehiclesResponse;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by ddelgado on 22/02/2018.
 */

public class ApiService implements ParkApi {

    ParkApi api;

    public ApiService(Retrofit retrofit) {
        this.api = retrofit.create(ParkApi.class);
    }

    @Override
    public Observable<VehiclesResponse> loadVehicles() {
        return api.loadVehicles();
    }
}