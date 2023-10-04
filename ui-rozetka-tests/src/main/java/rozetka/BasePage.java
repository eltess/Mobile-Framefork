package rozetka;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import rozetka.config.UIConfiguration;
import rozetka.enums.Platform;

import static rozetka.enums.Platform.ANDROID;
import static rozetka.enums.Platform.IOS;

public class BasePage {

    public static final Logger LOGGER = Logger.getRootLogger();
    public static final UIConfiguration UI_CONFIGURATION = ConfigFactory.create(UIConfiguration.class);
    public static Platform PLATFORM = UI_CONFIGURATION.executionPlatform().equals("android") ? ANDROID : IOS;
}
