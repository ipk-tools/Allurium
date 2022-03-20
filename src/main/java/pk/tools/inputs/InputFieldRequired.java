package pk.tools.inputs;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pk.tools.interfaces.RequiredInput;

/**
 * Wrapper among default text <input> which is required to be filled.
 * Extended from InputField
 * Shows the error message if blank.
 *
 *  > example of using:
 *
 *    <html>
 *        <input type="text">
 *    </html>
 *
 * ---------------------------------
 *
 *    <code>
 *        InputField inputField = new InputField("input");
 *        inputField.write("Hello World");
 *        (anyForm).clickSubmit();
 *        Assertions.assertTrue(inputField.isErrorMessageShown())
 *    </code>
 *
 *    Next commands will be recognized as a @Step and added to the Allure report:
 *      - write()
 *      - clear()
 */
@Slf4j
public class InputFieldRequired extends InputField implements RequiredInput {

    private final SelenideElement errorMessage;

    public InputFieldRequired(By rootLocator) {
        super(rootLocator);
        errorMessage = root.ancestor(".input-field").$(".input-field__error-message");
    }

    public InputFieldRequired(String selenideSelector) {
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
