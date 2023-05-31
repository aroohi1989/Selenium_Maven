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

    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='login.login']")
    WebElement user;
    @FindBy(xpath = "//input[@id='login.password']")
            WebElement passwd;
    @FindBy(xpath = "//button[@id='login.loginButton']")
            WebElement login;

   // By user= By.xpath("//input[@id='login.login']");
    //By passwd= By.xpath("//input[@id='login.password']");
   // By login= By.xpath("//button[@id='login.loginButton']");
    By missioncontrol = By.xpath("//div[@id='header.missionControl']");



    public void LoginToApplication(String username, String password)
    {
        System.out.println("Login with user "+username);
        System.out.println("Login with password  "+password);
        user.sendKeys(username);
        passwd.sendKeys(password);
        login.click();
    }

    public void addwait()
    {
        //driver = super.driver;
        wait= new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(missioncontrol));
    }
}

