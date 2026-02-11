package com.reportportal.base;

import com.reportportal.api.config.TestConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseApiTest {

    protected static final Logger log =
            LoggerFactory.getLogger(BaseApiTest.class);

    protected static String BASE_URL;
    protected static String PROJECT;
    protected static String API_KEY;

    @BeforeAll
    static void setup() {
        BASE_URL = TestConfig.apiUrl();
        PROJECT = TestConfig.project();
        API_KEY = TestConfig.apiKey();

        log.info("Setting up RestAssured");
        log.info("Base URL: {}", BASE_URL);
        log.info("Project: {}", PROJECT);
        log.info("API_KEY is null? {}", API_KEY == null);
        log.info("API_KEY length: {}", API_KEY != null ? API_KEY.length() : 0);
        log.info("API_KEY value: {}", API_KEY);

        if (API_KEY == null || API_KEY.isBlank()) {
            throw new RuntimeException("API_KEY is not set. Check environment variables or application.properties");
        }


        RestAssured.baseURI = BASE_URL;

        RestAssured.requestSpecification = RestAssured
                .given()
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json");
    }
}