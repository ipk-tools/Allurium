package allurium.inputs;

import allurium.interfaces.TextInputAsserts;
import allurium.interfaces.Writable;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

/**
 * Represents a text input field in the UI, extending {@link AbstractInputElement}.
 * <p>
 * This class provides wrapper functionality for managing text input fields
 * and logging each action as a step in Allure reports.
 * </p>
 * <h3>Features:</h3>
 * <ul>
 *     <li>Typing, clearing, and retrieving text from input fields.</li>
 *     <li>Assertions for input values, including checks for emptiness or specific values.</li>
 *     <li>All actions are logged as Allure steps for enhanced reporting.</li>
 * </ul>
 *
 * <h3>Example Usage:</h3>
 * <pre>{@code
 * <html>
 *     <input type="text">
 * </html>
 * }</pre>
 *
 * <pre>{@code
 * TextField textField = new TextField("input");
 * textField.write("Hello World");
 * textField.clear();
 * }</pre>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates text input field behavior.</li>
 *     <li>Standardizes interactions with input fields for consistency in tests.</li>
 * </ul>
 */
public class TextField extends AbstractInputElement implements Writable, TextInputAsserts {

    /**
     * Default constructor. Initializes the element type as "input".
     */
    public TextField() {
        setUiElementType(ElementType.INPUT.getType());
    }

    /**
     * Constructor that initializes a text field using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public TextField(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.INPUT.getType());
    }

    /**
     * Constructor that initializes a text field using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the text field
     */
    public TextField(String selenideLocator, String name) {
        super(selenideLocator, name);
        setUiElementType(ElementType.INPUT.getType());
    }

    /**
     * Constructor that initializes a text field using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the text field
     */
    public TextField(By locator) {
        super(locator);
        setUiElementType(ElementType.INPUT.getType());
    }

    /**
     * Constructor that initializes a text field using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the text field
     * @param name    the name of the text field
     */
    public TextField(By locator, String name) {
        super(locator, name);
        setUiElementType(ElementType.INPUT.getType());
    }

    /**
     * Constructor that initializes a text field using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the text field
     */
    public TextField(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.INPUT.getType());
    }

    /**
     * Constructor that initializes a text field using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the text field
     * @param name            the name of the text field
     */
    public TextField(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setUiElementType(ElementType.INPUT.getType());
    }

    /**
     * Creates a text field instance using a Selenium {@link By} locator.
     *
     * @param locator the Selenium locator for the text field
     * @return a new {@link TextField} instance
     */
    public static TextField $textField(By locator) {
        return new TextField(locator);
    }

    /**
     * Creates a text field instance using a Selenium {@link By} locator and a name.
     *
     * @param locator the Selenium locator for the text field
     * @param name    the name of the text field
     * @return a new {@link TextField} instance
     */
    public static TextField $textField(By locator, String name) {
        return new TextField(locator, name);
    }

    /**
     * Creates a text field instance using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the text field
     * @return a new {@link TextField} instance
     */
    public static TextField $textField(SelenideElement selenideElement) {
        return new TextField(selenideElement);
    }

    /**
     * Creates a text field instance using a Selenide element and a name.
     *
     * @param selenideElement the Selenide element representing the text field
     * @param name            the name of the text field
     * @return a new {@link TextField} instance
     */
    public static TextField $textField(SelenideElement selenideElement, String name) {
        return new TextField(selenideElement, name);
    }

    /**
     * Creates a text field instance using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     * @return a new {@link TextField} instance
     */
    public static TextField $textField(String selenideLocator) {
        return new TextField(selenideLocator);
    }

    /**
     * Creates a text field instance using a Selenide locator string and a name.
     *
     * @param selenideLocator the Selenide locator as a string
     * @param name            the name of the text field
     * @return a new {@link TextField} instance
     */
    public static TextField $textField(String selenideLocator, String name) {
        return new TextField(selenideLocator, name);
    }

    /**
     * Creates a text field instance using an XPath string.
     *
     * @param xpath the XPath string for the text field
     * @return a new {@link TextField} instance
     */
    public static TextField _$textField(String xpath) {
        return new TextField(By.xpath(xpath));
    }

    /**
     * Writes the specified text into the text field.
     * <p><b>< Step: Processed by Aspect ></b></p>
     *
     * @param text the text to be typed into the text field
     */
    public void write(String text) {
        root.sendKeys(text);
    }

    /*
    public void writeIntently(String text) {
        int counter = 5;
        while (!root.getAttribute("value").equals(text) && counter > 0) {
            root.sendKeys(text);
            counter--;
        }
    }
     */

    /**
     * Clears the text field and writes the specified text.
     *
     * @param text the text to be typed after clearing the field
     */
    public void clearAndWrite(String text) {
        clear();
        write(text);
    }

    /**
     * Clears the text field.
     * <p><b>< Step: Processed by Aspect ></b></p>
     */
    @Override
    public void clear() {
        root.click();
        while(!root.getAttribute("value").equals("")) {
            root.clear();
            root.doubleClick();
            root.sendKeys(Keys.BACK_SPACE);
        }
    }

    /**
     * Retrieves the current value of the text field.
     *
     * @return the current value of the text field
     */
    public String value() {
        super.applyName();
        return root.val();
    }

    /**
     * Simulates pressing the Enter key in the text field.
     */
    public void pressEnter() {
        root.pressEnter();
    }

    /**
     * Asserts that the text field is empty.
     * = Step: Processed by Aspect =
     */
    @Override
    public void assertEmpty() {
        Assertions.assertThat(root.getValue()).as(wrappedName()).isEqualTo("");
    }

    /**
     * Asserts that the text field is not empty.
     * <p><b>< Step: Processed by Aspect ></b></p>
     */
    @Override
    public void assertNotEmpty() {
        Assertions.assertThat(root.getValue()).as(wrappedName()).isNotEmpty();
    }

    /**
     * Retrieves the unique identifier for the text field.
     *
     * @return the ID or name of the text field
     */
    @Override
    public String getId() {
        if (!uiElementName.equals("")) {
            return uiElementName;
        } else {
            return getRoot().getAttribute("id");
        }
    }

//    @Override
//    public void assertCurrentValue(String value) {
//        long timer = Configuration.timeout / 1000;
//        while (!root.getValue().equals(value) && timer > 0) {
//            timer--;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        Assertions.assertThat(root.getValue()).as(wrappedName()).isEqualTo(value);
//    }

    /**
     * Asserts that the current value of the text field matches the specified value.
     * <p><b>< Step: Processed by Aspect ></b></p>
     *
     * @param value the expected value
     */
    @Override
    public void assertCurrentValue(String value) {
        root.shouldHave(Condition.value(value));
    }

    /**
     * Asserts that the current value of the text field contains the specified substring.
     * <p><b>< Step: Processed by Aspect ></b></p>
     *
     * @param value the substring to check for
     */
    @Override
    public void assertCurrentValueContains(String value) {
        long timer = Configuration.timeout / 1000;
        while (!root.getValue().contains(value) && timer > 0) {
            timer--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assertions.assertThat(root.getValue()).as(wrappedName()).contains(value);
    }
}
