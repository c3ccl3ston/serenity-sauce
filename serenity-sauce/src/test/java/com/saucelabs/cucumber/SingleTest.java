package com.saucelabs.cucumber;

import com.saucelabs.SauceLabsSerenityTest;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/single.feature")
public class SingleTest extends SauceLabsSerenityTest {
}
