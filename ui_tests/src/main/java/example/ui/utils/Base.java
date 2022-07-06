package example.ui.utils;

public class Base {
 public static String LOGIN_PHONE = PropertiesRead.getPropertyValue("user.info.login");
 public static String PASSWORD = PropertiesRead.getPropertyValue("user.info.password");
 public static String COUNTRY = PropertiesRead.getPropertyValue("user.info.country");
 public static String NEW_LOGIN_PHONE = PropertiesRead.getPropertyValue("userNew.info.login");
 public static String NEW_PASSWORD = PropertiesRead.getPropertyValue("userNew.info.password");
 public static String MODIFIED_PASSWORD = PropertiesRead.getPropertyValue("userNew.info.modified.password");
 public static String NEW_COUNTRY = PropertiesRead.getPropertyValue("userNew.info.country");
 public static String VERIFICATION_CODE=PropertiesRead.getPropertyValue("user.info.verification.code");
 public static String FIRST_NAME = PropertiesRead.getPropertyValue("user.info.first.name");
 public static String LAST_NAME = PropertiesRead.getPropertyValue("user.info.last.name");
 }
