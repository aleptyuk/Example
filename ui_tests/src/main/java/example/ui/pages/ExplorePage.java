package example.ui.pages;



import example.ui.pages.elements.AccountDropdown;
import example.ui.pages.elements.Notification;
import example.ui.pages.elements.UserBox;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static example.ui.enums.Locators.*;
import static example.ui.enums.NotificationMessage.NOTHING_FOUND_HINT;
import static org.testng.Assert.assertTrue;



@Getter
@Setter
public class ExplorePage extends BasePage {
    private static final String USERNAME_BUTTON = "explorepage.username.button";
    private static final String EXPLORE_BUTTON = "explorepage.explore.button";
    private static final String RECOMMENDED_PLAYLISTS = "explorepage.recommended.playlists";

    private static final String RECOMMENDED_PLAYLISTS_TEXT_FIELD = "explorepage.recommended.playlists.text.field";
    private static final String RECOMMENDED_NOW_TRENDING_TEXT_FIELD = "explorepage.recommended.now.trending.text.field";
    private static final String RECOMMENDED_GENRES_TEXT_FIELD = "explorepage.genres.text.field";
    private static final String RECOMMENDED_PLAYLIST = "explorepage.recommended.playlist";
    private static final String NOW_TRENDING_FIRST_SONG = "explorepage.now.trending.first.song";
    private static final String NOW_TRENDING_ADD_QUEUE_BUTTON = "explorepage.now.trending.add.queue.button";
    private static final String RECOMMENDED_ADD_QUEUE_BUTTON = "explorepage.recommended.add.queue.button";
    private static final String RECOMMENDED_SONG_PLAY_BUTTON = "explorepage.recommended.songs.play.button";
    private static final String RECOMMENDED_SONG_DISLIKE_BUTTON = "explorepage.recommended.songs.dislike.button";
    private static final String RECOMMENDED_SONG_LIKE_COUNTER = "explorepage.recommended.songs.like.counter";
    private static final String RECOMMENDED_PLAYLIST_LIKE_BUTTON = "explorepage.recommended.playlist.like.button";
    private static final String RECOMMENDED_PLAYLIST_LIKE_COUNTER = "explorepage.recommended.playlist.like.counter";

    private static final String PLAYING_SONG_LIKE_BUTTON = "explorepage.playing.song.like.button";
    private static final String PLAYING_SONG_LIKE_COUNTER = "explorepage.playing.song.like.counter";
    private static final String MENU_ADD_TRACK_TO_QUEUE = "explorepage.add.track.to.queue";
    private static final String MENU_ADD_TRACK_TO_PLAYLIST = "explorepage.add.track.to.playlist";
    private static final String REMOVE_TRACK_IN_QUEUE = "explorepage.remove.track.in.queue";
    private static final String TRACK_NAME_IN_QUEUE = "explorepage.trackname.in.queue";

    private static final String DISPLAYED_EXPLOREPAGE = "explorepage.displayed.explorepage";
    private static final String VIEW_ALL_ARTISTS = "explorepage.viewall.button";
    private static final String ALL_GENERS = "explorepage.geners.field";
    private static final String LOGO_GENERS = "explorepage.geners.logo";
    private static final String GENERS_BUTTON = "explorepage.geners.button";
    private static final String FOLK_BUTTON = "explorepage.folk.button";
    private static final String ROCK_BUTTON = "explorepage.rock.button";
    private static final String RAP_BUTTON = "explorepage.rap.button";
    private static final String CLASSICAL_BUTTON = "explorepage.classical.button";
    private static final String JAZZ_BUTTON = "explorepage.jazz.button";
    private static final String METALL_BUTTON = "explorepage.metall.button";
    private static final String HARCORE_BUTTON = "explorepage.hardcore.button";
    private static final String TRACE_BUTTON = "explorepage.trance.button";
    private static final String HOUSE_BUTTON = "explorepage.house.button";
    private static final String PUNK_BUTTON = "explorepage.punk.button";
    private static final String GRANGE_BUTTON = "explorepage.grange.button";
    private static final String CHANSON_BUTTON = "explorepage.chanson.button";
    private static final String FUNK_BUTTON = "explorepage.funk.button";
    private static final String ETHNIC_BUTTON = "explorepage.ethnic.button";
    private static final String REGGAE_BUTTON = "explorepage.reggae.button";
    private static final String LOUNGE_BUTTON = "explorepage.lounge.button";
    private static final String BACK_TO_EXPLORE = "popularartistspage.back.button";
    private static final String PLAYLIST_BUTTON = "explorepage.playlist.button";
    private static final String TRENDING_NOW_BUTTON = "explorepage.recommended.playlistsnow";

