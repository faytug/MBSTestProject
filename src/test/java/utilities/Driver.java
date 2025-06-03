package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {


    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (ConfigReader.getProperty("browser")) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "headlesschrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    // WebDriverManager.firefoxdriver().setup();

                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    // WebDriverManager.edgedriver().setup();

                    driver = new EdgeDriver();
                    break;
                //case "chrome":
                //    WebDriverManager.chromedriver().setup();
                //    ChromeOptions co = new ChromeOptions();
                //    co.addArguments("--remote-allow-origins=*");
                //    driver = new ChromeDriver(co);
                //    break;
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }


    }


}

