package example.ui.tests.explorePage;

import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.testng.Assert.assertTrue;


public class GenresButtonTest extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.signOut();
    }

    @Test(description = "Go to the genre page and back to the Explore")
    public void genresButtonTest() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login();
        helperUi.findGeners();
        assertTrue(helperUi.getExplorePage().isDisplayedGeners(), "Check Genres");
        helperUi.getExplorePage().allGenersButtonClick();
    }
}

