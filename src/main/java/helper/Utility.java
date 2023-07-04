package helper;

import base.BaseClass;
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
import java.util.Random;

public class Utility
{
    public static String captureScreenshotInBase64(WebDriver driver)
    {
        TakesScreenshot ts=(TakesScreenshot)driver;

        String base64=ts.getScreenshotAs(OutputType.BASE64);

        return base64;


    }
    public synchronized static WebDriver getDriver() {
        return BaseClass.driver;
    }
    public static String getScreenshot() {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);

        try {
            FileUtils.copyFile(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
    public static String getCurrentTime()
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
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong "+e.getMessage());
        }

    }
    public String randomAlphaNumeric(int len)
    {
        String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rand = new Random();
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<len;i++)
        {
            // generate a random index in the range of the characters string
            int index = rand.nextInt(characters.length());
            // extract the character at the generated index and append it to the StringBuilder
            char c = characters.charAt(index);
            builder.append(c);
        }
        String code = builder.toString();
        return code;
    }
}
