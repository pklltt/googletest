package com.automatedtest.sample.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/com/automatedtest/sample/ScrollPage.feature"},
        strict = false, plugin = {"pretty",
        "json:target/cucumber_json_reports/search.json",
        "html:target/scroll-page-html"},
        glue = {"com/automatedtest/sample/driver", "com.automatedtest.sample.definitions"})
public class ScrollPageTest {
}
