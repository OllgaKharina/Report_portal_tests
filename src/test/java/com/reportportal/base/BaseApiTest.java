package com.reportportal.base;

import com.reportportal.config.TestConfig;
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

        RestAssured.baseURI = BASE_URL;

        RestAssured.requestSpecification = RestAssured
                .given()
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json");
    }
}