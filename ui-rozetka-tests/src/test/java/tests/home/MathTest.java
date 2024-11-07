package tests.home;

import org.testng.annotations.Test;

import static rozetka.config.Config.UI_CONFIGURATION;

public class MathTest {

    @Test()
    public void AAAA()  {

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(UI_CONFIGURATION.getRun());
        System.out.println(UI_CONFIGURATION.getEngine());
        System.out.println(UI_CONFIGURATION.getAuth());

        System.out.println(System.getenv("DB_ENGINE"));
        System.out.println(System.getenv("DISABLE_AUTH"));
        System.out.println(System.getenv("RUN"));
        System.out.println(System.getenv("AAA"));

    }
}
