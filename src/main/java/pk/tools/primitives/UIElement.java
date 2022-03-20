package pk.tools.primitives;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.epam.jdi.tools.pairs.Pair;
import io.qameta.allure.Allure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import pk.tools.AbstractWidget;
import pk.tools.StepText;
import pk.tools.UiSteps;
import pk.tools.interfaces.ListComponent;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;

@NoArgsConstructor
@Slf4j
public class UIElement implements ListComponent {

    @Getter
    protected SelenideElement root;

    @Setter
    protected String id = "";

    @Getter
    @Setter
    protected String type = "";

    @Getter
    @Setter
    protected String name = "";

    @Setter
    protected String assignNameMethod = "text";

    @Getter
    @Setter
    protected String description = "";

    @Getter
    @Setter
    protected AbstractWidget parent = null;

    @Getter
    protected String pageHost = "";

    protected boolean stepsConsoleLoggingEnabled = true;
    protected boolean stepsReportLoggingEnabled = true;

    private final String nameWrapStart = "[";
    private final String nameWrapEnd = "]";

    protected UIElement(String selenideLocator) {
        root = Selenide.$(selenideLocator);
    }

    protected UIElement(String selenideLocator, String name) {
        root = Selenide.$(selenideLocator);
        this.name = name;
    }

    protected UIElement(By locator) {
        root = Selenide.$(locator);
    }

    protected UIElement(By locator, String name) {
        root = Selenide.$(locator);
        this.name = name;
    }

    public UIElement(SelenideElement selenideElement) {
        root = selenideElement;
    }

    protected UIElement(SelenideElement selenideElement, String name) {
        root = selenideElement;
        this.name = name;
    }

    public static UIElement from(By locator) {
        return new UIElement(locator);
    }

    public static UIElement from(By locator, String name) {
        return new UIElement(locator, name);
    }

    public static UIElement from(SelenideElement selenideElement) {
        return new UIElement(selenideElement);
    }

    public static UIElement from(SelenideElement selenideElement, String name) {
        return new UIElement(selenideElement, name);
    }

    public static UIElement from(String selenideLocator) {
        return new UIElement(selenideLocator);
    }

    public static UIElement from(String selenideLocator, String name) {
        return new UIElement(selenideLocator, name);
    }

    @Override
    public String getId() {
        if (!id.equals("")) return id;
        return root.text();
    }

    public int getWidth() {
        return root.getWrappedElement().getRect().getWidth();
    }

    public int getHeight() {
        return root.getWrappedElement().getRect().getHeight();
    }

    protected void logStep(String stepText) {
        if (stepsConsoleLoggingEnabled) log.info(stepText);
        if (stepsReportLoggingEnabled) Allure.step(stepText);
    }

    @SuppressWarnings("unchecked")
    protected void logStep(StepText stepText) {
        applyName();
        String parentName = "";
        if (parent != null) parentName = parent.getName();
        logStep(stepText.getStepText(
                Pair.$("{name}", wrappedName()),
                Pair.$("{element}", type),
                Pair.$("{parent}", parentName)));
    }

    protected void logStep(StepText stepText, Pair<String,String>... additionalPatterns) {
        applyName();
        String parentName = "";
        if (parent != null) parentName = parent.getName();
        List<Pair<String, String>> patterns = new ArrayList<>();
        patterns.add(Pair.$("{name}", wrappedName()));
        patterns.add(Pair.$("{element}", type));
        patterns.add(Pair.$("{parent}", parentName));
        patterns.addAll(Arrays.asList(additionalPatterns));
        logStep(stepText.getStepText(patterns));
    }

    protected void applyName() {
        if (name.equals(""))
            applyName(assignNameMethod);
    }

    public void applyName(String method) {
        switch (method) {
            case "text":
                name = root.getText();
                break;
            case "href":
                name = root.getAttribute("href");
                break;
            case "id":
                name = root.getAttribute("id");
                break;
        }
    }

