// Hooks.java dosyasının tam ve doğru hali

package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.Driver;
import utilities.ReusableMethods;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Senaryo başlıyor: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Senaryo bitti. Sonuç: " + scenario.getStatus());

        // DOĞRU KONTROL: Senaryonun durumunun PASSED olup olmadığını kontrol et
        if (scenario.getStatus().toString().equals("PASSED")) {
            System.out.println("Senaryo BAŞARILI. Kapatmadan önce 3 saniye bekleniyor...");
            ReusableMethods.sleep(3);
        }

        // Her senaryo sonunda driver'ı kapat
        Driver.closeDriver();
    }
}