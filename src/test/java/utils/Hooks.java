package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.FlightLocator;

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

        }
        BrowserDriver.quitBrowser();
       PAGES.remove();
    }

    public static FlightLocator getFlight() {
        PageObjects pages = PAGES.get();
        if (pages == null) {
            PAGES.set(new PageObjects(BrowserDriver.getDriver()));
            pages = PAGES.get();
        }
        return pages.flight;
    }

    private static class PageObjects {
        final FlightLocator flight;

        PageObjects(org.openqa.selenium.WebDriver driver) {
            this.flight = new FlightLocator(driver);
        }
    }
}
