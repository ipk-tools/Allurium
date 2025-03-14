package allurium;

import allurium.primitives.UIElement;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

import java.util.UUID;
import java.util.concurrent.Callable;

public class StepConverter {

    public static <T> void wrapIntoStep(Callable<T> stepBody, String stepText) throws Throwable {
        String stepUuid = String.valueOf(UUID.randomUUID());
        StepResult stepResult = new StepResult().setName(stepText);
        Allure.getLifecycle().startStep(stepUuid, stepResult);

        boolean errorStatus = false;
        try {
            stepBody.call();
        } catch (Throwable assertionException) {
            assertionException.printStackTrace();
            errorStatus = true;
            throw assertionException;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            Allure.getLifecycle().stopStep(stepUuid);
        }
    }

    public static void wrapIntoStepWithScreenshot(Runnable stepBody, String stepText, UIElement element) {
        String stepUuid = String.valueOf(UUID.randomUUID());
        StepResult stepResult = new StepResult().setName(stepText);
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);

        boolean errorStatus = false;
        try {
            stepBody.run();
            AllureUtils.attachElementScreenshotToStep(element.getRoot(), element.getUiElementName(), stepResult);
        } catch (Throwable assertionException) {
            assertionException.printStackTrace();
            errorStatus = true;
            throw assertionException;
        } finally {
            if (errorStatus)
                stepResult.setStatus(Status.FAILED);
            else {
                stepResult.setStatus(Status.PASSED);
            }
            AsyncAllureLogger.stopStepAsync();
        }
    }
}
