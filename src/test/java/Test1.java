import base.BaseClass;
import helper.Utility;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AboutPage;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class Test1 extends BaseClass
{
    public String testName;
    @BeforeMethod
    public void handleTestMethodName(Method method)
    {
         testName = method.getName();
    }

    @Test(priority = 1)
    @Parameters({"username","password","URL","browser"})
    public void LoginXGL(String uname, String pwrd , String URL, String browser)
    {

         //login to XGL
         LoginPage lp= new LoginPage();
         lp.LoginPage(driver);
        System.out.println("Login to XGL");
        lp.LoginToApplication(uname,pwrd);
        lp.addwait();
        System.out.println("Goto screenshot now");
        System.out.println("Method name is "+testName);
        Utility ut= new Utility();

        //Verify login
        //driver=lp.LoginPage(driver);
        System.out.println(driver.getCurrentUrl().contains("MissionControl"));
        Assert.assertEquals(driver.getCurrentUrl().contains("MissionControl"),true);
        Reporter.log("Pass: Login Successfull");
        ut.captureScreenshot(driver,testName);
    }


    @Test(priority = 2,dependsOnMethods ="LoginXGL")
    @Parameters({"Version"})
    public void About(String Version)
    {
        AboutPage ap= new AboutPage();
        ap.AboutPage(driver);
        ap.read_about(Version);
        System.out.println("Method name is "+testName);
        Utility ut= new Utility();
        ut.captureScreenshot(driver,testName);

    }
}
