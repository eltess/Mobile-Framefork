package rozetka.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:saucelabs.properties"
})

public interface SauceLabsConfiguration extends Config {

    @Key("appSauceLabs")
    String appSauceLabs();

    @Key("platformName")
    @DefaultValue("Android")
    String platformName();

    @Key("platformVersion")
    @DefaultValue("12.0")
    String platformVersion();

    @Key("deviceName")
    @DefaultValue("Android GoogleAPI Emulator")
    String deviceName();

    @Key("automationName")
    @DefaultValue("UiAutomator2")
    String automationName();

    @Key("SAUCE_USERNAME")
    String sauceUserName();

    @Key("SAUCE_ACCESS_KEY")
    String sauceAccessKey();
}
