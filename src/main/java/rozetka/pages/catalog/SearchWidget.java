package rozetka.pages.catalog;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import rozetka.Input;
import rozetka.util.NativeAction;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public class SearchWidget {

    public final Input searchField;

    public SearchWidget() {
        searchField = new Input("ua.com.rozetka.shop:id/et_query", "Search Field Widget");
    }

    public void setSearchField(String searchText) {
        NativeAction.sendKeysToKeyboard(searchText);
    }

    public List<String> getVisibleSearchResultText() {
        return getVisibleSearchResultElements().texts();
    }

    public boolean isSearchResultDisplayed() {
        return getVisibleSearchResultElements().first().isDisplayed();
    }

    public void selectFirstVisibleItemInSearchResult() {
        getVisibleSearchResultElements().first().should(Condition.visible).click();
    }

    private ElementsCollection getVisibleSearchResultElements() {
        return $$x("//androidx.recyclerview.widget.RecyclerView//*[@resource-id = 'ua.com.rozetka.shop:id/tv_title']");
    }
}
