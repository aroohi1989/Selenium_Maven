package pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BaseClass
{
    WebDriverWait wait;
    @FindBy(xpath = "//input[@id='login.login']")
    WebElement user;
    @FindBy(xpath = "//input[@id='login.password']")
    WebElement passwd;
    @FindBy(xpath = "//button[@id='login.loginButton']")
    WebElement login;
    public By getMissionControl()
    {
        return By.xpath("//div[@id='header.missionControl']");
    }
    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void setUserName(String szusername){
        user.sendKeys(szusername);
    }
    public void setPassword(String szpassword){
        passwd.sendKeys(szpassword);
    }
    public void clickLogin(){
        login.click();
    }

    public void Login_To_Application(String username, String password)
    {
     this.setUserName(username);
     this.setPassword(password);
     this.clickLogin();
    }

    public void addwait()
    {
        wait= new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.getMissionControl()));
    }
}

