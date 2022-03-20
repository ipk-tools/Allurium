package pk.tools.sliders;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pk.tools.AbstractWidget;
import pk.tools.ListWC;
import pk.tools.primitives.UIElement;

public abstract class AbstractSlider extends AbstractWidget {

    protected UIElement activeSlide;
    protected UIElement previousSlide;

    public AbstractSlider(By slidesLocator) {
        super();
    }

    public abstract ListWC<UIElement> getPreviews();

    public abstract ListWC<UIElement> getSlides();

    @Step("Выбираем слайд с индексом [{index}]")
    public void select(int index) {
        getPreviews().get(index).click();
    }

    @Step("Выбираем последний слайд")
    public void selectLast() {
        getPreviews().get(getPreviews().size() - 1).click();
    }

    public void select(String id) {
        getPreviews().get(id).click();
    }

    public abstract UIElement getActiveSlide();

    public abstract UIElement getPreviousSlide();

    public abstract void scrollRight();

    public abstract void scrollLeft();
}
