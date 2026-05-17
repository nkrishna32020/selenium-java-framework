package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import base.BaseTest;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utils.ExtentManager;

public class TestListener implements ITestListener {
    ExtentReports extent = ExtentManager.getInstance();

    ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("STARTED: "
                + result.getName());

        ExtentTest extentTest =
                extent.createTest(result.getName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("PASSED: "
                + result.getName());

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("FAILED: "
                + result.getName());

        test.get().fail(result.getThrowable());

        WebDriver driver =
                ((BaseTest) result.getInstance())
                        .getDriver();

        String screenshotPath =
                ScreenshotUtils.captureScreenshot(
                        driver,
                        result.getName()
                );

        try {

            test.get().addScreenCaptureFromPath(
                    screenshotPath
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {

        extent.flush();
    }
}