    private static UserBox userBox;
    private Notification notificationNothingFoundExist;
    private AccountDropdown accountDropdown;
    public static final String TITLE = "A-music";

    private int likeCount;
    private int likeCountAfterDislike;
    private int likeCountAfterLike;

    private List<String> tracksQueue = new ArrayList();
    private List<String> tracksQueueAfterRemoval = new ArrayList();

    public ExplorePage(WebDriver driver) {
        super(driver);
        userBox = new UserBox(this.driverManager.getDriver());
        accountDropdown = new AccountDropdown(this.driverManager.getDriver());
        notificationNothingFoundExist = new Notification(this.driverManager.getDriver(), NOTHING_FOUND_HINT, "searchbox.nothingfound.hint");
    }

    public void waitUntilAppearUsernameButton() {
        welActions.waitUntilAppear(USERNAME_BUTTON);

    }

    public void clickUserNameButton() {
        welActions.click(USERNAME_BUTTON);
    }

    public void clickPopUmMessage() {
        welActions.click(POP_UP_MESSAGE.getValue());
    }

    public ExplorePage clickSearch() {
        welActions.click(EXPLORE_PAGE_SEARCH.getValue());
        return this;
    }

    public ExplorePage inPutAuthor(String author) {
        welActions.input(EXPLORE_PAGE_SEARCH.getValue(), author);
        return this;
    }

    public ExplorePage inPutAuthorRandom(String author) {
        welActions.deletePreviousInputNew(EXPLORE_PAGE_SEARCH.getValue(), author);
        return this;
    }

    public boolean checkSearchMessage() {
        return welActions.isDisplayed(EXPLORE_PAGE_SEARCH_NOTHING_FOUND.getValue());
    }

    public WebElement recommendedSong() {
        return welActions.getElement(RECOMMENDED_SONG.getValue());
    }

    public WebElement secondRecommendedSong() {
        return welActions.getElements(RECOMMENDED_SONG.getValue()).get(1);
    }

    public WebElement nextRecommendedSong() {
        return welActions.getElements(RECOMMENDED_SONG.getValue()).get(1);
    }

    public WebElement secondRecommendedSongPlay() {
        return welActions.getElements(RECOMMENDED_SONG_PLAY_PANEL.getValue()).get(0);
    }

    public WebElement NowTrendingFirstSong() {
        return welActions.getElement(NOW_TRENDING_FIRST_SONG);
    }

    public WebElement recommendedPlayList() {
        return welActions.getElement(RECOMMENDED_PLAYLIST);
    }

    public boolean isDislikeDisplayedOnPlayList() {
        String dislikeButtonXpath = "//*[text()='Recommended playlists']/..//p/following-sibling::div[@title='Добавить в избранное']";
        byte[] bytes = StringUtils.getBytesUtf8(dislikeButtonXpath);
        String encodedDislikeButtonXpath = StringUtils.newStringUtf8(bytes);
        return !driverManager.getDriver().findElements(By.xpath(encodedDislikeButtonXpath)).isEmpty();
    }

    public WebElement recommendedSongPlayButton() {
        return welActions.getElementNoWait(RECOMMENDED_SONG_PLAY_PANEL.getValue());
    }

    public List<String> getQueueTracks() {
        return welActions.getTextsFromWebElements(TRACK_NAME_IN_QUEUE);
    }

    public WebElement recommendedSongAddQueueButton() {
        return welActions.getElements(RECOMMENDED_ADD_QUEUE_BUTTON).get(1);
    }

