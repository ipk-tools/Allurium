package allurium.aspects;

import allurium.AsyncAllureLogger;
import allurium.StepTextProvider;
import allurium.inputs.AbstractInputElement;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.apache.commons.lang3.RandomStringUtils;
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
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("assert_enabled", inputElement.getParent(),
                        Pair.of("{element}", inputElement.getUiElementType()),
                        Pair.of("{name}", inputElement.wrappedName())
                ));
        AsyncAllureLogger.stopStepAsync();
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (AssertionError assertionException) {
            errorStatus = true;
            assertionException.printStackTrace();
            throw assertionException;
        } finally {
            if (errorStatus) {
                stepResult.setStatus(Status.FAILED);
            } else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
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
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        String stepText = StepTextProvider.getStepText("assert_disabled", inputElement.getParent(),
                Pair.of("{element}", inputElement.getUiElementType()),
                Pair.of("{name}", inputElement.wrappedName()));
        StepResult stepResult = new StepResult()
                .setName(stepText);
        AsyncAllureLogger.stopStepAsync();
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (AssertionError assertionException) {
            errorStatus = true;
            assertionException.printStackTrace();
            throw assertionException;
        } finally {
            if (errorStatus) {
                stepResult.setStatus(Status.FAILED);
            } else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
    }
}
