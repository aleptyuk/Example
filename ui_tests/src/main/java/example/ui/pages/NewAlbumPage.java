package example.ui.pages;

import org.openqa.selenium.WebDriver;

import static example.ui.enums.Locators.*;

public class NewAlbumPage extends BasePage {

    public NewAlbumPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayedNewAlbumPage() {

        return welActions.isDisplayed(DISPLAYED_NEW_ALBUM_PAGE.getValue());
    }

    public void clickOnBackToExplorePage() {
        welActions.click(BACK_TO_EXPLORE_BUTTON.getValue());
    }

    public void clickOn(){
        welActions.click(NEW_ALBUM_FIRST_ALBUM.getValue());
    }

    public boolean isDisplayEqualizer() {
        return welActions.isDisplayed(NEW_ALBUM_EQUALIZER.getValue());
    }
    public boolean isDisplayListTracks(){
        return welActions.isDisplayed(NEW_ALBUM_FIRST_TRACK.getValue());
    }

    public void playTrackFromAlbum() {
        welActions.click(NEW_ALBUM_FIRST_TRACK.getValue());
    }

}
