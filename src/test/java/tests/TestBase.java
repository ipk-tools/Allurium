package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import dm.tools.UiSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pages.IframePage;
import pages.PracticeFormPage;
import pages.SimpleListsPage;
import pages.WebTablesPage;

public class TestBase {

    protected PracticeFormPage practiceFormPage = new PracticeFormPage();
    protected WebTablesPage webTablesPage = new WebTablesPage();
    protected IframePage iframePage = new IframePage();
    protected SimpleListsPage simpleListsPage = new SimpleListsPage();

    @BeforeAll
    protected static void beforeAll() {
        Configuration.browser = "chrome";
        Configuration.pageLoadTimeout = 30000;
        Configuration.timeout = 15000;
        WebDriverManager.chromedriver().setup();
        UiSteps.openBrowser();
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterAll
    protected static void afterAll() {
        WebDriverRunner.closeWebDriver();
    }
}
