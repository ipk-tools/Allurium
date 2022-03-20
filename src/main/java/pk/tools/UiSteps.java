package pk.tools;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import com.epam.jdi.tools.pairs.Pair;
import com.galenframework.api.Galen;
import com.galenframework.config.GalenConfig;
import com.galenframework.config.GalenProperty;
import com.galenframework.reports.model.LayoutReport;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pk.tools.primitives.UIElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;

@UtilityClass
@Slf4j
public class UiSteps {

    @Step("Ожидаем в течение [{seconds}] секунд")
    public static void waiting(long seconds) {
        Selenide.sleep(seconds*1000);
    }

    @Step("Ожидаем [{description}] в течение [{sec}] секунд")
    public static void waiting(long sec, String description) {
        waiting(sec);
    }

    @Step("Нажимаем кнопку - стрелка вверх")
    public static void clickUp() {
        $("body").sendKeys(Keys.ARROW_UP);
    }

    public static void pressEnter() {
        Allure.step("Press key 'Enter'");
        $("body").pressEnter();
    }

    @SuppressWarnings("unchecked")
    public static void dragAndDrop(UIElement dragThis, UIElement toThat) {
        Allure.step(StepText.Drad_and_Drop.getStepText(
                Pair.$("{from_name}", dragThis.getName()), Pair.$("{to_name}", toThat.getName())));
        Actions action = new Actions(WebDriverRunner.getWebDriver());
        action.dragAndDrop(dragThis.get().getWrappedElement(), toThat.get().getWrappedElement());
    }

    @Step("Скролл страницы вверх [{up}]")
    public static void scrollPage(boolean up) {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        if (up)
            js.executeScript("window.scrollTo({" +
                    "top: 0," +
                    "behavior: 'smooth'" +
                    "})");
        else
            js.executeScript("window.scrollTo({" +
                    "top: 999999999999999999," +
                    "behavior: 'smooth'" +
                    "})");
    }

    @Step("Удалить element из dom")
    public static void removeElement(UIElement element) {
        removeElement(element.get().getWrappedElement());
    }

    @Step("Удалить element из dom")
    public static void removeElement(By element) {
        try {
            removeElement(Selenide.$(element).getWrappedElement());
        } catch (NoSuchElementException | ElementNotFound ex) {
            log.error("Нельзя удалить элемент который не существует", ex);
        }
    }

    private static void removeElement(WebElement element) {
        try {
            ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("arguments[0].remove()", element);
        } catch (NoSuchElementException | ElementNotFound ex) {
            log.error("Нельзя удалить элемент который не существует", ex);
        }
    }

    @Step("Открываем страницу {url} c логином {login} и паролем {password}")
    public static void open(String url, String login, String password) {
        boolean success = false;
        int retry = 1;
        while (!success && retry <= 6) {
            try {
                retry++;
                Selenide.open(url, "", login, password);
                success = true;
            } catch (Exception e) {
                log.error("Ошибка открытия browser", e);
            }
        }
        if (!success)
            Selenide.open(url, "", login, password);
    }

    public static void open(String url) {
        boolean success = false;
        int retry = 1;
        while (!success && retry <= 6) {
            try {
                retry++;
                Selenide.open(url);
                success = true;
            } catch (Exception e) {
                log.error("Ошибка открытия browser", e);
            }
        }
        if (!success)
            Selenide.open(url);
    }
}
