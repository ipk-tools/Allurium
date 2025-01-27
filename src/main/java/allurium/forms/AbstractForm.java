package allurium.forms;

import com.codeborne.selenide.SelenideElement;
import allurium.AbstractWidget;
import allurium.ElementType;
import org.openqa.selenium.By;

/**
 * Represents the base class for form elements in a UI, extending {@link AbstractWidget}.
 * <p>
 * This class provides the foundation for creating form-related UI components and encapsulates
 * common behavior, such as initialization using various locator types. It also defines an abstract
 * `submit` method for implementing form submission functionality.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates behavior specific to form elements.</li>
 *     <li>Supports initialization using locators or {@link SelenideElement} objects.</li>
 *     <li>Defines an abstract method for custom form submission behavior.</li>
 *     <li>Integrates with aspects for monitoring submission behavior.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Serves as a base class for implementing specific form components.</li>
 *     <li>Standardizes form behavior across different test cases or components.</li>
 * </ul>
 *
 * <h3>Usage:</h3>
 * <p>
 * Extend this class to implement custom forms in your application.
 * </p>
 * <pre>{@code
 * public class LoginForm extends AbstractForm {
 *     @Name("Submit")
 *     protected Button btnSubmit = $button(".green-btn");
 *
 *     public LoginForm(SelenideElement root) {
 *         super(root);
 *     }
 *
 *     @Override
 *     public void submit() {
 *         // Custom form submission logic
 *         btnSubmit.click();
 *     }
 * }
 * }</pre>
 */
public abstract class AbstractForm extends AbstractWidget {

    /**
     * Default constructor. Initializes the element type as "form".
     */
    public AbstractForm() {
        super();
        setUiElementType("form");
    }

    /**
     * Constructor that initializes the form using a {@link SelenideElement}.
     *
     * @param rootElement the Selenide element representing the form
     */
    public AbstractForm(SelenideElement rootElement) {
        super(rootElement);
        setUiElementType(ElementType.FORM.getType());
    }

    /**
     * Constructor that initializes the form using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public AbstractForm(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.FORM.getType());
    }

    /**
     * Constructor that initializes the form using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the form
     */
    public AbstractForm(By locator) {
        super(locator);
        setUiElementType(ElementType.FORM.getType());
    }

    /**
     * Submits the form.
     * <p>
     * This method must be overridden by subclasses to define custom form submission logic.
     * It is also processed by aspects for monitoring submission actions.
     * </p>
     * <p><b>< Step: Processed by Aspect ></b></p>
     */
    public abstract void submit();
}
