# Allurium

The powerful framework for easy selenide tests writing with convenient auto-generating reporting system 
based on Allure. 

### [Web Site](https://ipk-tools.github.io/allurium-showcase/eng/index.html)
### [Documentation](https://ipk-tools.github.io/Allurium/)

---

## Pros

* **Reducing Boilerplate:** Avoid duplicating trivial wrapper methods for every simple action.
* **Enhancing Readability:** Produce reports that clearly describe each test step in plain language.
* **Flexibility:** Allow tests to be written in a free style while leveraging auto-logging features.
* **Multi-Language Reporting:** Enable report generation in languages other than English.

## Features

* **Seamless Integration:** Works with Allure and Selenide.
* **Page Object & Widget Pattern:** Encourages a clear and maintainable structure for your tests.
* **Automatic Allure Reporting:** Automatically logs steps and assertions, generating rich, BDD-style reports.
* **Type-Safe Elements:** Use strongly typed elements such as `TextField`, `Button`, `CheckBox`, and more.
* **Flexible Locators:** Support for CSS, XPath, class names, and IDs via annotations like `@Locator`.
* **Customizable Logging:** Edit and control the level of detail in logged steps.


## Quick Start

### Adding Allurium to Your Project

**For Maven users:**

```xml
<dependency>
    <groupId>io.github.ipk-tools</groupId>
    <artifactId>allurium</artifactId>
    <version>1.1.0</version>
</dependency>
```

For Gradle users:
```
implementation group: 'io.github.ipk-tools', name: 'allurium', version: '1.1.0'
```

## Usage Examples

### Subscription Form Example

A quick example using a Page Object to interact with a subscription form:

```java
import allurium.annotations.Locator;
import allurium.annotations.Name;
import allurium.annotations.PageObject;
import allurium.inputs.TextField;
import allurium.primitives.Button;

@PageObject
public class SubscriptionPage {

    @Name("Email")
    @Locator(css = "#email")
    private TextField fieldEmail;

    @Name("Submit")
    @Locator(xpath = "//button")
    private Button buttonSubmit;
}
```

And a test that utilizes this Page Object:

```java
import allurium.UiSteps;
import org.testng.annotations.Test;

public class MainTest {
    private SubscriptionPage subPage = new SubscriptionPage();
    private String subFormPageUrl = "your_subscription_page_url"; //Replace with actual URL

    @Test
    public void testSubscriptionForm() {
        UiSteps.openBrowser(subFormPageUrl);
        subPage.getFieldEmail().write("john.doe@email.com");
        subPage.getButtonSubmit().click();
        UiSteps.assertUrlPath("/success");
    }
}
```

And we automatically get the report

![My Image](https://ipk-tools.github.io/allurium-showcase/eng/examples/subscriptionForm/img/sub_form_report.jpg)



## Allurium - FAQ and Examples

- [Filling a Simple Form](https://ipk-tools.github.io/allurium-showcase/eng/examples/fillTheForm/fillTheForm.html)
- [Lists of Simple Elements, Search and Usage](https://ipk-tools.github.io/allurium-showcase/eng/examples/fillListOfSimpleInputElements/fillListOfSimpleInputElements.html)
- [Navigation Through Dropdown Menu](https://ipk-tools.github.io/allurium-showcase/eng/examples/multiLevelMenuNavigation/multiLevelMenuNavigation.html)
- [Widget List, Search and Usage](https://ipk-tools.github.io/allurium-showcase/eng/examples/accordionPassedTest/accordionPassedTest.html)
- [Example of a Test with an Error](https://ipk-tools.github.io/allurium-showcase/eng/examples/accordionFailedTest/accordionFailedTest.html)
- [Lists of Widgets with Nested Lists](https://ipk-tools.github.io/allurium-showcase/eng/examples/sectionsWithListsOfElements/sectionsWithListsOfElements.html)
- [Describing and Managing a Table with ListWC](https://ipk-tools.github.io/allurium-showcase/eng/examples/tableExample/tableExample.html)
- [Dynamic Table Management](https://ipk-tools.github.io/allurium-showcase/eng/examples/dynamicTable/dynamicTable.html)
- [List Filtering](https://ipk-tools.github.io/allurium-showcase/eng/examples/listFiltering/listFiltering.html)
- [Working with Elements Inside an iframe](https://ipk-tools.github.io/allurium-showcase/eng/examples/iframeExample/iframeExample.html)
- [Creating Your Own Element](https://ipk-tools.github.io/allurium-showcase/eng/examples/makeNewElementRequitedField/makeNewElementRequitedField.html)
- [Managing Carousel-type Widgets](https://ipk-tools.github.io/allurium-showcase/eng/examples/carouselExample/carouselExample.html)
- [Switch Management](https://ipk-tools.github.io/allurium-showcase/eng/examples/switchExample/switchExample.html)
- [General Purpose Steps](https://ipk-tools.github.io/allurium-showcase/eng/examples/ui_steps/ui_steps.html)
- [Browser Actions](https://ipk-tools.github.io/allurium-showcase/eng/examples/browser_steps/browser_steps.html)
- [Configuration Parameters](https://ipk-tools.github.io/allurium-showcase/eng/examples/properties/properties.html)
- [Editing Element Name Styling in the Report](https://ipk-tools.github.io/allurium-showcase/eng/examples/changing_names_wrapping_in_reports/changing_names_wrapping_in_reports.html)
- [Customization of Steps](https://ipk-tools.github.io/allurium-showcase/eng/examples/report_steps_editing/report_steps_editing.html)
- [Changing Step Detailing](https://ipk-tools.github.io/allurium-showcase/eng/examples/steps_detailing_level/steps_detailing_level.html)
- [Report Localization](https://ipk-tools.github.io/allurium-showcase/eng/examples/report_different_lang/report_different_lang.html)
