package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    //WebDriver driver;

    By username = By.id("user-name");

    By password = By.id("password");

    By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {

        //this.driver = driver;
        super(driver);
    }

    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginButton);
        //driver.findElement(username).sendKeys(user);
        //driver.findElement(password).sendKeys(pass);
        //driver.findElement(loginButton).click();
    }
}