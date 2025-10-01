package pages;

import io.cucumber.java.nl.Stel;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightLocator {
    private static final Logger log = Logger.getLogger(FlightLocator.class.getName());

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By flightMenu = By.cssSelector("a[href='/pesawat']");
    private final By departureField = By.cssSelector("div[data-testid='clickable-departure-input']");
    private final By destinationField = By.cssSelector("div[data-testid='clickable-arrival-input']");
    private final By searchField = By.cssSelector("input[placeholder='Masukkan nama kota atau bandara.']");
    private final By whatDate = By.xpath("//p[normalize-space()='Pergi']");
    private final By passengerForm = By.xpath("//span[contains(text(),'Penumpang')]");
    private final By quantityButtons = By.xpath("//button[contains(@class,'QuantityEditor_operation_button')]");
    private final By economyClass = By.xpath("//button[span[normalize-space()='Ekonomi']]");
    private final By firstClass = By.xpath("//button[span[normalize-space()='First']]");
    private final By businessClass = By.xpath("//button[span[normalize-space()='Bisnis']]");
    private final By premiumEconomyClass = By.xpath("//button[span[normalize-space()='Premium Ekonomi']]");
    private final By submitFlightDetail = By.xpath("//button[normalize-space()='Selesai']");
    private final By findFlightButton = By.xpath("//button[normalize-space()='Ayo Cari']");

    private static final int IDX_DECREASE_ADULT = 0;
    private static final int IDX_INCREASE_ADULT = 1;
    private static final int IDX_DECREASE_CHILD = 2;
    private static final int IDX_INCREASE_CHILD = 3;
    private static final int IDX_DECREASE_INFANT = 4;
    private static final int IDX_INCREASE_INFANT = 5;

    public FlightLocator(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void sleep(long ms) {
        try { Thread.sleep(ms);
        } catch (InterruptedException Ignored) { Thread.currentThread().interrupt(); }
    }

    public void type(By locator, String text) {
        WebElement el = waitForClickable(locator);
        el.clear();
        el.sendKeys(text);
    }

    public void click(By locator) {
        click(locator);
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

    public void clickFlightMenu() {
        click(flightMenu);
    }

    public void clickDepartureField() {
        click(departureField);
    }

    public void clickSearchField() {
        click(searchField);
    }
}
