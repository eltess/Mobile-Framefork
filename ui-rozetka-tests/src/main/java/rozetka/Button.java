package rozetka;

import com.codeborne.selenide.SelenideElement;
import rozetka.util.UiConfiguration;

import static com.codeborne.selenide.Selenide.$x;

public class Button implements ElementAction {

    private String name;
    public SelenideElement element;

    public Button(String id, String name) {
        this.element = $x("//*[@%s='%s']".formatted(UiConfiguration.PLATFORM.getIdAttribute(), id));
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
