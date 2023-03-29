package helper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Utility
{
    public String getCurrentTime()
    {
        String date;
        date= new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.S").format(new Date());
        date=date.replace(":","_");
        return date;
    }

    public void captureScreenshot(WebDriver driver, String testName)
    {
        System.out.println("Starting screenshot");
        try
        {
           // FileUtils.copyFile(SrcFile, DestFile);
           // FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File("./screenshots/Screenshot_"+getCurrentTime()+".png"));

            FileHandler.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File("./screenshots/"+testName+"_"+getCurrentTime()+".png"));
            System.out.println("In screenshot");
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong "+e.getMessage());
        }

    }
}
