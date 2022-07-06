package example.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:application.properties"})
public interface AppConfig extends Config {
    @Config.Key("target.url")
    String targetUrl();

    @Key("default.implicitly.wait")
    int implicitlyWait();

    @Key("default.implicitly.polls")
    int implicitlyPolls();

    @Key("chrome.version")
    String chromeVersion();

    @Key("firefox.version")
    String firefoxVersion();

    @Key("data.base")
    String dataBase();

    @Key("password.data.base")
    String passwordDataBase();

    @Key("user.data.base")
    String userDataBase();



}
