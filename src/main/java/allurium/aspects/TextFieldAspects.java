package allurium.aspects;

import allurium.StepTextProvider;
import allurium.inputs.TextField;
import allurium.utilities.AllureStepHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Aspect for enhancing the behavior of the {@link TextField} class by adding Allure reporting capabilities.
 * <p>
 * This aspect wraps specific methods in the `TextField` class to log their execution details as steps in Allure reports.
 * It improves test reporting by providing detailed visibility into actions and assertions performed on text fields.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Wraps common operations on text fields (e.g., writing text, clearing, pressing enter).</li>
 *     <li>Logs operations and assertions into Allure reports with context (e.g., text field name, value).</li>
 *     <li>Handles success and failure scenarios for assertions with appropriate status updates.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Enhances test step visibility by logging text field interactions and validations in Allure reports.</li>
 *     <li>Standardizes the reporting of text field-related operations across test cases.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Interacting with a text field
 * TextField textField = new TextField("input#username");
 * textField.write("exampleUser");  // Logs "Write 'exampleUser' to text field" in Allure
 * textField.clear();               // Logs "Clear text field" in Allure
 * textField.pressEnter();          // Logs "Press Enter on text field" in Allure
 * }
 * </pre>
 */
@Aspect
public class TextFieldAspects {

    /**
     * Logs the execution of {@link TextField#write(String)} as an Allure step.
     *
     * @param invocation the join point representing the method invocation
     * @throws Throwable if the original method throws an exception
     */
    @Around("execution (* allurium.inputs.TextField.write(String))")
    @SuppressWarnings("unchecked")
    public void stepWriteInjection(ProceedingJoinPoint invocation) throws Throwable {
        TextField inputField = (TextField) invocation.getThis();
        String typingText = (String) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText(
                "write",
                inputField.getParent(),
                Pair.of("{name}", inputField.wrappedName()),
                Pair.of("{element}", inputField.getUiElementType()),
                Pair.of("{text}", typingText)
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Logs the execution of {@link TextField#clear()} as an Allure step.
     *
     * @param invocation the join point representing the method invocation
     * @throws Throwable if the original method throws an exception
     */
    @Around("execution (* allurium.inputs.TextField.clear(..))")
    @SuppressWarnings("unchecked")
    public void stepClearInjection(ProceedingJoinPoint invocation) throws Throwable {
        TextField inputField = (TextField) invocation.getThis();
        String stepName = StepTextProvider.getStepText(
                "clear",
                inputField.getParent(),
                Pair.of("{name}", inputField.wrappedName()),
                Pair.of("{element}", inputField.getUiElementType())
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Logs the execution of {@link TextField#pressEnter()} as an Allure step.
     *
     * @param invocation the join point representing the method invocation
     * @throws Throwable if the original method throws an exception
     */
    @Around("execution (* allurium.inputs.TextField.pressEnter(..))")
    @SuppressWarnings("unchecked")
    public void stepPressEnterInjection(ProceedingJoinPoint invocation) throws Throwable {
        TextField inputField = (TextField) invocation.getThis();
        String stepName = StepTextProvider.getStepText(
                "text_field_press_enter",
                inputField.getParent(),
                Pair.of("{name}", inputField.wrappedName()),
                Pair.of("{element}", inputField.getUiElementType())
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Logs the execution of {@link TextField#assertEmpty()} as an Allure step.
     *
     * @param invocation the join point representing the method invocation
     * @throws Throwable if the original method throws an exception
     */
    @Around("execution (* allurium.inputs.TextField.assertEmpty(..))")
    @SuppressWarnings("unchecked")
    public void stepAssertEmptyInjection(ProceedingJoinPoint invocation) throws Throwable {
        TextField inputField = (TextField) invocation.getThis();
        String stepName = StepTextProvider.getStepText(
                "text_field_assert_blank",
                inputField.getParent(),
                Pair.of("{name}", inputField.wrappedName()),
                Pair.of("{element}", inputField.getUiElementType())
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Logs the execution of {@link TextField#assertNotEmpty()} as an Allure step.
     *
     * @param invocation the join point representing the method invocation
     * @throws Throwable if the original method throws an exception
     */
    @Around("execution (* allurium.inputs.TextField.assertNotEmpty(..))")
    @SuppressWarnings("unchecked")
    public void stepAssertNotEmpty(ProceedingJoinPoint invocation) throws Throwable {
        TextField inputField = (TextField) invocation.getThis();
        String stepName = StepTextProvider.getStepText(
                "text_field_assert_not_blank",
                inputField.getParent(),
                Pair.of("{name}", inputField.wrappedName()),
                Pair.of("{element}", inputField.getUiElementType())
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Logs the execution of {@link TextField#assertCurrentValue(String)} as an Allure step.
     *
     * @param invocation the join point representing the method invocation
     * @throws Throwable if the original method throws an exception
     */
    @Around("execution (* allurium.inputs.TextField.assertCurrentValue(String))")
    @SuppressWarnings("unchecked")
    public void stepAssertCurrentValue(ProceedingJoinPoint invocation) throws Throwable {
        TextField inputField = (TextField) invocation.getThis();
        String value = (String) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText(
                "text_field_assert_value",
                inputField.getParent(),
                Pair.of("{name}", inputField.wrappedName()),
                Pair.of("{element}", inputField.getUiElementType()),
                Pair.of("{value}", value)
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Logs the execution of {@link TextField#assertCurrentValueContains(String)} as an Allure step.
     *
     * @param invocation the join point representing the method invocation
     * @throws Throwable if the original method throws an exception
     */
    @Around("execution (* allurium.inputs.TextField.assertCurrentValueContains(String))")
    @SuppressWarnings("unchecked")
    public void stepAssertCurrentValueContains(ProceedingJoinPoint invocation) throws Throwable {
        TextField inputField = (TextField) invocation.getThis();
        String value = (String) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText(
                "text_field_assert_value_contains",
                inputField.getParent(),
                Pair.of("{name}", inputField.wrappedName()),
                Pair.of("{element}", inputField.getUiElementType()),
                Pair.of("{value}", value)
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

}
