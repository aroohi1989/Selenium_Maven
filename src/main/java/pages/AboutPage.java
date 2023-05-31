package pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.support.PageFactory;

public class AboutPage extends BaseClass
{
    public AboutPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
        @FindBy(xpath = "//div[@id='header.menuHelp']")
         WebElement help;
        @FindBy(xpath = "//div[@id='header.subMenuAbout/profile']")
                WebElement profile;
        @FindBy(xpath = "//div[@class='popupWindowBody']/h2")
                WebElement about_version;
        @FindBy(xpath = "//div[@class='popupWindowBody']//table//tr")
                List<WebElement> about_content;
        @FindBy(xpath = "//div[@class='popupWindowButtons buttonsArea']//button[@value='ok']")
                WebElement about_ok;

       public void read_about(String version)
       {
           help.click();
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
           Assert.assertTrue(profile.isDisplayed(),"About is present");
           profile.click();
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
           String ver= about_version.getText();
           Assert.assertEquals(ver,version);
           System.out.println("Version is "+ver);
           //  print about
           List<WebElement> arr= about_content;
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
           about_ok.click();
           Reporter.log("Pass: About information read");
       }


}
