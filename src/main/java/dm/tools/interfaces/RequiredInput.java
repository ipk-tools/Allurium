package dm.tools.interfaces;

/**
 * Interface for UI elements that represent required inputs.
 * <p>
 * This interface provides methods for determining and asserting the "required" state of an input field.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Defines a contract for checking if an input is marked as required using {@link #isMarked()}.</li>
 *     <li>Includes assertions for the required state of the input:
 *         <ul>
 *             <li>{@link #assertMarkedAsRequired()} to ensure the input is marked as required.</li>
 *             <li>{@link #assertNotMarkedAsRequired()} to ensure the input is not marked as required.</li>
 *         </ul>
 *     </li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes the handling of "required" state functionality across different input types.</li>
 *     <li>Ensures consistency and clarity when interacting with required input fields in tests.</li>
 * </ul>
 */
public interface RequiredInput extends InputElement {

    /**
     * Checks if the input is marked as required.
     *
     * @return {@code true} if the input is marked as required, {@code false} otherwise
     */
    boolean isMarked();

    /**
     * Asserts that the input is marked as required.
     * <p>
     * This method ensures that the required indicator is present.
     * </p>
     */
    void assertMarkedAsRequired();

    /**
     * Asserts that the input is not marked as required.
     * <p>
     * This method ensures that the required indicator is absent.
     * </p>
     */
    void assertNotMarkedAsRequired();
}
