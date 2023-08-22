package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import rozetka.component.BottomBarNavigation;
import rozetka.enums.Platform;
import rozetka.listener.DeviceCapabilities;
import rozetka.listener.UiConfiguration;
import rozetka.pages.HomePage;

public class BaseTest {

    protected final BottomBarNavigation bottomBarNavigation;
    protected final HomePage homePage;
    protected SoftAssert softAssert;
    protected static Platform PLATFORM = UiConfiguration.PLATFORM;

    public BaseTest() {
        bottomBarNavigation = new BottomBarNavigation();
        homePage = new HomePage();
    }

    @BeforeClass
    public void before() {
        DeviceCapabilities capabilities = new DeviceCapabilities();
        AppiumDriver appiumDriver = capabilities.driver();
        WebDriverRunner.setWebDriver(appiumDriver);

        Configuration.reportsFolder = "target/test-result/reports";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
        homePage.popup.closeButton.click();
    }

    @BeforeMethod
    public void resetSoftAssert() {
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void after() {
        WebDriverRunner.getWebDriver().quit();
    }
}
