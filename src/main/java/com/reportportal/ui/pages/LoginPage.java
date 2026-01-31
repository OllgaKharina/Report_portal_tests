package com.reportportal.ui.tests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import com.reportportal.config.TestConfig;

public class LoginPage extends BasePage {

    private static final String WELCOME_MESSAGE =
            "text=Your are on the public Demo Account.";

    public Locator loginButton() {
        return locator("button[type='submit']");
    }

    public Locator getWelcomeMessageLocator() {
        return page.locator(WELCOME_MESSAGE);
    }

    public LoginPage(Page page) {
        super(page);
    }

    public void open() {
        page.navigate(TestConfig.uiUrl(), new Page.NavigateOptions()
                .setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
    }

    public void login() {
        loginButton().waitFor();
        loginButton().click();
    }

}
