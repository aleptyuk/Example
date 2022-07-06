package example.ui.driver;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Data
@Slf4j
public class BrowserManager {

    private WebDriver driver;

    public BrowserManager(WebDriver driver) {
        this.driver = driver;
    }

    public void reloadPage() {
        driver.navigate().refresh();
    }

}
