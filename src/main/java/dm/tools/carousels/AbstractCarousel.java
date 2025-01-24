package dm.tools.carousels;

import com.codeborne.selenide.SelenideElement;
import dm.tools.AbstractWidget;
import dm.tools.ElementType;
import dm.tools.annotations.Widget;
import org.openqa.selenium.By;

/**
 * Represents the base class for carousels in the UI, extending {@link AbstractWidget}.
 * <p>
 * This class provides a foundation for creating carousel components with features such as forward
 * and backward scrolling. It integrates with the widget framework and supports dynamic extensions
 * for carousel-specific behavior.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates common behavior and attributes of carousel components.</li>
 *     <li>Provides abstract methods for forward and backward scrolling.</li>
 *     <li>Uses the {@link Widget} annotation for enhanced identification and integration.</li>
 * </ul>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *     <li>Standardizes the implementation of carousel-like components in the UI.</li>
 *     <li>Facilitates integration with the UI framework and test infrastructure.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * public class ImageCarousel extends AbstractCarousel {
 *
 *     public ImageCarousel(SelenideElement rootElement) {
 *         super(rootElement);
 *     }
 *
 *     @Override
 *     public void scrollForward() {
 *         getRoot().$("button.next").click();
 *     }
 *
 *     @Override
 *     public void scrollBackward() {
 *         getRoot().$("button.prev").click();
 *     }
 * }
 * }</pre>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #AbstractCarousel()} - Default constructor.</li>
 *     <li>{@link #AbstractCarousel(By)} - Creates a carousel using a Selenium {@link By} locator.</li>
 *     <li>{@link #AbstractCarousel(String)} - Creates a carousel using a Selenide locator string.</li>
 *     <li>{@link #AbstractCarousel(SelenideElement)} - Creates a carousel using a {@link SelenideElement}.</li>
 * </ul>
 *
 * <h3>Abstract Methods:</h3>
 * <ul>
 *     <li>{@link #scrollForward()} - Scrolls the carousel forward. This method must be implemented by subclasses.</li>
 *     <li>{@link #scrollBackward()} - Scrolls the carousel backward. This method must be implemented by subclasses.</li>
 * </ul>
 *
 * <h3>Integration:</h3>
 * <ul>
 *     <li>Extends {@link AbstractWidget} to inherit widget-related behaviors and attributes.</li>
 *     <li>Annotated with {@link Widget} for enhanced integration with the widget framework.</li>
 *     <li>Processed by Aspect-Oriented Programming (AOP) for extended behaviors during runtime.</li>
 * </ul>
 *
 * @see AbstractWidget
 * @see Widget
 *
 * @author Iaroslav Pilipenko
 */
@Widget
public abstract class AbstractCarousel extends AbstractWidget {

    /**
     * Default constructor. Initializes the carousel as a generic widget with the type "carousel".
     */
    public AbstractCarousel() {
        super();
        setUiElementType(ElementType.CAROUSEL.getType());
    }

    /**
     * Constructor that initializes the carousel using a Selenium {@link By} locator.
     *
     * @param rootLocator the Selenium locator for the carousel's root element
     */
    public AbstractCarousel(By rootLocator) {
        super(rootLocator);
        setUiElementType(ElementType.CAROUSEL.getType());
    }

    /**
     * Constructor that initializes the carousel using a Selenide locator string.
     *
     * @param selenideRootLocator the Selenide locator string for the carousel's root element
     */
    public AbstractCarousel(String selenideRootLocator) {
        super(selenideRootLocator);
        setUiElementType(ElementType.CAROUSEL.getType());
    }

    /**
     * Constructor that initializes the carousel using a Selenide element.
     *
     * @param selenideRootElement the Selenide element representing the carousel's root
     */
    public AbstractCarousel(SelenideElement selenideRootElement) {
        super(selenideRootElement);
        setUiElementType(ElementType.CAROUSEL.getType());
    }

    /**
     * Scrolls the carousel forward.
     * <p>
     * This method must be implemented by subclasses to provide the specific behavior for scrolling the
     * carousel to the next set of items.
     * </p>
     * <p><b>< Step: Processed by Aspect ></b></p>
     */
    public abstract void scrollForward();

    /**
     * Scrolls the carousel backward.
     * <p>
     * This method must be implemented by subclasses to provide the specific behavior for scrolling the
     * carousel to the previous set of items.
     * </p>
     * <p><b>< Step: Processed by Aspect ></b></p>
     */
    public abstract void scrollBackward();
}
