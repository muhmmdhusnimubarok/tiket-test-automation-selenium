package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.util.Locale;
import utils.HelpersFile;

public class FlightLocator extends HelpersFile{

    public FlightLocator(WebDriver driver) {
        super(driver);
    }

    public void clickFlightMenu() {
        waitForClickable(By.cssSelector("a[href='/pesawat']")).click();
    }

    public void selectFirstAirportMatch(String text) {
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

        }
    }

    public void setDepartureAndDestination(String departure, String destination) {
        waitForClickable(By.cssSelector("div[data-testid='clickable-departure-input']")).click();
        type(By.cssSelector("input[placeholder='Masukkan nama kota atau bandara.']"), departure);
        selectFirstAirportMatch(departure);
        sleep(1000);
        waitForClickable(By.cssSelector("div[data-testid='clickable-arrival-input']")).click();
        type(By.cssSelector("input[placeholder='Masukkan nama kota atau bandara.']"), destination);
        selectFirstAirportMatch(destination);
    }

    public void selectFlightDateOneWay(String dateText) {
        waitForClickable(By.xpath("//p[normalize-space()='Pergi']")).click();
        sleep(1000);
        LocalDate departureDate = LocalDate.parse(dateText, formatter);
        selectSingleDate(departureDate);
    }

    public void clickRoundTripButton() {
        waitForClickable(By.xpath("//div[contains(@class,'Toggle_toggle__')]")).click();
    }

   public void selectDFlightDateRoundTrip(String departureDateText, String returnDateText) {
        waitForClickable(By.xpath("//p[normalize-space()='Pergi']")).click();
        sleep(1000);
        LocalDate departureDate = LocalDate.parse(departureDateText, formatter);
        LocalDate returnDate = LocalDate.parse(returnDateText, formatter);
        selectSingleDate(departureDate);
        selectSingleDate(returnDate);
   }

   public void selectFlightClass(String flightClass) {
        waitForClickable(By.xpath("//span[contains(text(),'Penumpang')]")).click();
        sleep(1000);
        By classLocator;

        switch (flightClass) {
            case "Ekonomi":
                classLocator = By.xpath("//button[span[normalize-space()='Ekonomi']]");
                break;
            case "Premium Ekonomi":
                classLocator = By.xpath("//button[span[normalize-space()='Premium Ekonomi']]");
                break;
            case "Bisnis":
                classLocator = By.xpath("//button[span[normalize-space()='Bisnis']]");
                break;
            case "First":
                classLocator = By.xpath("//button[span[normalize-space()='First']]");
                break;
            default:
                throw new IllegalArgumentException();
        }
        waitForClickable(classLocator).click();
        waitForClickable(By.xpath("//button[normalize-space()='Selesai']")).click();
   }

   public void selectFlightCard() {
        waitForClickable(By.xpath("//button[normalize-space()='Ayo Cari']")).click();
        sleep(3000);
        waitForClickable(By.xpath("(//div[@data-testid='srp-flight-card'])[1]")).click();
        sleep(2000);
        waitForClickable(By.xpath("(//button[normalize-space()='Pilih'])[1]")).click();
       waitForClickable(By.xpath("//button[@data-testid='popup-button-primary']")).click();
   }

   public void selectFlightCards() {
       waitForClickable(By.xpath("//button[normalize-space()='Ayo Cari']")).click();
       sleep(3000);
       WebElement flightCard = driver.findElement(By.xpath("//div[@data-testid='srp-flight-card']"));
       waitForClickable(By.xpath("(//div[@data-testid='srp-flight-card'])[1]")).click();
       wait.until(ExpectedConditions.stalenessOf(flightCard));
       sleep(3000);
       waitForClickable(By.xpath("(//div[@data-testid='srp-flight-card'])[1]")).click();
       waitForClickable(By.xpath("(//button[normalize-space()='Pilih'])[1]")).click();
       waitForClickable(By.xpath("//button[@data-testid='popup-button-primary']")).click();
   }

   public void detailPemesan() {
       waitForClickable(By.xpath("(//span[normalize-space()='Tuan'])[1]")).click();
       type(By.id("nama-lengkap"), "Muhammad Husni Mubarok");
       type(By.id("nomor-telepon"), "081280135417");
       type(By.id("alamat-email"), "hsnva@gmail.com");
   }

   public void detailPenumpang1() {
       WebElement samaDgnPemesanBtn = driver.findElement(By.xpath("//div[contains(@class,'Toggle_toggle__')]"));
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", samaDgnPemesanBtn);
       sleep(500);
       waitForClickable(By.xpath("//div[contains(@class,'Toggle_toggle__')]")).click();

       //Tanggal Lahir
       if (isVisible(By.id("tanggal-lahir"))) {
           WebElement birthForm1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(@class,'styles_input_inner_wrapper')][.//label[normalize-space()='Tanggal Lahir']])[1]")));
           ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", birthForm1);
           sleep(700);
           birthForm1.click();

           sleep(700);
           selectValue(
                   By.xpath("//button[contains(@class, 'Navigation_month_menu_button__')]"),
                   By.xpath("//button[normalize-space()='Mei']")
           );

           sleep(700);
           selectValue(
                   By.xpath("//button[contains(@class, 'Navigation_year_menu_button__')]"),
                   By.xpath("//button[text()='2002']")
           );

           sleep(700);
           WebElement tanggalLahirContainer1 = driver.findElement(By.xpath("//div[contains(@class, 'DatePickerDesktop_month__')]"));
           WebElement tanggalLahir1 = tanggalLahirContainer1.findElement(By.xpath("//span[normalize-space()='23']"));
           tanggalLahir1.click();

           waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
       }

       //Kewarganegaraan
       if (isVisible(By.xpath("(//input[@id='kewarganegaraan'])[1]"))) {
            selectValue(
                    By.xpath("(//input[@id='kewarganegaraan'])[1]"),
                    By.xpath("//p[normalize-space()='Indonesia']")
            );
       }

       //Nomor Paspor
       if (isVisible(By.xpath("(//input[@id='nomor-paspor'])[1]"))) {
           type(By.xpath("(//input[@id='nomor-paspor'])[1]"), "A1234567");
       }

       //Tanggal Pengeluaran
       if (isVisible(By.xpath("(//input[@id='tanggal-pengeluaran'])[1]"))) {
           WebElement formTglPengeluaran1 = driver.findElement(By.xpath("(//input[@id='tanggal-pengeluaran'])[1]"));
           ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'start'});", formTglPengeluaran1);
           sleep(700);
           formTglPengeluaran1.click();

           sleep(700);
           selectValue(
                   By.xpath("//button[contains(@class, 'Navigation_month_menu_button__')]"),
                   By.xpath("//button[normalize-space()='Mei']")
           );

           sleep(700);
           selectValue(
                   By.xpath("//button[contains(@class, 'Navigation_year_menu_button__')]"),
                   By.xpath("//button[text()='2015']")
           );

           WebElement tglPengeluaranContainer1 = driver.findElement(By.xpath("//div[contains(@class, 'DatePickerDesktop_month__')]"));
           WebElement tglPengeluaran1 = tglPengeluaranContainer1.findElement(By.xpath("//span[normalize-space()='23']"));
           tglPengeluaran1.click();

           waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
       }

       //Negara yang mengeluarkan
       if (isVisible(By.xpath("(//input[@id='negara-wilayah-yang-mengeluarkan'])[1]"))) {
           selectValue(
                   By.xpath("(//input[@id='negara-wilayah-yang-mengeluarkan'])[1]"),
                   By.xpath("//p[normalize-space()='Indonesia']")
           );
       }

       //Tanggal habis berlaku
       if (isVisible(By.xpath("(//input[@id='tanggal-habis-berlaku'])[1]"))) {
           WebElement formTglKadarluwarsa1 = driver.findElement(By.xpath("(//input[@id='tanggal-habis-berlaku'])[1]"));
           ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'start'});", formTglKadarluwarsa1);
           sleep(700);
           formTglKadarluwarsa1.click();

           sleep(700);
           selectValue(
                   By.xpath("//button[contains(@class, 'Navigation_month_menu_button__')]"),
                   By.xpath("//button[normalize-space()='Mei']")
           );

           sleep(700);
           selectValue(
                   By.xpath("//button[contains(@class, 'Navigation_year_menu_button__')]"),
                   By.xpath("//button[text()='2055']")
           );

           WebElement tglKadarluwarsaContainer1 = driver.findElement(By.xpath("//div[contains(@class, 'DatePickerDesktop_month__')]"));
           WebElement tglKadarluwarsa1 = tglKadarluwarsaContainer1.findElement(By.xpath("//span[normalize-space()='23']"));
           tglKadarluwarsa1.click();

           waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
       }
   }

   public void tambahkanAsuransi() {
       WebElement expBtn1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("(button[data-testid='btn-add'])[1]")));
       ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", expBtn1);
       sleep(1500);
       waitForClickable(By.cssSelector("(button[data-testid='btn-add'])[2]")).click();
       waitForClickable(By.cssSelector("(button[data-testid='btn-add'])[3]")).click();
       WebElement expBtn4 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("(button[data-testid='btn-add'])[4]")));
       ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", expBtn4);
       sleep(1500);
       waitForClickable(By.cssSelector("(button[data-testid='btn-add'])[4]")).click();
       waitForClickable(By.cssSelector("(button[data-testid='btn-add'])[5]")).click();
   }

   public void lanjutBayar() {
       WebElement lanjutBayarBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Lanjut Bayar']")));
       ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",lanjutBayarBtn );
       sleep(1000);
       waitForClickable(By.xpath("(//button[normalize-space()='Lanjut Bayar'])[1]")).click();
       sleep(1000);

       if (isVisible(By.xpath("(//button[normalize-space()='Lanjut Bayar'])[2]"))) {
           waitForClickable(By.xpath("(//button[normalize-space()='Lanjut Bayar'])[2]")).click();
           sleep(3000);
       } else {
           waitForClickable(By.xpath("//button[normalize-space()='Lanjut bayar']")).click();
       }


       if (isVisible(By.xpath("//button[normalize-space()='Lanjut ke pembayaran']"))) {
           waitForClickable(By.xpath("//button[normalize-space()='Lanjut ke pembayaran']")).click();
       }
   }

   public void validatePaymentPage() {
        checkPaymentPage("/payment");
   }

}
