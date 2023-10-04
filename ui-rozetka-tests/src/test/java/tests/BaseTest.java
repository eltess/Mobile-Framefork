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
import rozetka.pages.HomePage;
import rozetka.util.DriverManager;
import rozetka.util.NativeAction;

import static rozetka.BasePage.LOGGER;

public class BaseTest {

    protected final BottomBarNavigation bottomBarNavigation;
    protected final HomePage homePage;
    protected SoftAssert softAssert;

    public BaseTest() {
        bottomBarNavigation = new BottomBarNavigation();
        homePage = new HomePage();
    }

    @BeforeClass
    public void before() {
        AppiumDriver appiumDriver = DriverManager.getDriver();
        WebDriverRunner.setWebDriver(appiumDriver);

        Configuration.reportsFolder = "target/test-result/reports";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
            .screenshots(true)
            .savePageSource(true));
        NativeAction.closeAlert();
        // homePage.popup.closeButton.click();
        LOGGER.info("START TEST");
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
