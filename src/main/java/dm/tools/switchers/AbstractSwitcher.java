package dm.tools.switchers;

import com.codeborne.selenide.SelenideElement;
import dm.tools.ElementType;
import dm.tools.primitives.UIElement;
import org.openqa.selenium.By;

/**
 * Base class for implementing switcher elements in the UI, extending {@link UIElement}.
 * <p>
 * This abstract class provides a foundation for creating toggleable components such as switches,
 * checkboxes, or other binary-state elements. It defines methods to query the current state and
 * perform toggle or state-changing actions, while delegating specific behavior to subclasses.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates common behaviors of switcher components.</li>
 *     <li>Standardizes the interface for querying and modifying switcher states.</li>
 *     <li>Supports Allure step logging for state-change actions.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encourages consistent implementation of switcher-like components across the codebase.</li>
 *     <li>Provides a reusable base for custom UI switcher elements.</li>
 * </ul>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #AbstractSwitcher()} - Default constructor.</li>
 *     <li>{@link #AbstractSwitcher(String)} - Initializes a switcher using a Selenide locator string.</li>
 *     <li>{@link #AbstractSwitcher(String, String)} - Initializes a switcher using a Selenide locator string and a name.</li>
 *     <li>{@link #AbstractSwitcher(By)} - Initializes a switcher using a Selenium {@link By} locator.</li>
 *     <li>{@link #AbstractSwitcher(By, String)} - Initializes a switcher using a Selenium {@link By} locator and a name.</li>
 *     <li>{@link #AbstractSwitcher(SelenideElement)} - Initializes a switcher using a Selenide element.</li>
 *     <li>{@link #AbstractSwitcher(SelenideElement, String)} - Initializes a switcher using a Selenide element and a name.</li>
 * </ul>
 *
 * <h3>Example Implementation:</h3>
 * <pre>{@code
 * public class ButtonToggle extends AbstractSwitcher {
 *
 *     public ButtonToggle(String selenideLocator) {
 *         super(selenideLocator);
 *     }
 *
 *      @Override
 *     public boolean getState() {
 *         return getRoot().has(Condition.cssClass("off"));
 *     }
 *
 *     @Override
 *     public void toggle() {
 *         getRoot().click();
 *     }
 *
 *     @Override
 *     public void switchOn() {
 *         if (getState()) {
 *             getRoot().click();
 *         }
 *     }
 *
 *     @Override
 *     public void switchOff() {
 *         if (!getState()) {
 *             getRoot().click();
 *         }
 *     }
 * }
 * </pre>
 *
 * <h3>Subclasses Must Implement:</h3>
 * <ul>
 *     <li>{@link #getState()} - Retrieves the current state of the switcher (e.g., ON or OFF).</li>
 *     <li>{@link #toggle()} - Toggles the current state of the switcher.</li>
 *     <li>{@link #switchOn()} - Sets the switcher state to ON if it's currently OFF.</li>
 *     <li>{@link #switchOff()} - Sets the switcher state to OFF if it's currently ON.</li>
 * </ul>
 */
public abstract class AbstractSwitcher extends UIElement {

    /**
     * Default constructor. Initializes the element type as "switcher".
     */
    protected AbstractSwitcher() {
        super();
        setUiElementType(ElementType.SWITCHER.getType());
    }

    /**
     * Constructor that initializes a switcher using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    protected AbstractSwitcher(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.SWITCHER.getType());
    }

    /**
     * Constructor that initializes a switcher using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the switcher
     */
    protected AbstractSwitcher(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.SWITCHER.getType());
    }

    /**
     * Constructor that initializes a switcher using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the switcher
     */
    protected AbstractSwitcher(By locator) {
        super(locator);
        setUiElementType(ElementType.SWITCHER.getType());
    }

    /**
     * Constructor that initializes a switcher using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the switcher
     * @param name    the name of the switcher
     */
    protected AbstractSwitcher(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.SWITCHER.getType());
    }

    /**
     * Constructor that initializes a switcher using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the switcher
     */
    public AbstractSwitcher(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.SWITCHER.getType());
    }

    /**
     * Constructor that initializes a switcher using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the switcher
     * @param name            the name of the switcher
     */
    protected AbstractSwitcher(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.SWITCHER.getType());
    }

    /**
     * Retrieves the current state of the switcher.
     * <p>Subclasses should implement this method to return whether the switcher is ON or OFF.</p>
     *
     * @return {@code true} if the switcher is ON; {@code false} otherwise
     */
    public abstract boolean getState();

    /**
     * Toggles the current state of the switcher.
     * <p>
     * This method changes the state of the switcher from ON to OFF or vice versa.
     * It should be implemented in subclasses.
     * </p>
     * <p><b>< Step: Processed by Aspect ></b></p>
     */
    public abstract void toggle();

    /**
     * Sets the switcher state to ON.
     * <p>
     * If the switcher is already ON, this method does nothing.
     * Otherwise, it performs the necessary actions to turn the switcher ON.
     * </p>
     * <p><b>< Step: Processed by Aspect ></b></p>
     */
    public abstract void switchOn();

    /**
     * Sets the switcher state to OFF.
     * <p>
     * If the switcher is already OFF, this method does nothing.
     * Otherwise, it performs the necessary actions to turn the switcher OFF.
     * </p>
     * <p><b>< Step: Processed by Aspect ></b></p>
     */
    public abstract void switchOff();
}
