package example.ui.pages.elements;

import example.ui.pages.BasePage;
import org.openqa.selenium.WebDriver;


public class UserBox extends BasePage {

    private static final String MENU_BUTTON = "userbox.menu.button";
    private static final String SIGNOUT_BUTTON = "userbox.signout.button";
    public UserBox(WebDriver driver) {
        super(driver);
    }

    public UserBox waitUntilExtendsOut(){
        welActions.waitUntilAppear(SIGNOUT_BUTTON);
        return this;
    }
    public UserBox waitUntilDisappear(){
        welActions.waitUntilDisappear(SIGNOUT_BUTTON);
        return this;
    }


    public boolean isPresentMenuButton(){
        return  welActions.isPresent(MENU_BUTTON);
    }

    public boolean isDisplayedMenuButton(){
        return  welActions.isDisplayed(MENU_BUTTON);
    }
    public UserBox clickMenuButton(){
        welActions.click(MENU_BUTTON);
        return this;
    }

    public UserBox clickSignOutButton(){
        welActions.click(SIGNOUT_BUTTON);
        return this;
    }

}
