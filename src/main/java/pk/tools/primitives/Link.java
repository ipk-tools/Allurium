package pk.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pk.tools.ElementType;

@Slf4j
public class Link extends UIElement {

    protected Link(String selenideLocator) {
        super(selenideLocator);
        setType(ElementType.LINK.getType());
    }

    public Link(SelenideElement selenideElement) {
        super(selenideElement);
        setType(ElementType.LINK.getType());
    }

    protected Link(By locator) {
        super(locator);
        setType(ElementType.LINK.getType());
    }

    protected Link(By locator, String name) {
        super(locator, name);
        setType(ElementType.LINK.getType());
    }

    protected Link(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setType(ElementType.LINK.getType());
    }

    protected Link(String selenideLocator, String name) {
        super(selenideLocator, name);
        setType(ElementType.LINK.getType());
    }

    public static Link from(By locator) {
        return new Link(locator);
    }

    public static Link from(By locator, String name) {
        return new Link(locator, name);
    }

    public static Link from(SelenideElement selenideElement) {
        return new Link(selenideElement);
    }

    public static Link from(SelenideElement selenideElement, String name) {
        return new Link(selenideElement, name);
    }

    public static Link from(String selenideLocator) {
        return new Link(selenideLocator);
    }

    public static Link from(String selenideLocator, String name) {
        return new Link(selenideLocator, name);
    }

    @Override
    public String getId() {
        super.applyName();
        return name;
    }

    public String getText() {
        super.applyName();
        Allure.step("Получаем текст ссылки [" + name + "]");
        return root.getText();
    }
}
