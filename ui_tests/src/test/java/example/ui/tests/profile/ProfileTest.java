package example.ui.tests.profile;

import example.ui.tests.BaseUiTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


@Slf4j

public class ProfileTest extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        log.info("!!!!ProfileTest");
        helperUi.openLoginPage();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
    }


    @Test(description = "Change profile password")
    public void changeProfilePasswordHappyPath() {
        helperUi.signUpViaPhoneBelarus()
                .getExplorePage()
                .waitUntilDisappearNotification();
        helperUi.openProfile()
                .getProfilePage()
                .scrollDownToDeleteMyAccount()
                .clickChangePassword();
        assertTrue(helperUi.getChangePasswordBox().checkFieldPassword(), "Password field is not a valid type");
        helperUi.changePasswordBelarus();
        assertTrue(helperUi.getProfilePage()
                             .getNotificationPasswordSuccessfullyChanged()
                             .isDisplayed(), "Password successfully changed");
        helperUi.getExplorePage()
                .waitUntilDisappearNotification();
        helperUi.signOut()
                .login();
        assertTrue(helperUi.isLoggedIn(), "User is logged in");
        helperUi.openProfile()
                .deleteUser();
    }
}