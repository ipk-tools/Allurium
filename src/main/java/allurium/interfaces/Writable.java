package allurium.interfaces;

/**
 * Interface for web components that support writing and clearing text.
 * <p>
 * This interface is typically implemented by elements such as input fields, text areas, and other
 * components where text can be entered or manipulated programmatically.
 * </p>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes the behavior of writable elements in the UI.</li>
 *     <li>Ensures that implementing classes provide essential methods for writing, clearing,
 *     and asserting text values.</li>
 * </ul>
 *
 * <h3>Common Implementations:</h3>
 * <ul>
 *     <li>Native input fields</li>
 *     <li>Text areas</li>
 *     <li>Custom components that mimic writable elements</li>
 * </ul>
 */
public interface Writable {

    /**
     * Writes the specified text into the element.
     *
     * @param text the text to be entered
     */
    void write(String text);

    /**
     * Clears the text from the element.
     */
    void clear();

    /**
     * Asserts that the current value of the element matches the specified value.
     *
     * @param value the expected value of the element
     */
    void assertCurrentValue(String value);

    /**
     * Asserts that the current value of the element contains the specified substring.
     *
     * @param value the substring that the element's value should contain
     */
    void assertCurrentValueContains(String value);
}
