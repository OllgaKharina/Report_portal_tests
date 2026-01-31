package com.reportportal.api.tests;

import com.reportportal.base.BaseApiTest;
import com.reportportal.api.utils.LaunchApiUtils;
import com.reportportal.api.utils.LaunchData;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class LaunchesApiTest extends BaseApiTest {

    private LaunchData launchToCleanup;

    @AfterEach
    void cleanUp() {
        if (launchToCleanup != null) {
            LaunchApiUtils.deleteLaunch(PROJECT, launchToCleanup.id);
            launchToCleanup = null;
        }
    }

    // GET LIST
    @Test
    void shouldGetLaunchesList() {
        launchToCleanup = LaunchApiUtils.createLaunch(PROJECT);

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

        assertAll(
                () -> assertThat(launches)
                        .isNotNull()
                        .isNotEmpty(),
                () -> assertThat(launches.get(0))
                        .containsKeys("id", "name")
        );
    }

    // GET BY UUID
    @Test
    void shouldGetLaunchByUuid() {
        launchToCleanup = LaunchApiUtils.createLaunch(PROJECT);

        Response response =
                given()
                        .when()
                        .get("/" + PROJECT + "/launch/" + launchToCleanup.uuid)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        String uuid = response.path("uuid");
        String name = response.path("name");

        assertAll(
                () -> assertThat(uuid)
                        .isEqualTo(launchToCleanup.uuid),
                () -> assertThat(name)
                        .isEqualTo("api-launch-test")
        );
    }

    // UPDATE
    @Test
    void shouldUpdateLaunch() {
        launchToCleanup = LaunchApiUtils.createLaunch(PROJECT);

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
                .put("/" + PROJECT + "/launch/" + launchToCleanup.id + "/update")
                .then()
                .statusCode(200);

        Response response =
                given()
                        .when()
                        .get("/" + PROJECT + "/launch/" + launchToCleanup.uuid)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        String description = response.path("description");
        List<Map<String, String>> attributes = response.path("attributes");

        assertAll(
                () -> assertThat(description)
                        .isEqualTo("updated via api test"),
                () -> assertThat(attributes)
                        .isNotEmpty(),
                () -> assertThat(attributes.get(0).get("key"))
                        .isEqualTo("env"),
                () -> assertThat(attributes.get(0).get("value"))
                        .isEqualTo("test")
        );
    }

    // DELETE
    @Test
    void shouldDeleteLaunch() {
        LaunchData launch = LaunchApiUtils.createLaunch(PROJECT);

        LaunchApiUtils.deleteLaunch(PROJECT, launch.id);

        launchToCleanup = null;
    }

    // PAGING & SORTING
    @Test
    void shouldGetLaunchesWithPagingAndSorting() {
        launchToCleanup = LaunchApiUtils.createLaunch(PROJECT);

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

        assertAll(
                () -> assertThat(launches)
                        .hasSizeLessThanOrEqualTo(3),
                () -> assertThat(launches.get(0))
                        .containsKey("startTime")
        );
    }

    // 404
    @Test
    void shouldReturn404ForNonExistingLaunch() {
        given()
                .when()
                .get("/" + PROJECT + "/launch/non-existing-uuid")
                .then()
                .statusCode(404);
    }
}


