package allurium.aspects;

import allurium.StepTextProvider;
import allurium.tabs.AbstractTabs;
import allurium.utilities.AllureStepHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Aspect class responsible for intercepting method executions related to tab selection in {@link AbstractTabs}.
 * <p>
 * This class enhances tab selection operations by wrapping them within an Allure step for better reporting.
 * The intercepted method (`select(String)`) logs the selected tab's name and integrates the step into
 * the Allure test report.
 * </p>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Automatically logs tab selection steps in Allure reports.</li>
 *     <li>Enhances visibility of test execution by associating the selected tab with its parent component.</li>
 * </ul>
 *
 * <h3>Usage:</h3>
 * <ul>
 *     <li>Applied automatically through AspectJ to methods matching the execution pattern.</li>
 *     <li>Intercepts the {@code select(String)} method of {@link AbstractTabs} implementations.</li>
 * </ul>
 *
 * @see AbstractTabs
 * @see StepTextProvider
 * @see AllureStepHelper
 * @author Iaroslav Pilipenko
 */
@Aspect
public class TabsAspects {

    /**
     * Wraps the {@link AbstractTabs#select(String)} method execution inside an Allure step for improved reporting.
     * <p>
     * This method intercepts calls to `select(String)`, retrieves the target tab name, and logs the step
     * in Allure using {@link AllureStepHelper#runAllureAspectStep(ProceedingJoinPoint, String)}.
     * </p>
     *
     * <h3>Behavior:</h3>
     * <ul>
     *     <li>Intercepts the method execution before it runs.</li>
     *     <li>Extracts the selected tab's name and the parent component's name.</li>
     *     <li>Formats the step text using {@link StepTextProvider}.</li>
     *     <li>Executes the original method inside the Allure step.</li>
     * </ul>
     *
     * <h3>Usage:</h3>
     * <pre>{@code
     * tabs.select("Settings");
     * }</pre>
     * This method call will be automatically wrapped inside an Allure step.
     *
     * @param invocation the intercepted method call to `select(String)`
     * @throws Throwable if the underlying method execution fails
     */
    @Around("execution (* allurium.tabs.AbstractTabs.select(String))")
    @SuppressWarnings("unchecked")
    public void stepSelect(ProceedingJoinPoint invocation) throws Throwable {
        AbstractTabs tabs = (AbstractTabs) invocation.getThis();
        String targetTabName = (String) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText(
                "tabs_select",
                tabs.getParent(),
                Pair.of("{element}", tabs.getUiElementType()),
                Pair.of("{name}", tabs.wrappedName()),
                Pair.of("{tab}", targetTabName)
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

}
