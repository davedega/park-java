package com.dega.parkjava.api;

import com.dega.parkjava.model.VehiclesResponse;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by davedega on 17/02/18.
 */
public interface ParkApi {
    @GET("/vehicles")
    Observable<VehiclesResponse> loadVehicles();
}