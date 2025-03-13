package allurium.primitives;

import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a `Label` element in the UI, extending {@link UIElement}.
 * <p>
 * This class encapsulates the behavior and attributes of a label element,
 * typically used to display non-interactive text in the UI.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of a label element.</li>
 *     <li>Provides multiple constructors for initialization using:
 *         <ul>
 *             <li>Selenide locators as strings</li>
 *             <li>Selenium {@link By} locators</li>
 *             <li>{@link SelenideElement} instances</li>
 *         </ul>
 *     </li>
 *     <li>Offers static factory methods for convenient creation of `Label` objects.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes the interaction with label elements in the UI.</li>
 *     <li>Simplifies the creation of `Label` objects using various initialization methods.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #Label()} - Default constructor.</li>
 *     <li>{@link #Label(String)} - Creates a `Label` using a Selenide locator string.</li>
 *     <li>{@link #Label(String, String)} - Creates a `Label` using a Selenide locator string and a name.</li>
 *     <li>{@link #Label(By)} - Creates a `Label` using a Selenium {@link By} locator.</li>
 *     <li>{@link #Label(By, String)} - Creates a `Label` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #Label(SelenideElement)} - Creates a `Label` from a {@link SelenideElement}.</li>
 *     <li>{@link #Label(SelenideElement, String)} - Creates a `Label` from a {@link SelenideElement} with a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #$label(By)} - Creates a `Label` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$label(SelenideElement)} - Creates a `Label` from a {@link SelenideElement}.</li>
 *     <li>{@link #$label(String)} - Creates a `Label` using a Selenide locator string.</li>
 *     <li>{@link #$label(SelenideElement, String)} - Creates a `Label` from a {@link SelenideElement} with a name.</li>
 *     <li>{@link #$label(By, String)} - Creates a `Label` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$label(String, String)} - Creates a `Label` using a Selenide locator string and a name.</li>
 *     <li>{@link #_$label(String)} - Creates a `Label` using an XPath expression.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating a label using a Selenide locator
 * Label label = $label("label.class");
 *
 * // Creating a label using a Selenium locator and a custom name
 * Label namedLabel = $label(By.cssSelector(".label-class"), "Custom Label");
 * }
 * </pre>
 */
public class Label extends UIElement {

    public Label() {
        setUiElementType(ElementType.LABEL.getType());
    }

    public Label(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.LABEL.getType());
    }

    public Label(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.LABEL.getType());
    }

    public Label(By locator) {
        super(locator);
        setUiElementType(ElementType.LABEL.getType());
    }

    public Label(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.LABEL.getType());
    }

    public Label(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.LABEL.getType());
    }

    public Label(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.LABEL.getType());
    }

    // Static Factory Methods

    public static Label $label(By locator) {
        return new Label(locator);
    }

    public static Label $label(SelenideElement selenideElement) {
        return new Label(selenideElement);
    }

    public static Label $label(String selenideLocator) {
        return new Label(selenideLocator);
    }

    public static Label $label(SelenideElement selenideElement, String name) {
        return new Label(selenideElement, name);
    }

    public static Label $label(By locator, String name) {
        return new Label(locator, name);
    }

    public static Label $label(String selenideLocator, String name) {
        return new Label(selenideLocator, name);
    }

    public static Label _$label(String xpath) {
        return new Label(By.xpath(xpath));
    }
}
