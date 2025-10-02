package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightLocator {
    private static final Logger log = Logger.getLogger(FlightLocator.class.getName());

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By flightMenuLocator = By.cssSelector("a[href='/pesawat']");
    private final By departureFieldLocator = By.cssSelector("div[data-testid='clickable-departure-input']");
    private final By destinationFieldLocator = By.cssSelector("div[data-testid='clickable-arrival-input']");
    private final By searchFieldLocator = By.cssSelector("input[placeholder='Masukkan nama kota atau bandara.']");
    private final By flightCalendarLocator = By.xpath("//p[normalize-space()='Pergi']");
    private final By flightCalendarMonthLocator = By.xpath("//div[contains(@class,'CalendarDesktop_month_heading_container')]/h2");
    private final By flightCalendarNextButtonLocator = By.xpath("//button[@aria-label='Bulan berikutnya']");
    private final By passengerFormLocator = By.xpath("//span[contains(text(),'Penumpang')]");
    private final By quantityButtonLocator = By.xpath("//button[contains(@class,'QuantityEditor_operation_button')]");
    private final By economyClassLocator = By.xpath("//button[span[normalize-space()='Ekonomi']]");
    private final By firstClassLocator = By.xpath("//button[span[normalize-space()='First']]");
    private final By businessClassLocator = By.xpath("//button[span[normalize-space()='Bisnis']]");
    private final By premiumEconomyClassLocator = By.xpath("//button[span[normalize-space()='Premium Ekonomi']]");
    private final By submitPassengerDetailLocator = By.xpath("//button[normalize-space()='Selesai']");
    private final By findFlightButtonLocator = By.xpath("//button[normalize-space()='Ayo Cari']");
    private final By flightCardLocator = By.xpath("//div[@data-testid='srp-flight-card']");
    private final By fareCardLocator = By.xpath("//button[normalize-space()='Pilih']");
    private final By nggakDuluDehLocator = By.xpath("//button[@data-testid='popup-button-secondary']");
    private final By extraProtectionButton = By.cssSelector("button[data-testid='btn-add']");
    private final By radioBtnTuanLocator = By.xpath("//span[normalize-space()='Tuan']");
    private final By radioBtnNyonyaLocator = By.xpath("//span[normalize-space()='Nyonya']");
    private final By radioBtnNonaLocator = By.xpath("//span[normalize-space()='Nona']");
    private final By nameFieldPemesanLocator = By.id("nama-lengkap");
    private final By tlpFieldPemesanLocator = By.id("nomor-telepon");
    private final By emailFieldLocator = By.id("alamat-email");
    private final By namaDepanFieldPenumpangLocator = By.xpath("//input[@id='nama-depan--nama-tengah']");
    private final By namaBelakangFieldPenumpangLocator = By.xpath("//input[@id='nama-keluarganama-belakang']");
    private final By birthFormLocator = By.id("tanggal-lahir");
    private final By samaDenganPemesanBtnLocator = By.cssSelector("input[type='checkbox']");

    private static final int IDX_DECREASE_ADULT = 0;
    private static final int IDX_INCREASE_ADULT = 1;
    private static final int IDX_DECREASE_CHILD = 2;
    private static final int IDX_INCREASE_CHILD = 3;
    private static final int IDX_DECREASE_INFANT = 4;
    private static final int IDX_INCREASE_INFANT = 5;

    private static final int IDX_FLIGHT_CARD_1 = 0;
    private static final int IDX_FLIGHT_CARD_2 = 1;
    private static final int IDX_FLIGHT_CARD_3 = 2;
    private static final int IDX_FLIGHT_CARD_4 = 3;
    private static final int IDX_FLIGHT_CARD_5 = 4;
    private static final int IDX_FLIGHT_CARD_6 = 5;

    private static final int IDX_EXP_BUTTON_1 = 0;
    private static final int IDX_EXP_BUTTON_2 = 1;
    private static final int IDX_EXP_BUTTON_3 = 2;
    private static final int IDX_EXP_BUTTON_4 = 3;

    private static final int TUAN_RADIOBTN_1 = 0;
    private static final int TUAN_RADIOBTN_2 = 1;
    private static final int TUAN_RADIOBTN_3 = 2;
    private static final int TUAN_RADIOBTN_4 = 3;
    private static final int TUAN_RADIOBTN_5 = 4;
    private static final int TUAN_RADIOBTN_6 = 5;
    private static final int TUAN_RADIOBTN_7 = 6;
    private static final int TUAN_RADIOBTN_8 = 7;

    private static final int NYONYA_RADIOBTN_1 = 0;
    private static final int NYONYA_RADIOBTN_2 = 1;
    private static final int NYONYA_RADIOBTN_3 = 2;
    private static final int NYONYA_RADIOBTN_4 = 3;
    private static final int NYONYA_RADIOBTN_5 = 4;
    private static final int NYONYA_RADIOBTN_6 = 5;
    private static final int NYONYA_RADIOBTN_7 = 6;
    private static final int NYONYA_RADIOBTN_8 = 7;

    private static final int NONA_RADIOBTN_1 = 0;
    private static final int NONA_RADIOBTN_2 = 1;
    private static final int NONA_RADIOBTN_3 = 2;
    private static final int NONA_RADIOBTN_4 = 3;
    private static final int NONA_RADIOBTN_5 = 4;
    private static final int NONA_RADIOBTN_6 = 5;
    private static final int NONA_RADIOBTN_7 = 6;
    private static final int NONA_RADIOBTN_8 = 7;



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

    public void addPassengerAdult() { clickQuantityButton(IDX_INCREASE_ADULT); }
    public void addPassengerChild() { clickQuantityButton(IDX_INCREASE_CHILD); }
    public void addPassengerInfant() { clickQuantityButton(IDX_INCREASE_INFANT); }
    public void decreasePassengerAdult() { clickQuantityButton(IDX_DECREASE_ADULT); }
    public void decreasePassengerChild() { clickQuantityButton(IDX_DECREASE_CHILD); }
    public void decreasePassengerInfant() { clickQuantityButton(IDX_DECREASE_INFANT); }

    public void flightCard1() { clickFlightCard(IDX_FLIGHT_CARD_1); }
    public void flightCard2() { clickFlightCard(IDX_FLIGHT_CARD_2); }
    public void flightCard3() { clickFlightCard(IDX_FLIGHT_CARD_3); }
    public void flightCard4() { clickFlightCard(IDX_FLIGHT_CARD_4); }
    public void flightCard5() { clickFlightCard(IDX_FLIGHT_CARD_5); }
    public void flightCard6() { clickFlightCard(IDX_FLIGHT_CARD_6); }

    public void expButton1() { clickExpButton(IDX_EXP_BUTTON_1); }
    public void expButton2() { clickExpButton(IDX_EXP_BUTTON_2); }
    public void expButton3() { clickExpButton(IDX_EXP_BUTTON_3); }
    public void expButton4() { clickExpButton(IDX_EXP_BUTTON_4); }

    public void radioBtnTuan1() { clickRadioBtnTuan(TUAN_RADIOBTN_1); }
    public void radioBtnTuan2() { clickRadioBtnTuan(TUAN_RADIOBTN_2); }
    public void radioBtnTuan3() { clickRadioBtnTuan(TUAN_RADIOBTN_3); }
    public void radioBtnTuan4() { clickRadioBtnTuan(TUAN_RADIOBTN_4); }
    public void radioBtnTuan5() { clickRadioBtnTuan(TUAN_RADIOBTN_5); }
    public void radioBtnTuan6() { clickRadioBtnTuan(TUAN_RADIOBTN_6); }
    public void radioBtnTuan7() { clickRadioBtnTuan(TUAN_RADIOBTN_7); }
    public void radioBtnTuan8() { clickRadioBtnTuan(TUAN_RADIOBTN_8); }

    public void radioBtnNyonya1() { clickRadioBtnNyonya(NYONYA_RADIOBTN_1); }
    public void radioBtnNyonya2() { clickRadioBtnNyonya(NYONYA_RADIOBTN_2); }
    public void radioBtnNyonya3() { clickRadioBtnNyonya(NYONYA_RADIOBTN_3); }
    public void radioBtnNyonya4() { clickRadioBtnNyonya(NYONYA_RADIOBTN_4); }
    public void radioBtnNyonya5() { clickRadioBtnNyonya(NYONYA_RADIOBTN_5); }
    public void radioBtnNyonya6() { clickRadioBtnNyonya(NYONYA_RADIOBTN_6); }
    public void radioBtnNyonya7() { clickRadioBtnNyonya(NYONYA_RADIOBTN_7); }
    public void radioBtnNyonya8() { clickRadioBtnNyonya(NYONYA_RADIOBTN_8); }

    public void radioBtnNona1() { clickRadioBtnNona(NONA_RADIOBTN_1); }
    public void radioBtnNona2() { clickRadioBtnNona(NONA_RADIOBTN_2); }
    public void radioBtnNona3() { clickRadioBtnNona(NONA_RADIOBTN_3); }
    public void radioBtnNona4() { clickRadioBtnNona(NONA_RADIOBTN_4); }
    public void radioBtnNona5() { clickRadioBtnNona(NONA_RADIOBTN_5); }
    public void radioBtnNona6() { clickRadioBtnNona(NONA_RADIOBTN_6); }
    public void radioBtnNona7() { clickRadioBtnNona(NONA_RADIOBTN_7); }
    public void radioBtnNona8() { clickRadioBtnNona(NONA_RADIOBTN_8); }


    public void clickFlightMenu() {
        waitForClickable(flightMenuLocator).click();
    }

    public void clickDestinationField() {
        waitForClickable(destinationFieldLocator).click();
    }

    public void clickDepartureField() {
        waitForClickable(departureFieldLocator).click();
    }

    public void clickFlightCalendar() {
        waitForClickable(flightCalendarLocator).click();
    }

    public void clickPassengerForm() {
        waitForClickable(passengerFormLocator).click();
    }

    public void clickNameFieldPemesan() {
        waitForClickable(nameFieldPemesanLocator).click();
    }

    public void clickEmailFieldPemesan() {
        waitForClickable(emailFieldLocator).click();
    }

    public void selectFareCard() {
        waitForClickable(fareCardLocator).click();
    }

    public void clickNggaDulu() {
        waitForClickable(nggakDuluDehLocator).click();
    }

    public void namaPemesan(String nama) {
        type(nameFieldPemesanLocator, nama);
    }

    public void noTlpPemesan(String noTlp) {
        type(tlpFieldPemesanLocator, noTlp);
    }

    public void emailPemesan(String email) {
        type(emailFieldLocator, email);
    }

    public void clickSamaDgnPemesan() {
        waitForClickable(samaDenganPemesanBtnLocator).click();
    }

    public void clicknamaDepanFieldPenumpang(int index) {
        List<WebElement> namaDepanFieldPenumpangList = driver.findElements(namaDepanFieldPenumpangLocator);
        if (namaDepanFieldPenumpangList.size() > index) {
            try {
                namaDepanFieldPenumpangList.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    public void clickQuantityButton(int index) {
        List<WebElement> quantityButtons = driver.findElements(quantityButtonLocator);
        if (quantityButtons.size() > index) {
            try {
                quantityButtons.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    public void clickFlightCard(int index) {
        List<WebElement> flightCards = driver.findElements(flightCardLocator);
        if (flightCards.size() > index) {
            try {
                flightCards.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    public void clickExpButton(int index) {
        List<WebElement> expButtons = driver.findElements(extraProtectionButton);
        if (expButtons.size() > index) {
            try {
                expButtons.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    public void clickRadioBtnTuan(int index) {
        List<WebElement> tuanButtons = driver.findElements(radioBtnTuanLocator);
        if (tuanButtons.size() > index) {
            try {
                tuanButtons.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    public void clickRadioBtnNyonya(int index) {
        List<WebElement> tuanButtons = driver.findElements(radioBtnNyonyaLocator);
        if (tuanButtons.size() > index) {
            try {
                tuanButtons.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    public void clickRadioBtnNona(int index) {
        List<WebElement> tuanButtons = driver.findElements(radioBtnNonaLocator);
        if (tuanButtons.size() > index) {
            try {
                tuanButtons.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }


    private void selectFirstAirportMatch(String text) {
        String lower = text.toLowerCase(Locale.ROOT).replace("\"","\\\"");
        By candidate = By.xpath("//ul//li//*[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" + lower + "')]");
        try {
            waitForClickable(candidate).click();
            return;
        } catch (Exception ignored) {

        }

        try {
            By generic = By.xpath("//*[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" + lower + "')]");
            waitForClickable(generic).click();
            return;
        } catch (Exception e) {
            log.log(Level.WARNING, "Tidak menemukan hasil pencarian otomatis untuk: {0}", text);
        }
    }

    public void setDeparture(String cityOrAirport) {
        clickDepartureField();
        waitForClickable(searchFieldLocator);
        type(searchFieldLocator, cityOrAirport);
        selectFirstAirportMatch(cityOrAirport);
    }

    public void setDestination(String cityOrAirport) {
        clickDestinationField();
        waitForClickable(searchFieldLocator);
        type(searchFieldLocator, cityOrAirport);
        selectFirstAirportMatch(cityOrAirport);
    }

    public void selectFlightDate(String dateText) {
        clickFlightCalendar();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy EEEE", new Locale("id", "ID"));
        LocalDate flightTargetDate = LocalDate.parse(dateText, formatter);

        while (true) {
            String headerText = waitForVisible(flightCalendarMonthLocator).getText().trim();
            DateTimeFormatter headerFormat = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("id", "ID"));
            LocalDate flightheaderDate = YearMonth.parse(headerText, headerFormat).atDay(1);

            if (flightheaderDate.getYear() == flightTargetDate.getYear() &&
                    flightheaderDate.getMonth() == flightTargetDate.getMonth()) {
                break;
            }
            waitForClickable(flightCalendarNextButtonLocator).click();
        }
        String fullDateText = flightTargetDate.format(formatter);
        By flightDayLocator = By.xpath("//span[@aria-label='" + fullDateText + "']");
        waitForClickable(flightDayLocator).click();
    }

    public void selectFlightCardNoInsurance() {
        waitForClickable(findFlightButtonLocator).click();
        sleep(3000);
        flightCard1();
        selectFareCard();
        clickNggaDulu();
    }

    public void detailPemesan() {
        radioBtnTuan1();
        namaPemesan("Muhammad Husni Mubarok");
        noTlpPemesan("081280135417");
        emailPemesan("hsnva@gmail.com");
    }
}
