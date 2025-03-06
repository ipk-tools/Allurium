package allurium.aspects;

import allurium.AlluriumConfig;
import allurium.AsyncAllureLogger;
import allurium.primitives.UIElement;
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

import java.util.UUID;

/**
 * This class provides Aspect-Oriented Programming (AOP) enhancements for the {@link UIElement} class.
 * It integrates with the Allure framework to automatically log and report steps, statuses, and exceptions during
 * the execution of various UI-related operations.
 *
 * <p>The aspects intercept specific methods of the {@link UIElement} class
 * to wrap their execution with logging and exception handling for improved debugging and reporting.</p>
 *
 * <h3>Key Features:</h3>
 * <ul>
 *   <li>Intercepts UI interactions such as clicks, assertions, and visibility checks.</li>
 *   <li>Logs steps to Allure reports with meaningful step names and statuses.</li>
 *   <li>Handles and reports exceptions during method execution.</li>
 * </ul>
 *
 * <h3>Example Usage:</h3>
 * <pre>{@code
 * // Example invocation in a test:
 * UIElement element = UIElement.$uiElement(By.id("example"));
 * element.click(); // Automatically logged by the associated aspect
 * }</pre>
 *
 * @see UIElement
 * @see io.qameta.allure.Allure
 */
@Aspect
public class UIElementAspects {

