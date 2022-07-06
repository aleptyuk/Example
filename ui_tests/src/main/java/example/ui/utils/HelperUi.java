package example.ui.utils;

import example.identities.Country;
import example.identities.Playlist;
import example.identities.UploadMusic;
import example.identities.User;
import example.ui.pages.*;
import example.ui.pages.elements.*;
import example.utils.WaitUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.List;

import static example.config.TestConfigProvider.TARGET_URL;
import static example.config.TestDataProvider.AVATAR_FILE;
import static example.ui.enums.Locators.*;
import static example.utils.RandomUtils.*;

@Slf4j
@Getter
public class HelperUi {
    private final BasePage basePage;
    private final LoginPage loginPage;
    private final LoginBox loginBox;
    private final SignUpBox signUpBox;
    private final ExplorePage explorePage;
    private final ProfilePage profilePage;
    private final AccountDropdown accountDropdown;
    private final MainMenu mainMenu;
    private final DashboardPage dashboardPage;
    private final CreateNewPlaylistPage createNewPlaylistPage;
    private final PlaylistsPage playlistsPage;
    private final UserBox userBox;
    private final UploadMusicPage uploadMusicPage;
    private final UploadNewTrack uploadNewTrack;
    private final ConfirmExitBox confirmExitBox;
    private final Genres generPage;
    private final PopularArtistsPage popularArtistsPage;
    private final TrendingNowPage trendingNowPage;
    private final NewAlbumPage newAlbumPage;
    private final Player player;
    private final MyTrackListPage myTracklistPage;
    private final FavoritesPage favoritesPage;
    private final PasswordChangeBox passwordChangeBox;
    private final ChangePasswordBox changePasswordBox;
    private final ArtistPage artistPage;

    private User user;
    private UploadMusic uploadMusic;

    public HelperUi(WebDriver driver) {
        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        loginBox = new LoginBox(driver);
        signUpBox = new SignUpBox(driver);
        explorePage = new ExplorePage(driver);
        profilePage = new ProfilePage(driver);
        accountDropdown = new AccountDropdown(driver);
        popularArtistsPage = new PopularArtistsPage(driver);
        mainMenu = new MainMenu(driver);
        dashboardPage = new DashboardPage(driver);
        createNewPlaylistPage = new CreateNewPlaylistPage(driver);
        playlistsPage = new PlaylistsPage(driver);
        userBox = new UserBox(driver);
        uploadMusicPage = new UploadMusicPage(driver);
        uploadNewTrack = new UploadNewTrack(driver);
        confirmExitBox = new ConfirmExitBox(driver);
        generPage = new Genres(driver);
        player = new Player(driver);
        myTracklistPage = new MyTrackListPage(driver);
        favoritesPage = new FavoritesPage(driver);
        trendingNowPage = new TrendingNowPage(driver);
        newAlbumPage = new NewAlbumPage(driver);
        passwordChangeBox = new PasswordChangeBox(driver);
        changePasswordBox = new ChangePasswordBox(driver);
        artistPage = new ArtistPage(driver);
    }


    public HelperUi openLoginPage() {
        loginPage.openPage(TARGET_URL);
        return this;
    }

    public HelperUi openNewTab(String url) {
        basePage.openNewTab();
        basePage.goLastTab();
        basePage.openPage(url);
        return this;
    }

    public HelperUi goPreviousTab() {
        basePage.goPreviousTab();
        return this;
    }

    public HelperUi openDashboardPage() {
        mainMenu.clickDashboardButton();
        return this;
    }

    public HelperUi openUploadMusicTab() {
        dashboardPage.clickUploadMusicTab();
        return this;
    }

    public HelperUi scrollDownUploadedTracks() {
        dashboardPage.scrollDownAllUploadedTracks();
        return this;
    }

    public HelperUi deleteLastTrack() {
        dashboardPage.deleteLastUploadedTrack();
        return this;
    }

    public HelperUi openPlaylistsPage() {
        mainMenu.clickPlaylistsButton();
        return this;
    }

    public HelperUi editPlaylist() {
        getPlaylistsPage()
                .clickMyPlaylistsTab()
                .clickFirstPlaylist()
                .clickEditPlaylist();
        return this;
    }

    public HelperUi signOut() {
        getUserBox().clickMenuButton()
                .waitUntilExtendsOut()
                .clickSignOutButton()
                .waitUntilDisappear();
        getConfirmExitBox()
                .waitUntilAppear()
                .clickYesButton();
        return this;
    }

    public HelperUi openProfile() {
        explorePage.waitUntilAppearUsernameButton();
        explorePage.clickUserNameButton();
        explorePage.getAccountDropdown().waitUntilAppearProfileButton();
        explorePage.getAccountDropdown().clickProfileButton();
        return this;
    }

    public HelperUi clickPopUmMessage() {
        explorePage.clickPopUmMessage();
        return this;
    }


    public HelperUi deleteProfile() {
        profilePage.clickDeleteButton();
        profilePage.clickConfirmDelete();
        return this;
    }

    public HelperUi reloadPage() {
        loginPage.reloadPage();
        return this;
    }


    public HelperUi login() {
        getLoginPage()
                .clickLoginButton()
                .waitUntilAppear()
                .clickSelectCountryButton()
                .clickInputCountryField()
                .inputCountry(user.getCountry().getCountryName())
                .clickOnCountryName()
                .inputPhone(user.getPhone())
                .inputPassword(user.getPass())
                .clickLoginButton();
        return this;
    }

