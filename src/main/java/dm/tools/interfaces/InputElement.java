package dm.tools.interfaces;

/**
 * Interface representing basic behavior for input elements in the UI.
 * <p>
 * This interface is designed to standardize interactions with input elements
 * by providing a method to check their enabled/disabled state.
 * </p>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Defines a common method for determining the enabled or disabled state of input elements.</li>
 *     <li>Simplifies validation of input interactivity in tests.</li>
 * </ul>
 *
 * <h3>Common Implementations:</h3>
 * <ul>
 *     <li>Text fields</li>
 *     <li>Radio buttons</li>
 *     <li>Check boxes</li>
 *     <li>Custom input components</li>
 *     <li>Buttons</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * public class CustomInput implements InputElement {
 *
 *     @Override
 *     public boolean isDisabled() {
 *         // Implementation to check if the input is disabled
 *         return getRoot().isDisabled(); // Example logic
 *     }
 * }
 * }</pre>
 */
public interface InputElement {

    /**
     * Determines whether the input element is disabled.
     * <p>
     * Implementing classes should provide logic to check if the input is not interactable.
     * </p>
     *
     * @return {@code true} if the input element is disabled; {@code false} otherwise
     */
    boolean isDisabled();
}
