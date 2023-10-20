package rozetka.config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:reportportal.properties",
})

public interface RPConfiguration extends Config {
    @Key("rp.api.key")
    String apiKey();
}
