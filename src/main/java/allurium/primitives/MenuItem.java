package allurium.primitives;

import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a `MenuItem` element in the UI, extending {@link UIElement}.
 * <p>
 * This class encapsulates the behavior and attributes of menu item elements (`<li>` or similar elements)
 * commonly used in navigation menus or dropdowns in a user interface.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of menu item elements.</li>
 *     <li>Supports initialization using various constructors and static factory methods.</li>
 *     <li>Provides a consistent interface for interacting with menu items in UI tests.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes the interaction with menu item elements in the UI.</li>
 *     <li>Simplifies the creation and manipulation of menu item elements in tests.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #MenuItem()} - Default constructor.</li>
 *     <li>{@link #MenuItem(String)} - Creates a `MenuItem` using a Selenide locator string.</li>
 *     <li>{@link #MenuItem(String, String)} - Creates a `MenuItem` using a Selenide locator string and a name.</li>
 *     <li>{@link #MenuItem(By)} - Creates a `MenuItem` using a Selenium {@link By} locator.</li>
 *     <li>{@link #MenuItem(By, String)} - Creates a `MenuItem` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #MenuItem(SelenideElement)} - Creates a `MenuItem` from a {@link SelenideElement}.</li>
 *     <li>{@link #MenuItem(SelenideElement, String)} - Creates a `MenuItem` from a {@link SelenideElement} with a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #_$menuItem(String)} - Creates a `MenuItem` using an XPath expression.</li>
 *     <li>{@link #$menuItem(By)} - Creates a `MenuItem` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$menuItem(By, String)} - Creates a `MenuItem` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$menuItem(SelenideElement)} - Creates a `MenuItem` from a {@link SelenideElement}.</li>
 *     <li>{@link #$menuItem(SelenideElement, String)} - Creates a `MenuItem` from a {@link SelenideElement} with a name.</li>
 *     <li>{@link #$menuItem(String)} - Creates a `MenuItem` using a Selenide locator string.</li>
 *     <li>{@link #$menuItem(String, String)} - Creates a `MenuItem` using a Selenide locator string and a name.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating a menu item using a Selenide locator
 * MenuItem menuItem = MenuItem.$menuItem("ul > li.menu-item");
 *
 * // Creating a menu item with a custom name
 * MenuItem namedMenuItem = MenuItem.$menuItem(By.cssSelector(".menu-item"), "Custom Menu Item");
 * }
 * </pre>
 */
public class MenuItem extends UIElement {

    public MenuItem() {
        setUiElementType(ElementType.MENU_ITEM.getType());
    }

    public MenuItem(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.MENU_ITEM.getType());
    }

    public MenuItem(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.MENU_ITEM.getType());
    }

    public MenuItem(By locator) {
        super(locator);
        setUiElementType(ElementType.MENU_ITEM.getType());
    }

    public MenuItem(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.MENU_ITEM.getType());
    }

    public MenuItem(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.MENU_ITEM.getType());
    }

    public MenuItem(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.MENU_ITEM.getType());
    }

    // Static Factory Methods

    public static MenuItem _$menuItem(String xpath) {
        return new MenuItem(By.xpath(xpath));
    }

    public static MenuItem $menuItem(By locator) {
        return new MenuItem(locator);
    }

    public static MenuItem $menuItem(By locator, String name) {
        return new MenuItem(locator, name);
    }

    public static MenuItem $menuItem(SelenideElement selenideElement) {
        return new MenuItem(selenideElement);
    }

    public static MenuItem $menuItem(SelenideElement selenideElement, String name) {
        return new MenuItem(selenideElement, name);
    }

    public static MenuItem $menuItem(String selenideLocator) {
        return new MenuItem(selenideLocator);
    }

    public static MenuItem $menuItem(String selenideLocator, String name) {
        return new MenuItem(selenideLocator, name);
    }

}
