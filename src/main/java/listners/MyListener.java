package listners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ITestListener
{
    //public static String testname;
    public void onTestStart(ITestResult result)
    {
        System.out.println("Test cases starting");

    }

    public void onTestSuccess(ITestResult result)
    {
        System.out.println("Test passed "+result.getName());
         //testname= result.getName();
    }

    public void onTestFailure(ITestResult result)
    {
        System.out.println("Test failed "+result.getName());
    }

    public void onTestSkipped(ITestResult result)
    {
        System.out.println("Test skipped "+result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result)
    {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context)
    {
    }

}
