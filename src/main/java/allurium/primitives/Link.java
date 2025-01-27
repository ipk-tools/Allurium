package allurium.primitives;

import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

/**
 * Represents a `Link` element in the UI, extending {@link UIElement}.
 * <p>
 * This class is designed to encapsulate the behavior and attributes of an anchor (`<a>`) element,
 * commonly used to navigate between pages or perform actions in a user interface.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of a `Link` element.</li>
 *     <li>Provides utility methods to retrieve and validate the `href` attribute.</li>
 *     <li>Supports multiple initialization methods through various constructors and static factory methods.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes the interaction with link elements in the UI.</li>
 *     <li>Simplifies the creation and validation of link elements in tests.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #Link()} - Default constructor.</li>
 *     <li>{@link #Link(String)} - Creates a `Link` using a Selenide locator string.</li>
 *     <li>{@link #Link(SelenideElement)} - Creates a `Link` using a {@link SelenideElement}.</li>
 *     <li>{@link #Link(By)} - Creates a `Link` using a Selenium {@link By} locator.</li>
 *     <li>{@link #Link(By, String)} - Creates a `Link` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #Link(SelenideElement, String)} - Creates a `Link` using a {@link SelenideElement} and a name.</li>
 *     <li>{@link #Link(String, String)} - Creates a `Link` using a Selenide locator string and a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #$link(By)} - Creates a `Link` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$link(By, String)} - Creates a `Link` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$link(SelenideElement)} - Creates a `Link` from a {@link SelenideElement}.</li>
 *     <li>{@link #$link(SelenideElement, String)} - Creates a `Link` from a {@link SelenideElement} with a name.</li>
 *     <li>{@link #$link(String)} - Creates a `Link` using a Selenide locator string.</li>
 *     <li>{@link #$link(String, String)} - Creates a `Link` using a Selenide locator string and a name.</li>
 *     <li>{@link #_$link(String)} - Creates a `Link` using an XPath expression.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating a link using a Selenide locator
 * Link link = $link("a.link-class");
 *
 * // Validating the href attribute
 * link.assertHref("https://example.com");
 * }
 * </pre>
 */
public class Link extends UIElement {

    public Link() {
        setUiElementType(ElementType.LINK.getType());
    }

    public Link(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.LINK.getType());
    }

    public Link(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.LINK.getType());
    }

    public Link(By locator) {
        super(locator);
        setUiElementType(ElementType.LINK.getType());
    }

    public Link(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.LINK.getType());
    }

    public Link(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.LINK.getType());
    }

    public Link(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.LINK.getType());
    }

    // Static Factory Methods

    public static Link $link(By locator) {
        return new Link(locator);
    }

    public static Link $link(By locator, String name) {
        return new Link(locator, name);
    }

    public static Link $link(SelenideElement selenideElement) {
        return new Link(selenideElement);
    }

    public static Link $link(SelenideElement selenideElement, String name) {
        return new Link(selenideElement, name);
    }

    public static Link $link(String selenideLocator) {
        return new Link(selenideLocator);
    }

    public static Link $link(String selenideLocator, String name) {
        return new Link(selenideLocator, name);
    }

    public static Link _$link(String xpath) {
        return new Link(By.xpath(xpath));
    }

    /**
     * Retrieves the `href` attribute of the link.
     *
     * @return the value of the `href` attribute
     */
    public String href() {
        return getRoot().getAttribute("href");
    }

    /**
     * Asserts that the `href` attribute of the link matches the expected value.
     * <p><b>< Step: Processed by Aspect ></b></p>
     *
     * @param href the expected `href` value
     */
    public void assertHref(String href) {
        Assertions.assertThat(getRoot().getAttribute("href")).isEqualTo(href);
    }
}
