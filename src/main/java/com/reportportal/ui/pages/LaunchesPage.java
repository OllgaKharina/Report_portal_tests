package com.reportportal.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LaunchesPage extends BasePage {

    private static final String LAUNCH_BY_NAME = "a:has-text('%s')";

    private final Locator activeLaunchesLink;
    private final Locator launchNameLinks;
    private final Locator gridHeader;
    private final Locator breadcrumbLaunchName;


    public LaunchesPage(Page page) {
        super(page);
        this.activeLaunchesLink = locator("a[href='#default_personal/launches'][aria-current='true']");
        this.launchNameLinks = locator("a[class*='itemInfo__name-link']");
        this.gridHeader = locator("div[class*='gridHeader']");
        this.breadcrumbLaunchName = locator("span[class*='breadcrumb__link-item'] span");
    }

    public void waitForActiveLaunchesLink() {
        activeLaunchesLink.waitFor();
    }

    public void waitForAtLeastOneLaunch() {
        launchNameLinks.first().waitFor();
    }

    public boolean hasAtLeastOneLaunch() {
        return launchNameLinks.count() > 0;
    }

    public String getFirstLaunchName() {
        launchNameLinks.first().waitFor();
        return launchNameLinks.first().textContent();
    }

    public void openFirstLaunch() {
        launchNameLinks.first().click();
    }

    public boolean isBreadcrumbContainsLaunchName(String launchName) {
        breadcrumbLaunchName.waitFor();
        return breadcrumbLaunchName.textContent().contains(launchName);
    }

    public boolean isLaunchesLinkVisible() {
        return activeLaunchesLink.isVisible();
    }

    public boolean hasMainTableColumns() {
        gridHeader.waitFor();

        String headerText = gridHeader.textContent();

        return headerText.contains("name")
                && headerText.contains("start")
                && headerText.contains("total")
                && headerText.contains("passed")
                && headerText.contains("failed")
                && headerText.contains("skipped");
    }
}



