package pk.tools;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pk.tools.primitives.UIElement;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public abstract class AbstractWidget extends UIElement {

    public AbstractWidget() {
        setType("widget");
    }

    public AbstractWidget(SelenideElement rootElement) {
        super(rootElement);
        setType("widget");
    }

    public AbstractWidget(String selenideLocator) {
        super(selenideLocator);
        setType("widget");
    }

    public AbstractWidget(By locator) {
        super(locator);
        setType("widget");
    }

    @Override
    public int getWidth() {
        if (root != null) {
            return super.getWidth();
        } else {
            log.warn("Widget " + name + " doesn't have root element to get width! Zero has returned!");
            return 0;
        }
    }

    @Override
    public int getHeight() {
        if (root != null) {
            return super.getHeight();
        } else {
            log.warn("Widget " + name + " doesn't have root element to get height! Zero has returned!");
            return 0;
        }
    }

    public SelenideElement getRoot() {
        return root != null ? root : $("body");
    }

    public boolean isLoaded() {
        return root.isDisplayed();
    }
}
