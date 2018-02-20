package com.dega.parkjava.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dega.parkjava.ParkApplication;
import com.dega.parkjava.R;
import com.dega.parkjava.detail.ParkDetalActivity;
import com.dega.parkjava.infrastructure.ApiManager;
import com.dega.parkjava.model.Constants;
import com.dega.parkjava.model.Vehicle;
import com.dega.parkjava.model.VehiclesResponse;

import java.net.UnknownHostException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;

/**
 * Created by davedega on 17/02/18.
 */

public class ParkPresenterImpl implements ParkContract.Presenter {

    @Inject
    ApiManager apiManager;

    private Context context;
    private ParkContract.View view;

    ParkPresenterImpl(ParkContract.View view, Context context) {
        this.view = view;
        this.context = context;
        ParkApplication.getComponent().inject(this);
    }

    @Override
    public void loadVehicles() {

        Observable<VehiclesResponse> vehicleClient = apiManager.getApiService().loadVehicles();

        vehicleClient.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<VehiclesResponse>() {
                    @Override
                    public void onNext(VehiclesResponse vehiclesResponse) {
                        view.showVehiclesInList(vehiclesResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof UnknownHostException) {
                            view.showErrorMessage(R.string.no_internet_connection);
                        } else if (e instanceof HttpException) {
                            view.showErrorMessage(R.string.not_found);
                        }
                    }

                    @Override
                    public void onComplete() {
                        view.showLastUpdate();
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
