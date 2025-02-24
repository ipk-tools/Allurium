package allurium.inputs;

import allurium.interfaces.InputElement;
import allurium.primitives.UIElement;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Represents the base class for input elements in the UI, extending {@link UIElement}.
 * <p>
 * This abstract class provides a foundation for specialized input field implementations,
 * such as text fields, checkboxes, and dropdowns. It includes utility methods for validating
 * input field states, such as checking whether the field is enabled or disabled.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Base implementation for input elements.</li>
 *     <li>Methods for checking and asserting the enabled or disabled state.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates shared behavior for input elements.</li>
 *     <li>Acts as a parent class for all input-specific components.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * public class Checkbox extends AbstractInputElement {
 *     // Custom logic for checkboxes
 * }
 * }</pre>
 */
public class AbstractInputElement extends UIElement implements InputElement {

    /**
     * Default constructor.
     */
    public AbstractInputElement() {
        super();
    }

    /**
     * Initializes an input element using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    protected AbstractInputElement(String selenideLocator) {
        super(selenideLocator);
    }

    /**
     * Initializes an input element using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the input element
     */
    protected AbstractInputElement(String selenideLocator, String name) {
        super(selenideLocator, name);
    }

    /**
     * Initializes an input element using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator
     */
    protected AbstractInputElement(By locator) {
        super(locator);
    }

    /**
     * Initializes an input element using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator
     * @param name    the name of the input element
     */
    protected AbstractInputElement(By locator, String name) {
        super(locator, name);
    }

    /**
     * Initializes an input element using a {@link SelenideElement}.
     *
     * @param selenideElement the Selenide element
     */
    public AbstractInputElement(SelenideElement selenideElement) {
        super(selenideElement);
    }

    /**
     * Initializes an input element using a {@link SelenideElement} and a name.
     *
     * @param selenideElement the Selenide element
     * @param name            the name of the input element
     */
    protected AbstractInputElement(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
    }

    /**
     * Checks if the input element is disabled.
     *
     * @return {@code true} if the element is disabled, {@code false} otherwise
     */
    @Override
    public boolean isDisabled() {
        return getRoot().is(Condition.disabled);
    }

    /**
     * Asserts that the element is enabled.
     * <p><b>< Step: Processed by Aspect ></b></p>
     *
     * @throws AssertionError if the input element is disabled
     */
    public void assertEnabled() {
        root.shouldBe(Condition.enabled);
    }

    /**
     * Asserts that the element is disabled.
     * <p><b>< Step: Processed by Aspect ></b></p>
     *
     * @throws AssertionError if the input element is enabled
     */
    public void assertDisabled() {
        root.shouldBe(Condition.disabled);
    }
}
