package example.ui.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import static example.config.TestConfigProvider.FIREFOX_VERSION;

@Slf4j
public class FirefoxLauncher {
    private static final FirefoxOptions optionsFirefox = new FirefoxOptions();

    public static RemoteWebDriver createDriver() {
        WebDriverManager.firefoxdriver().browserVersion(FIREFOX_VERSION).setup();
        GeckoDriverService driverService = GeckoDriverService.createDefaultService();
        RemoteWebDriver driver = new FirefoxDriver(driverService, defaultFirefoxOptions());
        SessionId sessionId = driver.getSessionId();
        log.info("Firefox browser instantiated");
        return driver;
    }

    public static FirefoxOptions defaultFirefoxOptions() {
        //  optionsFirefox.addArguments("--headless");
        optionsFirefox.addArguments("--incognito");
        optionsFirefox.addArguments("--disable-popup-blocking");
        optionsFirefox.addArguments("--disable-extensions");
        optionsFirefox.addArguments("--disable-infobars");
        optionsFirefox.addArguments("--disable-gpu");
        optionsFirefox.addArguments("--no-sandbox");
        optionsFirefox.addArguments("--ignore-certificate-errors");
        optionsFirefox.addArguments("--disable-dev-shm-usage");
        return optionsFirefox;
    }
}

