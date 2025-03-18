package allurium;

import allurium.driver.DriverWait;
import allurium.primitives.UIElement;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * This class provides utility methods for performing common UI interactions in Selenium tests.
 * All methods are marked with the `@Step` annotation from Allure for better reporting.
 *
 * @author Iaroslav Pilipenko
 */
@UtilityClass
@Slf4j
public class UiSteps {

    /**
     * Opens the browser and navigates to the specified URL.
     *
     * @param url the URL to open
     */
    @Step("Open the browser and navigate to {url}")
    public void openBrowser(String url) {
        Selenide.open(url);
    }

    /**
     * Opens the browser without navigating to a specific URL.
     */
    @Step("Open the browser")
    public void openBrowser() {
        Selenide.open();
    }

    /**
     * Opens the specified URL with login credentials.
     *
     * @param url     The URL to open.
     * @param login   The login username.
     * @param password The login password.
     */
    @Step("Open the page {url} with the login {login} and the password {password}")
    public static void loadPage(String url, String login, String password) {
        boolean success = false;
        int retry = 1;
        while (!success && retry <= 6) {
            try {
                retry++;
                Selenide.open(url, "", login, password);
                success = true;
            } catch (Exception e) {
                log.error("Error on browser open", e);
            }
        }
        if (!success)
            Selenide.open(url, "", login, password);
    }

    /**
     * Loads the specified URL with multiple retries.
     * Avoid using this method to open a browser, as it may cause multiple instances if retries are triggered.
     *
     * @param url the URL to load
     */
    @Step("Load the page {url}")
    public static void loadPage(String url) {
        boolean success = false;
        int retry = 1;
        while (!success && retry <= 6) {
            try {
                retry++;
                Selenide.open(url);
                success = true;
            } catch (Exception e) {
                log.error("Error on browser open", e);
            }
        }
        if (!success)
            Selenide.open(url);
    }

    /**
     * Loads the specified URL and waits for the page to reach a complete state.
     *
     * @param pageUrl the URL to load
     */
    @Step("Load the page {pageUrl} and wait for the complete status")
    public static void loadPageAndWaitCompleteState(final String pageUrl) {
        Selenide.open(pageUrl);
        DriverWait.waiting().waitForPageLoadSafety();
    }

    /**
     * Loads the specified URL, waits for the page to reach a complete state, and retries
     * the loading process if necessary.
     *
     * @param pageUrl the URL to load
     * @param retries the number of retries for loading
     */
    @Step("Load the page {pageUrl} and wait for the complete status")
    public static void loadPageAndWaitCompleteState(final String pageUrl, int retries) {
        Selenide.open(pageUrl);
        DriverWait.waiting().waitForPageLoadSafety(retries);
    }

    /**
     * Opens a new blank tab in the browser and switches to it.
     * Uses JavaScript execution to create the new tab.
     */
    @Step("Open a new blank tab")
    public static void openNewBlankTab() {
        try {
            ((JavascriptExecutor) getWebDriver()).executeScript("window.open('about:blank', '_blank');");
            log.info("New blank tab opened successfully.");
            Set<String> windowHandles = getWebDriver().getWindowHandles();
            String newTabHandle = (String) windowHandles.toArray()[windowHandles.size() - 1];
            WebDriverRunner.getWebDriver().switchTo().window(newTabHandle);
        } catch (Exception e) {
            log.error("Failed to open a new blank tab.", e);
            throw e;
        }
    }

    /**
     * Opens a new browser tab and navigates to the specified URL.
     * The method uses JavaScript execution to open the tab and switches to it.
     *
     * @param url the URL to open in the new tab
     */
    @Step("Open a new tab with the URL '{url}'")
    public static void openNewTabWithUrl(String url) {
        try {
            ((JavascriptExecutor) getWebDriver()).executeScript("window.open(arguments[0], '_blank');", url);
            log.info("New tab opened with URL: {}", url);
            Set<String> windowHandles = getWebDriver().getWindowHandles();
            String newTabHandle = (String) windowHandles.toArray()[windowHandles.size() - 1];
            WebDriverRunner.getWebDriver().switchTo().window(newTabHandle);
            log.info("Switched to the new tab with URL: {}", url);
        } catch (Exception e) {
            log.error("Failed to open a new tab with URL: {}", url, e);
            throw e;
        }
    }

