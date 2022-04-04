import com.thoughtworks.gauge.Logger;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.logging.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage extends BaseTest {

    @Step("<int> saniye kadar bekle")
    public void waitForsecond(int s) throws InterruptedException {
        Thread.sleep(1000 * s);
        Logger.info(s + " Saniye kadar bekleniyor");
    }

    @Step("<id> elemetin sayfada gorunur olduğunu kontrol et ve tıkla")
    public void findByelementEndclick(String id) {
        MobileElement element = appiumDriver.findElement(By.id(id));
        if (element.isDisplayed()) {
            element.click();
            Logger.info("Element Gorunur Tiklaniyor...");
        } else {
            Logger.info("Element Gorunur Degil...");
        }
    }

    @Step("Sayfayı aşağı doğru kaydır")
    public void swipeUp() {
        final int ANIMATION_TIME = 200; // ms
        final int PRESS_TIME = 200; // ms
        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;
        Dimension dims = appiumDriver.manage().window().getSize();
        System.out.println("Telefon Ekran Boyutu " + dims);
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        System.out.println("Başlangıç noktası " + pointOptionStart);
        pointOptionEnd = PointOption.point(dims.width / 4, dims.height / 6);
        System.out.println("Bitiş noktası " + pointOptionEnd);
        Logger.info("SAYFA SCROLL EDILDI..");
        try {
            new TouchAction(appiumDriver)
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    @Step("Xpath <xpath> li elementi bul ve tıkla")
    public void clickByxpath(String xpath) {
        appiumDriver.findElement(By.xpath(xpath)).click();
    }

    @Step("Id <id> li elementi bul ve tıkla")
    public void clickByid(String id) {
        appiumDriver.findElement(By.id(id)).click();
    }

    @Step("Rastgele <xpath> ürün seçilir")
    public void randomJean(String xpath) {
        List<MobileElement> elements = appiumDriver.findElements(By.xpath(xpath));
        Random rnd = new Random();
        int rndInt = rnd.nextInt(elements.size());
        elements.get(rndInt).click();
        Logger.info("Rastgele Urun Secimi Yapiliyor...");
    }

    @Step("<id> Kayıtlı kullanıcı bilgilerini <text> gir")
    public void enterUser(String id, String text) {
        appiumDriver.findElement(By.id(id)).sendKeys(text);
        Logger.info("Kullanici bilgileri giriliyor...");
    }

    @Step("<id> Kayıtlı Sifre <text> Giriliyor")
    public void enterPasswd(String id, String text) {
        appiumDriver.findElement(By.id(id)).sendKeys(text);
        Logger.info("Sifre bilgileri giriliyor...");
    }

    @Step("Rastgele <id> Beden seçilir")
    public void randomSize(String id) {
        List<MobileElement> elements = appiumDriver.findElements(By.id(id));
        Random rnd = new Random();
        int rndInt = rnd.nextInt(elements.size());
        elements.get(rndInt).click();
        Logger.info("Rastgele Beden Secimi Yapiliyor...");
    }
    @Step("Id <id> Uygulama Kontrolü")
    public void checkApp(String id){
        MobileElement element = appiumDriver.findElement(By.id(id));
        if (element.isDisplayed()) {
            Logger.info("Uygulama acildi!!");
        }
        else
        {
            Logger.info("Uygulama ACILMADII!!");
        }
    }
    @Step("Id <id> Sayfa Kontrolu")
    public void checkPages(String id){
        MobileElement element = appiumDriver.findElement(By.id(id));
        if (element.isDisplayed()) {
            Logger.info("Sayfa Acildi...");
        }
        else
        {
            Logger.info("Sayfa ACILMADI!");
        }
    }
}