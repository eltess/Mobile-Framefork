package tests;

import io.qameta.allure.*;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import rozetka.config.UIConfiguration;

public class MathTest {

    @Owner("Serhiy")
    @Feature("Math operation")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("This test checks 2 + 2")
    @Link(name = "Link", url = "https://github.com/eltess/Mobile-Framefork/tree/master/src/test")

    @Test(enabled = false)
    public void Sum() {
        UIConfiguration cfg = ConfigFactory.create(UIConfiguration.class);
        cfg.executionPlatform();
        var sum = summa();
        Assert.assertEquals(sum, 4, "Expected sum is different from actual");
    }

    @Step("Summation in progress {2 + 2}")
    private int summa() {
        return 2 + 2;
    }
}