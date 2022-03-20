package pk.tools.inputs;

import io.qameta.allure.Allure;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pk.tools.interfaces.Writable;
import pk.tools.primitives.UIElement;

/**
 * Wrapper among default <textarea></textarea>  to make each action with field as Allure @Step
 * and make it extendable.
 *
 * > example of using:
 *
 *    <html>
 *        <textarea>lorem ipsum</textarea>
 *    </html>
 *
 * ---------------------------------
 *
 *    <code>
 *        TextArea textarea = new TextArea("textarea");
 *        textarea.write("Hello World");
 *        textarea.clear();
 *    </code>
 *
 *    Next commands will be recognized as a @Step and added to the Allure report:
 *      - write()
 *      - clear()
 */
@Slf4j
@Getter
public class TextArea extends UIElement implements Writable {

    public TextArea(String selenideSelector) {
        super(selenideSelector);
    }

    @Override
    public void write(String text) {
        Allure.step("Вводим текст [" + text + "] в текстовую область '" + name + "'");
        log.info("Вводим текст [" + text + "] в текстовую область '" + name + "'");
        root.sendKeys(text);
    }

    @Override
    public void clear() {
        Allure.step("Очищаем текстовую область '" + name + "'");
        log.info("Очищаем текстовую область '" + name + "'");
        root.clear();
    }
}
