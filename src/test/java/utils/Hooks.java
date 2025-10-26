package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.FlightLocator;
import pages.HotelLocator;

import java.time.Duration;

public class Hooks {

   private static final ThreadLocal<PageObjects> PAGES = new ThreadLocal<>();

    @Before
    public void setup() {
        BrowserDriver.startBrowser();

        try {
            int timeoutSec = Integer.parseInt(utils.ConfigReader.get("TIMEOUT"));
            BrowserDriver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutSec));
        } catch (Exception ignored) {

        }
        PAGES.set(new PageObjects(BrowserDriver.getDriver()));
    }

    @After
    public void teardown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                TakesScreenshot ts = (TakesScreenshot) BrowserDriver.getDriver();
                byte[] png = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(png, "image/png", "screenshot");
            }
        } catch (Exception ignored) {

        } finally {
            BrowserDriver.quitBrowser();
            PAGES.remove();
        }
    }

    private static PageObjects getPages() {
        PageObjects pages = PAGES.get();
        if (pages == null) {
            PAGES.set(new PageObjects(BrowserDriver.getDriver()));
            pages = PAGES.get();
        }
        return pages;
    }

    public static FlightLocator getFlight() {
        return getPages().flight;
    }

    public static HotelLocator getHotel() {
        return getPages().hotel;
    }

    public static class PageObjects {
        public final FlightLocator flight;
        public final HotelLocator hotel;

        public PageObjects(WebDriver driver) {
            this.flight = new FlightLocator(driver);
            this.hotel  = new HotelLocator(driver);
        }
    }
}
