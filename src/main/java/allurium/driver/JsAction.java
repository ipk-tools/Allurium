package allurium.driver;

import com.codeborne.selenide.WebDriverRunner;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Selenide.executeJavaScript;

@UtilityClass
public class JsAction {

    public static JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) WebDriverRunner.getWebDriver();
    }

    public static String getPageLoadingStatus() {
        return executeJavaScript("return document.readyState");
    }
}
