package pageObjectManager;

import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;

public class PageObjectManager {

    WebDriver driver;

    private static HomePage homePage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;

    }

    public HomePage getHomePage(){
        return homePage = new HomePage(driver);
    }
}
