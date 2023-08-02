import base.BaseClass;
import dataProvider.ConfigReader;
import dataProvider.CustomDataProvider;
import helper.JavaScriptExecutor;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.AboutPage;
import pages.LoginPage;

public class XGLT_15177_Test extends BaseClass
{
    @Test(priority = 1,enabled = true,dataProvider = "loginDetails",dataProviderClass = CustomDataProvider.class)
    public void LoginXGL(String uname, String pwrd)
    {
        logger.info("Test XGLT_15177_Test");
        BaseClass b= new BaseClass();
        b.setupBrowser();
        LoginPage lp= new LoginPage(driver);
        lp.Login_To_Application(uname,pwrd);
        lp.addwait();
        Assert.assertTrue(driver.getCurrentUrl().contains("MissionControl"),"Login failed");
    }

    @Test(priority = 2,dependsOnMethods ="LoginXGL",enabled = true)
    public void About()
    {
        AboutPage ap= new AboutPage(driver);
        ap.clickhelp();
        Assert.assertTrue(ap.getProfile().isDisplayed(),"About is present");
        ap.clickAbout();
        Assert.assertEquals(ap.getVersion(),ConfigReader.getPropertyvalue("Version"));
        ap.printAboutDetails();

        ap.click_about_Ok();
        Reporter.log("Pass: About information read");
    }
}