package com.reportportal.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseApiTest {

    protected static final Logger log =
            LoggerFactory.getLogger(BaseApiTest.class);

    protected static final String BASE_URL =
            "https://demo.reportportal.io/api/v1";

    protected static final String PROJECT =
            "ollgakharina_personal";

    protected static final String API_KEY =
            "Peport-portal-API-Tests-key_WpNtsjIkS5u4DJhyBAbZZDLzyXhMD2kz2aJ83RZxk05R9BPcjOprNshfDiETa-X2";

    @BeforeAll
    static void setup() {
        log.info("Setting up RestAssured base configuration");

        RestAssured.baseURI = BASE_URL;

        RestAssured.requestSpecification = RestAssured
                .given()
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json");

        log.info("Base URI set to {}", BASE_URL);
    }
}


