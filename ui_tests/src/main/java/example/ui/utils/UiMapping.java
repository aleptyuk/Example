package example.ui.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
public class UiMapping {
    private static volatile UiMapping instance;
    private static final Properties properties = new Properties();
    private static final Map<String, String> locatorMap = new HashMap<>();

    private UiMapping() {
        properties.forEach((key, value) -> locatorMap.put((String) key, (String) value));
    }

    public static synchronized UiMapping getInstance() {
        if (instance == null) {
            instance = new UiMapping();
            return instance;
        }
        return instance;
    }

    public static String getValue(String key) {
        return locatorMap.get(key);
    }

    public static By ui(String key) {
        String[] partsOfLocators = new String[0];
        try {
            partsOfLocators = locatorMap.get(key).split("\"");
        } catch (NullPointerException e) {
            log.error("locator {} is not specified in properties", key, e);
            return null;
        }

        String findMethod = StringUtils.EMPTY;
        String locatorValue = StringUtils.EMPTY;
        try {
            findMethod = partsOfLocators[0];
            locatorValue = partsOfLocators[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error(e.getMessage(), e);
        }

        switch (findMethod) {
            case "id":
                return By.id(locatorValue);
            case "css":
                return By.cssSelector(locatorValue);
            case "name":
                return By.name(locatorValue);
            default:
                return By.xpath(locatorValue);
        }
    }
}
