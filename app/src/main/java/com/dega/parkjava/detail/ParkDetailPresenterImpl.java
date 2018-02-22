package com.dega.parkjava.detail;


import com.dega.parkjava.R;
import com.dega.parkjava.model.Vehicle;

/**
 * Created by davedega on 18/02/18.
 */
public class ParkDetailPresenterImpl implements IParkDetailContract.Presenter {

    private final IParkDetailContract.View view;

    ParkDetailPresenterImpl(IParkDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void loadDetailVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            view.showVehicleDetail(vehicle);
        } else {
            view.showErrorMessage(R.string.no_details_for_this_car);
        }
    }
}
