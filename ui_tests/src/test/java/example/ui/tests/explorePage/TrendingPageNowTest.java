package example.ui.tests.explorePage;


import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.testng.Assert.assertTrue;


@Slf4j

public class TrendingPageNowTest extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.signOut();
    }


    @Test(description = "Go to the Now trending page and back to Explore")
    public void trendingPageNowButton() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login();
        helperUi.clickOnViewAllTrendingNow();
        assertTrue(helperUi.getTrendingNowPage().isDisplayedTrendingNowPage(), "TrendingNow page is not opened");
        helperUi.clickFromAllTrendingNowOnBackToExplorePage();
        assertTrue(helperUi.getExplorePage().isDisplayedExplorePage(), "Explore page is not opened");
    }
}