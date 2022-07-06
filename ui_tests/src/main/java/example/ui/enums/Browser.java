package example.ui.enums;

public enum Browser {
    REMOTE("remote"),
    FIREFOX("firefox"),
    CHROME("chrome"),
    IE("ie");

    private String browserType;

    Browser(String browserType) {
        this.browserType = browserType;
    }

    public String getValue() {
        return browserType;
    }
}
