package allurium.interfaces;

import com.codeborne.selenide.SelenideElement;

/**
 * Marker interface for elements that are compatible with Allure reporting and logging mechanisms.
 * <p>
 * This interface is implemented by UI components to integrate with Allure for tracking and reporting
 * test steps and actions. Any class implementing this interface can provide enhanced logging and reporting
 * capabilities for test automation.
 * </p>
 *
 * <h3>Purpose:</h3>
 * <ul>
 *   <li>Identifies UI elements that support Allure reporting and logging.</li>
 *   <li>Facilitates detailed reporting of actions performed during test execution.</li>
 * </ul>
 *
 * <h3>Features:</h3>
 * <ul>
 *   <li>Encapsulates the root element of a UI component using Selenide.</li>
 *   <li>Serves as a base for custom UI elements that need integration with Allure.</li>
 * </ul>
 */
public interface AlluriumElement extends WebElementMeta {

    SelenideElement getRoot();
}
