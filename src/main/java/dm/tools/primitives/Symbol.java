package dm.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import dm.tools.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a `Symbol` element in the UI, extending {@link UIElement}.
 * <p>
 * This class encapsulates the behavior and attributes of a symbol element, which is often used to display
 * icons, special characters, or graphical representations in a user interface.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of a `Symbol` element.</li>
 *     <li>Supports multiple initialization methods through constructors and static factory methods.</li>
 *     <li>Extends {@link UIElement} to provide generic interaction capabilities.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes interactions with symbol elements in the UI.</li>
 *     <li>Simplifies the creation and management of symbol elements in tests.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #Symbol()} - Default constructor.</li>
 *     <li>{@link #Symbol(String)} - Creates a `Symbol` using a Selenide locator string.</li>
 *     <li>{@link #Symbol(String, String)} - Creates a `Symbol` using a Selenide locator string and a name.</li>
 *     <li>{@link #Symbol(By)} - Creates a `Symbol` using a Selenium {@link By} locator.</li>
 *     <li>{@link #Symbol(By, String)} - Creates a `Symbol` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #Symbol(SelenideElement)} - Creates a `Symbol` using a {@link SelenideElement}.</li>
 *     <li>{@link #Symbol(SelenideElement, String)} - Creates a `Symbol` using a {@link SelenideElement} and a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #$symbol(By)} - Creates a `Symbol` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$symbol(By, String)} - Creates a `Symbol` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$symbol(SelenideElement)} - Creates a `Symbol` using a {@link SelenideElement}.</li>
 *     <li>{@link #$symbol(SelenideElement, String)} - Creates a `Symbol` using a {@link SelenideElement} and a name.</li>
 *     <li>{@link #$symbol(String)} - Creates a `Symbol` using a Selenide locator string.</li>
 *     <li>{@link #$symbol(String, String)} - Creates a `Symbol` using a Selenide locator string and a name.</li>
 *     <li>{@link #_$symbol(String)} - Creates a `Symbol` using an XPath expression.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating a symbol using a Selenide locator
 * Symbol symbol = $symbol("span.icon-class");
 *
 * // Creating a symbol using a Selenium locator and assigning a custom name
 * Symbol namedSymbol = $symbol(By.cssSelector(".icon-class"), "Custom Icon");
 * }
 * </pre>
 */
public class Symbol extends UIElement {

    /**
     * Default constructor. Initializes the element type as "symbol".
     */
    public Symbol() {
        setUiElementType(ElementType.SYMBOL.getType());
    }

    /**
     * Constructor that initializes a `Symbol` using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public Symbol(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.SYMBOL.getType());
    }

    /**
     * Constructor that initializes a `Symbol` using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the symbol
     */
    public Symbol(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.SYMBOL.getType());
    }

    /**
     * Constructor that initializes a `Symbol` using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the symbol
     */
    public Symbol(By locator) {
        super(locator);
        setUiElementType(ElementType.SYMBOL.getType());
    }

    /**
     * Constructor that initializes a `Symbol` using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the symbol
     * @param name    the name of the symbol
     */
    public Symbol(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.SYMBOL.getType());
    }

    /**
     * Constructor that initializes a `Symbol` using a {@link SelenideElement}.
     *
     * @param selenideElement the Selenide element representing the symbol
     */
    public Symbol(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.SYMBOL.getType());
    }

    /**
     * Constructor that initializes a `Symbol` using a {@link SelenideElement} and a name.
     *
     * @param selenideElement the Selenide element representing the symbol
     * @param name            the name of the symbol
     */
    public Symbol(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.SYMBOL.getType());
    }

    // Static Factory Methods

    public static Symbol $symbol(By locator) {
        return new Symbol(locator);
    }

    public static Symbol $symbol(SelenideElement selenideElement) {
        return new Symbol(selenideElement);
    }

    public static Symbol $symbol(String selenideLocator) {
        return new Symbol(selenideLocator);
    }

    public static Symbol $symbol(SelenideElement selenideElement, String name) {
        return new Symbol(selenideElement, name);
    }

    public static Symbol $symbol(By locator, String name) {
        return new Symbol(locator, name);
    }

    public static Symbol $symbol(String selenideLocator, String name) {
        return new Symbol(selenideLocator, name);
    }

    public static Symbol _$symbol(String xpath) {
        return new Symbol(By.xpath(xpath));
    }
}
