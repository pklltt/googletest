package com.automatedtest.sample.driver;

import io.cucumber.java.Before;

public class Setup {

    @Before
    public void driverInit() {
        DriverUtils.driverInit();
    }

}
