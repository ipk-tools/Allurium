package allurium.interfaces;

import org.openqa.selenium.InvalidArgumentException;

/**
 * Interface for components that support selection actions, such as dropdown lists or other selectable elements.
 * <p>
 * This interface standardizes the methods used for selecting options or elements, providing consistency
 * in how selections are handled across different components.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Methods for selecting elements by value, index, or criteria.</li>
 *     <li>Support for generic selection logic, including first, last, or random selections.</li>
 *     <li>Option to exclude specific values during selection.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates selection behavior for various UI elements.</li>
 *     <li>Provides a standardized interface for implementing selectable components.</li>
 * </ul>
 *
 * <h3>Common Implementations:</h3>
 * <ul>
 *     <li>Select option lists</li>
 *     <li>Dropdown menus</li>
 *     <li>Custom list components</li>
 * </ul>
 *
 * <h3>Example Usage:</h3>
 * <pre>{@code
 * public class DropdownSelect implements Selectable {
 *     public void select(String value) { ... }
 *     public void selectFirst() { ... }
 *     public void selectLast() { ... }
 *     public void selectAny() { ... }
 *     public void selectAnyBesides(String value) { ... }
 * }
 *
 * // In a test case:
 * DropdownSelect dropdown = new DropdownSelect();
 * dropdown.select("Option 1");
 * dropdown.selectAnyBesides("Excluded Option");
 * }</pre>
 */

public interface Selectable extends AlluriumElement {

    /**
     * Selects an element based on its value.
     * <p>
     * This method allows selecting an element by matching its value. If the value does not exist,
     * an {@link InvalidArgumentException} is thrown.
     * </p>
     *
     * <h3>Example:</h3>
     * <pre>{@code
     * Selectable selectable = new DropdownSelect();
     * selectable.select("Option 1");
     * }</pre>
     *
     * @param value the value of the element to select
     * @throws InvalidArgumentException if the value does not exist
     */
    void select(String value) throws InvalidArgumentException;

    /**
     * Selects the first available element in the component.
     * <p>
     * Useful for components where selecting the first element is a valid test scenario.
     * </p>
     *
     * <h3>Example:</h3>
     * <pre>{@code
     * Selectable selectable = new DropdownSelect();
     * selectable.selectFirst();
     * }</pre>
     */
    void selectFirst();

    /**
     * Selects the last available element in the component.
     * <p>
     * Useful for components where selecting the last element is relevant for the test scenario.
     * </p>
     *
     * <h3>Example:</h3>
     * <pre>{@code
     * Selectable selectable = new DropdownSelect();
     * selectable.selectLast();
     * }</pre>
     */
    void selectLast();

    /**
     * Selects any available element in the component.
     * <p>
     * A random selection from the available options. This can be useful for tests where the specific
     * selected value is not important.
     * </p>
     *
     * <h3>Example:</h3>
     * <pre>{@code
     * Selectable selectable = new DropdownSelect();
     * selectable.selectAny();
     * }</pre>
     */
    void selectAny();

    /**
     * Selects any available element except the one specified by its value.
     * <p>
     * Filters the options to exclude the specified value and then selects one of the remaining options.
     * Throws an exception if there are no available options except the excluded value.
     * </p>
     *
     * <h3>Example:</h3>
     * <pre>{@code
     * Selectable selectable = new DropdownSelect();
     * selectable.selectAnyBesides("Excluded Option");
     * }</pre>
     *
     * @param value the value to exclude from the selection
     * @throws InvalidArgumentException if there are no options available except the excluded value
     */
    void selectAnyBesides(String value);
}
