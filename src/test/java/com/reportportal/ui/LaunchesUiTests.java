package com.reportportal.ui;

import com.reportportal.base.BaseUiTest;
import com.reportportal.ui.pages.LaunchesPage; // Импортируем новую страницу
import com.reportportal.ui.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Tag;

@Tag("UI")
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
        launchesPage.waitForAtLeastOneLaunch();

        assertTrue(
                launchesPage.hasAtLeastOneLaunch(),
                "В списке Launches должен быть хотя бы один запуск"
        );
    }

    @Test
    void shouldExpandLaunchAndShowBreadcrumb() {
        String launchName = launchesPage.getFirstLaunchName();

        launchesPage.openFirstLaunch();

        assertTrue(
                launchesPage.isBreadcrumbContainsLaunchName(launchName),
                "В breadcrumb должно отображаться имя выбранного запуска"
        );
    }

    @Test
    void shouldDisplayLaunchesTableHeaderWithMainColumns() {
        assertTrue(
                launchesPage.hasMainTableColumns(),
                "В таблице Launches должны отображаться основные колонки"
        );
    }
}