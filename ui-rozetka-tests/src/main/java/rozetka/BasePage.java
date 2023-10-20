package rozetka;

import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rozetka.config.UIConfiguration;
import rozetka.enums.Platform;

import static rozetka.enums.Platform.ANDROID;
import static rozetka.enums.Platform.IOS;

public class BasePage {

    public static final Logger LOGGER = LogManager.getRootLogger();
    public static final UIConfiguration UI_CONFIGURATION = ConfigFactory.create(UIConfiguration.class);
    public static final Platform PLATFORM = UI_CONFIGURATION.platformName().equals("android") ? ANDROID : IOS;
}
