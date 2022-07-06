package example.ui.driver;

import example.ui.enums.Browser;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import static example.utils.SystemUtils.isUnix;
import static example.utils.SystemUtils.isWindows;

@Slf4j
public class DriverFactory {
    private static final EventHandler eventHandler = new EventHandler();
    private static RemoteWebDriver driver;
    private static EventFiringWebDriver eventDriver;
    private DriverFactory() {
        //DriverFactory should never be instantiated
    }

    public static EventFiringWebDriver createDriver(Browser browserName) {
        if (isWindows()) {
            if (browserName.equals(Browser.CHROME)) {
                driver = ChromeLauncher.createDriver();
                eventDriver = new EventFiringWebDriver(driver);
                eventDriver.register(eventHandler);
            } else if (browserName.equals(Browser.FIREFOX)) {
                driver = FirefoxLauncher.createDriver();
                eventDriver = new EventFiringWebDriver(driver);
                eventDriver.register(eventHandler);
            }
        } else if (isUnix()) {
            System.setProperty("webdriver.chrome.driver", "/var/lib/jenkins/tools/chromedriver/chromedriver");//for Jenkins
            //System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");//for local build
        } else throw new NullPointerException("Unknown OS. Driver is not instantiated");

        if (driver == null) {
            throw new NullPointerException("Driver not instantiated");
        }
        return eventDriver;
    }
}
