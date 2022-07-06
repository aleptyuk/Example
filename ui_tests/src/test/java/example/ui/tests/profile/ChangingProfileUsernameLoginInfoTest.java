package example.ui.tests.profile;


import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static example.ui.enums.Locators.*;


@Slf4j


public class ChangingProfileUsernameLoginInfoTest extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterMethod() {
        helperUi.signOut();
    }

    @Test(description = "The presence of unchangeable fields in the fields for entering personal data.")

    public void changingProfileUsernameLoginInfo() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login();
        helperUi.openProfile();
        Assert.assertFalse(helperUi.getProfilePage().canChangeField(PROFILE_USERNAME_INPUT.getValue()), "You can change Username");
        Assert.assertFalse(helperUi.getProfilePage().canChangeField(PROFILE_PASSWORD_INPUT.getValue()), "You can change Password");
        Assert.assertFalse(helperUi.getProfilePage().canChangeField(PROFILE_PHONE_NUMBER_INPUT.getValue()), "You can change Phone Number");
        Assert.assertTrue(helperUi.getProfilePage().canChangeField(PROFILE_FIRSTNAME_INPUT.getValue()), "You can't change Username");
        Assert.assertTrue(helperUi.getProfilePage().canChangeField(PROFILE_LASTNAME_INPUT.getValue()), "You can't change Password");
        Assert.assertTrue(helperUi.getProfilePage().canChangeField(PROFILE_EMAIL_INPUT.getValue()), "You can't change Phone Number");
        Assert.assertTrue(helperUi.getProfilePage().canChangeField(PROFILE_COUNTRY_INPUT.getValue()), "You can't change Phone Number");
        Assert.assertTrue(helperUi.getProfilePage().canChangeField(PROFILE_CITY_INPUT.getValue()), "You can't change Phone Number");

    }
}
