package example.ui.tests.profile;


import example.ui.tests.BaseUiTest;
import example.ui.utils.Base;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


@Slf4j

public class AddAndChangeAvatarInProfileTest extends BaseUiTest {
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.signOut();
    }

    @Test(description = "Adding / changing an avatar in a profile")
    public void addAndChangeAvatarInProfile(){
        helperUi.createUser(Base.PASSWORD, Base.LOGIN_PHONE, Base.COUNTRY)
                .openLoginPage()
                .login();
        helperUi.openProfile();
        helperUi.openAvatarChangeWindow();
        helperUi.uploadAvatarJpg();
        helperUi.saveAvatar();
        assertTrue(helperUi.getProfilePage().getNotificationChangesSaved().waitUntilDisappear(),"No avatar change");
        helperUi.openAvatarChangeWindow();
        helperUi.uploadAvatarJpeg();
        helperUi.saveAvatar();
        assertTrue(helperUi.getProfilePage().getNotificationChangesSaved().waitUntilDisappear(),"No avatar change");
        helperUi.openAvatarChangeWindow();
        helperUi.uploadAvatarPng();
        helperUi.saveAvatar();
        assertTrue(helperUi.getProfilePage().getNotificationChangesSaved().waitUntilDisappear(),"No avatar change");
    }
}
