package rozetka.util;

import rozetka.enums.Platform;

import static rozetka.enums.Platform.ANDROID;
import static rozetka.enums.Platform.IOS;
import static rozetka.util.ReadProperties.readProperties;

public class UiConfiguration {

    public static Platform PLATFORM = readProperties().getProperty("platform").equals("android") ? ANDROID : IOS;
}
