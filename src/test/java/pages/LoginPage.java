package pages;

import base.BasePage;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    public LoginPage(String baseUrl,WebDriver driver) {
        super(baseUrl,driver);
    }

    /** -------------------------- Web Elements -------------------------- */
    private By TXT_USERNAME = By.id("user-name");
    private By TXT_PASSWORD = By.id("password");
    private By BTN_LOGIN = By.id("login-button");
    private By ERR_MSG = By.cssSelector("[data-test=error]");

    /** -------------------------- Page Methods -------------------------- */
    public void inputUsername(String username) {
        inputText(TXT_USERNAME, username);
    }

    public void inputPassword(String password) {
        inputText(TXT_PASSWORD, password);
    }

    public void clickLoginBtn() {
       clickBtn(BTN_LOGIN);
    }

    public String getErrorMessage() {
        return driver.findElement(ERR_MSG).getText();
    }
}
