package example.ui.pages;

import example.identities.UploadMusic;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static example.config.TestDataProvider.RADIOACTIVE_MP3;
import static example.ui.enums.Locators.*;


@Getter
@Setter
public class DashboardPage extends MainPage {

    private int countTracks;
    private int countTracksAfterDeleting;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage clickPlaylistsTab() {
        welActions.click(PLAYLISTS_TAB.getValue());
        return this;
    }

    public DashboardPage clickCreateNewPlaylistButton() {
        welActions.click(CREATE_NEW_PLAYLIST_BUTTON.getValue());
        return this;
    }

    public DashboardPage clickBackToDashboardButton() {
        welActions.click(BACK_TO_DASHBOARD_BUTTON.getValue());
        return this;
    }

    public boolean isDisplayedDashboard() {
        return welActions.isDisplayed(DISPLAYED_DASHBOARD.getValue());
    }

    public DashboardPage clickUploadMusicTab() {
        welActions.click(UPLOAD_MUSIC_TAB.getValue());
        return this;
    }

    public DashboardPage clickDashboardPlaylist() {
        welActions.click(DASHBOARD_PLAYLISTS.getValue());
        return this;
    }

    public DashboardPage clickCreateNewPlaylist() {
        welActions.click(DASHBOARD_CREATE_NEW_PLAYLIST.getValue());
        return this;
    }

    public DashboardPage inputPlayListInformation(String playlistName, String description) {
        welActions.click(DASHBOARD_PLAYLIST_NAME_FIELD.getValue());
        welActions.input(DASHBOARD_PLAYLIST_NAME_FIELD.getValue(), playlistName);
        welActions.click(DASHBOARD_DESCRIPTION_FIELD.getValue());
        welActions.input(DASHBOARD_DESCRIPTION_FIELD.getValue(), description);
        return this;
    }

    public DashboardPage clickSavePlayList() {
        welActions.click(DASHBOARD_PLAYLIST_SAVE.getValue());
        return this;
    }

    public DashboardPage addTrackToPlayList(UploadMusic uploadMusic) {
        scrollDown(welActions.getElement(DASHBOARD_PLAYLIST_SAVE.getValue()));
        welActions.input(ADD_TRACK_FIELD.getValue(), uploadMusic.getTitle());
        welActions.moveToElementAndClick(DASHBOARD_TRACKS_IN_SEARCH.getValue());
        welActions.click(DASHBOARD_PLAYLIST_SEARCH_ADD_TRACK.getValue());
        welActions.click(DASHBOARD_PLAYLIST_SEARCH_ADD_TRACK_TO_PLAYLIST.getValue());
        return this;
    }

    public DashboardPage moveToPlaylistFirst() {
        welActions.moveToElementFromList(DASHBOARD_PLAYLISTS_LIST.getValue());
        welActions.moveToElementFromList(DASHBOARD_PLAYLISTS_LIST_DELETE_BUTTON.getValue());
        if (welActions.isDisplayed(DASHBOARD_PLAYLISTS_LIST_PLAY_BUTTON.getValue())) {
            welActions.moveToElementFromList(DASHBOARD_PLAYLISTS_LIST_PLAY_BUTTON.getValue());
        }
        return this;
    }

    public DashboardPage moveToElementFromList() {
        welActions.moveToElementFromList(DASHBOARD_PLAYLISTS_LIST.getValue());
        return this;
    }

    public boolean checkIsVisibleDeleteButton() {
        return welActions.isDisplayed(DASHBOARD_PLAYLISTS_LIST_DELETE_BUTTON.getValue());
    }

    public boolean checkIsVisiblePlayButton() {
        return welActions.isDisplayed(DASHBOARD_PLAYLISTS_LIST_PLAY_BUTTON.getValue());
    }

    public DashboardPage deleteFirstPlaylist() {

        welActions.click(DASHBOARD_PLAYLISTS_LIST_DELETE_BUTTON.getValue());
        return this;
    }

    public DashboardPage clickConfirmWhenFewList() {
        welActions.clickConfirmWhenFewList(DASHBOARD_CONFIRM_MESSAGE_YES_BUTTON.getValue());
        return this;
    }

    public String getAuthorName() {
        return welActions.getElement(DASHBOARD_AUTHOR.getValue()).getText();
    }

