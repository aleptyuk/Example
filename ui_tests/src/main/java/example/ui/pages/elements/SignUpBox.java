package example.ui.pages.elements;


import example.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;

import static example.ui.enums.Locators.*;


public class SignUpBox extends BasePage {
    public SignUpBox(WebDriver driver) {
        super(driver);
    }

    public SignUpBox inPutVerificationCode(String verificationCode) {
        welActions.inPutVerificationCode(VERIFICATION_CODE_INPUT.getValue(), verificationCode);
        return this;
    }

    public void clickOnVerify() {
        welActions.click(VERIFY_BUTTON.getValue());
    }

    public SignUpBox inputUsername(String value) {
        welActions.input(USERNAME_INPUT_FIELD.getValue(), value);
        return this;
    }

    public SignUpBox inputPassword(String value) {
        welActions.input(PASSWORD_INPUT_FIELD.getValue(), value);
        return this;
    }

    public SignUpBox inputPasswordConfirm(String value) {
        welActions.input(PASSWORD_CONFIRM_INPUT_FIELD.getValue(), value);
        return this;
    }

    public SignUpBox clickSignUp() {
        welActions.click(SIGN_UP_BUTTON_ON_SIGN_UP_BOX.getValue());
        return this;
    }

    public SignUpBox clickSignUpFormSave() {
        welActions.click(SIGN_UP_BUTTON_ON_SIGN_UP_BOX.getValue());
        return this;
    }

    public SignUpBox inputFirstName(String firstName) {
        welActions.input(FIRST_NAME_INPUT.getValue(), firstName);
        return this;
    }

    public SignUpBox inputLastName(String lastName) {
        welActions.input(LAST_NAME_INPUT.getValue(), lastName);
        return this;
    }

    public SignUpBox inputEmail(String email) {
        welActions.input(EMAIL_INPUT.getValue(), email);
        return this;
    }

    public boolean isDisplayedMessagePasswordsMustMatch() {
        return welActions.isDisplayed(SIGN_UP_MESSAGE_PASSWORD_MUST_MATH.getValue());
    }

    public SignUpBox waitUntilDisappearTimeVerification() {
        welActions.waitUntilDisappear(SIGN_UP_TIME_VERIFICATION.getValue());
        return this;
    }

    public boolean isClickableButtonRequestVerification() {
        return welActions.isClickable(welActions.getElement(NEW_VERIFICATION_CODE_BUTTON.getValue()));
    }

    public SignUpBox clickLogin() {
        welActions.click(SIGN_UP_LOGIN_BUTTON.getValue());
        return this;
    }

}
