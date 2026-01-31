package com.reportportal.ui.tests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import com.reportportal.config.TestConfig;
import java.util.List;

public class LaunchesPage extends BasePage {

    public LaunchesPage(Page page) {
        super(page);
    }

    public void open() {
        page.navigate(TestConfig.launchesUrl(), new Page.NavigateOptions()
                .setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
    }

    public Locator activeLaunchesLink() {
        return locator("a[href='#default_personal/launches'][aria-current='true']");
    }

    public void waitForActiveLaunchesLink() {
        activeLaunchesLink().waitFor();
    }

    public boolean isLaunchesLinkVisible() {
        return activeLaunchesLink().isVisible();
    }

    public Locator launchesTable() {
        return locator("div[class*='launchesTable']");
    }

    public Locator filterInput() {
        return locator("input[placeholder='Enter name']");
    }

    public Locator launchByName(String name) {
        return locator("a:has-text('" + name + "')");
    }

    public void openLaunch(String name) {
        launchByName(name).first().click();
    }

    public Locator launchNameLinks() {
        return locator("a[class*='itemInfo__name-link']");
    }

    public Locator gridHeader() {
        return locator("div[class*='gridHeader']");
    }

    public Locator breadcrumbLaunchName() {
        return locator("span[class*='breadcrumb__link-item'] span");
    }

    public List<String> getVisibleLaunchNames() {
        return locator("a[class*='launchName']").allTextContents();
    }

    public boolean isLaunchDetailsOpened() {
        return locator("div[class*='launchInfo']").isVisible();
    }

    public boolean isOpened() {
        return launchesTable().isVisible();
    }
}


