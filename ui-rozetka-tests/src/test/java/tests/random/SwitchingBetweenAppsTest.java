package tests.random;

import org.testng.annotations.Test;
import rozetka.util.NativeAction;
import tests.BaseTest;

import static rozetka.config.Config.ACTIVITIES;

public class SwitchingBetweenAppsTest extends BaseTest {

    @Test
    public void switchingBetweenApps() {
        softAssert.assertEquals(ACTIVITIES.appPackage() + NativeAction.getAndroidCurrentAppActivity(),
            ACTIVITIES.appActivity(), "1");
        NativeAction.getAndroidCurrentContexts();

        NativeAction.openApp(ACTIVITIES.chromePackage(), ACTIVITIES.chromeActivity());

        softAssert.assertEquals(NativeAction.getAndroidCurrentAppActivity(),
            ACTIVITIES.chromeActivity(), "2");

        NativeAction.openApp(ACTIVITIES.appPackage(), ACTIVITIES.appActivity());

        softAssert.assertEquals(ACTIVITIES.appPackage() + NativeAction.getAndroidCurrentAppActivity(),
            ACTIVITIES.appActivity(), "3");
        softAssert.assertAll();
    }
}