    public WebElement addQueueButtonInPlayList() {
        return welActions.getElementNoWait(RECOMMENDED_ADD_QUEUE_BUTTON);
    }

    public WebElement NowTrendingAddQueueButton() {
        return welActions.getElements(NOW_TRENDING_ADD_QUEUE_BUTTON).get(0);
    }

    public WebElement recommendedSongDislikeButton() {
        return welActions.getElementNoWait(RECOMMENDED_SONG_DISLIKE_BUTTON);
    }

    public WebElement recommendedPlayListLikeButton() {
        return welActions.getElementNoWait(RECOMMENDED_PLAYLIST_LIKE_BUTTON);
    }


    public WebElement recommendedPlayListLikeCounter() {
        return welActions.getElement(RECOMMENDED_PLAYLIST_LIKE_COUNTER);
    }

    public void clickMenuAddTrackInQueue() {
        welActions.getElement(MENU_ADD_TRACK_TO_QUEUE).click();
    }

    public void clickMenuAddTrackInPlaylist() {
        welActions.getElement(MENU_ADD_TRACK_TO_PLAYLIST).click();
    }

    public void removeTrackInQueue() {
        welActions.click(REMOVE_TRACK_IN_QUEUE);
    }


    public String getPlayListLikeCount() {
        return welActions.getElement(RECOMMENDED_PLAYLIST_LIKE_COUNTER).getText();
    }

    public void scrollDownToPlaylistTextField() {
        scrollDown(welActions.getElement(RECOMMENDED_PLAYLISTS_TEXT_FIELD));
    }

    public void scrollDownToNowTrendingText() {
        scrollDown(welActions.getElement(RECOMMENDED_NOW_TRENDING_TEXT_FIELD));
    }

    public void scrollDownToGenresTextField() {
        scrollDown(welActions.getElement(RECOMMENDED_GENRES_TEXT_FIELD));
    }

    public void scrollDownToArtist() {
        scrollDown(welActions.getElement(EXPLORE_PAGE_SEARCH_INPUT_ARTISTS.getValue()));
    }

    public void clickOnArtistInSearch() {
        welActions.click(EXPLORE_PAGE_SEARCH_INPUT_ARTIST.getValue());
    }

    public void scrollDownToRecommededSongs() {
        scrollDown(welActions.getElement(RECOMMENDED_SONG.getValue()));
    }

    public void moveToElementsAndClick(WebElement elementToView, WebElement elementToClick) {
        Actions action = new Actions(driverManager.getDriver());
        action.moveToElement(elementToView)
                .moveToElement(elementToClick)
                .click()
                .build()
                .perform();
    }

    public void moveToElements(WebElement firstElement, WebElement secondElement) {
        Actions action = new Actions(driverManager.getDriver());
        action.moveToElement(firstElement)
                .moveToElement(secondElement)
                .build()
                .perform();
    }

    public ExplorePage scrollDownToRecommendedSongsText() {
        scrollDown(welActions.getElement(RECOMMENDED_SONG_TEXT.getValue()));
        return this;
    }

