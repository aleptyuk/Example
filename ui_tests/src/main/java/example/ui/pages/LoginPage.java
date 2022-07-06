package example.ui.pages;

import example.ui.pages.elements.LoginBox;
import example.ui.pages.elements.Notification;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

import static example.ui.enums.Locators.*;
import static example.ui.enums.NotificationMessage.*;


@Getter
public class LoginPage extends BasePage {

    private static LoginBox loginBox;
    private Notification notificationWrongLogin;
    private Notification notificationUnsuccessfulAuth;
    private Notification notificationPhoneExist;
    private Notification hintPhoneInvalid;
    private Notification notificationEmailExist;


    public LoginPage(WebDriver driver) {
        super(driver);
        loginBox = new LoginBox(this.driverManager.getDriver());
        notificationWrongLogin = new Notification(this.driverManager.getDriver(), WRONG_LOGIN, "notification.text.wronglogin");
        notificationUnsuccessfulAuth = new Notification(this.driverManager.getDriver(), UNSUCCESSFUL_AUTH, "notification.text.unsuccessfulauth");
        notificationPhoneExist = new Notification(this.driverManager.getDriver(), PHONE_EXIST, "notification.text.phoneisalreadyregistered");
        hintPhoneInvalid = new Notification(this.driverManager.getDriver(), PHONE_INVALID_HINT, "loginbox.phonenumberinvalid.hint");
        notificationEmailExist = new Notification(this.driverManager.getDriver(), EMAIL_INVALID_HINT, "loginbox.emailinvalid.hint");
    }

    public LoginBox clickLoginButton() {
        welActions.click(LOGIN_BUTTON.getValue());
        return loginBox;
    }
    public boolean isPresentLoginButton() {
        return welActions.isPresent(LOGIN_BUTTON.getValue());
    }

    public LoginBox clickSignUpButton() {
        welActions.click(SIGN_UP_BUTTON.getValue());
        return loginBox;
    }
    public boolean isDisplayedPopUpMessage() {
        return welActions.isDisplayed(POP_UP_MESSAGE_SUCCESS.getValue());
    }

    public boolean isDisplayedLoginPage() {
        return welActions.isDisplayed(LOGIN_PAGE.getValue());
    }

    public void clickPopUmMessage() {
        welActions.click(POP_UP_MESSAGE.getValue());
    }
}

