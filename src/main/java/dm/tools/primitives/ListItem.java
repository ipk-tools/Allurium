package dm.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import dm.tools.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a `ListItem` element in the UI, extending {@link UIElement}.
 * <p>
 * This class is designed to encapsulate the behavior and attributes of list item elements (`<li>`),
 * commonly used in unordered or ordered lists within a user interface.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of list item elements.</li>
 *     <li>Supports initialization using various constructors and static factory methods.</li>
 *     <li>Provides a consistent interface for interacting with list items in UI tests.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes the interaction with list item elements in the UI.</li>
 *     <li>Simplifies the creation and manipulation of list item elements in tests.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #ListItem()} - Default constructor.</li>
 *     <li>{@link #ListItem(String)} - Creates a `ListItem` using a Selenide locator string.</li>
 *     <li>{@link #ListItem(String, String)} - Creates a `ListItem` using a Selenide locator string and a name.</li>
 *     <li>{@link #ListItem(By)} - Creates a `ListItem` using a Selenium {@link By} locator.</li>
 *     <li>{@link #ListItem(By, String)} - Creates a `ListItem` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #ListItem(SelenideElement)} - Creates a `ListItem` from a {@link SelenideElement}.</li>
 *     <li>{@link #ListItem(SelenideElement, String)} - Creates a `ListItem` from a {@link SelenideElement} with a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #_$listItem(String)} - Creates a `ListItem` using an XPath expression.</li>
 *     <li>{@link #$listItem(By)} - Creates a `ListItem` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$listItem(By, String)} - Creates a `ListItem` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$listItem(SelenideElement)} - Creates a `ListItem` from a {@link SelenideElement}.</li>
 *     <li>{@link #$listItem(SelenideElement, String)} - Creates a `ListItem` from a {@link SelenideElement} with a name.</li>
 *     <li>{@link #$listItem(String)} - Creates a `ListItem` using a Selenide locator string.</li>
 *     <li>{@link #$listItem(String, String)} - Creates a `ListItem` using a Selenide locator string and a name.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating a list item using a Selenide locator
 * ListItem listItem = ListItem.$listItem("ul > li:nth-child(1)");
 *
 * // Creating a list item with a custom name
 * ListItem namedListItem = ListItem.$listItem(By.cssSelector("ul > li.item"), "Custom List Item");
 * }
 * </pre>
 */
public class ListItem extends UIElement {

    public ListItem() {
        setUiElementType(ElementType.LIST_ITEM.getType());
    }

    public ListItem(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.LIST_ITEM.getType());
    }

    public ListItem(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.LIST_ITEM.getType());
    }

    public ListItem(By locator) {
        super(locator);
        setUiElementType(ElementType.LIST_ITEM.getType());
    }

    public ListItem(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.LIST_ITEM.getType());
    }

    public ListItem(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.LIST_ITEM.getType());
    }

    public ListItem(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.LIST_ITEM.getType());
    }

    // Static Factory Methods

    public static ListItem _$listItem(String xpath) {
        return new ListItem(By.xpath(xpath));
    }

    public static ListItem $listItem(By locator) {
        return new ListItem(locator);
    }

    public static ListItem $listItem(By locator, String name) {
        return new ListItem(locator, name);
    }

    public static ListItem $listItem(SelenideElement selenideElement) {
        return new ListItem(selenideElement);
    }

    public static ListItem $listItem(SelenideElement selenideElement, String name) {
        return new ListItem(selenideElement, name);
    }

    public static ListItem $listItem(String selenideLocator) {
        return new ListItem(selenideLocator);
    }

    public static ListItem $listItem(String selenideLocator, String name) {
        return new ListItem(selenideLocator, name);
    }
}
