package com.dega.parkjava.detail;

import android.os.Bundle;

import com.dega.parkjava.model.Vehicle;

/**
 * Created by davedega on 18/02/18.
 */

interface ParkDetailContract {
    interface Presenter {
        void loadDetailVehicle(Vehicle vehicle);
    }

    interface View {

        void showErrorMessage(int message);

        void showVehicleDetail(Vehicle vehicle);

        void setPresenter(ParkDetailPresenter presenter);
    }
}
