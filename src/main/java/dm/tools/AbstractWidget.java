package dm.tools;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import dm.tools.primitives.UIElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

/**
 * Base class for creating composite UI components (widgets) by aggregating multiple {@link UIElement} elements or
 * elements extended of {@link UIElement} elements.
 * <p>
 * The `AbstractWidget` class provides a foundation for defining complex UI components, such as forms, panels, or custom widgets,
 * that are made up of multiple elements. It extends {@link UIElement} to inherit base element behavior while adding widget-specific
 * functionalities.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Provides a base for composite UI entities.</li>
 *     <li>Encapsulates the behavior of widgets with multiple sub-elements.</li>
 *     <li>Overrides element size methods to handle cases where a root element may not be initialized.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates the structure and behavior of composite UI components.</li>
 *     <li>Standardizes interaction with widgets in UI tests.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * public class CustomWidget extends AbstractWidget {
 *     private final Button submitButton;
 *     private final TextField inputField;
 *
 *     public CustomWidget(SelenideElement rootElement) {
 *         super(rootElement);
 *         submitButton = new Button(rootElement.$(".submit-button"));
 *         inputField = new TextField(rootElement.$(".input-field"));
 *     }
 *
 *     public void submit(String input) {
 *         inputField.write(input);
 *         submitButton.click();
 *     }
 * }
 * }
 * </pre>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #AbstractWidget()} - Default constructor.</li>
 *     <li>{@link #AbstractWidget(SelenideElement)} - Initializes the widget with a {@link SelenideElement} root element.</li>
 *     <li>{@link #AbstractWidget(String)} - Initializes the widget using a Selenide locator string.</li>
 *     <li>{@link #AbstractWidget(By)} - Initializes the widget using a Selenium {@link By} locator.</li>
 * </ul>
 *
 * @author Iaroslav Pilipenko
 */
@Slf4j
public abstract class AbstractWidget extends UIElement {

    /**
     * Metadata describing the widget. Default value includes "role:widget".
     */
    @Getter private final StringBuffer metaKeys = new StringBuffer().append("role:widget");

    /**
     * Default constructor. Initializes the element type as "widget".
     */
    public AbstractWidget() {
        setUiElementType("widget");
    }

    /**
     * Constructor that initializes the widget using a {@link SelenideElement}.
     *
     * @param rootElement the Selenide element representing the root of the widget
     */
    public AbstractWidget(SelenideElement rootElement) {
        super(rootElement);
        setUiElementType("widget");
    }

    /**
     * Constructor that initializes the widget using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator string for the root element
     */
    public AbstractWidget(String selenideLocator) {
        super(selenideLocator);
        setUiElementType("widget");
    }

    /**
     * Constructor that initializes the widget using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the root element
     */
    public AbstractWidget(By locator) {
        super(locator);
        setUiElementType("widget");
    }

    /**
     * Retrieves the width of the widget.
     * <p>If the root element is not initialized, logs a warning and returns 0.</p>
     *
     * @return the width of the widget or 0 if the root element is not set
     */
    @Override
    public int getWidth() {
        if (root != null) {
            return super.getWidth();
        } else {
            log.warn("Widget " + uiElementName + " doesn't have root element to get width! Zero has returned!");
            return 0;
        }
    }

    /**
     * Retrieves the height of the widget.
     * <p>If the root element is not initialized, logs a warning and returns 0.</p>
     *
     * @return the height of the widget or 0 if the root element is not set
     */
    @Override
    public int getHeight() {
        if (root != null) {
            return super.getHeight();
        } else {
            log.warn("Widget " + uiElementName + " doesn't have root element to get height! Zero has returned!");
            return 0;
        }
    }

    /**
     * Retrieves the root element of the widget.
     * <p>
     * If the root is not initialized, returns the `<body>` element as a fallback.
     * </p>
     *
     * @return the root element of the widget or the `<body>` element if the root is not set
     */
    public SelenideElement getRoot() {
        return root != null ? root : Selenide.$("body");
    }

    /**
     * Sets the root element of the widget.
     *
     * @param root the Selenide element to set as the root of the widget
     */
    public void setRoot(SelenideElement root) {
        this.root = root;
    }

}
