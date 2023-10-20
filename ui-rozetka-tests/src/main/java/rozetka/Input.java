package rozetka;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static rozetka.BasePage.PLATFORM;


public class Input implements InputAction, ElementAction {

    private String name;
    public SelenideElement element;

    public Input(String id, String name) {
        this.element = $x("//*[@%s='%s']".formatted(PLATFORM.getIdAttribute(), id));
        this.name = "%s %s".formatted(name, "Input");
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
