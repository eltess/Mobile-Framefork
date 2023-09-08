package rozetka.pages;

import com.codeborne.selenide.SelenideElement;
import rozetka.Popup;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    public final Popup popup;
    public final SelenideElement getAlertElement =
        $x("//*[@resource-id= 'com.android.permissioncontroller:id/grant_dialog'");

    public HomePage() {
        this.popup = new Popup();
    }
}
