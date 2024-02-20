package rozetka.config;

import org.aeonbits.owner.ConfigFactory;

public class Config {

    public static final UIConfiguration UI_CONFIGURATION = ConfigFactory.create(UIConfiguration.class);
    public static final Activities ACTIVITIES = ConfigFactory.create(Activities.class);
}