    /**
     * Switches to the browser tab with the specified title.
     * If the tab is not found, an exception is thrown.
     *
     * @param tabTitle the title of the tab to switch to
     */
    @Step("Switch to the browser  tab with the title '{tabTitle}'")
    public static void switchToTab(String tabTitle) {
        try {
            boolean tabFound = false;
            for (String handle : getWebDriver().getWindowHandles()) {
                WebDriverRunner.getWebDriver().switchTo().window(handle);
                if (WebDriverRunner.getWebDriver().getTitle().equals(tabTitle)) {
                    log.info("Switched to the tab with title '{}'.", tabTitle);
                    tabFound = true;
                    break;
                }
            }
            if (!tabFound) {
                throw new IllegalArgumentException("Tab with title '" + tabTitle + "' does not exist.");
            }
        } catch (Exception e) {
            log.error("Failed to switch to the tab with title '{}'.", tabTitle, e);
            throw e;
        }
    }

    /**
     * Switches to the browser tab at the specified index.
     * The index is based on the order of the window handles.
     *
     * @param index the index of the tab to switch to (0-based)
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    @Step("Switch to the browser tab with index {index}")
    public void switchToTab(int index) {
        try {
            Set<String> windowHandles = getWebDriver().getWindowHandles();
            if (index >= 0 && index < windowHandles.size()) {
                String tabName = (String) windowHandles.toArray()[index];
                WebDriverRunner.getWebDriver().switchTo().window(tabName);
                log.info("Switched to the tab at index '{}'.", index);
            } else {
                throw new IndexOutOfBoundsException("Invalid tab index: " + index);
            }
        } catch (Exception e) {
            log.error("Failed to switch to the tab at index '{}'.", index, e);
            throw e;
        }
    }

    /**
     * Closes the currently active browser tab.
     * If other tabs are open, switches to the next available tab.
     * Logs warnings if no tabs are available after closing.
     */
    @Step("Close the current tab")
    public static void closeCurrentTab() {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            String currentTabHandle = driver.getWindowHandle();
            driver.close();
            log.info("Current tab with handle '{}' has been closed.", currentTabHandle);
            Set<String> windowHandles = driver.getWindowHandles();
            if (!windowHandles.isEmpty()) {
                String nextTabHandle = windowHandles.iterator().next();
                driver.switchTo().window(nextTabHandle);
                log.info("Switched to the next available tab with handle '{}'.", nextTabHandle);
            } else {
                log.warn("No other tabs available to switch to after closing the current tab.");
            }
        } catch (Exception e) {
            log.error("Failed to close the current tab.", e);
            throw e;
        }
    }

    /**
     * Closes the browser tab identified by the given tab handle.
     * If other tabs are open, switches to the next available tab.
     * Logs warnings if the specified tab handle is not found or if no tabs remain open.
     *
     * @param tabHandle the handle of the tab to be closed
     */
    @Step("Close the tab with the handle '{tabHandle}'")
    public static void closeTab(String tabHandle) {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            Set<String> windowHandles = driver.getWindowHandles();
            if (windowHandles.contains(tabHandle)) {
                driver.switchTo().window(tabHandle);
                driver.close();
                log.info("Tab with handle '{}' has been closed.", tabHandle);
                Set<String> remainingHandles = driver.getWindowHandles();
                if (!remainingHandles.isEmpty()) {
                    String nextTabHandle = remainingHandles.iterator().next();
                    driver.switchTo().window(nextTabHandle);
                    log.info("Switched to the next available tab with handle '{}'.", nextTabHandle);
                } else {
                    log.warn("No other tabs available to switch to after closing the tab '{}'.", tabHandle);
                }
            } else {
                log.warn("No tab found with handle '{}'.", tabHandle);
            }
        } catch (Exception e) {
            log.error("Failed to close the tab with handle '{}'.", tabHandle, e);
            throw e;
        }
    }

    /**
     * Closes the browser tab located at the specified index in the list of open tabs.
     * If other tabs are available, switches to the next available tab.
     * Logs warnings if the index is invalid or if no tabs remain open after the operation.
     *
     * @param index the zero-based index of the tab to be closed
     * @throws IndexOutOfBoundsException if the provided index is invalid
     */
    @Step("Close the tab at index {index}")
    public static void closeTab(int index) {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            Set<String> windowHandles = driver.getWindowHandles();
            // Validate the index
            if (index < 0 || index >= windowHandles.size()) {
                throw new IndexOutOfBoundsException("Invalid tab index: " + index);
            }
            // Get the tab handle at the specified index
            String tabHandle = (String) windowHandles.toArray()[index];
            driver.switchTo().window(tabHandle);
            driver.close();
            log.info("Tab at index '{}' with handle '{}' has been closed.", index, tabHandle);
            // Switch to the next available tab (if any)
            Set<String> remainingHandles = driver.getWindowHandles();
            if (!remainingHandles.isEmpty()) {
                String nextTabHandle = remainingHandles.iterator().next();
                driver.switchTo().window(nextTabHandle);
                log.info("Switched to the next available tab with handle '{}'.", nextTabHandle);
            } else {
                log.warn("No other tabs available to switch to after closing the tab at index '{}'.", index);
            }
        } catch (Exception e) {
            log.error("Failed to close the tab at index '{}'.", index, e);
            throw e;
        }
    }

    /**
     * Asserts that the current browser URL matches the specified value.
     * If the URL does not match, retries every second until the timeout defined in
     * {@link Configuration#timeout} is reached. Throws an assertion error if the URL still
     * does not match after the timeout.
     *
     * @param url the expected URL to assert
     * @throws AssertionError if the current URL does not match the expected value
     */
    @Step("Assert that the current url is '{url}'")
    public static void assertUrl(String url) {
        long timer = Configuration.timeout / 1000;
        while (!WebDriverRunner.url().equals(url) && timer > 0) {
            timer--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assertions.assertThat(WebDriverRunner.url()).as("Current URL").isEqualTo(url);
    }

    /**
     * Asserts that the current browser URL contains the specified part.
     * Retries every second until the URL contains the part or the timeout defined in
     * {@link Configuration#timeout} is reached. Throws an assertion error if the condition is not met.
     *
     * @param urlPart the substring that the current URL should contain
     * @throws AssertionError if the URL does not contain the specified substring after the timeout
     */
    @Step("Assert that the current url contains '{urlPart}'")
    public static void assertUrlContains(String urlPart) {
        long timer = Configuration.timeout / 1000;
        while (!WebDriverRunner.url().contains(urlPart) && timer > 0) {
            timer--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assertions.assertThat(WebDriverRunner.url()).as("Current URL").contains(urlPart);
    }

    /**
     * Asserts that the current browser URL contains the specified part within a custom timeout.
     * Retries every second until the URL contains the part or the timeout is reached. Throws an assertion
     * error if the condition is not met.
     *
     * @param urlPart the substring that the current URL should contain
     * @param timerSec the maximum time in seconds to wait for the condition
     * @throws AssertionError if the URL does not contain the specified substring after the timeout
     */
    @Step("Assert that the current url contains '{urlPart}'")
    public static void assertUrlContains(String urlPart, int timerSec) {
        int timer = timerSec;
        while (!WebDriverRunner.url().contains(urlPart) && timer > 0) {
            timer--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assertions.assertThat(WebDriverRunner.url()).as("Current URL").contains(urlPart);
    }

    /**
     * Asserts that the current browser URL ends with the specified part.
     *
     * @param urlPart the expected ending of the current URL
     * @throws AssertionError if the URL does not end with the specified substring
     */
    @Step("Assert that the current url ends with '{urlPart}'")
    public static void assertUrlPath(String urlPart) {
        long timeoutInSeconds = Configuration.timeout / 1000;
        long endTime = System.currentTimeMillis() + timeoutInSeconds * 1000;
        while (!WebDriverRunner.url().endsWith(urlPart) && System.currentTimeMillis() < endTime) {
            Selenide.sleep(AlluriumConfig.retryIntervalMs());
        }
        Assertions.assertThat(WebDriverRunner.url()).as("Current URL").endsWith(urlPart);
    }

    /**
     * Pauses the execution for a specified duration.
     *
     * @param millisec the duration of the delay in milliseconds
     */
    @Step("Technical delay [{millisec}] seconds")
    public static void delay(long millisec) {
        Selenide.sleep(millisec);
    }

    /**
     * Pauses the execution for a specified duration with optional logging.
     *
     * @param seconds the duration of the delay in seconds
     * @param showLog whether to log the delay
     */
    public static void waiting(long seconds, boolean showLog) {
        if (showLog)
            waiting(seconds);
        else
            Selenide.sleep(seconds*1000);
    }

    /**
     * Asserts that the current browser page title matches the specified title.
     * Retries every second until the title matches or the timeout defined in
     * {@link Configuration#timeout} is reached. Throws an assertion error if the condition is not met.
     *
     * @param title the expected page title
     * @throws AssertionError if the page title does not match the specified title after the timeout
     */
    @Step("Assert that the current page title is '{title}'")
    public static void assertPageTitle(String title) {
        long timer = Configuration.timeout / 1000;
        while (!WebDriverRunner.getWebDriver().getTitle().equals(title) && timer > 0) {
            timer--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assertions.assertThat(WebDriverRunner.getWebDriver().getTitle())
                .as("Current Page Title")
                .isEqualTo(title);
    }

    /**
     * Asserts that the current browser page title contains the specified part.
     * Retries every second until the title contains the part or the timeout defined in
     * {@link Configuration#timeout} is reached. Throws an assertion error if the condition is not met.
     *
     * @param titlePart the substring that the current page title should contain
     * @throws AssertionError if the page title does not contain the specified substring after the timeout
     */
    @Step("Assert that the current page title contains '{titlePart}'")
    public static void assertPageTitleContains(String titlePart) {
        long timer = Configuration.timeout / 1000;
        while (!WebDriverRunner.getWebDriver().getTitle().contains(titlePart) && timer > 0) {
            timer--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assertions.assertThat(WebDriverRunner.getWebDriver().getTitle())
                .as("Current Page Title")
                .contains(titlePart);
    }

    /**
     * Pauses the execution for the specified number of seconds.
     *
     * @param seconds the number of seconds to wait
     */
    @Step("Waiting [{seconds}] seconds")
    public static void waiting(long seconds) {
        Selenide.sleep(seconds*1000);
    }

    /**
     * Pauses the execution for the specified number of seconds, with a custom description for the waiting step.
     *
     * @param sec         the number of seconds to wait
     * @param description a brief description of why the waiting is occurring
     */
    @Step("Waiting [{description}] during [{sec}] second(s)")
    public static void waiting(long sec, String description) {
        waiting(sec);
    }

    /**
     * Pauses the execution for the specified number of seconds, with a description of the condition being waited for.
     *
     * @param sec         the number of seconds to wait
     * @param description a description of the condition being waited for
     */
    @Step("Waiting [{sec}] second(s) until [{description}]")
    public static void waitingUntil(long sec, String description) {
        waiting(sec);
    }

    /**
     * Pauses the execution for the specified number of seconds, with a description of the action being waited for.
     *
     * @param sec         the number of seconds to wait
     * @param description a description of the action being waited for
     */
    @Step("Waiting [{sec}] second(s) for [{description}]")
    public static void waitingFor(long sec, String description) {
        waiting(sec);
    }

    /**
     * Refreshes the current page in the browser.
     */
    @Step("Refresh the current page")
    public static void refreshCurrentPage() {
        WebDriverRunner.getWebDriver().navigate().refresh();
    }

    /**
     * Navigates to the previous page in the browser's history.
     */
    @Step("Press 'Backspace'")
    public static void goBack() {
        WebDriverRunner.getWebDriver().navigate().back();
    }

    /**
     * Navigates to the next page in the browser's history.
     */
    @Step("Press 'Forward'")
    public static void goForward() {
        WebDriverRunner.getWebDriver().navigate().forward();
    }

    /**
     * Scrolls the page either to the top or the bottom.
     *
     * @param up if true, scrolls to the top; if false, scrolls to the bottom
     */
    @Step("Scrolling page up [{up}]")
    public static void scrollPage(boolean up) {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        if (up)
            js.executeScript("window.scrollTo({ top: 0, behavior: 'smooth' })");
        else
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Smoothly scrolls the page to the top.
     */
    @Step("Scroll the page to the top")
    public static void scrollTop() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        js.executeScript("window.scrollTo({ top: 0, behavior: 'smooth' })");
    }

    /**
     * Smoothly scrolls the page to the bottom.
     */
    @Step("Scroll the page to the bottom")
    public static void scrollBottom() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Simulates pressing the 'Enter' key on the body element of the page.
     */
    @Step("Press the key 'Enter'")
    public static void pressEnter() {
        $("body").pressEnter();
    }

    /**
     * Simulates pressing the 'Escape' key on the body element of the page.
     */
    @Step("Press the key 'Esc'")
    public static void pressEsc() {
        $("body").pressEscape();
    }

    /**
     * Simulates pressing the 'Arrow Up' key using WebDriver actions.
     */
    @Step("Press the 'Arrow Up' key")
    public static void pressArrowUp() {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            Actions actions = new Actions(driver);
            actions.sendKeys(org.openqa.selenium.Keys.ARROW_UP).perform();
        } catch (Exception e) {
            System.err.println("Failed to press the Arrow Up key: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Simulates pressing the 'Arrow Down' key using WebDriver actions.
     */
    @Step("Press the 'Arrow Down' key")
    public static void pressArrowDown() {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            Actions actions = new Actions(driver);
            actions.sendKeys(org.openqa.selenium.Keys.ARROW_DOWN).perform();
        } catch (Exception e) {
            System.err.println("Failed to press the Arrow Down key: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Simulates pressing the 'Arrow Left' key using WebDriver actions.
     */
    @Step("Press the 'Arrow Left' key")
    public static void pressArrowLeft() {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            Actions actions = new Actions(driver);
            actions.sendKeys(org.openqa.selenium.Keys.ARROW_LEFT).perform();
        } catch (Exception e) {
            System.err.println("Failed to press the Arrow Left key: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Simulates pressing the 'Arrow Right' key using WebDriver actions.
     */
    @Step("Press the 'Arrow Right' key")
    public static void pressArrowRight() {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            Actions actions = new Actions(driver);
            actions.sendKeys(org.openqa.selenium.Keys.ARROW_RIGHT).perform();
        } catch (Exception e) {
            System.err.println("Failed to press the Arrow Right key: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Simulates pressing the 'Tab' key to move focus to the next interactive element on the page.
     */
    @Step("Press the 'Tab' key")
    public static void pressTab() {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            Actions actions = new Actions(driver);
            actions.sendKeys(org.openqa.selenium.Keys.TAB).perform();
        } catch (Exception e) {
            System.err.println("Failed to press the Tab key: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Performs a drag-and-drop operation, moving one UI element onto another.
     *
     * @param dragThis the UI element to be dragged
     * @param toThat   the UI element to drop the dragged element onto
     */
    @Step("Drag and Drop")
    public static void dragAndDrop(UIElement dragThis, UIElement toThat) {
        try {
            Actions action = new Actions(WebDriverRunner.getWebDriver());
            action.dragAndDrop(dragThis.get().getWrappedElement(), toThat.get().getWrappedElement()).perform();
            log.info("Successfully dragged '{}' and dropped onto '{}'.",
                    dragThis.wrappedName(), toThat.wrappedName());
        } catch (Exception e) {
            log.error("Failed to drag '{}' and drop onto '{}'.",
                    dragThis.wrappedName(), toThat.wrappedName(), e);
            throw new RuntimeException("Drag-and-drop action failed.", e);
        }
    }

    /**
     * Maximizes the browser window to fill the screen.
     */
    @Step("Expand the browser window to full size")
    public static void maximize() {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            driver.manage().window().maximize();
        } catch (Exception e) {
            log.warn("Failed to maximize the browser window: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Sets the browser window size to the specified dimensions.
     *
     * @param width  the desired width of the browser window
     * @param height the desired height of the browser window
     */
    @Step("Set the browser window size to {width}x{height}")
    public static void setWindowSize(int width, int height) {
        try {
            WebDriver driver = WebDriverRunner.getWebDriver();
            driver.manage().window().setSize(new Dimension(width, height));
        } catch (Exception e) {
            log.warn("Failed to set browser window size to " + width + "x" + height + ": " + e.getMessage());
            throw e;
        }
    }

}
