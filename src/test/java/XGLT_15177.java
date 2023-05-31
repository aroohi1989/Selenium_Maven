import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import helper.Utility;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AboutPage;
import pages.LoginPage;
import java.lang.reflect.Method;

import static listners.ExtentManager.extent;

public class XGLT_15177 extends BaseClass
{
    public String testName;
    ExtentTest test = extent.createTest("Test Login to XGL", "This is a test to login to XGL");

    // move to base
    @BeforeMethod
    public void handleTestMethodName(Method method)
    {
         testName = method.getName();
    }

    @Test(priority = 1)
    @Parameters({"username","password","URL","browser"})
    public void LoginXGL(String uname, String pwrd, String URL, String browser)
    {
        logger.info("Test XGLT_15177");
        test.info("Step 1: Navigate to XGL");
         //login to XGL
        BaseClass b= new BaseClass();
        b.setupBrowser(browser,URL);
         LoginPage lp= new LoginPage(driver);
        test.info("Enter username and password");
        lp.LoginToApplication(uname,pwrd);
        lp.addwait();
        Utility ut= new Utility();
        //Verify login
        test.info("Verify user is logged in");
        Assert.assertEquals(driver.getCurrentUrl().contains("MissionControl"),true);
        Reporter.log("Pass: Login Successfull");
        ut.captureScreenshot(driver,testName);
    }


    @Test(priority = 2,dependsOnMethods ="LoginXGL")
    @Parameters({"Version"})
    public void About(String Version)
    {
        test.info("Read and verify the about page information");
        AboutPage ap= new AboutPage(driver);
        ap.read_about(Version);
        System.out.println("Method name is "+testName);
        Utility ut= new Utility();
        ut.captureScreenshot(driver,testName);

    }
}
