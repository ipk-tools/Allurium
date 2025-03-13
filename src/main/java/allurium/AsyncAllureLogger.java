package allurium;

import io.qameta.allure.Allure;
import io.qameta.allure.model.StepResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
This is not usable at this state and in dev. Serves to slightly accelerate aspect steps logging.
Currently, there are problems with steps shuffling while more than one thread.
 */
public class AsyncAllureLogger {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void startStepAsync(final String stepUuid, final StepResult stepResult) {
        executor.submit(() -> {
            // Offload the step start call
            Allure.getLifecycle().startStep(stepUuid, stepResult);
        });
    }

    public static void stopStepAsync() {
        executor.submit(() -> {
            // Offload the step stop call
            Allure.getLifecycle().stopStep();
        });
    }

    // Optional: call at the end of a test run to cleanly shutdown the executor.
    public static void shutdownLogger() {
        try {
            executor.shutdown();
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
