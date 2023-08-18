package rozetka.listener;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DeviceCapabilities {

    private static final String REMOTE_SERVER_URL = "http://127.0.0.1:4723/wd/hub";

    public AppiumDriver driver() {
        return ReadProperties.readProperties().getProperty("platform").equals("android") ? androidDriver()
            : iosDriver();
    }

    private AppiumDriver androidDriver() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(ReadProperties.readProperties().getProperty("deviceName"));
        options.setApp(ReadProperties.readProperties().getProperty("app"));

        return new AppiumDriver(getUrl(), options);
    }

    private AppiumDriver iosDriver() {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(ReadProperties.readProperties().getProperty("deviceName"));
        options.setApp(ReadProperties.readProperties().getProperty("app"));

        return new AppiumDriver(getUrl(), options);
    }

    private URL getUrl() {
        URL remoteUrl;
        try {
            remoteUrl = new URL(REMOTE_SERVER_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return remoteUrl;
    }
}
