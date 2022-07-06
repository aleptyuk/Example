package example.ui.tests.dashboard;

import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;



public class PlaylistEditingTest extends BaseUiTest {

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.signOut();
    }

    @Test(description = "Editing the name and description of the playlist", priority = 1)
    public void editingNameAndDescriptionOfPlaylistTest() {
        helperUi.openDashboardPage()
                .createNewTestPlaylist()
                .editTestPlaylist()
                .checkEditedPlaylist();
        assertTrue(helperUi.getPlaylistsPage().isEditedPlaylist(), "Name and description of Playlist not edited");
    }

    @Test(description = "Deleting a playlist using Edit Playlist", priority = 2)
    public void deletingPlaylistFromEditPlaylistPage(){
        helperUi.deleteEditedPlaylist();
    }
}
