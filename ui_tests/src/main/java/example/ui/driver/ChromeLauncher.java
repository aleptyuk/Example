package example.ui.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.util.HashMap;
import java.util.Map;

import static example.config.TestConfigProvider.CHROME_VERSION;

@Slf4j
public class ChromeLauncher {
    private static final ChromeOptions optionsChrome = new ChromeOptions();

    public static RemoteWebDriver createDriver() {
        WebDriverManager.chromedriver().browserVersion(CHROME_VERSION).setup();
        ChromeDriverService driverService = ChromeDriverService.createDefaultService();
        RemoteWebDriver driver = new ChromeDriver(driverService, defaultChromeOptions());
        SessionId sessionId = driver.getSessionId();
        log.info("Chrome browser instantiated");
        return driver;
    }

    public static ChromeOptions defaultChromeOptions() {
        optionsChrome.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        Map<String, Object> prefs = new HashMap<>();
        //TODO
        // prefs.put("download.default_directory", PATH_TO_FILE);
        optionsChrome.setExperimentalOption("prefs", prefs);
        //     optionsChrome.addArguments("--headless");
        optionsChrome.addArguments("start-maximized");
        optionsChrome.addArguments("enable-automation");
        optionsChrome.addArguments("--incognito");
        optionsChrome.addArguments("--disable-popup-blocking");
        optionsChrome.addArguments("--disable-extensions");
        optionsChrome.addArguments("--disable-infobars");
        optionsChrome.addArguments("--disable-gpu");
        optionsChrome.addArguments("--no-sandbox");
        optionsChrome.addArguments("--ignore-certificate-errors");
        //  optionsChrome.addArguments("screenshot");

        optionsChrome.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems on Linux only
        return optionsChrome;
    }
}
