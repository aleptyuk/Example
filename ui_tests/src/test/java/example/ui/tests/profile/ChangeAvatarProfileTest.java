package example.ui.tests.profile;

import example.ui.tests.BaseUiTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static example.config.TestDataProvider.AVATAR_FILE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;



public class ChangeAvatarProfileTest extends BaseUiTest {

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        helperUi.openLoginPage();
        helperUi.signUpViaPhoneBelarus();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        helperUi.deleteUser();
    }

    @Test(description = "Pop-up slider when hovering over the user's avatar with the caption Change avatar")
    public void checkPopupWhenHoverOverAvatar() {
        helperUi.getExplorePage()
                .waitUntilDisappearNotification();
        helperUi.openProfile()
                .setAvatar(AVATAR_FILE.getAbsoluteFile());
        helperUi.getProfilePage()
                .moveToAvatar();
        assertTrue(helperUi.getProfilePage().isDisplayedChangeAvatarImageProfilePage());
        assertEquals(helperUi.getProfilePage().isDisplayedChangeAvatarTextProfilePage(), "Change avatar");
    }

}
