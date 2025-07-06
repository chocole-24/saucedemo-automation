package test;

import base.BaseTest;
import config.ConfigReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.*;

public class LoginPageTest extends BaseTest {
    private String username;
    private String password;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        initializeDriver();
        String baseUrl = ConfigReader.get("baseUrl");
        username = ConfigReader.get("username");
        password = ConfigReader.get("password");
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        loginPage.login(username, password);

        // Verify that browser process to Inventory page
        assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    public void testInvalidLogin() {
        loginPage.login("wrg_user", "wrg_pwd");

        assertEquals(loginPage.getErrorMessage().getText(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testInvalidUsername() {
        loginPage.login("wrg_user", password);

        assertEquals(loginPage.getErrorMessage().getText(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testInvalidPassword() {
        loginPage.login(username, "wrg_pwd");

        assertEquals(loginPage.getErrorMessage().getText(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testLockedUser() {
        loginPage.login(ConfigReader.get("lockedUser"), password);

        assertEquals(loginPage.getErrorMessage().getText(),"Epic sadface: Sorry, this user has been locked out.");
    }
}