    public HelperUi clickDashboard() {
        explorePage.clickDashboard();
        return this;
    }

    public HelperUi clickUploadMusic() {
        dashboardPage.clickUploadMusicTab();
        return this;
    }

    public HelperUi clickDashboardPlaylist() {
        dashboardPage.clickDashboardPlaylist();
        return this;
    }

    public HelperUi createNewPlayListWithoutTracks() {
        dashboardPage.clickCreateNewPlaylist()
                .inputPlayListInformation(generateName(), generateName())
                .clickSavePlayList();
        explorePage.clickPopUmMessage();
        return this;
    }

    public HelperUi createNewPlayList() {
        dashboardPage.clickCreateNewPlaylist()
                .inputPlayListInformation(generateName(), generateName())
                .addTrackToPlayList(uploadMusic);
        dashboardPage.clickSavePlayList();
        explorePage.clickPopUmMessage();
        return this;
    }

    public HelperUi moveToPlaylistFirst() {
        dashboardPage.moveToPlaylistFirst();
        return this;
    }

    public boolean checkIsVisibleTrackListWithSong() {
        dashboardPage.moveToElementFromList();
        return dashboardPage.checkIsVisiblePlayButton() && dashboardPage.checkIsVisiblePlayButton();
    }

    public boolean checkIsVisibleTrackListWithoutSong() {
        dashboardPage.moveToElementFromList();
        return dashboardPage.checkIsVisibleDeleteButton() && !dashboardPage.checkIsVisiblePlayButton();
    }

    public boolean compareFirstTRackWithAddTrackInQueue() {
        return explorePage.compareFirstTrackWithAddTrackInQueue(EXPLORE_PAGE_RECOMMENDED_SONG_NAME_TRACK.getValue(), EXPLORE_PAG_SONG_QUEUE_TRACK.getValue());
    }

    public boolean compareSecondTRackWithAddTrackInQueue() {
        return explorePage.compareSecondTrackWithAddTrackInQueue(EXPLORE_PAGE_RECOMMENDED_SONG_NAME_TRACK.getValue(), EXPLORE_PAG_SONG_QUEUE_TRACK.getValue());
    }
    public boolean isTheSameListOfThreeTracksAndAddedThreeTracksInQueue(){
        return explorePage.isTheSameListOfTracksAndAddedTracksInQueue(EXPLORE_PAGE_RECOMMENDED_SONG_NAME_TRACK.getValue(), EXPLORE_PAG_SONG_QUEUE_TRACK.getValue(),3);
    }
    public HelperUi clickDeleteFirstTrackFromTheQueue(){
        explorePage.click(EXPLORE_QUEUE_DElETE_TRACK_BUTTON.getValue());
        return this;
    }
    public HelperUi clickLikeFirstTrackTheQueue(){
        explorePage.click(EXPLORE_QUEUE_LIKE_TRACK_BUTTON.getValue());
        return this;
    }
    public boolean isTheSameFirstTrackInQueueAndTrackInEqualizer(){
        return explorePage.isTheSameFirstTrackInQueueAndTrackInEqualizer(EXPLORE_PAGE_NAME_TRACK_EQUALIZER.getValue(), EXPLORE_PAG_SONG_QUEUE_TRACK.getValue());
    }


    public boolean checkPageArtist() {
        return artistPage.checkPageArtist(uploadMusic.getAuthor());
    }

    public boolean checkSearchMessage() {
        return explorePage.checkSearchMessage();
    }

    public HelperUi deleteAndConfirmDeleting() {
        dashboardPage.moveToElementFromList()
                .deleteFirstPlaylist()
                .clickConfirmWhenFewList();
        return this;
    }

    public HelperUi clickUploadNewTrack() {
        dashboardPage.clickUploadNewTrack();
        return this;
    }

    public HelperUi loginWithRememberMe() {
        getLoginPage()
                .clickLoginButton()
                .waitUntilAppear()
                .clickSelectCountryButton()
                .clickInputCountryField()
                .inputCountry(user.getCountry().getCountryName())
                .clickOnCountryName()
                .inputPhone(user.getPhone())
                .inputPassword(user.getPass())
                .clickRememberMe()
                .clickLoginButton();
        return this;
    }

    public HelperUi loginAfterRecoverPasswordByPhone() {
        loginBox.clickSelectCountryButton()
                .clickInputCountryField()
                .inputCountry(user.getCountry().getCountryName())
                .clickOnCountryName()
                .inputPhone(user.getPhone())
                .inputPassword(user.getPass())
                .clickLoginButton();
        return this;
    }

    public HelperUi changePasswordByPhone() {
        passwordChangeBox.clickSelectCountryButton()
                .clickInputCountryField()
                .inputCountry(user.getCountry().getCountryName())
                .clickOnCountryName()
                .inputPhone(user.getPhone())
                .clickContinueButton();
        inputVerificationCode(Base.VERIFICATION_CODE);
        createNewPassword();
        explorePage.clickPopUmMessage();
        return this;
    }

    public HelperUi changePasswordByPhoneWithoutVerify() {
        passwordChangeBox.clickSelectCountryButton()
                .clickInputCountryField()
                .inputCountry(user.getCountry().getCountryName())
                .clickOnCountryName()
                .inputPhone(user.getPhone())
                .clickContinueButton();
        return this;
    }

