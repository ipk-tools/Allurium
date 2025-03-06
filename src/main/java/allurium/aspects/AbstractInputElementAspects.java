package allurium.aspects;

import allurium.StepTextProvider;
import allurium.inputs.AbstractInputElement;
import allurium.utilities.AllureStepHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AbstractInputElementAspects {

    /**
     * Intercepts the {@code assertEnabled()} method of {@link AbstractInputElement}.
     * Logs the assertion for element enablement as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code assertEnabled()} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.inputs.AbstractInputElement.assertEnabled())")
    @SuppressWarnings("unchecked")
    public void stepAssertEnabled(ProceedingJoinPoint invocation) throws Throwable {
        AbstractInputElement inputElement = (AbstractInputElement) invocation.getThis();

        String stepName = StepTextProvider.getStepText(
                "assert_enabled",
                inputElement.getParent(),
                Pair.of("{element}", inputElement.getUiElementType()),
                Pair.of("{name}", inputElement.wrappedName())
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertDisabled()} method of {@link AbstractInputElement}.
     * Logs the assertion for element disablement as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code assertDisabled()} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.inputs.AbstractInputElement.assertDisabled())")
    @SuppressWarnings("unchecked")
    public void stepAssertDisabled(ProceedingJoinPoint invocation) throws Throwable {
        AbstractInputElement inputElement = (AbstractInputElement) invocation.getThis();

        String stepName = StepTextProvider.getStepText(
                "assert_disabled",
                inputElement.getParent(),
                Pair.of("{element}", inputElement.getUiElementType()),
                Pair.of("{name}", inputElement.wrappedName())
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

}
