package example.ui.tests.explore;


import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;


@Slf4j

public class PlayQueueFunctionalityTest extends BaseUiTest {

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        log.info("PlayQueueFunctionalityTest");
        helperUi.openLoginPage();
    }


    @Test(description = "Adding / removing tracks to the 'Play Queue' list")
    public void verifyLikeFunctionalityForRecommendedSongs() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .login()
                .scrollDownToNowTrendingTextField()
                .playRecommendedSong()
                .clickQueueButton()
                .scrollDownToPlaylistTextField()
                .clickAddQueueRecSong()
                .clickMenuAddTrackInQueue()
                .scrollDownToGenres()
                .clickAddQueueNowTrending()
                .clickMenuAddTrackInQueue()
                .rememberTracksQueue()
                .removeTrackInQueue()
                .rememberTracksQueueAfterRemoval();
        assertNotEquals(helperUi.getTracksQueue(), helperUi.getTracksQueueAfterRemoval(), "Queues are the same");
    }
}
