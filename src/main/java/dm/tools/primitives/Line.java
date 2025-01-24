package dm.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import dm.tools.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a `Line` element in the UI, extending {@link UIElement}.
 * <p>
 * This class is designed to represent structural or decorative line elements in a user interface.
 * It encapsulates behaviors and attributes specific to line elements, enabling standardized interaction
 * and representation in automated testing or UI frameworks.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behaviors and attributes of a `Line` element.</li>
 *     <li>Provides multiple constructors for initialization:
 *         <ul>
 *             <li>Using Selenide locators as strings.</li>
 *             <li>Using Selenium {@link By} locators.</li>
 *             <li>Using {@link SelenideElement} instances.</li>
 *         </ul>
 *     </li>
 *     <li>Offers static factory methods for convenient creation of `Line` objects.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes the interaction with line elements in the UI.</li>
 *     <li>Streamlines the creation and manipulation of `Line` objects in test or automation code.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #Line()} - Default constructor.</li>
 *     <li>{@link #Line(String)} - Creates a `Line` using a Selenide locator string.</li>
 *     <li>{@link #Line(String, String)} - Creates a `Line` using a Selenide locator string and a name.</li>
 *     <li>{@link #Line(By)} - Creates a `Line` using a Selenium {@link By} locator.</li>
 *     <li>{@link #Line(By, String)} - Creates a `Line` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #Line(SelenideElement)} - Creates a `Line` from a {@link SelenideElement}.</li>
 *     <li>{@link #Line(SelenideElement, String)} - Creates a `Line` from a {@link SelenideElement} with a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #$line(By)} - Creates a `Line` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$line(By, String)} - Creates a `Line` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$line(SelenideElement)} - Creates a `Line` from a {@link SelenideElement}.</li>
 *     <li>{@link #$line(SelenideElement, String)} - Creates a `Line` from a {@link SelenideElement} with a name.</li>
 *     <li>{@link #$line(String)} - Creates a `Line` using a Selenide locator string.</li>
 *     <li>{@link #$line(String, String)} - Creates a `Line` using a Selenide locator string and a name.</li>
 *     <li>{@link #_$line(String)} - Creates a `Line` using an XPath expression.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating a line using a Selenide locator
 * Line line = $line("div.line-class");
 *
 * // Creating a line using a Selenium locator and a custom name
 * Line namedLine = $line(By.cssSelector(".line-class"), "Custom Line");
 * }
 * </pre>
 */
public class Line extends UIElement {

    public Line() {
        setUiElementType(ElementType.LINE.getType());
    }

    public Line(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.LINE.getType());
    }

    public Line(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.LINE.getType());
    }

    public Line(By locator) {
        super(locator);
        setUiElementType(ElementType.LINE.getType());
    }

    public Line(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.LINE.getType());
    }

    public Line(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.LINE.getType());
    }

    public Line(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.LINE.getType());
    }

    // Static Factory Methods

    public static Line $line(By locator) {
        return new Line(locator);
    }

    public static Line $line(By locator, String name) {
        return new Line(locator, name);
    }

    public static Line $line(SelenideElement selenideElement) {
        return new Line(selenideElement);
    }

    public static Line $line(SelenideElement selenideElement, String name) {
        return new Line(selenideElement, name);
    }

    public static Line $line(String selenideLocator) {
        return new Line(selenideLocator);
    }

    public static Line $line(String selenideLocator, String name) {
        return new Line(selenideLocator, name);
    }

    public static Line _$line(String xpath) {
        return new Line(By.xpath(xpath));
    }
}
