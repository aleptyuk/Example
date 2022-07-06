package example.ui.pages;

import org.openqa.selenium.WebDriver;

import static example.ui.enums.Locators.ARTIST_PAGE_ARTIST;


public class ArtistPage extends BasePage {
    public ArtistPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkPageArtist(String author) {
        return author.equals(welActions.getElement(ARTIST_PAGE_ARTIST.getValue()).getText());
    }
}
