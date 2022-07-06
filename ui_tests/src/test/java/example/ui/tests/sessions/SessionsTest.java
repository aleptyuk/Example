package example.ui.tests.sessions;

import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Slf4j

public class SessionsTest extends BaseUiTest {

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.signOut();
    }

    @Test(description = "Play only one track at a time in different tabs")
    public void playSongInOnlyOneTab() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .loginWithRememberMe()
                .playRecommendedPlaylist();
        assertTrue(helperUi.getPlayer().isDisplayedIconPause(), "The track is not playing");

        helperUi.openNewTab(helperUi.getUrl())
                .playRecommendedPlaylist();
        assertTrue(helperUi.getPlayer().isDisplayedIconPause(), "The track is not playing");

        helperUi.goPreviousTab();
        assertTrue(helperUi.getPlayer().isDisplayedIconPlay(), "The track is playing");
    }

    @Test(description = "Saving the session when navigating to the current address in a new tab")
    public void checkSessionWhenSwitchToNewTab() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .loginWithRememberMe()
                .openPlaylistsPage();
        assertTrue(helperUi.getPlaylistsPage().isDisplayedPlaylistPage(), "Playlists tab is not open");
        String url = helperUi.getUrl();
        helperUi.openNewTab(url);
        assertEquals(helperUi.getUrl(),url);
        assertTrue(helperUi.getPlaylistsPage().isDisplayedPlaylistPage(), "Playlists tab is not open");
    }
}
