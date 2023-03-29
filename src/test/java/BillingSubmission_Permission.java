import base.BaseClass;
import helper.Read_write_Excel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class BillingSubmission_Permission
{
    public String testName;
    @BeforeMethod
    public void handleTestMethodName(Method method)
    {
        testName = method.getName();
    }
    //Reconciliation Pre-Billing Processing
    @Test(priority = 1, enabled = false)
    public void getLoginCred()
    {
        Read_write_Excel rd= new Read_write_Excel();
        try {
            rd.read_users();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Issue in loading the excel to read creds");
        }

    }
    @Test(priority = 2)
    @Parameters
    public void test_billing_permission()
    {
        Read_write_Excel rd= new Read_write_Excel();
        try {
            rd.read_DBI_Permission();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            System.out.println("Issue in loading the excel to read permissions");
        }
    }

    //View Billing Submission List


    //Review Billing Period List
}
