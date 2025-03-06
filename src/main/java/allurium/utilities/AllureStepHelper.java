package allurium.utilities;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.UUID;

public class AllureStepHelper {

    /**
     * Common helper to wrap an AspectJ around-advice in a single Allure step.
     *
     * @param invocation  the current join point
     * @param stepName    the final name to show in Allure for this step
     * @throws Throwable  so the original method’s exception can propagate
     */
    public static void runAllureAspectStep(ProceedingJoinPoint invocation, String stepName) throws Throwable {
        String stepUuid = String.valueOf(UUID.randomUUID());
        StepResult stepResult = new StepResult().setName(stepName);
        Allure.getLifecycle().startStep(stepUuid, stepResult);
        boolean errorStatus = false;
        try {
            invocation.proceed();
        } catch (Throwable t) {
            errorStatus = true;
            t.printStackTrace();
            throw t;
        } finally {
            if (errorStatus) {
                stepResult.setStatus(Status.FAILED);
            } else {
                stepResult.setStatus(Status.PASSED);
            }

            Allure.getLifecycle().stopStep(stepUuid);
        }
    }
}
