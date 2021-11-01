package com.saucelabs;

import io.cucumber.java.Scenario;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.events.AfterScenario;
import net.serenitybdd.core.annotations.events.BeforeScenario;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;

public class SauceLabsSerenityTest {

    @BeforeClass
    public static void setUp() {
    }

    @AfterScenario
    public static void beforeScenario(Scenario scenario) {
        ((JavascriptExecutor) Serenity.getDriver()).executeScript("sauce:job-name=" + scenario.getName());
    }

    @AfterClass
    public static void tearDown() {

    }
}
