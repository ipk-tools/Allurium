package dm.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import dm.tools.ElementType;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

import java.util.function.Supplier;

/**
 * Represents an `Image` element in the UI, extending {@link UIElement}.
 * <p>
 * This class provides constructors and methods to initialize and interact with image elements,
 * identified by locators or {@link SelenideElement}.
 * It also supports dynamically setting and retrieving a custom ID or falling back to the `url` attribute.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of an image element.</li>
 *     <li>Provides multiple constructors for initialization using:
 *         <ul>
 *             <li>Selenide locators as strings</li>
 *             <li>Selenium {@link By} locators</li>
 *             <li>{@link SelenideElement} instances</li>
 *         </ul>
 *     </li>
 *     <li>Offers static factory methods for convenient creation of `Image` objects.</li>
 *     <li>Supports setting and retrieving a custom ID for the image.</li>
 *     <li>Falls back to the `url` attribute if no custom ID is set.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes the interaction with image elements in the UI.</li>
 *     <li>Simplifies the creation of `Image` objects using various initialization methods.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #Image()} - Default constructor.</li>
 *     <li>{@link #Image(String)} - Creates an `Image` using a Selenide locator string.</li>
 *     <li>{@link #Image(String, String)} - Creates an `Image` using a Selenide locator string and a name.</li>
 *     <li>{@link #Image(By)} - Creates an `Image` using a Selenium {@link By} locator.</li>
 *     <li>{@link #Image(By, String)} - Creates an `Image` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #Image(SelenideElement)} - Creates an `Image` from a {@link SelenideElement}.</li>
 *     <li>{@link #Image(SelenideElement, String)} - Creates an `Image` from a {@link SelenideElement} with a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #$image(By)} - Creates an `Image` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$image(By, String)} - Creates an `Image` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$image(SelenideElement)} - Creates an `Image` from a {@link SelenideElement}.</li>
 *     <li>{@link #$image(SelenideElement, String)} - Creates an `Image` from a {@link SelenideElement} with a name.</li>
 *     <li>{@link #$image(String)} - Creates an `Image` using a Selenide locator string.</li>
 *     <li>{@link #$image(String, String)} - Creates an `Image` using a Selenide locator string and a name.</li>
 *     <li>{@link #_$image(String)} - Creates an `Image` using an XPath expression.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating an image using a Selenide locator
 * Image image = $image("img.icon-class");
 *
 * // Creating an image using a Selenium locator and a custom name
 * Image namedImage = $image(By.cssSelector(".image-class"), "Custom Image");
 *
 * // Asserting the image's source URL
 *  namedImage.assertSrcUrl("https://example.com/image.png");
 * }
 * </pre>
 */
public class Image extends UIElement {

    private String id = "";

    /**
     * Default constructor. Initializes the element type as "image".
     */
    public Image() {
        setUiElementType(ElementType.IMAGE.getType());
    }

    /**
     * Constructor that initializes an `Image` using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public Image(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.IMAGE.getType());
    }

    /**
     * Constructor that initializes an `Image` using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the image
     */
    public Image(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.IMAGE.getType());
    }

    /**
     * Constructor that initializes an `Image` using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the image
     */
    public Image(By locator) {
        super(locator);
        setUiElementType(ElementType.IMAGE.getType());
    }

    /**
     * Constructor that initializes an `Image` using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the image
     * @param name    the name of the image
     */
    public Image(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.IMAGE.getType());
    }

    /**
     * Constructor that initializes an `Image` using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the image
     */
    public Image(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.IMAGE.getType());
    }

    /**
     * Constructor that initializes an `Image` using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the image
     * @param name            the name of the image
     */
    public Image(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.IMAGE.getType());
    }

    // Static Factory Methods

    public static Image $image(By locator) {
        return new Image(locator);
    }

    public static Image $image(By locator, String name) {
        return new Image(locator, name);
    }

    public static Image $image(SelenideElement selenideElement) {
        return new Image(selenideElement);
    }

    public static Image $image(SelenideElement selenideElement, String name) {
        return new Image(selenideElement, name);
    }

    public static Image $image(String selenideLocator) {
        return new Image(selenideLocator);
    }

    public static Image $image(String selenideLocator, String name) {
        return new Image(selenideLocator, name);
    }

    public static Image _$image(String xpath) {
        return new Image(By.xpath(xpath));
    }

    /**
     * Sets a custom ID for the image using a {@link Supplier}.
     * <p>
     * This method allows you to dynamically generate and assign an ID to the image.
     * </p>
     *
     * @param howToMakeId a supplier function that provides the custom ID
     */
    public void setId(Supplier<String> howToMakeId) {
        id = howToMakeId.get();
        setId(id);
    }

    /**
     * Retrieves the ID of the image.
     * <p>
     * If a custom ID has been set using {@link #setId(Supplier)}, it will be returned.
     * If no custom ID is set, the method falls back to the `url` attribute of the image element.
     * </p>
     *
     * @return the custom ID if set, otherwise the `url` attribute of the image
     */
    @Override
    public String getId() {
        if (!id.isEmpty()) {
            return id;
        } else {
            return root.getAttribute("src");
        }
    }

    /**
     * Retrieves the source URL of the image.
     *
     * @return the `src` attribute of the image
     */
    public String getSrcUrl() {
        return getRoot().getAttribute("src");
    }

    /**
     * Asserts that the image's source URL matches the expected value.
     *
     * @param srcUrl the expected source URL
     */
    public void assertSrcUrl(String srcUrl) {
        Assertions.assertThat(getSrcUrl()).as("image source url").isEqualTo(srcUrl);
    }
}
