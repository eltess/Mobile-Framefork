package rozetka.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    public static Properties readProperties() {
        Properties property = new Properties();
        try {
            FileInputStream fileLink = new FileInputStream("src/test/resources/appium.properties");
            property.load(fileLink);
        } catch (IOException e) {
            System.err.println("%s".formatted(e));
        }
        return property;
    }
}
