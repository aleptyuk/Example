package example.api.controllers;

import example.api.constants.Headers;
import example.api.identities.authentication.AuthToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static example.api.constants.EndPoints.BEARER;
import static example.api.constants.EndPoints.USER_SERVICE;
import static example.config.TestConfigProvider.TARGET_URL;
import static org.apache.http.HttpHeaders.*;

@Slf4j
@Data
public class ApiRequest {


    protected io.restassured.mapper.ObjectMapper objectMapper;
    protected AuthToken authToken;
    protected RequestSpecification requestSpec;
    protected Response response;

    protected String getBasePath() {
        return USER_SERVICE;
    }

    public ApiRequest() {
        this.objectMapper = initObjectMapper();
    }

    public ApiRequest(AuthToken authToken) {
        this.objectMapper = initObjectMapper();
        this.authToken = authToken;

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(TARGET_URL)
                .setBasePath(getBasePath())
                .addHeader(CONTENT_TYPE, Headers.APPLICATION_JSON_UTF8)
                .addHeader(HOST, Headers.getHost())
                .addHeader(AUTHORIZATION, BEARER + authToken.getAccessToken())
                .setRelaxedHTTPSValidation()
                .build();
    }

    private static io.restassured.mapper.ObjectMapper initObjectMapper() {
        return new Jackson2Mapper(((type, charset) -> {
            com.fasterxml.jackson.databind.ObjectMapper om = new ObjectMapper().findAndRegisterModules();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om;
        }));
    }

    public ApiRequest logResponse() {
        log.info(getResponse().getBody().asString());
        return this;
    }
}
