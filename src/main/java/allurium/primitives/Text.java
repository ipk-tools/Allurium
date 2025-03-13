package allurium.primitives;

import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a `Text` element in the UI, extending {@link UIElement}.
 * <p>
 * This class encapsulates the behavior and attributes of a text-based UI component, such as paragraphs, labels,
 * or any element containing textual content.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of a `Text` element.</li>
 *     <li>Provides multiple constructors for flexible initialization using:
 *         <ul>
 *             <li>Selenide locators as strings</li>
 *             <li>Selenium {@link By} locators</li>
 *             <li>{@link SelenideElement} instances</li>
 *         </ul>
 *     </li>
 *     <li>Includes static factory methods for convenient creation of `Text` instances.</li>
 *     <li>Overrides `toString` and `getId` methods to return the element's name.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes interaction with text elements in the UI.</li>
 *     <li>Simplifies the creation and validation of text-based elements in tests.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #Text()} - Default constructor.</li>
 *     <li>{@link #Text(String)} - Creates a `Text` using a Selenide locator string.</li>
 *     <li>{@link #Text(String, String)} - Creates a `Text` using a Selenide locator string and a name.</li>
 *     <li>{@link #Text(By)} - Creates a `Text` using a Selenium {@link By} locator.</li>
 *     <li>{@link #Text(By, String)} - Creates a `Text` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #Text(SelenideElement)} - Creates a `Text` using a {@link SelenideElement}.</li>
 *     <li>{@link #Text(SelenideElement, String)} - Creates a `Text` using a {@link SelenideElement} and a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #$text(By)} - Creates a `Text` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$text(By, String)} - Creates a `Text` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$text(SelenideElement)} - Creates a `Text` using a {@link SelenideElement}.</li>
 *     <li>{@link #$text(SelenideElement, String)} - Creates a `Text` using a {@link SelenideElement} and a name.</li>
 *     <li>{@link #$text(String)} - Creates a `Text` using a Selenide locator string.</li>
 *     <li>{@link #$text(String, String)} - Creates a `Text` using a Selenide locator string and a name.</li>
 *     <li>{@link #_$text(String)} - Creates a `Text` using an XPath expression.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating a text element using a Selenide locator
 * Text textElement = $text(".text-class");
 *
 * // Creating a text element with a custom name
 * Text namedText = $text(By.cssSelector(".text-class"), "Description");
 *
 * // Getting the text element's name or ID
 * String textName = namedText.getId();
 * }
 * </pre>
 */
public class Text extends UIElement {

    /**
     * Default constructor. Initializes the element type as "text".
     */
    public Text() {
        setUiElementType(ElementType.TEXT.getType());
    }

    /**
     * Constructor that initializes a `Text` element using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public Text(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.TEXT.getType());
    }

    /**
     * Constructor that initializes a `Text` element using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the text element
     */
    public Text(String selenideLocator, String name) {
        super(selenideLocator, name);
    }

    /**
     * Constructor that initializes a `Text` element using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the text element
     */
    public Text(By locator) {
        super(locator);
        setUiElementType(ElementType.TEXT.getType());
    }

    /**
     * Constructor that initializes a `Text` element using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the text element
     * @param name    the name of the text element
     */
    public Text(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.TEXT.getType());
    }

    /**
     * Constructor that initializes a `Text` element using a {@link SelenideElement}.
     *
     * @param selenideElement the Selenide element representing the text element
     */
    public Text(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.TEXT.getType());
    }

    /**
     * Constructor that initializes a `Text` element using a {@link SelenideElement} and a name.
     *
     * @param selenideElement the Selenide element representing the text element
     * @param name            the name of the text element
     */
    public Text(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.TEXT.getType());
    }

    // Static Factory Methods

    public static Text $text(By locator) {
        return new Text(locator);
    }

    public static Text $text(By locator, String name) {
        return new Text(locator, name);
    }

    public static Text $text(SelenideElement selenideElement) {
        return new Text(selenideElement);
    }

    public static Text $text(SelenideElement selenideElement, String name) {
        return new Text(selenideElement, name);
    }

    public static Text $text(String selenideLocator) {
        return new Text(selenideLocator);
    }

    public static Text $text(String selenideLocator, String name) {
        return new Text(selenideLocator, name);
    }

    public static Text _$text(String xpath) {
        return new Text(By.xpath(xpath));
    }

    @Override
    public String toString() {
        super.applyName();
        return uiElementName;
    }

    @Override
    public String getId() {
        super.applyName();
        return uiElementName;
    }
}
