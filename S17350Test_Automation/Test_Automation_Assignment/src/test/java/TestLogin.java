import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.example.Common.Constants;


public class TestLogin {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(Constants.CHROME_ARGUMENT);
        driver = new ChromeDriver(chromeOptions);
    }

    @AfterMethod
    public void cleanup() {
        driver.quit();
    }

    @Test
    public void TestValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.setUsername("admin");
        loginPage.setPassword("manager");
        loginPage.clickLoginButton();
    }

    @Test
    public void TestIncorrectUserName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.setUsername("IncorrectUser");
        loginPage.setPassword("manager");
        loginPage.clickLoginButton();
    }

    @Test
    public void TestIncorrectPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.setUsername("admin");
        loginPage.setPassword("IncorrectPassword");
        loginPage.clickLoginButton();
    }

    @Test
    public void TestEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.setUsername("");
        loginPage.setPassword("");
        loginPage.clickLoginButton();

    }
    @Test
    public void TestInvalidCredential() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.setUsername("IncorrectUser");
        loginPage.setPassword("IncorrectPassword");
        loginPage.clickLoginButton();

    }



    @Test
    public void TestAccessToReportsWithoutLogin() {
        ReportsPage reportsPage = new ReportsPage(driver);
        reportsPage.open();

    }



    @Test
    public void ViewreportsDashboard() {
        LoginPage loginPage = new LoginPage(driver);
        ReportsPage reportsPage = new ReportsPage(driver);

        loginPage.open();
        loginPage.setUsername("admin");
        loginPage.setPassword("manager");
        loginPage.clickLoginButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/user/submit_tt.do"));

        reportsPage.clickReportsContainer();
    }
}