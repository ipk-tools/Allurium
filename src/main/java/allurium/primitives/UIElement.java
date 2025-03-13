package allurium.primitives;

import allurium.*;
import allurium.interfaces.AlluriumElement;
import allurium.interfaces.ListComponent;
import com.codeborne.selenide.*;
import allurium.driver.JsScripts;
import allurium.exceptions.RootElementIsNotInitializedException;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StatusDetails;
import io.qameta.allure.model.StepResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Represents a wrapper around a Selenide element, providing utility methods for interaction and validation.
 * The `UIElement` class integrates with Allure for step logging and reporting.
 *
 * <p>Supports custom naming, parent-child relationships, and dynamic attribute-based assertions.
 * Includes methods for handling visibility, existence, and text content checks with retry mechanisms.</p>
 *
 * <h3>Features:</h3>
 * <ul>
 *   <li>Custom initialization using locators or Selenide elements</li>
 *   <li>Allure integration for logging steps and generating reports</li>
 *   <li>Support for retry mechanisms during assertions</li>
 *   <li>Convenience methods for common UI interactions (e.g., click, hover, assert visibility)</li>
 * </ul>
 *
 * <h3>Example Usage:</h3>
 * <pre>{@code
 * UIElement element = UIElement.$uiElement(By.id("example"));
 * element.click();
 * element.assertVisible();
 * }</pre>
 *
 * @see com.codeborne.selenide.SelenideElement
 * @see io.qameta.allure.Allure
 */
@NoArgsConstructor
@Slf4j
public class UIElement implements AlluriumElement, ListComponent {

    /**
     * The root Selenide element represented by this UIElement.
     * Initialized via constructor or factory methods.
     */
    @Getter
    protected SelenideElement root;

    /**
     * Metadata describing the UI element. By default, initialized with "role:element".
     */
    @Getter
    private final StringBuffer metaKeys = new StringBuffer().append("role:element");

    /**
     * The unique identifier of this element. Fallbacks to the text content if not explicitly set.
     */
    @Setter
    protected String id = "";

    /**
     * Type of the UI element (e.g., "button", "link"). Useful for logging and reporting.
     */
    @Getter
    @Setter
    protected String uiElementType = "";

    /**
     * Name of the UI element. If not explicitly set, it may be derived dynamically based on attributes.
     */
    @Setter
    protected String uiElementName = "";

    /**
     * Method used to assign a name to the element dynamically (e.g., "text", "href").
     */
    @Setter
    protected String assignNameMethod = "text";

    /**
     * A description of the element for additional context.
     */
    @Getter
    @Setter
    protected String description = "";

    /**
     * The parent widget or component containing this UIElement, if any.
     */
    @Getter
    @Setter
    protected Optional<AbstractWidget> parent = Optional.empty();

    protected boolean stepsConsoleLoggingEnabled = true;
    protected boolean stepsReportLoggingEnabled = true;

    private static String nameWrapStart = AlluriumConfig.highlighterStart();
    private static String nameWrapEnd = AlluriumConfig.highlighterEnd();

    /**
     * Initializes a UIElement using a Selenide locator (CSS selector).
     *
     * @param selenideLocator the CSS selector to locate the element
     */
    protected UIElement(String selenideLocator) {
        root = Selenide.$(selenideLocator);
    }

    /**
     * Initializes a UIElement using a Selenide locator (CSS selector) and assigns a name.
     *
     * @param selenideLocator the CSS selector to locate the element
     * @param name            the name to assign to the element
     */
    protected UIElement(String selenideLocator, String name) {
        root = Selenide.$(selenideLocator);
        this.uiElementName = name;
        root = root.as(uiElementName);
    }

    /**
     * Creates a UIElement from a Selenium locator.
     *
     * @param locator the Selenium locator (e.g., By.id, By.xpath)
     */
    protected UIElement(By locator) {
        root = Selenide.$(locator);
    }

    /**
     * Creates a UIElement from a Selenium locator and assigns a name.
     *
     * @param locator the Selenium locator
     * @param name    the name to assign to the element
     */
    protected UIElement(By locator, String name) {
        root = Selenide.$(locator);
        this.uiElementName = name;
        root = root.as(uiElementName);
    }

    public UIElement(SelenideElement selenideElement) {
        root = selenideElement;
    }

