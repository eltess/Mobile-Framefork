import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

public class TestInit {

    public WebDriver driver;
    ChromeOptions options = new ChromeOptions();

    boolean headless = false;
    @BeforeSuite
    public void setUp() {

        if (isOSMac()){
            setProperty("src/drivers/chromedriver_91_mac");
        }else if (isOSWindows()){
            setProperty("src/drivers/chromedriver_90_windows.exe");
        }else {
            setProperty("src/drivers/chromedriver_90_linux");
        }

        driver = new ChromeDriver(options);

        if(headless) {
            driver.manage().window().setSize(new Dimension(1920, 1200));
        } else {
            driver.manage().window().maximize();
        }
    }
    private void setProperty(String path) {
        System.setProperty("webdriver.chrome.driver", path);
        if (headless){
            options.addArguments("--headless");
        }
    }
    private boolean isOSWindows() {
        return getOSName().contains("win");
    }

    private boolean isOSMac() {
        return getOSName().contains("mac");
    }

    private String getOSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    public void openUrl(String site) {
        driver.get(site);
    }

    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    @AfterMethod
    public void afterTest() {
        driver.quit();
    }
}