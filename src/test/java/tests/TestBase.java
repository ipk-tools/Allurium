package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestBase {

    @BeforeSuite
    protected void beforeSuite() {
        Configuration.browser = "chrome";
        Configuration.pageLoadTimeout = 60000;
        Configuration.timeout = 15000;
    }

    @AfterSuite
    protected void afterSuite() {}
}
