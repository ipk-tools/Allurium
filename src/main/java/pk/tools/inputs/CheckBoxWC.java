package pk.tools.inputs;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Allure;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pk.tools.primitives.UIElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

/**
 * Все наши кастомные чекбоксы содержат тэг input. Это скрытый дефолтный чекбокс. Именно до этого тэга нужно прописывать XPath
 */
@Slf4j
@Getter
public class CheckBoxWC extends UIElement {

    private SelenideElement customCheckbox;
    private String locator;

    public CheckBoxWC(String xpath) {
        super($x(xpath));
        locator = xpath;
        customCheckbox = $x(xpath + "/.."); //добавил в xPath переход на родительский элемент, т.к. сам элемент невидимый, а селениум в такие не умеет
    }

    public boolean getCheckboxState() {
        try {
            return executeJavaScript("return document.evaluate(\"" + locator + "\", " +
                    "document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null).snapshotItem(0).checked");
        } catch (NullPointerException e) {
            throw new ElementNotFound(locator, exist);
        }
    }

    /**
     * true - CHECKED, false - UNCHECKED
     */
    public void setChBoxState(boolean state) {
        Allure.step("Устанавливаем значение [" + state + "] чекбоксу '" + name + "'");
        log.info("Устанавливаем значение [" + state + "] чекбоксу '" + name + "'");
        if (state != getCheckboxState()) {
            customCheckbox.click();
        }
    }
}
