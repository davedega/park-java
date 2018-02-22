package com.dega.parkjava.home;

import com.dega.parkjava.api.ApiService;
import com.dega.parkjava.model.Constants;
import com.dega.parkjava.model.Vehicle;
import com.dega.parkjava.model.VehiclesResponse;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.observers.TestSubscriber;

/**
 * Created by ddelgado on 22/02/2018.
 */

public class ApiServiceTest {


    private List<Vehicle> vehiclesList;
    MockWebServer mockWebServer;
    TestSubscriber<VehiclesResponse> subscriber;

    @Before
    public void setUp() {

        Vehicle vehicleOne = new Vehicle(1, "VRN123", "Netherlands", "Purple", "manual", true);
        Vehicle vehicleTwo = new Vehicle(1, "VRN456", "Germany", "Red", "manual", false);
        vehiclesList = new ArrayList<>();
        vehiclesList.add(vehicleOne);
        vehiclesList.add(vehicleTwo);

        mockWebServer = new MockWebServer();
        subscriber = new TestSubscriber<>();
    }

    /**
     * The purpose of this method is to test the subscriber for an unsucessful request
     * given a endpoint
     * **/
    @Test
    public void unsuccesfulApiRequest() {

        String url = "http://google.com";
        mockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(vehiclesList)));
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mockWebServer.url(url))
                .build();
        ApiService apiService = new ApiService(retrofit);

        apiService.loadVehicles().subscribe(subscriber);

        //The test should fail in this point
        subscriber.assertError(HttpException.class);
    }

    /**
     * The purpose of this method is to test the subscriber for a sucessful request
     * **/
    @Test
    public void succesfulApiRequest() {

        String url = Constants.API_BASE_URL;
        mockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(vehiclesList)));
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mockWebServer.url(url))
                .build();
        ApiService apiService = new ApiService(retrofit);

        //When
        apiService.loadVehicles().subscribe(subscriber);

        //Test should pass
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }

}
