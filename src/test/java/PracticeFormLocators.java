import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PracticeFormLocators extends BasePage{

    public PracticeFormLocators(WebDriver driver) {
        super(driver);
    }

    public WebElement form() {return findElementByXpath("//h5[text()='Forms']");}

    public WebElement practiceForm() {return findElementByXpath("//span[text()='Practice Form']");}

    public WebElement firstName() {return findElementByXpath("//input[@id='firstName']");}

    public WebElement lastName() {return findElementByXpath("//input[@id='lastName']");}

    public WebElement genderMale() {return findElementByXpath("//div[@class='custom-control custom-radio custom-control-inline']");}

    public WebElement userNumber() {return findElementByXpath("//input[@id='userNumber']");}

    public WebElement subMit() {return findElementByXpath("//button[@id='submit']");}

    public WebElement ThanksForSubmittingTheForm() {return findElementByXpath("//div[@class='modal-content']");}

    public WebElement studentRegistrationForm() {return findElementByXpath("//form[@id='userForm']");}

}