    protected UIElement(SelenideElement selenideElement, String name) {
        root = selenideElement;
        this.uiElementName = name;
        root = root.as(uiElementName);
    }

    /**
     * Creates a UIElement from a Selenium locator.
     *
     * @param locator the Selenium locator (e.g., By.id, By.xpath)
     * @return a new UIElement instance
     */
    public static UIElement $uiElement(By locator) {
        return new UIElement(locator);
    }

    /**
     * Creates a UIElement from a Selenium locator and assigns a name.
     *
     * @param locator the Selenium locator
     * @param name    the name to assign to the element
     * @return a new UIElement instance
     */
    public static UIElement $uiElement(By locator, String name) {
        return new UIElement(locator, name);
    }

    public static UIElement $uiElement(SelenideElement selenideElement) {
        return new UIElement(selenideElement);
    }

    public static UIElement $uiElement(SelenideElement selenideElement, String name) {
        return new UIElement(selenideElement, name);
    }

    public static UIElement $uiElement(String selenideLocator) {
        return new UIElement(selenideLocator);
    }

    public static UIElement $uiElement(String selenideLocator, String name) {
        return new UIElement(selenideLocator, name);
    }

    public static UIElement _$uiElement(String xpath) {
        return new UIElement(By.xpath(xpath));
    }

    public void setRoot(By locator) {
        this.root = Selenide.$(locator);
    }

    public void setRoot(SelenideElement selenideElement) {
        this.root = Selenide.$(selenideElement);
    }

    @SuppressWarnings("unchecked")
    public <T> T as(String name) {
        this.uiElementName = name;
        return (T) this;
    }

    /**
     * Retrieves the unique identifier of the element.
     *
     * @return the ID if explicitly set; otherwise, the text content of the root element
     */
    @Override
    public String getId() {
        if (!id.equals("")) return id;
        return root.text();
    }

    /**
     * Retrieves the width of the element in pixels.
     *
     * @return the width of the element
     */
    public int getWidth() {
        return root.getWrappedElement().getRect().getWidth();
    }

    /**
     * Retrieves the height of the element in pixels.
     *
     * @return the height of the element
     */
    public int getHeight() {
        return root.getWrappedElement().getRect().getHeight();
    }

    public String getAttribute(String attribute) {
        return root.getAttribute(attribute);
    }

    /**
     * Logs a step for reporting purposes.
     *
     * @param stepText the text to log for the step
     */
    protected void logStep(String stepText) {
        //if (stepsConsoleLoggingEnabled) log.info(stepText);
        if (stepsReportLoggingEnabled) Allure.step(stepText);
    }

    public String getStepText(String stepName) {
        applyName();
        String parentName = "";
        if (parent.isPresent()) parentName = parent.get().wrappedName();
        return StepTextProvider.getStepText(stepName,
                Pair.of("{name}", wrappedName()),
                Pair.of("{element}", uiElementType),
                Pair.of("{parent}", parentName));
    }

    /**
     * Compiles a step text for Allure reports.
     *
     * @param stepName           the name of the step
     * @param additionalPatterns additional placeholder-value pairs for substitution
     * @return the compiled step text
     */
    @SuppressWarnings("unchecked")
    public String getAllureCompiledStep(String stepName, Pair<String,String>... additionalPatterns) {
        applyName();
        String parentName = "";
        if (parent.isPresent()) parentName = parent.get().wrappedName();
        List<Pair<String, String>> patterns = new ArrayList<>();
        patterns.add(Pair.of("{name}", wrappedName()));
        patterns.add(Pair.of("{element}", uiElementType));
        patterns.add(Pair.of("{parent}", parentName));
        patterns.addAll(Arrays.asList(additionalPatterns));
        return StepTextProvider.getStepText(stepName, patterns);
    }

    /**
     * Logs a step to the report with a custom step name.
     *
     * @param stepName the name of the step
     */
    public void logStepToReport(String stepName) {
        applyName();
        String parentName = "";
        if (parent.isPresent()) parentName = parent.get().getUiElementName();
        logStep(StepTextProvider.getStepText(stepName,
                Pair.of("{name}", wrappedName()),
                Pair.of("{element}", uiElementType),
                Pair.of("{parent}", parentName))
        );
    }

