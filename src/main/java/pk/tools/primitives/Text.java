package pk.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pk.tools.ElementType;

@Slf4j
@Getter
public class Text extends UIElement {

    protected Text(String selenideLocator) {
        super(selenideLocator);
        setType(ElementType.TEXT.getType());
    }

    protected Text(String selenideLocator, String name) {
        super(selenideLocator, name);
    }

    protected Text(By locator) {
        super(locator);
        setType(ElementType.TEXT.getType());
    }

    protected Text(By locator, String name) {
        super(locator, name);
        setType(ElementType.TEXT.getType());
    }

    protected Text(SelenideElement selenideElement) {
        super(selenideElement);
        setType(ElementType.TEXT.getType());
    }

    protected Text(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setType(ElementType.TEXT.getType());
    }

    public static Text from(By locator) {
        return new Text(locator);
    }

    public static Text from(By locator, String name) {
        return new Text(locator, name);
    }

    public static Text from(SelenideElement selenideElement) {
        return new Text(selenideElement);
    }

    public static Text from(SelenideElement selenideElement, String name) {
        return new Text(selenideElement, name);
    }

    public static Text from(String selenideLocator) {
        return new Text(selenideLocator);
    }

    public static Text from(String selenideLocator, String name) {
        return new Text(selenideLocator, name);
    }

    @Override
    public String toString() {
        super.applyName();
        return name;
    }

    @Override
    public String getId() {
        super.applyName();
        return name;
    }
}
