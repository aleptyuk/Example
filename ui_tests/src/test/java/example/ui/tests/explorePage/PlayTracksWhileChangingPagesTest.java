package example.ui.tests.explorePage;

import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.testng.Assert.assertTrue;




public class PlayTracksWhileChangingPagesTest extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.signOut();
    }


    @Test(description = "Play tracks when changing pages and tracks does not stop playback")
    public void playTracksWhileChangingPages() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login();
        helperUi.playRecommendedSong();
        helperUi.openFavoritesPage();
        assertTrue(helperUi.getFavoritesPage().isDisplayedFavoritesPage(), "Favorites page is not opened");
        assertTrue(helperUi.getPlayer().isDisplayedPlayer(), "Player in not displayed");
        helperUi.openMyTracklistPage();
        assertTrue(helperUi.getMyTracklistPage().isDisplayedMyTracklistPage(), "My tracklists page is not opened");
        assertTrue(helperUi.getPlayer().isDisplayedPlayer(), "Player in not displayed");
        helperUi.openPlaylistsPage();
        assertTrue(helperUi.getPlaylistsPage().isDisplayedPlaylistPage(), "Playlist page is not opened");
        assertTrue(helperUi.getPlayer().isDisplayedPlayer(), "Player in not displayed");
        helperUi.openDashboardPage();
        assertTrue(helperUi.getDashboardPage().isDisplayedDashboard(), "Dashboard page is not opened");
        assertTrue(helperUi.getPlayer().isDisplayedPlayer(), "Player in not displayed");
    }
}
