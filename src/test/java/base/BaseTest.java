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
    public void setup() throws Exception {
        ConfigReader config = new ConfigReader();

        String executionMode =
                config.getProperty("executionMode");

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        if (executionMode.equalsIgnoreCase("remote")) {

            driver.set(
                    new RemoteWebDriver(
                            new URL("http://localhost:4444/wd/hub"),
                            options
                    )
            );

        } else {

            driver.set(new ChromeDriver(options));
        }

        getDriver().manage().window().maximize();
        getDriver().get(config.getProperty("baseUrl"));
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
