package rozetka.pages.catalog;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import rozetka.Input;
import rozetka.util.NativeAction;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public class SearchWidget {

    public final Input searchFieldInput;

    public SearchWidget() {
        searchFieldInput = new Input("ua.com.rozetka.shop:id/et_query", "Search Field Widget");
    }

    public void setSearchField(String searchText) {
        NativeAction.sendKeysToKeyboard(searchText);
    }

    public List<String> getVisibleSearchResultText() {
        return getVisibleSearchResultElements().texts();
    }

    @Step("Checking the results on the display = isSearchResultDisplayed")
    public boolean isSearchResultDisplayed() {
        return getVisibleSearchResultElements().first().isDisplayed();
    }

    @Step("Selecting the first visible result = selectFirstVisibleItemInSearchResult")
    public void selectFirstVisibleItemInSearchResult() {
        getVisibleSearchResultElements().first().should(Condition.visible).click();
    }

    private ElementsCollection getVisibleSearchResultElements() {
        return $$x("//androidx.recyclerview.widget.RecyclerView//*[@resource-id = 'ua.com.rozetka.shop:id/tv_title']");
    }
}
