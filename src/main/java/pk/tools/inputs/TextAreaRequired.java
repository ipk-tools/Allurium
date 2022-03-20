package pk.tools.inputs;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import pk.tools.interfaces.RequiredInput;

/**
 * Wrapper among default <textarea> which is required to be filled.
 * Extended from TextArea
 * Shows the error message if blank.
 *
 *  > example of using:
 *
 *    <html>
 *        <textarea>lorem ipsum</textarea>
 *    </html>
 *
 * ---------------------------------
 *
 *    <code>
 *        InputField textarea = new InputField("input");
 *        textarea.write("Hello World");
 *        (anyForm).clickSubmit();
 *        Assertions.assertTrue(textarea.isErrorMessageShown())
 *    </code>
 *
 *    Next commands will be recognized as a @Step and added to the Allure report:
 *      - write()
 *      - clear()
 */
@Slf4j
public class TextAreaRequired extends TextArea implements RequiredInput {

    private final SelenideElement errorMessage;

    public TextAreaRequired(String selenideSelector) {
        super(selenideSelector);
        errorMessage = root.ancestor(".input-field").$(".input-field__error-message");
    }

    public boolean isErrorMessageShown() {
        log.debug("input required message : " + errorMessage.text());
        return  errorMessage.isDisplayed();
    }

    @Override
    public String getErrorMessage() {
        return errorMessage.text();
    }
}