    public HelperUi deletePreviousInputVerificationCode(String verificationCode) {
        passwordChangeBox.deletePreviousInputNew(VERIFICATION_CODE_INPUT.getValue(), verificationCode);
        signUpBox.clickOnVerify();
        return this;
    }

    public HelperUi clickOnVerify() {
        signUpBox.clickOnVerify();
        return this;
    }

    public HelperUi deletePrevious() {
        passwordChangeBox.deletePrevious(VERIFICATION_CODE_INPUT.getValue());
        return this;
    }

    public HelperUi changePassword() {
        passwordChangeBox.clickSelectCountryButton()
                .clickInputCountryField()
                .inputCountry(user.getCountry().getCountryName())
                .clickOnCountryName()
                .inputPhone(user.getPhone())
                .clickContinueButton();
        inputVerificationCode(Base.VERIFICATION_CODE);
        return this;
    }

    public HelperUi changePasswordByPhoneNoInDatabase() {
        passwordChangeBox.clickSelectCountryButton()
                .clickInputCountryField()
                .inputCountry(user.getCountry().getCountryName())
                .clickOnCountryName()
                .inputPhone(user.getPhone())
                .clickContinueButton()
                .waitUntilAppearPopUpMessage();
        return this;
    }

    public HelperUi changePasswordByEmailNoInDatabase() {
        loginBox.inputEmail(user.getEmail())
                .clickContinueButton();
        passwordChangeBox.waitUntilAppearPopUpMessageUnsuccessEmail();
        return this;
    }


    public HelperUi recoveryPasswordPhone() {
        getLoginPage()
                .clickLoginButton()
                .waitUntilAppear()
                .clickForgotPassword();
        return this;
    }

    public HelperUi recoveryPasswordEmail() {
        getLoginPage()
                .clickLoginButton()
                .waitUntilAppear()
                .clickForgotPassword()
                .clickRecoverViaEmail();
        return this;
    }

    public HelperUi loginAfterSignUp() {
        loginBox.clickLoginButton();
        return this;
    }


    public HelperUi loginViaEmail(User user) {
        getLoginPage()
                .clickLoginButton()
                .waitUntilAppear()
                .clickLoginViaEmailButton()
                .inputEmail(user.getEmail())
                .inputPassword(user.getPass())
                .clickLoginButton();
        return this;
    }

    public boolean isLoggedIn() {
        WaitUtils.setWait(3);
        return !getLoginPage().isPresentLoginButton() && (getUserBox().isPresentMenuButton() || getUserBox().isDisplayedMenuButton());
    }

    public HelperUi signUpViaPhone(User user) {
        getLoginPage()
                .clickSignUpButton()
                .clickSelectCountryButton()
                .clickInputCountryField()
                .inputCountry(user.getCountry().getCountryName())
                .clickCountryDropdownInfo()
                .inputPhone(user.getPhone())
                .clickContinueButton();
        return this;
    }

    public HelperUi signUpViaPhone() {
        user = User.builder()
                .username(generateName())
                .pass(generatePass())
                .phone(generatePhoneNumberBelarus())
                .country(new Country(Base.NEW_COUNTRY))
                .email(generateEmail())
                .lastName(generateName())
                .firstName(generateName())
                .build();
        getLoginPage()
                .clickSignUpButton()
                .clickSelectCountryButton()
                .clickInputCountryField()
                .inputCountry(user.getCountry().getCountryName())
                .clickCountryDropdownInfo()
                .inputPhone(user.getPhone())
                .clickContinueButton();
        inputVerificationCode(Base.VERIFICATION_CODE);
        signUpData(user);
        signUpBox.clickLogin();
        return this;
    }

    public HelperUi signUpViaPhoneBelarus() {
        openLoginPage();
        user = createUserBelarus();
        signUpViaPhone(user);
        inputVerificationCode(Base.VERIFICATION_CODE);
        signUpData(user);
        getSignUpBox().clickLogin();
        getLoginBox().clickLoginButton();
        return this;
    }

    public HelperUi createNewPlaylist(Playlist playlist) {
        getDashboardPage().clickPlaylistsTab()
                .clickCreateNewPlaylistButton();
        getCreateNewPlaylistPage()
                .inputPlaylistName(playlist.getName())
                .inputDescription(playlist.getDescription())
                .inputPicture(playlist.getPicture())
                .clickSaveButton();
        return this;
    }

    public HelperUi createNewPlaylist() {
        Playlist playlist = Playlist.builder()
                .name(generateName())
                .description(generateDesription())
                .picture(AVATAR_FILE.getAbsoluteFile())
                .build();
        createNewPlaylist(playlist);
        return this;
    }

    public HelperUi createNewPlaylistWithTrack(Playlist playlist) {
        clickDashboard();
        clickUploadMusic();
        clickUploadNewTrack();
        uploadNewTrack();
        createTrackAndSave();
        openDashboardPage();
        getDashboardPage()
                .clickCreateNewPlaylistButton();
        getCreateNewPlaylistPage()
                .inputPlaylistName(playlist.getName())
                .inputDescription(playlist.getDescription())
                .inputPicture(playlist.getPicture())
                .scrollDownToAddTracksText();
        inputTrackName(uploadMusic.getTitle());
        clickAddToPlaylist();
        clickMenuAddTrackInPlaylist();
        clickSavePlaylist();
        return this;
    }

