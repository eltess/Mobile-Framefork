package rozetka.util;

import com.codeborne.selenide.*;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import rozetka.enums.RelativePosition;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;
import static rozetka.BasePage.LOGGER;
import static rozetka.BasePage.PLATFORM;
import static rozetka.enums.Platform.ANDROID;
import static rozetka.util.Direction.UP;

@SuppressWarnings("PMD")
public class NativeAction {

    private static final int DEFAULT_POLLING_INTERVAL = 100;

    @Deprecated(forRemoval = true)
    public static void backButtonClick() {
        LOGGER.info("Navigating back with native back button...");
        Selenide.back();
    }

    public static void openApp(String bundleId, String appActivity) {
        LOGGER.info("Opening application...");
        if (PLATFORM.equals(Platform.ANDROID)) {
            ((AndroidDriver) getWebDriver()).startActivity(new Activity(bundleId, appActivity));
        } else {
            ((AppiumDriver) getWebDriver()).executeScript("mobile: activateApp",
                Map.of("bundleId", bundleId));
        }
    }

    public static String getAndroidCurrentActivity() {
        return ((AndroidDriver) getWebDriver()).currentActivity();
    }

    public static void tapBackButton() {
        LOGGER.info("Navigating back with native back button...");
        Selenide.back();
    }

    public static void tapHomeButton() {
        ((AppiumDriver) getWebDriver()).executeScript("mobile: pressButton", Map.of("name", "home"));
        Selenide.sleep(500);
    }

