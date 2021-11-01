package com.saucelabs;

import java.net.URL;
import java.util.Iterator;

import com.saucelabs.saucebindings.options.SauceOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;

public class SauceLabsSerenityDriver implements DriverSource {

    public WebDriver newDriver() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

        String username = System.getenv("SAUCE_USERNAME");
        if (username == null) {
            username = (String) environmentVariables.getProperty("sauce.user");
        }

        String accessKey = System.getenv("SAUCE_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = (String) environmentVariables.getProperty("sauce.key");
        }

        String driverCreationURL = System.getenv("SAUCE_DRIVER_CREATION_URL");
        if (driverCreationURL == null) {
            driverCreationURL = "https://" + username + ":" + accessKey + "@"
                    + environmentVariables.getProperty("sauce.server") + "/wd/hub";
        }

        String environment = System.getProperty("environment");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        Iterator it = environmentVariables.getKeys().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();

            if (key.equals("sauce.user") || key.equals("sauce.key") || key.equals("sauce.server")) {
                continue;
            } else if (key.startsWith("sauce_")) {
                capabilities.setCapability(key.replace("sauce_", ""), environmentVariables.getProperty(key));
            } else if (environment != null && key.startsWith("environment." + environment)) {
                capabilities.setCapability(key.replace("environment." + environment + ".", ""), environmentVariables.getProperty(key));
            }
        }

        SauceOptions options = new SauceOptions();
        for(String capabilityName : capabilities.getCapabilityNames()) {
            options.setCapability(capabilityName, capabilities.getCapability(capabilityName));
        }

        try {
            return new RemoteWebDriver(new URL(driverCreationURL), options.toCapabilities());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean takesScreenshots() {
        return true;
    }
}
