package browserfactory;

import base.BaseClass;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\dP-PL\\LearnMaven\\LearnMaven\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(applicationURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}
