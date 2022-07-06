package example.ui.tests.profile;


import example.identities.User;
import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import example.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static example.utils.RandomUtils.generateName;
import static org.testng.Assert.assertTrue;


@Slf4j

public class ChangeProfileEmailTest extends BaseUiTest {


    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterMethod() {
        helperUi.signOut();
    }


    @Test(description = "Change user e-mail")
    public void changeEmailInProfile() {
        String email = RandomUtils.generateEmail();
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login();

        helperUi.openProfile();
        helperUi.deletePreviousInputNewEmailProfile(email);

        helperUi.clickSaveButtonProfile();
        Assert.assertTrue(helperUi.getProfilePage().isDisplayedProfilePage(), "Enter don't valid email");
        helperUi.signOut();

        User user = User.builder()
                .username(generateName())
                .pass(Base.PASSWORD)
                .phone(Base.LOGIN_PHONE)
                .email(email)
                .build();
        helperUi.openLoginPage()
                .loginViaEmail(user);
        assertTrue(helperUi.getExplorePage().isDisplayedExplorePage(), "Explore page is not opened");
    }
}


