package rozetka;

import com.codeborne.selenide.SelenideElement;
import rozetka.enumactions.ElementAction;
import rozetka.listener.UiConfiguration;

import static com.codeborne.selenide.Selenide.$x;

public class Label implements ElementAction {

    private String name;
    public SelenideElement element;

    public Label(String id, String name) {
        this.element = $x("//*[@%s='%s']".formatted(UiConfiguration.PLATFORM.getIdAttribute(), id));
        this.name = "%s %s".formatted(name, "Label");;
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
