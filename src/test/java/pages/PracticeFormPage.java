package pages;

import dm.tools.annotations.Locator;
import dm.tools.annotations.Name;
import dm.tools.annotations.PageObject;
import dm.tools.inputs.CheckBox;
import dm.tools.inputs.TextArea;
import dm.tools.inputs.TextField;
import dm.tools.inputs.UploadField;
import dm.tools.primitives.Button;
import dm.tools.inputs.RadioButton;
import lombok.Getter;
import lombok.experimental.Accessors;
import widgets.LeftSideCategoriesMenu;

@PageObject
@Getter
@Accessors(fluent = true)
public class PracticeFormPage {

    @Name("Left Categories menu")
    protected LeftSideCategoriesMenu leftSideCategoriesMenu = new LeftSideCategoriesMenu();

    @Name("Name")
    @Locator(css = "#firstName")
    protected TextField firstNameField;

    @Name("Surname")
    @Locator(css = "#lastName")
    protected TextField lastNameField;

    @Name("Email")
    @Locator(css = "#userEmail")
    protected TextField emailField;

    @Name("Male")
    @Locator(css = "#gender-radio-1")
    protected RadioButton maleRadioButton;

    @Name("Female")
    @Locator(css = "#gender-radio-2")
    protected RadioButton femaleRadioButton;

    @Name("Music")
    @Locator(css = "#hobbies-checkbox-3")
    protected CheckBox hobbiesMusic;

    @Name("Other")
    @Locator(css = "#gender-radio-3")
    protected RadioButton otherButton;

    @Name("Mobile")
    @Locator(css = "#userNumber")
    protected TextField mobileField;

    @Name("Picture")
    @Locator(css = "#uploadPicture")
    protected UploadField pictureUploadField;

    @Name("Current Address")
    @Locator(css = "#currentAddress")
    protected TextArea currentAddress;

    @Name("Sumbit")
    @Locator(css = "#submit")
    protected Button submitButton;
}
