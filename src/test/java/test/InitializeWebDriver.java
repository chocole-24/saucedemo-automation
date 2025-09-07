package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class InitializeWebDriver {
    public static WebDriver initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--language=en-US");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);

        return driver;
    }
}
