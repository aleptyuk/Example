package example.ui.pages.elements;

import example.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;

import static example.ui.enums.Locators.*;


public class LoginBox extends BasePage {

    private static final String LOGIN_BUTTON = "loginbox.login.button";
    private static final String EMAIL_INPUT = "loginbox.email.input";
    private static final String MODAL = "loginbox.modal";
    private static final String LOGIN_VIA_EMAIL_BUTTON = "loginbox.loginviaemail.button";
    private static final String SELECT_COUNTRY_BUTTON = "loginbox.selectcountry.button";
    private static final String COUNTRY_DROPDOWN_INFO = "loginbox.countrydropdown.info";

    public LoginBox(WebDriver driver) {
        super(driver);
    }

    public LoginBox inputPhone(String value) {
        welActions.input(PHONE_INPUT.getValue(), value);
        return this;
    }

    public LoginBox inputEmail(String value) {
        welActions.input(EMAIL_INPUT, value);
        return this;
    }

    public LoginBox inputPassword(String value) {
        welActions.input(PASSWORD_INPUT.getValue(), value);
        return this;
    }

    public LoginBox inputCountry(String value) {
        welActions.input(COUNTRY_INPUT.getValue(), value);
        return this;
    }

    public LoginBox clickLoginButton() {
        welActions.click(LOGIN_BUTTON);
        return this;
    }

    public LoginBox clickLoginViaEmailButton() {
        welActions.click(LOGIN_VIA_EMAIL_BUTTON);
        return this;
    }

    public LoginBox waitUntilAppear() {
        welActions.waitUntilAppear(MODAL);
        return this;
    }

    public LoginBox clickSelectCountryButton() {
        welActions.click(SELECT_COUNTRY_BUTTON);
        return this;
    }

    public LoginBox clickInputCountryField() {
        welActions.click(COUNTRY_INPUT.getValue());
        return this;
    }

    public LoginBox clickOnCountryName() {
        welActions.click(CLICK_ON_COUNTRY_NAME.getValue());
        return this;
    }

    public LoginBox clickCountryDropdownInfo() {
        welActions.click(COUNTRY_DROPDOWN_INFO);
        return this;
    }

    public LoginBox clickContinueButton() {
        welActions.click(CONTINUE_BUTTON.getValue());
        return this;
    }

    public BasePage waitUntilDisappear() {
        welActions.waitUntilDisappear(MODAL);
        return this;
    }

    public LoginBox clickForgotPassword() {
        welActions.click(FORGOT_PASSWORD.getValue());
        return this;
    }

    public LoginBox clickRecoverViaEmail() {
        welActions.click(RECOVER_VIA_EMAIL.getValue());
        return this;
    }

    public boolean isDisplayedPopUpMessageUnSuccess() {
        return welActions.isDisplayed(POP_UP_MESSAGE_UNSUCCESS_AUTHORIZATION.getValue());
    }

    public boolean isEmptyLoginField() {
        return welActions.getElement(PHONE_INPUT.getValue()).getAttribute("value").length() < 6;
    }

    public boolean isEmptyPasswordField() {
        return welActions.getElement(PASSWORD_INPUT.getValue()).getAttribute("value").isEmpty();
    }

    public LoginBox clickRememberMe() {
        welActions.click(REMEMBER_ME_BUTTON.getValue());
        return this;
    }
}
