package com.dega.parkjava.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dega.parkjava.R;

public class ParkActivity extends AppCompatActivity {

    ParkPresenter presenter;
    ParkContract.View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);

        view = (ParkContract.View) getSupportFragmentManager()
                .findFragmentById(R.id.parkFragment);

        presenter =  new ParkPresenter(view, getApplicationContext());
        view.setPresenter(presenter);

        presenter.loadVehicles();
    }
}