    /**
     * Intercepts the {@code click()} method of {@link UIElement}.
     * Logs the click action as a step in Allure reports and updates the step status based on execution outcome.
     *
     * @param invocation the join point representing the {@code click()} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.click())")
    @SuppressWarnings("unchecked")
    public void stepClick(ProceedingJoinPoint invocation) throws Throwable {
        long startTime = System.nanoTime();
        if (AlluriumConfig.profilingAspectTime()) startTime = System.nanoTime();

        UIElement uiElement = (UIElement) invocation.getThis();
        long stepDefinitionStart = System.nanoTime();
        if (AlluriumConfig.profilingAspectTime()) stepDefinitionStart = System.nanoTime();
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("click", uiElement.getParent(),
                        Pair.of("{element}", uiElement.getUiElementType()),
                        Pair.of("{name}", uiElement.wrappedName())
                ));
        if (AlluriumConfig.profilingAspectTime())
            System.out.println("step definition has taken: " + (System.nanoTime() - stepDefinitionStart) / 1_000_000 + " ms");

        Allure.getLifecycle().startStep(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            long invocationStart = System.nanoTime();
            if (AlluriumConfig.profilingAspectTime()) invocationStart = System.nanoTime();
            invocation.proceed();
            if (AlluriumConfig.profilingAspectTime())
                System.out.println("invocation has taken: " + (System.nanoTime() - invocationStart) / 1_000_000);
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
            if (AlluriumConfig.profilingAspectTime())
                System.out.println("[(Click) on "+uiElement.getUiElementName()+"] aspect duration: " + (System.nanoTime() - startTime) / 1_000_000 + " ms");
        }
    }

    /**
     *
     * @param invocation the join point representing the {@code click()} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.doubleClick())")
    public void stepDoubleClick(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String stepName = StepTextProvider.getStepText("double_click",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName())
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    @Around("execution (* allurium.primitives.UIElement.clickAndHold(long))")
    public void stepClickAndHold(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        long holdingTime = (long) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText("click_and_hold",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName()),
                Pair.of("{milliseconds}", String.valueOf(holdingTime))
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code contextClick()} method of {@link UIElement}.
     * Logs the context click action as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code contextClick()} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.contextClick())")
    public void stepContextClick(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String stepName = StepTextProvider.getStepText("context_click",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName())
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertText(String)} method of {@link UIElement}.
     * Logs the assertion as a step in Allure reports, including the expected text.
     *
     * @param invocation the join point representing the {@code assertText(String)} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertText(String))")
    public void stepAssertText(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String text = (String) invocation.getArgs()[0];
        String stepName = uiElement.getAllureCompiledStep("assert_text", Pair.of("{text}", text));

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertHasText(String)} method of {@link UIElement}.
     * Logs the assertion step to Allure reports, including the expected text value.
     *
     * @param invocation the join point representing the {@code assertHasText(String)} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertHasText(String))")
    public void stepAssertHasText(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String text = (String) invocation.getArgs()[0];
        String stepName = uiElement.getAllureCompiledStep("assert_has_text", Pair.of("{text}", text));

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertHasText(String, Integer)} method of {@link UIElement}.
     * Logs the assertion step to Allure reports with the expected text value and duration.
     *
     * @param invocation the join point representing the {@code assertHasText(String, Integer)} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertHasText(String,Integer))")
    public void stepAssertHasTextWithinTime(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String text = (String) invocation.getArgs()[0];
        Integer duration = (Integer) invocation.getArgs()[1];
        String stepName = uiElement.getAllureCompiledStep("assert_has_text",
                Pair.of("{text}", text),
                Pair.of("{duration}", String.valueOf(duration))
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code softAssertHasText(String)} method of {@link UIElement}.
     * Logs the assertion as a step in Allure reports. Handles soft assertion logic.
     *
     * @param invocation the join point representing the {@code softAssertHasText(String)} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.softAssertHasText(String))")
    public void stepSoftAssertHasText(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String text = (String) invocation.getArgs()[0];
        String stepName = uiElement.getAllureCompiledStep("assert_has_text", Pair.of("{text}", text));

        // If you need custom logic for "soft" assertions, you can still do so,
        // but 99% can remain in the helper.
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertEquals(Object)} method of {@link UIElement}.
     * Logs the equality check as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code assertEquals(Object)} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertEquals(Object))")
    public void stepAssertEquals(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        UIElement objToEq = (UIElement) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText("assert_equals",
                uiElement.getParent(),
                Pair.of("{element1}", uiElement.wrappedName()),
                Pair.of("{element2}", objToEq.wrappedName())
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertVisible()} method of {@link UIElement}.
     * Logs the visibility check as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code assertVisible()} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertVisible())")
    public void stepAssertVisible(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String stepName = StepTextProvider.getStepText("assert_visible",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName())
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertVisibleWithDuration(int)} method of {@link UIElement}.
     * Logs the visibility check with duration as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code assertVisibleWithDuration(int)} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertVisible(int))")
    public void stepAssertVisibleWithDuration(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        int seconds = (int) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText("assert_visible_with_duration",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName()),
                Pair.of("{sec}", String.valueOf(seconds))
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    @Around("execution (* allurium.primitives.UIElement.assertNotVisible())")
    public void stepAssertNotVisible(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String stepName = StepTextProvider.getStepText("assert_not_visible",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName())
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertExists()} method of {@link UIElement}.
     * Logs the existence check as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code assertExists()} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertExists())")
    public void stepAssertExists(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String stepName = StepTextProvider.getStepText("assert_exist",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName())
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertNotExists()} method of {@link UIElement}.
     * Logs the non-existence check as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code assertNotExists()} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertNotExists())")
    public void stepAssertNotExists(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String stepName = StepTextProvider.getStepText("assert_not_exist",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName())
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code hover()} method of {@link UIElement}.
     * Logs the hover action as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code hover()} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.hover())")
    public void stepHover(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String stepName = StepTextProvider.getStepText("hover",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName())
        );

        // If you need to catch certain exceptions and rethrow differently,
        // you can do that either in the helper or inline here. But 90% is the same.
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertHasCssClass(String)} method of {@link UIElement}.
     * Logs the assertion for the presence of a CSS class as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code assertHasCssClass(String)} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertHasCssClass(String))")
    public void stepAssertHasCssClass(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String clazz = (String) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText("assert_has_css_class",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName()),
                Pair.of("{clazz}", clazz)
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertHasNotCssClass(String)} method of {@link UIElement}.
     * Logs the assertion for the absence of a CSS class as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code assertHasNotCssClass(String)} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertHasNotCssClass(String))")
    public void stepAssertHasNotCssClass(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String clazz = (String) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText("assert_has_not_css_class",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName()),
                Pair.of("{clazz}", clazz)
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertHasCssClass(String, int)} method of {@link UIElement}.
     * Logs the assertion for the presence of a CSS class within a duration as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code assertHasCssClass(String, int)} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertHasCssClass(String,int))")
    public void stepAssertHasCssClassDuringSeconds(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String clazz = (String) invocation.getArgs()[0];
        int duringSec = (int) invocation.getArgs()[1];
        String stepName = StepTextProvider.getStepText("assert_has_css_class_during_time",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName()),
                Pair.of("{clazz}", clazz),
                Pair.of("{seconds}", String.valueOf(duringSec))
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertHasNotCssClass(String, int)} method of {@link UIElement}.
     * Logs the assertion for the absence of a CSS class within a duration as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code assertHasNotCssClass(String, int)} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertHasNotCssClass(String,int))")
    public void stepAssertHasNotCssClassDuringSeconds(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String clazz = (String) invocation.getArgs()[0];
        int duringSec = (int) invocation.getArgs()[1];
        String stepName = StepTextProvider.getStepText("assert_has_not_css_class_during_time",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName()),
                Pair.of("{clazz}", clazz),
                Pair.of("{seconds}", String.valueOf(duringSec))
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertEmpty()} method of {@link UIElement}.
     * Logs the assertion as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code assertEmpty()} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertEmpty())")
    public void stepAssertEmpty(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String stepName = StepTextProvider.getStepText("assert_element_not_empty",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName())
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code assertIsNotEmpty()} method of {@link UIElement}.
     * Logs the assertion for a non-empty element as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code assertIsNotEmpty()} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.assertIsNotEmpty())")
    public void stepAssertNotEmpty(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String stepName = StepTextProvider.getStepText("assert_element_not_empty",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName())
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

    /**
     * Intercepts the {@code scrollTo()} method of {@link UIElement}.
     * Logs the scroll action as a step in Allure reports.
     *
     * @param invocation the join point representing the {@code scrollTo()} method
     * @throws Throwable if an exception occurs during method execution
     */
    @Around("execution (* allurium.primitives.UIElement.scrollTo())")
    public void stepScrollTo(ProceedingJoinPoint invocation) throws Throwable {
        UIElement uiElement = (UIElement) invocation.getThis();
        String stepName = StepTextProvider.getStepText("scroll",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName())
        );

        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

}
