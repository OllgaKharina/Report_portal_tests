package com.reportportal.ui;

import com.reportportal.base.BaseUiTest;
import com.reportportal.ui.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlaywrightSmokeTest extends BaseUiTest {

    @Test
    void shouldLoginToReportPortal() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.open();
        loginPage.login();

        assertTrue(loginPage.isWelcomeMessageVisible());
    }
}