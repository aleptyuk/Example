package example.ui.pages;

import example.ui.pages.elements.Notification;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static example.config.TestDataProvider.INVALID_MP3_FILE;
import static example.config.TestDataProvider.SAMPLE_MP3_FILE;
import static example.ui.enums.Locators.*;
import static example.ui.enums.NotificationMessage.TRACK_UPLOADED;


@Getter
public class UploadMusicPage extends MainPage {

    public String locator;
    private final Notification notificationTrackUploaded;

    public UploadMusicPage(WebDriver driver) {
        super(driver);
        notificationTrackUploaded = new Notification(driverManager.getDriver(), TRACK_UPLOADED, "notification.text.trackuploaded");
    }

    public boolean isDisplayedUploadMusicPage() {
        return welActions.isDisplayed(BACK_TO_DASHBOARD_BUTTON.getValue());
    }

    public UploadMusicPage clickUploadANewTrackButton() {
        welActions.click(UPLOAD_A_NEW_TRACK_BUTTON.getValue());
        return this;
    }

    public UploadMusicPage clickUploadTrackDropFileButton() {
        WebElement element = welActions.getInvisibleElement(UPLOAD_TRACK_DROP_FILE_BUTTON.getValue());
        element.sendKeys(SAMPLE_MP3_FILE.getAbsolutePath());
        welActions.click(UPLOAD_BUTTON.getValue());
        return this;
    }

    public UploadMusicPage clickUploadTrackDropFileButtonForInvalidTrack() {
        WebElement element = welActions.getInvisibleElement(UPLOAD_TRACK_DROP_FILE_BUTTON.getValue());
        element.sendKeys(INVALID_MP3_FILE.getAbsolutePath());
        welActions.click(UPLOAD_BUTTON.getValue());
        return this;
    }

    public void waitUntilDisappearNotification() {
        if (welActions.isDisplayed(TRACK_UPLOADED.getValue())) {
            welActions.waitUntilDisappear(TRACK_UPLOADED.getValue());
        }
    }
    public boolean isDisplayedErrorAboutInvalidDTrackSize(){
        return welActions.isDisplayed(INVALID_TRACK_SIZE_ERORR.getValue());
    }

    public UploadMusicPage clickCancelUploadButton(){
        welActions.click(CANCEL_UPLOAD_BUTTON.getValue());
        return this;
    }
}