    public void logStepToReport(String stepName, Pair<String,String>... additionalPatterns) {
        applyName();
        String parentName = "";
        if (parent.isPresent()) parentName = parent.get().getUiElementName();
        List<Pair<String, String>> patterns = new ArrayList<>();
        patterns.add(Pair.of("{name}", wrappedName()));
        patterns.add(Pair.of("{element}", uiElementType));
        patterns.add(Pair.of("{parent}", parentName));
        patterns.addAll(Arrays.asList(additionalPatterns));
        logStep(StepTextProvider.getStepText(stepName, patterns));
    }

    public String getUiElementName() {
        if (uiElementName.equals("")) {
            applyName(assignNameMethod);
        }
        return uiElementName;
    }

    protected void applyName() {
        if (uiElementName.equals(""))
            applyName(assignNameMethod);
    }

    /**
     * Assigns a name to the element using a specified method.
     *
     * @param method the method to use (e.g., "text", "href", "id")
     */
    public void applyName(String method) {
        switch (method) {
            case "text":
                uiElementName = root.getText();
                break;
            case "href":
                uiElementName = root.getAttribute("href");
                break;
            case "id":
                uiElementName = root.getAttribute("id");
                break;
        }
    }

    /**
     * Wraps the element's name for reporting purposes.
     *
     * @return the wrapped name
     */
    public String wrappedName() {
        return nameWrapStart + uiElementName + nameWrapEnd;
    }

    /**
     * @return native selenide element, which is used as a core of the UiElement
     */
    public SelenideElement get() {
        return root;
    }

    /**
     * Retrieves the text content of the element.
     *
     * @return the text content
     */
    public String text() {
        applyName();
        return get().text();
    }

    /**
     * Checks if the element is displayed.
     *
     * @return true if the element is visible; false otherwise
     */
    public boolean isDisplayed() {
        applyName();
        return root.isDisplayed();
    }

    /**
     * Clicks on the element.
     * = Processed by Aspect =
     *  method is overridable
     */
    public void click() {
        root.click();
    }

    /**
     * Clicks on the element. Supports additional logging options.
     * = Step: Processed by Aspect =
     *
     * @param clickOptions   click options for customization
     */
    @SuppressWarnings("unchecked")
    public void click(ClickOptions clickOptions) {
        logStepToReport("click");
        root.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
        root.hover().click(clickOptions);
    }

    /**
     * Performs a click action on the element with optional logging to Allure reports.
     *
     * @param logAsStepOrNot whether to log the click action as a step in Allure
     *                       reports. If {@code true}, the click action is logged.
     */
    public void click(boolean logAsStepOrNot) {
        if (logAsStepOrNot) {
            click(ClickOptions.usingDefaultMethod(), false);
        } else {
            root.click();
        }
    }

