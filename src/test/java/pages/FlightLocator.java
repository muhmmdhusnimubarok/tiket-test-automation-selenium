package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import utils.HelpersFile;

public class FlightLocator extends HelpersFile{

    public FlightLocator(WebDriver driver) {
        super(driver);
    }

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy EEEE", new Locale("id", "ID"));
    public static final DateTimeFormatter headerFormat = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("id", "ID"));

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

    public void clickFlightMenu() {
        waitForClickable(By.cssSelector("a[href='/pesawat']")).click();
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

    public void setPassangersAndFlightClass(String flightClass) {
        waitForClickable(By.xpath("//span[contains(text(),'Penumpang')]")).click();
        sleep(700);
        waitForClickable(By.xpath("(//button[contains(@class,'QuantityEditor_operation_button__')])[4]")).click();
        waitForClickable(By.xpath("(//button[contains(@class,'QuantityEditor_operation_button__')])[6]")).click();

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

    public void detailPenumpang2() {
        if (isVisible(By.xpath("(//label[normalize-space()='Nama Lengkap']/preceding-sibling::input)[2]"))) {
            WebElement namaLengkapForm2 = driver.findElement(By.xpath("(//label[normalize-space()='Nama Lengkap']/preceding-sibling::input)[2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", namaLengkapForm2);
            sleep(500);
            type(By.xpath("(//label[normalize-space()='Nama Lengkap']/preceding-sibling::input)[2]"), "Robert Baratheon");
            waitForClickable(By.xpath("(//span[normalize-space()='Tuan'])[3]")).click();
        } else {
            waitForClickable(By.xpath("(//span[normalize-space()='Tuan'])[3]")).click();
            WebElement namaDepanForm2 = driver.findElement(By.xpath("(//label[normalize-space()='Nama Depan & Nama Tengah']/preceding-sibling::input)[2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", namaDepanForm2);
            sleep(500);
            type(By.xpath("(//label[normalize-space()='Nama Depan & Nama Tengah']/preceding-sibling::input)[2]"), "Robert");
            type(By.xpath("(//label[normalize-space()='Nama Keluarga/Nama Belakang']/preceding-sibling::input)[2]"), "Baratheon");
        }



        //Tanggal Lahir
        if (isVisible(By.id("tanggal-lahir"))) {
            WebElement birthForm2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(@class,'styles_input_inner_wrapper')][.//label[normalize-space()='Tanggal Lahir']])[2]")));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", birthForm2);
            sleep(700);
            birthForm2.click();

            sleep(700);
            selectValue(
                    By.xpath("//button[contains(@class, 'Navigation_month_menu_button__')]"),
                    By.xpath("//button[normalize-space()='Mei']")
            );

            sleep(700);
            selectValue(
                    By.xpath("//button[contains(@class, 'Navigation_year_menu_button__')]"),
                    By.xpath("//button[text()='2018']")
            );

            sleep(700);
            WebElement tanggalLahirContainer1 = driver.findElement(By.xpath("//div[contains(@class, 'DatePickerDesktop_month__')]"));
            WebElement tanggalLahir1 = tanggalLahirContainer1.findElement(By.xpath("//span[normalize-space()='23']"));
            tanggalLahir1.click();

            waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
        }

        //Kewarganegaraan
        if (isVisible(By.xpath("(//input[@id='kewarganegaraan'])[2]"))) {
            WebElement kewarganegaraanForm2 = driver.findElement(By.xpath("(//input[@id='kewarganegaraan'])[2]"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", kewarganegaraanForm2);
            selectValue(
                    By.xpath("(//input[@id='kewarganegaraan'])[2]"),
                    By.xpath("//p[normalize-space()='Indonesia']")
            );
        }

        //Nomor Paspor
        if (isVisible(By.xpath("(//input[@id='nomor-paspor'])[2]"))) {
            WebElement nomorPasporForm2 = driver.findElement(By.xpath("(//input[@id='nomor-paspor'])[2]"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", nomorPasporForm2);
            type(By.xpath("(//input[@id='nomor-paspor'])[2]"), "A1234674");
        }

        //Tanggal Pengeluaran
        if (isVisible(By.xpath("(//input[@id='tanggal-pengeluaran'])[2]"))) {
            WebElement formTglPengeluaran2 = driver.findElement(By.xpath("(//input[@id='tanggal-pengeluaran'])[2]"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'start'});", formTglPengeluaran2);
            sleep(700);
            formTglPengeluaran2.click();

            sleep(700);
            selectValue(
                    By.xpath("//button[contains(@class, 'Navigation_month_menu_button__')]"),
                    By.xpath("//button[normalize-space()='Mei']")
            );

            sleep(700);
            selectValue(
                    By.xpath("//button[contains(@class, 'Navigation_year_menu_button__')]"),
                    By.xpath("//button[text()='2021']")
            );

            WebElement tglPengeluaranContainer1 = driver.findElement(By.xpath("//div[contains(@class, 'DatePickerDesktop_month__')]"));
            WebElement tglPengeluaran1 = tglPengeluaranContainer1.findElement(By.xpath("//span[normalize-space()='23']"));
            tglPengeluaran1.click();

            waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
        }

        //Negara yang mengeluarkan
        if (isVisible(By.xpath("(//input[@id='negara-wilayah-yang-mengeluarkan'])[2]"))) {
            selectValue(
                    By.xpath("(//input[@id='negara-wilayah-yang-mengeluarkan'])[2]"),
                    By.xpath("//p[normalize-space()='Indonesia']")
            );
        }

        //Tanggal habis berlaku
        if (isVisible(By.xpath("(//input[@id='tanggal-habis-berlaku'])[2]"))) {
            WebElement formTglKadarluwarsa2 = driver.findElement(By.xpath("(//input[@id='tanggal-habis-berlaku'])[2]"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'start'});", formTglKadarluwarsa2);
            sleep(700);
            formTglKadarluwarsa2.click();

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

    public void detailPenumpang3() {
        if (isVisible(By.xpath("(//label[normalize-space()='Nama Lengkap']/preceding-sibling::input)[3]"))) {
            WebElement namaLengkapForm3 = driver.findElement(By.xpath("(//label[normalize-space()='Nama Lengkap']/preceding-sibling::input)[3]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", namaLengkapForm3);
            sleep(500);
            type(By.xpath("(//label[normalize-space()='Nama Lengkap']/preceding-sibling::input)[3]"), "Ned Stark");
            waitForClickable(By.xpath("(//span[normalize-space()='Tuan'])[4]")).click();
        } else {
            waitForClickable(By.xpath("(//span[normalize-space()='Tuan'])[4]")).click();
            WebElement namaDepanForm3 = driver.findElement(By.xpath("(//label[normalize-space()='Nama Depan & Nama Tengah']/preceding-sibling::input)[3]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", namaDepanForm3);
            sleep(500);
            type(By.xpath("(//label[normalize-space()='Nama Depan & Nama Tengah']/preceding-sibling::input)[3]"), "Ned");
            type(By.xpath("(//label[normalize-space()='Nama Keluarga/Nama Belakang']/preceding-sibling::input)[3]"), "Stark");
        }

        //Tanggal Lahir
        if (isVisible(By.id("tanggal-lahir"))) {
            WebElement birthForm3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(@class,'styles_input_inner_wrapper')][.//label[normalize-space()='Tanggal Lahir']])[3]")));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", birthForm3);
            sleep(700);
            birthForm3.click();

            sleep(700);
            selectValue(
                    By.xpath("//button[contains(@class, 'Navigation_month_menu_button__')]"),
                    By.xpath("//button[normalize-space()='Mei']")
            );

            sleep(700);
            selectValue(
                    By.xpath("//button[contains(@class, 'Navigation_year_menu_button__')]"),
                    By.xpath("//button[text()='2024']")
            );

            sleep(700);
            WebElement tanggalLahirContainer1 = driver.findElement(By.xpath("//div[contains(@class, 'DatePickerDesktop_month__')]"));
            WebElement tanggalLahir1 = tanggalLahirContainer1.findElement(By.xpath("//span[normalize-space()='23']"));
            tanggalLahir1.click();

            waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
        }

        //Kewarganegaraan
        if (isVisible(By.xpath("(//input[@id='kewarganegaraan'])[3]"))) {
            WebElement kewarganegaraanForm3 = driver.findElement(By.xpath("(//input[@id='kewarganegaraan'])[3]"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", kewarganegaraanForm3);
            selectValue(
                    By.xpath("(//input[@id='kewarganegaraan'])[3]"),
                    By.xpath("//p[normalize-space()='Indonesia']")
            );
        }

        //Nomor Paspor
        if (isVisible(By.xpath("(//input[@id='nomor-paspor'])[3]"))) {
            type(By.xpath("(//input[@id='nomor-paspor'])[3]"), "A1237165");
        }

        //Tanggal Pengeluaran
        if (isVisible(By.xpath("(//input[@id='tanggal-pengeluaran'])[3]"))) {
            WebElement formTglPengeluaran3 = driver.findElement(By.xpath("(//input[@id='tanggal-pengeluaran'])[3]"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'start'});", formTglPengeluaran3);
            sleep(700);
            formTglPengeluaran3.click();

            sleep(700);
            selectValue(
                    By.xpath("//button[contains(@class, 'Navigation_month_menu_button__')]"),
                    By.xpath("//button[normalize-space()='Mei']")
            );

            sleep(700);
            selectValue(
                    By.xpath("//button[contains(@class, 'Navigation_year_menu_button__')]"),
                    By.xpath("//button[text()='2025']")
            );

            WebElement tglPengeluaranContainer1 = driver.findElement(By.xpath("//div[contains(@class, 'DatePickerDesktop_month__')]"));
            WebElement tglPengeluaran1 = tglPengeluaranContainer1.findElement(By.xpath("//span[normalize-space()='23']"));
            tglPengeluaran1.click();

            waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
        }

        //Negara yang mengeluarkan
        if (isVisible(By.xpath("(//input[@id='negara-wilayah-yang-mengeluarkan'])[3]"))) {
            selectValue(
                    By.xpath("(//input[@id='negara-wilayah-yang-mengeluarkan'])[3]"),
                    By.xpath("//p[normalize-space()='Indonesia']")
            );
        }

        //Tanggal habis berlaku
        if (isVisible(By.xpath("(//input[@id='tanggal-habis-berlaku'])[3]"))) {
            WebElement formTglKadarluwarsa1 = driver.findElement(By.xpath("(//input[@id='tanggal-habis-berlaku'])[3]"));
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

   public void tambahkanBagasiSolo() {
       WebElement tmbhBagasiBtn1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@data-testid='add'])[1]")));
       ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'start'});", tmbhBagasiBtn1);
       waitForClickable(By.xpath("(//button[@data-testid='add'])[1]")).click();
       waitForClickable(By.xpath("(//button[.//span[normalize-space()='Pilih']])[1]")).click();
       waitForClickable(By.xpath("(//div[@data-testid='baggage-selection'])[2]")).click();
       waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
       waitForClickable(By.xpath("//button[normalize-space()='Selesai Pilih Bagasi']")).click();
   }

   public void tambahkanBagasiGroup() {
       WebElement tmbhBagasiBtn1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@data-testid='add'])[1]")));
       ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'start'});", tmbhBagasiBtn1);
       waitForClickable(By.xpath("(//button[@data-testid='add'])[1]")).click();
       waitForClickable(By.xpath("(//button[.//span[normalize-space()='Pilih']])[1]")).click();
       waitForClickable(By.xpath("(//div[@data-testid='baggage-selection'])[2]")).click();
       waitForClickable(By.xpath("(//div[@data-testid='baggage-selection'])[9]")).click();
       waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
       waitForClickable(By.xpath("//button[normalize-space()='Selesai Pilih Bagasi']")).click();
   }

   public void tambahkanMakananSolo() {
       waitForClickable(By.xpath("(//button[@data-testid='add'])[1]")).click();
       sleep(700);
       waitForClickable(By.xpath("(//button[.//span[normalize-space()='Pilih']])[1]")).click();
       sleep(700);
       waitForClickable(By.xpath("(//div[contains(@class,'AddonsMultiPicker_picker_description__')])[1]")).click();
       waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
       waitForClickable(By.xpath("//button[normalize-space()='Selesai Pilih Makanan']")).click();
   }

   public void tambahkanMakananGroup() {
       waitForClickable(By.xpath("(//button[@data-testid='add'])[1]")).click();
       sleep(700);
       waitForClickable(By.xpath("(//button[.//span[normalize-space()='Pilih']])[1]")).click();
       sleep(700);
       waitForClickable(By.xpath("(//div[contains(@class,'AddonsMultiPicker_picker_description__')])[1]")).click();
       waitForClickable(By.xpath("(//div[contains(@class,'AddonsPassengerPicker_passenger_item')])[2]")).click();
       waitForClickable(By.xpath("(//div[contains(@class,'AddonsMultiPicker_picker_description__')])[1]")).click();
       waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
       waitForClickable(By.xpath("//button[normalize-space()='Selesai Pilih Makanan']")).click();
   }

   public void pilihKursiSolo() {
       waitForClickable(By.xpath("(//button[@data-testid='add'])[1]")).click();
       waitForClickable(By.xpath("(//button[.//span[normalize-space()='Pilih']])[1]")).click();
       WebElement seatContainer = driver.findElement(By.xpath("//section[contains(@class, 'SeatSelection_seat_map_container_')]"));
       WebElement seatTarget = driver.findElement(By.xpath("(//div[contains(@class,'Seat_seat__') and contains(@style,'background-color: rgb(0, 154, 255)')])[133]"));
       ((JavascriptExecutor)driver).executeScript("arguments[0].scrollTop = arguments[1].offsetTop;", seatContainer, seatTarget);
       sleep(700);
       waitForClickable(By.xpath("(//div[contains(@class,'Seat_seat__') and contains(@style,'background-color: rgb(0, 154, 255)')])[133]")).click();
       waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
       waitForClickable(By.xpath("//button[normalize-space()='Selesai Pilih Kursi']")).click();
   }

   public void pilihKursiGroup() {
       waitForClickable(By.xpath("(//button[@data-testid='add'])[1]")).click();
       waitForClickable(By.xpath("(//button[.//span[normalize-space()='Pilih']])[1]")).click();
       WebElement seatContainer = driver.findElement(By.xpath("//section[contains(@class, 'SeatSelection_seat_map_container_')]"));
       WebElement seatTarget = driver.findElement(By.xpath("(//div[contains(@class,'Seat_seat__')])[131]"));
       ((JavascriptExecutor)driver).executeScript("arguments[0].scrollTop = arguments[1].offsetTop;", seatContainer, seatTarget);
       sleep(700);
       waitForClickable(By.xpath("(//div[contains(@class,'Seat_seat__')])[132]")).click();
       waitForClickable(By.xpath("(//div[contains(@class,'Seat_seat__')])[131]")).click();
       waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
       waitForClickable(By.xpath("//button[normalize-space()='Selesai Pilih Kursi']")).click();
   }

   public void tambahkanAsuransi() {
       WebElement expBtn2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@data-testid='btn-add'])[2]")));
       ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", expBtn2);
       sleep(700);
       waitForClickable(By.xpath("(//button[@data-testid='btn-add'])[2]")).click();
       WebElement expBtn3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@data-testid='btn-add'])[3]")));
       ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", expBtn3);
       sleep(700);
       waitForClickable(By.xpath("(//button[@data-testid='btn-add'])[3]")).click();
       waitForClickable(By.xpath("(//button[@data-testid='btn-add'])[4]")).click();
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
       } else if (isVisible(By.xpath("//button[normalize-space()='Lanjutkan ke pembayaran']"))) {
           waitForClickable(By.xpath("//button[normalize-space()='Lanjutkan ke pembayaran']")).click();
       } else if (isVisible(By.xpath("//button[normalize-space()='Lanjut bayar']"))) {
           waitForClickable(By.xpath("//button[normalize-space()='Lanjut bayar']")).click();
       } else if (isVisible(By.xpath("//button[normalize-space()='Lanjut ke pembayaran']"))) {
           waitForClickable(By.xpath("//button[normalize-space()='Lanjut ke pembayaran']")).click();
       }
   }

   public void validatePaymentPage() {
        checkPaymentPage("/payment");
   }

}
