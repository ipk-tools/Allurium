package pk.tools.examples.pages;


import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;

public abstract class BasePage {

    // to override
    public boolean checkPageLoaded() {
        return true;
    }

    @Step("Veryfy URL [{url}]")
    public void verifyUrl(String url) {
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), url,
                "wrong page opened");
    }

    @Step("Verify URL contains [{text}]")
    public void checkUrlContains(String text) {
        Assertions.assertTrue(WebDriverRunner.getWebDriver().getCurrentUrl().contains(text),
                "url of current page doesn't contain text [" + text + "]");
    }

}
