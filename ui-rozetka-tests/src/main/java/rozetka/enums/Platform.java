package rozetka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Platform {

    ANDROID("resource-id", "text", "clickable", "android.widget.TextView",
        "android.view.ViewGroup", "android.widget.ScrollView", "android.widget.HorizontalScrollView"),
    IOS("name", "label", "accessible", "XCUIElementTypeStaticText",
        "XCUIElementTypeOther", "XCUIElementTypeScrollView", "XCUIElementTypeScrollView");

    private final String idAttribute;
    private final String textAttribute;
    private final String clickableAttribute;
    private final String textClass;
    private final String defaultClass;
    private final String scrollClass;
    private final String horizontalScrollClass;
}
