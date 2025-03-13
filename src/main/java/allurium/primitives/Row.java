package allurium.primitives;

import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a `Row` element in the UI, extending {@link UIElement}.
 * <p>
 * This class encapsulates the behavior and attributes of a row element, commonly used in tables, grids,
 * or list-based UI components.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of a row element.</li>
 *     <li>Supports multiple initialization methods through constructors and static factory methods.</li>
 *     <li>Extends {@link UIElement} to provide generic interaction capabilities.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes interactions with row elements in UI components.</li>
 *     <li>Simplifies the creation and management of rows in tests.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #Row()} - Default constructor.</li>
 *     <li>{@link #Row(String)} - Creates a `Row` using a Selenide locator string.</li>
 *     <li>{@link #Row(String, String)} - Creates a `Row` using a Selenide locator string and a name.</li>
 *     <li>{@link #Row(By)} - Creates a `Row` using a Selenium {@link By} locator.</li>
 *     <li>{@link #Row(By, String)} - Creates a `Row` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #Row(SelenideElement)} - Creates a `Row` using a {@link SelenideElement}.</li>
 *     <li>{@link #Row(SelenideElement, String)} - Creates a `Row` using a {@link SelenideElement} and a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #$row(By)} - Creates a `Row` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$row(By, String)} - Creates a `Row` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$row(SelenideElement)} - Creates a `Row` using a {@link SelenideElement}.</li>
 *     <li>{@link #$row(SelenideElement, String)} - Creates a `Row` using a {@link SelenideElement} and a name.</li>
 *     <li>{@link #$row(String)} - Creates a `Row` using a Selenide locator string.</li>
 *     <li>{@link #$row(String, String)} - Creates a `Row` using a Selenide locator string and a name.</li>
 *     <li>{@link #_$row(String)} - Creates a `Row` using an XPath expression.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating a row using a Selenide locator
 * Row row = $row("div.row-class");
 *
 * // Creating a row using a Selenium locator and assigning a custom name
 * Row namedRow = $row(By.cssSelector(".row-class"), "Custom Row");
 * }
 * </pre>
 */
public class Row extends UIElement {

    /**
     * Default constructor. Initializes the element type as "row".
     */
    public Row() {
        setUiElementType(ElementType.ROW.getType());
    }

    /**
     * Constructor that initializes a `Row` using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public Row(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.ROW.getType());
    }

    /**
     * Constructor that initializes a `Row` using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the row
     */
    public Row(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.ROW.getType());
    }

    /**
     * Constructor that initializes a `Row` using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the row
     */
    public Row(By locator) {
        super(locator);
        setUiElementType(ElementType.ROW.getType());
    }

    /**
     * Constructor that initializes a `Row` using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the row
     * @param name    the name of the row
     */
    public Row(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.ROW.getType());
    }

    /**
     * Constructor that initializes a `Row` using a {@link SelenideElement}.
     *
     * @param selenideElement the Selenide element representing the row
     */
    public Row(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.ROW.getType());
    }

    /**
     * Constructor that initializes a `Row` using a {@link SelenideElement} and a name.
     *
     * @param selenideElement the Selenide element representing the row
     * @param name            the name of the row
     */
    public Row(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.ROW.getType());
    }

    // Static Factory Methods

    public static Row $row(By locator) {
        return new Row(locator);
    }

    public static Row $row(SelenideElement selenideElement) {
        return new Row(selenideElement);
    }

    public static Row $row(String selenideLocator) {
        return new Row(selenideLocator);
    }

    public static Row $row(SelenideElement selenideElement, String name) {
        return new Row(selenideElement, name);
    }

    public static Row $row(By locator, String name) {
        return new Row(locator, name);
    }

    public static Row $row(String selenideLocator, String name) {
        return new Row(selenideLocator, name);
    }

    public static Row _$row(String xpath) {
        return new Row(By.xpath(xpath));
    }
}
