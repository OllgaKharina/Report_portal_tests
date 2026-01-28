package com.reportportal.ui.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.options.WaitUntilState;

public class LoginPage {

    private final Page page;

    private static final String URL = "https://demo.reportportal.io";
    private static final String LOGIN_BUTTON = "button[type='submit']";
    // Используем уникальный текст на странице как локатор
    private static final String WELCOME_MESSAGE_TEXT = "text=Your are on the public Demo Account.";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate(URL, new Page.NavigateOptions()
                .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
                .setTimeout(60_000));

        page.waitForSelector(LOGIN_BUTTON, new Page.WaitForSelectorOptions()
                .setTimeout(60_000));
    }

    public void login() {
        page.click(LOGIN_BUTTON);
    }

    public boolean isUserLoggedIn() {
        try {
            page.waitForSelector(WELCOME_MESSAGE_TEXT, new Page.WaitForSelectorOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(30_000));
            return true;
        } catch (PlaywrightException e) {

            return false;
        }
    }
}