package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import rozetka.pages.CatalogPage;
import rozetka.pages.catalog.SearchResultPage;

public class CheckSearchResultTest extends BaseTest {

    private final CatalogPage catalogPage = new CatalogPage();
    private final SearchResultPage searchResultPage = new SearchResultPage();
    private static final String TEFAL = "tefal";

    @Owner("Serhiy Lebediev")
    @Feature("Search Field Feature")
    @Severity(SeverityLevel.BLOCKER)
    @Description("This test checks the search functionality and its result")
    @Link(name = "Link", url = "https://github.com/eltess/Mobile-Framefork/tree/master")

    @TmsLink("ROZ=3131")
    @Test
    public void checkSearchResult() {
        bottomBarNavigation.catalogButton.click();
        catalogPage.searchFieldInput.click();
        catalogPage.descriptionLabel.click();
        catalogPage.searchWidget.searchFieldInput.click();

        softAssert.assertFalse(catalogPage.searchWidget.isSearchResultDisplayed(), "Search Result is Displayed.");

        catalogPage.searchWidget.setSearchField(TEFAL);

        softAssert.assertTrue(catalogPage.searchWidget.isSearchResultDisplayed(), "Search Result is not Displayed.");

        catalogPage.searchWidget.selectFirstVisibleItemInSearchResult();

        softAssert.assertTrue(searchResultPage.searchResultLabel.isDisplayed(),
            "Search Result Label is not Displayed.");
        softAssert.assertAll();
    }
}
