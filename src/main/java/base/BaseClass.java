package base;

import browserfactory.BrowserFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class BaseClass
{
  public static WebDriver driver;

  @BeforeClass
  @Parameters({"browser","URL"})
  public void setupBrowser(String browser, String URL)
  {

      System.out.println("LOG:INFO - Setting up browser");
      BrowserFactory bf= new BrowserFactory();
      driver=bf.startBrowser(browser,URL);
      //return driver;
  }

    @AfterSuite
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
