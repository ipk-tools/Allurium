package pk.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pk.tools.ElementType;

@Slf4j
@Getter
public class Button extends UIElement {

    protected Button(String selenideLocator) {
        super(selenideLocator);
        setType(ElementType.BUTTON.getType());
    }

    protected Button(String selenideLocator, String name) {
        super(selenideLocator, name);
        setType(ElementType.BUTTON.getType());
    }

    protected Button(By locator) {
        super(locator);
        setType(ElementType.BUTTON.getType());
    }

    protected Button(By locator, String name) {
        super(locator, name);
        setType(ElementType.BUTTON.getType());
    }

    protected Button(SelenideElement selenideElement) {
        super(selenideElement);
        setType(ElementType.BUTTON.getType());
    }

    protected Button(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setType(ElementType.BUTTON.getType());
    }

    public static Button from(By locator) {
        return new Button(locator);
    }

    public static Button from(By locator, String name) {
        return new Button(locator, name);
    }

    public static Button from(SelenideElement selenideElement) {
        return new Button(selenideElement);
    }

    public static Button from(SelenideElement selenideElement, String name) {
        return new Button(selenideElement, name);
    }

    public static Button from(String selenideLocator) {
        return new Button(selenideLocator);
    }

    public static Button from(String selenideLocator, String name) {
        return new Button(selenideLocator, name);
    }

    @Override
    public String getId() {
        super.applyName();
        return name;
    }

    @Override
    public String toString() {
        super.applyName();
        return name;
    }
}
