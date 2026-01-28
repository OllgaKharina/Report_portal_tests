package com.reportportal.api.tests;

import com.reportportal.base.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class LaunchesApiTest extends BaseApiTest {

    private String createdLaunchUuid;
    private Integer createdLaunchId;

    @BeforeEach
    void createLaunch() {
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
                        .post("/" + PROJECT + "/launch")
                        .then()
                        .statusCode(201)
                        .extract()
                        .response();


        createdLaunchUuid = createResponse.path("id");

        Response getResponse =
                given()
                        .when()
                        .get("/" + PROJECT + "/launch/" + createdLaunchUuid)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        createdLaunchId = getResponse.path("id");
    }

    // -------------------- GET LIST --------------------

    @Test
    void shouldGetLaunchesList() {
        Response response =
                given()
                        .queryParam("page.page", 1)
                        .queryParam("page.size", 5)
                        .when()
                        .get("/" + PROJECT + "/launch")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        List<Map<String, Object>> launches = response.path("content");

        assertThat(launches)
                .isNotNull()
                .isNotEmpty();

        assertThat(launches.get(0))
                .containsKeys("id", "name");
    }

    // -------------------- GET BY UUID --------------------

    @Test
    void shouldGetLaunchByUuid() {
        Response response =
                given()
                        .when()
                        .get("/" + PROJECT + "/launch/" + createdLaunchUuid)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        String uuid = response.path("uuid");
        String name = response.path("name");

        assertThat(uuid).isEqualTo(createdLaunchUuid);
        assertThat(name).isEqualTo("api-launch-test");
    }

    // -------------------- UPDATE --------------------

    @Test
    void shouldUpdateLaunch() {

        given()
                .body("""
                {
                  "description": "updated via api test",
                  "attributes": [
                    {
                      "key": "env",
                      "value": "test"
                    }
                  ]
                }
                """)
                .when()
                .put("/" + PROJECT + "/launch/" + createdLaunchId + "/update")
                .then()
                .statusCode(200);

        Response response =
                given()
                        .when()
                        .get("/" + PROJECT + "/launch/" + createdLaunchUuid)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        String description = response.path("description");
        List<Map<String, String>> attributes = response.path("attributes");

        assertThat(description).isEqualTo("updated via api test");
        assertThat(attributes).isNotEmpty();
        assertThat(attributes.get(0).get("key")).isEqualTo("env");
        assertThat(attributes.get(0).get("value")).isEqualTo("test");
    }

    // -------------------- DELETE --------------------

    @Test
    void shouldDeleteLaunch() {

        given()
                .queryParam("ids", createdLaunchId)
                .when()
                .delete("/" + PROJECT + "/launch")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldGetLaunchesWithPagingAndSorting() {

        Response response =
                given()
                        .queryParam("page.page", 1)
                        .queryParam("page.size", 3)
                        .queryParam("page.sort", "startTime,DESC")
                        .when()
                        .get("/" + PROJECT + "/launch")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        List<Map<String, Object>> launches = response.path("content");

        assertThat(launches)
                .as("Должны вернуться запуски с учётом page.size")
                .hasSizeLessThanOrEqualTo(3);

        assertThat(launches.get(0))
                .containsKey("startTime");
    }

    @Test
    void shouldReturn404ForNonExistingLaunch() {

        given()
                .when()
                .get("/" + PROJECT + "/launch/" + "non-existing-uuid")
                .then()
                .statusCode(404);
    }
}


