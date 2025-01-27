package allurium.carousels;

import allurium.annotations.Name;
import allurium.primitives.Button;
import com.codeborne.selenide.SelenideElement;
import allurium.ElementType;
import lombok.Getter;
import org.openqa.selenium.By;

import static allurium.primitives.Button.$button;

/**
 * Represents a concrete implementation of the {@link AbstractCarousel} class for image sliders or carousels.
 * <p>
 * The `Carousel` class encapsulates the behavior and structure of a carousel component with forward and backward
 * navigation buttons. It extends the functionality provided by the base {@link AbstractCarousel}.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates the forward and backward navigation buttons as {@link Button} components.</li>
 *     <li>Implements the {@link #scrollForward()} and {@link #scrollBackward()} methods for navigation.</li>
 *     <li>Supports initialization with various locator and element types for flexibility.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Provides a standardized implementation of a carousel component with dynamic navigation.</li>
 *     <li>Enhances testability and reusability of carousel components in UI automation.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * Carousel imageCarousel = new Carousel(
 *     By.cssSelector(".carousel"),
 *     By.cssSelector(".next-button"),
 *     By.cssSelector(".back-button")
 * );
 *
 * imageCarousel.scrollForward();  // Navigate to the next set of images.
 * imageCarousel.scrollBackward(); // Navigate to the previous set of images.
 * }</pre>
 *
 * <h3>Integration:</h3>
 * <ul>
 *     <li>Extends {@link AbstractCarousel} to inherit common carousel behaviors.</li>
 *     <li>Encapsulates navigation buttons as {@link Button} elements, annotated with {@link Name} for identification.</li>
 *     <li>Supports interaction through Selenium locators or {@link SelenideElement} instances.</li>
 * </ul>
 *
 * @see AbstractCarousel
 * @see Button
 *
 * @author Iaroslav Pilipenko
 */
@Getter
public class Carousel extends AbstractCarousel {

    /**
     * The button used to scroll the carousel forward.
     */
    @Name("Forward")
    protected Button buttonNext;

    /**
     * The button used to scroll the carousel backward.
     */
    @Name("Back")
    protected Button buttonBack;

    /**
     * Constructor to initialize the carousel with Selenium {@link By} locators for navigation buttons.
     *
     * @param buttonNextLocator   the locator for the "Next" button
     * @param buttonBackLocator   the locator for the "Back" button
     */
    public Carousel(By buttonNextLocator, By buttonBackLocator) {
        super();
        buttonNext = Button.$button(buttonNextLocator, "Next");
        buttonBack = Button.$button(buttonBackLocator, "Back");
    }

    /**
     * Constructor to initialize the carousel with Selenium {@link By} locators for the root and navigation buttons.
     *
     * @param rootLocator         the locator for the carousel's root element
     * @param buttonNextLocator   the locator for the "Next" button
     * @param buttonBackLocator   the locator for the "Back" button
     */
    public Carousel(By rootLocator, By buttonNextLocator, By buttonBackLocator) {
        super(rootLocator);
        buttonNext = Button.$button(buttonNextLocator, "Next");
        buttonBack = Button.$button(buttonBackLocator, "Back");
        setUiElementType(ElementType.IMAGE_SLIDER.getType());
    }

    /**
     * Constructor to initialize the carousel with Selenide locator strings for the root and navigation buttons.
     *
     * @param selenideRootLocator        the Selenide locator string for the carousel's root element
     * @param buttonNextSelenideLocator  the Selenide locator string for the "Next" button
     * @param buttonBackSelenideLocator  the Selenide locator string for the "Back" button
     */
    public Carousel(String selenideRootLocator, String buttonNextSelenideLocator, String buttonBackSelenideLocator) {
        super(selenideRootLocator);
        buttonNext = Button.$button(buttonNextSelenideLocator);
        buttonBack = Button.$button(buttonBackSelenideLocator);
        setUiElementType(ElementType.IMAGE_SLIDER.getType());
    }

    /**
     * Constructor to initialize the carousel with {@link SelenideElement} instances for the root and navigation buttons.
     *
     * @param selenideRootElement  the {@link SelenideElement} representing the carousel's root element
     * @param buttonNextElement    the {@link SelenideElement} representing the "Next" button
     * @param buttonBackLocator    the {@link SelenideElement} representing the "Back" button
     */
    public Carousel(SelenideElement selenideRootElement,
                    SelenideElement buttonNextElement,
                    SelenideElement buttonBackLocator) {
        super(selenideRootElement);
        buttonNext = Button.$button(buttonNextElement);
        buttonBack = Button.$button(buttonBackLocator);
        setUiElementType(ElementType.IMAGE_SLIDER.getType());
    }

    /**
     * Scrolls the carousel forward by clicking the "Next" button.
     */
    @Override
    public void scrollForward() {
        buttonNext.click();
    }

    /**
     * Scrolls the carousel backward by clicking the "Back" button.
     */
    @Override
    public void scrollBackward() {
        buttonBack.click();
    }
}
