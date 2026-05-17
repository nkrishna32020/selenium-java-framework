package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public static String captureScreenshot(
            WebDriver driver,
            String testName
    ) {

        File srcFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File destFile = new File(
                "screenshots/" + testName + ".png"
        );

        try {

            FileUtils.copyFile(srcFile, destFile);

        } catch (IOException e) {

            e.printStackTrace();
        }

        return destFile.getAbsolutePath();
    }
}