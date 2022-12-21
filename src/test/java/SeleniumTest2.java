import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumTest2
{
    WebDriver driver;
    @Test(priority = 1)
    public void logintoHRM()
    {
        String url= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.tagName("button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Boolean flag=driver.getCurrentUrl().contains("dashboard");
        Assert.assertEquals(flag,true);
    }


    @Test(dependsOnMethods = "logintoHRM",priority = 2)
    public void logout()
    {
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/i")).isDisplayed());
        driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']/i")).click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"),"Logout successfull");
    }

    @AfterClass
    public void close()
    {
        driver.quit();
    }
}
