package tests;

import com.codeborne.selenide.Condition;
import allurium.UiSteps;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

public class ITExampleTest extends TestBase {

    /*
    @Test
    @DisplayName("Practice form filling")
    public void practiceForm() {
        URL imageUrl = this.getClass().getClassLoader().getResource("img/testicon.png");

        UiSteps.loadPage("https://demoqa.com/automation-practice-form");
        practiceFormPage.firstNameField().write("Traver");
        practiceFormPage.lastNameField().write("Gor");
        practiceFormPage.emailField().write("qa-main-email@wtf.com");
        practiceFormPage.maleRadioButton().click();
        practiceFormPage.mobileField().write("3575566999");
        practiceFormPage.hobbiesMusic().check();
        practiceFormPage.currentAddress().write("Far Far Away");
        practiceFormPage.pictureUploadField().uploadFile(new File(imageUrl.getFile()));
        practiceFormPage.submitButton().click();
    }
     */

    @Test
    @DisplayName("Playing with categories folding menu")
    public void composeComponentListsWithChainedLocators() {
        UiSteps.loadPage("https://demoqa.com/automation-practice-form");
        practiceFormPage.leftSideCategoriesMenu().blockElements().iconArrow().click();
        practiceFormPage.leftSideCategoriesMenu().blockElements().subcategories().get("Links").click();
        practiceFormPage.leftSideCategoriesMenu().blockElements().subcategories().get("Text Box").click();
        practiceFormPage.leftSideCategoriesMenu().blockForms().iconArrow().click();
        practiceFormPage.leftSideCategoriesMenu().blockForms().subcategories().get("Practice Form");
        practiceFormPage.leftSideCategoriesMenu().blockWidgets().iconArrow().click();
        practiceFormPage.leftSideCategoriesMenu().blockWidgets().subcategories().get("Slider").click();
        practiceFormPage.leftSideCategoriesMenu().blockWidgets().subcategories().get("Tabs").click();
    }

    @Test
    @DisplayName("Using list to handle a table")
    public void usingListToHandleTable() {
        UiSteps.loadPage("https://demoqa.com/webtabl" +
                "es");
        webTablesPage.listEmployee().get("Vega").firstName().click();
        webTablesPage.listEmployee().get("Cantrell").lastName().click();
        webTablesPage.listEmployee().get("Gentry").email().click();
    }

    @Test
    @DisplayName("Using 'Iframe' class to easily work with iframe elements")
    public void iframeExample() {
        String iframePageUrl = System.getProperty("user.dir")+"/src/test/resources/html/iframe.html";
        UiSteps.openBrowser(iframePageUrl);

        iframePage.fieldName().write("John");
        iframePage.fieldEmail().write("john.doe@email.com");
        iframePage.fieldPassword().write("hardAndLong");
        iframePage.btnSubmit().click();

        iframePage.iframeLoginForm().switchDriverToIframe();

        iframePage.iframeLoginForm().fieldName().write("framed-John");
        iframePage.iframeLoginForm().fieldEmail().write("framed-doe@email.com");
        iframePage.iframeLoginForm().fieldPassword().write("framed_hardAndLong");
        iframePage.iframeLoginForm().switchDriverBack();

        iframePage.fieldName().clearAndWrite("Will");
        iframePage.fieldEmail().clearAndWrite("will.doe@email.com");
    }

    @Test
    @Feature("Simple elements list")
    @DisplayName("Walk through the lists of simple elements")
    public void fillListOfSimpleInputElements() {
        UiSteps.openBrowser("file:///D:/projects/java/allurium-showcase/allurium-showcase/src/test/resources/html/lists.html");
        simpleListsPage.inputTextFields().get(0).write("Eagle");
        simpleListsPage.inputTextFields().get(1).write("Sparrow");
        simpleListsPage.inputTextFields().get(2).write("Parrot");
        simpleListsPage.inputTextFields().get(3).write("Penguin");
        simpleListsPage.inputTextFields().get(4).write("Owl");
        simpleListsPage.inputTextFields().get(5).write("Flamingo");
        simpleListsPage.inputTextFields().get(6).write("Peacock");
        simpleListsPage.inputTextFields().get(7).write("Hummingbird");
        simpleListsPage.inputTextFields().get(8).write("Woodpecker");
        simpleListsPage.inputTextFields().get(9).write("Crow");
        simpleListsPage.inputTextFields().get(5).assertCurrentValue("Flamingo");
        simpleListsPage.inputTextFields().get(7).assertCurrentValue("Hummingbird");
        simpleListsPage.listBirdNameButtons().get("Eagle").click();
        simpleListsPage.listBirdNameButtons().get("Sparrow").click();
        simpleListsPage.listBirdNameButtons().get("Parrot").click();
        simpleListsPage.listBirdNameButtons().get("Penguin").click();
        simpleListsPage.listBirdNameButtons().get("Owl").click();
        simpleListsPage.listBirdNameButtons().get("Flamingo").click();
        simpleListsPage.listBirdNameButtons().get("Peacock").click();
        simpleListsPage.listBirdNameButtons().get("Hummingbird").click();
        simpleListsPage.listBirdNameButtons().get("Woodpecker").click();
        simpleListsPage.listBirdNameButtons().get("Crow").click();
        simpleListsPage.listBirdNameButtons().filter(Condition.text("Sparrow")).get(0).click();
    }
}
