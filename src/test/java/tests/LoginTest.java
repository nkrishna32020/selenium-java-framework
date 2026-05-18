package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import org.testng.annotations.DataProvider;
import dataproviders.LoginDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(LoginTest.class);
    @Test(groups = {"smoke"},
            dataProvider = "loginData",
            dataProviderClass = LoginDataProvider.class
    )
    public void testLogin(String username, String password) {
       LoginPage loginPage = new LoginPage(getDriver());
       logger.info("Login test started");
       loginPage.login(username, password);
        logger.info("Login successful");
       InventoryPage inventoryPage = new InventoryPage(getDriver());
       inventoryPage.openCart();
       Assert.assertTrue(getDriver().getCurrentUrl().contains("cart"));
        //System.out.println(driver.getCurrentUrl());
        //Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test(groups = {"smoke"},
            retryAnalyzer = retry.RetryAnalyzer.class
    )
    public void secondLoginTest() {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.login("standard_user", "secret_sauce");

        System.out.println("Second Login Test");
        //Assert.assertTrue(false);
    }

}
