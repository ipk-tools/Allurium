package allurium.driver;

import allurium.AlluriumConfig;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;

import java.util.Objects;

import static java.lang.Thread.sleep;

@Slf4j
public class DriverWait {

    private static DriverWait wait;
    private boolean isTrue;

    public static DriverWait waiting() {
        if (Objects.isNull(wait)) {
            wait = new DriverWait();
        }
        return wait;
    }

    @Step("Wait until current page will be loaded")
    public void waitForPageLoadSafety() {
        boolean isLoaded = false;
        int retries = AlluriumConfig.retryAmount();

        while (!isLoaded && retries > 0) {
            isLoaded = "complete".equals(JsAction.getPageLoadingStatus());
            if (!isLoaded) {
                try {
                    sleep(AlluriumConfig.retryIntervalMs());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            retries--;
        }

        if (!isLoaded) {
            log.error("Page did not load in the expected time");
        }
        Assertions.assertThat(isLoaded).as("loading status").isTrue();
    }

    @Step("Wait until current page will be loaded")
    public void waitForPageLoadSafety(int timerSec) {
        boolean isLoaded = false;
        int retries = timerSec;

        while (!isLoaded && retries > 0) {
            isLoaded = "complete".equals(JsAction.getPageLoadingStatus());
            if (!isLoaded) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            retries--;
        }

        if (!isLoaded) {
            log.error("Page did not load in the expected time");
        }
        Assertions.assertThat(isLoaded).as("loading status").isTrue();
    }
}
