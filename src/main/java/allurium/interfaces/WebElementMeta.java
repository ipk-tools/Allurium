package allurium.interfaces;

import allurium.AbstractWidget;

import java.util.Optional;

/**
 * Interface defining metadata and hierarchical relationships for UI elements.
 * <p>
 * This interface allows setting and retrieving metadata about a UI element, such as its name,
 * description, and hierarchical parent widget. It is typically implemented by UI elements to
 * enable richer reporting and debugging.
 * </p>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *   <li>Provides metadata about UI elements, including name, description, and parent relationships.</li>
 *   <li>Enables structured and organized reporting for test automation frameworks.</li>
 * </ul>
 *
 * <h3>Features:</h3>
 * <ul>
 *   <li>Defines methods to set and get metadata like element name and description.</li>
 *   <li>Supports hierarchical relationships between UI elements and their parent widgets.</li>
 *   <li>Stores metadata keys for additional context or debugging information.</li>
 * </ul>
 */
public interface WebElementMeta {

    /**
     * Sets the name of the UI element.
     *
     * @param uiElementName the name of the UI element
     */
    void setUiElementName(String uiElementName);

    /**
     * Gets the name of the UI element.
     *
     * @return the name of the UI element
     */
    String getUiElementName();

    /**
     * Sets the description of the UI element.
     *
     * @param description the description of the UI element
     */
    void setDescription(String description);

    /**
     * Gets the description of the UI element.
     *
     * @return the description of the UI element
     */
    String getDescription();

    /**
     * Sets the parent widget of the UI element.
     *
     * @param parent the parent widget wrapped in an {@link Optional}
     */
    void setParent(Optional<AbstractWidget> parent);

    /**
     * Gets the parent widget of the UI element.
     *
     * @return the parent widget wrapped in an {@link Optional}
     */
    Optional<AbstractWidget> getParent();

    /**
     * Sets the method used for assigning the element's name.
     *
     * @param method the method name
     */
    void setAssignNameMethod(String method);

    /**
     * Gets the metadata keys associated with the UI element.
     *
     * @return a {@link StringBuffer} containing the metadata keys
     */
    StringBuffer getMetaKeys();
}
