package rozetka;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static rozetka.BasePage.PLATFORM;

public class Button implements ElementAction {

    private String name;
    private SelenideElement element;

    public Button(String id, String name) {
        this.element = $x("//*[@%s='%s']".formatted(PLATFORM.getIdAttribute(), id));
        this.name = "%s %s".formatted(name, "Button");
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public SelenideElement element() {
        return element;
    }
}
