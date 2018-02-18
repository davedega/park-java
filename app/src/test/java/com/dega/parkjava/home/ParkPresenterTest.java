package com.dega.parkjava.home;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by davedega on 18/02/18.
 */
public class ParkPresenterTest {
    @Mock
    ParkContract.View view;

    @Mock
    Context context;

    ParkPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new ParkPresenter(view, context);
    }

    @Test
    public void loadVehicles() throws Exception {
        presenter.loadVehicles();
        
    }

}