package allurium.inputs;

import allurium.interfaces.Dropdown;
import allurium.options.SelectOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import allurium.UiSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

/**
 * Represents a custom dropdown menu that mimics the behavior of a native `<select>` element.
 * <p>
 * This class extends the {@link Select} class to provide additional functionality specific to
 * non-native dropdown menus (e.g., `<ul><li></li></ul>` structures) and implements the {@link Dropdown} interface.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Select options by text or index, with extended support for custom behaviors.</li>
 *     <li>Manually navigate dropdowns using keyboard arrow keys to select an option.</li>
 *     <li>Handle dropdown expansion explicitly via {@link #extend()}.</li>
 *     <li>Supports Allure reporting for dropdown interactions.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Encapsulates dropdown-specific behavior for custom dropdown elements.</li>
 *     <li>Standardizes interactions with dropdown menus in tests.</li>
 * </ul>
 *
 * <h3>Example Usage:</h3>
 * <pre>{@code
 * <html>
 *     <ul>
 *         <li>option1</li>
 *         <li>option2</li>
 *         <li>option3</li>
 *     </ul>
 * </html>
 *
 * DropdownSelect dropdown = new DropdownSelect("ul");
 * dropdown.select("option2");
 * }</pre>
 *
 * The following methods are automatically logged as Allure steps:
 * <ul>
 *     <li>{@link #extend()}</li>
 *     <li>{@link #select(String)}</li>
 *     <li>{@link #select(int)}</li>
 *     <li>{@link #select(String, SelectOptions)}</li>
 *     <li>{@link #selectFirst()}</li>
 *     <li>{@link #selectLast()}</li>
 *     <li>{@link #selectAnyBesides(String)}</li>
 *     <li>{@link #selectAny()}</li>
 *     <li>Assertions for dropdown state and options.</li>
 * </ul>
 */

public class DropdownSelect extends Select implements Dropdown {

    /**
     * Default constructor. Initializes the dropdown type as "dropdown select".
     */
    public DropdownSelect() {
        setUiElementType(ElementType.DROPDOWN_SELECT.getType());
    }

    /**
     * Constructor that initializes the dropdown using a Selenium {@link By} locator.
     *
     * @param rootLocator the Selenium locator for the dropdown element
     */
    public DropdownSelect(By rootLocator) {
        super(rootLocator);
        setUiElementType(ElementType.DROPDOWN_SELECT.getType());
    }

    /**
     * Constructor that initializes the dropdown using a Selenide locator string.
     *
     * @param selenideLocator the Selenide locator as a string
     */
    public DropdownSelect(String selenideLocator) {
        super(selenideLocator);
        setUiElementType(ElementType.DROPDOWN_SELECT.getType());
    }

    /**
     * Constructor that initializes the dropdown using a Selenide element.
     *
     * @param selenideElement the Selenide element representing the dropdown
     */
    public DropdownSelect(SelenideElement selenideElement) {
        super(selenideElement);
        setUiElementType(ElementType.DROPDOWN_SELECT.getType());
    }

    public static DropdownSelect $dropdownSelect(By rootLocator) {
        return new DropdownSelect(rootLocator);
    }

    public static DropdownSelect $dropdownSelect(String selenideLocator) {
        return new DropdownSelect(selenideLocator);
    }

    public static DropdownSelect $dropdownSelect(SelenideElement selenideElement) {
        return new DropdownSelect(selenideElement);
    }

    public static DropdownSelect _$dropdownSelect(String xpath) {
        return new DropdownSelect(By.xpath(xpath));
    }

    /**
     * Expands the dropdown menu by clicking its root element.
     */
    @Override
    public void extend() {
        getRoot().click();
    }

//    /**
//     * Selects an option by its text value.
//     *
//     * @param value the text of the option to select
//     */
//    @Override
//    public void select(String value) {
//        super.select(value);
//    }
//
//    /**
//     * Selects an option by its index.
//     * <p>
//     * This method adjusts the index to account for 1-based indexing commonly
//     * associated with dropdown menus.
//     * </p>
//     *
//     * @param index the index of the option to select (1-based)
//     */
//    @Override
//    public void select(int index) {
//        super.select(index-1);
//    }

    /**
     * Selects an option by its visible text with specific selection behavior.
     *
     * @param option        the text of the option to select
     * @param selectOptions the additional selection behavior
     */
    @Override
    public void select(String option, SelectOptions selectOptions) {
        switch (selectOptions) {
            case PRESS_ESC_TO_CLOSE:
                select(option);
                UiSteps.pressEsc();
                break;
            case HONESTLY:
                selectManually(option);
                break;
        }
    }

    /**
     * Manually navigates the dropdown options using keyboard arrow keys until the target option is selected.
     * <p>
     * This method is useful for dropdowns where direct interaction is not possible, or for simulating real user behavior.
     * </p>
     *
     * @param value the text of the target option
     */
    private void selectManually(String value) {
        extend();
        refreshOptions();
        SelenideElement current = getOptions().filterBy(Condition.text(getRoot().getSelectedText())).first();
        SelenideElement target = getOptions().filterBy(Condition.text(value)).first();
        int currentIndex = getOptions().indexOf(current);
        int targetIndex = getOptions().indexOf(target);
        if (targetIndex > currentIndex) {
            int clicksTillTarget = targetIndex - currentIndex;
            while (clicksTillTarget > 0) {
                getRoot().sendKeys(Keys.ARROW_DOWN);
                clicksTillTarget--;
            }
        } else if (currentIndex > targetIndex) {
            int clicksTillTarget = currentIndex - targetIndex;
            while (clicksTillTarget > 0) {
                getRoot().sendKeys(Keys.ARROW_UP);
                clicksTillTarget--;
            }
        }
        getRoot().sendKeys(Keys.ENTER);
    }

}
