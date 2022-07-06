package example.ui.pages.elements;

import example.ui.pages.MainPage;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class UploadNewTrack extends MainPage {

    private static final String COVER_FILE_BUTTON = "uploadmusictrack.cover.button";
    private static final String SAVE_BUTTON = "uploadmusictrack.save.button";
    private static final String TRACK_TITLE_INPUT = "uploadmusictrack.track.title.input";
    private static final String TRACK_AUTHOR_INPUT = "uploadmusictrack.track.author.input";
    private static final String TRACK_ALBUM_INPUT = "uploadmusictrack.track.album.input";
    private static final String TRACK_GENRE_INPUT = "uploadmusictrack.track.genre.input";
    private static final String POP_UP_AUTHOR = "uploadmusictrack.track.popup.author";
    private static final String POP_UP_GENRE = "uploadmusictrack.track.popup.genre";
    private static final String INVALID_DATA_ERORR = "uploadmusictrack.track.invaliddata";

    public UploadNewTrack(WebDriver driver) {
        super(driver);
    }

    public UploadNewTrack inputTrackAuthor(String value) {
        welActions.deletePreviousInputNew(TRACK_AUTHOR_INPUT, value);
        welActions.click(POP_UP_AUTHOR);
        return this;
    }
    public UploadNewTrack inputTrackTitle(String value) {
        welActions.deletePreviousInputNew(TRACK_TITLE_INPUT, value);
        return this;
    }

    public UploadNewTrack inputTrackAlbum(String value) {
        welActions.deletePreviousInputNew(TRACK_ALBUM_INPUT, value);
        return this;
    }

    public UploadNewTrack inputTrackGenre(String value) {
        welActions.deletePreviousInputNew(TRACK_GENRE_INPUT, value);
        welActions.click(POP_UP_GENRE);
        return this;
    }

    public UploadNewTrack dropFiles(File file) {
        welActions.inputFilePath(COVER_FILE_BUTTON, file.getPath());
        return this;
    }

    public UploadNewTrack clickSaveButton() {
        welActions.click(SAVE_BUTTON);
        return this;
    }

    public boolean isDisplayedErrorAboutInvalidData(){
        return welActions.isDisplayed(INVALID_DATA_ERORR);
    }
}

