package pageObjects;

import baseClass.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Set;

public class HomePage {
    WebDriver driver;
    BaseClass baseClass;

    //Constructor to initialize the driver
    public HomePage(WebDriver driver) {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
        baseClass = new BaseClass(driver);
    }

    // locating page objects using the @Findby annotation

    @FindBy(xpath = "//div[2]/om-main-navigation-menu/ul/li[1]/ul/li[3]/a")
    WebElement lnkOurSolutions;

    @FindBy(xpath = "//Div[2]//li//a//span[contains(., 'Personal Loans')][1]")
    WebElement lnkPersonalLoans;

    @FindBy(xpath = "//a//span[contains(., 'CALCULATE')]")
    WebElement btnCalculate;

    @FindBy(xpath = "//*[@id=\"blt9c764616951e6d18\"]/div")
    WebElement formBlock;

    @FindBy(xpath = "//om-form-dropdown-field//div//div//span")
    WebElement drpAmountDropdown;

    @FindBy(xpath = "//ul[@class='dropdown-options-list']//li[contains(., 'R50')]")
    WebElement sltAmountOptions;

    @FindBy(xpath = "//span[contains(., 'Next')]")
    WebElement btnNext;

    @FindBy(xpath = "//*[@id=\"repaymentDuration\"]/div/div/div/om-form-dropdown-field/div/div[1]/span[1]")
    WebElement drpHowLong;

    @FindBy(id="60 Months")
    WebElement sltSixtyMonths;

    @FindBy(xpath = "//button//span[contains(., 'Calculate')]")
    WebElement btnCalculateLoan;

    @FindBy(xpath = "//span[contains(., 'Back')]")
    WebElement btnBack;

    // hover over to avail menu options
    public void hoverOurSolutions() {
        try {
            baseClass.clickObject(lnkOurSolutions);

        } catch (Exception e) {
            System.out.println("Could not click Our Solutions " + e.getStackTrace());
        }
    }

    // Click Personal Loans from the menu options
    public void clickPersonalLoans() {
        try {
            baseClass.clickObject(lnkPersonalLoans);
        } catch (Exception e) {
            System.out.println("Could not open Personal Loans " + e.getStackTrace());
        }
    }

    // Click the Calculate button
    public void clickCalculate() {
        try {
            baseClass.clickObject(btnCalculate);
        } catch (Exception e) {
            System.out.println("Could not click calculate button " + e.getMessage());
        }
    }

    // Select the desired amount from the dropdown
    public void selectAmount() throws Exception {
        Set<String> currentTab = driver.getWindowHandles();

        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
            }
        }
        try {
            baseClass.clickObject(drpAmountDropdown);
            baseClass.clickObject(sltAmountOptions);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    // Click the Next button
    public void clickNext() {
        try {
            baseClass.clickObject(btnNext);
        } catch (Exception e) {
            System.out.println("Could not click the Next button " + e.getStackTrace());
        }
    }

    private void clickTermDropDown(){
        baseClass.clickObject(drpHowLong);
    }

    private void clickSixty(){
        baseClass.clickObject(sltSixtyMonths);
    }

    public void selectTerm(){
        clickTermDropDown();
        clickSixty();
    }


    public void clickCalculateLoan(){
        baseClass.clickObject(btnCalculateLoan);
    }

    public void btnBackIsDisplayed() {
        if (btnBack.isDisplayed()) {
            System.out.println("Back button displayed");
            Assert.assertTrue(true);
        } else {
            System.out.println("Back button not displayed");
            Assert.assertTrue(false);
        }
        /*try {
            baseClass.objectDisplayed(btnBack);
        } catch (Exception e) {
            System.out.println("Could not find the Back button" + e.getMessage());
        }*/
    }
}
