package example.ui.tests.profile;

import example.ui.tests.BaseUiTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.testng.Assert.assertTrue;

@Slf4j

public class ProfileInformationTest extends BaseUiTest {
    @BeforeMethod(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @Test(description = "Automatic loading of personal data in input fields")
    public void personalDataProfile() {
        helperUi.signUpViaPhone()
                .loginAfterSignUp()
                .clickPopUmMessage()
                .openProfile();
        assertTrue(helperUi.compareEnteringParametersWithProfile(), "The filled parameters are different from the parameters in the profile");
        helperUi.deleteProfile();
    }
}
