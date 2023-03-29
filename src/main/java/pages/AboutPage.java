package pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.time.Duration;
import java.util.List;

public class AboutPage extends BaseClass
{
    public void AboutPage(WebDriver driver)
    {
        this.driver=driver;
    }

       By help = By.xpath("//div[@id='header.menuHelp']");
       By profile=By.xpath("//div[@id='header.subMenuAbout/profile']");
       By about_popup= By.xpath("//div[@class='popupWindowBody']");
       By about_version=By.xpath("//div[@class='popupWindowBody']/h2");
       By about_content=By.xpath("//div[@class='popupWindowBody']//table//tr");

       By about_ok= By.xpath("//div[@class='popupWindowButtons buttonsArea']//button[@value='ok']");

       public void read_about(String version)
       {
           driver.findElement(help).click();
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
           Assert.assertTrue(driver.findElement(profile).isDisplayed(),"About is present");
           driver.findElement(profile).click();
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
           String ver= driver.findElement(about_version).getText();
           Assert.assertEquals(ver,version);
           System.out.println("Version is "+ver);
           //  print about
           List<WebElement> arr= driver.findElements(about_content);
           for(int i=0;i<arr.size();i++)
           {
               List<WebElement> at=arr.get(i).findElements(By.tagName("td"));
               String about= "";
               for(int j=0;j<at.size();j++)
               {
                   about= about + " " + at.get(j).getText();
               }
               System.out.println(about);
               Reporter.log(about);
           }
           driver.findElement(about_ok).click();
           Reporter.log("Pass: About information read");
       }


}
