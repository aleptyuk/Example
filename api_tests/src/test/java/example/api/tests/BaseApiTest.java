package example.api.tests;

import example.api.data_base.ConnectorUtil;
import example.api.helpers.HelperApi;
import example.identities.User;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.sql.SQLException;

@Slf4j
@Data
@Listeners(TestListener.class)
public class BaseApiTest {

    protected static HelperApi helperApi;


    @BeforeClass(alwaysRun = true)
        public void filters() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite(ITestContext ctx) {
        System.out.println("BeforeSuite");
        log.info("EXECUTING SUITE: {}", ctx.getSuite().getName());
        initHelperApi();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite(ITestContext ctx) throws SQLException {
        log.info("FINISHING SUITE: " + ctx.getSuite().getName());
        helperApi = null;
        ConnectorUtil.closeConnection();
    }

    private static void initHelperApi() {
        User user = User.builder()
                .phone("")
                .pass("")
                .build();
        helperApi = HelperApi.loginAs(user);
    }
}
