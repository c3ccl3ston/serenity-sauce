package com.saucelabs.cucumber.steps;

import io.cucumber.java.Scenario;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.events.BeforeScenario;
import org.openqa.selenium.JavascriptExecutor;

public class BaseSteps {

    @BeforeScenario
    public void beforeScenario(Scenario scenario) {
        ((JavascriptExecutor) Serenity.getDriver()).executeScript("sauce:job-name=" + scenario.getName());
    }
}
