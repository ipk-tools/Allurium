package allurium.interfaces;

/**
 * Interface for web components that are part of a collection and can be managed or retrieved by an identifier.
 *
 * <p>Web components implementing this interface can be grouped into a collection, allowing retrieval
 * and interaction with specific components based on their identifiers.</p>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *   <li>Facilitates managing and accessing a collection of web components.</li>
 *   <li>Provides a standard way to retrieve components using their IDs.</li>
 * </ul>
 *
 * <h3>Example Scenario:</h3>
 * Consider a webpage with multiple forms, each containing a dropdown, an input field,
 * and a text area. Implementing `ListComponent` allows grouping the forms into a collection
 * for efficient interaction.
 *
 * <h3>Code Example:</h3>
 * <pre>{@code
 * @Getter
 * public class Form implements ListComponent {
 *     private SelenideElement root;
 *     private SelenideElement formName;
 *     private DropdownWC marks;
 *     private InputField emailInput;
 *     private TextArea description;
 *
 *     public Form(By rootLocator) {
 *         root = $(rootLocator);
 *         formName = root.$("span");
 *         marks = new DropdownWC(root.$("ul"));
 *         emailInput = new InputField(root.$("input"));
 *         description = new TextArea(root.$("textarea"));
 *     }
 *
 *     @Override
 *     public String getId() {
 *         return formName.text();
 *     }
 * }
 *
 * @Step
 * public void writeReview() {
 *     ListWC<Form> reviewForms = new ListWC<>(".form");
 *     Form form = reviewForms.get("form 2");
 *     form.getMarks().select("Normal");
 *     form.getEmailInput().write("example@email.com");
 *     form.getDescription().write("example description");
 * }
 * }</pre>
 *
 * <h3>Methods:</h3>
 * <ul>
 *   <li>{@link #getId()}: Retrieves the unique identifier of the component.</li>
 * </ul>
 */
public interface ListComponent extends AlluriumElement {

    /**
     * Retrieves the unique identifier for this component.
     *
     * @return the component's unique ID as a {@link String}.
     */
    String getId();
}
