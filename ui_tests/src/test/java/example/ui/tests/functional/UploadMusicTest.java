package example.ui.tests.functional;


import example.identities.UploadMusic;
import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static example.config.TestDataProvider.AVATAR_FILE;
import static example.utils.RandomUtils.generateName;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;


@Slf4j

public class UploadMusicTest extends BaseUiTest {

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

     @Test(description = "Adding an invalid track size", priority = 1)
    public void uploadTrackInvalidSizeTest() {
        helperUi.openDashboardPage();
        helperUi.uploadInvalidTrack();
        assertTrue(helperUi.getUploadMusicPage().isDisplayedErrorAboutInvalidDTrackSize(), "There is no error about track size");
        helperUi.cancelUploadTrack();
        helperUi.openDashboardPage();
    }

     @Test(description = "Loading a track in a valid format (mp3)", priority = 2)
    public void uploadTrackInValidFormatTest() {
        helperUi.openDashboardPage();
        helperUi.uploadTrack();
        assertTrue(helperUi.getUploadMusicPage().isDisplayedUploadMusicPage(), "Track has not been uploaded");
    }

    @Test(description = "Validating fields when adding a track", priority = 3)
    public void validatingFieldsWhenAddingATrackTest() {
        UploadMusic uploadMusicWithInvalidData = UploadMusic.builder()
                .author("Кадышева")
                .title(".234")
                .album("1")
                .genre("ROCK")
                .cover(AVATAR_FILE.getAbsoluteFile())
                .build();
        helperUi.createTrack(uploadMusicWithInvalidData);
        assertTrue(helperUi.getUploadNewTrack().isDisplayedErrorAboutInvalidData(), "There is no error about invalid data");
        UploadMusic uploadMusicWithValidData = UploadMusic.builder()
                .author("Рубен")
                .title(generateName())
                .album(generateName())
                .genre("ROCK")
                .cover(AVATAR_FILE.getAbsoluteFile())
                .build();
        helperUi.createTrack(uploadMusicWithValidData);
        assertTrue(helperUi.getUploadMusicPage().getNotificationTrackUploaded().waitUntilDisappear(), "No track was uploaded");
    }

     @Test(description = "Removing a track from the Recently Upload list", priority = 4)
     public void deleteTrackFromRecentlyUploadedTest() {
        helperUi.openDashboardPage()
                .openUploadMusicTab()
                .scrollDownUploadedTracks()
                .rememberCountOfUploadedTracks()
                .deleteLastTrack();
        helperUi.reloadPage();
        helperUi.scrollDownUploadedTracks();
        helperUi.rememberCountOfUploadedTracksAfterDeleting();
        assertNotEquals(helperUi.getTrackCount(), helperUi.getTrackCountAfterDeleting(),
                "Count of tracks is the same, track hasn't been deleted");
    }
}

