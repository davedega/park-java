package com.dega.parkjava;

import com.dega.parkjava.model.VehiclesResponse;

/**
 * Created by davedega on 17/02/18.
 */

public interface ParkContract {

    interface Presenter{
        void loadVehicles();
    }

    interface View{

        void setPresenter(Presenter presenter);

        void showVehiclesInList(VehiclesResponse vehiclesResponse);

        void showErrorMessage(int message);

        void showLastUpdate();
    }
}
