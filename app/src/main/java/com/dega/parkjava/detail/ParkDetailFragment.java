package com.dega.parkjava.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dega.parkjava.R;
import com.dega.parkjava.model.Vehicle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by davedega on 18/02/18.
 */

public class ParkDetailFragment extends Fragment implements IParkDetailContract.View {

    @BindView(R.id.vehicleIdTv)
    TextView vehicleIdTv;
    @BindView(R.id.vrnTv)
    TextView vrnTv;
    @BindView(R.id.countryTv)
    TextView countryTv;
    @BindView(R.id.colorTv)
    TextView colorTv;
    @BindView(R.id.typeTv)
    TextView typeTv;
    @BindView(R.id.defaultSwitch)
    SwitchCompat defaultSwitch;
    View rootView;
    private Unbinder unbinder;
    private ParkDetailPresenterImpl presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.park_detail_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showErrorMessage(int message) {
        Snackbar.make(rootView,
                getString(R.string.no_details_for_this_car), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showVehicleDetail(Vehicle vehicle) {
        vehicleIdTv.setText("" + vehicle.getVehicleId());
        vrnTv.setText(vehicle.getVrn());
        countryTv.setText(vehicle.getCountry());
        colorTv.setText(vehicle.getColor());
        typeTv.setText(vehicle.getType());
        defaultSwitch.setChecked(vehicle.getDefault());
    }

    @Override
    public void setPresenter(ParkDetailPresenterImpl presenter) {
        this.presenter = presenter;
    }
}
