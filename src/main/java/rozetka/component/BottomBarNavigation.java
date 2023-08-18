package rozetka.component;

import rozetka.Button;

public class BottomBarNavigation {

    public final Button homButton;
    public final Button catalogButton;
    public final Button bugButton;
    public final Button favoritesButton;
    public final Button moreButton;

    public BottomBarNavigation() {
        homButton = new Button("ua.com.rozetka.shop:id/tab_home", "Home");
        catalogButton = new Button("ua.com.rozetka.shop:id/tab_fat_menu", "Catalog");
        bugButton = new Button("ua.com.rozetka.shop:id/tab_cart", "Bug");
        favoritesButton = new Button("ua.com.rozetka.shop:id/tab_wishlists", "Favorites");
        moreButton = new Button("ua.com.rozetka.shop:id/tab_more", "More");
    }
}
