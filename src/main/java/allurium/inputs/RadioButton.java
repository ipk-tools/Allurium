package allurium.inputs;

import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import org.openqa.selenium.By;

/**
 * Represents a radio button input field in the UI, extending {@link AbstractInputElement}.
 * <p>
 * This class encapsulates the behavior and interactions specific to radio button elements,
 * providing utility methods for initialization and handling through locators or pre-existing
 * {@link SelenideElement} instances.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates radio button behavior in the UI.</li>
 *     <li>Supports multiple initialization options (locators, Selenide elements, etc.).</li>
 *     <li>Integration-ready with Allure for enhanced reporting.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * // Using a CSS selector
 * RadioButton radioButton = new RadioButton("#radio1");
 * radioButton.assertEnabled();
 *
 * // Using a Selenium By locator
 * RadioButton radioButtonBy = $radioButton(By.id("radio1"));
 * radioButtonBy.assertDisabled();
 * }</pre>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes interactions with radio button elements.</li>
 *     <li>Provides a consistent API for testing radio button inputs.</li>
 * </ul>
 */
public class RadioButton extends AbstractInputElement {

    /**
     * Default constructor. Initializes the element type as "radiobutton".
     */
    public RadioButton() {
        setUiElementType(ElementType.RADIOBUTTON.getType());
    }

    /**
     * Initializes a radio button using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public RadioButton(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.RADIOBUTTON.getType());
    }

    /**
     * Initializes a radio button using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the radio button
     */
    public RadioButton(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.RADIOBUTTON.getType());
    }

    /**
     * Initializes a radio button using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the radio button
     */
    public RadioButton(By locator) {
        super(locator);
        setUiElementType(ElementType.RADIOBUTTON.getType());
    }

    /**
     * Initializes a radio button using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the radio button
     * @param name    the name of the radio button
     */
    public RadioButton(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.RADIOBUTTON.getType());
    }

    /**
     * Initializes a radio button using a {@link SelenideElement}.
     *
     * @param selenideElement the Selenide element representing the radio button
     */
    public RadioButton(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.RADIOBUTTON.getType());
    }

    /**
     * Initializes a radio button using a {@link SelenideElement} and a name.
     *
     * @param selenideElement the Selenide element representing the radio button
     * @param name            the name of the radio button
     */
    public RadioButton(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.RADIOBUTTON.getType());
    }

    public static RadioButton $radioButton(By locator) {
        return new RadioButton(locator);
    }

    public static RadioButton $radioButton(SelenideElement selenideElement) {
        return new RadioButton(selenideElement);
    }

    public static RadioButton $radioButton(String selenideLocator) {
        return new RadioButton(selenideLocator);
    }

    public static RadioButton $radioButton(SelenideElement selenideElement, String name) {
        return new RadioButton(selenideElement, name);
    }

    public static RadioButton $radioButton(By locator, String name) {
        return new RadioButton(locator, name);
    }

    public static RadioButton $radioButton(String selenideLocator, String name) {
        return new RadioButton(selenideLocator, name);
    }

    public static RadioButton _$radioButton(String xpath) {
        return new RadioButton(By.xpath(xpath));
    }
}
