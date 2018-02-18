package com.dega.parkjava;

import android.content.Context;
import android.util.Log;

import com.dega.parkjava.api.ParkApi;
import com.dega.parkjava.model.VehiclesResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by davedega on 17/02/18.
 */

public class ParkPresenter implements ParkContract.Presenter {
    private String TAG = this.getClass().getName();
    private String API_BASE_URL = "http://private-6d86b9-vehicles5.apiary-mock.com/";


    ParkContract.View view;
    Context context;

    ParkApi parkApi;
    Retrofit retrofit;

    ParkPresenter(ParkContract.View view, Context context) {
        this.view = view;
        this.context = context;

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_BASE_URL)
                .build();
    }

    @Override
    public void loadVehicles() {
        parkApi = retrofit.create(ParkApi.class);

        Observable<VehiclesResponse> vehicleClient = parkApi.loadVehicles();

        vehicleClient.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<VehiclesResponse>() {
                    @Override
                    public void onNext(VehiclesResponse vehiclesResponse) {
                        Log.e(TAG, "onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "awef" + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");

                    }
                });
    }
}
