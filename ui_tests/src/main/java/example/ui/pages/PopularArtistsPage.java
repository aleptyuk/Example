package example.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class PopularArtistsPage extends BasePage {
    private static final String BACK_TO_EXPLORE_BUTTON = "popularartists.page.back.button";

    public PopularArtistsPage(WebDriver driver) {
        super(driver);
    }


}