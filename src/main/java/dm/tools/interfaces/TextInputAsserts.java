package dm.tools.interfaces;

/**
 * Interface for providing basic assertions on input elements.
 * <p>
 * This interface is designed to standardize assertions for input elements such as fields and text areas,
 * allowing checks for empty or non-empty states, as well as validating specific values.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Asserts that input elements are empty or not empty.</li>
 *     <li>Validates the current value of input elements.</li>
 *     <li>Checks whether the input value contains a specific substring.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Ensures consistency in input validation methods across implementing classes.</li>
 *     <li>Provides a standardized approach to validate the state of input elements in tests.</li>
 * </ul>
 *
 * <h3>Common Implementations:</h3>
 * <ul>
 *     <li>Text fields</li>
 *     <li>Text areas</li>
 *     <li>Custom input components</li>
 * </ul>
 *
 * <h3>Example Usage:</h3>
 * <pre>{@code
 * public class TextField implements TextInputAsserts {
 *     // Implementations of assertEmpty(), assertNotEmpty(), etc.
 * }
 *
 * // In a test case:
 * TextField textField = new TextField("input");
 * textField.assertEmpty();
 * textField.assertCurrentValue("Expected Value");
 * }</pre>
 */

public interface TextInputAsserts {

    /**
     * Asserts that the input element is empty.
     * <p>
     * Ensures that the input element's current value is an empty string or has no value.
     * Typically used to verify that a field is cleared or uninitialized.
     * </p>
     * <h3>Example:</h3>
     * <pre>{@code
     * TextField textField = new TextField("input");
     * textField.assertEmpty();
     * }</pre>
     */
    void assertEmpty();

    /**
     * Asserts that the input element is not empty.
     * <p>
     * Ensures that the input element contains some value, typically used to verify that
     * the field is populated during or after user interaction.
     * </p>
     * <h3>Example:</h3>
     * <pre>{@code
     * TextField textField = new TextField("input");
     * textField.assertNotEmpty();
     * }</pre>
     */
    void assertNotEmpty();

    /**
     * Asserts that the current value of the input element matches the specified value.
     * <p>
     * Ensures that the input element's value is exactly equal to the expected value.
     * </p>
     * <h3>Example:</h3>
     * <pre>{@code
     * TextField textField = new TextField("input");
     * textField.assertCurrentValue("Expected Value");
     * }</pre>
     *
     * @param value the expected value to match
     */
    void assertCurrentValue(String value);

    /**
     * Asserts that the current value of the input element contains the specified substring.
     * <p>
     * Ensures that the input element's value includes the given substring, often used
     * to validate partial matches or expected formats.
     * </p>
     * <h3>Example:</h3>
     * <pre>{@code
     * TextField textField = new TextField("input");
     * textField.assertCurrentValueContains("Expected");
     * }</pre>
     *
     * @param value the substring that the current value should contain
     */
    void assertCurrentValueContains(String value);
}