    public HelperUi removeCreatedPlaylist() {
        playlistsPage.clickMyPlaylistsTab()
                .removeMyPlaylist();
        return this;
    }
    public HelperUi removeTrackInQueue() {
        explorePage.removeTrackInQueue();
        return this;
    }

    public HelperUi scrollDownToPlaylistTextField() {
        explorePage.scrollDownToPlaylistTextField();
        return this;
    }

    public HelperUi scrollDownToNowTrendingTextField() {
        explorePage.scrollDownToNowTrendingText();
        return this;
    }

    public HelperUi scrollDownToGenres() {
        explorePage.scrollDownToGenresTextField();
        return this;
    }

    public HelperUi scrollDownToRecommendedSongs() {
        explorePage.scrollDownToRecommededSongs();
        return this;
    }

    public HelperUi playRecommendedSong() {
        explorePage.moveToElementsAndClick(explorePage.recommendedSong(), explorePage.recommendedSongPlayButton());
        return this;
    }

    public HelperUi playRecommendedFirstSong() {
        explorePage.click(RECOMMENDED_SONG_PLAY_PANEL.getValue());
        return this;
    }

    public HelperUi clickAddQueueRecSong() {
        explorePage.moveToElementsAndClick(explorePage.nextRecommendedSong(), explorePage.recommendedSongAddQueueButton());
        return this;
    }

    public HelperUi clickAddToPlaylist() {
        explorePage.moveToElementsAndClick(playlistsPage.getTrackFromSearchResults(), explorePage.addQueueButtonInPlayList());
        return this;
    }

    public HelperUi clickAddQueueNowTrending() {
        explorePage.moveToElementsAndClick(explorePage.NowTrendingFirstSong(), explorePage.NowTrendingAddQueueButton());
        return this;
    }

    public HelperUi clickMenuAddTrackInQueue() {
        explorePage.clickMenuAddTrackInQueue();
        return this;
    }

    public HelperUi clickMenuAddTrackInPlaylist() {
        explorePage.clickMenuAddTrackInPlaylist();
        return this;
    }

    public HelperUi likeRecommendedPlaylist() {
        explorePage.moveToElementsAndClick(explorePage.recommendedPlayList(), explorePage.recommendedPlayListLikeButton());
        return this;
    }

    public HelperUi checkBeforeDislike() {
        moveToPlayListAndLikeButton();
        if (explorePage.isDislikeDisplayedOnPlayList()) {
            likeRecommendedPlaylist();
        }
        return this;
    }

    public HelperUi dislikeRecommendedPlaylist() {
        explorePage.moveToElementsAndClick(explorePage.recommendedPlayList(), explorePage.recommendedPlayListLikeButton());
        return this;
    }

    public HelperUi rememberTracksQueue() {
        explorePage.setTracksQueue(explorePage.getQueueTracks());
        return this;

    }

    public HelperUi rememberTracksQueueAfterRemoval() {
        explorePage.setTracksQueueAfterRemoval(explorePage.getQueueTracks());
        return this;
    }

    public boolean isDisplayedPanelOnCoverSong() {
        return explorePage.isDisplayedButtonAddRemoveLikeSong()
                && explorePage.isDisplayedButtonAddToPlaylistQueueSong()
                && explorePage.isDisplayedButtonPlaySong();
    }

    public boolean isDisplayedPanelOnCoverPlaylist() {
        return explorePage.isDisplayedButtonAddRemoveLikePlaylist()
                && explorePage.isDisplayedButtonPlayPlaylist();
    }

    public List<String> getTracksQueue() {
        return explorePage.getTracksQueue();
    }

    public List<String> getTracksQueueAfterRemoval() {
        return explorePage.getTracksQueueAfterRemoval();
    }

    public int getLikeCount() {
        return explorePage.getLikeCount();
    }

    public int getLikeCountAfterDislike() {
        return explorePage.getLikeCountAfterDislike();
    }

    public int getLikeCountAfterLike() {
        return explorePage.getLikeCountAfterLike();
    }

    public HelperUi createUser(String pass, String password, String country) {
        user = User.builder()
                .pass(pass)
                .phone(password)
                .country(new Country(country))
                .build();
        return this;
    }

    public HelperUi createUser() {
        user = User.builder()
                .pass(generatePass())
                .phone(generatePhoneNumberBelarus())
                .country(new Country(Base.NEW_COUNTRY))
                .build();
        return this;
    }

    public HelperUi createUser(String username, String pass, String phone, String userEmail, String country) {
        user = User.builder()
                .username(username)
                .pass(pass)
                .phone(phone)
                .email(userEmail)
                .country(new Country(country))
                .build();
        return this;
    }

    public User createUserBelarus() {
        return user = User.builder()
                .firstName(Base.FIRST_NAME)
                .lastName(Base.LAST_NAME)
                .username(generateName())
                .pass(Base.NEW_PASSWORD)
                .phone(generatePhoneNumberBelarus())
                .email(generateEmail())
                .country(new Country(Base.NEW_COUNTRY))
                .build();
    }

    public HelperUi clickPlaylistsTab() {
        playlistsPage.clickPlaylistsTab();
        return this;
    }

    public HelperUi clickSavePlaylist() {
        playlistsPage.clickSavePlaylist();
        return this;
    }

