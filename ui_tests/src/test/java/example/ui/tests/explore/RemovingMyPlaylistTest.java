package example.ui.tests.explore;


import example.ui.tests.BaseUiTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;



public class RemovingMyPlaylistTest extends BaseUiTest {

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.signUpViaPhoneBelarus();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.getExplorePage()
                .waitUntilDisappearNotification();
        helperUi.openProfile()
                .deleteUser();
    }

    @Test(description = "Removing MyPlaylists")
    public void removingMyPlaylistTest() {
        helperUi.openDashboardPage();
        helperUi.createNewPlaylist();
        helperUi.openPlaylistsPage();
        helperUi.removeCreatedPlaylist();
        assertTrue(helperUi
                .getPlaylistsPage()
                .getNotificationRemoveMyPlaylist()
                .isDisplayed(), "Playlist was removed");
    }

}
