package helper;


import org.openqa.selenium.StaleElementReferenceException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class ExceptionHandling
{
    public static void handleNoSuchElementException(NoSuchElementException e) {

        System.out.println("NoSuchElementException occurred: " + e.getMessage());
    }
    public static void handleStaleElementReferenceException(StaleElementReferenceException e) {

        System.out.println("StaleElementReferenceException occurred: " + e.getMessage());
    }
    public static void handleNullPointerException(NullPointerException e) {

        System.out.println("NullPointerException occurred: " + e.getMessage());
    }
    public static void handleArithmeticException(ArithmeticException e) {

        System.out.println("ArithmeticException occurred: " + e.getMessage());
    }
    public static void handleIOException(IOException e) {

        System.out.println("IOException occurred: " + e.getMessage());
    }
    public static void handleAWTException(AWTException e) {

        System.out.println("AWTException occurred: " + e.getMessage());
    }
    public static void handleFileNotFoundException(FileNotFoundException e) {

        System.out.println("FileNotFoundException occurred: " + e.getMessage());
    }
}
