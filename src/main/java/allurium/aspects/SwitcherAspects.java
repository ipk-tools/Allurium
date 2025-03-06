package allurium.aspects;

import allurium.AsyncAllureLogger;
import allurium.switchers.AbstractSwitcher;
import allurium.StepTextProvider;
import allurium.utilities.AllureStepHelper;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.openqa.selenium.WebDriverException;

import java.util.UUID;

/**
 * Aspect class for intercepting and logging actions performed on {@link AbstractSwitcher} with additional behaviors.
 * <p>
 * This class intercepts method calls on switcher elements (e.g., toggle, switchOn, switchOff) and wraps them
 * with Allure step logging to provide better visibility into test execution.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Logs switcher actions as steps in Allure reports.</li>
 *     <li>Handles errors during intercepted method executions, ensuring proper status updates in reports.</li>
 *     <li>Provides additional debugging information in case of exceptions.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Enhances visibility of switcher interactions in tests by integrating Allure step logging.</li>
 *     <li>Standardizes the logging format and behavior for switcher operations.</li>
 * </ul>
 *
 * <h3>Intercepted Methods:</h3>
 * <ul>
 *     <li>{@link AbstractSwitcher#toggle()} - Intercepts and logs the toggle action.</li>
 *     <li>{@link AbstractSwitcher#switchOn()} - Intercepts and logs the switch-on action.</li>
 *     <li>{@link AbstractSwitcher#switchOff()} - Intercepts and logs the switch-off action.</li>
 * </ul>
 *
 * <h3>Example Usage:</h3>
 * <pre>{@code
 * AbstractSwitcher switcher = new ToggleSwitch(Selenide.$(".switch"));
 * switcher.toggle();  // Logged as a toggle step in Allure report
 * switcher.switchOn();  // Logged as a switch-on step in Allure report
 * switcher.switchOff();  // Logged as a switch-off step in Allure report
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
 *     <li>Sets the step status to FAILED in case of an error.</li>
 * </ul>
 *
 * @see AbstractSwitcher
 * @see io.qameta.allure.Allure
 */
@Aspect
public class SwitcherAspects {

    /**
     * Intercepts the {@link AbstractSwitcher#toggle()} method to log the toggle action as an Allure step.
     *
     * @param invocation the join point representing the intercepted method call
     * @throws Throwable any exception thrown by the intercepted method
     */
    @Around("execution (* allurium.switchers.AbstractSwitcher.toggle())")
    @SuppressWarnings("unchecked")
    public void stepShift(ProceedingJoinPoint invocation) throws Throwable {
        AbstractSwitcher switcher = (AbstractSwitcher) invocation.getThis();
        String stepName;
        try {
            stepName = StepTextProvider.getStepText(
                    "switcher_toggle",
                    switcher.getParent(),
                    Pair.of("{element}", switcher.getUiElementType()),
                    Pair.of("{name}", switcher.wrappedName())
            );
        } catch (WebDriverException ex) {
            stepName = switcher.wrappedName() + " " + ex.getMessage();
            throw ex;
        }
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@link AbstractSwitcher#switchOn()} method to log the switch-on action as an Allure step.
     *
     * @param invocation the join point representing the intercepted method call
     * @throws Throwable any exception thrown by the intercepted method
     */
    @Around("execution (* allurium.switchers.AbstractSwitcher.switchOn())")
    @SuppressWarnings("unchecked")
    public void stepSwitchOn(ProceedingJoinPoint invocation) throws Throwable {
        AbstractSwitcher switcher = (AbstractSwitcher) invocation.getThis();
        String stepName = StepTextProvider.getStepText(
                "switcher_switch_on",
                switcher.getParent(),
                Pair.of("{element}", switcher.getUiElementType()),
                Pair.of("{name}", switcher.wrappedName())
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@link AbstractSwitcher#switchOff()} method to log the switch-off action as an Allure step.
     *
     * @param invocation the join point representing the intercepted method call
     * @throws Throwable any exception thrown by the intercepted method
     */
    @Around("execution (* allurium.switchers.AbstractSwitcher.switchOff())")
    @SuppressWarnings("unchecked")
    public void stepSwitchOff(ProceedingJoinPoint invocation) throws Throwable {
        AbstractSwitcher switcher = (AbstractSwitcher) invocation.getThis();
        String stepName = StepTextProvider.getStepText(
                "switcher_switch_off",
                switcher.getParent(),
                Pair.of("{element}", switcher.getUiElementType()),
                Pair.of("{name}", switcher.wrappedName())
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

}
