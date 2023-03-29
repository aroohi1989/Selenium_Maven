package pages;

import base.BaseClass;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BaseClass
{
    WebDriverWait wait;

    public void LoginPage(WebDriver driver)
    {
        this.driver=driver;
    }

    By user= By.xpath("//input[@id='login.login']");
    By passwd= By.xpath("//input[@id='login.password']");
    By login= By.xpath("//button[@id='login.loginButton']");

    By missioncontrol = By.xpath("//div[@id='header.missionControl']");

    public void LoginToApplication(String username, String password)
    {
        System.out.println("Login with user "+username);
        driver.findElement(user).sendKeys(username);
        driver.findElement(passwd).sendKeys(password);
        driver.findElement(login).click();
    }

    public void addwait()
    {
        driver = super.driver;
        wait= new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(missioncontrol));
    }
}

