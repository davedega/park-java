package com.dega.parkjava.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dega.parkjava.R;
import com.dega.parkjava.model.Constants;
import com.dega.parkjava.model.Vehicle;

/**
 * Created by davedega on 18/02/18.
 */
public class ParkDetalActivity extends AppCompatActivity {

    ParkDetailPresenterImpl presenter;
    IParkDetailContract.View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_detail);

        view = (IParkDetailContract.View) getSupportFragmentManager()
                .findFragmentById(R.id.parkDetailFragment);

        presenter = new ParkDetailPresenterImpl(view);
        view.setPresenter(presenter);
        presenter.loadDetailVehicle(vehicleFromBundle(getIntent().getExtras()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private Vehicle vehicleFromBundle(Bundle bundle) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(bundle.getInt(Constants.Data.VEHICLE_ID));
        vehicle.setVrn(bundle.getString(Constants.Data.VRN));
        vehicle.setCountry(bundle.getString(Constants.Data.COUNTRY));
        vehicle.setColor(bundle.getString(Constants.Data.COLOR));
        vehicle.setType(bundle.getString(Constants.Data.TYPE));
        vehicle.setDefault(bundle.getBoolean(Constants.Data.DEFAULT));
        return vehicle;
    }
}
