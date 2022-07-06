package example.ui.pages.elements;

import example.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;

import static example.ui.enums.Locators.PLAYLIST_PLAY_ICON;


public class Player extends BasePage {

    private static final String DISPLAYED_PLAYER = "player.displayed.player";

    public Player(WebDriver driver) {
        super(driver);
    }
    public boolean isDisplayedPlayer() {
        return welActions.isDisplayed(DISPLAYED_PLAYER);
    }

    public boolean isDisplayedIconPlay() {
        return welActions.getElements(PLAYLIST_PLAY_ICON.getValue()).size() == 1;
    }

    public boolean isDisplayedIconPause() {
        return welActions.getElements(PLAYLIST_PLAY_ICON.getValue()).size() == 2;
    }
}
