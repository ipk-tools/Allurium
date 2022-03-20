package pk.tools.switchers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import pk.tools.AbstractWidget;
import pk.tools.interfaces.SwitcherItem;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public abstract class AbstractSwitcherItem extends AbstractWidget implements SwitcherItem {

    public AbstractSwitcherItem(SelenideElement rootElement) {
        root = $(rootElement);
    }

    @Override
    public String getId() {
        return root.text();
    }

    @Override
    public void choose() {
        Allure.step("Выбираем [" + getId() + "] в виджете " + getParent().getName());
        log.info("Выбираем [" + getId() + "] в виджете " + getParent().getName());
        root.click();
    }
}
