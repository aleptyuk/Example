package example.ui.tests.explore;


import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static example.ui.enums.Locators.*;


public class RecommendedSongsTest extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterMethod() {
        helperUi.signOut();
    }

    @Test(description = "Display of the current possible action on the track")

    public void playRecommendedSongs() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login()
                .scrollDownToRecommendedSongs()
                .moveToElementSong();
        Assert.assertTrue(helperUi.getExplorePage().isDisplayedPanelRecommendedSong(RECOMMENDED_SONG_LIKES_PANEL.getValue()), "Likes panel is missing");
        Assert.assertTrue(helperUi.getExplorePage().isDisplayedPanelRecommendedSong(RECOMMENDED_SONG_ADD_PANEL.getValue()), "Add panel is missing");
        Assert.assertTrue(helperUi.getExplorePage().isDisplayedPanelRecommendedSong(RECOMMENDED_SONG_PLAY_PANEL.getValue()), "Play panel is missing");
        helperUi.playRecommendedFirstSong();
        Assert.assertTrue(helperUi.getExplorePage().isDisplayedPanelRecommendedSong(RECOMMENDED_SONG_PAUSE_PANEL.getValue()), "Pause panel is missing");
        Assert.assertTrue(helperUi.getExplorePage().isDisplayedPanelRecommendedSong(EXPLORE_PAGE_EQUALIZER.getValue()), "Equalizer is missing");
        helperUi.moveToAndPlaySecondSong();
        Assert.assertTrue(helperUi.getExplorePage().isDisplayedPanelRecommendedSong(RECOMMENDED_SONG_PAUSE_PANEL.getValue()), "Pause panel is missing");
        helperUi.moveToElementSong();
        Assert.assertTrue(helperUi.getExplorePage().isDisplayedPanelRecommendedSong(RECOMMENDED_SONG_PLAY_PANEL.getValue()), "Play panel is missing");
    }
}