    public HelperUi inputTrackName(String trackName) {
        playlistsPage.inputTrackName(trackName);
        return this;
    }

    public HelperUi playPlaylist() {
        explorePage.moveToElementsAndClick(playlistsPage.playlist(), playlistsPage.playlistPlayButton());
        return this;
    }

    public HelperUi clickQueueButton() {
        playlistsPage.playlistQueueButton();
        return this;
    }

    public HelperUi clickOnPlaylist() {
        playlistsPage.clickOnPlaylist();
        return this;
    }

    public HelperUi clickEditPlaylist() {
        playlistsPage.clickEditPlaylist();
        return this;
    }

    public HelperUi clickDeleteTrack() {
        playlistsPage.clickDeleteTrackButton();
        return this;
    }

    public HelperUi moveToPlayListAndLikeButton() {
        explorePage.moveToElements(explorePage.recommendedPlayList(), explorePage.recommendedPlayListLikeButton());
        return this;
    }

    public HelperUi rememberLikeCountOnPlaylist() {
        moveToPlayListAndLikeButton();
        explorePage.setLikeCount(Integer.parseInt(explorePage.recommendedPlayListLikeCounter().getText()));
        return this;
    }

    public HelperUi rememberLikeCountOnPlaylistAfterDislike() {
        moveToPlayListAndLikeButton();
        explorePage.setLikeCountAfterDislike(Integer.parseInt(explorePage.recommendedPlayListLikeCounter().getText()));
        return this;
    }

    public HelperUi rememberLikeCountOnPlaylistAfterLike() {
        moveToPlayListAndLikeButton();
        explorePage.setLikeCountAfterLike(Integer.parseInt(explorePage.getPlayListLikeCount()));
        return this;
    }

    public List<String> getPlaylistTracks() {
        return playlistsPage.getPlaylistTracks();
    }

    public List<String> getQueueTracks() {
        return playlistsPage.getQueueTracks();
    }

    public HelperUi clickOnBackButton() {
        profilePage.clickBackButton();
        return this;
    }

    public HelperUi deletePreviousInputNewEmailProfile(String email) {
        profilePage.deletePreviousInputNewEmailProfile(email);
        return this;
    }

    public HelperUi clickSaveButtonProfile() {
        profilePage.clickSaveButton();
        return this;
    }

    public HelperUi findGeners() {
        explorePage.getGeners();
        return this;
    }

    public HelperUi clickOnViewAllTrendingNow() {
        explorePage.clickOnViewAllTrendingNowButton();
        return this;
    }

    public HelperUi clickFromAllTrendingNowOnBackToExplorePage() {
        trendingNowPage.clickOnBackToExplorePage();
        return this;
    }

    public HelperUi clickOnViewNewAlbumPageButton() {
        explorePage.clickOnViewNewAlbumPageButton();
        return this;
    }

    public HelperUi clickFromViewNewAlbumPageToExplorePage() {
        newAlbumPage.clickOnBackToExplorePage();
        return this;
    }

    public HelperUi openMyTracklistPage() {
        mainMenu.clickMyTracklistButton();
        return this;
    }

    public HelperUi openFavoritesPage() {
        mainMenu.clickFavoritesButton();
        return this;
    }

    public HelperUi inputVerificationCode(String verificationCode) {
        signUpBox.inPutVerificationCode(verificationCode)
                .clickOnVerify();
        return this;

    }

    public HelperUi signUpData(User user) {
        signUpBox.inputUsername(user.getUsername())
                .inputFirstName(user.getFirstName())
                .inputLastName(user.getLastName())
                .inputEmail(user.getEmail())
                .inputPassword(user.getPass())
                .inputPasswordConfirm(user.getPass())
                .clickSignUp();
        return this;
    }

    public HelperUi createNewPassword() {
        passwordChangeBox.inputPassword(user.getPass())
                .inputPasswordConfirm(user.getPass())
                .clickConfirm();
        return this;
    }

    public HelperUi createNewDifferentPasswords() {
        passwordChangeBox.inputPassword(generatePass())
                .inputPasswordConfirm(generatePass())
                .clickConfirm();
        return this;
    }

    public HelperUi createPasswordNewPasswordAndNull() {
        passwordChangeBox.deletePreviousInputNew(PASSWORD_INPUT_FIELD.getValue(), generatePass())
                .deletePreviousInputNew(PASSWORD_CONFIRM_INPUT_FIELD.getValue(), "")
                .clickConfirm();
        return this;
    }

    public HelperUi createPasswordNewNullAndPassword() {
        passwordChangeBox.deletePreviousInputNew(PASSWORD_INPUT_FIELD.getValue(), "")
                .deletePreviousInputNew(PASSWORD_CONFIRM_INPUT_FIELD.getValue(), generatePass())
                .clickConfirm();
        return this;
    }

    public HelperUi createPasswordNewNullAndNull() {
        passwordChangeBox.deletePreviousInputNew(PASSWORD_INPUT_FIELD.getValue(), "")
                .deletePreviousInputNew(PASSWORD_CONFIRM_INPUT_FIELD.getValue(), "")
                .clickConfirm();
        return this;
    }

