package example.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class FavoritesPage extends MainPage {

    private static final String DISPLAYED_FAVORITESPAGE = "favoritepage.displayer.favoritepage";

    public FavoritesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayedFavoritesPage() {
        return welActions.isDisplayed(DISPLAYED_FAVORITESPAGE);
    }
}
