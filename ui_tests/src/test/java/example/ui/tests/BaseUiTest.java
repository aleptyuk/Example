package example.ui.tests;

import example.ui.driver.DriverProvider;
import example.ui.enums.Browser;
import example.ui.utils.HelperUi;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Slf4j
@Listeners(TestListener.class)
public class BaseUiTest {
    @Getter
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    //HELPERS
    protected HelperUi helperUi;

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite(ITestContext ctx) {
        System.out.println("BeforeSuite");
        log.info("EXECUTING SUITE: {}", ctx.getSuite().getName());
    }

    @BeforeClass(alwaysRun = true)
    @Parameters("browserName")
    public void createHelper(@Optional("CHROME") String browserName) {
        Browser browser = Browser.valueOf(browserName);
        driver.set(DriverProvider.getDriver(browser));
        driver.get().manage().window().maximize();
        helperUi = initHelpers(driver.get());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownSuite(ITestContext ctx) {
        deleteTestData();
        DriverProvider.tearDown(getDriverThread());
        log.info("FINISHING SUITE: " + ctx.getSuite().getName());
    }

    @AfterSuite(alwaysRun = true)
    public void removeThread() {
        driver.remove();
    }

    public static WebDriver getDriverThread() {
        return driver.get();
    }

    private HelperUi initHelpers(WebDriver driver) {
        return new HelperUi(driver);
    }

    private static void deleteTestData() {
    }

}
