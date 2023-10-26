package rozetka.util;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import rozetka.config.SauceLabsConfiguration;

import static rozetka.BasePage.UI_CONFIGURATION;

public class Capabilities {

    private static final SauceLabsConfiguration SAUCE_LABS_CONFIGURATION =
        ConfigFactory.create(SauceLabsConfiguration.class);

    public static DesiredCapabilities get() {
        return "saucelabs".equals(UI_CONFIGURATION.executionPlatform()) ? getSauceLabsCapabilities()
            : getLocalCapabilities();
    }

    private static DesiredCapabilities getLocalCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", UI_CONFIGURATION.deviceName());
        caps.setCapability("app", UI_CONFIGURATION.appLink());
        return caps;
    }

    private static DesiredCapabilities getSauceLabsCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", SAUCE_LABS_CONFIGURATION.platformName());
        caps.setCapability("appium:app", SAUCE_LABS_CONFIGURATION.appSauceLabs());
        caps.setCapability("appium:deviceName", SAUCE_LABS_CONFIGURATION.deviceName());
        caps.setCapability("appium:deviceOrientation", "portrait");
        caps.setCapability("appium:platformVersion", SAUCE_LABS_CONFIGURATION.platformVersion());
        caps.setCapability("appium:automationName", SAUCE_LABS_CONFIGURATION.automationName());

        DesiredCapabilities sauceOptions = new DesiredCapabilities();
        sauceOptions.setCapability("username", SAUCE_LABS_CONFIGURATION.sauceUserName());
        sauceOptions.setCapability("accessKey", SAUCE_LABS_CONFIGURATION.sauceAccessKey());
        sauceOptions.setCapability("build", "appium-build-ZCSYN");
        sauceOptions.setCapability("name", "<Rozetka>");
        caps.setCapability("sauce:options", sauceOptions);
        return caps;
    }
}
