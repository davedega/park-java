package com.dega.parkjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dega.parkjava.model.Vehicle;
import com.dega.parkjava.model.VehiclesResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by davedega on 17/02/18.
 */
public class ParkFragment extends Fragment implements ParkContract.View {

    @BindView(R.id.vehiclesRecyclerView)
    RecyclerView vehiclesList;
    @BindView(R.id.logoImageView)
    ImageView logoImageView;
    private ParkContract.Presenter presenter;
    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.park_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(ParkContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showVehiclesInList(VehiclesResponse vehiclesResponse) {
        vehiclesList.setVisibility(View.VISIBLE);
        logoImageView.setVisibility(View.GONE);

        LinearLayoutManager mLayoutManager = new
                LinearLayoutManager(getActivity().getApplicationContext());
        vehiclesList.setLayoutManager(mLayoutManager);

        VehiclesAdapter adapterGrass = new
                VehiclesAdapter(new ArrayList<>(vehiclesResponse.getVehicles()));

        vehiclesList.setAdapter(adapterGrass);

    }

    @Override
    public void showErrorMessage(int message) {
        logoImageView.setVisibility(View.VISIBLE);
        vehiclesList.setVisibility(View.GONE);
        Snackbar mySnackbar = Snackbar.make(logoImageView,
                message, Snackbar.LENGTH_SHORT);
        mySnackbar.setAction(R.string.try_again, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadVehicles();
            }
        });

        mySnackbar.show();
    }

    @Override
    public void showLastUpdate() {
        Date date = new Date();
        String stringDate = DateFormat.getTimeInstance(DateFormat.SHORT).format(date);
        Snackbar.make(logoImageView,
                getString(R.string.last_update, stringDate), Snackbar.LENGTH_LONG).show();
    }


    class VehiclesAdapter extends RecyclerView.Adapter<VehiclesAdapter.VehicleViewHolder> {

        ArrayList<Vehicle> vehicles;

        public VehiclesAdapter(ArrayList<Vehicle> vehicles) {
            this.vehicles = vehicles;
        }

        @Override
        public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View root = LayoutInflater.from(getActivity().getApplicationContext()).
                    inflate(R.layout.vehicle_list_item, parent, false);
            return new VehicleViewHolder(root);
        }

        @Override
        public void onBindViewHolder(VehicleViewHolder holder, int position) {
            Vehicle vehicle = vehicles.get(position);
            holder.setVehicleName(vehicle.getVrn());
        }

        @Override
        public int getItemCount() {
            return vehicles.size();
        }

        class VehicleViewHolder extends RecyclerView.ViewHolder {
            TextView vehicleName;

            VehicleViewHolder(View itemView) {
                super(itemView);
                this.vehicleName = itemView.findViewById(R.id.vehicleName);
            }

            void setVehicleName(String vehicleName) {
                this.vehicleName.setText(vehicleName);
            }
        }
    }
}
