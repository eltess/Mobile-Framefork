import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDemoqaCaseOne extends TestInit{

    PracticeFormLocators locators;

    @Test
    public void demoqaDotCom(){
        locators = new PracticeFormLocators(driver);
        openUrl("https://demoqa.com");
        locators.form().click();
        locators.practiceForm().click();
        fillingOutTheForm();
        scrollDown();
        locators.subMit().click();
        Assert.assertTrue(locators.ThanksForSubmittingTheForm().isDisplayed());
    }

    private void fillingOutTheForm() {
        locators.firstName().sendKeys("Provectus");
        locators.lastName().sendKeys("ProvectusPetrovich");
        locators.genderMale().click();
        locators.userNumber().sendKeys("0681234455");
    }
}