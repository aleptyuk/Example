package example.api.helpers;

import example.api.controllers.AuthenticationController;
import example.api.constants.Headers;
import example.api.controllers.RegistrationController;
import example.api.identities.authentication.AuthRequest;
import example.api.identities.authentication.AuthToken;
import example.identities.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static example.api.constants.EndPoints.*;
import static example.config.TestConfigProvider.TARGET_URL;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpHeaders.HOST;

@Slf4j
@Data
public class HelperApi {

    private AuthToken authToken;
    private AuthenticationController authController;
    private RegistrationController registrationController;


    public HelperApi(AuthToken authToken) {
        this.authToken = authToken;
        this.authController = new AuthenticationController(this.authToken);
        this.registrationController = new RegistrationController(this.authToken);
    }

    public static HelperApi loginAs(User user) {
        AuthRequest request = AuthRequest.builder()
                .login(user.getPhone())
                .password(user.getPass())
                .build();
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(TARGET_URL)
                .setBasePath(LOGIN)
                .addHeader(CONTENT_TYPE, Headers.APPLICATION_JSON_UTF8)
                .addHeader(HOST, Headers.getHost())
                .setRelaxedHTTPSValidation()
                .build();
        AuthToken authToken = given()
                .spec(requestSpecification)
                .body(request)
                .post()
                .as(AuthToken.class);
        return new HelperApi(authToken);
    }
}
