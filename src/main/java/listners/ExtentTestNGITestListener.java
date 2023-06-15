package listners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import browserfactory.BrowserFactory;
import helper.Utility;

public class ExtentTestNGITestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(ExtentTestNGITestListener.class);
    ExtentReports extent=ExtentManager.getInstance();

    ThreadLocal<ExtentTest> parentTest=new ThreadLocal<ExtentTest>();


    public void onTestStart(ITestResult result)
    {
        ExtentTest extentTest=extent.createTest(result.getMethod().getMethodName());
        parentTest.set(extentTest);
        logger.info("Test case started: " + result.getName());
    }


    public void onTestSuccess(ITestResult result) {

        WebDriver driver=BrowserFactory.getBrowserInstance();
        String base64=Utility.captureScreenshotInBase64(driver);
        logger.info("Test case passed: " + result.getName());
        parentTest.get().pass("Test Passed",MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
    }


    /*public void onTestFailure(ITestResult result)
    {
        logger.error("Test case failed: " + result.getName());
        WebDriver driver=BrowserFactory.getBrowserInstance();

        String base64=Utility.captureScreenshotInBase64(driver);

        parentTest.get().fail("Test Failed "+result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
    }*/

    public void onTestSkipped(ITestResult result) {
        parentTest.get().skip("Test Skipped "+result.getThrowable().getMessage());
        logger.error("Test case skipped: " + result.getName());

    }


    public  void onFinish(ITestContext context) {
        extent.flush();

    }
}