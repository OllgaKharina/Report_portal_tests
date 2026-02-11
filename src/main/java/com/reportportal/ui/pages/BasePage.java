package com.reportportal.ui.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {

    protected final Page page;
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected BasePage(Page page) {
        this.page = page;
    }

    protected Locator locator(String selector) {
        return page.locator(selector);
    }

    protected void click(String selector) {
        log.info("Clicking element: {}", selector);
        locator(selector).click();
    }

    protected String getText(String selector) {
        log.info("Getting text from element: {}", selector);
        return locator(selector).innerText();
    }

    protected void waitForVisible(String selector) {
        log.info("Waiting for element to be visible: {}", selector);
        locator(selector).waitFor();
    }
}
