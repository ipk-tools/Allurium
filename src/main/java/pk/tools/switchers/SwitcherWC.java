package pk.tools.switchers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.By;
import pk.tools.AbstractWidget;
import pk.tools.interfaces.SelectWC;

import static com.codeborne.selenide.Selenide.$$;

/**
 * The simplest switcher widget which have only two positions (left/right)
 *
 * example of using:
 *
 *    <html>
 *       <ul>
 *          <li>left</li>
 *          <li>right</li>
 *       </ul>
 *    </html>
 *
 * ---------------------------------
 *
 *    <code>
 *        SwitcherWC switcher = new Switcher("ul");
 *        switcher.switchTo(Switch_To.RIGHT);
 *        switcher.switchTo(Switch_To.LEFT);
 *    </code>
 *
 *    Next commands Will be recognized as a @Step and added to the Allure report:
 *      - switchTo()
 */
public class SwitcherWC extends AbstractWidget implements SelectWC {

    private ElementsCollection options;
    private Pair<SelenideElement,SelenideElement> pair;

    public SwitcherWC(By optionsLocator) {
        options = $$(optionsLocator);
        pair = Pair.of(options.get(0), options.get(1));
    }

    public SwitcherWC(String optionsSelenideLocator) {
        options = $$(optionsSelenideLocator);
        pair = Pair.of(options.get(0), options.get(1));
    }

    @Step
    public SwitcherWC selectItem(Switch_To side) {
//        options = root.$$x("li");
        pair = Pair.of(options.get(0), options.get(1));
        switch (side) {
            case LEFT:
                pair.getLeft().click();
                break;
            case RIGHT:
                pair.getRight().click();
        }
        return this;
    }

    @Override
    public void select(String value) {
        switchTo(value);
    }

    public enum Switch_To {
        LEFT, RIGHT
    }

    @Step("Переключаем опцию в свитчере на [{value}]")
    public SwitcherWC switchTo(String value) {
        if (options.filterBy(Condition.text(value)).size() < 1)
            throw new IllegalArgumentException(
                    "Передано неверное значение [" + value + "] в переключатель" + this.getClass().getName());
        options.filterBy(Condition.text(value)).first().click();
        return this;
    }
}
