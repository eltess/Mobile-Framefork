package rozetka.util;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static rozetka.BasePage.LOGGER;
import static rozetka.enums.Platform.ANDROID;

public class NativeAction {

    @Step("Entering text character by character = {keys}")
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

    public static void closeAlert() {
        if (isAlertPresent()) {
            Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
            alert.accept();
        }
    }

    @Step("Check for ALERT")
    private static boolean isAlertPresent() {
        try {
            WebDriverRunner.getWebDriver().switchTo().alert();
            LOGGER.info("ALERT IS PRESENT !! ");
            return true;
        } catch (Exception e) {
            LOGGER.info("ALERT IS NOT PRESENT !! ");
            return false;
        }
    }
}
