package example.ui.driver;

import example.config.AppConfigProvider;
import example.logger.AllureReportUtils;
import lombok.Data;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

@Data
public class DriverManager {
    private static final int DEFAULT_IMPL_WAIT_SEC = AppConfigProvider.get().implicitlyWait();
    private static final int WEBBDRIVER_POLLS_MS = AppConfigProvider.get().implicitlyPolls();

    private WebDriver driver;
    private WebDriverWait driverWait;
    private JavascriptExecutor js;

    public DriverManager(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, DEFAULT_IMPL_WAIT_SEC, WEBBDRIVER_POLLS_MS);
        this.js = (JavascriptExecutor) driver;
    }

    public static File takeScreenshot(WebDriver driver) {
        if (driver == null) {
            return null;
        }
        byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        AllureReportUtils.attachPng("SCREENSHOT ", bytes);
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        return scrShot.getScreenshotAs(OutputType.FILE);
    }
}
