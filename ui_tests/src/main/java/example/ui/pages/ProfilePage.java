package example.ui.pages;

import example.identities.User;
import example.ui.pages.elements.Notification;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

import static example.config.TestDataProvider.*;
import static example.ui.enums.Locators.*;
import static example.ui.enums.NotificationMessage.CHANGES_SAVED;
import static example.ui.enums.NotificationMessage.PASSWORD_CHANGED;

@Getter
public class ProfilePage extends BasePage {
    /**
     * LOCATORS
     */
    private static final String NAME = "profile.line.name";
    private static final String USERNAME = "profile.line.username";
    private static final String ABOUT_BUTTON = "profile.about.button";
    private static final String BACK_BUTTON = "profile.back.button";
    private static final String ATTRIBUTE_ARIA_SELECTED = "aria-selected";
    private static final String CITY = "profile.city.input";
    private static final String COUNTRY = "profile.country.input";
    private static final String LABLE_EMAIL = "profile.lable.email";
    private static final String SAVE_BUTTON = "profile.save.button";
    private static final String DISPLAYED_PROFILE = "profile.displayed.profile";
    private static final String HIDDEN_AVATAR_BUTTON = "profile.avatar.hiddenbutton";
    private static final String AVATAR_CHANGE_BUTTON = "profile.avatar.changebutton";
    private static final String AVATAR_SAVE_BUTTON = "profile.avatar.savebutton";
    private static final String AVATAR_SIZE_CLOSE_BUTTON = "profile.avatar.avatarsizeclosebutton";
    private final Notification notificationChangesSaved;
    private final Notification notificationPasswordSuccessfullyChanged;

    public ProfilePage(WebDriver driver) {
        super(driver);
        notificationChangesSaved = new Notification(this.driverManager.getDriver(), CHANGES_SAVED, "notification.text.changessaved");
        notificationPasswordSuccessfullyChanged = new Notification(this.driverManager.getDriver(), PASSWORD_CHANGED, "notification.text.changed");
    }

    public String getName() {
        WebElement element = welActions.getElement(NAME);
        return welActions.getElementsText(element);
    }

    public String getCity() {
        WebElement element = welActions.getElement(CITY);
        return welActions.getElementsText(element);
    }

    public String getCountry() {
        WebElement element = welActions.getElement(COUNTRY);
        return welActions.getElementsText(element);
    }

    public ProfilePage deletePreviousInputNewEmailProfile(String email) {
        welActions.deletePreviousInputNew(PROFILE_EMAIL_INPUT.getValue(), email);
        return this;
    }

    public void clickBackButton() {
        welActions.click(BACK_BUTTON);
    }

    public void clickSaveButton() {
        welActions.click(SAVE_BUTTON);
    }

    public boolean isDisplayedProfilePage() {
        return welActions.isDisplayed(DISPLAYED_PROFILE);
    }

    public boolean canChangeField(String element) {
        return welActions.isEnableField(element);
    }

    public void clickDeleteButton() {
        welActions.click(PROFILE_DELETE_BUTTON.getValue());
    }

    public void clickConfirmDelete() {
        welActions.click(PROFILE_DELETE_BUTTON_YES.getValue());
    }

    public boolean isDisplayedChangeAvatarImageProfilePage() {
        return welActions.isDisplayed(DISPLAYED_CHANGE_AVATAR.getValue());
    }

    public String isDisplayedChangeAvatarTextProfilePage() {
        return welActions.getElementsText(welActions.getElement(DISPLAYED_CHANGE_AVATAR.getValue()));
    }

    public ProfilePage clickDeleteAccountButton() {
        welActions.click(DELETE_MY_ACCOUNT.getValue());
        return this;
    }

    public ProfilePage confirmationDeleteAccount() {
        welActions.click(DELETE_MY_ACCOUNT_CONFIRMATION.getValue());
        return this;
    }

