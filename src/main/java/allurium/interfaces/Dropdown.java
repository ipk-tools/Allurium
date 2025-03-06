package allurium.interfaces;

/**
 * Interface for web components that behave similarly to native `<select>` and `<option>` elements.
 * <p>
 * This interface defines the standard behavior for dropdown-like components, including
 * extension (showing/hiding or folding/unfolding) and option selection.
 * </p>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Provides a unified structure for handling dropdown-like components.</li>
 *     <li>Encapsulates behaviors such as showing/hiding options and selecting specific values.</li>
 *     <li>Supports custom dropdown implementations and integration with Allure reporting.</li>
 * </ul>
 *
 * <h3>Common Implementations:</h3>
 * <ul>
 *     <li>Custom dropdown menus</li>
 *     <li>Native `<select>` elements with extended functionality</li>
 *     <li>List-based dropdowns with selectable `<li>` elements</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre><code>
 * public class CustomDropdown implements Dropdown {
 *
 *     @Override
 *     public void extend() {
 *         // Implementation to unfold or display the dropdown
 *     }
 *
 *     @Override
 *     public void select(String value) {
 *         // Implementation to select an option by its value
 *     }
 * }
 * </code></pre>
 *
 * @see AlluriumElement
 */
public interface Dropdown extends AlluriumElement {

    /**
     * Extends the dropdown to make the options visible.
     * <p>
     * This method handles the behavior for showing or unfolding the dropdown list.
     * </p>
     */
    void extend();

    /**
     * Selects an option from the dropdown by its visible text.
     * <p>
     * This method implements the behavior for choosing a specific value from the dropdown's options.
     * </p>
     *
     * @param value the text of the option to select
     */
    void select(String value);
}
