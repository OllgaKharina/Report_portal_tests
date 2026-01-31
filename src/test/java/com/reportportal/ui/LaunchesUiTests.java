package com.reportportal.ui.tests.tests;

import com.reportportal.base.BaseUiTest;
import com.reportportal.ui.tests.pages.LaunchesPage; // Импортируем новую страницу
import com.reportportal.ui.tests.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LaunchesUiTests extends BaseUiTest {

    private LaunchesPage launchesPage;

    @BeforeEach
    void loginAndOpenLaunches() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.open();
        loginPage.login();

        launchesPage = new LaunchesPage(page);
        launchesPage.waitForActiveLaunchesLink();
    }

    @Test
    void shouldOpenLaunchesPageAfterLogin() {
        assertTrue(
                launchesPage.isLaunchesLinkVisible(),
                "После логина пользователь должен находиться на странице Launches"
        );
    }

    @Test
    void shouldDisplayAtLeastOneLaunch() {
        launchesPage.launchNameLinks().first().waitFor();

        assertTrue(
                launchesPage.launchNameLinks().count() > 0,
                "В списке Launches должен быть хотя бы один запуск"
        );
    }

    @Test
    void shouldExpandLaunchAndShowBreadcrumb() {
        launchesPage.launchNameLinks().first().waitFor();
        var firstLaunchLink = launchesPage.launchNameLinks().first();
        String launchName = firstLaunchLink.innerText();

        firstLaunchLink.click();

        var breadcrumb = launchesPage.breadcrumbLaunchName();
        breadcrumb.waitFor();

        assertTrue(
                breadcrumb.innerText().contains(launchName),
                "В breadcrumb должно отображаться имя выбранного запуска"
        );
    }

    @Test
    void shouldDisplayLaunchesTableHeaderWithMainColumns() {
        var gridHeader = launchesPage.gridHeader();

        assertAll("Проверка заголовков таблицы",
                () -> assertThat(gridHeader).containsText("name"),
                () -> assertThat(gridHeader).containsText("start"),
                () -> assertThat(gridHeader).containsText("total"),
                () -> assertThat(gridHeader).containsText("passed"),
                () -> assertThat(gridHeader).containsText("failed"),
                () -> assertThat(gridHeader).containsText("skipped")
        );
    }

}