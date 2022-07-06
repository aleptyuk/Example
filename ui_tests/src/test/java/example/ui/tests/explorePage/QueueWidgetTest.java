package example.ui.tests.explorePage;


import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class QueueWidgetTest extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.signOut();
    }

    @Test(description = "Adding a track to the queue")
    public void addingTrackToQueue() {
        helperUi.createUser(Base.NEW_PASSWORD, Base.NEW_LOGIN_PHONE, Base.NEW_COUNTRY)
                .login()
                .clickPlayRecommendedSong()
                .openQueueList();
        assertTrue(helperUi.compareFirstTRackWithAddTrackInQueue(), "Track doesn't add to queue ");
        helperUi.clickPlayRecommendedSecondSong();
        assertTrue(helperUi.compareSecondTRackWithAddTrackInQueue(), "Track doesn't add to queue ");
    }

    @Test(description = "Removing a track from the list in the queue for playing")
    public void removingTrackFromQueue() {
        helperUi.createUser(Base.NEW_PASSWORD, Base.NEW_LOGIN_PHONE, Base.NEW_COUNTRY)
                .login()
                .addThreeSongsFromRecommendedSongToQueue()
                .clickPlayAndPauseRecommendedSong()
                .openQueueList();
        assertTrue(helperUi.isTheSameListOfThreeTracksAndAddedThreeTracksInQueue(), "Tracks didn't add to the queue");
        helperUi.clickDeleteFirstTrackFromTheQueue();
        assertTrue(helperUi.isTheSameFirstTrackInQueueAndTrackInEqualizer(), "Track in equalizer is not the same like in the queue");
    }

    @Test(description = "Adding a like in the expanded form of the player")
    public void addingLikeTrackInQueue() {
        helperUi.createUser(Base.NEW_PASSWORD, Base.NEW_LOGIN_PHONE, Base.NEW_COUNTRY)
                .login()
                .clickPlayRecommendedSong()
                .getExplorePage().clickRecommendedSongPauseButton();
        helperUi.openQueueList();
        int numberOfLikesBeforeClicking = helperUi.getExplorePage().countNumberOfLikes();
        assertTrue(helperUi.getExplorePage().isDisplayedRedHeartInQueue());
        helperUi.clickLikeFirstTrackTheQueue();
        int numberOfLikesAfterClicking = helperUi.getExplorePage().countNumberOfLikes();
        assertTrue(helperUi.getExplorePage().isDifferentNumberForOneValue(numberOfLikesBeforeClicking, numberOfLikesAfterClicking),"Number of likes changed not for one value");
        helperUi.clickLikeFirstTrackTheQueue();
        numberOfLikesBeforeClicking = helperUi.getExplorePage().countNumberOfLikes();
        assertTrue(helperUi.getExplorePage().isDifferentNumberForOneValue(numberOfLikesBeforeClicking, numberOfLikesAfterClicking),"Number of likes changed not for one value");
    }
}
