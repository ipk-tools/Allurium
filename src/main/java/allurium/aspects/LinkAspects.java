package allurium.aspects;

import allurium.AsyncAllureLogger;
import allurium.primitives.Link;
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
 * Aspect for enhancing the behavior of the {@link Link} class by adding Allure reporting capabilities.
 * <p>
 * This aspect wraps specific methods in the `Link` class to log their execution details as steps in Allure reports.
 * It is designed to improve test reporting by providing visibility into link-related assertions.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Wraps the {@link Link#assertHref(String)} method to log its execution in Allure reports.</li>
 *     <li>Handles success and failure scenarios for assertions with appropriate status updates.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Enhances the reporting of link assertions in test frameworks.</li>
 *     <li>Provides detailed logs for `href` validation steps.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>
 * {@code
 * // Using the Link class in tests
 * Link link = $link("a.example-link");
 * link.assertHref("https://example.com"); // Automatically logs this assertion in Allure
 * }
 * </pre>
 */
@Aspect
public class LinkAspects {

    /**
     * Wraps the execution of {@link Link#assertHref(String)} to log it as an Allure step.
     *
     * @param invocation the join point representing the method invocation
     * @throws Throwable if the original method throws an exception
     */
    @Around("execution (* allurium.primitives.Link.assertHref(String))")
    @SuppressWarnings("unchecked")
    public void stepAssertHref(ProceedingJoinPoint invocation) throws Throwable {
        Link uiElement = (Link) invocation.getThis();
        String href = (String) invocation.getArgs()[0];
        String stepName = StepTextProvider.getStepText(
                "assert_href",
                uiElement.getParent(),
                Pair.of("{element}", uiElement.getUiElementType()),
                Pair.of("{name}", uiElement.wrappedName()),
                Pair.of("{href}", href)
        );
        AllureStepHelper.runAllureAspectStep(invocation, stepName);
    }

}
