package example.ui.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static example.ui.enums.Locators.*;


@Getter
public class CreateNewPlaylistPage extends MainPage {


    public CreateNewPlaylistPage(WebDriver driver) {
        super(driver);
    }

    public CreateNewPlaylistPage clickSaveButton() {
        welActions.click(SAVE_BUTTON.getValue());
        return this;
    }

    public CreateNewPlaylistPage inputPlaylistName(String value) {
        welActions.input(PLAYLIST_NAME_INPUT.getValue(), value);
        return this;
    }

    public CreateNewPlaylistPage inputDescription(String value) {
        welActions.input(DESCRIPTION_INPUT.getValue(), value);
        return this;
    }

    public CreateNewPlaylistPage inputPicture(File file) {
        welActions.inputFilePath(PICTURE_INPUT.getValue(), file.getPath());
        return this;
    }

    public void scrollDownToAddTracksText() {
        scrollDown(welActions.getElement(ADD_TRACK_TEXT.getValue()));
    }
}