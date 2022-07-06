package example.api.constants;

import static example.config.TestConfigProvider.TARGET_URL;
import static example.api.constants.EndPoints.SLASH;


public final class Headers {

    public static final String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";

    public static String getHost() {
        return TARGET_URL.split(SLASH)[2];
    }
}
