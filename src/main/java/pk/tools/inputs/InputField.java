package pk.tools.inputs;

import com.codeborne.selenide.SelenideElement;
import com.epam.jdi.tools.pairs.Pair;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pk.tools.AbstractWidget;
import pk.tools.ElementType;
import pk.tools.StepText;
import pk.tools.interfaces.Writable;

/**
 * Wrapper among default text <input> field to make each action with field as Allure @Step
 * and make it extendable.
 *
 * > example of using:
 *
 * @<code>
 *     <html>
 *         <input type="text">
 *     </html>
 * </code>
 * ---------------------------------
 *
 * @<code>
 *     InputField inputField = new InputField("input");
 *     inputField.write("Hello World");
 *     inputField.clear();
 * </code>
 *
 * Next commands Will be recognized as a @Step and added to the Allure report:
 * - write()
 * - clear()
 * - value()
 * - pressEnter()
 */
@Slf4j
@Getter
public class InputField extends AbstractWidget implements Writable {

    private SelenideElement clearButton = root.parent().$(".input-field__clear-field");

    protected InputField(By rootLocator) {
        super(rootLocator);
        setType(ElementType.INPUT.getType());
    }

    protected InputField(String selenideSelector) {
        super(selenideSelector);
        setType(ElementType.INPUT.getType());
    }

    protected InputField(SelenideElement selenideElement) {
        super(selenideElement);
        setType(ElementType.INPUT.getType());
    }
    public static InputField from(By locator) {
        return new InputField(locator);
    }

    public static InputField from(SelenideElement selenideElement) {
        return new InputField(selenideElement);
    }

    public static InputField from(String selenideLocator) {
        return new InputField(selenideLocator);
    }

    public void write(String text) {
//        super.setName();
//        logStep("Вводим текст [" + text + "] в поле '" + name + "'");
        logStep(StepText.Write, Pair.$("{text}", text));
        root.clear();
        root.sendKeys(text);
    }

    @Override
    public void clear() {
//        super.setName();
        logStep(StepText.Clear_text_field);
        root.clear();
    }

    public void clearButtonClick() {
        super.applyName();
        logStep("Очищаем поле ввода c помощью кнопки '" + name + "'");
        clearButton.click();
    }

    public String value() {
        super.applyName();
        String value = root.getValue();
        logStep("Получаем значение поля ввода '" + name + "' значение = '" + value +"'");
        return value;
    }

    public void pressEnter() {
        logStep(StepText.Text_field_press_enter);
        root.pressEnter();
    }

    public void checkIsEmpty() {
        logStep(StepText.Verify_text_field_blank);
        Assertions.assertEquals("", root.getValue(),
                StepText.Field_has_value.getStepText(Pair.$("{name}", wrappedName())));
    }


}