    public HelperUi createPasswordNewSevenSymbols() {
        passwordChangeBox.deletePreviousInputNew(PASSWORD_INPUT_FIELD.getValue(), generatePassSevenSymbols())
                .deletePreviousInputNew(PASSWORD_CONFIRM_INPUT_FIELD.getValue(), "")
                .clickConfirm();
        return this;
    }

    public HelperUi createPasswordNewFirstPoint() {
        passwordChangeBox.deletePreviousInputNew(PASSWORD_INPUT_FIELD.getValue(), generatePassFirstPoint())
                .deletePreviousInputNew(PASSWORD_CONFIRM_INPUT_FIELD.getValue(), "")
                .clickConfirm();
        return this;
    }

    public HelperUi createPasswordWithPoint() {
        passwordChangeBox.deletePreviousInputNew(PASSWORD_INPUT_FIELD.getValue(), generatePassSixteen() + ".")
                .deletePreviousInputNew(PASSWORD_CONFIRM_INPUT_FIELD.getValue(), "")
                .clickConfirm();
        return this;
    }

    public HelperUi addPasswordSixteenCharacter() {
        passwordChangeBox.inputPassword(generatePassSixteen())
                .clickConfirm();
        return this;
    }

    public HelperUi playTrackFromAlbum() {
        newAlbumPage.playTrackFromAlbum();
        return this;
    }

    public HelperUi moveToElementSong() {
        explorePage.moveToElement(RECOMMENDED_SONG.getValue());
        return this;
    }

    public HelperUi moveToAndPlaySecondSong() {
        explorePage.moveToElementsAndClick(explorePage.secondRecommendedSong(), explorePage.secondRecommendedSongPlay());
        return this;
    }
    public HelperUi openAvatarChangeWindow() {
        profilePage.clickAvatarChangeButton();
        return this;
    }

    public HelperUi uploadAvatarJpg() {
        profilePage.clickUploadAvatarJpg();
        return this;
    }

    public HelperUi uploadAvatarJpeg() {
        profilePage.clickUploadAvatarJpeg();
        return this;
    }

    public HelperUi uploadAvatarPng() {
        profilePage.clickUploadAvatarPng();
        return this;
    }

    public HelperUi saveAvatar() {
        profilePage.clickAvatarSaveButton();
        return this;
    }

    public HelperUi uploadNewTrack() {
        dashboardPage.uploadNewTrack();
        return this;
    }

    public HelperUi signUpDataWhenPasswordMismatch(User user) {
        signUpBox.inputFirstName(user.getFirstName())
                .inputLastName(user.getLastName())
                .inputUsername(user.getUsername())
                .inputPassword(user.getPass())
                .inputPasswordConfirm(user.getUsername());
        return this;
    }

    public void deleteUser() {
        profilePage.scrollDownToDeleteMyAccount()
                .clickDeleteAccountButton()
                .confirmationDeleteAccount();
    }

    public HelperUi setAvatar(File file) {
        profilePage.clickImageAvatar()
                .addImageAvatar(file)
                .clickSaveAvatar();
        return this;
    }

    public HelperUi uploadTrack() {
        dashboardPage.clickUploadMusicTab();
        uploadMusicPage.clickUploadANewTrackButton();
        uploadMusicPage.clickUploadTrackDropFileButton();
        return this;
    }

    public HelperUi uploadInvalidTrack() {
        dashboardPage.clickUploadMusicTab();
        uploadMusicPage.clickUploadANewTrackButton();
        uploadMusicPage.clickUploadTrackDropFileButtonForInvalidTrack();
        return this;
    }

    public HelperUi cancelUploadTrack() {
        uploadMusicPage.clickCancelUploadButton();
        return this;
    }

    public HelperUi createTrack(UploadMusic uploadMusic) {
        getUploadNewTrack()
                .inputTrackAuthor(uploadMusic.getAuthor())
                .inputTrackTitle(uploadMusic.getTitle())
                .inputTrackAlbum(uploadMusic.getAlbum())
                .inputTrackGenre(uploadMusic.getGenre())
                .dropFiles(uploadMusic.getCover())
                .clickSaveButton();
        uploadMusicPage.waitUntilDisappearNotification();
        return this;
    }

    public HelperUi createTrackAndSave() {
        uploadMusic = UploadMusic.builder()
                .author("")
                .title(generateName())
                .album(generateName())
                .genre("")
                .build();
        dashboardPage.inputTrackInformation(uploadMusic);
        dashboardPage.clickSave()
                .waitUntilDisappear(DASHBOARD_UPLOAD_NEW_TRACK.getValue());
        explorePage.clickPopUmMessage();
        return this;
    }

    public HelperUi getArtistNameAndGoExplorerPage() {
        uploadMusic.setAuthor(dashboardPage.getAuthorName());
        dashboardPage.clickGoToExplorerPAge();
        return this;
    }

    public HelperUi searchTrackIsInDatabase() {
        explorePage.clickSearch();
        explorePage.inPutAuthor(uploadMusic.getAuthor());
        explorePage.scrollDownToArtist();
        explorePage.clickOnArtistInSearch();
        return this;
    }

    public HelperUi searchTrackNoInDatabase() {
        explorePage.clickSearch();
        explorePage.inPutAuthorRandom(generateName());
        return this;
    }

    public boolean checkSortingTrack() {
        return dashboardPage.checkSortingTrackByTitle(uploadMusic)
                && dashboardPage.checkSortingTrackByAlbum(uploadMusic);
    }

