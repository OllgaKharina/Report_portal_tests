package com.reportportal.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class LaunchesPage {

    private final Page page;

    private final Locator launchesTable;
    private final Locator filterInput;

    public LaunchesPage(Page page) {
        this.page = page;
        this.launchesTable = page.locator("div[class*='launchesTable']");
        this.filterInput = page.locator("input[placeholder='Enter name']");
    }

    public void open() {
        page.navigate("https://demo.reportportal.io/ui/#default_personal/launches");
    }

    public boolean isOpened() {
        return launchesTable.isVisible();
    }

    public Locator launchByName(String name) {
        return page.locator("a:has-text('" + name + "')");
    }

    public void openLaunch(String name) {
        launchByName(name).first().click();
    }

    public void filterByName(String name) {
        filterInput.fill(name);
        filterInput.press("Enter");
    }

    public List<String> getVisibleLaunchNames() {
        return page.locator("a[class*='launchName']")
                .allTextContents();
    }

    public boolean isLaunchDetailsOpened() {
        return page.locator("div[class*='launchInfo']").isVisible();
    }
}

//package com.reportportal.ui.pages;
//
//import com.microsoft.playwright.Page;
//import com.microsoft.playwright.Locator;
//
//public class LaunchesPage {
//
//    private final Page page;

    // Локаторы для элементов страницы Launches
//    private static final String LAUNCHES_TABLE_ROW = ".launches-table__row";
//    private static final String TOTAL_LAUNCHES_COUNT = ".total-launches";
//    private static final String FILTER_BUTTON = ".filter-button";
//    private static final String ADD_FILTER_BUTTON = ".add-filter-btn";
//    private static final String FILTER_NAME_INPUT = "input[placeholder='Enter filter name']";
//    private static final String LAUNCH_NAME_LINK = "a.launch-name-link";
//
//    public LaunchesPage(Page page) {
//        this.page = page;
//    }
//
//    public Locator getLaunchesRows() {
//        return page.locator(LAUNCHES_TABLE_ROW);
//    }
//
//    public String getTotalLaunchesCountText() {
//        // Дождемся видимости счетчика
//        page.waitForSelector(TOTAL_LAUNCHES_COUNT);
//        return page.locator(TOTAL_LAUNCHES_COUNT).innerText();
//    }
//
//    // Метод для перехода в детали первого запуска
//    public void clickFirstLaunchName() {
//        Locator firstLaunchLink = page.locator(LAUNCH_NAME_LINK).first();
//        firstLaunchLink.click();
//    }
//
//}