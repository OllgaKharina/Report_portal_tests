package com.reportportal.ui.tests;

import com.reportportal.base.BaseUiTest;
import com.reportportal.ui.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlaywrightSmokeTest extends BaseUiTest {

    @Test
    void shouldLoginToReportPortal() {
        LoginPage loginPage = new LoginPage(page);

        loginPage.open();
        loginPage.login();

        // Проверяем, что пользователь залогинен, ожидая появления текста
        assertTrue(loginPage.isUserLoggedIn(), "Приветственное сообщение должно быть видно после логина.");
    }
}