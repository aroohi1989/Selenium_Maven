package base;

import browserfactory.BrowserFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class BaseClass
{
  public static WebDriver driver;
  public static String Network;
  public static String break_format;

    public static final Logger logger = LogManager.getLogger(BaseClass.class);

    @BeforeClass
    public void setupLog4j() {
        // Configure Log4j
        System.out.println("I am in setuplog4j");
        System.setProperty("log4j.configurationFile", "C:\\Users\\dP-PL\\LearnMaven\\LearnMaven\\src\\test\\resources\\log4j2.xml");
    }
  @Parameters({"browser","URL"})
  public void setupBrowser(String browser, String URL)
  {

      System.out.println("LOG:INFO - Setting up browser");
      BrowserFactory bf= new BrowserFactory();
      driver=bf.startBrowser(browser,URL);
      //return driver;
  }

    //@AfterSuite
    public void closeBrowser()
    {
        driver.quit();

        System.out.println("LOG:INFO - Closing the browser and application");
    }
    @BeforeMethod
    public void name(ITestContext context)
    {
        System.out.println("Before method Test name: " + context.getName());
    }
}
