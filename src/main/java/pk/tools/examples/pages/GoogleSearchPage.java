package pk.tools.examples.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import pk.tools.annotations.Name;
import pk.tools.annotations.PageObject;
import pk.tools.inputs.InputField;
import pk.tools.examples.widgets.SubmitCookiesPopup;

@PageObject(description = "Google search")
@Getter
public class GoogleSearchPage extends BasePage {

    @Name("submit cookie popup")
    protected SubmitCookiesPopup submitCookiesPopup = new SubmitCookiesPopup();

    @Name("search")
    protected InputField searchField = InputField.from(By.xpath("//input[@title='Search']"));
}
