package pk.tools.switchers;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import pk.tools.AbstractWidget;
import pk.tools.ListWC;
import pk.tools.interfaces.Switch;
import pk.tools.interfaces.SwitcherItem;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 *
 * @param <T>
 */
@Getter
@Slf4j
public class WidgetSwitcher<T extends SwitcherItem> extends AbstractWidget implements Switch {

    private final ElementsCollection sourceSwitches;
    private ListWC<T> switches;
    private final Class<T> switchComponentClass;

    public WidgetSwitcher(By switchesLocator, Class<T> switchComponentClass) {
        super($(switchesLocator).parent());
        this.switchComponentClass = switchComponentClass;
        sourceSwitches = $$(switchesLocator);
    }

    public WidgetSwitcher(String selenideSwitchesLocator, Class<T> switchComponentClass) {
        super($(selenideSwitchesLocator).parent());
        this.switchComponentClass = switchComponentClass;
        sourceSwitches = $$(selenideSwitchesLocator);
    }

    public WidgetSwitcher(SelenideElement rootElement, String switchesTag, Class<T> switchComponentClass) {
        super(rootElement);
        this.switchComponentClass = switchComponentClass;
        sourceSwitches = rootElement.$$(switchesTag);
    }

    public WidgetSwitcher(By rootLocator, String switchesTag, Class<T> switchComponentClass) {
        super(rootLocator);
        this.switchComponentClass = switchComponentClass;
        sourceSwitches = root.$$(switchesTag);
    }

    public WidgetSwitcher(String selenideRootLocator, String switchesTag, Class<T> switchComponentClass) {
        super(selenideRootLocator);
        this.switchComponentClass = switchComponentClass;
        sourceSwitches = root.$$(switchesTag);
    }

    public T getSwitch(String itemId) {
        if (switches == null) switches = new ListWC<>(sourceSwitches, switchComponentClass);
        return switches.get(itemId);
    }

    public T getSwitcher(int index) {
        if (switches == null) switches = new ListWC<>(sourceSwitches, switchComponentClass);
        return switches.get(index);
    }

    @Override
    @Step("Выбираем {itemId} в переключателе")
    public void switchTo(String itemId) {
        switchTo(itemId, ClickOptions.usingDefaultMethod());
    }

    @Step("Выбираем {itemId} в переключателе")
    public void switchTo(String itemId, ClickOptions clickOptions) {
        Allure.step("Выбираем " + itemId + " в переключателе " + name);
        if (switches == null) switches = new ListWC<>(sourceSwitches, switchComponentClass);
        switches.get(itemId).getRoot().click(clickOptions);
    }

    public WidgetSwitcher<T> assertIsSelected(String itemId) {
        Allure.step("Проверяем, что выбран [" + itemId + "] в переключателе " + name);
        log.info("Проверяем, что выбран [" + itemId + "] в переключателе " + name);
        Assert.assertTrue(getSwitch(itemId).checkSelected());
        return this;
    }

    public WidgetSwitcher<T> assertIsSelected(String itemId, int timeoutSec) {
        Allure.step("Проверяем, что выбран [" + itemId + "] в переключателе " + name);
        log.info("Проверяем, что выбран [" + itemId + "] в переключателе " + name);
        if (!getSwitch(itemId).checkSelected() && timeoutSec > 0) {
            Selenide.sleep(1000);
            assertIsSelected(itemId, timeoutSec - 1);
        }
        Assert.assertTrue(getSwitch(itemId).checkSelected());
        return this;
    }

    public WidgetSwitcher<T> assertIsSelected(String itemId, String assertPhrase) {
        Allure.step("Проверяем, что выбран [" + itemId + "] в переключателе " + name);
        log.info("Проверяем, что выбран [" + itemId + "] в переключателе " + name);
        Assert.assertTrue(getSwitch(itemId).checkSelected(), assertPhrase);
        return this;
    }

    public WidgetSwitcher<T> assertIsSelected(String itemId, String assertPhrase, int timeoutSec) {
        Allure.step("Проверяем, что выбран [" + itemId + "] в переключателе " + name);
        log.info("Проверяем, что выбран [" + itemId + "] в переключателе " + name);
        if (!getSwitch(itemId).checkSelected() && timeoutSec > 0) {
            Selenide.sleep(1000);
            assertIsSelected(itemId, assertPhrase, timeoutSec - 1);
        }
        Assert.assertTrue(getSwitch(itemId).checkSelected(), assertPhrase);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (switches == null) switches = new ListWC<>(sourceSwitches, switchComponentClass);
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WidgetSwitcher<?> that = (WidgetSwitcher<?>) o;
        return switches.equals(that.switches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(switches);
    }
}
