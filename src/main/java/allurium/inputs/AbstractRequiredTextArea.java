package allurium.inputs;

import allurium.interfaces.RequiredInput;
import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

/**
 * Represents an abstract class for a required text area in the UI, extending {@link TextArea}.
 * <p>
 * This class implements the {@link RequiredInput} interface, adding functionality specific to
 * required text areas, such as methods to check if the field is marked as required and assert its state.
 * It also includes utility methods to verify if an error message is displayed.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Implements {@link RequiredInput} for standardized handling of required fields.</li>
 *     <li>Checks for required field indicators using {@link #isMarked()}.</li>
 *     <li>Assertions to validate the required state of the text area:
 *         <ul>
 *             <li>{@link #assertMarkedAsRequired()} ensures the text area is marked as required.</li>
 *             <li>{@link #assertNotMarkedAsRequired()} ensures the text area is not marked as required.</li>
 *         </ul>
 *     </li>
 *     <li>Designed for extension to implement specific behaviors of required text areas.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates behavior specific to required text areas.</li>
 *     <li>Provides utility methods for asserting required field states and error indicators in tests.</li>
 * </ul>
 */

public abstract class AbstractRequiredTextArea extends TextArea implements RequiredInput {

    /**
     * Default constructor. Initializes the element type as "textarea_required".
     */
    public AbstractRequiredTextArea() {
        super();
        setUiElementType(ElementType.TEXTAREA_REQUIRED.getType());
    }

    /**
     * Constructor that initializes a required text area using a Selenium {@link By} locator.
     *
     * @param rootLocator the Selenium locator for the required text area
     */
    public AbstractRequiredTextArea(By rootLocator) {
        super(rootLocator);
        setUiElementType(ElementType.TEXTAREA_REQUIRED.getType());
    }

    /**
     * Constructor that initializes a required text area using a Selenium {@link By} locator and a name.
     *
     * @param rootLocator the Selenium locator for the required text area
     * @param name        the name of the required text area
     */
    public AbstractRequiredTextArea(By rootLocator, String name) {
        super(rootLocator, name);
        setUiElementType(ElementType.TEXTAREA_REQUIRED.getType());
    }

    /**
     * Constructor that initializes a required text area using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public AbstractRequiredTextArea(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.TEXTAREA_REQUIRED.getType());
    }

    /**
     * Constructor that initializes a required text area using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the required text area
     */
    public AbstractRequiredTextArea(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.TEXTAREA_REQUIRED.getType());
    }

    /**
     * Constructor that initializes a required text area using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the required text area
     */
    public AbstractRequiredTextArea(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.TEXTAREA_REQUIRED.getType());
    }

    /**
     * Constructor that initializes a required text area using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the required text area
     * @param name            the name of the required text area
     */
    public AbstractRequiredTextArea(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.TEXTAREA_REQUIRED.getType());
    }

    /**
     * Checks if an error message is shown for the required text area.
     *
     * @return {@code true} if an error message is displayed, {@code false} otherwise
     */
    public abstract boolean isErrorMessageShown();

    /**
     * Checks if the text area is marked as required.
     *
     * @return {@code true} if the field is marked as required, {@code false} otherwise
     */
    public abstract boolean isMarked();

    /**
     * Asserts that the text area is marked as required.
     * <p>
     * = Step: Processed by Aspect =
     */
    public void assertMarkedAsRequired() {
        Assertions.assertThat(isMarked()).as("Requited field message").isTrue();
    }

    /**
     * Asserts that the text area is not marked as required.
     * <p>
     * = Step: Processed by Aspect =
     */
    public void assertNotMarkedAsRequired() {
        Assertions.assertThat(isMarked()).as("Requited field message").isFalse();
    }
}
