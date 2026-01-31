package com.reportportal.api.utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LaunchApiUtils {

    public static LaunchData createLaunch(String project) {
        Response createResponse =
                given()
                        .body("""
                                {
                                  "name": "api-launch-test",
                                  "startTime": "2026-01-28T10:00:00.000Z",
                                  "mode": "DEFAULT"
                                }
                                """)
                        .when()
                        .post("/" + project + "/launch")
                        .then()
                        .statusCode(201)
                        .extract()
                        .response();

        String uuid = createResponse.path("id");

        Response getResponse =
                given()
                        .when()
                        .get("/" + project + "/launch/" + uuid)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        LaunchData data = new LaunchData();
        data.uuid = uuid;
        data.id = getResponse.path("id");

        return data;
    }

    public static void deleteLaunch(String project, Integer id) {
        given()
                .queryParam("ids", id)
                .when()
                .delete("/" + project + "/launch")
                .then()
                .statusCode(200);
    }
}