package dm.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import dm.tools.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a Cell element in the UI, extending {@link UIElement}.
 * <p>
 * This class provides specific functionality for managing cell elements in tables or grids,
 * such as setting the element type to "cell" and offering utility methods for creating cell instances.
 * </p>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates cell-specific behavior.</li>
 *     <li>Provides utility methods for convenient creation and management of cell elements.</li>
 * </ul>
 */
public class Cell extends UIElement {

    /**
     * Default constructor. Initializes the cell element type.
     */
    public Cell() {
        setUiElementType(ElementType.CELL.getType());
    }

    /**
     * Constructor that initializes a cell using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    protected Cell(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.CELL.getType());
    }

    /**
     * Constructor that initializes a cell using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the cell
     */
    protected Cell(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.CELL.getType());
    }

    /**
     * Constructor that initializes a cell using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the cell
     */
    protected Cell(By locator) {
        super(locator);
        setUiElementType(ElementType.CELL.getType());
    }

    /**
     * Constructor that initializes a cell using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the cell
     * @param name    the name of the cell
     */
    protected Cell(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.CELL.getType());
    }

    /**
     * Constructor that initializes a cell using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the cell
     */
    public Cell(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.CELL.getType());
    }

    /**
     * Constructor that initializes a cell using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the cell
     * @param name            the name of the cell
     */
    protected Cell(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.CELL.getType());
    }

    /**
     * Creates a cell using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the cell
     * @return a new {@link Cell} instance
     */
    public static Cell $cell(By locator) {
        return new Cell(locator);
    }

    /**
     * Creates a cell using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the cell
     * @param name    the name of the cell
     * @return a new {@link Cell} instance
     */
    public static Cell $cell(By locator, String name) {
        return new Cell(locator, name);
    }

    /**
     * Creates a cell using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the cell
     * @return a new {@link Cell} instance
     */
    public static Cell $cell(SelenideElement selenideElement) {
        return new Cell(selenideElement);
    }

    /**
     * Creates a cell using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the cell
     * @param name            the name of the cell
     * @return a new {@link Cell} instance
     */
    public static Cell $cell(SelenideElement selenideElement, String name) {
        return new Cell(selenideElement, name);
    }

    /**
     * Creates a cell using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     * @return a new {@link Cell} instance
     */
    public static Cell $cell(String selenideLocator) {
        return new Cell(selenideLocator);
    }

    /**
     * Creates a cell using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the cell
     * @return a new {@link Cell} instance
     */
    public static Cell $cell(String selenideLocator, String name) {
        return new Cell(selenideLocator, name);
    }

    /**
     * Creates a cell using an XPath string.
     *
     * @param xpath the XPath string for the cell
     * @return a new {@link Cell} instance
     */
    public static Cell _$cell(String xpath) {
        return new Cell(By.xpath(xpath));
    }
}
