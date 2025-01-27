package allurium.aspects;

import allurium.carousels.AbstractCarousel;
import allurium.StepTextProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Aspect class for enhancing interactions with carousel components using {@link AbstractCarousel}.
 * <p>
 * This class provides aspect-oriented programming (AOP) implementations for the methods in
 * {@link AbstractCarousel}, including scrolling forward and backward. It integrates with
 * Allure reporting for comprehensive test step tracking and visualization.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Intercepts method calls to carousel scrolling methods.</li>
 *     <li>Generates detailed Allure steps for forward and backward scrolling actions.</li>
 *     <li>Tracks success or failure of intercepted methods and updates Allure steps accordingly.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Enhances test reporting by providing step-level tracking of carousel interactions.</li>
 *     <li>Ensures consistent handling of carousel method execution with error management.</li>
 * </ul>
 *
 * <h3>Methods Processed by Aspects:</h3>
 * <ul>
 *     <li>{@link AbstractCarousel#scrollForward()} - Scrolls the carousel forward.</li>
 *     <li>{@link AbstractCarousel#scrollBackward()} - Scrolls the carousel backward.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * public class ImageCarousel extends AbstractCarousel {
 *
 *     public ImageCarousel(SelenideElement root) {
 *         super(root);
 *     }
 *
 *     @Override
 *     public void scrollForward() {
 *         getRoot().$("button.next").click();
 *     }
 *
 *     @Override
 *     public void scrollBackward() {
 *         getRoot().$("button.prev").click();
 *     }
 * }
 *
 * // Test with Allure tracking
 * ImageCarousel carousel = new ImageCarousel($(".carousel"));
 * carousel.scrollForward();
 * carousel.scrollBackward();
 * }</pre>
 *
 * @see AbstractCarousel
 */
@Aspect
public class CarouselAspects {

    /**
     * Intercepts and logs the execution of the {@link AbstractCarousel#scrollForward()} method.
     * <p>
     * This aspect generates an Allure step for tracking the forward scroll action on the carousel.
     * </p>
     *
     * @param invocation the join point representing the intercepted method
     * @throws Throwable if the intercepted method throws an exception
     */
    @Around("execution (* allurium.carousels.AbstractCarousel.scrollForward())")
    public void stepScrollForward(ProceedingJoinPoint invocation) throws Throwable {
        AbstractCarousel slider = (AbstractCarousel) invocation.getThis();
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("carousel_scroll_forward", slider.getParent(),
                        Pair.of("{element}", slider.getUiElementType()),
                        Pair.of("{name}", slider.wrappedName())
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
     * Intercepts and logs the execution of the {@link AbstractCarousel#scrollBackward()} method.
     * <p>
     * This aspect generates an Allure step for tracking the backward scroll action on the carousel.
     * </p>
     *
     * @param invocation the join point representing the intercepted method
     * @throws Throwable if the intercepted method throws an exception
     */
    @Around("execution (* allurium.carousels.AbstractCarousel.scrollBackward())")
    public void stepScrollBackward(ProceedingJoinPoint invocation) throws Throwable {
        AbstractCarousel slider = (AbstractCarousel) invocation.getThis();
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("carousel_scroll_backward", slider.getParent(),
                        Pair.of("{element}", slider.getUiElementType()),
                        Pair.of("{name}", slider.wrappedName())
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
}
