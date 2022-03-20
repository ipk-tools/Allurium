package pk.tools.examples.widgets;

import pk.tools.AbstractWidget;
import pk.tools.annotations.Name;
import pk.tools.annotations.Widget;
import pk.tools.primitives.Button;

import static com.codeborne.selenide.Selectors.withText;

@Widget(onPage = "Search Page")
public class SubmitCookiesPopup extends AbstractWidget {

    @Name("Submit")
    public Button submitButton = Button.from(withText("I agree"));

    @Name("Sign in")
    public Button loginButton = Button.from(withText("Sign in"));
}
