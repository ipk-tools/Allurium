package allurium.inputs;

import allurium.exceptions.NoSuchOptionException;
import allurium.interfaces.Selectable;
import allurium.options.SelectOptions;
import allurium.primitives.UIElement;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import allurium.StepConverter;
import allurium.StepTextProvider;
import allurium.UiSteps;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Encapsulates interactions with a select menu (`<select>` element) in a web application.
 * <p>
 * This class provides methods for selecting options, asserting the presence of options,
 * and performing various selection-related operations. It integrates with Selenide for
 * fluent, chainable interactions and supports Allure reporting for step-level visibility.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Select options by visible text, index, or navigation (e.g., keyboard arrows).</li>
 *     <li>Supports additional selection behaviors using {@link SelectOptions}.</li>
 *     <li>Assert the presence or absence of specific options within the selectOptionsList.</li>
 *     <li>Support for selecting options while excluding specific values.</li>
 *     <li>Convenient factory methods for instantiating the class.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates and simplifies interactions with `<select>` elements in web pages.</li>
 *     <li>Enhances readability and maintainability of tests through a unified interface.</li>
 *     <li>Standardizes select menu interactions across different test cases.</li>
 *     <li>Integrates seamlessly with Allure for comprehensive test reporting.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * Select selectOptionsList = $select("#dropdown-list-class");
 * selectOptionsList.select("Option 1");
 * selectOptionsList.assertCurrentValue("Option 1");
 * selectOptionsList.selectAnyBesides("Option 2");
 * selectOptionsList.assertHasItem("Option 3");
 * }</pre>
 *
 * <h3>Class Integration:</h3>
 * <ul>
 *     <li>Inherits from {@link UIElement} for shared behavior across web elements.</li>
 *     <li>Implements {@link Selectable} for standard select operations.</li>
 * </ul>
 */

public class Select extends UIElement implements Selectable {

    /**
     * Collection of options within the options list.
     */
    @Getter
    protected ElementsCollection options;

    /**
     * Default constructor. Initializes the element type as "select".
     */
    protected Select() {
        setUiElementType(ElementType.SELECT.getType());
    }

    /**
     * Constructor that initializes the select using a Selenium {@link By} locator.
     *
     * @param rootLocator the Selenium locator for the dropdown
     */
    public Select(By rootLocator) {
        super(rootLocator);
        setUiElementType(ElementType.SELECT.getType());
    }

