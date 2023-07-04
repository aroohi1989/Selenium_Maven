import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import dataProvider.ConfigReader;
import dataProvider.CustomDataProvider;
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
  //  ExtentTest test = extent.createTest("Test Login to XGL", "This is a test to login to XGL");

    // move to base
    @BeforeMethod
    public void handleTestMethodName(Method method)
    {
         testName = method.getName();
    }

    @Test(priority = 1,dataProvider = "loginDetails",dataProviderClass = CustomDataProvider.class)
    public void LoginXGL(String uname, String pwrd)
    {
        errorLog.error("This is an error message specifically logged to the error log file");
        logger.info("Test XGLT_15177");
         //login to XGL
        BaseClass b= new BaseClass();
        b.setupBrowser();
         LoginPage lp= new LoginPage(driver);
        lp.LoginToApplication(uname,pwrd);
        lp.addwait();

        Assert.assertEquals(driver.getCurrentUrl().contains("MissionControl"),true);
        //Reporter.log("Pass: Login Successfull");
        //ut.captureScreenshot(driver,testName);
    }


    @Test(priority = 2,dependsOnMethods ="LoginXGL")
    //@Parameters({"Version"})
    public void About()
    {
        //test.info("Read and verify the about page information");
        AboutPage ap= new AboutPage(driver);
        ap.read_about(ConfigReader.getProperty("Version"));
        System.out.println("Method name is "+testName);
        Utility ut= new Utility();
        ut.captureScreenshot(driver,testName);

    }
}
