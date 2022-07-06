package example.api.requestSpecifications;

import example.api.constants.Headers;
import example.api.identities.authentication.AuthRequest;
import example.identities.UpdatePasswordRequestDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static example.api.constants.EndPoints.*;
import static example.config.TestConfigProvider.*;
import static org.apache.http.HttpHeaders.*;

public class RequestSpecifications {

    public RequestSpecification setBaseRequestSpecForAuthentication(AuthRequest authRequest) {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri(TARGET_URL)
                .setBasePath(LOGIN)
                .setContentType(ContentType.JSON)
                .addHeader(HOST, Headers.getHost())
                .setRelaxedHTTPSValidation()
                .setBody(authRequest)
                .build();
        return requestSpec;
    }

    public RequestSpecification setRequestSpecificationForUpdatePassword(UpdatePasswordRequestDto updatePasswordRequestDto, String phoneNumber) {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri(TARGET_URL)
                .setBasePath(LOGIN + PASSWORD)
                .setContentType(ContentType.JSON)
                .addHeader(HOST, Headers.getHost())
                .setRelaxedHTTPSValidation()
                .addQueryParam("mobilePhone", phoneNumber)
                .setBody(updatePasswordRequestDto)
                .build();
        return requestSpec;
    }
}