    public HelperUi deleteTracks() {
        dashboardPage.deleteAllTracks();
        return this;
    }


    public HelperUi changePasswordBelarus() {
        getChangePasswordBox()
                .inputOldPassword(Base.NEW_PASSWORD)
                .inputNewPassword(Base.MODIFIED_PASSWORD)
                .inputConfirmPassword(Base.MODIFIED_PASSWORD);
        changePasswordConfirm();
        user.setPass(Base.MODIFIED_PASSWORD);
        return this;
    }

    private void changePasswordConfirm() {
        getChangePasswordBox()
                .clickConfirmButton();
    }

    public HelperUi playRecommendedPlaylist() {
        explorePage.moveToRecommendedPlaylist()
                .clickPlayRecommendedPlaylist();
        return this;
    }

    public String getUrl() {
        return explorePage.getCurrentUrl();
    }

    public boolean compareEnteringParametersWithProfile() {
        return profilePage.compareEnteringParameterWithProfileFirstName(user)
                && profilePage.compareEnteringParameterWithProfileLastName(user)
                && profilePage.compareEnteringParameterWithProfileUserName(user)
                && profilePage.compareEnteringParameterWithProfileEmail(user)
                && profilePage.compareEnteringParameterWithProfilePhoneNumber(user);
    }

    public HelperUi rememberCountOfUploadedTracks() {
        dashboardPage.setCountTracks(dashboardPage.getCountRecentlyUploadedTracks());
        return this;
    }

    public HelperUi rememberCountOfUploadedTracksAfterDeleting() {
        dashboardPage.setCountTracksAfterDeleting(dashboardPage.getCountRecentlyUploadedTracksAfterDeleting());
        return this;
    }

    public int getTrackCount() {
        return dashboardPage.getCountTracks();
    }

    public int getTrackCountAfterDeleting() {
        return dashboardPage.getCountTracksAfterDeleting();
    }

    public boolean isDisplayedMessageInvalidDataName(String textMessage) {
        return playlistsPage.isDisplayedMessageInvalidDataName()
                && playlistsPage.checkColorMessageInvalidDataName()
                && playlistsPage.checkTextMessageInvalidDataName(textMessage);
    }

    public boolean isDisplayedMessageInvalidDataDescription(String textMessage) {
        return playlistsPage.isDisplayedMessageInvalidDataDescription()
                && playlistsPage.checkColorMessageInvalidDataDescription()
                && playlistsPage.checkTextMessageInvalidDataDescription(textMessage);
    }

    public HelperUi changeNameAndDescriptionPlaylist(String name, String description) {
        playlistsPage.inputNamePlaylist(name)
                .inputDescriptionPlaylist(description)
                .clickSavePlaylist();
        return this;
    }

    public HelperUi changeImagePlaylist(File file) {
        createNewPlaylistPage.inputPicture(file);
        playlistsPage.clickSavePlaylist();
        return this;
    }

    public boolean isDisplayedMessageInvalidDataImage(String textMessage) {
        return playlistsPage.isDisplayedMessageInvalidDataImage()
                && playlistsPage.checkColorMessageInvalidDataImage()
                && playlistsPage.checkTextMessageInvalidDataImage(textMessage);
    }

    public boolean isViewPublicPlaylistInformation(boolean myPlaylist) {
        return playlistsPage.isDisplayedCoverPlaylist()
                && playlistsPage.isDisplayedTitlePlaylist()
                && playlistsPage.isDisplayedDescriptionPlaylist()
                && playlistsPage.isDisplayedNumberTracksPlaylist()
                && playlistsPage.isDisplayedNumberLikesPlaylist()
                && playlistsPage.isDisplayedPlayAllButton()
                && playlistsPage.isDisplayedTrackInPlaylist()
                && isDisplayedEditButton(myPlaylist);
    }

    public boolean isViewEditPlaylistInformation() {
        return playlistsPage.isDisplayedCoverEditPlaylist()
                && playlistsPage.isDisplayedTitleEditPlaylist()
                && playlistsPage.isDisplayedDescriptionEditPlaylist()
                && playlistsPage.isDisplayedTrackInPlaylist()
                && playlistsPage.isDisplayedUploadButton()
                && playlistsPage.isDisplayedSharePlaylistText();
    }

    public boolean isDisplayedEditButton(boolean myPlaylist) {
        if (myPlaylist) {
            return playlistsPage.isDisplayedEditButton();
        }
        return !playlistsPage.isDisplayedEditButton();
    }

    public HelperUi clickPlayRecommendedSong() {
        explorePage.scrollDownToRecommendedSong(RECOMMENDED_SONG.getValue());
        explorePage.moveToRecommendedSong();
        explorePage.clickRecommendedSongPlayButton();
        return this;
    }

    public HelperUi clickPlayAndPauseRecommendedSong() {
        explorePage.moveToRecommendedSong()
                .clickRecommendedSongPlayButton();
        explorePage.clickRecommendedSongPauseButton();
        return this;
    }