    protected String wrappedName() {
        return nameWrapStart + name + nameWrapEnd;
    }

    /**
     * @return native selenide element
     */
    public SelenideElement get() {
        return root;
    }

    public String text() {
        applyName();
        return get().text();
    }

    public boolean isDisplayed() {
        applyName();
        return root.isDisplayed();
    }

    public String getText() {
        applyName();
        //logStep(StepText.Get_text.getStepText(type, name)); //probably not need to be step
        return root.text().trim();
    }

    public UIElement click() {
        root.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
        click(ClickOptions.usingDefaultMethod());
        return this;
    }

    /**
     * Полезно при работе с автоскроллом. Иначе не всегда успевает проскроллиться и кликает мимо элемента
     */
    public UIElement clickWithDelay(int seconds) {
        UiSteps.waiting(seconds, "перед кликом");
        root.scrollIntoView("{behavior: \"auto\", block: \"start\", inline: \"start\"}");
        click(ClickOptions.usingDefaultMethod());
        return this;
    }

    @SuppressWarnings("unchecked")
    public UIElement click(ClickOptions clickOptions) {
        logStep(StepText.Click);
        root.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
        root.hover().click(clickOptions);
        return this;
    }

    public UIElement click(String stepText) {
        logStep(stepText);
        root.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
        root.hover().click();
        return this;
    }

    @SuppressWarnings("unchecked")
    public UIElement assertText(String text) {
        logStep(StepText.Assert_text, Pair.$("{text}", text));
        root.shouldHave(Condition.text(text));
        return this;
    }

    @SuppressWarnings("unchecked")
    public void assertValue(String text) {
        logStep(StepText.Assert_value, Pair.$("{text}", text));
        root.shouldHave(Condition.value(text));
    }

    @SuppressWarnings("unchecked")
    public UIElement scrollIntoView() {
        logStep(StepText.Scroll);
        root.scrollIntoView("{behavior: \"auto\", block: \"center\", inline: \"center\"}");
        return this;
    }

    /**
     * Проверка, что элемент отображается на текущем экране, т.е. в видимой части страницы. Полезно при проверке автоскролла.
     */
    public UIElement assertVisibleInViewport() {
        logStep(StepText.Assert_visibleInViewPort);
//        Assert.assertEquals(executeJavaScript(GlobalVariable.isVisibleInViewport, root), Boolean.TRUE);
        return this;
    }

    public UIElement assertVisible() {
        logStep(StepText.Assert_visible);
        root.shouldBe(visible);
        return this;
    }

    public UIElement assertNotVisible() {
        logStep(StepText.Assert_not_visible);
        root.shouldNotBe(visible);
        return this;
    }

    @SuppressWarnings("unchecked")
    public UIElement assertVisibleWithDuration(int seconds) {
        logStep(StepText.Assert_visible_with_duration, Pair.$("{sec}", String.valueOf(seconds)));
        root.shouldBe(visible, Duration.ofSeconds(seconds));
        return this;
    }

    public UIElement exist(boolean flag) {
        if (flag) {
            logStep(StepText.Assert_exist);
            root.should(exist);
        } else {
            logStep(StepText.Assert_not_exist);
            root.shouldNot(exist);
        }
        return this;
    }

    public UIElement hover() {
        logStep(StepText.Hover);
        root.hover();
        return this;
    }

    @SuppressWarnings("unchecked")
    public UIElement shouldHaveCssClass(String clazz) {
        logStep(StepText.Assert_has_css_class, Pair.$("{clazz}", clazz));
        root.shouldBe(Condition.cssClass(clazz), Duration.ofSeconds(30));
        return this;
    }

    @SuppressWarnings("unchecked")
    public UIElement shouldNotHaveCssClass(String clazz) {
        logStep(StepText.Assert_has_not_css_class, Pair.$("{clazz}", clazz));
        root.shouldNotBe(Condition.cssClass(clazz), Duration.ofSeconds(30));
        return this;
    }

}