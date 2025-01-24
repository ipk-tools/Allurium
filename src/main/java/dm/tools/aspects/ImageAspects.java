package dm.tools.aspects;

import dm.tools.StepTextProvider;
import dm.tools.primitives.Image;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Aspect for intercepting methods in the {@link Image} class to enhance Allure reporting.
 * <p>
 * This aspect specifically targets methods of the `Image` class to provide step-level reporting
 * in Allure, capturing methods details and outcomes (pass/fail).
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Intercepts the {@link Image#assertSrcUrl(String)} method.</li>
 *     <li>Generates detailed Allure steps with information about the element, its type, and the expected source URL.</li>
 *     <li>Handles both successful and failed assertions, updating the Allure step status accordingly.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Enhances test reporting by integrating aspect-oriented programming with Allure.</li>
 *     <li>Provides detailed insights into assertion checks performed on image elements.</li>
 * </ul>
 *
 * <h3>Usage:</h3>
 * <pre>{@code
 * // In a test, asserting the source URL of an image:
 * Image image = $image("img.logo");
 * image.assertSrcUrl("https://example.com/logo.png");
 *
 * // Allure will capture the step with relevant details through this aspect.
 * }</pre>
 */
@Aspect
public class ImageAspects {

    /**
     * Intercepts the {@link Image#assertSrcUrl(String)} method to log an Allure step.
     * <p>
     * Captures the expected source URL and details about the image element,
     * and logs the step status (PASSED or FAILED) in Allure.
     * </p>
     *
     * @param invocation the join point representing the intercepted method
     * @throws Throwable if the intercepted method throws any exception
     */
    @Around("execution (* dm.tools.primitives.Image.assertSrcUrl(String))")
    @SuppressWarnings("unchecked")
    public void stepAssertSrcUrl(ProceedingJoinPoint invocation) throws Throwable {
        Image image = (Image) invocation.getThis();
        String expectedSrc = (String) invocation.getArgs()[0];
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("image_assert_src", image.getParent(),
                Pair.of("{name}", image.wrappedName()),
                Pair.of("{element}", image.getUiElementType()),
                Pair.of("{src}", expectedSrc)
        ));
        Allure.getLifecycle().startStep(stepUuid, stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable stepFailException) {
            errorStatus = true;
            throw stepFailException;
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