    public HelperUi addThreeSongsFromRecommendedSongToQueue() {
        explorePage.scrollDownToRecommendedSong(RECOMMENDED_SONG.getValue());
        explorePage.moveToRecommendedSong()
                .clickRecommendedSongAddButton(RECOMMENDED_SONG_ADD_PANEL.getValue(),1)
                .clickMenuAddTrackInQueue();
        explorePage.moveToRecommendedSecondSong()
                .clickRecommendedSongAddButton(RECOMMENDED_SONG_ADD_PANEL.getValue(),2)
                .clickMenuAddTrackInQueue();
        explorePage.moveToRecommendedThirdSong()
                .clickRecommendedSongAddButton(RECOMMENDED_SONG_ADD_PANEL.getValue(),3)
                .clickMenuAddTrackInQueue();
        return this;
    }
    public HelperUi clickPlayRecommendedSecondSong() {
        explorePage.moveToRecommendedSecondSong();
        explorePage.clickRecommendedSecondSongPlayButton();
        return this;
    }

    public HelperUi openQueueList() {
        explorePage.click(EXPLORE_PAGE_QUEUE_BUTTON.getValue());
        return this;
    }

    public HelperUi openMyPlaylistNotPublic() {
        List<String> namesPublicPlaylist = playlistsPage.getNamesPlaylists();
        playlistsPage.clickMyPlaylistsTab()
                .clickMyPlaylistNotPublic(namesPublicPlaylist);
        return this;
    }

    public HelperUi clickSharePlaylist(String namePlaylist) {
        playlistsPage.clickPublicMyPlaylist(namePlaylist)
                .clickEditPlaylist()
                .clickSharePlaylistButton()
                .clickSavePlaylist();
        return this;
    }

    public HelperUi clickOnLogo() {
        explorePage.clickOnLogo();
        return this;
    }

    public HelperUi editDashboardPlaylist() {
        dashboardPage.isPresent(DASHBOARD_PLAYLISTS_LIST.getValue())
                .movePlaylistAndClick()
                .clickEditButton();
        return this;
    }

    public HelperUi clickButtonNoOnModalWindow() {
        dashboardPage.clickDeleteButton()
                .clickButtonNoOnModalWindow();
        return this;
    }

    public HelperUi clickButtonYesOnModalWindow() {
        dashboardPage.clickDeleteButton()
                .clickConfirmWhenFewList();
        return this;
    }

    public String getTextAlertMessage() {
        return dashboardPage.getAlertMessage();
    }

    public int getCountOfPlaylist() {
        return dashboardPage.getCountOfPlaylist();
    }

    public HelperUi waitUntilDisappearConfirmMessage() {
        dashboardPage.waitUntilDisappear(DASHBOARD_CONFIRM_MESSAGE_PLAYLIST_WAS_REMOVED.getValue());
        return this;
    }

    public HelperUi createNewTestPlaylist() {
        dashboardPage.clickPlaylistsTab();
        dashboardPage.clickCreateNewPlaylist();
        createNewPlaylistPage.inputPlaylistName("TEST NAME")
                .inputDescription("TEST DESCRIPTION")
                .inputPicture(AVATAR_FILE)
                .clickSaveButton();
        dashboardPage.waitUntilDisappear(POP_UP_MESSAGE.getValue());
        return this;
    }

    public HelperUi editTestPlaylist() {
        dashboardPage.clickOnCreatedTestPlaylist();
        playlistsPage.clickEditPlaylist();
        playlistsPage.inputNamePlaylist("NEW TEST NAME")
                .inputDescriptionPlaylist("NEW TEST DESCRIPTION")
                .clickSavePlaylist();
        dashboardPage.waitUntilDisappear(POP_UP_MESSAGE.getValue());
        return this;
    }

    public HelperUi checkEditedPlaylist() {
        dashboardPage.clickOnEditedTestPlaylist();
        playlistsPage.checkName();
        playlistsPage.checkDescription();
        return this;
    }

    public HelperUi deleteEditedPlaylist() {
        playlistsPage.clickEditPlaylist();
        playlistsPage.clickOnDeletePlaylistButton();
        return this;
    }

    public HelperUi moveToPlaylistAndClickPlayButton() {
        dashboardPage.moveToPlaylistAndClickPlayButton();
        return this;
    }

    public HelperUi playlistQueueButton(){
        playlistsPage.playlistQueueButton();
        return this;
    }

    public int getCountOfTracksInPlayQueue() {
        return dashboardPage.getCountOfTracksInPlayQueue();
    }

    public int getCountTracksInPlaylist(){
        return dashboardPage.getCountTracksInPlaylist();
    }

    public HelperUi moveToUploadMusicTab(){
        dashboardPage.moveToUploadMusicTab();
        return this;
    }

    public HelperUi createNewTrack(){
        uploadMusic = UploadMusic.builder()
                .author("")
                .title(generateName())
                .album(generateName())
                .genre("")
                .build();
        return this;
    }

    public String getCreatedTrackTitle(){
        uploadMusicPage.clickUploadANewTrackButton()
                .clickUploadTrackDropFileButton();
        createNewTrack();
        dashboardPage.inputTrackInformation(uploadMusic);
        String textTitle = uploadMusic.getTitle();
        dashboardPage.clickSave();
        return textTitle;
    }

    public String getTitleTrack(){
        return dashboardPage.getTitleTrack();
    }

    public int getCountUploadTrack(){
        return dashboardPage.getCountUploadTrack();
    }

    public HelperUi waitUntilDisappearConfirmMessageUpload() {
        dashboardPage.waitUntilDisappear(DASHBOARD_CONFIRM_MESSAGE_NEW_TRACK_UPLOADED.getValue());
        return this;
    }
}
