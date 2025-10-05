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
    private final By roundTripBtnLocator = By.cssSelector("div.Toggle_knob__PZRcL");
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
    private final By lindungiPenerbanganMuLocator = By.xpath("//button[@data-testid='popup-button-primary']");
    private final By extraProtectionButtonLocator = By.cssSelector("button[data-testid='btn-add']");
    private final By radioBtnTuanLocator = By.xpath("//span[normalize-space()='Tuan']");
    private final By radioBtnNyonyaLocator = By.xpath("//span[normalize-space()='Nyonya']");
    private final By radioBtnNonaLocator = By.xpath("//span[normalize-space()='Nona']");
    private final By nameFieldPemesanLocator = By.id("nama-lengkap");
    private final By tlpFieldPemesanLocator = By.id("nomor-telepon");
    private final By emailFieldLocator = By.id("alamat-email");
    private final By namaDepanFieldPenumpangLocator = By.xpath("//input[@id='nama-depan--nama-tengah']");
    private final By namaBelakangFieldPenumpangLocator = By.xpath("//input[@id='nama-keluarganama-belakang']");
    private final By birthFormLocator = By.id("tanggal-lahir");
    private final By samaDenganPemesanBtnLocator = By.cssSelector("div.Toggle_toggle__EuMnT");
    private final By lanjutBayarBtnLocator = By.xpath("//button[normalize-space()='Lanjut Bayar']");
    private final By bcaVirtualAccountLocator = By.xpath("//span[normalize-space()='BCA Virtual Account']");
    private final By monthsMenuLocator = By.cssSelector(".Navigation_month_menu_button__AWr8E");
    private final By yearsMenuLocator = By.cssSelector(".Navigation_year_menu_button__hHa3K");
    private final By simpanBtnLocator = By.xpath("//button[normalize-space()='Simpan']");
    private final By kewarganegaraanFormLocator = By.cssSelector("button.styles_clickable_icon__Gfbs_");
    private final By negaraPilihanLocator = By.xpath("//p[normalize-space(text())='Indonesia']");
    private final By popupLanjutKePembayaranLocator = By.xpath("//div[contains(@class, 'Dialog_pop_up')]");
    private final By lanjutKePembayaranBtnLocator = By.xpath("//button[normalize-space()='Lanjut ke pembayaran']");


    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy EEEE", new Locale("id", "ID"));
    private static final DateTimeFormatter headerFormat = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("id", "ID"));


    private static final int DECREASE_ADULT = 0;
    private static final int INCREASE_ADULT = 1;
    private static final int DECREASE_CHILD = 2;
    private static final int INCREASE_CHILD = 3;
    private static final int DECREASE_INFANT = 4;
    private static final int INCREASE_INFANT = 5;

    private static final int FLIGHT_CARD_1 = 0;
    private static final int FLIGHT_CARD_2 = 1;
    private static final int FLIGHT_CARD_3 = 2;
    private static final int FLIGHT_CARD_4 = 3;
    private static final int FLIGHT_CARD_5 = 4;
    private static final int FLIGHT_CARD_6 = 5;

    private static final int EXP_BUTTON_1 = 0;
    private static final int EXP_BUTTON_2 = 1;
    private static final int EXP_BUTTON_3 = 2;
    private static final int EXP_BUTTON_4 = 3;
    private static final int EXP_BUTTON_5 = 4;

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

    private static final int LANJUT_BAYARBTN_1 = 0;
    private static final int LANJUT_BAYARBTN_2 = 1;




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

    private void type(By locator, String text) {
        WebElement el = waitForClickable(locator);
        el.clear();
        el.sendKeys(text);
    }

    private String getText(By locator) {
        return waitForVisible(locator).getText();
    }

    private boolean isVisible(By locator) {
        try {
            return waitForVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", element);
    }




    private void addPassengerAdult() { clickQuantityButton(INCREASE_ADULT); }
    private void addPassengerChild() { clickQuantityButton(INCREASE_CHILD); }
    private void addPassengerInfant() { clickQuantityButton(INCREASE_INFANT); }
    private void decreasePassengerAdult() { clickQuantityButton(DECREASE_ADULT); }
    private void decreasePassengerChild() { clickQuantityButton(DECREASE_CHILD); }
    private void decreasePassengerInfant() { clickQuantityButton(DECREASE_INFANT); }

    private void flightCard1() { clickFlightCard(FLIGHT_CARD_1); }
    private void flightCard2() { clickFlightCard(FLIGHT_CARD_2); }
    private void flightCard3() { clickFlightCard(FLIGHT_CARD_3); }
    private void flightCard4() { clickFlightCard(FLIGHT_CARD_4); }
    private void flightCard5() { clickFlightCard(FLIGHT_CARD_5); }
    private void flightCard6() { clickFlightCard(FLIGHT_CARD_6); }

    private void expButton1() { clickExpButton(EXP_BUTTON_1); }
    private void expButton2() { clickExpButton(EXP_BUTTON_2); }
    private void expButton3() { clickExpButton(EXP_BUTTON_3); }
    private void expButton4() { clickExpButton(EXP_BUTTON_4); }
    private void expButton5() { clickExpButton(EXP_BUTTON_5); }


    private void radioBtnTuan1() { clickRadioBtnTuan(TUAN_RADIOBTN_1); }
    private void radioBtnTuan2() { clickRadioBtnTuan(TUAN_RADIOBTN_2); }
    private void radioBtnTuan3() { clickRadioBtnTuan(TUAN_RADIOBTN_3); }
    private void radioBtnTuan4() { clickRadioBtnTuan(TUAN_RADIOBTN_4); }
    private void radioBtnTuan5() { clickRadioBtnTuan(TUAN_RADIOBTN_5); }
    private void radioBtnTuan6() { clickRadioBtnTuan(TUAN_RADIOBTN_6); }
    private void radioBtnTuan7() { clickRadioBtnTuan(TUAN_RADIOBTN_7); }
    private void radioBtnTuan8() { clickRadioBtnTuan(TUAN_RADIOBTN_8); }

    private void radioBtnNyonya1() { clickRadioBtnNyonya(NYONYA_RADIOBTN_1); }
    private void radioBtnNyonya2() { clickRadioBtnNyonya(NYONYA_RADIOBTN_2); }
    private void radioBtnNyonya3() { clickRadioBtnNyonya(NYONYA_RADIOBTN_3); }
    private void radioBtnNyonya4() { clickRadioBtnNyonya(NYONYA_RADIOBTN_4); }
    private void radioBtnNyonya5() { clickRadioBtnNyonya(NYONYA_RADIOBTN_5); }
    private void radioBtnNyonya6() { clickRadioBtnNyonya(NYONYA_RADIOBTN_6); }
    private void radioBtnNyonya7() { clickRadioBtnNyonya(NYONYA_RADIOBTN_7); }
    private void radioBtnNyonya8() { clickRadioBtnNyonya(NYONYA_RADIOBTN_8); }

    private void radioBtnNona1() { clickRadioBtnNona(NONA_RADIOBTN_1); }
    private void radioBtnNona2() { clickRadioBtnNona(NONA_RADIOBTN_2); }
    private void radioBtnNona3() { clickRadioBtnNona(NONA_RADIOBTN_3); }
    private void radioBtnNona4() { clickRadioBtnNona(NONA_RADIOBTN_4); }
    private void radioBtnNona5() { clickRadioBtnNona(NONA_RADIOBTN_5); }
    private void radioBtnNona6() { clickRadioBtnNona(NONA_RADIOBTN_6); }
    private void radioBtnNona7() { clickRadioBtnNona(NONA_RADIOBTN_7); }
    private void radioBtnNona8() { clickRadioBtnNona(NONA_RADIOBTN_8); }

    private void expBtn1() { clickExpButton(EXP_BUTTON_1); }
    private void expBtn2() { clickExpButton(EXP_BUTTON_2); }
    private void expBtn3() { clickExpButton(EXP_BUTTON_3); }
    private void expBtn4() { clickExpButton(EXP_BUTTON_4); }
    private void expBtn5() { clickExpButton(EXP_BUTTON_5); }





    public void clickFlightMenu() {
        waitForClickable(flightMenuLocator).click();
    }

    private void clickDestinationField() {
        waitForClickable(destinationFieldLocator).click();
    }

    private void clickDepartureField() {
        waitForClickable(departureFieldLocator).click();
    }

    private void clickFlightCalendar() {
        waitForClickable(flightCalendarLocator).click();
    }

    private void clickPassengerForm() {
        waitForClickable(passengerFormLocator).click();
    }

    private void clickNameFieldPemesan() {
        waitForClickable(nameFieldPemesanLocator).click();
    }

    private void clickEmailFieldPemesan() {
        waitForClickable(emailFieldLocator).click();
    }

    private void selectFareCard() {
        waitForClickable(fareCardLocator).click();
    }

    private void clickSimpanBtn() {
        waitForClickable(simpanBtnLocator).click();
        sleep(1000);
    }

    private void clickNggaDulu() {
        waitForClickable(nggakDuluDehLocator).click();
    }

    private void namaPemesan(String nama) {
        type(nameFieldPemesanLocator, nama);
    }

    private void noTlpPemesan(String noTlp) {
        type(tlpFieldPemesanLocator, noTlp);
    }

    private void emailPemesan(String email) {
        type(emailFieldLocator, email);
    }


    private void scrollSamaDgnPemesan() {
        WebElement samaDenganPemesanBtn =  wait.until(ExpectedConditions.presenceOfElementLocated(samaDenganPemesanBtnLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", samaDenganPemesanBtn);
        sleep(500);
    }

    private void clickbirthForm1() {
        List<WebElement> birthFormList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(birthFormLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", birthFormList.get(0));
        sleep(500);
        birthFormList.get(0).click();
    }

    private void clickSamaDgnPemesanBtn() {
        waitForClickable(samaDenganPemesanBtnLocator).click();
    }


    private void clickBulanLahir1() {
        waitForClickable(monthsMenuLocator).click();
        WebElement bulanLahirContainer1 = driver.findElement(By.cssSelector("section.month-year-menus_menu__HPgiS"));
        WebElement bulanLahir1 = bulanLahirContainer1.findElement(By.xpath("//button[normalize-space()='Mei']"));
        bulanLahir1.click();
    }

    private void clickTahunLahir1() {
        waitForClickable(yearsMenuLocator).click();
        WebElement tahunLahirContainer1 = driver.findElement(By.cssSelector("section.month-year-menus_menu__HPgiS"));
        WebElement tahunLahir1 = tahunLahirContainer1.findElement(By.xpath("//button[text()='2002']"));
        tahunLahir1.click();
    }

    private void clickTanggalLahir1() {
        WebElement tanggalLahirContainer1 = driver.findElement(By.cssSelector("section.DatePickerDesktop_content_container__icOYA"));
        WebElement tanggalLahir1 = tanggalLahirContainer1.findElement(By.xpath("//span[normalize-space()='23']"));
        tanggalLahir1.click();
    }

    private void scrollAsuransiBtn1() {
        List<WebElement> expBtnList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(extraProtectionButtonLocator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", expBtnList.get(0));
        sleep(1500);
    }

    private void scrollAsuransiBtn4() {
        List<WebElement> expBtnList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(extraProtectionButtonLocator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", expBtnList.get(3));
        sleep(1500);
    }

    private void clicknamaDepanFieldPenumpang(int index) {
        List<WebElement> namaDepanFieldPenumpangList = driver.findElements(namaDepanFieldPenumpangLocator);
        if (namaDepanFieldPenumpangList.size() > index) {
            try {
                namaDepanFieldPenumpangList.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    private void clickQuantityButton(int index) {
        List<WebElement> quantityButtons = driver.findElements(quantityButtonLocator);
        if (quantityButtons.size() > index) {
            try {
                quantityButtons.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    private void clickFlightCard(int index) {
        List<WebElement> flightCards = driver.findElements(flightCardLocator);
        if (flightCards.size() > index) {
            try {
                flightCards.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    private void clickExpButton(int index) {
        List<WebElement> expButtons = driver.findElements(extraProtectionButtonLocator);
        if (expButtons.size() > index) {
            try {
                expButtons.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    private void clickRadioBtnNyonya(int index) {
        List<WebElement> tuanButtons = driver.findElements(radioBtnNyonyaLocator);
        if (tuanButtons.size() > index) {
            try {
                tuanButtons.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    private void clickRadioBtnTuan(int index) {
        List<WebElement> tuanButtons = driver.findElements(radioBtnTuanLocator);
        if (tuanButtons.size() > index) {
            try {
                tuanButtons.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    private void clickRadioBtnNona(int index) {
        List<WebElement> tuanButtons = driver.findElements(radioBtnNonaLocator);
        if (tuanButtons.size() > index) {
            try {
                tuanButtons.get(index).click();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    private void scrollLanjutBayarBtn() {
        WebElement lanjutBayarBtn = wait.until(ExpectedConditions.presenceOfElementLocated(lanjutBayarBtnLocator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",lanjutBayarBtn );
        sleep(1000);

    }

    private void clickLanjutBayarBtn() {
        WebElement lanjutBayarBtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Lanjut Bayar'])[1]")));
        lanjutBayarBtn1.click();
        sleep(1000);

        WebElement lanjutBayarBtn2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Lanjut Bayar'])[2]")));
        lanjutBayarBtn2.click();
        sleep(3000);
    }

    private void clickLanjutKePembayaranBtn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(popupLanjutKePembayaranLocator));
        WebElement lanjutKePembayaranBtn = wait.until(ExpectedConditions.elementToBeClickable(lanjutKePembayaranBtnLocator));
        lanjutKePembayaranBtn.click();
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

    private void selectSingleDate(LocalDate targetDate) {
        while (true) {
            String headerText = waitForVisible(flightCalendarMonthLocator).getText().trim();
            LocalDate headerDate = YearMonth.parse(headerText, headerFormat).atDay(1);

            if (headerDate.getYear() == targetDate.getYear() &&
                headerDate.getMonth() == targetDate.getMonth()) {
                break;
            }
            waitForClickable(flightCalendarNextButtonLocator).click();
            sleep(500);
        }
        String fullDateText = targetDate.format(formatter);
        By dayLocator = By.xpath("//span[@aria-label='" + fullDateText + "']");
        waitForClickable(dayLocator).click();
    }

    private boolean cekPaymentUrl() {
        String currentUrl = driver.getCurrentUrl();
        boolean paymentUrl = currentUrl.contains("/payment");
        return paymentUrl;
    }


    public void pilihKewarganegaraan1() {
        List<WebElement> kewarganegaraanList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(kewarganegaraanFormLocator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", kewarganegaraanList.get(0));
        kewarganegaraanList.get(2).click();
        sleep(500);
        waitForClickable(negaraPilihanLocator).click();
    }

    public void setTanggalLahir1() {
        clickbirthForm1();
        sleep(1000);
        clickBulanLahir1();
        clickTahunLahir1();
        clickTanggalLahir1();
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

    public void selectFlightDateOneWay(String dateText) {
        clickFlightCalendar();
        sleep(1000);

        LocalDate departureDate = LocalDate.parse(dateText, formatter);
        selectSingleDate(departureDate);
    }

    public void clickRoundTripButton() {
        waitForClickable(roundTripBtnLocator).click();
    }

   public void selectDFlightDateRoundTrip(String departureDateText, String returnDateText) {
        clickFlightCalendar();
        sleep(1000);
        LocalDate departureDate = LocalDate.parse(departureDateText, formatter);
        LocalDate returnDate = LocalDate.parse(returnDateText, formatter);
        selectSingleDate(departureDate);
        selectSingleDate(returnDate);
   }

    public void selectFlightCard() {
        waitForClickable(findFlightButtonLocator).click();
        sleep(3000);
        flightCard1();
        selectFareCard();
    }

    public void selectFlightCards() {
        waitForClickable(findFlightButtonLocator).click();
        sleep(3000);
        WebElement flightCard = driver.findElement(flightCardLocator);
        flightCard1();
        wait.until(ExpectedConditions.stalenessOf(flightCard));
        flightCard1();
        selectFareCard();
    }

    public void rejectFlightProtection() {
        clickNggaDulu();
    }

    public void detailPemesan() {
        radioBtnTuan1();
        namaPemesan("Muhammad Husni Mubarok");
        noTlpPemesan("081280135417");
        emailPemesan("hsnva@gmail.com");
    }

    public void detailPenumpang1() {
        scrollSamaDgnPemesan();
        clickSamaDgnPemesanBtn();
        setTanggalLahir1();
        clickSimpanBtn();
        pilihKewarganegaraan1();
    }

    public void batalkanAsuransi() {
        scrollAsuransiBtn();
        expBtn1();
    }

    public void lanjutBayar() {
        scrollLanjutBayarBtn();
        clickLanjutBayarBtn();
    }

    public void lanjutKePembayaran() {
        clickLanjutKePembayaranBtn();
    }

    public void validatePaymentPage() {
        isVisible(bcaVirtualAccountLocator);
    }

}
