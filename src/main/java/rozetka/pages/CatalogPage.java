package rozetka.pages;

import rozetka.Input;
import rozetka.Label;
import rozetka.pages.catalog.SearchWidget;

public class CatalogPage {

    public final Input searchField;
    public final Label descriptionLabel;
    public final SearchWidget searchWidget = new SearchWidget();

    public CatalogPage() {
        searchField = new Input("ua.com.rozetka.shop:id/tv_search", "Search Field");
        descriptionLabel = new Label("ua.com.rozetka.shop:id/empty_base_tv_description", "Description");
    }
}
