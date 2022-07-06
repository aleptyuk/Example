package example.ui.pages;


import example.ui.pages.elements.Notification;
import example.utils.WaitUtils;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static example.config.TestConfigProvider.WAIT_LOAD_AVATAR_PLAYLIST;
import static example.ui.enums.Locators.*;
import static example.ui.enums.NotificationMessage.*;


@Getter
public class PlaylistsPage extends MainPage {
    private static final String PLAYLISTS_TAB = "playlistspage.playlists.tab";
    private static final String MY_PLAYLISTS_TAB = "playlistspage.myplaylists.tab";
    private static final String DISPLAYED_PLAYLIST = "playlistspage.displayed.playlist";
    private static final String PLAYLIST_PLAY_BUTTON = "playlistspage.playlist.play.button";
    private static final String PLAYLIST_QUEUE_BUTTON = "playlistspage.playlist.queue.button";
    private static final String PLAYLIST_QUEUE_TRACK = "playlistspage.queue.track";
    private static final String PLAYLIST_TRACK = "playlistspage.track";
    private static final String EDIT_PLAYLIST_BUTTON = "playlistpage.edit.playlist.button";
    private static final String ADD_TRACK_FIELD = "playlistspage.add.track.field";
    private static final String TRACK_IN_SEARCH_RESULTS = "playlistspage.track.in.search.results";
    private static final String SAVE_PLAYLIST_BUTTON = "playlistspage.save.playlist";
    private static final String DELETE_TRACK_BUTTON = "playlistspage.delete.playlist.track";
    private static final String DISPLAYED_PLAYLISTPAGE = "playlistspage.displayed.playlistpage";
    private final Notification notificationSuccessfulCreation;
    private final Notification notificationRemoveMyPlaylist;
    private final Notification notificationPlaylistChangesSaved;

    public PlaylistsPage(WebDriver driver) {
        super(driver);
        notificationSuccessfulCreation = new Notification(this.driverManager.getDriver(), SUCCESSFUL_CREATION, "notification.text.successfulcreation");
        notificationRemoveMyPlaylist = new Notification(this.driverManager.getDriver(), REMOVE_MY_PLAYLIST, "notification.text.remove");
        notificationPlaylistChangesSaved = new Notification(this.driverManager.getDriver(), PLAYLIST_SAVED, "notification.text.playlistsaved");
    }

    public boolean isDisplayedPlaylist(String playlist) {
        WaitUtils.setWait(WAIT_LOAD_AVATAR_PLAYLIST);
        List<WebElement> elements = welActions.getElements(DISPLAYED_PLAYLIST);
        return elements.stream().anyMatch(p -> playlist.equals(p.getText()));
    }

    public PlaylistsPage clickPlaylistsTab() {
        welActions.click(PLAYLISTS_TAB);
        return this;
    }

    public PlaylistsPage clickSavePlaylist() {
        welActions.click(SAVE_PLAYLIST_BUTTON);
        return this;
    }

    public void clickDeleteTrackButton() {
        welActions.click(DELETE_TRACK_BUTTON);
    }

    public PlaylistsPage inputTrackName(String trackName) {
        welActions.inputWithoutClear(ADD_TRACK_FIELD, trackName);
        return this;
    }

    public WebElement getTrackFromSearchResults() {
        return welActions.getElement(TRACK_IN_SEARCH_RESULTS);
    }

    public PlaylistsPage clickMyPlaylistsTab() {
        welActions.click(MY_PLAYLISTS_TAB);
        return this;
    }

    public WebElement playlist() {
        return welActions.getElement(DISPLAYED_PLAYLIST);
    }

    public void clickOnPlaylist() {
        welActions.click(DISPLAYED_PLAYLIST);
    }

    public PlaylistsPage clickEditPlaylist() {
        welActions.click(EDIT_PLAYLIST_BUTTON);
        return this;
    }

    public WebElement playlistPlayButton() {
        return welActions.getElementNoWait(PLAYLIST_PLAY_BUTTON);
    }

    public void playlistQueueButton() {
        welActions.click(PLAYLIST_QUEUE_BUTTON);
    }

    public List<String> getQueueTracks() {
        return welActions.getTextsFromWebElements(PLAYLIST_QUEUE_TRACK);
    }

    public List<String> getPlaylistTracks() {
        return welActions.getTextsFromWebElements(PLAYLIST_TRACK);
    }

    public boolean isDisplayedPlaylistPage() {
        return welActions.isDisplayed(DISPLAYED_PLAYLISTPAGE)
                && welActions.getElement(PLAYLISTS_BUTTON.getValue()).getAttribute("aria-current").equals("page");
    }

    public PlaylistsPage removeMyPlaylist() {
        welActions.moveToElement(MODAL_WINDOW_REMOVE_MY_PLAYLIST.getValue());
        welActions.click(REMOVE_MY_PLAYLIST_BUTTON.getValue());
        confirmationRemoveMyPlaylist();
        return this;
    }

