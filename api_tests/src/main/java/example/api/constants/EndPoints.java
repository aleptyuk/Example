package example.api.constants;


import org.apache.commons.lang3.StringUtils;

public final class EndPoints {
    public static final String USER_SERVICE = "";
    public static final String LOGIN = USER_SERVICE + "/login";
    public static final String REGISTRATION = USER_SERVICE + "/registration";
    public static final String USER_PROFILE = REGISTRATION + "/user-profile";
    public static final String NEW = "/new";
    public static final String BEARER = "Bearer" + StringUtils.SPACE;
    public static final String SLASH = "/";
    public static final String PASSWORD = "/password";
}
