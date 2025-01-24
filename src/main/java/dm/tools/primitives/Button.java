package dm.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import dm.tools.ElementType;
import dm.tools.inputs.AbstractInputElement;
import org.openqa.selenium.By;

/**
 * Represents a Button element in the UI, extending {@link AbstractInputElement}.
 * <p>
 * This class provides additional functionality specific to button elements, such as
 * setting the element type to "button" and offering utility methods for creating button
 * instances. It also includes helper methods to simplify interaction and assertions for
 * button elements in tests.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Sets the UI element type as "button" for easier identification.</li>
 *     <li>Supports convenient factory methods for instantiating button objects.</li>
 *     <li>Integrates with the {@link AbstractInputElement} functionality for input-related behavior.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates button-specific behavior for improved test readability and maintainability.</li>
 *     <li>Standardizes the creation and management of button elements in UI tests.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * Button button = new Button("button.submit");
 * button.click();
 * button.assertEnabled();
 * }</pre>
 */
public class Button extends AbstractInputElement {

    /**
     * Default constructor. Initializes the button element type.
     */
    public Button() {
        setUiElementType(ElementType.BUTTON.getType());
    }

    /**
     * Constructor that initializes a button using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public Button(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.BUTTON.getType());
    }

    /**
     * Constructor that initializes a button using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the button
     */
    public Button(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.BUTTON.getType());
    }

    /**
     * Constructor that initializes a button using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the button
     */
    public Button(By locator) {
        super(locator);
        setUiElementType(ElementType.BUTTON.getType());
    }

    /**
     * Constructor that initializes a button using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the button
     * @param name    the name of the button
     */
    public Button(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.BUTTON.getType());
    }

    /**
     * Constructor that initializes a button using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the button
     * @param name            the name of the button
     */
    public Button(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.BUTTON.getType());
    }

    /**
     * Constructor that initializes a button using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the button
     */
    public Button(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.BUTTON.getType());
    }

    /**
     * Creates a button using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the button
     * @param name    the name of the button
     * @return a new {@link Button} instance
     */
    public static Button $button(By locator, String name) {
        return new Button(locator, name);
    }

    /**
     * Creates a button using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the button
     * @return a new {@link Button} instance
     */
    public static Button $button(SelenideElement selenideElement) {
        return new Button(selenideElement);
    }

    /**
     * Creates a button using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the button
     * @param name            the name of the button
     * @return a new {@link Button} instance
     */
    public static Button $button(SelenideElement selenideElement, String name) {
        return new Button(selenideElement, name);
    }

    /**
     * Creates a button using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     * @return a new {@link Button} instance
     */
    public static Button $button(String selenideLocator) {
        return new Button(selenideLocator);
    }

    /**
     * Creates a button using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the button
     * @return a new {@link Button} instance
     */
    public static Button $button(String selenideLocator, String name) {
        return new Button(selenideLocator, name);
    }

    /**
     * Creates a button using an XPath string.
     *
     * @param xpath the XPath string for the button
     * @return a new {@link Button} instance
     */
    public static Button _$button(String xpath) {
        return new Button(By.xpath(xpath));
    }

    /**
     * Retrieves the unique identifier for this button, which is its text.
     *
     * @return the button's text as its unique identifier
     */
    @Override
    public String getId() {
        return getRoot().text();
    }
}
