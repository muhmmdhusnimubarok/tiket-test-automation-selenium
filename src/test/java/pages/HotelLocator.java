package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import utils.HelpersFile;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class HotelLocator extends HelpersFile {
    public HotelLocator(WebDriver driver) {
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

    public boolean selectFirstRegionMatch(String text) {
        String lower = text.toLowerCase(Locale.ROOT).replace("\"","\\\"");
        By candidate = By.xpath("//div[contains(@class,'HotelListItem_destination_row_wrapper__MXGR_')]//*[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" + lower + "')]");
        try {
            waitUntilVisible(candidate, 5);
            WebElement candidate1 = waitForClickable(candidate);
            sleep(700);
            candidate1.click();
            waitForInvisibility(candidate, 5);
            return true;
        } catch (Exception e1) {
            System.out.println("Gagal klik dengan locator utama, coba fallback. Pesan: " + e1.getMessage());
        }
        return false;
    }

    public boolean selectFirstCountryMatch(String text) {
        String lower = text.toLowerCase(Locale.ROOT).replace("\"","\\\"");
        By candidate = By.xpath("//div[contains(@class,'List_list_content__')]//*[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" + lower + "')]");
        try {
            waitUntilVisible(candidate, 5);
            WebElement candidate1 = waitForClickable(candidate);
            sleep(700);
            candidate1.click();
            waitForInvisibility(candidate, 5);
            return true;
        } catch (Exception e1) {
            System.out.println("Gagal klik dengan locator utama, coba fallback. Pesan: " + e1.getMessage());
        }
        return false;
    }

    public void clickHotelMenu() {
        waitForClickable(By.cssSelector("a[href='/hotel']")).click();
    }

    public void setRegionToStay(String region) {
        waitForClickable(By.xpath("//span[@data-testid='destination-input-text']")).click();
        type(By.xpath("//input[@data-testid='destination-search-box']"), region);
        boolean clicked = selectFirstRegionMatch(region);
        Assert.assertTrue(clicked, "Gagal memilih region");
    }

    public void selectCheckinAndCheckoutDate(String checkInText, String checkOutText) {
        waitForClickable(By.xpath("//span[@data-testid='date-picker']")).click();
        sleep(700);
        LocalDate checkInDate = LocalDate.parse(checkInText, formatter);
        LocalDate checkOutDate = LocalDate.parse(checkOutText, formatter);
        selectSingleDate(checkInDate);
        selectSingleDate(checkOutDate);
    }

    public void selectHotel() {
        waitForClickable(By.xpath("//button[normalize-space()='Ayo Cari']")).click();
        sleep(5000);
        waitForClickable(By.xpath("(//a[@data-testid='full-product-card'])[1]")).click();
        switchToNewTab();
        sleep(7000);
        WebElement pesanBtn = driver.findElement(By.xpath("(//button[starts-with(@data-testid, 'book-button-')])[1]"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", pesanBtn);
        sleep(700);
        waitForClickable(By.xpath("(//button[starts-with(@data-testid, 'book-button-')])[1]")).click();
    }

    public void detailPemesan() {
        waitForClickable(By.xpath("(//span[normalize-space()='Tuan'])")).click();
        type(By.xpath("(//input[@id='nama-lengkap-sesuai-identitas'])[1]"), "Muhammad Husni Mubarok");
        type(By.xpath("//input[@id='nomor-ponsel']"), "081280135417");
        type(By.xpath("//input[@id='alamat-email']"), "hsnva@gmail.com");

    }

    public void detailTamu() {
        WebElement namaTamu1 = driver.findElement(By.xpath("//span[normalize-space()='Masukkan nama tamu di sini']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", namaTamu1);
        sleep(700);
        waitForClickable(By.xpath("//span[normalize-space()='Masukkan nama tamu di sini']")).click();
        type(By.xpath("(//input[@id='nama-lengkap-sesuai-identitas'])[2]"), "Fauzan Wahid");
        waitForClickable(By.xpath("//input[@id='negarawilayah-tempat-tinggal']")).click();
        type(By.xpath("//input[@placeholder='Cari berdasarkan nama negara/wilayah']"), "Indonesia");
        sleep(700);
        boolean clicked = selectFirstCountryMatch("Indonesia");
        Assert.assertTrue(clicked, "Gagal memilih Indonesia dari daftar negara");
        waitForClickable(By.xpath("//button[normalize-space()='Simpan']")).click();
        waitForClickable(By.xpath("//button[normalize-space()='Lanjutkan']")).click();
    }

    public void pelengkapMenginap() {
        WebElement btnPilihPembayaran = driver.findElement(By.xpath("//button[normalize-space()='Pilih Pembayaran']"));
        scrollIntoView(btnPilihPembayaran);
        sleep(700);
        btnPilihPembayaran.click();
    }


}
