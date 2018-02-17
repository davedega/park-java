package com.dega.parkjava;

import android.content.Context;
import android.util.Log;

/**
 * Created by davedega on 17/02/18.
 */

public class ParkPresenter implements ParkContract.Presenter {

    ParkContract.View view;
    Context context;

    ParkPresenter(ParkContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void loadVehicles() {
        //todo load vehicles using retrofit
    }
}
