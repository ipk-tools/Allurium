package allurium.inputs;

import allurium.primitives.UIElement;
import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Represents a checkbox element in the UI, extending {@link UIElement}.
 * <p>
 * This class provides methods to interact with and validate the state of checkboxes. It supports toggling
 * the checkbox on/off and asserting its state. A proxy element can also be used for cases where the checkbox
 * itself is not directly interactable.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Check and uncheck functionality.</li>
 *     <li>State verification (checked/unchecked).</li>
 *     <li>Proxy element support for inaccessible checkboxes.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates behavior specific to checkbox elements.</li>
 *     <li>Simplifies interactions and state validations for checkboxes.</li>
 * </ul>
 */
public class CheckBox extends AbstractInputElement {

    /**
     * A proxy element used to interact with the checkbox when it is not directly interactable.
     */
    private SelenideElement proxyElement;

    /**
     * Default constructor. Initializes the element type as "checkbox".
     */
    public CheckBox() {
        setUiElementType(ElementType.CHECKBOX.getType());
    }

    /**
     * Constructor that initializes a checkbox using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the checkbox
     */
    public CheckBox(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.CHECKBOX.getType());
    }

    /**
     * Constructor that initializes a checkbox using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public CheckBox(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.CHECKBOX.getType());
    }

    /**
     * Constructor that initializes a checkbox using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the checkbox
     */
    public CheckBox(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.CHECKBOX.getType());
    }

    /**
     * Constructor that initializes a checkbox using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the checkbox
     */
    public CheckBox(By locator) {
        super(locator);
        setUiElementType(ElementType.CHECKBOX.getType());
    }

    /**
     * Constructor that initializes a checkbox using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the checkbox
     * @param name    the name of the checkbox
     */
    public CheckBox(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.CHECKBOX.getType());
    }

    /**
     * Constructor that initializes a checkbox using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the checkbox
     * @param name            the name of the checkbox
     */
    public CheckBox(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.CHECKBOX.getType());
    }

    /**
     * Constructor that initializes a checkbox using a main locator and a proxy locator.
     *
     * @param checkBoxLocator   the main locator for the checkbox
     * @param proxyElementLocator the locator for the proxy element
     */
    public CheckBox(By checkBoxLocator, By proxyElementLocator) {
        super(checkBoxLocator);
        setUiElementType(ElementType.CHECKBOX.getType());
        proxyElement = $(proxyElementLocator);
    }

    public static CheckBox $checkbox(By locator) {
        return new CheckBox(locator);
    }

    public static CheckBox $checkbox(By locator, String name) {
        return new CheckBox(locator, name);
    }

    public static CheckBox $checkbox(SelenideElement selenideElement) {
        return new CheckBox(selenideElement);
    }

    public static CheckBox $checkbox(SelenideElement selenideElement, String name) {
        return new CheckBox(selenideElement, name);
    }

    public static CheckBox $checkbox(String selenideLocator) {
        return new CheckBox(selenideLocator);
    }

    public static CheckBox _$checkbox(String xpath) {
        return new CheckBox(By.xpath(xpath));
    }

    /**
     * Checks if the checkbox is currently selected.
     *
     * @return {@code true} if the checkbox is selected, {@code false} otherwise
     */
    public boolean isChecked() {
        return root.isSelected();
    }

    /**
     * Toggles the checkbox to the checked state.
     * <p>
     * < Processed by Aspect >
     * </p>
     */
    public void check() {
        if (!root.isSelected()) {
            if (root.isDisplayed()) {
                root.click(ClickOptions.usingJavaScript());
            } else if (!root.isDisplayed() && proxyElement != null && proxyElement.exists()) {
                proxyElement.click();
            } else {
                throw new NullPointerException("Neither of checkbox main input nor the proxy element were found");
            }
        }
        else {
            logStep("checkbox is already ON");
        }
    }

    /**
     * Toggles the checkbox to the unchecked state.
     * <p>
     * < Processed by Aspect >
     * </p>
     */
    public void uncheck() {
        if (root.isSelected()) {
            if (root.isDisplayed()) {
                root.click(ClickOptions.usingJavaScript());
            } else if (!root.isDisplayed() && proxyElement != null && proxyElement.exists()) {
                proxyElement.click();
            } else {
                throw new NullPointerException("Neither of checkbox main input nor the proxy element were found");
            }
        }
        else {
            logStep("checkbox is already OFF");
        }
    }

    /**
     * Sets the checkbox to the specified state.
     *
     * @param state {@code true} to check the checkbox, {@code false} to uncheck it
     */
    public void set(boolean state) {
        if (state) {
            check();
        } else {
            uncheck();
        }
    }

    /**
     * Asserts that the checkbox is currently checked.
     * <p>
     * < Processed by Aspect >
     * </p>
     */
    public void assertChecked() {
        Assertions.assertThat(isChecked()).as(getUiElementName()+" checkBox state").isTrue();
    }

    /**
     * Asserts that the checkbox is currently unchecked.
     * <p>
     * < Processed by Aspect >
     * </p>
     */
    public void assertUnchecked() {
        Assertions.assertThat(isChecked()).as(getUiElementName()+" checkBox state").isFalse();
    }

    /*
    public boolean getCheckboxState() {
        try {
            return executeJavaScript("return document.evaluate(\"" + locator + "\", " +
                    "document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null).snapshotItem(0).checked");
        } catch (NullPointerException e) {
            throw new ElementNotFound(WebDriverRunner.driver(), Alias.NONE, locator, exist);
        }
    }
     */
}
