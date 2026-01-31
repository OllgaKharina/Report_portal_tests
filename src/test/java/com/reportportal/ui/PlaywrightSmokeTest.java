package com.reportportal.ui.tests.tests;

import com.reportportal.base.BaseUiTest;
import com.reportportal.ui.tests.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PlaywrightSmokeTest extends BaseUiTest {

    @Test
    void shouldLoginToReportPortal() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.open();
        loginPage.login();

        assertThat(loginPage.getWelcomeMessageLocator()).isVisible();
    }
}