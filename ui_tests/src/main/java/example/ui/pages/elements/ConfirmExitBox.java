package example.ui.pages.elements;

import example.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;


public class ConfirmExitBox extends BasePage {
    private static final String  YES_BUTTON = "confirmexit.yes.button";

    public ConfirmExitBox(WebDriver driver) {
        super(driver);
    }

    public void clickYesButton() {
        welActions.click(YES_BUTTON);
    }

    public ConfirmExitBox waitUntilAppear(){
        welActions.waitUntilAppear(YES_BUTTON);
        return this;
    }

}
