package com.dega.parkjava;

import android.content.Context;

import com.dega.parkjava.api.ParkApi;
import com.dega.parkjava.model.VehiclesResponse;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by davedega on 17/02/18.
 */

public class ParkPresenter implements ParkContract.Presenter {

    private String API_BASE_URL = "http://private-6d86b9-vehicles5.apiary-mock.com/";

    private ParkContract.View view;
    private Retrofit retrofit;

    ParkPresenter(ParkContract.View view) {
        this.view = view;

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_BASE_URL)
                .build();
    }

    @Override
    public void loadVehicles() {
        ParkApi parkApi = retrofit.create(ParkApi.class);

        Observable<VehiclesResponse> vehicleClient = parkApi.loadVehicles();

        vehicleClient.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<VehiclesResponse>() {
                    @Override
                    public void onNext(VehiclesResponse vehiclesResponse) {
                        view.showVehiclesInList(vehiclesResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e instanceof UnknownHostException){
                            view.showErrorMessage(R.string.no_internet_connection);
                        }else if (e instanceof HttpException){
                            view.showErrorMessage(R.string.not_found);
                        }
                    }

                    @Override
                    public void onComplete() {
                        view.showLastUpdate();
                    }
                });
    }
}