    public ProfilePage clickImageAvatar() {
        welActions.moveToElementAndClick(PROFILE_AVATAR_IMAGE.getValue());
        return this;
    }

    public ProfilePage addImageAvatar(File file) {
        welActions.inputFilePath(PICTURE_INPUT_AVATAR.getValue(), file.getPath());
        return this;
    }

    public ProfilePage clickSaveAvatar() {
        welActions.click(SAVE_AVATAR_BUTTON.getValue());
        return this;
    }

    public ProfilePage moveToAvatar() {
        welActions.moveToElement(PROFILE_AVATAR_IMAGE.getValue());
        return this;
    }

    public ProfilePage scrollDownToDeleteMyAccount() {
        scrollDown(welActions.getElement(DELETE_MY_ACCOUNT.getValue()));
        return this;
    }

    public ProfilePage clickChangePassword() {
        welActions.click(PROFILE_CHANGE_PASSWORD_BUTTON.getValue());
        return this;
    }

    public void clickAvatarChangeButton() {
        welActions.waitUntilAppear(ABOUT_BUTTON);
        WebElement element = welActions.getInvisibleElement(HIDDEN_AVATAR_BUTTON);
        element.click();
    }

    public void clickUploadAvatarJpg() {
        WebElement element = welActions.getInvisibleElement(AVATAR_CHANGE_BUTTON);
        element.sendKeys(AVATAR_FILE.getAbsolutePath());
    }

    public void clickUploadAvatarJpeg() {
        WebElement element1 = welActions.getElement(AVATAR_SIZE_CLOSE_BUTTON);
        element1.click();
        WebElement element2 = welActions.getInvisibleElement(AVATAR_CHANGE_BUTTON);
        element2.sendKeys(AVATAR_FILE_JPEG.getAbsolutePath());
    }

    public void clickUploadAvatarPng() {
        WebElement element1 = welActions.getElement(AVATAR_SIZE_CLOSE_BUTTON);
        element1.click();
        WebElement element2 = welActions.getInvisibleElement(AVATAR_CHANGE_BUTTON);
        element2.sendKeys(AVATAR_FILE_PNG.getAbsolutePath());
    }

    public void clickAvatarSaveButton() {
        welActions.click(AVATAR_SAVE_BUTTON);
        welActions.waitUntilDisappear(String.valueOf(CHANGES_SAVED));
    }

    public boolean compareEnteringParameterWithProfileFirstName(User user) {
        return user.getFirstName().equals(welActions.getElement(PROFILE_FIRSTNAME_INPUT.getValue()).getAttribute("value"));
    }

    public boolean compareEnteringParameterWithProfileLastName(User user) {
        return user.getLastName().equals(welActions.getElement(PROFILE_LASTNAME_INPUT.getValue()).getAttribute("value"));
    }

    public boolean compareEnteringParameterWithProfileUserName(User user) {
        return user.getUsername().equals(welActions.getElement(PROFILE_USERNAME_INPUT.getValue()).getAttribute("value"));
    }

    public boolean compareEnteringParameterWithProfileEmail(User user) {
        return user.getEmail().equals(welActions.getElement(PROFILE_EMAIL_INPUT.getValue()).getAttribute("value"));
    }

    public boolean compareEnteringParameterWithProfilePhoneNumber(User user) {
        String phoneNumberFull = welActions.getElement(PROFILE_PHONE_NUMBER_INPUT.getValue()).getAttribute("value");
        phoneNumberFull = phoneNumberFull.replace(" ", "");
        int lengthPhoneNumberFull = phoneNumberFull.length();
        String phoneNumber = phoneNumberFull.substring(lengthPhoneNumberFull - 7, lengthPhoneNumberFull);
        int lengthPhoneNumberEntered = user.getPhone().length();
        String phoneNumberEntered = user.getPhone().substring(lengthPhoneNumberEntered - 7, lengthPhoneNumberEntered);
        return phoneNumberEntered.equals(phoneNumber);
    }


}




