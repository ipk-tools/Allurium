package dm.tools.browser;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * A utility class for managing browser actions related to local storage, cookies, and other browser state interactions.
 * <p>
 * The `BrowserSteps` class provides a collection of static methods to interact with the browser's local storage, manage cookies,
 * and perform common browser-related steps. It simplifies browser state manipulation in test automation and debugging scenarios.
 * </p>

 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Provide reusable methods for browser state operations.</li>
 *     <li>Streamline test setup and teardown by handling browser state programmatically.</li>
 * </ul>
 *
 * @author Iaroslav Pilipenko
 */
@UtilityClass
@Slf4j
public class BrowserSteps {

    /**
     * Clears all entries from the browser's local storage.
     * <p>This method executes a JavaScript command to clear all data from the browser's local storage.</p>
     * <h3>Purpose:</h3>
     * <ul>
     *     <li>Ensure a clean state for tests by removing all local storage items.</li>
     * </ul>
     * <h3>Step:</h3>
     * <pre>{@code Clear the local storage}</pre>
     */
    @Step("Clear the local storage")
    public static void clearLocalStorage() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("window.localStorage.clear();");
            log.info("Local storage has been cleared.");
        } catch (Exception e) {
            log.error("Failed to clear local storage", e);
        }
    }

    /**
     * Inserts a key-value pair into the browser's local storage.
     * <p>This method uses JavaScript to add an item with the specified key and value into the browser's local storage.</p>
     * @param key   the key of the local storage item
     * @param value the value to associate with the key
     * <h3>Step:</h3>
     * <pre>{@code Insert the item '{key}' with the value '{value}' into the local storage}</pre>
     */
    @Step("Insert the item '{key}' with the value '{value}' into the local storage")
    public static void addValueToLocalStorage(String key, String value) {
        ((JavascriptExecutor) getWebDriver())
                .executeScript("localStorage.setItem(arguments[0],arguments[1])",key,value);
    }

    /**
     * Updates the value of an existing item in the browser's local storage.
     * <p>
     * This method modifies the value of an item with the specified key in local storage, or creates it if it does not exist.
     * </p>
     * @param key      the key of the local storage item to update
     * @param newValue the new value to set for the item
     * <h3>Step:</h3>
     * <pre>{@code Update the local storage item '{key}' with the new value '{newValue}'}</pre>
     */
    @Step("Update the local storage item '{key}' with the new value '{newValue}'")
    public static void updateValueInLocalStorage(String key, String newValue) {
        try {
            ((JavascriptExecutor) getWebDriver())
                    .executeScript("localStorage.setItem(arguments[0], arguments[1])", key, newValue);
            log.info("Value for key '{}' has been updated in local storage to '{}'.", key, newValue);
        } catch (Exception e) {
            log.error("Failed to update value for key '{}' in local storage", key, e);
        }
    }

    /**
     * Removes an item from the browser's local storage by its key.
     * <p> This method deletes the local storage item with the specified key, if it exists.</p>
     * @param variable the key of the local storage item to remove
     * <h3>Step:</h3>
     * <pre>{@code Removing the '{variable}' from the local storage}</pre>
     */
    @Step("Removing the '{variable}' from the local storage")
    public static void deleteValueFromLocalStorage(String variable) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("window.localStorage.removeItem(arguments[0]);", variable);
            log.info("Variable '{}' has been removed from local storage.", variable);
        } catch (Exception e) {
            log.error("Failed to remove variable '{}' from local storage", variable, e);
        }
    }

    /**
     * Asserts that the local storage item with the specified key has the expected value.
     * <p>
     * This method retrieves the value from the local storage for the provided key and compares it
     * with the expected value. Throws an assertion error if the key is not present or the value does not match.
     * </p>
     *
     * @param key           the key of the local storage item
     * @param expectedValue the expected value for the key
     * <h3>Step:</h3>
     * <pre>{@code Assert the local storage item '{key}' has the value '{expectedValue}'}</pre>
     */
    @Step("Assert the local storage item '{key}' has the value '{expectedValue}'")
    public static void assertLocalStorageValue(String key, String expectedValue) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            String actualValue = (String) js.executeScript("return localStorage.getItem(arguments[0]);", key);

            if (actualValue == null) {
                throw new AssertionError(String.format("No value found in local storage for key '%s'", key));
            }
            if (!actualValue.equals(expectedValue)) {
                throw new AssertionError(String.format("Value mismatch for key '%s': expected '%s', but found '%s'",
                        key, expectedValue, actualValue));
            }
            log.info("Local storage item '{}' has the expected value '{}'.", key, expectedValue);
        } catch (AssertionError e) {
            log.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error while asserting local storage value for key '{}'", key, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Asserts that the local storage does not contain an item with the specified key.
     * <p>
     * This method checks the absence of a key in the local storage and throws an assertion error if the key exists.
     * </p>
     *
     * @param key the key to check in the local storage
     * <h3>Step:</h3>
     * <pre>{@code Assert that the local storage does not contain an item with the key '{key}'}</pre>
     */
    @Step("Assert that the local storage does not contain an item with the key '{key}'")
    public static void assertLocalStorageValueAbsent(String key) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            Object value = js.executeScript("return localStorage.getItem(arguments[0]);", key);
            if (value != null) {
                throw new AssertionError(String.format("Key '%s' exists in local storage with value '%s', but it was expected to be absent.", key, value));
            }
            log.info("Verified that local storage does not contain item with key '{}'.", key);
        } catch (AssertionError e) {
            log.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error while asserting absence of local storage key '{}'", key, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Asserts that the local storage is empty.
     * <p>
     * This method verifies that there are no items in the local storage by checking its length.
     * Throws an assertion error if the local storage is not empty.
     * </p>
     * <h3>Step:</h3>
     * <pre>{@code Assert that the local storage is blank}</pre>
     */
    @Step("Assert that the local storage is blank")
    public static void assertLocalStorageIsBlank() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            Long localStorageLength = (Long) js.executeScript("return localStorage.length;");
            if (localStorageLength != 0) {
                throw new AssertionError(String.format("Local storage is not blank. It contains %d item(s).", localStorageLength));
            }
            log.info("Verified that local storage is blank.");
        } catch (AssertionError e) {
            log.error("Assertion failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error while asserting that local storage is blank", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * Adds a cookie to the browser and refreshes the current page.
     * <p>
     * This method appends a cookie with the specified name and value to the browser's cookie storage
     * and then refreshes the page to ensure the cookie is applied.
     * </p>
     *
     * @param nameCookie  the name of the cookie
     * @param valueCookie the value of the cookie
     * <h3>Step:</h3>
     * <pre>{@code Add the cookie '{nameCookie}' with the value='{valueCookie}'}</pre>
     */
    @Step("Add the cookie '{nameCookie}' with the value='{valueCookie}'")
    public static void addCookie(String nameCookie, String valueCookie) {
        try {
            getWebDriver().manage().addCookie(new Cookie(nameCookie, valueCookie));
            Selenide.refresh();
        } catch (Exception e) {
            log.error("Error on the cookie adding", e);
        }
    }

    /**
     * Adds multiple cookies to the browser and refreshes the current page.
     * <p>
     * This method iterates over the provided map of cookies, adding each cookie to the browser's
     * cookie storage, and then refreshes the page to apply the changes.
     * </p>
     *
     * @param cookies a map containing cookie names as keys and their respective values as values
     * <h3>Example Usage:</h3>
     * <pre>{@code
     * Map<String, String> cookies = Map.of("sessionToken", "abc123", "userId", "42");
     * BrowserSteps.addCookies(cookies);
     * }</pre>
     */
    public static void addCookies(Map<String, String> cookies) {
        try {
            for (Map.Entry<String, String> cookie : cookies.entrySet()) {
                String nameCookie = cookie.getKey();
                String valueCookie = cookie.getValue();
                getWebDriver().manage().addCookie(new Cookie(nameCookie, valueCookie));
            }
            Selenide.refresh();
        } catch (Exception e) {
            log.error("Error on the cookie adding", e);
        }
    }

    /**
     * Updates the value of an existing cookie or creates a new one if it doesn't exist, then refreshes the page.
     * <p>
     * This method first deletes the cookie with the specified name (if present) and then adds a new cookie with
     * the same name but a new value. The page is refreshed to apply the updated cookie.
     * </p>
     *
     * @param nameCookie     the name of the cookie to update
     * @param newValueCookie the new value for the cookie
     * <h3>Step:</h3>
     * <pre>{@code Update the cookie '{nameCookie}' with the new value='{newValueCookie}'}</pre>
     */
    @Step("Update the cookie '{nameCookie}' with the new value='{newValueCookie}'")
    public static void updateCookie(String nameCookie, String newValueCookie) {
        try {
            getWebDriver().manage().deleteCookieNamed(nameCookie);
            getWebDriver().manage().addCookie(new Cookie(nameCookie, newValueCookie));
            Selenide.refresh();
        } catch (Exception e) {
            log.error("Error updating the cookie value", e);
        }
    }

    /**
     * Deletes a cookie from the browser by its name and refreshes the current page.
     * <p>
     * This method removes a single cookie specified by its name from the browser's
     * cookie storage and then refreshes the page to apply the changes.
     * </p>
     *
     * @param nameCookie the name of the cookie to remove
     * <h3>Step:</h3>
     * <pre>{@code Remove the cookie '{nameCookie}'}</pre>
     */
    @Step("Remove the cookie '{nameCookie}'")
    public static void deleteCookie(String nameCookie) {
        try {
            getWebDriver().manage().deleteCookieNamed(nameCookie);
            Selenide.refresh();
        } catch (Exception e) {
            log.error("Error on the cookie removal", e);
        }
    }

    /**
     * Deletes all cookies from the browser and refreshes the current page.
     * <p>
     * This method clears all cookies stored in the browser and then refreshes the page
     * to reflect the changes.
     * </p>
     * <h3>Step:</h3>
     * <pre>{@code Remove all cookies}</pre>
     */
    @Step("Remove all cookies")
    public static void deleteAllCookies() {
        try {
            getWebDriver().manage().deleteAllCookies();
            Selenide.refresh();
        } catch (Exception e) {
            log.error("Error on removing all cookies", e);
        }
    }

    /**
     * Asserts that a cookie with the specified name is present in the browser.
     * <p>This method checks if the cookie exists within the browser's storage, retrying until the timeout is reached.</p>
     *
     * @param cookieName the name of the cookie to check
     * <h3>Step:</h3>
     * <pre>{@code Assert that the cookie '{cookieName}' presents}</pre>
     * <h3>Example Usage:</h3>
     * <pre>{@code
     * BrowserSteps.assertCookiePresent("sessionCookie");
     * }</pre>
     */
    @Step("Assert that the cookie '{cookieName}' presents")
    public static void assertCookiePresent(String cookieName) {
        long timer = Configuration.timeout / 1000;
        Cookie cookie = null;

        while (cookie == null && timer > 0) {
            cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed(cookieName);
            timer--;
            if (cookie == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Assertions.assertThat(cookie)
                .as("Cookie with name '" + cookieName + "' should be present")
                .isNotNull();
    }

    /**
     * Asserts that a cookie with the specified name is absent in the browser's storage.
     * <p>This method ensures that the cookie does not exist, retrying until the timeout is reached.</p>
     *
     * @param cookieName the name of the cookie to check
     * <h3>Step:</h3>
     * <pre>{@code Assert that the cookie '{cookieName}' is absent in the local storage}</pre>
     * <h3>Example Usage:</h3>
     * <pre>{@code
     * BrowserSteps.assertCookieAbsent("obsoleteCookie");
     * }</pre>
     */
    @Step("Assert that the cookie '{cookieName}' is absent in the local storage")
    public static void assertCookieAbsent(String cookieName) {
        long timer = Configuration.timeout / 1000;
        Cookie cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed(cookieName);

        while (cookie != null && timer > 0) {
            cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed(cookieName);
            timer--;
            if (cookie != null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Assertions.assertThat(cookie)
                .as("Cookie with name '" + cookieName + "' should be absent")
                .isNull();
    }

    /**
     * Asserts that a cookie has the expected value.
     * <p>
     * This method retrieves the cookie with the specified name and verifies that its value matches the expected value.
     * </p>
     *
     * @param cookieName    the name of the cookie to check
     * @param expectedValue the expected value of the cookie
     * <h3>Step:</h3>
     * <pre>{@code Assert that the cookie '{cookieName}' has the value '{expectedValue}'}</pre>
     * <h3>Example Usage:</h3>
     * <pre>{@code
     * BrowserSteps.assertCookieValue("sessionCookie", "xyz123");
     * }</pre>
     */
    @Step("Assert that the cookie '{cookieName}' has the value '{expectedValue}'")
    public static void assertCookieValue(String cookieName, String expectedValue) {
        try {
            Cookie cookie = getWebDriver().manage().getCookieNamed(cookieName);
            if (cookie == null) {
                throw new AssertionError("Cookie with name '" + cookieName + "' was not found.");
            }
            String actualValue = cookie.getValue();
            if (!actualValue.equals(expectedValue)) {
                throw new AssertionError("Expected value for cookie '" + cookieName + "' is '" + expectedValue
                        + "', but found '" + actualValue + "'.");
            }
            log.info("Cookie '{}' has the expected value '{}'.", cookieName, expectedValue);
        } catch (Exception e) {
            log.error("Error while asserting value for cookie '{}'", cookieName, e);
            throw new AssertionError("Error occurred during cookie value assertion for cookie '" + cookieName + "'.", e);
        }
    }

}
