package example.config;

import org.aeonbits.owner.ConfigFactory;

public class AppConfigProvider {
    public static final int MIN_PASS_LENGTH = 8;


    private static AppConfig config;

    private AppConfigProvider() {
        throw new IllegalStateException("AppConfigProvider should never be instantiated");
    }

    public static AppConfig get() {
        if (config == null) {
            config = ConfigFactory.create(AppConfig.class, System.getProperties());
        }
        return config;
    }
}
