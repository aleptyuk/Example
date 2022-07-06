package example.ui.pages.elements;


import example.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class AccountDropdown extends BasePage {
    private static final String PROFILE_BUTTON = "explorepage.profile.button";

    public AccountDropdown(WebDriver driver) {
        super(driver);
    }

    public void clickProfileButton() {
        welActions.click(PROFILE_BUTTON);
    }

    public void waitUntilAppearProfileButton() {
        welActions.waitUntilAppear(PROFILE_BUTTON);
    }

}