    public void scrollDownToRecommendedSong(String value) {
        WebElement element = welActions.getElement(value);
        JavascriptExecutor executor = (JavascriptExecutor) driverManager.getDriver();
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public int countNumberOfLikes() {
        return Integer.parseInt(welActions.getElement(EXPLORE_QUEUE_NUMBER_LIKE_TRACK_BUTTON.getValue()).getText());
    }

    public boolean isDifferentNumberForOneValue(int numberOfLikesBeforeClicking, int numberOfLikesAfterClicking) {
        return Math.abs(numberOfLikesAfterClicking - numberOfLikesBeforeClicking) == 1;
    }

    public ExplorePage moveToRecommendedSong() {
        welActions.moveToElement(RECOMMENDED_SONG.getValue());
        return this;
    }

    public ExplorePage moveToRecommendedSecondSong() {
        welActions.moveToElements(RECOMMENDED_SONG.getValue());
        return this;
    }

    public void clickRecommendedSongPlayButton() {
        welActions.click(RECOMMENDED_SONG_PLAY_BUTTON);
    }

    public void clickRecommendedSongPauseButton() {
        welActions.click(RECOMMENDED_SONG_PAUSE_PANEL.getValue());
    }

    public ExplorePage clickRecommendedSongAddButton(String addButtonLocator, int numberPositionOfAddButton) {
        welActions.clickByNumber(RECOMMENDED_SONG_ADD_PANEL.getValue(), numberPositionOfAddButton);
        return this;
    }

    public ExplorePage moveToRecommendedThirdSong() {

        welActions.moveToElementByNumber(RECOMMENDED_SONG.getValue(), 3);
        return this;
    }


    public void clickRecommendedSecondSongPlayButton() {
        welActions.clicks(RECOMMENDED_SONG_PLAY_BUTTON);
    }


    public ExplorePage moveToRecommendedPlaylist() {
        welActions.moveToElement(RECOMMENDED_PLAYLIST);
        return this;
    }

    public boolean isDisplayedButtonAddRemoveLikeSong() {
        return welActions.isDisplayed(RECOMMENDED_SONG_DISLIKE_BUTTON);
    }

    public boolean isDisplayedButtonAddToPlaylistQueueSong() {
        return welActions.isDisplayed(RECOMMENDED_ADD_QUEUE_BUTTON);
    }

    public boolean isDisplayedButtonPlaySong() {
        return welActions.isDisplayed(RECOMMENDED_SONG_PLAY_BUTTON);
    }

    public boolean isDisplayedNumberLikesOnCoverSong() {
        moveToElements(recommendedSong(), recommendedSongDislikeButton());
        return welActions.isDisplayed(RECOMMENDED_SONG_LIKE_COUNTER);
    }

    public void moveToElement(String key) {
        Actions action = new Actions(driverManager.getDriver());
        WebElement element = welActions.getElement(key);
        action.moveToElement(element)
                .build()
                .perform();
    }


    public boolean isDisplayedButtonAddRemoveLikePlaylist() {
        return welActions.isDisplayed(RECOMMENDED_PLAYLIST_LIKE_BUTTON);
    }

    public boolean isDisplayedButtonPlayPlaylist() {
        return welActions.isDisplayed(RECOMMENDED_PLAYLIST_PLAY_BUTTON.getValue());
    }

    public ExplorePage moveToRecommendedPlayListLikeButton() {
        moveToElements(recommendedPlayList(), recommendedPlayListLikeButton());
        return this;
    }

    public String getColorHeart() {
        return welActions.getElement(RECOMMENDED_PLAYLIST_LIKE_SVG.getValue()).getCssValue("fill");
    }

    public String getColorHeartOutline() {
        return welActions.getElement(RECOMMENDED_PLAYLIST_LIKE_SVG.getValue()).getCssValue("stroke");
    }
    public String getColorHeart(String heart) {
        return welActions.getElement(heart).getCssValue("fill");
    }
    public String getColorHeartOutline(String heart) {
        return welActions.getElement(heart).getCssValue("stroke");
    }

    public boolean isDisplayedWhiteHeartWithBlackOutline() {
        return getColorHeart().equals("none")
                && getColorHeartOutline().equals("rgb(18, 18, 18)");
    }

    public boolean isDisplayedWhiteHeartWithRedOutline() {
        return getColorHeart().equals("none")
                && getColorHeartOutline().equals("rgb(239, 75, 65)");
    }

    public boolean isDisplayedRedHeart() {
        return getColorHeart().equals("rgb(239, 75, 65)")
                && getColorHeartOutline().equals("rgb(239, 75, 65)");
    }
    public boolean isDisplayedRedHeartInQueue() {
        return getColorHeart(EXPLORE_QUEUE_HEART_LIKE_BUTTON.getValue()).equals("rgb(239, 75, 65)")
                && getColorHeartOutline(EXPLORE_QUEUE_HEART_LIKE_BUTTON.getValue()).equals("rgb(239, 75, 65)");
    }

    public ExplorePage clickRecommendedPlayListLikeButton() {
        moveToRecommendedPlayListLikeButton()
                .click(RECOMMENDED_PLAYLIST_LIKE_BUTTON);
        return this;
    }

    public boolean isDisplayedNumberLikesOnCoverPlaylist() {
        moveToElements(recommendedPlayList(), recommendedPlayListLikeButton());
        return welActions.isDisplayed(RECOMMENDED_PLAYLIST_LIKE_COUNTER);
    }
    public boolean isDisplayedExplorePage() {
        return welActions.isDisplayed(DISPLAYED_EXPLOREPAGE);
    }

    public void clickDashboard() {
        welActions.click(DASHBOARD_BUTTON.getValue());
    }

    public boolean isDisplayedGeners() {
        return welActions.isDisplayed(ALL_GENERS);
    }

    public String getGeners() {
        WebElement element = welActions.getElement(ALL_GENERS);
        return welActions.getElementsText(element);
    }

    public void allGenersButtonClick() {
        List<String> genersList = new ArrayList<>();
        genersList.add(CLASSICAL_BUTTON);
        genersList.add(METALL_BUTTON);
        genersList.add(HARCORE_BUTTON);
        for (String generButton : genersList) {
            welActions.click(generButton);
            welActions.click(BACK_TO_EXPLORE);
            assertTrue(isDisplayedExplorePage(), "Chek gener");
        }
    }

    public void clickOnViewAllTrendingNowButton() {
        welActions.click(TRENDING_NOW_BUTTON);
    }

    public void clickOnViewNewAlbumPageButton() {
        welActions.click(NEW_ALBUM_PAGE_BUTTON.getValue());
    }

    public boolean isContainsCoversNewAlbums() {
        return welActions.isDisplayed(DISPlAY_COVER_NEW_AlBUM.getValue());
    }

    public boolean isDisplayedPanelRecommendedSong(String key) {
        return welActions.isDisplayed(key);
    }

    public void click(String key) {
        welActions.click(key);
    }

    public boolean compareFirstTrackWithAddTrackInQueue(String firstTrackInRecommended, String firstTrackInQueue) {
        String nameTrack = welActions.getElements(firstTrackInRecommended).get(0).getText();
        String nameQueueTrack = welActions.getElements(firstTrackInQueue).get(0).getText();
        return nameTrack.equals(nameQueueTrack);
    }

    public boolean isTheSameFirstTrackInQueueAndTrackInEqualizer(String trackInEqualizer, String firstTrackInQueue) {
        String trackEqualizer = welActions.getElement(trackInEqualizer).getText();
        String nameQueueTrack = welActions.getElements(firstTrackInQueue).get(0).getText();
        welActions.click(EXPLORE_PAGE_QUEUE_BUTTON.getValue());
        return trackEqualizer.equals(nameQueueTrack);
    }

    public boolean compareSecondTrackWithAddTrackInQueue(String secondTrackInRecommended, String firstTrackInQueue) {
        String nameTrack = welActions.getElements(secondTrackInRecommended).get(1).getText();
        String nameQueueTrack = welActions.getElements(firstTrackInQueue).get(0).getText();
        return nameTrack.equals(nameQueueTrack);
    }

    public boolean isTheSameListOfTracksAndAddedTracksInQueue(String thirdTrackInRecommended, String thirdTrackInQueue, int numberOfTracks) {
        List<WebElement> list = welActions.getElements(thirdTrackInRecommended);
        List<WebElement> listInQueue = welActions.getElements(thirdTrackInQueue);
        return IntStream.iterate(0, i -> i++).limit(numberOfTracks)
                .allMatch(i -> list.get(i).getText().equals(listInQueue.get((listInQueue.size() - numberOfTracks) + i).getText()));
    }

    public String getCurrentUrl() {
        return welActions.getCurrentUrl();
    }

    public void waitUntilDisappearNotification() {
        welActions.waitUntilDisappear(MODAL_NOTIFICATION.getValue());
    }

    public void clickPlayRecommendedPlaylist() {
        welActions.click(RECOMMENDED_PLAYLIST_PLAY_BUTTON.getValue());
    }

    public void clickOnLogo(){
        welActions.click(LOGO.getValue());
    }
}
