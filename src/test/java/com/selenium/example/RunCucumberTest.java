package com.selenium.example;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions( 
        features = "src/test/java/com/selenium/example/features",
        glue= {"com.selenium.example.steps"},
        plugin= {"pretty", "summary", "html:target/cucumber-reports.html"},
        monochrome = true )
public class RunCucumberTest {
}
