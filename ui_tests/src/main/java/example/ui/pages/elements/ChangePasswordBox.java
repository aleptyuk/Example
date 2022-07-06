package example.ui.pages.elements;

import example.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;

import static example.ui.enums.Locators.*;


public class ChangePasswordBox extends BasePage {

    public ChangePasswordBox(WebDriver driver) {
        super(driver);
    }

    public ChangePasswordBox inputOldPassword(String password) {
        welActions.input(OLD_PASSWORD_INPUT.getValue(), password);
        return this;
    }

    public ChangePasswordBox inputNewPassword(String password) {
        welActions.input(NEW_PASSWORD_INPUT.getValue(), password);
        return this;
    }

    public ChangePasswordBox inputConfirmPassword(String password) {
        welActions.input(CONFIRMATION_PASSWORD_INPUT.getValue(), password);
        return this;
    }

    public void clickConfirmButton() {
        welActions.click(CONFIRMATION_PASSWORD_BUTTON.getValue());
    }

    public boolean checkFieldPassword() {
        return welActions.getElement(OLD_PASSWORD_INPUT.getValue())
                            .getAttribute("type").equals("password");
    }
}