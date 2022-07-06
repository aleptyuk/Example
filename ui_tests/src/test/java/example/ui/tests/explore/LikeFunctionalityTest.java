package example.ui.tests.explore;

import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


@Slf4j
public class LikeFunctionalityTest extends BaseUiTest {

    public static final String LIKE_COUNT_ERROR_MESSAGE = "Like count is not as expected";

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        log.info("LikeFunctionalityTest");
        helperUi.openLoginPage();
    }


    @Test(description = "Displaying the panel for adding / removing likes, adding to the playlist / queue," +
                        " playing after hovering over the cover of the track")
    public void isDisplayedPanelAndNumberLikesRecommendedSong() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login();
        helperUi.getExplorePage()
                .scrollDownToRecommendedSongsText()
                .moveToRecommendedSong();
        assertTrue(helperUi.isDisplayedPanelOnCoverSong());
        assertTrue(helperUi.getExplorePage().isDisplayedNumberLikesOnCoverSong());
    }

    @Test(description = "Displaying the panel for adding / removing a like, " +
                                "playing when you hover over the cover of the playlist")
    public void isDisplayedPanelAndNumberLikesRecommendedPlaylist() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login();
        helperUi.getExplorePage()
                .moveToRecommendedPlaylist();
        assertTrue(helperUi.isDisplayedPanelOnCoverPlaylist());
        assertTrue(helperUi.getExplorePage().isDisplayedNumberLikesOnCoverPlaylist());
    }

    @Test(description = "Adding a Like to Playlists")
    public void AddingLikeToPlaylists() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login()
                .openPlaylistsPage();
        assertTrue(helperUi.getPlaylistsPage().isDisplayedAddRemoveLikePlaylist());
        int numberLikePlaylist = helperUi.getPlaylistsPage().getNumberLikePlaylist();
        helperUi.getPlaylistsPage()
                .clickButtonLikePlaylist();
        assertTrue(helperUi.getPlaylistsPage().getNumberLikePlaylist() == numberLikePlaylist + 1
                || helperUi.getPlaylistsPage().getNumberLikePlaylist() == numberLikePlaylist -1);
        helperUi.getPlaylistsPage()
                .clickButtonLikePlaylist();
        helperUi.signOut();
    }

    @Test(description = "Displaying likes")
    public void isDisplayedLikesDifferentColors() {
        helperUi.signUpViaPhoneBelarus();
        helperUi.getExplorePage()
                .moveToRecommendedPlaylist();
        assertTrue(helperUi.isDisplayedPanelOnCoverPlaylist());
        assertTrue(helperUi.getExplorePage().isDisplayedWhiteHeartWithBlackOutline());

        helperUi.getExplorePage()
                .moveToRecommendedPlayListLikeButton();
        assertTrue(helperUi.getExplorePage().isDisplayedWhiteHeartWithRedOutline());

        helperUi.getExplorePage()
                .clickRecommendedPlayListLikeButton();
        assertTrue(helperUi.getExplorePage().isDisplayedRedHeart());

        helperUi.openProfile()
                .deleteUser();
    }
}
