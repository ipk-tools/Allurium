package pk.tools.primitives;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pk.tools.ElementType;

import java.util.function.Supplier;

@Slf4j
@Getter
public class Image extends UIElement {

    @Setter
    private String text = "";

    protected Image(String selenideLocator) {
        super(selenideLocator);
        setType(ElementType.IMAGE.getType());
    }

    protected Image(String selenideLocator, String name) {
        super(selenideLocator, name);
        setType(ElementType.IMAGE.getType());
    }

    protected Image(By locator) {
        super(locator);
        setType(ElementType.IMAGE.getType());
    }

    protected Image(By locator, String name) {
        super(locator, name);
        setType(ElementType.IMAGE.getType());
    }

    protected Image(SelenideElement selenideElement) {
        super(selenideElement);
        setType(ElementType.IMAGE.getType());
    }

    protected Image(SelenideElement selenideElement, String name) {
        super(selenideElement, name);
        setType(ElementType.IMAGE.getType());
    }

    public static Image from(By locator) {
        return new Image(locator);
    }

    public static Image from(By locator, String name) {
        return new Image(locator, name);
    }

    public static Image from(SelenideElement selenideElement) {
        return new Image(selenideElement);
    }

    public static Image from(SelenideElement selenideElement, String name) {
        return new Image(selenideElement, name);
    }

    public static Image from(String selenideLocator) {
        return new Image(selenideLocator);
    }

    public static Image from(String selenideLocator, String name) {
        return new Image(selenideLocator, name);
    }

    // for images 'text' is same role as ID for ListWC components
    // probably can be canceled and remain only getId()
    @Override
    public String getText() {
        return text;
    }

    public void setId(Supplier<String> howToMakeId) {
        text = howToMakeId.get();
        setId(text);
    }

    @Override
    public String getId() {
        return text;
    }
}
