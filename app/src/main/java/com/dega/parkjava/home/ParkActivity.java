package com.dega.parkjava.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dega.parkjava.ParkApplication;
import com.dega.parkjava.R;
import com.dega.parkjava.api.ApiService;
import com.dega.parkjava.infrastructure.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

public class ParkActivity extends AppCompatActivity {

    @Inject
    ApiService apiService;

    @Inject
    BaseSchedulerProvider baseSchedulerProvider;

    ParkPresenterImpl presenter;
    ParkContract.View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park);

        ParkApplication.getComponent().inject(this);

        view = (ParkContract.View) getSupportFragmentManager()
                .findFragmentById(R.id.parkFragment);

        presenter = new ParkPresenterImpl(apiService, baseSchedulerProvider, view, getApplicationContext());
        view.setPresenter(presenter);

        presenter.loadVehicles();
    }
}
