package example.ui.tests.explore;


import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LogoTest extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.signOut();
    }


    @Test(description = "Return to the home page of the website after clicking on the logo.")
    public void clickOnLogoForReturnToHomePageTest() {
        helperUi.openProfile()
                .clickOnLogo();
        assertTrue(helperUi.getExplorePage().isDisplayedExplorePage(), "There is no explore page");
    }
}
