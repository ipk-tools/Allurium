package dm.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import dm.tools.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a single-line text element in the UI, extending {@link UIElement}.
 * <p>
 * This class encapsulates the behavior and attributes of a single-line text-based UI component,
 * such as labels, input hints, or other single-line text elements.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of a single-line text element.</li>
 *     <li>Provides multiple constructors for flexible initialization using:
 *         <ul>
 *             <li>Selenide locators as strings</li>
 *             <li>Selenium {@link By} locators</li>
 *             <li>{@link SelenideElement} instances</li>
 *         </ul>
 *     </li>
 *     <li>Includes static factory methods for convenient creation of `TextLine` instances.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes interaction with single-line text elements in the UI.</li>
 *     <li>Simplifies the creation and validation of single-line text elements in tests.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #TextLine()} - Default constructor.</li>
 *     <li>{@link #TextLine(String)} - Creates a `TextLine` using a Selenide locator string.</li>
 *     <li>{@link #TextLine(String, String)} - Creates a `TextLine` using a Selenide locator string and a name.</li>
 *     <li>{@link #TextLine(By)} - Creates a `TextLine` using a Selenium {@link By} locator.</li>
 *     <li>{@link #TextLine(By, String)} - Creates a `TextLine` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #TextLine(SelenideElement)} - Creates a `TextLine` using a {@link SelenideElement}.</li>
 *     <li>{@link #TextLine(SelenideElement, String)} - Creates a `TextLine` using a {@link SelenideElement} and a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #$textLine(By)} - Creates a `TextLine` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$textLine(By, String)} - Creates a `TextLine` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$textLine(SelenideElement)} - Creates a `TextLine` using a {@link SelenideElement}.</li>
 *     <li>{@link #$textLine(SelenideElement, String)} - Creates a `TextLine` using a {@link SelenideElement} and a name.</li>
 *     <li>{@link #$textLine(String)} - Creates a `TextLine` using a Selenide locator string.</li>
 *     <li>{@link #$textLine(String, String)} - Creates a `TextLine` using a Selenide locator string and a name.</li>
 *     <li>{@link #_$textLine(String)} - Creates a `TextLine` using an XPath expression.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating a text line using a Selenide locator
 * TextLine textLine = $textLine(".text-line-class");
 *
 * // Creating a text line with a custom name
 * TextLine namedTextLine = $textLine(By.cssSelector(".text-line-class"), "Header Text");
 * }
 * </pre>
 */
public class TextLine extends UIElement {

    /**
     * Default constructor. Initializes the element type as "string".
     */
    public TextLine() {
        setUiElementType(ElementType.STRING.getType());
    }

    /**
     * Constructor that initializes a `TextLine` element using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public TextLine(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.STRING.getType());
    }

    /**
     * Constructor that initializes a `TextLine` element using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the text line
     */
    public TextLine(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.STRING.getType());
    }

    /**
     * Constructor that initializes a `TextLine` element using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the text line
     */
    public TextLine(By locator) {
        super(locator);
        setUiElementType(ElementType.STRING.getType());
    }

    /**
     * Constructor that initializes a `TextLine` element using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the text line
     * @param name    the name of the text line
     */
    public TextLine(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.STRING.getType());
    }

    /**
     * Constructor that initializes a `TextLine` element using a {@link SelenideElement}.
     *
     * @param selenideElement the Selenide element representing the text line
     */
    public TextLine(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.STRING.getType());
    }

    /**
     * Constructor that initializes a `TextLine` element using a {@link SelenideElement} and a name.
     *
     * @param selenideElement the Selenide element representing the text line
     * @param name            the name of the text line
     */
    public TextLine(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.STRING.getType());
    }

    // Static Factory Methods

    public static TextLine $textLine(By locator) {
        return new TextLine(locator);
    }

    public static TextLine $textLine(By locator, String name) {
        return new TextLine(locator, name);
    }

    public static TextLine $textLine(SelenideElement selenideElement) {
        return new TextLine(selenideElement);
    }

    public static TextLine $textLine(SelenideElement selenideElement, String name) {
        return new TextLine(selenideElement, name);
    }

    public static TextLine $textLine(String selenideLocator) {
        return new TextLine(selenideLocator);
    }

    public static TextLine $textLine(String selenideLocator, String name) {
        return new TextLine(selenideLocator, name);
    }

    public static TextLine _$textLine(String xpath) {
        return new TextLine(By.xpath(xpath));
    }

}
