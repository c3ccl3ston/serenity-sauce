package com.saucelabs;

import com.saucelabs.saucebindings.Browser;
import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Iterator;

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

        String dataCenter = environmentVariables.getProperty("sauce.server");
        System.out.println("DATA CENTER: " + dataCenter);

        String environment = System.getProperty("environment");

        SauceOptions options = new SauceOptions();
        SauceSession session;

        Iterator it = environmentVariables.getKeys().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();

           if (environment != null && key.startsWith("environment." + environment)) {
                String cap = key.replace("environment." + environment + ".", "");
                switch (cap) {
                    case "browserName":
                        options.setBrowserName(Browser.valueOf(environmentVariables.getProperty(key)));
                        break;
                    case "browserVersion":
                        options.setBrowserVersion(environmentVariables.getProperty(key));
                        break;
                    case "platformName":
                        options.setPlatformName(SaucePlatform.valueOf(environmentVariables.getProperty(key)));
                        break;
                }
            }
        }

        session = new SauceSession(options);
        session.setDataCenter(DataCenter.valueOf(dataCenter));

        try {
            return session.start();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean takesScreenshots() {
        return true;
    }
}
