package com.dega.parkjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ParkActivity extends AppCompatActivity {

    ParkPresenter presenter;
    ParkContract.View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);

        view = (ParkContract.View) getSupportFragmentManager()
                .findFragmentById(R.id.parkFragment);

        presenter =  new ParkPresenter(view,getApplicationContext());
        presenter.loadVehicles();
    }
}