    public static void androidScrollToText(String visibleText) {
        $(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
                + visibleText + "\").instance(0))"));
    }

    public static void androidScrollToElementWithId(String resourceId) {
        $(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceId(\""
                + resourceId + "\").instance(0))"));
    }

    // public static void scrollToText(String text) {
    // scrollToText(text, 1);
    // }
    //
    // public static void scrollToText(String text, double swipeCoef) {
    // scrollToElement(getElementByTextIgnoringCase(text), swipeCoef);
    // }

    public static void swipeElement(SelenideElement element, Direction direction) {
        swipe(GeometryUtil.getElementCenter(element), direction, 1);
    }

    public static void swipeElement(SelenideElement element, Direction direction, double swipeCoef) {
        swipe(GeometryUtil.getElementCenter(element), direction, swipeCoef);
    }

    public static void swipeFromScreenCenter(Direction direction) {
        swipeFromScreenCenter(direction, 1);
    }

    public static void swipeFromScreenCenter(Direction direction, double swipeCoef) {
        var driver = (AppiumDriver) getWebDriver();
        var screenCenter = new Point(driver.manage().window().getSize().getWidth() / 2,
            driver.manage().window().getSize().getHeight() / 2);
        swipe(screenCenter, direction, swipeCoef);
    }

    public static void swipe(Point from, Direction direction, double swipeCoef) {
        var driver = (AppiumDriver) getWebDriver();
        swipe(from, switch (direction) {
            case UP -> new Point(from.getX(), (int) ((1 - swipeCoef) * from.getY()));
            case DOWN -> new Point(from.getX(),
                driver.manage().window().getSize().getHeight() - (int) ((1 - swipeCoef) * from.getY()));
            case LEFT -> new Point((int) ((1 - swipeCoef) * from.getY()), from.getX());
            case RIGHT -> new Point(
                driver.manage().window().getSize().getWidth() - (int) ((1 - swipeCoef) * from.getX()), from.getY());
        });
    }

    public static void swipe(Point from, Point to) {
        var driver = (AppiumDriver) getWebDriver();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 0);
        sequence.addAction(finger.createPointerMove(Duration.ZERO, viewport(), from));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(finger.createPointerMove(ofMillis(1000), viewport(), to))
            .addAction(new Pause(finger, ofSeconds(1)))
            .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(sequence));
    }

    public static void swipeTillCondition(Point from, Point to, Supplier<Boolean> condition, int iterationsLimit) {
        int count = 0;
        while (!condition.get() && count < iterationsLimit) {
            swipe(from, to);
            count++;
        }
    }

    public static boolean isKeyboardShown() {
        return ((HasOnScreenKeyboard) getWebDriver()).isKeyboardShown();
    }

    @Deprecated(forRemoval = true)
    public static void waitForKeyboard(int secondsLimit) {
        long startTime = System.currentTimeMillis();
        while (!isKeyboardShown() && System.currentTimeMillis() - startTime < secondsLimit * 1000L) {
            Selenide.sleep(DEFAULT_POLLING_INTERVAL);
        }
    }

    public static void waitUntilKeyboardAppears(int secondsLimit) {
        waitUntil(driver -> ((HasOnScreenKeyboard) driver).isKeyboardShown(),
            secondsLimit * 1000L,
            "Keyboard is not shown.");
    }

    public static void waitUntilKeyboardDisappears(int secondsLimit) {
        waitUntil(driver -> !((HasOnScreenKeyboard) driver).isKeyboardShown(),
            secondsLimit * 1000L,
            "Keyboard is shown.");
    }

    private static void waitUntil(Function<? super WebDriver, Boolean> condition, long timeout, String msg) {
        new SelenideWait(getWebDriver(), timeout, DEFAULT_POLLING_INTERVAL)
            .withMessage(msg)
            .until(condition);
    }

    public static void pressEnterOnKeyboard() {
        new Actions(getWebDriver()).sendKeys("\n").perform();
    }

    public static void pressSearchOnKeyboard() {
        switch (PLATFORM) {
            case ANDROID -> ((AppiumDriver) getWebDriver()).executeScript("mobile: performEditorAction",
                ImmutableMap.of("action", "search"));
            case IOS -> pressEnterOnKeyboard();
        }
    }

    public static void scrollToElement(SelenideElement selenideElement, double swipeCoef) {
        int counter = 1;
        while (!selenideElement.isDisplayed() && counter < 8) {
            swipeFromScreenCenter(UP, swipeCoef);
            // log.info("Swipe '{}' screen", UP + " " + counter);
            counter++;
        }
    }

    public static void scrollElementsToLastElement(ElementsCollection elementList, Direction direction) {
        int counter = 1;
        String resourceId = elementList.first().attr("%s".formatted(PLATFORM.getIdAttribute()));
        do {
            if (elementList.last().attr("%s".formatted(PLATFORM.getIdAttribute())).equals(resourceId)) {
                break;
            }
            resourceId = elementList.last().attr("%s".formatted(PLATFORM.getIdAttribute()));
            swipe(GeometryUtil.getElementCenter(elementList.first()), direction, 1);
            // log.info("Swipe '{}' screen", direction + " " + counter);
            counter++;
        } while (counter < 8);
    }

    public static void iOSScrolling(double swipeCoef, int amount) {
        int counter = 0;
        do {
            swipeFromScreenCenter(UP, swipeCoef);
            counter++;
        } while (counter <= amount);
    }

    public static void scrollToElement(SelenideElement selenideElement) {
        scrollToElement(selenideElement, 1);
    }

    public static void scrollElementUpAndPlaceWithShiftToCenterOfScreen(SelenideElement selenideElement, double shift) {
        Point center = GeometryUtil.getElementCenter(selenideElement);
        var driver = (AppiumDriver) getWebDriver();
        int heightScreen = driver.manage().window().getSize().getHeight();
        Point finalPoint = new Point(0, (int) (heightScreen * shift));
        while ((center.getY() - heightScreen * shift) > 30) {
            swipe(center, finalPoint);
            center = GeometryUtil.getElementCenter(selenideElement);
        }
        // log.info("Scroll '{}' screen", UP);
    }

    public static boolean isElementSticky(SelenideElement element, Direction direction, RelativePosition position) {
        boolean positionBefore =
            GeometryUtil.getVerticalRelativePositionForElement(element).equals(position);
        swipeFromScreenCenter(direction);
        boolean positionAfter =
            GeometryUtil.getVerticalRelativePositionForElement(element).equals(position);
        return positionAfter && positionBefore;
    }

    // public static void setValue(Input input, String value) {
    // if (PLATFORM.equals(Platform.ANDROID)) {
    // input.setValue(value);
    // } else {
    // input.click();
    // sendKeysToKeyboard(value);
    // input.elementAction().pressEnter();
    // }
    // }

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
        (PLATFORM.equals(ANDROID) ? ((AndroidDriver) getWebDriver()) : (IOSDriver) getWebDriver())
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
