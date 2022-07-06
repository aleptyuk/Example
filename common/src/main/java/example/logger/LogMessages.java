package example.logger;

public class LogMessages {
    public static final String MSG_LINE = "=======================================================================";
    public static final String UNDEFINED = "UNDEFINED";
    public static final String STATUS_PASSED = "PASSED";
    public static final String STATUS_FAILED = "FAILED";
    public static final String STATUS_SKIPPED = "SKIPPED";
    public static final String STATUS_RETESTED = "Need to be retested";

    private LogMessages() {
        throw new IllegalStateException("Utility class");
    }

}
