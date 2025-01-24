package dm.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import dm.tools.ElementType;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

/**
 * Represents an `Icon` element in the UI, extending {@link UIElement}.
 * <p>
 * This class provides constructors and static factory methods to initialize and interact
 * with icon elements, identified by locators or {@link SelenideElement}.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of an icon element.</li>
 *     <li>Provides multiple constructors for initialization using:
 *         <ul>
 *             <li>Selenide locators as strings</li>
 *             <li>Selenium {@link By} locators</li>
 *             <li>{@link SelenideElement} instances</li>
 *         </ul>
 *     </li>
 *     <li>Offers static factory methods for convenient creation of `Icon` objects.</li>
 *     <li>Automatically sets the element type to "icon".</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes the interaction with icon elements in the UI.</li>
 *     <li>Simplifies the creation of `Icon` objects using various initialization methods.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #Icon()} - Default constructor.</li>
 *     <li>{@link #Icon(String)} - Creates an `Icon` using a Selenide locator string.</li>
 *     <li>{@link #Icon(String, String)} - Creates an `Icon` using a Selenide locator string and a name.</li>
 *     <li>{@link #Icon(By)} - Creates an `Icon` using a Selenium {@link By} locator.</li>
 *     <li>{@link #Icon(By, String)} - Creates an `Icon` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #Icon(SelenideElement)} - Creates an `Icon` from a {@link SelenideElement}.</li>
 *     <li>{@link #Icon(SelenideElement, String)} - Creates an `Icon` from a {@link SelenideElement} with a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #$icon(By)} - Creates an `Icon` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$icon(By, String)} - Creates an `Icon` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$icon(SelenideElement)} - Creates an `Icon` from a {@link SelenideElement}.</li>
 *     <li>{@link #$icon(SelenideElement, String)} - Creates an `Icon` from a {@link SelenideElement} with a name.</li>
 *     <li>{@link #$icon(String)} - Creates an `Icon` using a Selenide locator string.</li>
 *     <li>{@link #$icon(String, String)} - Creates an `Icon` using a Selenide locator string and a name.</li>
 *     <li>{@link #_$icon(String)} - Creates an `Icon` using an XPath expression.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating an icon using a Selenide locator
 * Icon icon = Icon.$icon("div.icon-class");
 *
 * // Creating an icon using a Selenium locator and a custom name
 * Icon namedIcon = Icon.$icon(By.cssSelector(".icon-class"), "Custom Icon");
 * }
 * </pre>
 */

public class Icon extends UIElement {

    public Icon() {
        setUiElementType(ElementType.ICON.getType());
    }

    public Icon(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.ICON.getType());
    }

    public Icon(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.ICON.getType());
    }

    public Icon(By locator) {
        super(locator);
        setUiElementType(ElementType.ICON.getType());
    }

    public Icon(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.ICON.getType());
    }

    public Icon(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.ICON.getType());
    }

    public Icon(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.ICON.getType());
    }

    // Static Factory Methods

    public static Icon $icon(By locator) {
        return new Icon(locator);
    }

    public static Icon $icon(By locator, String name) {
        return new Icon(locator, name);
    }

    public static Icon $icon(SelenideElement selenideElement) {
        return new Icon(selenideElement);
    }

    public static Icon $icon(SelenideElement selenideElement, String name) {
        return new Icon(selenideElement, name);
    }

    public static Icon $icon(String selenideLocator) {
        return new Icon(selenideLocator);
    }

    public static Icon $icon(String selenideLocator, String name) {
        return new Icon(selenideLocator, name);
    }

    public static Icon _$icon(String xpath) {
        return new Icon(By.xpath(xpath));
    }

}
