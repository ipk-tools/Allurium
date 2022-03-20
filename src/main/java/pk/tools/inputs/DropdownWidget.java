package pk.tools.inputs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pk.tools.AbstractWidget;
import pk.tools.interfaces.Dropdown;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

/**
 * Widget to working with custom drop down list which looks as native element <select><option/><</select>
 *
 * example of using:
 *
 *    <html>
 *        <ul>
 *            <li>option1</li>
 *            <li>option2</li>
 *            <li>option3</li>
 *        </ul>
 *    </html>
 *
 * ---------------------------------
 *
 *    <code>
 *        DropdownWC dropdown = new DropdownWC("ul");
 *        dropdown.selectItem("option2");
 *    </code>
 *
 *    Next commands Will be recognized as a @Step and added to the Allure report:
 *      - extend()
 *      - select(value)
 *      - selectFirst()
 *      - selectLast()
 *      - selectNot(value)
 *      - selectAny()
 *      - assertValue(value)
 */
@Slf4j
public class DropdownWidget extends AbstractWidget implements Dropdown {

    @Getter private ElementsCollection options;
    @Setter private By optionsLocator = By.tagName("li");
    private SelenideElement scrollBox;

    public DropdownWidget(By rootLocator) {
        super(rootLocator);
        options = root.$$(optionsLocator);
    }

    public DropdownWidget(String selenideLocator) {
        super(selenideLocator);
        options = root.$$(optionsLocator);
    }

    public DropdownWidget(By rootLocator, By optionsLocator) {
        super(rootLocator);
        this.optionsLocator = optionsLocator;
        options = root.$$(optionsLocator);
    }

    public DropdownWidget(SelenideElement selenideElement) {
        super(selenideElement);
        options = root.$$(optionsLocator);
    }

    public DropdownWidget(SelenideElement selenideElement, By optionsLocator) {
        super(selenideElement);
        this.optionsLocator = optionsLocator;
        options = root.$$(optionsLocator);
    }

    @Override
    public SelenideElement select(String value) {
        extend();
        Allure.step("Выбираем значение [" + value + "] из выпадающего списка [" + name + "]");
        dump();
        options.shouldHave(sizeGreaterThan(1));
        SelenideElement option = options.stream()
                .filter(x -> x.getAttribute("textContent").trim().contains(value))
                .findFirst()
                .get();
        option.scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"center\"}").click();
        return option;
    }

    @Override
    public boolean isOptionExists(String value) {
        extend();
        return options.stream().anyMatch(item -> item.text().contains(value));
    }

    public String getSelectedOption() {
        return root.$("button").getText();
    }

    public void dump() {
        log.info(options.size() + " options found in DD List");
        options.forEach(option -> log.info("- " + option.text()));
    }

    public String selectFirst() {
        extend();
        Allure.step("Выбираем первый элемент из выпадающего списка [" + name + "]");
        String chosenModel = options.get(0).getText();
        options.get(0).click();
        return chosenModel;
    }

    @Override
    public void extend() {
        Allure.step("Открываем выпадающий список " + name);
        root.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
        root.shouldBe(Condition.visible).hover().click();
        scrollBox = root.$("ul");
    }

    public DropdownWidget selectLast() {
        extend();
        Allure.step("Выбераем последнюю опцию из выпадающего списка [" + name + "]");
        scrollBox.scrollIntoView("{block: \"end\"}");
        options.get(options.size()).click();
        return this;
    }

    public String selectNot(String optionName) {
        extend();
        Allure.step("Выбираем доступную опцию с любым значением кроме [" + optionName + "] из выпадающего списка [" + name + "]");
        SelenideElement option = options.filterBy(Condition.not(Condition.text(optionName))).first();
        option.click();
        return option.text();
    }

    public String selectAny() {
        Allure.step("Выбераем случайную опцию из выпадающего списка [" + name + "]");
        extend();
        SelenideElement option = options.stream().findAny().get();
        String value = option.text();
        option.click();
        return value;
    }

    public void assertValue(String value) {
        Allure.step("Проверяем выбранное значение [" + value +"] в выпадающем списке [" + name + "]");
        Assertions.assertEquals(value, getSelectedOption(),
                "выбранное значение не совпадает с ожидаемым в выпадающем списке [" + name + "]");
    }
}

