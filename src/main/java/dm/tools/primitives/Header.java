package dm.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import dm.tools.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a Header element in the UI, extending {@link UIElement}.
 * <p>
 * This class provides functionality for managing header elements in a webpage,
 * such as setting the element type to "header" and offering utility methods for creating header instances.
 * </p>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates header-specific behavior.</li>
 *     <li>Provides utility methods for convenient creation and management of header elements.</li>
 * </ul>
 */
public class Header extends UIElement {

    /**
     * Default constructor. Initializes the header element type.
     */
    public Header() {
        setUiElementType(ElementType.HEADER.getType());
    }

    /**
     * Constructor that initializes a header using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public Header(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.HEADER.getType());
    }

    /**
     * Constructor that initializes a header using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the header
     */
    public Header(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.HEADER.getType());
    }

    /**
     * Constructor that initializes a header using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the header
     */
    public Header(By locator) {
        super(locator);
        setUiElementType(ElementType.HEADER.getType());
    }

    /**
     * Constructor that initializes a header using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the header
     * @param name    the name of the header
     */
    public Header(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.HEADER.getType());
    }

    /**
     * Constructor that initializes a header using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the header
     */
    public Header(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.HEADER.getType());
    }

    /**
     * Constructor that initializes a header using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the header
     * @param name            the name of the header
     */
    public Header(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.HEADER.getType());
    }

    /**
     * Creates a header using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the header
     * @return a new {@link Header} instance
     */
    public static Header $header(By locator) {
        return new Header(locator);
    }

    /**
     * Creates a header using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the header
     * @return a new {@link Header} instance
     */
    public static Header $header(SelenideElement selenideElement) {
        return new Header(selenideElement);
    }

    /**
     * Creates a header using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     * @return a new {@link Header} instance
     */
    public static Header $header(String selenideLocator) {
        return new Header(selenideLocator);
    }

    /**
     * Creates a header using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the header
     * @param name            the name of the header
     * @return a new {@link Header} instance
     */
    public static Header $header(SelenideElement selenideElement, String name) {
        return new Header(selenideElement, name);
    }

    /**
     * Creates a header using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the header
     * @param name    the name of the header
     * @return a new {@link Header} instance
     */
    public static Header $header(By locator, String name) {
        return new Header(locator, name);
    }

    /**
     * Creates a header using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the header
     * @return a new {@link Header} instance
     */
    public static Header $header(String selenideLocator, String name) {
        return new Header(selenideLocator, name);
    }

    /**
     * Creates a header using an XPath string.
     *
     * @param xpath the XPath string for the header
     * @return a new {@link Header} instance
     */
    public static Header _$header(String xpath) {
        return new Header(By.xpath(xpath));
    }
}