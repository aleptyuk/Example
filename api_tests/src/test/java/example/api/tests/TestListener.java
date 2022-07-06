package example.api.tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;

import static example.logger.LogMessages.*;


@Slf4j
public class TestListener extends TestListenerAdapter {
    @Override
    public void onStart(ITestContext result) {
        //Before class
        log.info(MSG_LINE);
        String className = result.getAllTestMethods().length > 0 ? result.getAllTestMethods()[0].getInstance().getClass().toString() : UNDEFINED;
        log.info("START CLASS: {}", className);
        log.info(MSG_LINE);
        super.onStart(result);
    }

    @Override
    public void onFinish(ITestContext result) {
        //After class
        log.info(MSG_LINE);
        String className = result.getAllTestMethods().length > 0 ? result.getAllTestMethods()[0].getInstance().getClass().toString() : UNDEFINED;
        log.info("CLASS FINISHED: {}", className);
        log.info("Passed tests: {}", result.getPassedTests().size());
        log.info("Failed tests: {}", result.getFailedTests().size());
        log.info("Skipped tests: {}", result.getSkippedTests().size());
        log.info(MSG_LINE);
        super.onFinish(result);
    }

    public void onTestFailure(ITestResult result) {
        logTestResult(result, null);
        super.onTestFailure(result);
    }

    public void onTestSkipped(ITestResult result) {
        logTestResult(result, null);
        super.onTestSkipped(result);
    }

    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        logTestResult(result, null);
    }

    public void onTestStart(ITestResult result) {
        log.info(MSG_LINE);
        log.info("START TEST: {}", result.getMethod().getMethodName());
        log.info(MSG_LINE);
        super.onTestStart(result);
    }


    private void logTestResult(ITestResult testResult, File screenshot) {
        //TODO Implement testrail ids
        printTestResult(testResult, "Unknown id");
    }

    public void printTestResult(ITestResult testResult, String id) {
        String testStatus = getStatus(testResult);
        log.info(MSG_LINE);
        log.info("API: Test case #" + id + "  '" + testResult.getName() + "' is " + testStatus);
        if (testResult.getThrowable() != null)
            log.info(testResult.getThrowable().getMessage());
        log.info(MSG_LINE);
    }

    public String getStatus(ITestResult testResult) {
        switch (testResult.getStatus()) {
            case ITestResult.SUCCESS:
                return STATUS_PASSED;
            case ITestResult.FAILURE:
                return STATUS_FAILED;
            case ITestResult.SKIP:
                return STATUS_SKIPPED;
            default:
                return STATUS_RETESTED;
        }
    }
}

