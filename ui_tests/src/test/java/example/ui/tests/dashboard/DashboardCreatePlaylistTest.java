package example.ui.tests.dashboard;



import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.testng.Assert.assertTrue;


public class DashboardCreatePlaylistTest extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.signOut();
    }

    @Test(description = " Sorting recently uploaded tracks in the \"Recently uploaded\" tab")
    public void createTracksAndCheckPositionOnList() {
        helperUi.createUser(Base.NEW_PASSWORD, Base.NEW_LOGIN_PHONE, Base.NEW_COUNTRY)
                .login()
                .clickDashboard()
                .clickUploadMusic()
                .clickUploadNewTrack()
                .uploadNewTrack()
                .createTrackAndSave()
                .clickUploadMusic()
                .clickUploadNewTrack()
                .uploadNewTrack()
                .createTrackAndSave();
        assertTrue(helperUi.checkSortingTrack(), "Track added, doesn't displayed first in the Recently uploaded list");
        helperUi.deleteTracks();
    }


    @Test(description = "Remove playlist from Dashboard page Playlists tab")
    public void createPlaylistCheckAndDelete() {
        helperUi.createUser(Base.NEW_PASSWORD, Base.NEW_LOGIN_PHONE, Base.NEW_COUNTRY)
                .login()
                .clickDashboard()
                .clickUploadMusic()
                .clickUploadNewTrack()
                .uploadNewTrack()
                .createTrackAndSave()
                .clickDashboardPlaylist()
                .createNewPlayListWithoutTracks()
                .createNewPlayList();
        assertTrue(helperUi.checkIsVisibleTrackListWithSong());
        helperUi.moveToPlaylistFirst()
                .deleteAndConfirmDeleting();
        assertTrue(helperUi
                .getPlaylistsPage()
                .getNotificationRemoveMyPlaylist()
                .isDisplayed(), "Playlist was removed");
        helperUi.clickPopUmMessage();
        assertTrue(helperUi.checkIsVisibleTrackListWithoutSong());
        helperUi.moveToPlaylistFirst()
                .deleteAndConfirmDeleting();
        assertTrue(helperUi
                .getPlaylistsPage()
                .getNotificationRemoveMyPlaylist()
                .isDisplayed(), "Playlist was removed");
        helperUi.clickPopUmMessage();
    }
}