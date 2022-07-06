package example.ui.driver;

import example.config.AppConfigProvider;
import example.ui.utils.UiMapping;
import example.utils.WaitUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static example.config.TestConfigProvider.WAIT_LOAD_AVATAR_PLAYLIST;

@Slf4j
public class WebElementActions {
    private static final int DEFAULT_IMPL_WAIT_SEC = AppConfigProvider.get().implicitlyWait();

    protected WebDriverWait wait;
    protected WebDriver driver;

    public WebElementActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        UiMapping.getInstance();
    }


    public WebElementActions moveToElement(String locator) {
        Actions action = new Actions(this.driver);
        action.moveToElement(getElement(locator));
        action.perform();
        return this;
    }

    public WebElementActions moveToElements(String locator) {
        Actions action = new Actions(this.driver);
        action.moveToElement(getElements(locator).get(1));
        action.perform();
        return this;
    }
    public WebElementActions moveToElementByNumber(String locator,int numberInList) {
        Actions action = new Actions(this.driver);
        action.moveToElement(getElements(locator).get(numberInList-1));
        action.perform();
        return this;
    }

    public WebElementActions moveToElementFromList(String locator) {
        Actions action = new Actions(this.driver);
        action.moveToElement(getElements(locator).get(0));
        action.perform();
        return this;
    }

    public WebElementActions moveToElements(WebElement firstElement, WebElement secondElement) {
        Actions action = new Actions(this.driver);
        action.moveToElement(firstElement)
                .moveToElement(secondElement)
                .build()
                .perform();
        return this;
    }

    public WebElementActions moveToElementAndClick(String locator) {
        Actions action = new Actions(this.driver);
        action.moveToElement(getElement(locator))
                .click()
                .build()
                .perform();
        return this;
    }

    protected WebElementActions waitModal() {
        WaitUtils.setWaitMs(500);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(org.openqa.selenium.TimeoutException.class);
        return this;
    }

    public WebElement getElement(String key) {
        waitModal();
        By element = UiMapping.ui(key);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element);
    }

    public WebElement getInvisibleElement(String key) {
        waitModal();
        By element = UiMapping.ui(key);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
        return driver.findElement(element);
    }

    public WebElement getElementNoWait(String key) {
        waitModal();
        By element = UiMapping.ui(key);
        return driver.findElement(element);
    }

    public WebElementActions inputFilePath(String key, String value) {
        waitModal();
        WebElement element = driver.findElement(UiMapping.ui(key));
        element.sendKeys(value);
        return this;
    }

       public List<WebElement> getElements(String key) {
        waitModal();
        By element = UiMapping.ui(key);
        List<WebElement> test1 = driver.findElements(element);

        return driver.findElements(element);
    }

    public List<WebElement> getElement(By by) {
        return driver.findElements(By.xpath(""));
    }

    public WebElementActions click(String locator) {
        WebElement element = getElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }

    public WebElementActions clicks(String locator) {
        WebElement element = getElements(locator).get(1);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }
    public WebElementActions clickByNumber(String locator,int numberInList) {
        WebElement element = getElements(locator).get(numberInList-1);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }
    public WebElementActions clickConfirmWhenFewList(String locator) {
        List<WebElement> elements = getElements(locator);
        WebElement element = elements.get(elements.size() - 1);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }


    public WebElementActions input(String key, String value) {
        WebElement element = getElement(key);
        element.clear();
        element.sendKeys(value);
        return this;
    }

    public WebElementActions deletePreviousInputNew(String key, String value) {
        WebElement element = getElement(key);
        element.sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        element.sendKeys(value);
        return this;
    }

    public WebElementActions deletePrevious(String key) {
        WebElement element = getElement(key);
        element.sendKeys(Keys.CONTROL + "A", Keys.BACK_SPACE);
        return this;
    }

    public WebElementActions inputWithoutClear(String key, String value) {
        WebElement element = getElement(key);
        element.sendKeys(value);
        return this;
    }

    public boolean waitUntilAppear(String key, int wait) {
        int count = wait;
        while (!isClickable(key) && count-- > 0) {
            WaitUtils.setWait(1);
        }
        return count != 0;
    }

    public boolean waitUntilAppear(String key) {
        return waitUntilAppear(key, DEFAULT_IMPL_WAIT_SEC);
    }

    public boolean waitUntilDisappear(String key) {
        int count = DEFAULT_IMPL_WAIT_SEC;
        while (isPresent(key) && count-- > 0) {
            WaitUtils.setWait(1);
        }
        return count != 0;
    }

    public boolean isClickable(String key) {
        if (isPresent(key)) {
            WebElement wel = getElement(key);
            return isClickable(wel);
        }
        return false;
    }

    public boolean isClickable(WebElement element) {
        return element.isEnabled() && element.isDisplayed();
    }

    public boolean isPresent(String key) {

        waitModal();
        By by = UiMapping.ui(key);
        try {
            WebElement element = driver.findElement(by);
            return element.isDisplayed();
        } catch (Exception e) {
            log.info("Element is not found");
            return false;
        }
    }

    public boolean isDisplayed(String key) {
        try {
            return getElement(key).isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    public String getElementsText(WebElement element) {
        return element.getText();
    }

    public List<String> getTextsFromWebElements(String key) {
        WaitUtils.setWait(WAIT_LOAD_AVATAR_PLAYLIST);
        return this.getElements(key).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public WebElement getChild(WebElement parent, String key) {
        WebElement element = parent;
        try {
            element = parent.findElement(By.xpath(key));
        } catch (Exception e) {
            log.info("Element is not found", e.getMessage());
        }
        return element;
    }

    public WebElementActions inPutVerificationCode(String key, String value) {
        WebElement element = getElement(key);
        element.sendKeys(value);
        return this;
    }

    public boolean isEnableField(String key) {
        WebElement element = getElement(key);
        return element.isEnabled();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public WebElement getFirstElement(String keyFromLocators) {
        By element = UiMapping.ui(keyFromLocators);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        List<WebElement> listElements = driver.findElements(element);
        WebElement firstElement = null;
        for (WebElement listElement : listElements) {
            firstElement = listElement;
            break;
        }
        return firstElement;
    }

    public List<WebElement> getElementsWithWaiting(String keyFromLocators) {
        By element = UiMapping.ui(keyFromLocators);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        List<WebElement> listElements = driver.findElements(element);
        return listElements;
    }
    public WebElementActions moveToElementAndClickByKeyFromLocators(String keyFromLocators) {
        Actions action = new Actions(this.driver);
        action.moveToElement(getFirstElement(keyFromLocators))
                .click()
                .build()
                .perform();
        return this;
    }
}
