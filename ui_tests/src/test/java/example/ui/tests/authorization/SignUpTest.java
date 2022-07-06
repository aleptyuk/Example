package example.ui.tests.authorization;

import example.identities.User;
import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignUpTest extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
    }
    @Test(description = "Password mismatch during registration")
    public void signUpWhenPasswordMismatch() {
        User user = helperUi.createUserBelarus();
        helperUi.signUpViaPhone(user)
                .inputVerificationCode(Base.VERIFICATION_CODE);
        helperUi.signUpDataWhenPasswordMismatch(user);
        String urlPage = helperUi.getExplorePage().getCurrentUrl();
        helperUi.getSignUpBox().clickSignUpFormSave();
        assertTrue(helperUi.getSignUpBox().isDisplayedMessagePasswordsMustMatch(),"Passwords must match");
        assertEquals(helperUi.getExplorePage().getCurrentUrl(),urlPage);
    }

    @Test(description = "Time limit on code confirmation during registration")
    public void signUpWhenTimeUpVerification() {
        User user = helperUi.createUserBelarus();
        helperUi.signUpViaPhone(user);
        helperUi.getSignUpBox()
                .waitUntilDisappearTimeVerification();
        assertTrue(helperUi.getSignUpBox()
                .isClickableButtonRequestVerification(), "Request a new verification code");
    }
}
