package example.ui.enums;

public enum NotificationMessage {
    UNSUCCESSFUL_AUTH("Unsuccesfull authorization"),
    WRONG_LOGIN("Wrong login or password"),
    PHONE_EXIST("Phone number is already registered!"),
    PHONE_INVALID_HINT("Phone number is not valid"),
    EMAIL_INVALID_HINT("Mail is already registered!"),
    NOTHING_FOUND_HINT("Nothing found"),
    REMOVE_MY_PLAYLIST("Playlist was removed"),
    PLAYLIST_SAVED("Playlist saved"),
    PASSWORD_CHANGED("Password successfully changed"),
    SUCCESSFUL_CREATION("Success"),
    CHANGES_SAVED("Changes have been saved"),
    TRACK_UPLOADED("A new track has been uploaded");

    private final String value;

    NotificationMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
