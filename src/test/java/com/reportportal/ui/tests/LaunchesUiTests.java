package com.reportportal.ui.tests;

import com.reportportal.base.BaseUiTest;
import com.reportportal.ui.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LaunchesUiTests extends BaseUiTest {

    @BeforeEach
    void loginAndOpenLaunches() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.open();
        loginPage.login();

        page.locator(
                "a[href='#default_personal/launches'][aria-current='true']"
        ).waitFor();
    }

    @Test
    void shouldOpenLaunchesPageAfterLogin() {
        boolean launchesMenuIsActive = page.locator(
                "a[href='#default_personal/launches'][aria-current='true']"
        ).isVisible();

        assertTrue(
                launchesMenuIsActive,
                "После логина пользователь должен находиться на странице Launches"
        );
    }

    @Test
    void shouldDisplayAtLeastOneLaunch() {
        var launchLinks = page.locator("a[class*='itemInfo__name-link']");

        launchLinks.first().waitFor();

        assertTrue(
                launchLinks.count() > 0,
                "В списке Launches должен быть хотя бы один запуск"
        );
    }

    @Test
    void shouldExpandLaunchAndShowBreadcrumb() {
        // Arrange
        var firstLaunchLink = page.locator("a[class*='itemInfo__name-link']").first();

        String launchName = firstLaunchLink.innerText();

        firstLaunchLink.click();

        var breadcrumbLaunchName = page.locator(
                "span[class*='breadcrumb__link-item'] span"
        );

        breadcrumbLaunchName.waitFor();

        assertTrue(
                breadcrumbLaunchName.innerText().contains(launchName),
                "В breadcrumb должно отображаться имя выбранного запуска"
        );
    }

    @Test
    void shouldDisplayLaunchesTableHeaderWithMainColumns() {
        var gridHeader = page.locator("div[class*='gridHeader']");

        assertThat(gridHeader).containsText("name");
        assertThat(gridHeader).containsText("start");
        assertThat(gridHeader).containsText("total");
        assertThat(gridHeader).containsText("passed");
        assertThat(gridHeader).containsText("failed");
        assertThat(gridHeader).containsText("skipped");
    }

}

