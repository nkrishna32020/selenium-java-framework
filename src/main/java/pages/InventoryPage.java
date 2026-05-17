package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


public class InventoryPage extends BasePage {
    //WebDriver driver;
    By cart = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver){
        //this.driver = driver;
        super(driver);
    }

    public void openCart(){
        //driver.findElement(cart).click();
        click(cart);
    }
}
