package example.ui.pages;


import example.ui.driver.BrowserManager;
import example.ui.driver.DriverManager;
import example.ui.driver.WebElementActions;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

@Slf4j

public abstract class AbstractBasePage {
    protected DriverManager driverManager;
    protected BrowserManager browserManager;
    protected WebElementActions welActions;

    public AbstractBasePage(WebDriver driver) {
        log.info("{} created", this.getClass().getName());
        this.driverManager = new DriverManager(driver);
        this.welActions = new WebElementActions(driver, this.driverManager.getDriverWait());
        this.browserManager = new BrowserManager(driver);
    }

    public void openPage(String url) {
        driverManager.getDriver().get(url);
    }

    public void openNewTab() {
        JavascriptExecutor executor = (JavascriptExecutor) driverManager.getDriver();
        executor.executeScript("window.open()");
    }

    public void goPreviousTab() {
        ArrayList<String> tabs = new ArrayList<>(driverManager.getDriver().getWindowHandles());
        driverManager.getDriver().switchTo().window(tabs.get(tabs.size()-2));
    }

    public void goLastTab() {
        ArrayList<String> tabs = new ArrayList<>(driverManager.getDriver().getWindowHandles());
        driverManager.getDriver().switchTo().window(tabs.get(tabs.size()-1));
    }

    public void browserQuit() {
        browserManager.getDriver().quit();
    }

    public void reloadPage() {
        browserManager.reloadPage();
    }

    public void scrollDown(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driverManager.getDriver();
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }
}