    /**
     * Performs a click action on the element and logs the step with a custom message
     * in Allure reports.
     *
     * @param uniqueStepText the custom message to log in the Allure report for this
     *                       click action.
     */
    public void click(String uniqueStepText) {
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult().setName(uniqueStepText);
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            root.click();
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
            AsyncAllureLogger.stopStepAsync();
        }
    }

    /**
     * Clicks on the element. Supports additional logging options.
     *
     * @param clickOptions   click options for customization
     * @param logAsStepOrNot whether to log the step in the report
     */
    public void click(ClickOptions clickOptions, boolean logAsStepOrNot) {
        try {
            if (logAsStepOrNot) {
                logStepToReport("click");
                root.hover().click(clickOptions);
            } else
                root.hover().click(clickOptions);
        } catch (NullPointerException nEx) {
            throw new RootElementIsNotInitializedException("the root element "+uiElementName+"' is not instanced");
        }
    }

    /**
     * Performs double click on the element.
     * = Step: Processed by Aspect =
     */
    public void doubleClick() {
        root.doubleClick();
    }

    /**
     * Performs a double click action on the element with optional logging to Allure reports
     *
     * @param logAsStepOrNot whether to log the double click action as a step in
     *                       Allure reports. If {@code true}, the action is logged.
     */
    public void doubleClick(boolean logAsStepOrNot) {
        if (logAsStepOrNot) doubleClick();
        else root.doubleClick();
    }

    /**
     * Performs a double click action on the element with optional logging to Allure reports.
     *
     * @param uniqueStepText the custom message to log in the Allure report for the double click action.
     */
    public void doubleClick(String uniqueStepText) {
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult().setName(uniqueStepText);
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            root.doubleClick();
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
            AsyncAllureLogger.stopStepAsync();
        }
    }

    /**
     * Clicks on the element and holds the click for the specified duration in milliseconds,
     * then releases the click.
     * = Step: Processed by Aspect =
     *
     * @param milliseconds click holding period
     */
    public void clickAndHold(long milliseconds) {
        root.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
        Selenide.actions()
                .moveToElement(root.getWrappedElement())
                .clickAndHold()
                .pause(Duration.ofMillis(milliseconds))
                .release()
                .perform();
    }

    /**
     *  Clicks on the element and holds the click for the specified duration in milliseconds with optional logging
     *  to Allure reports
     *
     * @param milliseconds click holding period
     * @param logAsStepOrNot whether to log the click and hold action as a step in
     *                       Allure reports. If {@code true}, the action is logged.
     */
    public void clickAndHold(long milliseconds, boolean logAsStepOrNot) {
        if (logAsStepOrNot) {
            clickAndHold(milliseconds);
        } else {
            root.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
            Selenide.actions()
                    .moveToElement(root.getWrappedElement())
                    .clickAndHold()
                    .pause(Duration.ofMillis(milliseconds))
                    .release()
                    .perform();
        }
    }

    /**
     * the custom message to log in the Allure report for the click and hold action.
     *
     * @param milliseconds click holding period
     * @param uniqueStepText the custom message to log in the Allure report for the click and hold action.
     */
    public void clickAndHold(long milliseconds, String uniqueStepText) {
        StepResult stepResult = new StepResult().setName(uniqueStepText);
        AsyncAllureLogger.startStepAsync(UUID.randomUUID().toString(), stepResult);
        boolean errorStatus = false;
        try {
            root.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
            Selenide.actions()
                    .moveToElement(root.getWrappedElement())
                    .clickAndHold()
                    .pause(Duration.ofMillis(milliseconds))
                    .release()
                    .perform();
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
            AsyncAllureLogger.stopStepAsync();
        }
    }

    /**
     * Performs a context click (right-click) action on the element.
     * = Step: Processed by Aspect =
     */
    public void contextClick() {
        root.contextClick();
    }

    /**
     * Performs a context click (right-click) action on the element with optional logging to Allure reports.
     *
     * @param logAsStepOrNot whether to log the context click action as a step in
     *                       Allure reports. If {@code true}, the action is logged.
     */
    public void contextClick(boolean logAsStepOrNot) {
        if (logAsStepOrNot) {
            contextClick();
        } else {
            root.contextClick();
        }
    }

    /**
     * Performs a context click (right-click) action on the element and logs the
     * step with a custom message in Allure reports.
     *
     * @param uniqueStepText the custom message to log in the Allure report for the context click action.
     */
    public void contextClick(String uniqueStepText) {
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult().setName(uniqueStepText);
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            root.contextClick();
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
            AsyncAllureLogger.stopStepAsync();
        }
    }

    /**
     * Performs a hover action on the element.
     * = Step: Processed by Aspect =
     */
    public void hover() {
        root.hover();
    }

    /**
     * Performs a hover action on the element with optional logging to Allure
     * reports.
     *
     * @param logAsStepOrNot whether to log the hover action as a step in Allure
     *                       reports. If {@code true}, the action is logged.
     */
    public void hover(boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            hover();
        else
            root.hover();
    }

    /**
     * Performs a hover action on the element and logs the step with a custom
     * message in Allure reports.
     *
     * @param uniqueStepText the custom message to log in the Allure report for this
     *                       hover action.
     */
    public void hover(String uniqueStepText) {
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult().setName(uniqueStepText);
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            root.hover();
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
            AsyncAllureLogger.stopStepAsync();
        }
    }

    /**
     * Scrolls the element into view.
     * = Step: Processed by Aspect =
     */
    public void scrollTo() {
        root.scrollTo();
    }

    /**
     * Scrolls the element into view with optional logging to Allure reports.
     *
     * @param logAsStepOrNot whether to log the scroll action as a step in Allure
     *                       reports. If {@code true}, the action is logged.
     */
    public void scrollTo(boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            scrollTo();
        else
            root.scrollTo();
    }

    /**
     * Scrolls the element into view and logs the step with a custom message in
     * Allure reports.
     *
     * @param uniqueStepText the custom message to log in the Allure report for this
     *                       scroll action.
     */
    public void scrollTo(String uniqueStepText) {
        String stepUuid = RandomStringUtils.random(25,"12344567890qwertyuioasdfghjklzxcvbnm");
        StepResult stepResult = new StepResult().setName(uniqueStepText);
        AsyncAllureLogger.startStepAsync(String.valueOf(UUID.randomUUID()), stepResult);
        boolean errorStatus = false;
        try {
            root.scrollTo();
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
            AsyncAllureLogger.stopStepAsync();
        }
    }

    /**
     * Verifies that the element's text matches the specified value.
     * = Step: Processed by Aspect =
     *
     * @param text the expected text
     */
    public void assertText(String text) {
        root.shouldHave(Condition.exactText(text));
    }

    /**
     * Asserts that the element's text contains the specified substring.
     * = Step: Processed by Aspect =
     *
     * @param text the substring that the element's text should contain
     * @throws AssertionError if the element's text does not contain the specified substring
     */
    public void assertHasText(String text) {
        root.shouldHave(Condition.text(text));
    }

    /**
     * Asserts that the element is visible in the viewport.
     * = Step: Processed by Aspect =
     *
     * @throws AssertionError if the element is not visible
     */
    public void assertVisibleInViewport() {
        assertThat((String) executeJavaScript(JsScripts.isVisibleInViewport, root))
                .as(uiElementName).isEqualTo("true");
    }

    /**
     * Asserts that the element is visible.
     * = Step: Processed by Aspect =
     */
    public void assertVisible() {
        root.shouldBe(visible);
    }

    /**
     * Asserts that the element is visible, logging the specified step text.
     * = Step: Processed by Aspect =
     *
     * @param uniqueStepText the step text to log
     */
    public void assertVisible(String uniqueStepText) {
        logStep(uniqueStepText);
        assertThat(root.is(visible)).as(uiElementName).isTrue();
    }

    /**
     * Asserts that the element is visible, with optional logging to a report.
     * = Step: Processed by Aspect =
     *
     * @param logAsStepOrNot whether to log the assertion as a step
     */
    public void assertVisible(boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            assertVisible();
        else
            assertThat(root.is(visible)).as(uiElementName).isTrue();
    }

    /**
     * Asserts that the element is not visible.
     * = Step: Processed by Aspect =
     */
    public void assertNotVisible() {
        root.shouldNotBe(visible);
    }

    /**
     * Asserts that the element is not visible, logging the specified step text.
     * = Step: Processed by Aspect =
     *
     * @param uniqueStepText the step text to log
     */
    public void assertNotVisible(String uniqueStepText) {
        logStep(uniqueStepText);
        int counter = AlluriumConfig.retryAmount();
        boolean isVisible = root.is(visible);
        while (counter > 0 && isVisible) {
            try {
                Thread.sleep(AlluriumConfig.retryIntervalMs());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isVisible = root.is(visible);
            counter--;
        }
        assertThat(root.is(visible)).as(uiElementName).isFalse();
    }

    /**
     * Asserts that the element is not visible, with optional logging to a report.
     * = Step: Processed by Aspect =
     *
     * @param logAsStepOrNot whether to log the assertion as a step
     */
    public void assertNotVisible(boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            assertNotVisible();
        else
            assertThat(root.is(visible)).as(uiElementName).isFalse();
    }

    /**
     * Asserts that the element is visible within the specified duration.
     * = Step: Processed by Aspect =
     *
     * @param durationSeconds the duration in seconds
     */
    @SuppressWarnings("unchecked")
    public void assertVisible(int durationSeconds) {
        logStepToReport("assert_visible_with_duration", Pair.of("{sec}", String.valueOf(durationSeconds)));
        root.shouldBe(visible, Duration.ofSeconds(durationSeconds));
    }

    /**
     * Asserts that the element exists in the DOM.
     * = Step: Processed by Aspect =
     */
    public void assertExists() {
        root.should(exist);
    }

    /**
     * Asserts that the element exists in the DOM, with optional logging to a report.
     *
     * @param logAsStepOrNot whether to log the assertion as a step
     */
    public void assertExists(boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            assertExists();
        else
            root.should(exist);
    }

    /**
     * Asserts that the element does not exist in the DOM.
     * = Step: Processed by Aspect =
     */
    public void assertNotExist() {
        root.shouldNot(exist);
    }

    /**
     * @param logAsStepOrNot show step in report or not
     */
    public void assertNotExist(boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            assertNotExist();
        else
            root.shouldNot(exist);
    }

    /**
     * = Step: Processed by Aspect =
     * @param obj to compare
     */
    public void assertEquals(Object obj) {
        assertThat(obj).as(wrappedName()).isEqualTo(this);
    }

    /**
     * Asserts that the element has the specified CSS class.
     * = Step: Processed by Aspect =
     *
     * @param clazz the CSS class to check
     */
    @SuppressWarnings("unchecked")
    public void assertHasCssClass(String clazz) {
        root.shouldHave(cssClass(clazz));
    }

    /**
     * Asserts that the element does not have the specified CSS class.
     * = Step: Processed by Aspect =
     *
     * @param clazz the CSS class to check
     */
    @SuppressWarnings("unchecked")
    public void assertHasNotCssClass(String clazz) {
        root.shouldNotHave(cssClass(clazz));
    }

    /**
     * Asserts that the element does not have the specified CSS class, with optional logging to a report.
     *
     * @param clazz          the CSS class to check
     * @param logAsStepOrNot whether to log the assertion as a step
     */
    public void assertHasNotCssClass(String clazz, boolean logAsStepOrNot) {
        if (logAsStepOrNot)
            assertHasNotCssClass(clazz);
        else
            root.shouldNotHave(cssClass(clazz));
    }

    /**
     * Asserts that the element has the specified CSS class within the specified duration.
     *
     * @param clazz          the CSS class to check
     * @param duringSeconds  the duration in seconds
     */
    @SuppressWarnings("unchecked")
    public void assertHasCssClass(String clazz, int duringSeconds) {
        root.shouldBe(cssClass(clazz), Duration.ofSeconds(duringSeconds));
    }

    /**
     * Asserts that the element does not have the specified CSS class within the specified duration.
     *
     * @param clazz          the CSS class to check
     * @param duringSeconds  the duration in seconds
     */
    @SuppressWarnings("unchecked")
    public void assertHasNotCssClass(String clazz, int duringSeconds) {
        root.shouldNotBe(cssClass(clazz), Duration.ofSeconds(duringSeconds));
    }

    /**
     * Asserts that the element is empty.
     * = Step: Processed by Aspect =
     */
    public void assertEmpty() {
        assertThat(root.text()).as(wrappedName()).isEmpty();
    }

    /**
     * Performs a soft assertion to check if the element is empty, logging the result.
     */
    public void verifyEmpty() {
        String stepUuid = UUID.randomUUID().toString();
        StepResult stepResult = new StepResult().setName(getStepText("assert_element_empty"));
        Allure.getLifecycle().startStep(UUID.randomUUID().toString(), stepResult);
        if (root.text().isEmpty())
            stepResult.setStatus(Status.PASSED);
        else {
            stepResult.setStatus(Status.FAILED);
            stepResult.setStatusDetails(new StatusDetails().setMessage(
                    "Expected to be empty"
            ));
        }
        Allure.getLifecycle().stopStep(stepUuid);
    }

    /**
     * Asserts that the element is not empty.
     * = Step: Processed by Aspect =
     */
    public void assertIsNotEmpty() {
        assertThat(root.text()).as(wrappedName()).isNotEmpty();
    }

    /**
     * Performs a soft assertion to check if the element is not empty, logging the result.
     */
    public void verifyIsNotEmpty() {
        String stepUuid = UUID.randomUUID().toString();
        StepResult stepResult = new StepResult().setName(getStepText("assert_element_not_empty"));
        Allure.getLifecycle().stopStep();
//        AllureUtils.attachElementScreenshotToStep(root, uiElementName +" view", stepResult);
        if (!root.text().isEmpty())
            stepResult.setStatus(Status.PASSED);
        else {
            stepResult.setStatus(Status.FAILED);
            stepResult.setStatusDetails(new StatusDetails().setMessage(
                    "Expected not to be empty"
            ));
        }
        Allure.getLifecycle().stopStep(stepUuid);
    }

}