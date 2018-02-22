package com.dega.parkjava.home;

import com.dega.parkjava.model.Vehicle;
import com.dega.parkjava.model.VehiclesResponse;

/**
 * Created by davedega on 17/02/18.
 */
public interface ParkContract {

    interface Presenter {

        void loadVehicles();

        //ANDR-300 select vehicle and show detail in new view
        void showDetailInNewView(Vehicle vehicle);
    }

    interface View {

        void setPresenter(Presenter presenter);

        void showVehiclesInList(VehiclesResponse vehiclesResponse);

        void showErrorMessage(int message);

        void showErrorMessage(String message);


        void showLastUpdate();
    }
}
