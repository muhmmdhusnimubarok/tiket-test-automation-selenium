package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class HelpersFile {

    public final WebDriver driver;
    public final WebDriverWait wait;


    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy EEEE", new Locale("id", "ID"));
    public static final DateTimeFormatter headerFormat = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("id", "ID"));

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

    public void selectSingleDate(LocalDate targetDate) {
        while (true) {
            String headerText = waitForVisible(By.xpath("//div[contains(@class,'CalendarDesktop_month_heading_container')]/h2")).getText().trim();
            LocalDate headerDate = YearMonth.parse(headerText, headerFormat).atDay(1);

            if (headerDate.getYear() == targetDate.getYear() &&
                    headerDate.getMonth() == targetDate.getMonth()) {
                break;
            }
            waitForClickable(By.xpath("//button[@aria-label='Bulan berikutnya']")).click();
            sleep(500);
        }
        String fullDateText = targetDate.format(formatter);
        By dayLocator = By.xpath("//span[@aria-label='" + fullDateText + "']");
        waitForClickable(dayLocator).click();
    }

    public boolean checkPaymentPage(String text) {
        try {
            return wait.until(ExpectedConditions.urlContains(text));
        } catch (Exception e) {
            return false;
        }
    }
}
