import baseClass.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pageObjectManager.PageObjectManager;
import pageObjects.HomePage;
import utilities.ReadConfig;
import utilities.Reporting;
import webDriverManager.WebDriverManager;

public class TC_OMF_PersonalLoanCalcTest {

    static final Logger narrator = LogManager.getLogger(TC_OMF_PersonalLoanCalcTest.class);
    WebDriver driver;
    WebDriverManager webDriverManager;
    ReadConfig readConfig;
    BaseClass baseClass;
    HomePage homePage;
    PageObjectManager pageObjectManager;
    Reporting reporting;

    public TC_OMF_PersonalLoanCalcTest() throws Exception {

        webDriverManager = new WebDriverManager();
        readConfig = new ReadConfig("config");
        driver = webDriverManager.getWebDriver(readConfig.getBrowser());
        baseClass = new BaseClass(driver);
        pageObjectManager = new PageObjectManager(driver);
        homePage = pageObjectManager.getHomePage();
        reporting = new Reporting();
    }

    @Test (priority = 0)
    public void verifyTitle() throws Exception{
        webDriverManager.launchOMF();
        Thread.sleep(2000);

        String expTitle = "Bank and Borrow Solutions | Old Mutual";
        String actTitle = driver.getTitle();
        Assert.assertEquals(expTitle,actTitle);
        baseClass.CaptureScreenshot(driver,"verifyTitle");
    }

    @Test (priority = 1)
    public void navigateToPersonalLoans() throws Exception{
        homePage.hoverOurSolutions();
        homePage.clickPersonalLoans();

        String expTitle = "Personal Loans | Apply Online | Old Mutual";
        String actTitle = driver.getTitle();
        Assert.assertEquals(expTitle,actTitle);
        baseClass.CaptureScreenshot(driver,"navigateToPersonalLoans");
    }

    @Test (priority = 2)
    public void selectAmount() throws Exception {
        homePage.clickCalculate();
        homePage.selectAmount();
        homePage.clickNext();
    }

    @Test (priority = 3)
    public void calculateTerm() {
        homePage.selectTerm();
    }

    @Test (priority = 4)
    public void validateRepaymentAmount() throws Exception {
        homePage.clickCalculateLoan();

        String expRepayment = "R1 656.43 - R1 810.05";
        String actRepayment = driver.findElement(By.xpath("//strong[contains(., 'R1 656.43 - R1 810.05')]")).getText();
        Assert.assertEquals("Verify monthly repayment: ",expRepayment,actRepayment);
        baseClass.CaptureScreenshot(driver,"validateRepaymentAmount");
    }

    @AfterClass
    void tearDown(){
        driver.quit();
    }
}
