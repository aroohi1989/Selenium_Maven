import base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import helper.Utility;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.NetworkPage;

import java.lang.reflect.Method;
import java.time.Duration;

import static listners.ExtentManager.extent;

public class Create_Network extends BaseClass
{
    public String testName;
    public String network;
    ExtentTest test = extent.createTest("Test Login to XGL", "This is a test to login to XGL");

    @BeforeMethod
    public void handleTestMethodName(Method method)
    {
        testName = method.getName();
    }

    @Test(priority = 1)
    public void search_Network()
    {
        Boolean b=true;
        Utility ut= new Utility();
        Network=ut.randomAlphaNumeric(2);
        NetworkPage np= new NetworkPage(driver);
        b=np.search_network(Network);
        Assert.assertTrue(b);
        if(b)
        {
            ut.captureScreenshot(driver, testName);
            System.out.println("Can proceed to create a new network "+Network);
            this.Network=Network;
        }
        else
        {
            System.out.println("Need a new network");
        }
    }

    @Test(priority = 2,dependsOnMethods = "search_Network")
    public void create_Network()
    {
        NetworkPage np= new NetworkPage(driver);
        Boolean result=np.create_Network(Network);
        Assert.assertTrue(result);
        System.out.println("Network "+Network+" is created");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
