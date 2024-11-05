package rozetka.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:appium.properties"
})

public interface UIConfiguration extends Config {

    @Key("TEST_PLATFORM")
    @DefaultValue("saucelabs")
    String executionPlatform();

    @Key("platformName")
    @DefaultValue("android")
    String platformName();

    @Key("deviceName")
    @DefaultValue("emulator-5554")
    String deviceName();

    @Key("language")
    @DefaultValue("english")
    String language();

    @Key("appLink")
    String appLink();

    @Key("RUN")
    String getRun();
}
