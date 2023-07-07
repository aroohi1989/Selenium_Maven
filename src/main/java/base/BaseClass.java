package base;

import browserfactory.BrowserFactory;
import dataProvider.ConfigReader;
import helper.File_Archieve;
import listners.WebEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
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
    public  static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;

    @BeforeSuite
    public void setupLog4j() throws URISyntaxException{
            Path log4jConfigPath = Paths.get(getClass().getClassLoader().getResource("log4j2.xml").toURI());
            Configurator.initialize("Log4j2", null, log4jConfigPath.toUri());
            log = LogManager.getLogger(getClass());
            errorLog = LogManager.getLogger("LearnMaven");
    }
    @BeforeSuite
    public void archive_old()
    {
        File_Archieve fa=new File_Archieve();
        fa.create_archive("reports","Archive/old_Reports/Reports.zip");
        fa.create_archive("screenshots","Archive/old_screenshots/Screenshots.zip");
        fa.deleteFilesFromFolder("reports");
        fa.deleteFilesFromFolder("screenshots");
    }

  public void setupBrowser()
  {
      log.info("Setting up browser");
      BrowserFactory bf= new BrowserFactory();
      driver=bf.startBrowser(ConfigReader.getProperty("browser"),ConfigReader.getProperty("url"));
  }

    //@AfterSuite
    public void closeBrowser()
    {
        log.info("closing up browser");
        driver.quit();
    }
    @BeforeMethod
    public void name(ITestContext context)
    {
        System.out.println("Before method Test name: " + context.getName());
    }
}
