package allurium.browser;

import allurium.primitives.UIElement;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static allurium.PropertyLoader.loadSystemPropertyOrDefault;

/**
 * A utility class for performing various browser-related actions and interactions.
 * <p>
 * The `BrowserActions` class provides a collection of static methods to manage browser logs,
 * manipulate DOM elements, handle cookies and local storage, and switch between iframes or tabs.
 * It utilizes Selenide and WebDriver to perform operations, enhancing testing and debugging capabilities.
 * </p>

 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Provide a centralized utility for browser-related actions.</li>
 *     <li>Enhance test debugging through detailed log analysis.</li>
 *     <li>Simplify common browser interactions with reusable methods.</li>
 * </ul>
 *
 * @author Iaroslav Pilipenko
 */
@UtilityClass
@Slf4j
public class BrowserActions {

    /**
     * Retrieves the browser console log entries.
     *
     * @return LogEntries object containing all console log entries from the browser.
     */
    public static LogEntries getConsoleLogEntries() {
        return WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER);
    }

    /**
     * Retrieves the browser console logs as a formatted string.
     *
     * @return A string of concatenated console log entries.
     */
    public static String getConsoleLogs() {
        return Optional.ofNullable(getConsoleLogEntries())
                .map(logs -> logs.getAll()
                        .stream()
                        .map(LogEntry::toString)
                        .collect(Collectors.joining(StringUtils.LF, StringUtils.EMPTY, StringUtils.EMPTY)))
                .orElse(StringUtils.EMPTY);
    }

    /**
     * Retrieves browser console logs, excluding specified log entries.
     *
     * @param exclusions A list of strings representing log entry substrings to exclude.
     * @return A string of filtered console log entries.
     */
    public static String getConsoleLogs(List<String> exclusions) {
        return Optional.ofNullable(getConsoleLogEntries())
                .map(logs -> logs.getAll()
                        .stream()
                        .filter(logEntry -> !logEntryMessageHasFilteredValue(logEntry, exclusions))
                        .map(LogEntry::toString)
                        .collect(Collectors.joining(StringUtils.LF, StringUtils.EMPTY, StringUtils.EMPTY)))
                .orElse(StringUtils.EMPTY);
    }

    /**
     * Removes an element from the DOM using its UIElement wrapper.
     *
     * @param element The UIElement to be removed.
     */
    public static void removeElement(UIElement element) {
        removeElement(element.get().getWrappedElement());
    }

    /**
     * Removes an element from the DOM using its Selenium By locator.
     *
     * @param element The By locator of the element to be removed.
     */
    public static void removeElement(By element) {
        try {
            removeElement(Selenide.$(element).getWrappedElement());
        } catch (NoSuchElementException | ElementNotFound ex) {
            log.error("Unable remove non existing element", ex);
        }
    }

    /**
     * Removes a WebElement from the DOM using JavaScript.
     *
     * @param element The WebElement to be removed.
     */
    private static void removeElement(WebElement element) {
        try {
            ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("arguments[0].remove()", element);
        } catch (NoSuchElementException | ElementNotFound ex) {
            log.error("Unable remove non existing element", ex);
        }
    }

    /**
     * Checks if a log entry message matches any of the exclusion criteria.
     *
     * @param logEntry The log entry to check.
     * @param criteria A list of strings to filter log entries.
     * @return True if the log entry contains an exclusion criterion, false otherwise.
     */
    private boolean logEntryMessageHasFilteredValue(final LogEntry logEntry, List<String> criteria) {
        val excludedLogs = new ArrayList<>(criteria);
        return excludedLogs.stream().anyMatch(s -> logEntry.getMessage().contains(s));
    }

    /**
     * Retrieves a cookie by its name.
     *
     * @param nameCookie The name of the cookie to retrieve.
     * @return The Cookie object if found, null otherwise.
     */
    public static Cookie getCookie(String nameCookie) {
        try {
            Set<Cookie> cookies = getWebDriver().manage().getCookies();

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(nameCookie)) {
                    return cookie;
                }
            }
        } catch (Exception e) {
            log.error("Error on the cookie getting: " + nameCookie, e);
        }
        return null;
    }

    /**
     * Retrieves a value from the browser's local storage.
     *
     * @param key The key for the value in local storage.
     * @return The value as a string, or null if not found or on failure.
     */
    public static String getValueFromLocalStorage(String key) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            String value = (String) js.executeScript("return localStorage.getItem(arguments[0]);", key);
            log.info("Retrieved value '{}' for key '{}' from local storage.", value, key);
            return value;
        } catch (Exception e) {
            log.error("Failed to retrieve value for key '{}' from local storage", key, e);
            return null;
        }
    }

    /**
     * Retrieves error logs from the browser console, excluding certain log types.
     *
     * @return A StringBuilder containing filtered console error logs.
     */
    public static StringBuilder errorInBrowserConsole() {
        if (loadSystemPropertyOrDefault("browserName", "chrome").equals("firefox"))
            return new StringBuilder().append("Logs are not available for current browser");
        else if (loadSystemPropertyOrDefault("browserName", "chrome").equals("safari"))
            return new StringBuilder().append("Logs are not available for current browser");

        LogEntries logEntries = getWebDriver().manage().logs().get(LogType.BROWSER);
        List<LogEntry> logs = logEntries.getAll().stream()
                .filter(l -> !l.getMessage().contains("example"))    // Условие для исключения
                .collect(Collectors.toList());

        StringBuilder b = new StringBuilder();
        logs.forEach(s -> b.append(s).append("\n"));
        if (logs.size() > 0)
            return b;
        else
            return new StringBuilder();
    }

    /**
     * Switches to a specific iframe by its ID.
     *
     * @param id The ID of the iframe to switch to.
     */
    public void switchFrame(String id) {
        WebDriverRunner.getWebDriver().switchTo().frame(id);
    }

    /**
     * Switches to a specific iframe by its index.
     *
     * @param id The index of the iframe to switch to.
     */
    public void switchTo(int id) {
        WebDriverRunner.getWebDriver().switchTo().frame(id);
    }

}
