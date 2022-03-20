package pk.tools.switchers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pk.tools.AbstractWidget;
import pk.tools.ListWC;
import pk.tools.interfaces.Switch;
import pk.tools.primitives.UIElement;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
@Slf4j
public class MultiSwitcher extends AbstractWidget implements Switch {

    private final ListWC<UIElement> switches;

    public MultiSwitcher(SelenideElement rootElement, String tag) {
        super(rootElement);
        switches = new ListWC<>($(rootElement).$$(tag), UIElement.class);
    }

    public MultiSwitcher(String switchesSelenideLocator) {
        super(switchesSelenideLocator);
        switches = new ListWC<>($$(switchesSelenideLocator), UIElement.class);
    }

    public MultiSwitcher(By switchesLocator) {
        super(switchesLocator);
        switches = new ListWC<>($$(switchesLocator), UIElement.class);
    }

    @Override
    public void switchTo(String option) {
        Allure.step("Выбираем параметр [" + option + "] " + "в переключателе " + name);
        switches.get(option).click();
    }

    public void assertIsSelected(String option, String mustHaveCsClass) {
        Allure.step("Проверяем, что опция " + option + " выделена");
        log.info("Проверяем, что опция " + option + " выделена");
        switches.get(option).get().has(cssClass(mustHaveCsClass));
    }
}
