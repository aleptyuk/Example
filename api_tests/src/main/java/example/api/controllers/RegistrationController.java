package example.api.controllers;

import example.api.constants.Headers;
import example.api.identities.authentication.AuthToken;
import example.identities.User;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


import static example.api.constants.EndPoints.*;
import static example.config.TestConfigProvider.TARGET_URL;
import static example.utils.RandomUtils.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.HttpHeaders.HOST;

public class RegistrationController extends ApiRequest {

    public RegistrationController(AuthToken authToken) {
        super(authToken);
    }


    @Step("Sign Up")
    public static int signUp(User user) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(TARGET_URL)
                .setBasePath(USER_PROFILE + NEW)
                .addHeader(CONTENT_TYPE, Headers.APPLICATION_JSON_UTF8)
                .addHeader(HOST, Headers.getHost())
                .setRelaxedHTTPSValidation()
                .build();
        return given()
                .spec(requestSpecification)
                .body(user)
                .post()
                .statusCode();
    }

}
