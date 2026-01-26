package com.reportportal.ui.tests;

import com.reportportal.base.BaseUiTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlaywrightSmokeTest extends BaseUiTest {

    @Test
    void shouldOpenReportPortalHome() {
        page.navigate("https://demo.reportportal.io/ui/");
        assertNotNull(page.title()); // проверка, что страница открылась
    }
}
