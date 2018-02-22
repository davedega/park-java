package com.dega.parkjava.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dega.parkjava.R;
import com.dega.parkjava.api.ApiService;
import com.dega.parkjava.detail.ParkDetalActivity;
import com.dega.parkjava.infrastructure.schedulers.BaseSchedulerProvider;
import com.dega.parkjava.model.Constants;
import com.dega.parkjava.model.Vehicle;
import com.dega.parkjava.model.VehiclesResponse;

import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by davedega on 17/02/18.
 */

public class ParkPresenterImpl implements ParkContract.Presenter {


    private final ApiService apiService;
    private final BaseSchedulerProvider schedulerProvider;
    private final ParkContract.View view;
    private final Context context;
    private final CompositeSubscription subscriptions;


    ParkPresenterImpl(ApiService apiService, BaseSchedulerProvider schedulerProvider,
                      ParkContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.apiService = apiService;
        this.schedulerProvider = schedulerProvider;
        subscriptions = new CompositeSubscription();

//        ParkApplication.getComponent().inject(this);
    }

    @Override
    public void loadVehicles() {

        Observable<VehiclesResponse> vehicleClient = apiService.loadVehicles();

        vehicleClient
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .subscribe(new Observer<VehiclesResponse>() {
                    @Override
                    public void onCompleted() {
                        view.showLastUpdate();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            view.showErrorMessage(R.string.no_internet_connection);
                        } else if (e instanceof HttpException) {
                            view.showErrorMessage(R.string.not_found);
                        }else{
                            view.showErrorMessage(R.string.expection_message);
                        }
                    }

                    @Override
                    public void onNext(VehiclesResponse vehiclesResponse) {
                        view.showVehiclesInList(vehiclesResponse);

                    }
                });
    }

    @Override
    public void showDetailInNewView(Vehicle vehicle) {

        Bundle extras = new Bundle();
        extras.putInt(Constants.Data.VEHICLE_ID, vehicle.getVehicleId());
        extras.putString(Constants.Data.VRN, vehicle.getVrn());
        extras.putString(Constants.Data.COUNTRY, vehicle.getCountry());
        extras.putString(Constants.Data.COLOR, vehicle.getColor());
        extras.putString(Constants.Data.TYPE, vehicle.getType());
        extras.putBoolean(Constants.Data.DEFAULT, vehicle.getDefault());

        Intent detail = new Intent(context, ParkDetalActivity.class);
        detail.putExtras(extras);
        context.startActivity(detail);
    }
}
