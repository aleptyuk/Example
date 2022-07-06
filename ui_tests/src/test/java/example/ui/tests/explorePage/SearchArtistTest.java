package example.ui.tests.explorePage;


import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.testng.Assert.assertTrue;


public class SearchArtistTest extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.signOut();
    }

    @Test(description = "Search for the artist")
    public void searchArtist() {
        helperUi.createUser(Base.NEW_PASSWORD, Base.NEW_LOGIN_PHONE, Base.NEW_COUNTRY)
                .login()
                .clickDashboard()
                .clickUploadMusic()
                .clickUploadNewTrack()
                .uploadNewTrack()
                .createTrackAndSave()
                .getArtistNameAndGoExplorerPage()
                .searchTrackIsInDatabase();
        assertTrue(helperUi.checkPageArtist(), "Not on the artist page");
        helperUi.clickOnBackButton()
                .searchTrackNoInDatabase();
        assertTrue(helperUi.checkSearchMessage(), "Message: Nothing found is missing");
    }


}
