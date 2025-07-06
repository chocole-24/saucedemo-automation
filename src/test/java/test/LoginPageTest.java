package test;

import base.BaseTest;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.LoginPage;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
public class LoginPageTest extends BaseTest {
    private String baseUrl = ConfigReader.get("baseUrl");
    private String username = ConfigReader.get("username");
    private String password = ConfigReader.get("password");

    @Test
    public void testValidLogin() {
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        // Verify that browser process to Inventory page
        assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    public void testInValidLogin() {
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrg_user", "wrg_pwd");

        WebElement error = driver.findElement(By.cssSelector("[data-test=error]"));
        assertTrue(error.getText().contains("Username and password do not match"));
    }

    @Test
    public void testInValidUsername() {
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrg_user", password);

        WebElement error = driver.findElement(By.cssSelector("[data-test=error]"));
        assertTrue(error.getText().contains("Username and password do not match"));
    }

    @Test
    public void testInValidPassword() {
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, "wrg_pwd");

        WebElement error = driver.findElement(By.cssSelector("[data-test=error]"));
        assertTrue(error.getText().contains("Username and password do not match"));
    }

    @Test
    public void testLockedUser() {
        driver.get(baseUrl);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("lockedUser"), password);

        WebElement error = driver.findElement(By.cssSelector("[data-test=error]"));
        assertTrue(error.getText().contains("Sorry, this user has been locked out"));
    }


}
