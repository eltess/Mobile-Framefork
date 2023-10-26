package tests.home;

import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MathTest {

    @Owner("Serhiy")
    @Feature("Math operation")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("This test checks 2 + 2")
    @Link(name = "Link", url = "https://github.com/eltess/Mobile-Framefork/tree/master/src/test")

    @Attributes(attributes = {@Attribute(key = "key", value = "value")})
    @Test()
    public void Sum() throws IOException {

        FileInputStream fileLink = new FileInputStream("src/test/resources/reportportal.properties");
        Properties prop = new Properties();
        prop.load(fileLink);
        System.out.println(prop.getProperty("rp.attributes"));

        var sum = summa();
        Assert.assertEquals(sum, 4, "Expected sum is different from actual");
    }

    @Step("Summation in progress {2 + 2}")
    private int summa() {
        return 2 + 2;
    }
}
