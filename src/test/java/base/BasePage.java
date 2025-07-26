package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.WeakHashMap;

public abstract class BasePage {
    protected String baseUrl;
    protected WebDriver driver;

    protected WebDriverWait wait;

    public BasePage(String baseUrl, WebDriver driver) {
        this.baseUrl = baseUrl;
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void inputText(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void clickBtn(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

}
