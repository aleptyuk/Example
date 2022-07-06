package example.config;

public final class TestConfigProvider {
    public static final String TARGET_URL = AppConfigProvider.get().targetUrl();
    public static final int WAIT_LOAD_AVATAR_PLAYLIST = 3;
    public static final String CHROME_VERSION=AppConfigProvider.get().chromeVersion();
    public static final String FIREFOX_VERSION=AppConfigProvider.get().firefoxVersion();
    public static final String DATA_BASE=AppConfigProvider.get().dataBase();
    public static final String PASSWORD_DATA_BASE=AppConfigProvider.get().passwordDataBase();
    public static final String USER_DATA_BASE=AppConfigProvider.get().userDataBase();


}
