package example.ui.tests.profile;


import example.ui.tests.BaseUiTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class CreateNewAccountAndDeleteItTest extends BaseUiTest {

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @Test(description = "Deleting a user account.")
    public void createNewAccountAndDeleteIt() {
        helperUi.signUpViaPhone()
                .loginAfterSignUp()
                .clickPopUmMessage()
                .openProfile()
                .deleteProfile();
        assertTrue(helperUi.getLoginPage().isDisplayedPopUpMessage(), " Message \"Success\" doesn't appears");
        assertTrue(helperUi.getLoginPage().isDisplayedLoginPage(), "Login page is not opened");
        helperUi.getLoginPage().clickPopUmMessage();
        helperUi.login();
        assertTrue(helperUi.getLoginBox().isDisplayedPopUpMessageUnSuccess(), " Message \"Unsuccesfull authorization \" doesn't appears");
    }
}
