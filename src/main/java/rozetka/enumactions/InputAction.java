package rozetka.enumactions;

import com.codeborne.selenide.SelenideElement;

import static rozetka.BasePage.LOGGER;

public interface InputAction {

    String name();

    SelenideElement element();

    default void setText(String text) {
        element().setValue(text);
    }

    default boolean isEmpty() {
        LOGGER.info("%s %s".formatted(name(), "isEmpty =%s".formatted(element().exists())));
        return element().exists();
    }
}
