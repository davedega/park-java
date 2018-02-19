package com.dega.parkjava.api;

import com.dega.parkjava.model.VehiclesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by davedega on 17/02/18.
 */
public interface ParkApi {
    @GET("/vehicles")
    Observable<VehiclesResponse> loadVehicles();
}