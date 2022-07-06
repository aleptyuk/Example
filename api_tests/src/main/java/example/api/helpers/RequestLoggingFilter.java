package example.api.helpers;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.commons.lang3.Validate;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.filter.log.LogDetail.STATUS;

public class RequestLoggingFilter implements Filter {

    private static final boolean SHOW_URL_ENCODED_URI = true;
    private final LogDetail logDetail;
    private final PrintStream stream;
    private final boolean shouldPrettyPrint;
    private final boolean showUrlEncodedUri;
    private final Set<String> blacklistedHeaders;
    private final Set<LogDetail> logDetailSet = new HashSet<>();

    public RequestLoggingFilter() {
        this(ALL, System.out);
    }

    public RequestLoggingFilter(LogDetail logDetail) {
        this(logDetail, System.out);
    }

    public RequestLoggingFilter(PrintStream printStream) {
        this(ALL, printStream);
    }

    public RequestLoggingFilter(LogDetail logDetail, PrintStream stream) {
        this(logDetail, true, stream);
    }

    public RequestLoggingFilter(LogDetail logDetail, boolean shouldPrettyPrint, PrintStream stream) {
        this(logDetail, shouldPrettyPrint, stream, SHOW_URL_ENCODED_URI);
    }

    public RequestLoggingFilter(LogDetail logDetail, boolean shouldPrettyPrint, PrintStream stream, boolean showUrlEncodedUri) {
        this(logDetail, shouldPrettyPrint, stream, showUrlEncodedUri, Collections.emptySet());
    }

    public RequestLoggingFilter(LogDetail logDetail, boolean shouldPrettyPrint, PrintStream stream, boolean showUrlEncodedUri, Set<String> blacklistedHeaders) {
        Validate.notNull(stream, "Print stream cannot be null");
        Validate.notNull(blacklistedHeaders, "Blacklisted headers cannot be null");
        Validate.notNull(logDetail, "Log details cannot be null");
        if (logDetail == STATUS) {
            throw new IllegalArgumentException(String.format("%s is not a valid %s for a request.", STATUS, LogDetail.class.getSimpleName()));
        }
        this.stream = stream;
        this.logDetail = logDetail;
        this.blacklistedHeaders = new HashSet<>(blacklistedHeaders);
        this.shouldPrettyPrint = shouldPrettyPrint;
        this.showUrlEncodedUri = showUrlEncodedUri;
    }

    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        String uri = requestSpec.getURI();
        if (!showUrlEncodedUri) {
            uri = UrlDecoder.urlDecode(uri, Charset.forName(requestSpec.getConfig().getEncoderConfig().defaultQueryParameterCharset()), true);
        }

        if (logDetailSet.isEmpty()) {
            RequestPrinter.print(requestSpec, requestSpec.getMethod(), uri, logDetail, blacklistedHeaders, stream, shouldPrettyPrint);
        } else {
            RequestPrinter.print(requestSpec, requestSpec.getMethod(), uri, logDetail, blacklistedHeaders, stream, shouldPrettyPrint);
        }
        return ctx.next(requestSpec, responseSpec);
    }

    public static RequestLoggingFilter logRequestTo(PrintStream stream) {
        return new RequestLoggingFilter(stream);
    }

    public static RequestLoggingFilter with(LogDetail... logDetails) {
        return new RequestLoggingFilter().addLog(logDetails);
    }

    private RequestLoggingFilter addLog(LogDetail... logDetails) {
        logDetailSet.addAll(Arrays.asList(logDetails));
        return this;
    }
}
