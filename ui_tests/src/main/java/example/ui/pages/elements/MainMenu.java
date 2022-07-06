package example.ui.pages.elements;

import example.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class MainMenu extends BasePage {

    private static final String MY_TRACKLIST_BUTTON = "mainmenu.mytracklist.button";
    private static final String FAVORITES_BUTTON = "mainmenu.favorites.button";
    private static final String PLAYLISTS_BUTTON = "mainmenu.playlists.button";
    private static final String DASHBOARD_BUTTON = "mainmenu.dashboard.button";

    public MainMenu(WebDriver driver) {
        super(driver);
    }

    public MainMenu clickMyTracklistButton() {
        welActions.click(MY_TRACKLIST_BUTTON);
        return this;
    }

    public MainMenu clickFavoritesButton() {
        welActions.click(FAVORITES_BUTTON);
        return this;
    }

    public MainMenu clickPlaylistsButton() {
        welActions.click(PLAYLISTS_BUTTON);
        return this;
    }

    public MainMenu clickDashboardButton() {
        welActions.click(DASHBOARD_BUTTON);
        return this;
    }

}
