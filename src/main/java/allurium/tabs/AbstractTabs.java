package allurium.tabs;

import allurium.AbstractWidget;
import allurium.ElementType;
import allurium.primitives.Tab;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import lombok.Setter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Represents an abstract base class for tab components in a UI.
 * <p>
 * This class provides a foundation for handling tab-based navigation, allowing subclasses to define
 * specific behaviors such as retrieving the currently active tab.
 * </p>
 *
 * <h3>Features:</h3>
 * <ul>
 *     <li>Encapsulates tab selection logic.</li>
 *     <li>Supports multiple initialization methods using different locators.</li>
 *     <li>Provides an abstract contract for retrieving the active tab.</li>
 *     <li>Extends {@link AbstractWidget} to inherit UI component functionality.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <pre>{@code
 * public class NavigationTabs extends AbstractTabs {
 *
 *     public NavigationTabs(String selector) {
 *         super(selector);
 *     }
 *
 *     @Override
 *     public String getActiveTabName() {
 *         return tabs.filter(Condition.cssClass("active")).first().text();
 *     }
 *
 *     @Override
 *     public Tab getActive() {
 *         return new Tab(tabs.filter(Condition.cssClass("active")).first());
 *     }
 * }
 *
 * // Selecting a tab
 * NavigationTabs tabs = new NavigationTabs(".nav-tabs > li");
 * tabs.select("Settings");
 * }</pre>
 *
 * <h3>Constructors:</h3>
 * <ul>
 *     <li>{@link #AbstractTabs()} - Default constructor.</li>
 *     <li>{@link #AbstractTabs(String)} - Initializes the tabs using a Selenide selector string.</li>
 *     <li>{@link #AbstractTabs(By)} - Initializes the tabs using a Selenium {@link By} locator.</li>
 * </ul>
 *
 * @see AbstractWidget
 * @see ElementType
 * @author Iaroslav Pilipenko
 */
@Setter
public abstract class AbstractTabs extends AbstractWidget {

    private ElementsCollection tabs;

    /**
     * Default constructor. Initializes the tab component as a generic UI element with type "tabs".
     */
    public AbstractTabs() {
        super();
        setUiElementType(ElementType.TABS.getType());
    }

    /**
     * Constructs an {@code AbstractTabs} instance using a Selenide selector string.
     * <p>
     * The provided selector is used to locate all tab elements.
     * </p>
     *
     * @param selenideLocator the Selenide selector string for locating tab elements
     */
    public AbstractTabs(String selenideLocator) {
        super();
        setUiElementType(ElementType.TABS.getType());
        tabs = $$(selenideLocator);
    }

    /**
     * Constructs an {@code AbstractTabs} instance using a Selenium {@link By} locator.
     * <p>
     * The provided locator is used to find the tab elements.
     * </p>
     *
     * @param locator the Selenium {@link By} locator for finding tab elements
     */
    public AbstractTabs(By locator) {
        super();
        setUiElementType(ElementType.TABS.getType());
        tabs = $$(locator);
    }

    /**
     * Selects a tab by its displayed name.
     * <p>
     * The method searches for a tab element that exactly matches the provided {@code tabName}
     * and clicks on it to activate the tab.
     * </p>
     *
     * <h3>Behavior:</h3>
     * <ul>
     *     <li>Filters the tab elements based on exact text match.</li>
     *     <li>Clicks the first matching tab.</li>
     * </ul>
     *
     * <h3>Usage Example:</h3>
     * <pre>{@code
     * AbstractTabs tabs = new NavigationTabs(".tab-items");
     * tabs.select("Dashboard");
     * }</pre>
     *
     * @param tabName the exact text of the tab to be selected
     */
    public void select(String tabName) {
        tabs.filter(Condition.exactText(tabName)).first().click();
    }

    /**
     * Retrieves the name of the currently active tab.
     * <p>
     * Subclasses must implement this method to define the logic for identifying the active tab.
     * </p>
     *
     * @return the name of the active tab
     */
    public abstract String getActiveTabName();

    /**
     * Retrieves the currently active tab as a {@link Tab} object.
     * <p>
     * Subclasses must implement this method to define how the active tab is identified
     * and wrapped in a {@link Tab} instance.
     * </p>
     *
     * <h3>Usage Example:</h3>
     * <pre>{@code
     * Tab activeTab = myTabs.getActive();
     * System.out.println("Currently active tab: " + activeTab.text());
     * }</pre>
     *
     * @return a {@link Tab} instance representing the currently active tab
     */
    public abstract Tab getActive();

}
