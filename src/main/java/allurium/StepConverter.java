package allurium;

import allurium.primitives.UIElement;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;
import java.util.concurrent.Callable;

public class StepConverter {

    public static <T> void wrapIntoStep(Callable<T> stepBody, String stepText) throws Throwable {
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult().setName(stepText);
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);

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
            AsyncAllureLogger.stopStepAsync();
        }
    }

    public static void wrapIntoStepWithScreenshot(Runnable stepBody, String stepText, UIElement element) {
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
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
