package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import utils.ConfigReader;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    @BeforeMethod(alwaysRun = true)
    public void setup() throws Exception{
        ConfigReader config = new ConfigReader();
        //driver = new ChromeDriver();
        //driver.set(new ChromeDriver());
        String executionMode =
                config.getProperty("executionMode");

        if (executionMode.equalsIgnoreCase("remote")) {

            ChromeOptions options =
                    new ChromeOptions();

            driver.set(
                    new RemoteWebDriver(
                            new URL("http://localhost:4444/wd/hub"),
                            options
                    )
            );

        } else {

            driver.set(new ChromeDriver());
        }
        //driver.manage().window().maximize();
        getDriver().manage().window().maximize();
        getDriver().get(config.getProperty("baseUrl"));
        //driver.get("https://www.saucedemo.com/");
        //driver.get(config.getProperty("baseUrl"));
    }

    public WebDriver getDriver() {

        return driver.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
        
    }
}
