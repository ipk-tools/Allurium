package allurium.inputs;

import allurium.interfaces.RequiredInput;
import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

/**
 * Represents an abstract class for a required text field in the UI, extending {@link TextField}.
 * <p>
 * This class implements the {@link RequiredInput} interface, adding functionality specific to
 * required text fields, such as methods to check if the field is marked as required and assert its state.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Checks for required field indicators using {@link #isMarked()}.</li>
 *     <li>Assertions to validate the required state of the field:
 *         <ul>
 *             <li>{@link #assertMarkedAsRequired()} ensures the field is marked as required.</li>
 *             <li>{@link #assertNotMarkedAsRequired()} ensures the field is not marked as required.</li>
 *         </ul>
 *     </li>
 *     <li>Designed for extension to implement specific behaviors of required text fields.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates behavior specific to required text fields.</li>
 *     <li>Provides utility methods for asserting required field states in tests.</li>
 * </ul>
 */
public abstract class AbstractRequiredTextField extends TextField implements RequiredInput {

    /**
     * Default constructor. Initializes the element type as "textfield_required".
     */
    public AbstractRequiredTextField() {
        setUiElementType(ElementType.TEXTFIELD_REQUIRED.getType());
    }

    /**
     * Constructor that initializes a required text field using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public AbstractRequiredTextField(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.TEXTFIELD_REQUIRED.getType());
    }

    /**
     * Constructor that initializes a required text field using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the required text field
     */
    public AbstractRequiredTextField(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.TEXTFIELD_REQUIRED.getType());
    }

    /**
     * Constructor that initializes a required text field using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the required text field
     */
    public AbstractRequiredTextField(By locator) {
        super(locator);
        setUiElementType(ElementType.TEXTFIELD_REQUIRED.getType());
    }

    /**
     * Constructor that initializes a required text field using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the required text field
     * @param name    the name of the required text field
     */
    public AbstractRequiredTextField(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.TEXTFIELD_REQUIRED.getType());
    }

    /**
     * Constructor that initializes a required text field using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the required text field
     */
    public AbstractRequiredTextField(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.TEXTFIELD_REQUIRED.getType());
    }

    /**
     * Constructor that initializes a required text field using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the required text field
     * @param name            the name of the required text field
     */
    public AbstractRequiredTextField(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.TEXTFIELD_REQUIRED.getType());
    }

    /**
     * Checks if the text field is marked as required.
     *
     * @return {@code true} if the field is marked as required, {@code false} otherwise
     */
    public abstract boolean isMarked();

    /**
     * Asserts that the text field is marked as required.
     * <p>
     * <p><b>< Step: Processed by Aspect ></b></p>
     */
    public void assertMarkedAsRequired() {
        Assertions.assertThat(isMarked()).as("Required field indicator").isTrue();
    }

    /**
     * Asserts that the text field is not marked as required.
     * <p>
     * <p><b>< Step: Processed by Aspect ></b></p>
     */
    public void assertNotMarkedAsRequired() {
        Assertions.assertThat(isMarked()).as("Required field indicator").isFalse();
    }

}
