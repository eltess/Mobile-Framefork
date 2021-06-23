import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoqaCaseTwo extends TestInit{

    PracticeFormLocators locators;

    @Test
    public void demoqaDotCom(){
        locators = new PracticeFormLocators(driver);
        openUrl("https://demoqa.com");
        locators.form().click();
        locators.practiceForm().click();
        scrollDown();
        locators.subMit().click();
        Assert.assertTrue(locators.studentRegistrationForm().getAttribute("class").contains("was-validated"));
    }
}