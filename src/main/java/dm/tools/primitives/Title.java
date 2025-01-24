package dm.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import dm.tools.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a `Title` element in the UI, extending {@link UIElement}.
 * <p>
 * This class encapsulates the behavior and attributes of a title element, such as headers or text elements that act
 * as titles in the UI (e.g., `<h1>`, `<h2>`). It provides constructors and factory methods for creating and interacting
 * with title elements.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of a title element.</li>
 *     <li>Provides constructors for flexible initialization using:
 *         <ul>
 *             <li>Selenide locators as strings</li>
 *             <li>Selenium {@link By} locators</li>
 *             <li>{@link SelenideElement} instances</li>
 *         </ul>
 *     </li>
 *     <li>Includes static factory methods for convenient creation of `Title` instances.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes interaction with title elements in the UI.</li>
 *     <li>Simplifies creation and management of title elements in tests.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #Title()} - Default constructor.</li>
 *     <li>{@link #Title(String)} - Creates a `Title` using a Selenide locator string.</li>
 *     <li>{@link #Title(String, String)} - Creates a `Title` using a Selenide locator string and a name.</li>
 *     <li>{@link #Title(By)} - Creates a `Title` using a Selenium {@link By} locator.</li>
 *     <li>{@link #Title(By, String)} - Creates a `Title` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #Title(SelenideElement)} - Creates a `Title` using a {@link SelenideElement}.</li>
 *     <li>{@link #Title(SelenideElement, String)} - Creates a `Title` using a {@link SelenideElement} and a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #$title(By)} - Creates a `Title` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$title(By, String)} - Creates a `Title` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$title(SelenideElement)} - Creates a `Title` using a {@link SelenideElement}.</li>
 *     <li>{@link #$title(SelenideElement, String)} - Creates a `Title` using a {@link SelenideElement} and a name.</li>
 *     <li>{@link #$title(String)} - Creates a `Title` using a Selenide locator string.</li>
 *     <li>{@link #$title(String, String)} - Creates a `Title` using a Selenide locator string and a name.</li>
 *     <li>{@link #_$title(String)} - Creates a `Title` using an XPath expression.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating a title using a Selenide locator
 * Title title = $title("h1.page-title");
 *
 * // Creating a title with a custom name
 * Title namedTitle = $title(By.cssSelector("h1.page-title"), "Main Page Title");
 * }
 * </pre>
 */
public class Title extends UIElement {

    /**
     * Default constructor. Initializes the element type as "title".
     */
    public Title() {
        setUiElementType(ElementType.TITLE.getType());
    }

    /**
     * Constructor that initializes a `Title` element using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public Title(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.TITLE.getType());
    }

    /**
     * Constructor that initializes a `Title` element using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the title element
     */
    public Title(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.TITLE.getType());
    }

    /**
     * Constructor that initializes a `Title` element using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the title element
     */
    public Title(By locator) {
        super(locator);
        setUiElementType(ElementType.TITLE.getType());
    }

    /**
     * Constructor that initializes a `Title` element using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the title element
     * @param name    the name of the title element
     */
    public Title(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.TITLE.getType());
    }

    /**
     * Constructor that initializes a `Title` element using a {@link SelenideElement}.
     *
     * @param selenideElement the Selenide element representing the title element
     */
    public Title(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.TITLE.getType());
    }

    /**
     * Constructor that initializes a `Title` element using a {@link SelenideElement} and a name.
     *
     * @param selenideElement the Selenide element representing the title element
     * @param name            the name of the title element
     */
    public Title(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.TITLE.getType());
    }

    // Static Factory Methods

    public static Title $title(By locator) {
        return new Title(locator);
    }

    public static Title $title(By locator, String name) {
        return new Title(locator, name);
    }

    public static Title $title(SelenideElement selenideElement) {
        return new Title(selenideElement);
    }

    public static Title $title(SelenideElement selenideElement, String name) {
        return new Title(selenideElement, name);
    }

    public static Title $title(String selenideLocator) {
        return new Title(selenideLocator);
    }

    public static Title $title(String selenideLocator, String name) {
        return new Title(selenideLocator, name);
    }

    public static Title _$title(String xpath) {
        return new Title(By.xpath(xpath));
    }
}
