package rozetka.enumactions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static rozetka.BasePage.LOGGER;

public interface ElementAction {

    String name();

    SelenideElement element();

    default void waiterForElement() {
        element().should(Condition.visible);
    }

    @Step("Клик = {this.name}")
    default void click() {
        LOGGER.info("%s %s".formatted("Click", name()));
        waiterForElement();
        element().click();
    }

    @Step("Проверка результатов на дисплаед = {this.name}")
    default boolean isDisplayed() {
        waiterForElement();
        LOGGER.info("%s %s".formatted(name(), "isDisplayed =%s".formatted(element().isDisplayed())));
        return element().isDisplayed();
    }

    default boolean isEnabled() {
        waiterForElement();
        LOGGER.info("%s %s".formatted(name(), "isEnabled =%s".formatted(element().isEnabled())));
        return element().isEnabled();
    }

    default boolean isSelected() {
        waiterForElement();
        LOGGER.info("%s %s".formatted(name(), "isSelected =%s".formatted(element().isSelected())));
        return element().isSelected();
    }
}
