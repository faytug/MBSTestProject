package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration; // TimeUnit yerine Duration kullanıldı (Güncel kullanım)

public class Driver {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // driver sadece null ise yeni bir oturum aç
            switch (ConfigReader.getProperty("browser")) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "headlesschrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
            }
            // Ayarlar sadece driver ilk oluşturulduğunda bir kez yapılır
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); // Güncel ve daha makul bir süre
        }
        // return ifadesi her zaman çalışması için if bloğunun dışında olmalı
        return driver;
    }

    // closeDriver metodu getDriver metodunun DIŞINDA olmalı
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // driver'ı null yaparak bir sonraki getDriver() çağrısında yeni bir oturum açılmasını sağlarız
        }
    }
}