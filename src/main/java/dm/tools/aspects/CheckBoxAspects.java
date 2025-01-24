package dm.tools.aspects;

import dm.tools.StepTextProvider;
import dm.tools.inputs.CheckBox;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Aspect for enhancing methods in {@link CheckBox} with additional behaviors such as step logging.
 * <p>
 * This class intercepts method calls on checkbox elements (e.g., check, uncheck, assertChecked, assertUnchecked)
 * and wraps them with Allure step logging to provide better visibility into test execution.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Logs checkbox actions (check, uncheck) as steps in Allure reports.</li>
 *     <li>Handles errors during intercepted method executions, ensuring proper status updates in reports.</li>
 *     <li>Provides additional debugging information in case of exceptions or assertion failures.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Enhances visibility of checkbox interactions in tests by integrating Allure step logging.</li>
 *     <li>Standardizes the logging format and behavior for checkbox operations.</li>
 * </ul>
 *
 * <h3>Intercepted Methods:</h3>
 * <ul>
 *     <li>{@link CheckBox#check()} - Logs the checkbox checking action.</li>
 *     <li>{@link CheckBox#uncheck()} - Logs the checkbox unchecking action.</li>
 *     <li>{@link CheckBox#assertChecked()} - Logs the assertion of the checkbox being checked.</li>
 *     <li>{@link CheckBox#assertUnchecked()} - Logs the assertion of the checkbox being unchecked.</li>
 * </ul>
 *
 * <h3>Example Usage:</h3>
 * <pre>{@code
 * CheckBox checkBox = $checkbox(".checkbox-class");
 * checkBox.check();  // Logged as a check step in Allure report
 * checkBox.uncheck();  // Logged as an uncheck step in Allure report
 * checkBox.assertChecked();  // Logged as an assertion step in Allure report
 * checkBox.assertUnchecked();  // Logged as an assertion step in Allure report
 * }</pre>
 *
 * <h3>Allure Integration:</h3>
 * <ul>
 *     <li>Each intercepted method starts a new Allure step with a unique UUID.</li>
 *     <li>Step names are generated dynamically using {@link StepTextProvider} for consistency.</li>
 *     <li>Step statuses are updated based on the success or failure of the intercepted method.</li>
 * </ul>
 *
 * <h3>Exception Handling:</h3>
 * <ul>
 *     <li>Logs exceptions encountered during method execution.</li>
 *     <li>Sets the step status to FAILED in case of an error or assertion failure.</li>
 * </ul>
 *
 * @see CheckBox
 * @see io.qameta.allure.Allure
 */
@Aspect
public class CheckBoxAspects {

    /**
     * Intercepts the {@link CheckBox#check()} method to log the checkbox checking action as an Allure step.
     *
     * @param invocation the join point representing the intercepted method call
     * @throws Throwable any exception thrown by the intercepted method
     */
    @Around("execution (* dm.tools.inputs.CheckBox.check())")
    public void stepCheck(ProceedingJoinPoint invocation) throws Throwable {
        CheckBox checkBox = (CheckBox) invocation.getThis();
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("checkbox_check", checkBox.getParent(),
                        Pair.of("{element}", checkBox.getUiElementType()),
                        Pair.of("{name}", checkBox.wrappedName())
                ));
        Allure.getLifecycle().startStep(stepUuid, stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable throwable) {
            errorStatus = true;
            throwable.printStackTrace();
            throw throwable;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
    }

    /**
     * Intercepts the {@link CheckBox#uncheck()} method to log the checkbox unchecking action as an Allure step.
     *
     * @param invocation the join point representing the intercepted method call
     * @throws Throwable any exception thrown by the intercepted method
     */
    @Around("execution (* dm.tools.inputs.CheckBox.uncheck())")
    public void stepUncheck(ProceedingJoinPoint invocation) throws Throwable {
        CheckBox checkBox = (CheckBox) invocation.getThis();
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("checkbox_uncheck", checkBox.getParent(),
                        Pair.of("{element}", checkBox.getUiElementType()),
                        Pair.of("{name}", checkBox.wrappedName())
                ));
        Allure.getLifecycle().startStep(stepUuid, stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable throwable) {
            errorStatus = true;
            throwable.printStackTrace();
            throw throwable;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
    }

    /**
     * Intercepts the {@link CheckBox#assertChecked()} method to log the checkbox assertion action as an Allure step.
     *
     * @param invocation the join point representing the intercepted method call
     * @throws Throwable any exception thrown by the intercepted method
     */
    @Around("execution (* dm.tools.inputs.CheckBox.assertChecked())")
    public void stepAssertChecked(ProceedingJoinPoint invocation) throws Throwable {
        CheckBox checkBox = (CheckBox) invocation.getThis();
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("checkbox_assert_checked", checkBox.getParent(),
                        Pair.of("{element}", checkBox.getUiElementType()),
                        Pair.of("{name}", checkBox.wrappedName())
                ));
        Allure.getLifecycle().startStep(stepUuid, stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (AssertionError assertionError) {
            errorStatus = true;
            assertionError.printStackTrace();
            throw assertionError;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
    }

    /**
     * Intercepts the {@link CheckBox#assertUnchecked()} method to log the checkbox assertion action as an Allure step.
     *
     * @param invocation the join point representing the intercepted method call
     * @throws Throwable any exception thrown by the intercepted method
     */
    @Around("execution (* dm.tools.inputs.CheckBox.assertUnchecked())")
    public void stepAsserUnchecked(ProceedingJoinPoint invocation) throws Throwable {
        CheckBox checkBox = (CheckBox) invocation.getThis();
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("checkbox_assert_unchecked", checkBox.getParent(),
                        Pair.of("{element}", checkBox.getUiElementType()),
                        Pair.of("{name}", checkBox.wrappedName())
                ));
        Allure.getLifecycle().startStep(stepUuid, stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (AssertionError assertionError) {
            errorStatus = true;
            assertionError.printStackTrace();
            throw assertionError;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep();
        }
    }
}
