package test;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.LoginPage;

import static org.testng.Assert.*;
import static test.InitializeWebDriver.initializeDriver;

public class LoginTest {
    private String username;
    private String password;
    private LoginPage loginPage;
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = initializeDriver();
        String baseUrl = ConfigReader.get("BASE_URL");
        username = ConfigReader.get("USERNAME");
        password = ConfigReader.get("PASSWORD");
        driver.get(baseUrl);
        loginPage = new LoginPage(baseUrl, driver);
    }

    @Test
    public void testValidLogin() {
        loginPage.inputUsername(username);
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();
        // Verify that browser process to Inventory page
        assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    public void testInvalidLogin() {
        loginPage.inputUsername("wrg_user");
        loginPage.inputPassword("wrg_pwd");
        loginPage.clickLoginBtn();
        assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testInvalidUsername() {
        loginPage.inputUsername("wrg_user");
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();
        assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testInvalidPassword() {
        loginPage.inputUsername(username);
        loginPage.inputPassword("wrg_pwd");
        loginPage.clickLoginBtn();

        assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testLockedUser() {
        loginPage.inputUsername(ConfigReader.get("LOCKED_USER"));
        loginPage.inputPassword(password);
        loginPage.clickLoginBtn();

        assertEquals(loginPage.getErrorMessage(),"Epic sadface: Sorry, this user has been locked out.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
