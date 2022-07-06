package example.ui.pages;

import example.ui.pages.elements.MainMenu;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class MainPage extends BasePage {

    private static final String SEARCH_INPUT = "mainpage.search.input";
    private static final String USER_AVATAR = "mainpage.user.avatar";
    private static final String USER_NAME = "mainpage.user.name";
    private static final String ACCOUNT_DROPDOWN = "mainpage.account.dropdown";
    private static final String PROF_BUTTON = "mainpage.account.profButton";


    @Getter
    private static MainMenu mainMenu;

    public MainPage(WebDriver driver) {
        super(driver);
        mainMenu = new MainMenu(this.driverManager.getDriver());
    }
}
