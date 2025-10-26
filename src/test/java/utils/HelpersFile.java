package utils;

import io.cucumber.java.an.E;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Locale;


public class HelpersFile {

    public final WebDriver driver;
    public final WebDriverWait wait;


    public HelpersFile(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void sleep(long ms) {
        try { Thread.sleep(ms);
        } catch (InterruptedException Ignored) { Thread.currentThread().interrupt(); }
    }

    public void type(By locator, String text) {
        WebElement el = waitForClickable(locator);
        el.clear();
        el.sendKeys(text);
    }

    public void selectValue(By locator, By value) {
        driver.findElement(locator).click();
        driver.findElement(value).click();
    }

    public void switchToNewTab() {
        String currentTab = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> driver.getWindowHandles().size() > 1);
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentTab)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    public String getText(By locator) {
        return waitForVisible(locator).getText();
    }

    public boolean isVisible(By locator) {
        try {
            return waitForVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void scrollIntoView(WebElement el) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        } catch (Exception ignored) {}
    }

    public WebElement waitUntilVisible(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean waitForInvisibility(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean checkPaymentPage(String text) {
        try {
            return wait.until(ExpectedConditions.urlContains(text));
        } catch (Exception e) {
            return false;
        }
    }

    public void validatePaymentPage() {
        checkPaymentPage("/payment");
    }
}
