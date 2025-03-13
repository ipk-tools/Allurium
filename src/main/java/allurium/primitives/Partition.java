package allurium.primitives;

import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a `Partition` element in the UI, extending {@link UIElement}.
 * <p>
 * This class encapsulates the behavior and attributes of partition elements,
 * which are commonly used as containers or structural components in UI layouts.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior and attributes of partition elements.</li>
 *     <li>Provides multiple constructors for initialization using:
 *         <ul>
 *             <li>Selenide locators as strings</li>
 *             <li>Selenium {@link By} locators</li>
 *             <li>{@link SelenideElement} instances</li>
 *         </ul>
 *     </li>
 *     <li>Offers static factory methods for convenient creation of `Partition` objects.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes the interaction with partition elements in the UI.</li>
 *     <li>Simplifies the creation and manipulation of partition elements in tests.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #Partition()} - Default constructor.</li>
 *     <li>{@link #Partition(String)} - Creates a `Partition` using a Selenide locator string.</li>
 *     <li>{@link #Partition(String, String)} - Creates a `Partition` using a Selenide locator string and a name.</li>
 *     <li>{@link #Partition(By)} - Creates a `Partition` using a Selenium {@link By} locator.</li>
 *     <li>{@link #Partition(By, String)} - Creates a `Partition` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #Partition(SelenideElement)} - Creates a `Partition` from a {@link SelenideElement}.</li>
 *     <li>{@link #Partition(SelenideElement, String)} - Creates a `Partition` from a {@link SelenideElement} with a name.</li>
 * </ul>
 *
 * <h3>Static Factory Methods:</h3>
 * <ul>
 *     <li>{@link #$partition(By)} - Creates a `Partition` using a Selenium {@link By} locator.</li>
 *     <li>{@link #$partition(By, String)} - Creates a `Partition` using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #$partition(SelenideElement)} - Creates a `Partition` from a {@link SelenideElement}.</li>
 *     <li>{@link #$partition(SelenideElement, String)} - Creates a `Partition` from a {@link SelenideElement} with a name.</li>
 *     <li>{@link #$partition(String)} - Creates a `Partition` using a Selenide locator string.</li>
 *     <li>{@link #$partition(String, String)} - Creates a `Partition` using a Selenide locator string and a name.</li>
 *     <li>{@link #_$partition(String)} - Creates a `Partition` using an XPath expression.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Creating a partition using a Selenide locator
 * Partition partition = $partition("div.partition-class");
 *
 * // Creating a partition with a custom name
 * Partition namedPartition = $partition(By.cssSelector(".partition-class"), "Custom Partition");
 * }
 * </pre>
 */
public class Partition extends UIElement {

    public Partition() {
        setUiElementType(ElementType.PARTITION.getType());
    }

    public Partition(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.PARTITION.getType());
    }

    public Partition(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.PARTITION.getType());
    }

    public Partition(By locator) {
        super(locator);
        setUiElementType(ElementType.PARTITION.getType());
    }

    public Partition(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.PARTITION.getType());
    }

    public Partition(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.PARTITION.getType());
    }

    public Partition(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.PARTITION.getType());
    }

    // Static Factory Methods

    public static Partition $partition(By locator) {
        return new Partition(locator);
    }

    public static Partition $partition(By locator, String name) {
        return new Partition(locator, name);
    }

    public static Partition $partition(SelenideElement selenideElement) {
        return new Partition(selenideElement);
    }

    public static Partition $partition(SelenideElement selenideElement, String name) {
        return new Partition(selenideElement, name);
    }

    public static Partition $partition(String selenideLocator) {
        return new Partition(selenideLocator);
    }

    public static Partition $partition(String selenideLocator, String name) {
        return new Partition(selenideLocator, name);
    }

    public static Partition _$partition(String xpath) {
        return new Partition(By.xpath(xpath));
    }
}
