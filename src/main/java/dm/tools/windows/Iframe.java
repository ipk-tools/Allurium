package dm.tools.windows;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import dm.tools.AbstractWidget;
import org.openqa.selenium.By;


/**
 * Represents an `<iframe>` element in the UI, extending {@link AbstractWidget}.
 * <p>
 * This class encapsulates interactions with iframe elements, providing utility methods to switch the WebDriver
 * context into and out of the iframe.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates iframe behavior and interactions.</li>
 *     <li>Provides methods to switch WebDriver focus into and out of the iframe.</li>
 *     <li>Extends {@link AbstractWidget} to leverage widget functionality.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes interactions with iframe elements in the UI.</li>
 *     <li>Simplifies switching WebDriver context for iframe-based tests.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * Iframe iframe = new CustomIframe("#iframe-id");
 * iframe.switchDriverToIframe();
 *
 * // Perform actions inside the iframe
 *
 * iframe.switchDriverBack();
 * }</pre>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #Iframe(SelenideElement)} - Initializes the iframe using a {@link SelenideElement}.</li>
 *     <li>{@link #Iframe(String)} - Initializes the iframe using a Selenide locator string.</li>
 *     <li>{@link #Iframe(By)} - Initializes the iframe using a Selenium {@link By} locator.</li>
 * </ul>
 */
public abstract class Iframe extends AbstractWidget {

    /**
     * Constructor that initializes the iframe using a {@link SelenideElement}.
     *
     * @param rootElement the Selenide element representing the iframe
     */
    public Iframe(SelenideElement rootElement) {
        super(rootElement);
        setUiElementType("iframe");
    }


    /**
     * Constructor that initializes the iframe using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator string for the iframe
     */
    public Iframe(String selenideLocator) {
        super(selenideLocator);
        setUiElementType("iframe");
    }

    /**
     * Constructor that initializes the iframe using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the iframe
     */
    public Iframe(By locator) {
        super(locator);
        setUiElementType("iframe");
    }

    /**
     * Switches the WebDriver context to the iframe.
     * <p>
     * This method utilizes Selenium's `switchTo().frame()` functionality to
     * set the current context to the iframe represented by this object.
     * </p>
     *
     * <h3>Usage Example:</h3>
     * <pre>{@code
     * Iframe iframe = new CustomIframe("#iframe-id");
     * iframe.switchDriverToIframe();
     * // Perform actions inside the iframe
     * }</pre>
     */
    public final void switchDriverToIframe() {
        WebDriverRunner.getWebDriver().switchTo().frame(getRoot());
    }

    /**
     * Switches the WebDriver context back to the default content.
     * <p>
     * This method resets the WebDriver context to the main document
     * using Selenium's `switchTo().defaultContent()` functionality.
     * </p>
     *
     * <h3>Usage Example:</h3>
     * <pre>{@code
     * iframe.switchDriverBack();
     * }</pre>
     */
    public final void switchDriverBack() {
        WebDriverRunner.getWebDriver().switchTo().defaultContent();
    }

    //todo: under development
    //private void testAspect() {
    //    System.out.println("This method must be intercepted by the Aspect. Verify the log above and below.");
    //}

}
