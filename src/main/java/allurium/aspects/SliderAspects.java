package allurium.aspects;

import allurium.AsyncAllureLogger;
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

import java.util.UUID;

@Aspect
public class SliderAspects {

    @Around("execution (* allurium.carousels.AbstractSlider.scrollForward())")
    @SuppressWarnings("unchecked")
    public void stepScrollForward(ProceedingJoinPoint invocation) throws Throwable {
        AbstractCarousel slider = (AbstractCarousel) invocation.getThis();
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("slider_scroll_forward", slider.getParent(),
                        Pair.of("{element}", slider.getUiElementType()),
                        Pair.of("{name}", slider.wrappedName())
                ));
        Allure.getLifecycle().startStep(String.valueOf(UUID.randomUUID()), stepResult);
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

    @Around("execution (* allurium.carousels.AbstractSlider.scrollLeft())")
    @SuppressWarnings("unchecked")
    public void stepScrollBackward(ProceedingJoinPoint invocation) throws Throwable {
        AbstractCarousel slider = (AbstractCarousel) invocation.getThis();
        StepResult stepResult = new StepResult()
                .setName(StepTextProvider.getStepText("slider_scroll_back", slider.getParent(),
                        Pair.of("{element}", slider.getUiElementType()),
                        Pair.of("{name}", slider.wrappedName())
                ));
        Allure.getLifecycle().startStep(String.valueOf(UUID.randomUUID()), stepResult);
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