    public PlaylistsPage confirmationRemoveMyPlaylist() {
        welActions.click(CONFIRMATION_REMOVE_MY_PLAYLIST_BUTTON.getValue());
        return this;
    }

    public PlaylistsPage clickFirstPlaylist() {
        welActions.click(MODAL_WINDOW_REMOVE_MY_PLAYLIST.getValue());
        return this;
    }

    public PlaylistsPage clickPlayAllButton() {
        welActions.click(PLAYLIST_PLAY_ALL_BUTTON.getValue());
        return this;
    }

    public boolean isDisplayedPlayAllIconPlay() {
        return welActions.getElements(PLAYLIST_PLAY_ALL_ICON.getValue()).size() == 1;
    }

    public boolean isDisplayedPlayAllIconPause() {
        return welActions.getElements(PLAYLIST_PLAY_ALL_ICON.getValue()).size() == 2;
    }

    public PlaylistsPage moveToPlaylist() {
        welActions.moveToElement(MODAL_WINDOW_REMOVE_MY_PLAYLIST.getValue());
        return this;
    }

    public PlaylistsPage moveToButtonLikePlaylist() {
        welActions.moveToElements(welActions.getElement(MODAL_WINDOW_REMOVE_MY_PLAYLIST.getValue())
                , welActions.getElement(PLAYLIST_LIKE_BUTTON.getValue()));
        return this;
    }

    public int getNumberLikePlaylist() {
        moveToButtonLikePlaylist();
        return Integer.parseInt(welActions.getElement(PLAYLIST_LIKE_COUNT.getValue()).getText());
    }

    public PlaylistsPage clickButtonLikePlaylist() {
        moveToPlaylist();
        welActions.click(PLAYLIST_LIKE_BUTTON.getValue());
        return this;
    }

    public boolean isDisplayedAddRemoveLikePlaylist() {
        moveToPlaylist();
        return welActions.isDisplayed(PLAYLIST_LIKE_BUTTON.getValue());
    }

    public PlaylistsPage inputNamePlaylist(String name) {
        welActions.deletePreviousInputNew(EDIT_PLAYLIST_NAME_INPUT.getValue(), name);
        return this;
    }

    public PlaylistsPage inputDescriptionPlaylist(String name) {
        welActions.deletePreviousInputNew(EDIT_PLAYLIST_DESCRIPTION_INPUT.getValue(), name);
        return this;
    }

    public boolean isDisplayedMessageInvalidDataName() {
        return welActions.isDisplayed(MESSAGE_INVALID_DATA_NAME.getValue());
    }

    public boolean checkColorMessageInvalidDataName() {
        return welActions.getElement(MESSAGE_INVALID_DATA_NAME.getValue())
                .getCssValue("color")
                .equals("rgba(254, 218, 0, 1)");
    }

    public boolean checkTextMessageInvalidDataName(String textMessage) {
        return welActions.getElement(MESSAGE_INVALID_DATA_NAME.getValue())
                .getText()
                .equals(textMessage);
    }

    public boolean isDisplayedMessageInvalidDataDescription() {
        return welActions.isDisplayed(MESSAGE_INVALID_DATA_DESCRIPTION.getValue());
    }

    public boolean checkColorMessageInvalidDataDescription() {
        return welActions.getElement(MESSAGE_INVALID_DATA_DESCRIPTION.getValue())
                .getCssValue("color")
                .equals("rgba(254, 218, 0, 1)");
    }

    public boolean checkTextMessageInvalidDataDescription(String textMessage) {
        return welActions.getElement(MESSAGE_INVALID_DATA_DESCRIPTION.getValue())
                .getText()
                .equals(textMessage);
    }

    public boolean isDisplayedMessageInvalidDataImage() {
        return welActions.isDisplayed(MESSAGE_INVALID_DATA_IMAGE.getValue());
    }

    public boolean checkColorMessageInvalidDataImage() {
        return welActions.getElement(MESSAGE_INVALID_DATA_IMAGE.getValue())
                .getCssValue("color")
                .equals("rgba(254, 218, 0, 1)");
    }

    public boolean checkTextMessageInvalidDataImage(String textMessage) {
        return welActions.getElement(MESSAGE_INVALID_DATA_IMAGE.getValue())
                .getText()
                .equals(textMessage);
    }

    public boolean isDisplayedCoverPlaylist() {
        return welActions.isDisplayed(PLAYLIST_COVER.getValue());
    }

    public boolean isDisplayedTitlePlaylist() {
        return welActions.isDisplayed(PLAYLIST_TITLE.getValue());
    }

    public boolean isDisplayedDescriptionPlaylist() {
        return welActions.isDisplayed(PLAYLIST_DESCRIPTION.getValue());
    }

    public boolean isDisplayedNumberTracksPlaylist() {
        return welActions.isDisplayed(PLAYLIST_NUMBER_TRACKS.getValue());
    }

    public boolean isDisplayedNumberLikesPlaylist() {
        return welActions.isDisplayed(PLAYLIST_NUMBER_LIKES.getValue());
    }

    public boolean isDisplayedPlayAllButton() {
        return welActions.isDisplayed(PLAYLIST_PLAY_ALL_BUTTON.getValue());
    }

