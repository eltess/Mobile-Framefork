package rozetka.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:activities.properties"
})
public interface Activities extends Config {

    @Config.Key("appPackage")
    String appPackage();

    @Config.Key("appActivity")
    String appActivity();

    @Config.Key("chromePackage")
    String chromePackage();

    @Config.Key("chromeActivity")
    String chromeActivity();
}
