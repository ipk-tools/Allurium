package dm.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import dm.tools.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a `Tab` element in the UI, extending {@link UIElement}.
 * <p>
 * This class encapsulates the behavior and attributes of a tab component in a user interface,
 * commonly used for navigation or organizing content into sections.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of a `Tab` element.</li>
 *     <li>Provides multiple constructors for flexible initialization using:
 *         <ul>
 *             <li>Selenide locators as strings</li>
 *             <li>Selenium {@link By} locators</li>
 *             <li>{@link SelenideElement} instances</li>
 *         </ul>
 *     </li>
 *     <li>Includes static factory methods for convenient creation of `Tab` instances.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes interaction with tab elements in the UI.</li>
 *     <li>Simplifies the creation and management of tab components in tests.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #Tab()} - Default constructor.</li>
 *     <li>{@link #Tab(String)} - Creates a `Tab` using a Selenide locator string.</li>
 *     <li>{@link #Tab(String, String)} - Creates a `Tab` using a Selenide locator string and a name.</li>
 *     <li>{@link #Tab(By)} - Creates a `Tab` using a Selenium {@link By} locator.</li>
 *     <li>{@link #Tab(By, String)} - Creates a `Tab` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #Tab(SelenideElement)} - Creates a `Tab` using a {@link SelenideElement}.</li>
 *     <li>{@link #Tab(SelenideElement, String)} - Creates a `Tab` using a {@link SelenideElement} and a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #$tab(By)} - Creates a `Tab` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$tab(By, String)} - Creates a `Tab` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$tab(SelenideElement)} - Creates a `Tab` using a {@link SelenideElement}.</li>
 *     <li>{@link #$tab(SelenideElement, String)} - Creates a `Tab` using a {@link SelenideElement} and a name.</li>
 *     <li>{@link #$tab(String)} - Creates a `Tab` using a Selenide locator string.</li>
 *     <li>{@link #$tab(String, String)} - Creates a `Tab` using a Selenide locator string and a name.</li>
 *     <li>{@link #_$tab(String)} - Creates a `Tab` using an XPath expression.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating a tab using a Selenide locator
 * Tab tab = $tab(".tab-class");
 *
 * // Creating a tab with a custom name
 * Tab namedTab = $tab(By.cssSelector(".tab-class"), "Settings Tab");
 * }
 * </pre>
 */
public class Tab extends UIElement {

    /**
     * Default constructor. Initializes the element type as "tab".
     */
    public Tab() {
        setUiElementType(ElementType.TAB.getType());
    }

    /**
     * Constructor that initializes a `Tab` using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public Tab(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.TAB.getType());
    }

    /**
     * Constructor that initializes a `Tab` using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the tab
     */
    public Tab(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.TAB.getType());
    }

    /**
     * Constructor that initializes a `Tab` using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the tab
     */
    public Tab(By locator) {
        super(locator);
        setUiElementType(ElementType.TAB.getType());
    }

    /**
     * Constructor that initializes a `Tab` using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the tab
     * @param name    the name of the tab
     */
    public Tab(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.TAB.getType());
    }

    /**
     * Constructor that initializes a `Tab` using a {@link SelenideElement}.
     *
     * @param selenideElement the Selenide element representing the tab
     */
    public Tab(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.TAB.getType());
    }

    /**
     * Constructor that initializes a `Tab` using a {@link SelenideElement} and a name.
     *
     * @param selenideElement the Selenide element representing the tab
     * @param name            the name of the tab
     */
    public Tab(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.TAB.getType());
    }

    // Static Factory Methods

    public static Tab $tab(By locator) {
        return new Tab(locator);
    }

    public static Tab $tab(SelenideElement selenideElement) {
        return new Tab(selenideElement);
    }

    public static Tab $tab(String selenideLocator) {
        return new Tab(selenideLocator);
    }

    public static Tab $tab(SelenideElement selenideElement, String name) {
        return new Tab(selenideElement, name);
    }

    public static Tab $tab(By locator, String name) {
        return new Tab(locator, name);
    }

    public static Tab $tab(String selenideLocator, String name) {
        return new Tab(selenideLocator, name);
    }

    public static Tab _$tab(String xpath) {
        return new Tab(By.xpath(xpath));
    }
}
