package example.ui.tests.dashboard;

import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;



public class DashboardPlayPlaylistTest extends BaseUiTest {

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.signOut();
    }

    @Test(description = "Whether the number of tracks in the queue matches the number of tracks in the playlist.")
    public void checkIfNumberOfTracksInPlaylistMatches() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .login()
                .clickDashboard()
                .moveToPlaylistAndClickPlayButton()
                .playlistQueueButton();
        assertEquals(helperUi.getCountTracksInPlaylist(), helperUi.getCountOfTracksInPlayQueue());
    }
    @Test(description = "Displaying tracks in the \"Recently uploaded\" block", priority = 2)
    public void DisplayingTracksInRecentlyUploadedBlock() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .login()
                .clickDashboard()
                .moveToUploadMusicTab();
           String textTitle = helperUi.getCreatedTrackTitle();
        assertEquals(helperUi.getTitleTrack(), textTitle);
        assertTrue(helperUi.getCountUploadTrack() <= 20);
        helperUi.waitUntilDisappearConfirmMessageUpload();
    }
}
