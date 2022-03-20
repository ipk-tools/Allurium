package pk.tools.interfaces;

/**
 * Some input fields either native/custom can be required. These elements show some error indicators like text
 * message. This interface can be applied to any element which shows error message.
 *
 * Foe example:
 *
 *     class InputFieldRequired extends InputField implements RequiredInput {
 *         public String errorMessage() {
 *             return errorMessageElement.text();
 *         }
 *     }
 */
public interface RequiredInput {

    String getErrorMessage();
}
