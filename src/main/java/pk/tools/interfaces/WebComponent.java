package pk.tools.interfaces;

import com.codeborne.selenide.SelenideElement;

public interface WebComponent extends WebElementMeta {

    SelenideElement getRoot();
}