    /**
     * Constructor that initializes the select using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public Select(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.SELECT.getType());
    }

    /**
     * Constructor that initializes the select using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the dropdown
     */
    public Select(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.SELECT.getType());
    }

    // Static factory methods

    public static Select $select(By locator) {
        return new Select(locator);
    }

    public static Select $select(String selenideLocator) {
        return new Select(selenideLocator);
    }

    public static Select $select(SelenideElement selenideElement) {
        return new Select(selenideElement);
    }

    public static Select _$select(String xpath) {
        return new Select(By.xpath(xpath));
    }

    /**
     * Refreshes the list of options within the options list.
     */
    protected void refreshOptions() {
        options = getRoot().$$("option");
    }

    /**
     * Selects an option by its visible text.
     * <p><b>Step: Processed by Aspect</b></p>
     *
     * @param option the text of the option to select
     */
    @Override
    public void select(String option) {
        root.selectOption(option);
    }

    /**
     * Selects an option by its visible text with additional selection behavior.
     *
     * @param option        the text of the option to select
     * @param selectOptions additional selection behavior
     */
    public void select(String option, SelectOptions selectOptions) {
        switch (selectOptions) {
            case PRESS_ESC_TO_CLOSE:
                select(option);
                UiSteps.pressEsc();
                break;
        }
    }

    /**
     * Selects an option by its index.
     * <p><b>Step: Processed by Aspect</b></p>
     *
     * @param index the index of the option to select
     */
    public void select(int index) {
        root.selectOption(index);
    }

    /**
     * Selects an option by its index with additional selection behavior.
     *
     * @param index         the index of the option to select
     * @param selectOptions additional selection behavior
     */
    public void select(int index, SelectOptions selectOptions) {
        select(index);
        switch (selectOptions) {
            case PRESS_ESC_TO_CLOSE:
                UiSteps.pressEsc();
                break;
        }
    }

    /**
     * Selects an option by navigating using keyboard arrows.
     * <p><b>Step: Processed by Aspect</b></p>
     *
     * @param text the text of the option to select
     */
    public void selectByArrowsLeftAndRight(String text) {
        root.selectOption(0);
        ElementsCollection options = root.$$("option");
        boolean found = false;
        for (int i = 0; i < options.size(); i++) {
            if (!root.getSelectedText().equals(text))
                root.sendKeys(Keys.ARROW_RIGHT);
            else {
                found = true;
                break;
            }
        }
        if (!found) throw new NoSuchOptionException(this, text);
    }

    /**
     * Selects the first available option.
     * <p><b>Step: Processed by Aspect</b></p>
     */
    @Override
    public void selectFirst() {
        options.get(0).click();
    }

    /**
     * Selects the last available option.
     * <p><b>Step: Processed by Aspect</b></p>
     */
    @Override
    public void selectLast() {
        options.get(options.size()).click();
    }

    /**
     * Selects a random option from the selectOptionsList.
     * <p><b>Step: Processed by Aspect</b></p>
     */
    @Override
    public void selectAny() {
        options.get(new Random().nextInt(options.size())).click();
    }

    /**
     * Selects any option from the select or dropdown list except the one specified by its value.
     * <p>
     * This method filters the dropdown options to exclude the specified value
     * and then selects the first available option that does not match.
     * </p>
     * <p><b>Step: Processed by Aspect</b></p>
     *
     * @param value the text of the option to exclude
     * @throws IllegalArgumentException if no option besides the specified value exists
     */
    public void selectAnyBesides(String value) {
        options.filterBy(Condition.not(Condition.text(value))).first().click();
    }

    /**
     * Asserts that the currently selected value in the select or dropdown list matches the expected value.
     * <p>
     * This method verifies the `value` attribute of the select element and ensures it matches
     * the provided input.
     * </p>
     * <p><b>Step: Processed by Aspect</b></p>
     *
     * <h3>Example Usage:</h3>
     * <pre>{@code
     * Select selectOptionsList = new Select("select");
     * selectOptionsList.assertSelectedValue("Expected Option");
     * }</pre>
     *
     * <h3>Behavior:</h3>
     * <ul>
     *     <li>Throws an {@link AssertionError} if the selected value does not match the provided value.</li>
     * </ul>
     *
     * @param value the expected value to match
     */
    public void assertCurrentValue(String value) {
//        Assertions.assertThat(root.getValue()).as(getUiElementName()+"'s value").isEqualTo(value);
        root.shouldHave(Condition.value(value));
    }

    /**
     * Asserts that the currently selected value in the select or dropdown list does not match the given value.
     * <p>
     * This method verifies the `value` attribute of the select element and ensures it is not equal
     * to the provided input.
     * </p>
     * <p><b>Step: Processed by Aspect</b></p>
     *
     * <h3>Example Usage:</h3>
     * <pre>{@code
     * Select selectOptionsList = new Select("select");
     * selectOptionsList.assertSelectedValueIsNot("Excluded Option");
     * }</pre>
     *
     * <h3>Behavior:</h3>
     * <ul>
     *     <li>Throws an {@link AssertionError} if the selected value matches the provided value.</li>
     * </ul>
     *
     * @param value the value to ensure is not selected
     */
    public void assertCurrentValueIsNot(String value) {
        root.shouldNotHave(Condition.value(value));
    }

    /**
     * Asserts that the select or dropdown list contains a specific option by its visible text.
     * <p>
     * This method checks the list of available options within the dropdown and verifies
     * that the specified item exists.
     * </p>
     * <p><b>Step: Processed by Aspect</b></p>
     *
     * <h3>Example Usage:</h3>
     * <pre>{@code
     * Select selectOptionsList = new Select("select");
     * selectOptionsList.assertHasItem("Option 1");
     * }</pre>
     *
     * <h3>Behavior:</h3>
     * <ul>
     *     <li>Throws an {@link AssertionError} if the specified item is not found in the selectOptionsList.</li>
     * </ul>
     *
     * @param item the visible text of the option to verify
     */
    public void assertHasItem(String item) {
        this.options = root.$$("option");
        SelenideElement[] options = new SelenideElement[] {};
        options = this.options.toArray(options);
        Assertions.assertThat(Arrays.stream(options).map(SelenideElement::text).collect(Collectors.toList()))
                .as(uiElementName)
                .contains(item);
    }


    @SuppressWarnings("unchecked")
    private void assertHasItems(String... item) throws Throwable {
        this.options = root.$$("option");
        StepConverter.wrapIntoStep(() -> {
            SelenideElement[] options = new SelenideElement[] {};
            options = this.options.toArray(options);
            Assertions.assertThat(Arrays.stream(options).map(SelenideElement::text).collect(Collectors.toList()))
                    .as(uiElementName)
                    .contains(item);
            return null;
        }, StepTextProvider.getStepText("select_assert_has_option",
                Pair.of("{element}", uiElementType),
                Pair.of("{name}", wrappedName()),
                //todo: make string from array values
                Pair.of("{option}", Arrays.stream(item).toArray().toString())
                //Pair.of("{parent}", parent.wrappedName())
        ));
    }

    /**
     * method is overridable
     * <p><b>Step: Processed by Aspect</b></p>
     *
     * @param items
     */
    public void assertHasItems(List<String> items) throws Throwable {
        assertHasItems(items.toArray(new String[0]));
    }

}
