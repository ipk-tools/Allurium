package allurium.inputs;

import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a text area (`<textarea></textarea>`) element in the UI, extending {@link TextField}.
 * <p>
 * This class provides wrapper functionality for managing text area elements and logging each action
 * as a step in Allure reports. It inherits most of its functionality from the {@link TextField} class.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Typing and clearing text in a text area field.</li>
 *     <li>Assertions for input values, including checks for emptiness or specific values.</li>
 *     <li>All actions are logged as Allure steps for enhanced reporting.</li>
 * </ul>
 *
 * <h3>Example Usage:</h3>
 * <pre>{@code
 * <html>
 *     <textarea>lorem ipsum</textarea>
 * </html>
 * }</pre>
 *
 * <pre>{@code
 * TextArea textArea = new TextArea("textarea");
 * textArea.write("Hello World");
 * textArea.clear();
 * }</pre>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates text area-specific behavior.</li>
 *     <li>Provides utility methods for convenient interaction with text area elements in tests.</li>
 * </ul>
 */
public class TextArea extends TextField {

    /**
     * Default constructor. Initializes the element type as "textarea".
     */
    public TextArea() {
        setUiElementType(ElementType.TEXTAREA.getType());
    }

    /**
     * Constructor that initializes a text area using a Selenium {@link By} locator.
     *
     * @param rootLocator the Selenium locator for the text area
     */
    public TextArea(By rootLocator) {
        super(rootLocator);
        setUiElementType(ElementType.TEXTAREA.getType());
    }

    /**
     * Constructor that initializes a text area using a Selenium {@link By} locator and a name.
     *
     * @param rootLocator the Selenium locator for the text area
     * @param name        the name of the text area
     */
    public TextArea(By rootLocator, String name) {
        super(rootLocator, name);
        setUiElementType(ElementType.TEXTAREA.getType());
    }

    /**
     * Constructor that initializes a text area using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public TextArea(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.TEXTAREA.getType());
    }

    /**
     * Constructor that initializes a text area using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the text area
     */
    public TextArea(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.TEXTAREA.getType());
    }

    /**
     * Constructor that initializes a text area using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the text area
     */
    public TextArea(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.TEXTAREA.getType());
    }

    /**
     * Constructor that initializes a text area using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the text area
     * @param name            the name of the text area
     */
    public TextArea(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.TEXTAREA.getType());
    }

    /**
     * Creates a text area instance using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the text area
     * @return a new {@link TextArea} instance
     */
    public static TextArea $textArea(By locator) {
        return new TextArea(locator);
    }

    /**
     * Creates a text area instance using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the text area
     * @param name    the name of the text area
     * @return a new {@link TextArea} instance
     */
    public static TextArea $textArea(By locator, String name) {
        return new TextArea(locator, name);
    }

    /**
     * Creates a text area instance using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     * @return a new {@link TextArea} instance
     */
    public static TextArea $textArea(String selenideLocator) {
        return new TextArea(selenideLocator);
    }

    /**
     * Creates a text area instance using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the text area
     * @return a new {@link TextArea} instance
     */
    public static TextArea $textArea(String selenideLocator, String name) {
        return new TextArea(selenideLocator, name);
    }

    /**
     * Creates a text area instance using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the text area
     * @return a new {@link TextArea} instance
     */
    public static TextArea $textArea(SelenideElement selenideElement) {
        return new TextArea(selenideElement);
    }

    /**
     * Creates a text area instance using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the text area
     * @param name            the name of the text area
     * @return a new {@link TextArea} instance
     */
    public static TextArea $textArea(SelenideElement selenideElement, String name) {
        return new TextArea(selenideElement, name);
    }

    /**
     * Creates a text area instance using an XPath string.
     *
     * @param xpath the XPath string for the text area
     * @return a new {@link TextArea} instance
     */
    public static TextArea _$textArea(String xpath) {
        return new TextArea(By.xpath(xpath));
    }

}
