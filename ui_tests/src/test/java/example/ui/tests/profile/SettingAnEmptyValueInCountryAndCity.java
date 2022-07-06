package example.ui.tests.profile;

import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SettingAnEmptyValueInCountryAndCity extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.signOut();
    }


    @Test(description = "Setting an empty value in the fields Country and City")
    public void chekingValue() {
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login();
        helperUi.openProfile();
        assertEquals(helperUi.getProfilePage().getCity(), "");
        assertEquals(helperUi.getProfilePage().getCountry(), "");
    }
}
