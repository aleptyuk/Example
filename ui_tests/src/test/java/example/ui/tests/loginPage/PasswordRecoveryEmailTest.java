package example.ui.tests.loginPage;

import example.ui.tests.BaseUiTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class PasswordRecoveryEmailTest extends BaseUiTest {
    @BeforeMethod(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @Test(description = "Password recovery via email, which is not in the database")
    public void passwordRecoveryEmailNoInDatabase() {
        helperUi.recoveryPasswordEmail()
                .createUserBelarus();
        helperUi.changePasswordByEmailNoInDatabase();
        assertTrue(helperUi.getPasswordChangeBox().isDisplayedPopUpMessageUnsuccessEmail(), " Message \"Email doesn't exist\" doesn't appears");

    }

}
