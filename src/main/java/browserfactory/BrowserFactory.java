package browserfactory;

import base.BaseClass;
import com.fasterxml.jackson.databind.ser.Serializers;
import dataProvider.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import listners.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.Duration;

public class BrowserFactory extends BaseClass
{


    public static WebDriver getBrowserInstance() {
        return driver;
    }

    public static WebDriver startBrowser(String browserName, String applicationURL)
    {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        if (browserName.contains("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.contains("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.contains("Firefox")) {
            System.setProperty("webdriver.gecko.driver", ConfigReader.getPropertyvalue("GeckoexePath"));
            driver = new FirefoxDriver();
        }
        //Event Listener initialization
        e_driver = new EventFiringWebDriver(driver);
        // Now create object of EventListerHandler to register it with EventFiringWebDriver
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;
        //end of event listener initialization

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(applicationURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        return driver;
    }
}
