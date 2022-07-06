package example.api.tests.user;

import example.api.tests.BaseApiTest;
import org.testng.annotations.Test;

public class UserTest extends BaseApiTest {

    @Test(description = "Method for get Token and showing table UserProfileTable", groups = {"Regression"})
    public void getToken() {
        System.out.println(helperApi.getAuthToken());
    }
}
