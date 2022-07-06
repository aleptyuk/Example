package example.ui.driver;

import example.ui.enums.Browser;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class DriverProvider {


    private static WebDriver driver;

    private DriverProvider() {
    }

    public static WebDriver getDriver(Browser browserName) {
        if (driver == null) {
            driver = DriverFactory.createDriver(browserName);
            log.info("DRIVER is CREATED: " + browserName);
        }
        return driver;
    }

    public static void tearDown(WebDriver driverThread) {
        if (driverThread != null) {
            driverThread.quit();
            driverThread=null;
        }
    }
}