    public boolean isDisplayedTrackInPlaylist() {
        return welActions.isDisplayed(TRACK_IN_PLAYLIST.getValue());
    }

    public List<String> getNamesPlaylists() {
        return welActions.getTextsFromWebElements(NAME_PLAYLIST.getValue());
    }

    public PlaylistsPage clickPublicMyPlaylist(List<String> namesMyPlaylist) {
        List<WebElement> publicPlaylist = welActions.getElements(NAME_PLAYLIST.getValue());
        for (WebElement element : publicPlaylist) {
            for (String name : namesMyPlaylist) {
                if (element.getText().equals(name)) {
                    element.click();
                    return this;
                }
            }
        }
        return this;
    }

    public PlaylistsPage clickPublicMyPlaylist(String nameMyPlaylist) {
        List<WebElement> publicPlaylist = welActions.getElements(NAME_PLAYLIST.getValue());
        for (WebElement element : publicPlaylist) {
            if (element.getText().equals(nameMyPlaylist)) {
                element.click();
                return this;
            }
        }
        return this;
    }

    public void clickPublicNotMyPlaylist(List<String> namesMyPlaylist) {
        List<WebElement> publicPlaylist = welActions.getElements(NAME_PLAYLIST.getValue());
        for (WebElement element : publicPlaylist) {
            for (String name : namesMyPlaylist) {
                if (!element.getText().equals(name)) {
                    element.click();
                    return;
                }
            }
        }
    }

    public PlaylistsPage clickMyPlaylistNotPublic(List<String> publicPlaylist) {
        List<WebElement> namesMyPlaylist = welActions.getElements(NAME_PLAYLIST.getValue());
        for (WebElement element : namesMyPlaylist) {
            if (!publicPlaylist.stream().anyMatch(name -> name.equals(element.getText()))) {
                element.click();
                return this;
            }
        }
        return this;
    }

    public String getPlaylistsName() {
        return welActions.getElement(PLAYLIST_TITLE.getValue()).getText();
    }


    public boolean isDisplayedEditButton() {
        return welActions.isDisplayed(EDIT_PLAYLIST_BUTTON);
    }

    public boolean isDisplayedCoverEditPlaylist() {
        return welActions.isDisplayed(PLAYLIST_EDIT_INFORMATION_COVER.getValue());
    }

    public boolean isDisplayedTitleEditPlaylist() {
        return welActions.isDisplayed(EDIT_PLAYLIST_NAME_INPUT.getValue());
    }

    public boolean isDisplayedDescriptionEditPlaylist() {
        return welActions.isDisplayed(EDIT_PLAYLIST_DESCRIPTION_INPUT.getValue());
    }

    public boolean isDisplayedUploadButton() {
        return welActions.isDisplayed(DASHBOARD_BROWSE_FILE_UPLOAD_BUTTON.getValue());
    }

    public boolean isDisplayedSharePlaylistText() {
        return welActions.isDisplayed(PLAYLIST_EDIT_INFORMATION_SHARE_TEXT.getValue());
    }

    public PlaylistsPage clickSharePlaylistButton() {
        welActions.click(PLAYLIST_EDIT_INFORMATION_SHARE_BUTTON.getValue());
        return this;
    }

    public boolean isDisplayedSharePlaylistButton(String textButton) {
        return welActions.getElement(PLAYLIST_EDIT_INFORMATION_SHARE_BUTTON_TEXT.getValue())
                .getText()
                .equals(textButton);
    }

    public boolean isDisplayedMyPlaylistInPublicPlaylistsTab(String namePlaylist) {
        WaitUtils.setWait(WAIT_LOAD_AVATAR_PLAYLIST);
        List<WebElement> elements = welActions.getElements(NAME_PLAYLIST.getValue());
        return elements.stream().anyMatch(p -> namePlaylist.equals(p.getText()));
    }
    public String checkName() {
        WebElement nameText = welActions.getElement(CHECK_NAME_PLAYLIST.getValue());
        return welActions.getElementsText(nameText);
    }

    public String checkDescription() {
        WebElement descriptionText = welActions.getElement(CHECK_DESCRIPTION_PLAYLIST.getValue());
        return welActions.getElementsText(descriptionText);
    }

    public boolean isEditedPlaylist() {
        return (checkName().equals("NEW TEST NAME")) && (checkDescription().equals("NEW TEST DESCRIPTION"));
    }

    public void clickOnDeletePlaylistButton() {
        welActions.click(DELETE_PLAYLIST_BUTTON.getValue());
        welActions.moveToElementAndClick(NO_BUTTON_TO_DELETE_PLAYLIST.getValue());
        welActions.click(DELETE_PLAYLIST_BUTTON.getValue());
        welActions.moveToElementAndClick(YES_BUTTON_TO_DELETE_PLAYLIST.getValue());
        welActions.waitUntilAppear(POP_UP_MESSAGE.getValue());
        welActions.waitUntilDisappear(POP_UP_MESSAGE.getValue());
    }
}