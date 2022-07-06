package example.api.controllers;

import example.api.identities.authentication.AuthRequest;
import example.api.identities.authentication.AuthToken;


import example.identities.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static example.api.constants.EndPoints.*;
import static example.config.TestConfigProvider.TARGET_URL;
import static io.restassured.RestAssured.given;

@Data
@Slf4j
public class AuthenticationController extends ApiRequest {
    public AuthenticationController(AuthToken authToken) {
        super(authToken);
    }

    public AuthToken authenticate(User user) {
        requestSpec.baseUri(TARGET_URL)
                .basePath(LOGIN);
        AuthRequest request = AuthRequest.builder()
                .login(user.getPhone())
                .password(user.getPass())
                .build();
        return given().spec(requestSpec)
                .body(request)
                .post()
                .as(AuthToken.class, objectMapper);
    }
}
