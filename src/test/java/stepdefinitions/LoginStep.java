package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods; // ReusableMethods import edildi
import org.openqa.selenium.interactions.Actions;
import static org.junit.jupiter.api.Assertions.*;


public class LoginStep {

    // Sınıf seviyesinde nesneleri oluşturmak iyi bir pratiktir
    LoginPage loginPage = new LoginPage(); // 'LoginPage' yerine 'loginPage' isimlendirmesi daha yaygındır
    Actions actions = new Actions(Driver.getDriver());

    @Given("user goes to Swagger page")
    public void user_goes_to_swagger_page() {
        Driver.getDriver().get(ConfigReader.getProperty("swaggerUrl"));
        try{
            // Thread.sleep(3000) yerine butonu tıklanabilir olana kadar bekle
            ReusableMethods.waitForClickability(loginPage.gelismisButon, 5).click();

            // Thread.sleep(3000) yerine linki tıklanabilir olana kadar bekle
            actions.moveByOffset(0,200).perform(); // Bu satır kaydırma için kalabilir
            ReusableMethods.waitForClickability(loginPage.proceedLink, 5).click();

        }catch (Exception e){
            System.out.println("Advanced Link ve Proceed Link istenmedi veya bulunamadı.");
        }
    }

    @When("user goes to login page")
    public void user_goes_to_login_page() {
        Driver.getDriver().get(ConfigReader.getProperty("loginUrl"));
    }

    @And("the user enters valid username")
    public void the_user_enters_valid_username() {
        // Bir input alanına veri göndermeden önce görünür olmasını beklemek iyi bir pratiktir
        ReusableMethods.waitForVisibility(loginPage.username, 5).clear();
        loginPage.username.sendKeys(ConfigReader.getProperty("validUsername"));
    }

    @And("the user enters valid password")
    public void the_user_enters_valid_password() {
        ReusableMethods.waitForVisibility(loginPage.password, 5).clear();
        loginPage.password.sendKeys(ConfigReader.getProperty("validPassword"));
    }

    @When("user clicks on SignIn button")
    public void user_clicks_on_sign_in_button() {
        // Thread.sleep(3000) yerine butonu tıklanabilir olana kadar bekle
        ReusableMethods.waitForClickability(loginPage.SignIn, 5).click();
    }

    @Then("user should be redirected to dashboard page")
    public void user_should_be_redirected_to_dashboard_page() {
        String expectedDashboardUrl = ConfigReader.getProperty("dashboardUrl");

        // Dinamik bekleme ile URL'nin "dashboard" içermesini bekle
        try {
            ReusableMethods.waitForUrlToContain("dashboard", 50);
        } catch (Exception e) {
            fail("Kullanıcı, belirtilen sürede dashboard sayfasına yönlendirilmedi.");
        }

        String actualUrl = Driver.getDriver().getCurrentUrl();

        // KONTROLÜN DOĞRUSU: Gerçekleşen URL'nin beklenen dashboard URL'sine EŞİT olduğunu doğrula
        assertEquals(expectedDashboardUrl, actualUrl, "FAIL - Beklenen dashboard URL'si ile mevcut URL uyuşmuyor.");

        System.out.println("PASS - Kullanıcı başarıyla dashboard sayfasına yönlendirildi.");
    }


    @Then("An error message should be displayed")
    public void an_error_message_should_be_displayed() {
        try {
            // Hata mesajının görünür olmasını bekle
            WebElement errorMsg = ReusableMethods.waitForVisibility(loginPage.loginErrorText, 10);
            assertEquals("Invalid username or password", errorMsg.getText());
            System.out.println("Hata mesajı başarıyla doğrulandı.");
        } catch (Exception e) {
            fail("Hata mesajı DOM'da bulunamadı veya belirtilen sürede görünür olmadı. BUG!");
        }
    }

    @When("the user enters invalid username")
    public void the_user_enters_invalid_username() {
        ReusableMethods.waitForVisibility(loginPage.username, 5).clear();
        loginPage.username.sendKeys(ConfigReader.getProperty("invalidUsername"));
    }
    @When("the user enters invalid password")
    public void the_user_enters_invalid_password() {
        ReusableMethods.waitForVisibility(loginPage.password, 5).clear();
        loginPage.password.sendKeys(ConfigReader.getProperty("invalidPassword"));
    }

    //... LoginStep.java sınıfının içinde

    @Then("User should not be redirected to the dashboard")
    public void user_should_not_be_redirected_to_the_dashboard() {
        // 1. Beklenen ve Gerçekleşen Değerler İçin Anlaşılır Değişkenler Oluştur
        // ConfigReader'dan gelmesi gereken URL'yi alıyoruz.
        String expectedDashboardUrl = ConfigReader.getProperty("dashboardUrl");

        // Tarayıcıdaki mevcut (gerçekleşen) URL'yi alıyoruz.
        String actualUrl = Driver.getDriver().getCurrentUrl();


        // 2. Doğru Assert Metodu ile Kontrol Et
        // Amacımız bu iki URL'nin birbirine EŞİT OLMADIĞINI doğrulamak.
        // `assertNotEquals` bu amaç için en uygun metottur.
        assertNotEquals(expectedDashboardUrl, actualUrl, "TEST FAIL - Kullanıcı dashboard'a yönlendirildi, ancak yönlendirilmemesi gerekiyordu.");


        // 3. Başarı Durumunu Logla
        // Eğer assert adımı başarılı olursa, bu satır çalışır ve testin beklendiği gibi geçtiğini teyit eder.
        System.out.println("PASS - Kullanıcı beklendiği gibi dashboard sayfasına yönlendirilmedi.");
    }


}