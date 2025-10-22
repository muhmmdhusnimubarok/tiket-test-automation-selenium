package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserDriver {

    private static final ThreadLocal<WebDriver> THREAD_DRIVER = new ThreadLocal<>();

    public static void startBrowser() {
        if (THREAD_DRIVER.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            //headless based on config
            if ("true".equalsIgnoreCase(ConfigReader.get("HEADLESS"))) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
            }
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            THREAD_DRIVER.set(new ChromeDriver(options));
        }
    }

    public static WebDriver getDriver() {
        if (THREAD_DRIVER.get() == null) startBrowser();
        return THREAD_DRIVER.get();
    }

    private static boolean keepBrowserOpen = true;

    public static void quitBrowser() {
        WebDriver driver = THREAD_DRIVER.get();
        if (driver != null && !keepBrowserOpen) {
            try { driver.quit(); } catch (Exception ignored) {}
            THREAD_DRIVER.remove();
        }
    }
}