    public List<WebElement> scrollDownAllUploadedTracks() {
        welActions.waitUntilAppear(SCROLL_TRACK_BY_TITLE.getValue());
        JavascriptExecutor js = driverManager.getJs();
        List<WebElement> tracks = welActions.getElements(SCROLL_TRACK_BY_TITLE.getValue());
        while (true) {
            for (int i = 0; i < tracks.size(); i++) {
                js.executeScript("arguments[0].scrollIntoView();", tracks.get(i));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<WebElement> tracksNew = welActions.getElements(SCROLL_TRACK_BY_TITLE.getValue());
            if (tracksNew.size() == tracks.size()) break;
            for (int i = tracks.size(); i < tracksNew.size(); i++) {
                js.executeScript("arguments[0].scrollIntoView();", tracksNew.get(i));
            }
            tracks = tracksNew;
        }
        return tracks;
    }

    public DashboardPage deleteLastUploadedTrack() {
        welActions.moveToElementAndClick(DELETE_LAST_TRACK.getValue());
        welActions.waitUntilAppear(POP_UP_DELETE_TRACK.getValue());
        welActions.waitUntilDisappear(POP_UP_DELETE_TRACK.getValue());
        return this;
    }

    public int getCountRecentlyUploadedTracks() {
        return welActions.getElements(SCROLL_TRACK_BY_TITLE.getValue()).size();
    }

    public int getCountRecentlyUploadedTracksAfterDeleting() {
        return welActions.getElements(SCROLL_TRACK_BY_TITLE.getValue()).size();
    }

    public DashboardPage clickUploadNewTrack() {
        welActions.click(DASHBOARD_UPLOAD_NEW_TRACK.getValue());
        return this;
    }

    public void uploadNewTrack() {
        WebElement element = welActions.getInvisibleElement(DASHBOARD_DROP_FILE.getValue());
        element.sendKeys(RADIOACTIVE_MP3.getAbsolutePath());
        welActions.click(DASHBOARD_BROWSE_FILE_UPLOAD_BUTTON.getValue());
    }

    public void inputTrackInformation(UploadMusic uploadMusic) {
        welActions.input(DASHBOARD_TITLE_INPUT.getValue(), uploadMusic.getTitle())
                .input(DASHBOARD_ALBUM_INPUT.getValue(), uploadMusic.getAlbum())
                .click(DASHBOARD_AUTHOR_BUTTON.getValue())
                .getElements(DASHBOARD_AUTHOR_CHOOSE.getValue()).get(0).click();
        welActions.click(DASHBOARD_GENRES_BUTTON.getValue())
                .getElements(DASHBOARD_GENRES_CHOOSE.getValue()).get(0).click();
    }

    public DashboardPage clickSave() {
        welActions.click(DASHBOARD_SAVE_BUTTON.getValue());
        return this;
    }

    public DashboardPage clickGoToExplorerPAge() {
        welActions.click(DASHBOARD_EXPLORE_BUTTON.getValue());
        return this;
    }

    public boolean checkSortingTrackByTitle(UploadMusic uploadMusic) {
        return uploadMusic.getTitle().equals(welActions.getElements(DASHBOARD_TRACK_TITLE.getValue()).get(0).getText());
    }

    public boolean checkSortingTrackByAlbum(UploadMusic uploadMusic) {
        return uploadMusic.getAlbum().equals(welActions.getElements(DASHBOARD_TRACK_ALBUM.getValue()).get(0).getText());
    }

    public void waitUntilDisappear(String key) {
        welActions.waitUntilAppear(key);
        welActions.waitUntilDisappear(key);
    }


    public void deleteAllTracks() {
        List<WebElement> elements = welActions.getElements(DASHBOARD_DELETE_TRACK_BUTTON.getValue());
        for (int i = 0; i < elements.size(); i++) {
            welActions.getElement(DASHBOARD_DELETE_TRACK_BUTTON.getValue()).click();
            welActions.click(POP_UP_MESSAGE.getValue());
        }
    }

    public DashboardPage movePlaylistAndClick() {
        welActions.moveToElementAndClickByKeyFromLocators(DASHBOARD_PLAYLISTS_LIST.getValue());
        return this;
    }

    public DashboardPage clickButtonNoOnModalWindow() {
        welActions.clickConfirmWhenFewList(DASHBOARD_CONFIRM_MESSAGE_NO_BUTTON.getValue());
        return this;
    }

    public void clickOnCreatedTestPlaylist(){
        welActions.moveToElementAndClick(FIND_TEST_PLAYLIST.getValue());
    }

    public void clickOnEditedTestPlaylist() {
        welActions.moveToElementAndClick(FIND_EDITED_PLAYLIST.getValue());
    }

    public DashboardPage clickEditButton() {
        welActions.moveToElementAndClickByKeyFromLocators(PLAYLIST_EDIT_PLAYLIST_BUTTON.getValue());
        return this;
    }

    public DashboardPage clickDeleteButton() {
        welActions.moveToElementAndClickByKeyFromLocators(PLAYLIST_EDIT_DELETE_BUTTON.getValue());
        return this;
    }

    public DashboardPage isPresent(String key) {
        welActions.isPresent(key);
        return this;
    }

    public String getAlertMessage() {
        return welActions.getFirstElement(DASHBOARD_CONFIRM_MESSAGE_PLAYLIST_WAS_REMOVED.getValue()).getText();
    }

    public int getCountOfPlaylist() {
        return welActions.getElementsWithWaiting(DASHBOARD_PLAYLISTS_LIST.getValue()).size();
    }

    public DashboardPage moveToPlaylistAndClickPlayButton(){
        welActions.moveToElementAndClickByKeyFromLocators(DASHBOARD_PLAYLISTS_LIST_PLAY_BUTTON.getValue());
        return this;
    }

    public int getCountOfTracksInPlayQueue(){
        return welActions.getElements(DASHBOARD_PLAYLISTS_PLAY_QUEUE_TRACKS.getValue()).size();
    }

    public String getTextTracksInPlaylist(){
        String textAboutTracksInPlaylist = welActions.getFirstElement(DASHBOARD_PLAYLISTS_LIST_TRACKS.getValue()).getText();
        return textAboutTracksInPlaylist;
    }

    public int getCountTracksInPlaylist(){
        String[] textTracksInPlaylistArray = getTextTracksInPlaylist().split("\\D+");
        int countTracks = Integer.parseInt(String.join("", textTracksInPlaylistArray));
        return countTracks;
    }

    public DashboardPage moveToUploadMusicTab(){
        welActions.moveToElementAndClickByKeyFromLocators(UPLOAD_MUSIC_TAB.getValue());
        return this;
    }

    public String getTitleTrack(){
        return welActions.getFirstElement(DASHBOARD_TRACK_TITLE.getValue()).getText();
    }

    public int getCountUploadTrack(){
        return welActions.getElementsWithWaiting(DASHBOARD_TRACK_TITLE.getValue()).size();
    }
}
