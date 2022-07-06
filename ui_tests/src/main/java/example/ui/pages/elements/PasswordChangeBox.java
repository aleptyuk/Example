package example.ui.pages.elements;


import example.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;

import static example.ui.enums.Locators.*;


public class PasswordChangeBox extends BasePage {
    public PasswordChangeBox(WebDriver driver) {
        super(driver);
    }

    public PasswordChangeBox inPutVerificationCode(String verificationCode) {
        welActions.inPutVerificationCode(VERIFICATION_CODE_INPUT.getValue(), verificationCode);
        clickOnVerify();
        return this;
    }

    public void clickOnVerify() {
        welActions.click(VERIFY_BUTTON.getValue());
    }

    public PasswordChangeBox clickSelectCountryButton() {
        welActions.click(PASSWORD_CHANGE_BOX_COUNTRY_BUTTON.getValue());
        return this;
    }

    public PasswordChangeBox clickInputCountryField() {
        welActions.click(COUNTRY_INPUT.getValue());
        return this;
    }

    public PasswordChangeBox inputCountry(String value) {
        welActions.input(COUNTRY_INPUT.getValue(), value);
        return this;
    }

    public PasswordChangeBox inputPhone(String value) {
        welActions.input(PHONE_INPUT.getValue(), value);
        return this;
    }

    public PasswordChangeBox clickContinueButton() {
        welActions.click(CONTINUE_BUTTON.getValue());
        return this;
    }

    public PasswordChangeBox inputPassword(String value) {
        welActions.input(PASSWORD_INPUT_FIELD.getValue(), value);
        return this;
    }

    public PasswordChangeBox inputPasswordConfirm(String value) {
        welActions.input(PASSWORD_CONFIRM_INPUT_FIELD.getValue(), value);
        return this;
    }

    public PasswordChangeBox clickConfirm() {
        welActions.click(CONFIRM_BUTTON_ON_PASSWORD_CHANGE_BOX.getValue());
        return this;
    }

    public PasswordChangeBox clickOnCountryName() {
        welActions.click(CLICK_ON_COUNTRY_NAME.getValue());
        return this;
    }

    public boolean isDisplayedPopUpMessage() {
        return welActions.isDisplayed(POP_UP_MESSAGE_UNSUCCESS.getValue());
    }

    public boolean isDisplayedPopUpMessageUnsuccessEmail() {
        return welActions.isDisplayed(POP_UP_MESSAGE_UNSUCCESS_EMAIL.getValue());
    }

    public void waitUntilAppearPopUpMessage() {
        welActions.waitUntilAppear(POP_UP_MESSAGE_UNSUCCESS.getValue());
    }

    public void waitUntilAppearPopUpMessageUnsuccessEmail() {
        welActions.waitUntilAppear(POP_UP_MESSAGE_UNSUCCESS_EMAIL.getValue());
    }

    public boolean isDisplayedMessage(String locator) {
        return welActions.isDisplayed(locator);
    }

    public boolean checkingCountDigitsVerificationCode() {
        return welActions.getElement(VERIFICATION_CODE_INPUT.getValue()).getAttribute("value").length() == 4;
    }

    public boolean checkingVerificationCodeEnterOneIncorrect(int attributeBefore) {
        return welActions.getElement(VERIFICATION_CODE_INPUT.getValue()).getAttribute("value").length()==attributeBefore;

    }
    public int checkingVerificationCodeBeforeEnter() {
        return welActions.getElement(VERIFICATION_CODE_INPUT.getValue()).getAttribute("value").length();
    }
    public int deleteAndCheckingVerificationCodeBeforeEnter() {
        welActions.deletePrevious(VERIFICATION_CODE_INPUT.getValue());
        return welActions.getElement(VERIFICATION_CODE_INPUT.getValue()).getAttribute("value").length();
    }

    public PasswordChangeBox deletePreviousInputNew(String locator, String value) {
        welActions.deletePreviousInputNew(locator, value);
        return this;
    }
    public PasswordChangeBox deletePrevious(String locator) {
        welActions.deletePrevious(locator);
        return this;
    }

}
