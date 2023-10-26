package rozetka.util;

import io.qameta.allure.Step;

import java.net.MalformedURLException;
import java.net.URL;

import static rozetka.BasePage.LOGGER;
import static rozetka.BasePage.UI_CONFIGURATION;

public class RemoteLinkedServerProvider {

    private static final String REMOTE_SERVER_URL = "http://127.0.0.1:4723/wd/hub";
    private static final String SAUCE_LABS_URL = "https://ondemand.eu-central-1.saucelabs.com:443/wd/hub";
    private static final String SERVER =
        UI_CONFIGURATION.executionPlatform().equals("saucelabs") ? SAUCE_LABS_URL : REMOTE_SERVER_URL;

    @Step("Connection to server")
    public static URL getUrl() {
        LOGGER.info("Connection to server %s".formatted(SERVER));
        URL remoteUrl;
        try {
            remoteUrl = new URL(SERVER);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return remoteUrl;
    }
}
