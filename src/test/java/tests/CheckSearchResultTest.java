package tests;

import org.testng.annotations.Test;
import rozetka.pages.CatalogPage;
import rozetka.pages.catalog.SearchResultPage;

public class CheckSearchResultTest extends BaseTest {

    private final CatalogPage catalogPage = new CatalogPage();
    private final SearchResultPage searchResultPage = new SearchResultPage();
    private static final String TEFAL = "tefal";

    @Test
    public void checkSearchResult() {
        bottomBarNavigation.catalogButton.click();
        catalogPage.searchField.click();
        catalogPage.descriptionLabel.click();
        catalogPage.searchWidget.searchField.click();

        softAssert.assertFalse(catalogPage.searchWidget.isSearchResultDisplayed(), "Search Result is Displayed.");

        catalogPage.searchWidget.setSearchField(TEFAL);

        softAssert.assertTrue(catalogPage.searchWidget.isSearchResultDisplayed(), "Search Result is not Displayed.");

        catalogPage.searchWidget.selectFirstVisibleItemInSearchResult();

        softAssert.assertTrue(searchResultPage.searchResultLabel.isDisplayed(),
            "Search Result Label is not Displayed.");
        softAssert.assertAll();
    }
}
