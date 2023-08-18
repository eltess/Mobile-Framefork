package rozetka.util;

import com.codeborne.selenide.Selenide;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.interactions.Actions;
import rozetka.listener.UiConfiguration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static rozetka.BasePage.LOGGER;
import static rozetka.enums.Platform.ANDROID;

public class NativeAction {

    public static void sendKeysToKeyboard(String keys) {
        Actions actions = new Actions(getWebDriver());
        LOGGER.info("%s *%s*".formatted("Set text =", keys));
        for(Character character : keys.toCharArray()) {
            actions.sendKeys(character.toString()).perform();
            Selenide.sleep(100);
        }
    }

    // todo need fix this method
    public static void hideKeyboard() {
        (UiConfiguration.PLATFORM.equals(ANDROID) ? ((AndroidDriver) getWebDriver()) : (IOSDriver) getWebDriver())
            .hideKeyboard();
    }
}
