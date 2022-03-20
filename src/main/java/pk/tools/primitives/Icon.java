package pk.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pk.tools.ElementType;

public class Icon extends UIElement {

    protected Icon(String selenideLocator) {
        super(selenideLocator);
        setType(ElementType.ICON.getType());
    }

    protected Icon(String selenideLocator, String name) {
        super(selenideLocator, name);
        setType(ElementType.ICON.getType());
    }

    protected Icon(By locator) {
        super(locator);
        setType(ElementType.ICON.getType());
    }

    protected Icon(By locator, String name) {
        super(locator, name);
        setType(ElementType.ICON.getType());
    }

    protected Icon(SelenideElement selenideElement) {
        super(selenideElement);
        setType(ElementType.ICON.getType());
    }

    protected Icon(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setType(ElementType.ICON.getType());
    }

    public static Icon from(By locator) {
        return new Icon(locator);
    }

    public static Icon from(By locator, String name) {
        return new Icon(locator, name);
    }

    public static Icon from(SelenideElement selenideElement) {
        return new Icon(selenideElement);
    }

    public static Icon from(SelenideElement selenideElement, String name) {
        return new Icon(selenideElement, name);
    }

    public static Icon from(String selenideLocator) {
        return new Icon(selenideLocator);
    }

    public static Icon from(String selenideLocator, String name) {
        return new Icon(selenideLocator, name);
    }
}
