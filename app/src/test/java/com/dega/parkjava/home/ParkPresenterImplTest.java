package com.dega.parkjava.home;

import android.content.Context;

import com.dega.parkjava.R;
import com.dega.parkjava.api.ApiService;
import com.dega.parkjava.infrastructure.schedulers.BaseSchedulerProvider;
import com.dega.parkjava.infrastructure.schedulers.ImmediateSchedulerProvider;
import com.dega.parkjava.model.Vehicle;
import com.dega.parkjava.model.VehiclesResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ddelgado on 22/02/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ParkPresenterImplTest {

    @Mock
    private Context context;

    @Mock
    private ApiService apiService;

    @Mock
    private ParkContract.View mView;

    private ParkPresenterImpl presenter;
    private List<Vehicle> vehiclesList;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        Vehicle vehicleOne = new Vehicle(1, "VRN123", "Netherlands", "Purple", "manual", true);
        Vehicle vehicleTwo = new Vehicle(1, "VRN456", "Germany", "Red", "manual", false);
        vehiclesList = new ArrayList<>();
        vehiclesList.add(vehicleOne);
        vehiclesList.add(vehicleTwo);

        BaseSchedulerProvider mSchedulerProvider = new ImmediateSchedulerProvider();
        presenter = new ParkPresenterImpl(apiService, mSchedulerProvider, mView, context);
    }

    /**
     * The purpose of this methos is to test the view when a valid list of vehicles is
     * passed to the view
     * ***/
    @Test
    public void loadVehicles() {
        VehiclesResponse response = new VehiclesResponse();
        response.setCount(2);
        response.setCurrentPage(1);
        response.setNextPage(1);
        response.setTotalPages(1);
        Vehicle vehicleOne = new Vehicle(1,"VRN3456","Netherlans","Yellow","manual",true);
        Vehicle vehicleTwo = new Vehicle(2,"VRN1234","Netherlans","Green","manual",false);
        ArrayList<Vehicle> vehicles =  new ArrayList<>();
        vehicles.add(vehicleOne);
        vehicles.add(vehicleTwo);

        when(apiService.loadVehicles())
                .thenReturn(rx.Observable.just(response));

        presenter.loadVehicles();
        InOrder inOrder = Mockito.inOrder(mView);
        inOrder.verify(mView).showVehiclesInList(response);
        inOrder.verify(mView).showLastUpdate();
    }

    /**
     * The purpose of this method is to test the view when an UnknownHostException is thrown
     * ***/
    @Test
    public void noInternetConnection() {

        when(apiService.loadVehicles())
                .thenReturn(Observable.<VehiclesResponse>error(new UnknownHostException("No internet!")));

        presenter.loadVehicles();
        InOrder inOrder = Mockito.inOrder(mView);

        inOrder.verify(mView).showErrorMessage(R.string.no_internet_connection);
    }


}