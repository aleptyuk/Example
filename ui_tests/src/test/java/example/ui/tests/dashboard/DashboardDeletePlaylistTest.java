package example.ui.tests.dashboard;


import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static example.ui.enums.NotificationMessage.REMOVE_MY_PLAYLIST;
import static org.testng.Assert.assertEquals;


public class DashboardDeletePlaylistTest extends BaseUiTest {

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.signOut();
    }

    @Test(description = "Deleting a playlist using Edit Playlist")
    public void deletingPlaylistUsingEditPlaylist() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .login()
                .clickDashboard();
        int quantityBeforeCreation = helperUi.getCountOfPlaylist();
        helperUi.createNewPlaylist()
                .editDashboardPlaylist()
                .clickButtonNoOnModalWindow()
                .clickButtonYesOnModalWindow();
        assertEquals(helperUi.getTextAlertMessage(), REMOVE_MY_PLAYLIST.getValue());
        int quantityAfterCreation = helperUi.getCountOfPlaylist();
        assertEquals(quantityBeforeCreation, quantityAfterCreation);
        helperUi.waitUntilDisappearConfirmMessage();
    }
}
