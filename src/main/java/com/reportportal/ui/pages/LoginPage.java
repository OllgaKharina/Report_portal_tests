package com.reportportal.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import com.reportportal.config.TestConfig;

public class LoginPage extends BasePage {

    private static final String WELCOME_MESSAGE_TEXT =
            "text=You are on the public Demo Account.";

    private final Locator loginButton;
    private final Locator welcomeMessage;

    public LoginPage(Page page) {
        super(page);
        this.loginButton = locator("button[type='submit']");
        this.welcomeMessage = page.locator(WELCOME_MESSAGE_TEXT);
    }

    public void open() {
        page.navigate(
                TestConfig.uiUrl(),
                new Page.NavigateOptions()
                        .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
        );
    }

    public void login() {
        loginButton.waitFor();
        loginButton.click();
    }

    public boolean isWelcomeMessageVisible() {
        welcomeMessage.waitFor();
        return welcomeMessage.isVisible();
    }
}


