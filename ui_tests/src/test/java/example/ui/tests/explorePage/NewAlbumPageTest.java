package example.ui.tests.explorePage;


import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;



public class NewAlbumPageTest extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        helperUi.signOut();
    }

    @Test(description = "Go to the New albums page and back to Explore")
    public void newAlbumPageButton() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login();
        helperUi.clickOnViewNewAlbumPageButton();
        assertTrue(helperUi.getNewAlbumPage().isDisplayedNewAlbumPage(), "NewAlbum page is not opened");

        helperUi.clickFromViewNewAlbumPageToExplorePage();
        assertTrue(helperUi.getExplorePage().isDisplayedExplorePage(), "Explore page is not opened");
    }


    @Test(description = "Playing tracks from New albums")
    public void playingTrackFromNewAlbums() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login();
        assertTrue(helperUi.getExplorePage().isContainsCoversNewAlbums(), "The block doesn't contain covers of new albums");
        helperUi.clickOnViewNewAlbumPageButton();
        assertTrue(helperUi.getNewAlbumPage().isDisplayedNewAlbumPage(), "NewAlbum page is not opened");
        helperUi.getNewAlbumPage().clickOn();
        assertTrue(helperUi.getNewAlbumPage().isDisplayListTracks(),"Tracks list is absent");
        helperUi.playTrackFromAlbum();
        assertTrue(helperUi.getNewAlbumPage().isDisplayEqualizer(), "Equalizer is not appeared");
    }


}