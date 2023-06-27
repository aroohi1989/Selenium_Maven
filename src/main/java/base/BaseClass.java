package base;

import browserfactory.BrowserFactory;
import dataProvider.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;



public class BaseClass
{
  public static WebDriver driver;
  public static String Network;
  public static String break_format;

    public static final Logger logger = LogManager.getLogger(BaseClass.class);
    protected static org.apache.logging.log4j.Logger log;
    protected static Logger errorLog;

    @BeforeSuite
    public void setupLog4j() throws URISyntaxException{
            Path log4jConfigPath = Paths.get(getClass().getClassLoader().getResource("log4j2.xml").toURI());
            Configurator.initialize("Log4j2", null, log4jConfigPath.toUri());
            log = LogManager.getLogger(getClass());
            errorLog = LogManager.getLogger("LearnMaven");
    }
  //@Parameters({"browser","URL"})
    String browser=ConfigReader.getProperty("browser");
    String url=ConfigReader.getProperty("url");
  public void setupBrowser()
  {

        log.info("Setting up browser");
      System.out.println("LOG:INFO - Setting up browser");
      BrowserFactory bf= new BrowserFactory();
      driver=bf.startBrowser(ConfigReader.getProperty("browser"),ConfigReader.getProperty("url"));
      //return driver;
  }

    //@AfterSuite
    public void closeBrowser()
    {
        log.info("closing up browser");
        driver.quit();

        System.out.println("LOG:INFO - Closing the browser and application");
    }
    @BeforeMethod
    public void name(ITestContext context)
    {
        System.out.println("Before method Test name: " + context.getName());
    }
}
