package example.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class MyTrackListPage extends MainPage {

    private static final String DISPLAYED_MYTRACKLISTPAGE = "mytracklistpage.displayed.mytracklistpage";

    public MyTrackListPage(WebDriver driver) {
        super(driver);
    }
    public boolean isDisplayedMyTracklistPage() {
        return welActions.isDisplayed(DISPLAYED_MYTRACKLISTPAGE);
    }
}
