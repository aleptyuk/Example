package example.ui.pages;

import org.openqa.selenium.WebDriver;


public class TrendingNowPage extends BasePage {
    private static final String BACK_TO_EXPLORE_BUTTON = "nowtrending.page.back.button";
    private static final String DISPLAYED_TRENDING_PAGE_NOW = "nowtrending.displayed.nowtrendingpage";

    public TrendingNowPage(WebDriver driver) {

        super(driver);
    }

    public boolean isDisplayedTrendingNowPage() {

        return welActions.isDisplayed(DISPLAYED_TRENDING_PAGE_NOW);
    }

    public void clickOnBackToExplorePage() {
        welActions.click(BACK_TO_EXPLORE_BUTTON);
    }


}
