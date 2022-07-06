package example.ui.tests.explore;


import example.enums.CharacterSet;
import example.identities.Playlist;
import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static example.config.TestDataProvider.*;
import static example.ui.tests.explore.LikeFunctionalityTest.LIKE_COUNT_ERROR_MESSAGE;
import static example.utils.RandomUtils.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;


public class PlaylistsTest extends BaseUiTest {
    private static final String SONG_NAME = "lkjhlk";

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.createUser(generateName(), Base.PASSWORD, Base.LOGIN_PHONE , generateEmail(), Base.COUNTRY)
                .openLoginPage()
                .login();
    }

    @Test(description = "Create a new playlist")
    public void createNewPlaylistTest() {
        Playlist playlist = Playlist.builder()
                .name(generateName())
                .description(generateDesription())
                .picture(AVATAR_FILE.getAbsoluteFile())
                .build();
        helperUi.openDashboardPage()
                .createNewPlaylist(playlist);
        assertTrue(helperUi.getPlaylistsPage().getNotificationSuccessfulCreation().isDisplayed(), "Notification 'Success' is displayed");
        assertTrue(helperUi.getPlaylistsPage().isDisplayedPlaylist(playlist.getName()), "User created a new playlist");
    }

    @Test(description = "Return to Dashboard page Playlists tab after visiting create-playlist page")
    public void returnToDashboardPageTest() {
        helperUi.openDashboardPage()
                .getDashboardPage()
                .clickPlaylistsTab()
                .clickCreateNewPlaylistButton()
                .clickBackToDashboardButton();
        assertTrue(helperUi.getDashboardPage().isDisplayedDashboard(), "User on dashboard page");
    }

    @Test(description = "Playlist playback")
    public void playingPlaylistTest() {
        helperUi.openDashboardPage()
                .clickPlaylistsTab()
                .playPlaylist()
                .clickQueueButton()
                .clickOnPlaylist();
        assertEquals(helperUi.getQueueTracks(), helperUi.getPlaylistTracks(), "Playlist tracks are not equal to tracks in queue");
    }

    @Test(description = "Add / remove a like to a playlist")
    public void likeFunctionalityPlaylistTest() {
        helperUi.checkBeforeDislike()
                .rememberLikeCountOnPlaylist()
                .dislikeRecommendedPlaylist()
                .rememberLikeCountOnPlaylistAfterDislike()
                .likeRecommendedPlaylist()
                .rememberLikeCountOnPlaylistAfterLike();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(helperUi.getLikeCount() > helperUi.getLikeCountAfterDislike(), LIKE_COUNT_ERROR_MESSAGE);
        softAssert.assertTrue(helperUi.getLikeCountAfterDislike() < helperUi.getLikeCountAfterLike(), LIKE_COUNT_ERROR_MESSAGE);
        softAssert.assertAll();
    }

    @Test(description = "Playlist editing (deleting, adding tracks)")
    public void playlistEditingTest() {
        helperUi.openDashboardPage()
                .clickOnPlaylist()
                .clickEditPlaylist()
                .inputTrackName(SONG_NAME)
                .clickAddToPlaylist()
                .clickMenuAddTrackInPlaylist()
                .clickSavePlaylist()
                .clickOnPlaylist()
                .clickEditPlaylist()
                .clickDeleteTrack()
                .clickSavePlaylist()
                .clickOnPlaylist();
        assertFalse(helperUi.getPlaylistTracks().contains(SONG_NAME));
    }

    @Test(description = "Stop playback of the playlist by the 'Play All' button")
    public void stopPlayingPlaylistButtonPlayAll() {
        helperUi.openPlaylistsPage()
                .getPlaylistsPage()
                .clickFirstPlaylist()
                .clickPlayAllButton();
        assertTrue(helperUi.getPlayer().isDisplayedPlayer(), "Player in not displayed");
        assertTrue(helperUi.getPlaylistsPage().isDisplayedPlayAllIconPause(), "Icon  pause in not displayed");
        helperUi.getPlaylistsPage()
                .clickPlayAllButton();
        assertTrue(helperUi.getPlaylistsPage().isDisplayedPlayAllIconPlay(), "Icon  play in not displayed");
    }

    @Test(description = "Editing MyPlaylists with invalid data")
    public void editingMyPlaylistsWithInvalidData() {
        helperUi.openPlaylistsPage()
                .getPlaylistsPage()
                .clickMyPlaylistsTab()
                .clickFirstPlaylist()
                .clickEditPlaylist();
        helperUi.changeNameAndDescriptionPlaylist("", "");
        assertTrue(helperUi.isDisplayedMessageInvalidDataName("Required"));
        assertTrue(helperUi.isDisplayedMessageInvalidDataDescription("Required"));
        helperUi.changeNameAndDescriptionPlaylist(generateVerifyCodeWithLetter(), generateVerifyCodeWithLetter());
        assertTrue(helperUi.isDisplayedMessageInvalidDataName("Must be 2 characters or more"));
        assertTrue(helperUi.isDisplayedMessageInvalidDataDescription("Must be 2 characters or more"));
        helperUi.changeNameAndDescriptionPlaylist(generateNameSixtyOne(), generateDescriptionTwoHundredFiftySix());
        assertTrue(helperUi.isDisplayedMessageInvalidDataName("Must be 60 characters or less"));
        assertTrue(helperUi.isDisplayedMessageInvalidDataDescription("Must be 255 characters or less"));
        helperUi.changeNameAndDescriptionPlaylist(generateName("."), generateName("."));
        assertTrue(helperUi.isDisplayedMessageInvalidDataName("Can contain letters, numbers," +
                " !@#$%^&*()_-=+;:'\"?,<>[]{}|/№!~' symbols, and one dot not first or last"));
        assertTrue(helperUi.isDisplayedMessageInvalidDataDescription("Can contain letters, numbers," +
                " !@#$%^&*()_-=+;:'\"?,<>[]{}|/№!~' symbols, and one dot not first or last"));
        helperUi.changeNameAndDescriptionPlaylist(generateNameWithSuffixes("."), generateNameWithSuffixes("."));
        assertTrue(helperUi.isDisplayedMessageInvalidDataName("Can contain letters, numbers," +
                " !@#$%^&*()_-=+;:'\"?,<>[]{}|/№!~' symbols, and one dot not first or last"));
        assertTrue(helperUi.isDisplayedMessageInvalidDataDescription("Can contain letters, numbers," +
                " !@#$%^&*()_-=+;:'\"?,<>[]{}|/№!~' symbols, and one dot not first or last"));
        helperUi.changeNameAndDescriptionPlaylist(generateNameWithSuffixes(".") + generateName(".")
                , generateNameWithSuffixes(".") + generateName("."));
        assertTrue(helperUi.isDisplayedMessageInvalidDataName("Can contain letters, numbers," +
                " !@#$%^&*()_-=+;:'\"?,<>[]{}|/№!~' symbols, and one dot not first or last"));
        assertTrue(helperUi.isDisplayedMessageInvalidDataDescription("Can contain letters, numbers," +
                " !@#$%^&*()_-=+;:'\"?,<>[]{}|/№!~' symbols, and one dot not first or last"));
        helperUi.openProfile()
                .signOut();
    }

    @Test(description = "Creation of MyPlaylists")
    public void createNewMyPlaylist() {
        Playlist playlist = Playlist.builder()
                .name(generateName())
                .description(generateDesription())
                .picture(AVATAR_FILE.getAbsoluteFile())
                .build();
        helperUi.openDashboardPage()
                .createNewPlaylistWithTrack(playlist);
        assertTrue(helperUi.getPlaylistsPage().getNotificationSuccessfulCreation().isDisplayed());
        helperUi.openPlaylistsPage()
                .getPlaylistsPage()
                .clickMyPlaylistsTab();
        assertTrue(helperUi.getPlaylistsPage().isDisplayedPlaylist(playlist.getName()));
        helperUi.removeCreatedPlaylist()
                .getExplorePage()
                .waitUntilDisappearNotification();
        helperUi.signOut();

    }

    @Test(description = "Validation for the format of the loaded playlist image")
    public void editingMyPlaylistsWithInvalidImage() {
        helperUi.openPlaylistsPage()
                .getPlaylistsPage()
                .clickMyPlaylistsTab()
                .clickFirstPlaylist()
                .clickEditPlaylist();
        helperUi.changeImagePlaylist(Tiff);
        assertTrue(helperUi.isDisplayedMessageInvalidDataImage("Available formats are: JPEG, PNG"));
        helperUi.changeImagePlaylist(CAT_GIF);
        assertTrue(helperUi.isDisplayedMessageInvalidDataImage("Available formats are: JPEG, PNG"));
        helperUi.signOut();
    }


    @Test(description = "Viewing \"Public Playlist\" Information.")
    public void ViewPublicPlaylistInformation() {
        helperUi.openPlaylistsPage()
                .getPlaylistsPage()
                .clickMyPlaylistsTab();
        List<String> name = helperUi.getPlaylistsPage().getNamesPlaylists();
        helperUi.openPlaylistsPage()
                .getPlaylistsPage()
                .clickPublicMyPlaylist(name);
        assertTrue(helperUi.isViewPublicPlaylistInformation(true));
        helperUi.openPlaylistsPage()
                .getPlaylistsPage()
                .clickPublicNotMyPlaylist(name);
        assertTrue(helperUi.isViewPublicPlaylistInformation(false));
        helperUi.signOut();
    }

    @Test(description = "Transferring \"My Playlists\" to \"Public Playlist\".")
    public void transferMyPlaylistsToPublicPlaylist() {
        helperUi.openPlaylistsPage()
                .openMyPlaylistNotPublic();
        String namePlaylist = helperUi.getPlaylistsPage().getPlaylistsName();
        assertTrue(helperUi.isViewPublicPlaylistInformation(true));
        helperUi.getPlaylistsPage()
                .clickEditPlaylist();
        assertTrue(helperUi.isViewEditPlaylistInformation());
        helperUi.getPlaylistsPage()
                .clickSharePlaylistButton();
        assertTrue(helperUi.getPlaylistsPage().isDisplayedSharePlaylistButton("Shared"));
        helperUi.getPlaylistsPage()
                .clickSavePlaylist();
        helperUi.openPlaylistsPage();
        assertTrue(helperUi.getPlaylistsPage().isDisplayedMyPlaylistInPublicPlaylistsTab(namePlaylist));
        helperUi.clickSharePlaylist(namePlaylist);
        helperUi.getExplorePage()
                .waitUntilDisappearNotification();
        helperUi.signOut();
    }

    @Test(description = "Editing MyPlaylists with valid data")
    public void editingMyPlaylistsWithValidData(){
        helperUi.openPlaylistsPage()
                .editPlaylist();
        helperUi.changeNameAndDescriptionPlaylist(generateNameSixtyRu(), generateDescriptionTwoHundredFiftyFiveRu());
        assertTrue(helperUi.getPlaylistsPage().getNotificationPlaylistChangesSaved().isDisplayed(), "Failed");
        helperUi.clickPopUmMessage();
        helperUi.editPlaylist();
        helperUi.changeNameAndDescriptionPlaylist(generateNameSixtyEng(), generateDescriptionTwoHundredFiftyFiveEng());
        assertTrue(helperUi.getPlaylistsPage().getNotificationPlaylistChangesSaved().isDisplayed(), "Failed");
        helperUi.clickPopUmMessage();
        helperUi.editPlaylist();
        helperUi.changeNameAndDescriptionPlaylist(generateVerifyCodeWithLettersEng(), generateVerifyCodeWithLettersEng());
        assertTrue(helperUi.getPlaylistsPage().getNotificationPlaylistChangesSaved().isDisplayed(), "Failed");
        helperUi.clickPopUmMessage();
        helperUi.editPlaylist();
        helperUi.changeNameAndDescriptionPlaylist(generateVerifyCodeWithLettersRu(), generateVerifyCodeWithLettersRu());
        assertTrue(helperUi.getPlaylistsPage().getNotificationPlaylistChangesSaved().isDisplayed(), "Failed");
        helperUi.clickPopUmMessage();
        helperUi.editPlaylist();
        helperUi.changeNameAndDescriptionPlaylist(generateNumeric(), generateNumeric());
        assertTrue(helperUi.getPlaylistsPage().getNotificationPlaylistChangesSaved().isDisplayed(), "Failed");
        helperUi.clickPopUmMessage();
        helperUi.editPlaylist();
        helperUi.changeNameAndDescriptionPlaylist(CharacterSet.SPECIAL_CHARS.getCharacters(), CharacterSet.SPECIAL_CHARS.getCharacters());
        assertTrue(helperUi.getPlaylistsPage().getNotificationPlaylistChangesSaved().isDisplayed(), "Failed");
        helperUi.clickPopUmMessage();
        helperUi.editPlaylist();
        helperUi.changeNameAndDescriptionPlaylist(generateNameWithSuffixes(".") + generateName(), generateNameWithSuffixes(".") + generateName());
        assertTrue(helperUi.getPlaylistsPage().getNotificationPlaylistChangesSaved().isDisplayed(), "Failed");
        helperUi.clickPopUmMessage();
        helperUi.editPlaylist();
        helperUi.changeNameAndDescriptionPlaylist(generateNameWithSuffixes(" ") + generateName(), generateNameWithSuffixes(" ") + generateName());
        assertTrue(helperUi.getPlaylistsPage().getNotificationPlaylistChangesSaved().isDisplayed(), "Failed");
        helperUi.clickPopUmMessage();
        helperUi.signOut();
    }
}
