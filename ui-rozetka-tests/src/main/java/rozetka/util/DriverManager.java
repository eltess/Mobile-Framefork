package rozetka.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.qameta.allure.Step;

import static rozetka.BasePage.LOGGER;
import static rozetka.BasePage.UI_CONFIGURATION;

public class DriverManager {

    @Step("Creating a Driver!")
    public static AppiumDriver getDriver() {
        return UI_CONFIGURATION.platformName().equals("android") ? getAndroidLocalDriver() : getIosLocalDriver();
    }

    @Step("Creating Driver for Android!")
    private static AppiumDriver getAndroidLocalDriver() {
        LOGGER.info("Creating a Driver for Android!");
        UiAutomator2Options options = new UiAutomator2Options().merge(Capabilities.get());
        return new AppiumDriver(RemoteLinkedServerProvider.getUrl(), options);
    }

    @Step("Creating Driver for IOS!")
    private static AppiumDriver getIosLocalDriver() {
        LOGGER.info("Creating a Driver for IOS!");
        XCUITestOptions options = new XCUITestOptions().merge(Capabilities.get());
        return new AppiumDriver(RemoteLinkedServerProvider.getUrl(), options);
    }
}
