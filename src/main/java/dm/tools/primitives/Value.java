package dm.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import dm.tools.ElementType;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

/**
 * Represents a `Value` element in the UI, extending {@link UIElement}.
 * <p>
 * This class encapsulates the behavior and attributes of elements representing values or metrics in the UI,
 * such as numerical displays, calculated values, or status indicators.
 * It provides constructors and factory methods for creating and interacting with value elements.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of value elements.</li>
 *     <li>Provides constructors for flexible initialization using:
 *         <ul>
 *             <li>Selenide locators as strings</li>
 *             <li>Selenium {@link By} locators</li>
 *             <li>{@link SelenideElement} instances</li>
 *         </ul>
 *     </li>
 *     <li>Includes static factory methods for convenient creation of `Value` instances.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes interaction with value elements in the UI.</li>
 *     <li>Simplifies creation and management of value elements in tests.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #Value()} - Default constructor.</li>
 *     <li>{@link #Value(String)} - Creates a `Value` using a Selenide locator string.</li>
 *     <li>{@link #Value(String, String)} - Creates a `Value` using a Selenide locator string and a name.</li>
 *     <li>{@link #Value(By)} - Creates a `Value` using a Selenium {@link By} locator.</li>
 *     <li>{@link #Value(By, String)} - Creates a `Value` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #Value(SelenideElement)} - Creates a `Value` using a {@link SelenideElement}.</li>
 *     <li>{@link #Value(SelenideElement, String)} - Creates a `Value` using a {@link SelenideElement} and a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #$value(By)} - Creates a `Value` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$value(By, String)} - Creates a `Value` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$value(SelenideElement)} - Creates a `Value` using a {@link SelenideElement}.</li>
 *     <li>{@link #$value(SelenideElement, String)} - Creates a `Value` using a {@link SelenideElement} and a name.</li>
 *     <li>{@link #$value(String)} - Creates a `Value` using a Selenide locator string.</li>
 *     <li>{@link #$value(String, String)} - Creates a `Value` using a Selenide locator string and a name.</li>
 *     <li>{@link #_$value(String)} - Creates a `Value` using an XPath expression.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating a value element using a Selenide locator
 * Value value = $value(".value-display");
 *
 * // Creating a value element with a custom name
 * Value namedValue = $value(By.cssSelector(".value-display"), "Total Value");
 * }
 * </pre>
 */
public class Value extends UIElement {

    /**
     * Default constructor. Initializes the element type as "value".
     */
    public Value() {
        setUiElementType(ElementType.VALUE.getType());
    }

    /**
     * Constructor that initializes a `Value` element using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public Value(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.VALUE.getType());
    }

    /**
     * Constructor that initializes a `Value` element using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the value element
     */
    public Value(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.VALUE.getType());
    }

    /**
     * Constructor that initializes a `Value` element using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the value element
     */
    public Value(By locator) {
        super(locator);
        setUiElementType(ElementType.VALUE.getType());
    }

    /**
     * Constructor that initializes a `Value` element using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the value element
     * @param name    the name of the value element
     */
    public Value(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.VALUE.getType());
    }

    /**
     * Constructor that initializes a `Value` element using a {@link SelenideElement}.
     *
     * @param selenideElement the Selenide element representing the value element
     */
    public Value(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.VALUE.getType());
    }

    /**
     * Constructor that initializes a `Value` element using a {@link SelenideElement} and a name.
     *
     * @param selenideElement the Selenide element representing the value element
     * @param name            the name of the value element
     */
    public Value(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.VALUE.getType());
    }

    // Static Factory Methods

    public static Value $value(By locator) {
        return new Value(locator);
    }

    public static Value $value(SelenideElement selenideElement) {
        return new Value(selenideElement);
    }

    public static Value $value(String selenideLocator) {
        return new Value(selenideLocator);
    }

    public static Value $value(SelenideElement selenideElement, String name) {
        return new Value(selenideElement, name);
    }

    public static Value $value(By locator, String name) {
        return new Value(locator, name);
    }

    public static Value $value(String selenideLocator, String name) {
        return new Value(selenideLocator, name);
    }

    public static Value _$value(String xpath) {
        return new Value(By.xpath(xpath));
    }
}
