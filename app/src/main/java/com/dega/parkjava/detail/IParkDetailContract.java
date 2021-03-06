package com.dega.parkjava.detail;

import com.dega.parkjava.model.Vehicle;

/**
 * Created by davedega on 18/02/18.
 */
interface IParkDetailContract {

    interface Presenter {

        void loadDetailVehicle(Vehicle vehicle);

    }

    interface View {

        void showErrorMessage(int message);

        void showVehicleDetail(Vehicle vehicle);

        void setPresenter(ParkDetailPresenterImpl presenter);
    }
}
