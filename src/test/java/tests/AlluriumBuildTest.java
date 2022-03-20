package tests;

import org.testng.annotations.Test;
import pk.tools.UiSteps;
import pk.tools.examples.pages.GoogleSearchPage;
import pk.tools.examples.pages.SearchResultPage;


public class AlluriumBuildTest extends TestBase {

    GoogleSearchPage googleSearchPage = new GoogleSearchPage();
    SearchResultPage searchResultPage = new SearchResultPage();

    @Test
    public void googleSearch() {
        UiSteps.open("https://www.google.com/");
        googleSearchPage.getSubmitCookiesPopup().submitButton.click();
        googleSearchPage.getSearchField().write("mountain view");
        googleSearchPage.getSearchField().pressEnter();
        searchResultPage.getSearchResults().get(0).click();
    }

    @Test
    public void searchImage() {
        UiSteps.open("https://www.google.com/");
        googleSearchPage.getSubmitCookiesPopup().submitButton.click();
        googleSearchPage.getSearchField().write("mountain view");
        googleSearchPage.getSearchField().pressEnter();
        searchResultPage.getSearchGroups().get("Images").click();
    }
}
