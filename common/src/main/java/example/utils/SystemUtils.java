package example.utils;

public class SystemUtils {
    private static String operationSystem = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        return (operationSystem.indexOf("win") >= 0);
    }

    public static boolean isUnix() {
        return (operationSystem.indexOf("nix") >= 0 || operationSystem.indexOf("nux") >= 0 || operationSystem.indexOf("aix") >= 0);
    }
}
