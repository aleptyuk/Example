package example.ui.pages.elements;

import example.ui.enums.NotificationMessage;
import example.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class Notification extends BasePage {
    private static final String MODAL = "notification.modal";
    private static final String MODAL_TEXT_UNSUCCESSFUL_AUTH = "notification.text.unsuccessfulauth";
    private static final String MODAL_TEXT_WRONG_LOGIN = "notification.text.wronglogin";
    private static final String MODAL_TEXT_PHONE_IS_ALREADY_REGISTRED = "Phone number is already registered!";
    public static final String MODAL_TEXT_SUCCESSFUL_CREATION = "notification.text.successfulcreation";
    private static final String MODAL_TEXT_REMOVE_MY_PLAYLIST = "notification.text.remove";
    private static final String MODAL_SAVED_CHANGES = "notification.text.changessaved";
    private static final String TRACK_UPLOADED = "notification.text.trackuploaded";

    private NotificationMessage message;
    private String locator;

    public Notification(WebDriver driver, NotificationMessage message, String locator) {
        super(driver);
        this.message = message;
        this.locator = locator;
    }

    public boolean isDisplayed() {
        try {
            return welActions.isDisplayed(this.locator);
        } catch (Exception e){
            return false;
        }
    }
    public boolean waitUntilDisappear(){
        return welActions.waitUntilDisappear(this.locator);
    }
}
